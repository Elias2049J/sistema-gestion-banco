package org.sistema.vista;

import org.sistema.entity.Cliente;
import org.sistema.entity.Cuenta;
import org.sistema.interfaces.CrudInterface;
import org.sistema.model.CrudCuentaModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaCuenta extends JFrame {
    private LienzoHeader lienzoHeader;
    private LienzoCentral lienzoCentral;
    private LienzoFooter lienzoFooter;

    public VentanaCuenta() {
        super("GESTIÓN DE CUENTAS BANCARIAS");
        setSize(960, 514);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        lienzoHeader = new LienzoHeader();
        lienzoCentral = new LienzoCentral();
        lienzoFooter = new LienzoFooter();

        add(lienzoHeader, BorderLayout.NORTH);
        add(lienzoCentral, BorderLayout.CENTER);
        add(lienzoFooter, BorderLayout.SOUTH);
    }

    class LienzoHeader extends JPanel {
        public LienzoHeader() {
            setBackground(new Color(30, 31, 34));
            JLabel titulo = new JLabel("GESTIÓN DE CUENTAS DE USUARIO");
            titulo.setForeground(Color.WHITE);
            titulo.setFont(new Font("Arial", Font.BOLD, 22));
            add(titulo);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(getWidth(), 60);
        }
    }

    class LienzoCentral extends JPanel {
        private CrudInterface<Cuenta, Integer> crudCuentaModel = new CrudCuentaModel();
        private JTable tablaCuentas;
        private DefaultTableModel modeloTabla;
        private JButton btnGuardarCambios = new JButton("Guardar cambios en la fila");
        private JButton btnEliminar = new JButton("Eliminar Cuenta");

        public LienzoCentral() {
            setLayout(new BorderLayout());
            setBackground(Color.WHITE);

            String[] columnas = {"Id_Cuenta", "Saldo", "Tipo_Moneda", "Tipo_Cuenta", "Tasa_Interes", "Limite_Sobre_Giros", "Id_Cliente"};
            modeloTabla = new DefaultTableModel(columnas, 0);
            tablaCuentas = new JTable(modeloTabla);
            JScrollPane scrollPane = new JScrollPane(tablaCuentas);
            add(scrollPane, BorderLayout.CENTER);

            cargarDatosTabla();

            JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            configurarBoton(btnGuardarCambios);
            configurarBoton(btnEliminar);

            panelBotones.add(btnGuardarCambios);
            panelBotones.add(btnEliminar);
            add(panelBotones, BorderLayout.SOUTH);

            btnEliminar.addActionListener(e -> {

                int fila = tablaCuentas.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Integer idCuenta = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
                int confirmacion = JOptionPane.showConfirmDialog(this, "¿Eliminar Cliente?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (crudCuentaModel.delete(idCuenta)) {
                    modeloTabla.removeRow(fila);
                    JOptionPane.showMessageDialog(this, "Cuenta eliminada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar la cuenta.");
                }
            });

            btnGuardarCambios.addActionListener(e -> {
                int fila = tablaCuentas.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona una cuenta para editar.");
                } else {
                    try {
                        Cuenta cuenta = getCuentaDeFila(fila);
                        if (crudCuentaModel.update(cuenta)) {
                            JOptionPane.showMessageDialog(this, "Cuenta actualizada correctamente.");
                        } else {
                            JOptionPane.showMessageDialog(this, "No se pudo actualizar la cuenta.");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage());
                    }
                }
            });
        }

        private void cargarDatosTabla() {
            List<Cuenta> cuentas = crudCuentaModel.findAll();
            for (Cuenta c : cuentas) {
                modeloTabla.addRow(new Object[]{
                        c.getIdCuenta(),
                        c.getSaldo(),
                        c.getTipoMoneda(),
                        c.getTipoCuenta() != null ? c.getTipoCuenta() : "n/a",
                        c.getTasaInteres() != null ? c.getTasaInteres() : "n/a",
                        c.getLimiteSobreGiros() != null ? c.getLimiteSobreGiros() : "n/a",
                        c.getCliente() != null ? c.getCliente().getIdCliente() : "n/a"
                });
            }
        }

        private Cuenta getCuentaDeFila(int fila) {
            Integer id = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
            double saldo = Double.parseDouble(modeloTabla.getValueAt(fila, 1).toString());
            String tipoMoneda = modeloTabla.getValueAt(fila, 2).toString();
            String tipoCuenta = modeloTabla.getValueAt(fila, 3).toString();
            String tasa = modeloTabla.getValueAt(fila, 4) != null ? modeloTabla.getValueAt(fila, 4).toString() : "";
            String lsg = modeloTabla.getValueAt(fila, 5) != null ? modeloTabla.getValueAt(fila, 5).toString() : "";
            Integer idCliente = Integer.parseInt(modeloTabla.getValueAt(fila, 6).toString());

            Cuenta cuenta = new Cuenta();
            cuenta.setIdCuenta(id);
            cuenta.setSaldo(saldo);
            cuenta.setTipoMoneda(tipoMoneda);
            cuenta.setTipoCuenta(tipoCuenta);
            cuenta.setCliente(new Cliente());
            cuenta.getCliente().setIdCliente(idCliente);
            if (tipoCuenta.equalsIgnoreCase("ahorro")) {
                cuenta.setTasaInteres(!tasa.isEmpty() ? Double.parseDouble(tasa) : null);
                cuenta.setLimiteSobreGiros(null);
            } else if (tipoCuenta.equalsIgnoreCase("corriente")) {
                cuenta.setLimiteSobreGiros(!lsg.isEmpty() ? Double.parseDouble(lsg) : null);
                cuenta.setTasaInteres(null);
            }
            return cuenta;
        }

        private void configurarBoton(JButton boton) {
            boton.setBackground(new Color(0x1B4686));
            boton.setForeground(Color.WHITE);
            boton.setFocusPainted(false);
        }
    }

    class LienzoFooter extends JPanel {
        public LienzoFooter() {
            setLayout(new FlowLayout(FlowLayout.RIGHT));
            setBackground(new Color(30, 31, 34));
            JButton btnSalir = new JButton("Salir");
            btnSalir.addActionListener(e -> dispose());
            btnSalir.setForeground(Color.WHITE);
            btnSalir.setBackground(Color.RED);
            add(btnSalir);

            btnSalir.addActionListener(e -> {
                dispose();
            });
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(getWidth(), 50);
        }
    }
}
