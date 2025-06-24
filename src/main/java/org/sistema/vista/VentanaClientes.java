package org.sistema.vista;

import org.sistema.entity.Cliente;
import org.sistema.entity.Cuenta;
import org.sistema.interfaces.CrudInterface;
import org.sistema.model.CrudClienteModel;
import org.sistema.model.CrudCuentaModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VentanaClientes extends JFrame{
    private CrudInterface<Cliente, Integer> crudClienteModel;
    private CrudInterface<Cuenta, Integer> crudCuentaModel;

    private LienzoCentral lienzoCentral;
    private LienzoHeader lienzoHeader;
    private LienzoFooter lienzoFooter;
    private VentanaRegistroCliente ventanaRegCliente;
    private VentanaRegCuenta ventanaRegCuenta;

    public VentanaClientes() throws HeadlessException {
        super();
        this.crudClienteModel = new CrudClienteModel();
        this.crudCuentaModel = new CrudCuentaModel();
        this.lienzoCentral = new LienzoCentral();
        this.lienzoHeader = new LienzoHeader();
        this.lienzoFooter = new LienzoFooter();
        this.setTitle("Sección Clientes");
        this.setSize(960, 514);
        this.setLocationRelativeTo(rootPane);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(lienzoHeader, BorderLayout.NORTH);
        this.add(lienzoCentral, BorderLayout.CENTER);
        this.add(lienzoFooter, BorderLayout.SOUTH);
    }

    class LienzoHeader extends JPanel{
        private JLabel lblTitulo = new JLabel("GESTIÓN DE CLIENTES", SwingConstants.CENTER);

        public LienzoHeader ( ){
            super();
            this.setLayout(new BorderLayout());
            this.setBackground(new Color(30, 31, 34));
            this.lblTitulo.setForeground(Color.WHITE);
            this.lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
            this.add(lblTitulo, BorderLayout.CENTER);
        }

        @Override
        public Dimension getPreferredSize(){
            Container contenedorPadre = getParent();
            int ancho = contenedorPadre.getWidth();
            int alto = (int) (contenedorPadre.getHeight() * 0.12);
            return new Dimension(ancho, alto);
        }
    }

    class LienzoCentral extends JPanel {
        //panel superior de tabla
        private JPanel panelTabla = new JPanel();
        private JTable tablaDatos = new JTable();
        private JScrollPane scpTabla = new JScrollPane();
        private String[] columnas = {"Id_Cliente", "Direccion", "Tipo_Cliente", "Nombre", "Apellido", "DNI", "Razon_Social", "RUC"};
        private Object[][] datos;
        private DefaultTableModel modeloTabla = new DefaultTableModel();
        private GridBagConstraints gbcSuperior = new GridBagConstraints();
        // panel inferior de botones
        private JPanel panelBtns = new JPanel();
        private JButton btnGuardar = new JButton("Guardar Cambios en la fila");
        private JButton btnDescartar = new JButton("Descatar Cambios");
        private JButton btnRegCli = new JButton("Registrar Cliente");
        private JButton btnRegCuenta = new JButton("Abrir una Cuenta");
        private JButton btnEliminar = new JButton("Eliminar Cliente");
        private GridBagConstraints gbcInferior = new GridBagConstraints();

        public LienzoCentral() {
            super();
            this.setLayout(new BorderLayout());
            //panel tabla
            this.panelTabla.setLayout(new GridBagLayout());
            this.gbcSuperior.insets = new Insets(10, 10, 10, 10);
            this.gbcSuperior.weightx = 1; this.gbcSuperior.weighty = 1;
            this.gbcSuperior.fill = GridBagConstraints.BOTH;

            this.datos = new Object[crudClienteModel.findAll().size()][columnas.length];
            for (int i = 0; i < crudClienteModel.findAll().size(); i++) {
                Cliente c = crudClienteModel.findAll().get(i);
                datos[i][0] = c.getIdCliente();
                datos[i][1] = c.getDireccion();
                datos[i][2] = c.getTipoCliente() != null ? c.getTipoCliente() : "n/a";
                datos[i][3] = c.getNombre() != null ? c.getNombre() : "n/a";
                datos[i][4] = c.getApellido() != null ? c.getApellido() : "n/a";
                datos[i][5] = c.getDni() != null ? c.getDni() : "n/a";
                datos[i][6] = c.getRazonSocial() != null ? c.getRazonSocial() : "n/a";
                datos[i][7] = c.getRuc() != null ? c.getRuc() : "n/a";
            }

            this.modeloTabla.setDataVector(datos, columnas);
            this.tablaDatos.setModel(modeloTabla);
            this.scpTabla.setViewportView(tablaDatos);
            this.panelTabla.add(scpTabla, gbcSuperior);
            this.add(panelTabla, BorderLayout.CENTER);
            //panel btns
            this.panelBtns.setLayout(new GridBagLayout());
            this.gbcInferior.insets = new Insets(10, 10, 10, 10);
            this.btnGuardar.setBackground(new Color(0x333533));
            this.btnGuardar.setForeground(Color.WHITE);
            this.btnDescartar.setBackground(new Color(0x333533));
            this.btnDescartar.setForeground(Color.WHITE);
            this.btnRegCli.setBackground(new Color(0x333533));
            this.btnRegCli.setForeground(Color.WHITE);
            this.btnRegCuenta.setBackground(new Color(0x333533));
            this.btnRegCuenta.setForeground(Color.WHITE);
            this.btnEliminar.setBackground(new Color(0x333533));
            this.btnEliminar.setForeground(Color.WHITE);
            this.panelBtns.add(btnGuardar, gbcInferior);
            this.panelBtns.add(btnDescartar, gbcInferior);
            this.panelBtns.add(btnRegCli, gbcInferior);
            this.panelBtns.add(btnRegCuenta, gbcInferior);
            this.panelBtns.add(btnEliminar, gbcInferior);
            this.add(panelBtns, BorderLayout.SOUTH);

            btnGuardar.addActionListener(e -> {
                int fila = tablaDatos.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Seleccione una fila para guardar los cambios.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Cliente cliente = getClienteDeFila(fila);
                if (crudClienteModel.update(cliente)) {
                    JOptionPane.showMessageDialog(this, "Cliente actualizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo actualizar el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            btnDescartar.addActionListener(e -> {
                actualizarTabla();
                JOptionPane.showMessageDialog(this, "Cambios descartados.", "Información", JOptionPane.INFORMATION_MESSAGE);
            });

            btnRegCli.addActionListener(e -> {
                ventanaRegCliente = new VentanaRegistroCliente();
                ventanaRegCliente.setVisible(true);
                dispose();
            });

            btnRegCuenta.addActionListener(e -> {
                int fila = tablaDatos.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Seleccione un cliente para abrir una cuenta.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Integer idCliente = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
                ventanaRegCuenta = new VentanaRegCuenta(idCliente);
                ventanaRegCuenta.setVisible(true);
            });

            btnEliminar.addActionListener(e -> {
                int fila = tablaDatos.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int idCliente = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
                int confirmacion = JOptionPane.showConfirmDialog(this, "¿Eliminar Cliente?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (confirmacion != JOptionPane.YES_OPTION) return;
                java.util.List<Cuenta> cuentaList = crudCuentaModel.findAll();

                java.util.List<Cuenta> cuentasAEliminar = new ArrayList<>();

                cuentaList.forEach(cuenta -> {
                    if (cuenta.getCliente().getIdCliente() == idCliente) {
                        cuentasAEliminar.add(cuenta);
                    }
                });

                cuentasAEliminar.forEach(cuenta -> {
                    crudCuentaModel.delete(cuenta.getIdCuenta());
                });



                if (crudClienteModel.delete(idCliente)) {
                    JOptionPane.showMessageDialog(this, "Cliente eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    actualizarTabla();
                }
            });
        }

        private Cliente getClienteDeFila(int fila) {
            Integer idCliente = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
            String direccion = modeloTabla.getValueAt(fila, 1).toString();
            String tipoCliente = modeloTabla.getValueAt(fila, 2).toString();

            Cliente cliente = new Cliente();
            cliente.setIdCliente(idCliente);
            cliente.setDireccion(direccion);
            cliente.setTipoCliente(tipoCliente);

            if (tipoCliente.equalsIgnoreCase("natural")) {
                String nombre = modeloTabla.getValueAt(fila, 3).toString();
                String apellido = modeloTabla.getValueAt(fila, 4).toString();
                String dni = modeloTabla.getValueAt(fila, 5).toString();

                cliente.setNombre(nombre.equals("n/a") ? "" : nombre);
                cliente.setApellido(apellido.equals("n/a") ? "" : apellido);
                cliente.setDni(dni.equals("n/a") ? "" : dni);
                cliente.setRazonSocial(null);
                cliente.setRuc(null);
            } else if (tipoCliente.equalsIgnoreCase("empresa")) {
                String razonSocial = modeloTabla.getValueAt(fila, 6).toString();
                String ruc = modeloTabla.getValueAt(fila, 7).toString();

                cliente.setNombre(null);
                cliente.setApellido(null);
                cliente.setDni(null);
                cliente.setRazonSocial(razonSocial.equals("n/a") ? "" : razonSocial);
                cliente.setRuc(ruc.equals("n/a") ? "" : ruc);
            }

            return cliente;
        }

        private boolean actualizarTabla() {
            modeloTabla.setRowCount(0);
            for (Cliente c : crudClienteModel.findAll()) {
                Object[] fila = new Object[8];
                fila[0] = c.getIdCliente();
                fila[1] = c.getDireccion();
                fila[2] = c.getTipoCliente() != null ? c.getTipoCliente() : "n/a";
                fila[3] = c.getNombre() != null ? c.getNombre() : "n/a";
                fila[4] = c.getApellido() != null ? c.getApellido() : "n/a";
                fila[5] = c.getDni() != null ? c.getDni() : "n/a";
                fila[6] = c.getRazonSocial() != null ? c.getRazonSocial() : "n/a";
                fila[7] = c.getRuc() != null ? c.getRuc() : "n/a";
                modeloTabla.addRow(fila);
            }
            return true;
        }
    }

    class LienzoFooter extends JPanel{
        private JButton btnSalir = new JButton("Salir");
        private JButton btnAyuda = new JButton("Ayuda");
        public LienzoFooter (){
            super();
            this.setLayout(new FlowLayout(FlowLayout.RIGHT));
            this.setBackground(new Color(37, 55, 40));
            this.setForeground(Color.WHITE);
            this.add(btnAyuda);
            this.add(btnSalir);
            this.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));

            btnSalir.addActionListener(e -> {
                dispose();
            });
        }

        @Override
        public Dimension getPreferredSize(){
            Container contenedorPadre = getParent();
            int ancho = contenedorPadre.getWidth();
            int alto = (int) (contenedorPadre.getHeight() * 0.08);
            return new Dimension(ancho, alto);
        }
    }
}