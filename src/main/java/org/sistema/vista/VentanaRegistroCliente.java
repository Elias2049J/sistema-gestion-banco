package org.sistema.vista;

import org.sistema.entity.Cliente;
import org.sistema.interfaces.CrudInterface;
import org.sistema.model.CrudClienteModel;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroCliente extends JFrame{
    private CrudInterface<Cliente, Integer> crudClienteModel;
    private LienzoHeader lienzoHeader;
    private LienzoCentral lienzoCentral;
    private LienzoFooter lienzoFooter;

    public VentanaRegistroCliente(){
        super();
        this.crudClienteModel = new CrudClienteModel();
        this.lienzoHeader = new LienzoHeader();
        this.lienzoCentral = new LienzoCentral();
        this.lienzoFooter = new LienzoFooter();
        this.setTitle("Registro de Clientes");
        this.setSize(600, 500);
        this.setLocationRelativeTo(rootPane);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(lienzoHeader, BorderLayout.NORTH);
        this.add(lienzoCentral, BorderLayout.CENTER);
        this.add(lienzoFooter, BorderLayout.SOUTH);
    }

    class LienzoCentral extends JPanel {
        private JLabel lblRegistro = new JLabel("INGRESAR DATOS", SwingConstants.CENTER);

        private JComboBox<String> cboTipoCliente = new JComboBox<>();

        private JLabel lblNombre = new JLabel("Nombres:");
        private JTextField jtfNombre = new JTextField(20);
        private JLabel lblApellido = new JLabel("Apellidos:");
        private JTextField jtfApellido = new JTextField(20);
        private JLabel lblDni = new JLabel("DNI:");
        private JTextField jtfDni = new JTextField(20);
        private JLabel lblDireccion = new JLabel("Dirección:");
        private JTextField jtfDireccion = new JTextField(20);

        private JLabel lblRazonSocial = new JLabel("Razón Social:");
        private JTextField jtfRazonSocial = new JTextField(20);
        private JLabel lblRuc = new JLabel("RUC:");
        private JTextField jtfRuc = new JTextField(20);

        private JButton btnRegistrar = new JButton("Registrar");

        public LienzoCentral() {
            super();
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 8, 8, 8);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            jtfRazonSocial.setEditable(false);
            jtfRuc.setEditable(false);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 5;
            gbc.anchor = GridBagConstraints.CENTER;
            lblRegistro.setFont(new Font("Arial", Font.BOLD, 18));
            this.add(lblRegistro, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 5;
            gbc.anchor = GridBagConstraints.CENTER;
            cboTipoCliente.addItem("Natural");
            cboTipoCliente.addItem("Empresa");
            this.add(cboTipoCliente, gbc);

            gbc.anchor = GridBagConstraints.WEST;
            gbc.gridwidth = 1;

            gbc.gridx = 0;
            gbc.gridy = 2;
            this.add(lblNombre, gbc);
            gbc.gridy++;
            this.add(lblApellido, gbc);
            gbc.gridy++;
            this.add(lblDni, gbc);
            gbc.gridy++;
            this.add(lblDireccion, gbc);
            gbc.gridy++;
            this.add(lblRazonSocial, gbc);
            gbc.gridy++;
            this.add(lblRuc, gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridheight = 7;
            this.add(Box.createHorizontalStrut(30), gbc);
            gbc.gridheight = 1;

            gbc.gridx = 2;
            gbc.gridy = 2;
            this.add(jtfNombre, gbc);
            gbc.gridy++;
            this.add(jtfApellido, gbc);
            gbc.gridy++;
            this.add(jtfDni, gbc);
            gbc.gridy++;
            this.add(jtfDireccion, gbc);
            gbc.gridy++;
            this.add(jtfRazonSocial, gbc);
            gbc.gridy++;
            this.add(jtfRuc, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 3;
            gbc.anchor = GridBagConstraints.CENTER;
            this.add(btnRegistrar, gbc);

            cboTipoCliente.addItemListener(e -> {
                if (cboTipoCliente.getSelectedItem() == "Natural") {
                    jtfNombre.setEditable(true);
                    jtfApellido.setEditable(true);
                    jtfDni.setEditable(true);
                    jtfRazonSocial.setEditable(false);
                    jtfRuc.setEditable(false);
                    jtfDireccion.setEditable(true);
                    jtfRazonSocial.setText("");
                    jtfRuc.setText("");
                } else {
                    jtfNombre.setText("");
                    jtfApellido.setText("");
                    jtfDni.setText("");
                    jtfNombre.setEditable(false);
                    jtfApellido.setEditable(false);
                    jtfDni.setEditable(false);
                    jtfRazonSocial.setEditable(true);
                    jtfRuc.setEditable(true);
                    jtfDireccion.setEditable(true);
                }
            });

            btnRegistrar.addActionListener(e -> {
                String nombre = jtfNombre.getText() != null ? jtfNombre.getText() : "";
                String apellido = jtfApellido.getText() != null ? jtfApellido.getText() : "";
                String dni = jtfDni.getText() != null ? jtfDni.getText() : "";
                String direccion = jtfDireccion.getText() != null ? jtfDireccion.getText() : "";
                String razonSocial = jtfRazonSocial.getText() != null ? jtfRazonSocial.getText() : "";
                String ruc = jtfRuc.getText() != null ? jtfRuc.getText() : "";
                String tipoCliente = String.valueOf(cboTipoCliente.getSelectedItem());

                Cliente c  = new Cliente(null, direccion, null, tipoCliente, nombre, apellido, dni, razonSocial, ruc);
                c.setDireccion(direccion);
                c.setTipoCliente(tipoCliente);
                c.setNombre(nombre);
                c.setApellido(apellido);
                c.setDni(dni);
                c.setRazonSocial(razonSocial);
                c.setRuc(ruc);
                if (crudClienteModel.crear(c)) {
                    JOptionPane.showMessageDialog(this, "Se ha registrado correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Hubo un Error al registrar, compruebe los campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }

    class LienzoHeader extends JPanel{
        LienzoHeader (){
            super();
            this.setLayout(new BorderLayout());
            this.setBackground(new Color(37, 55, 40));
        }

        @Override
        public Dimension getPreferredSize(){
            Container contenedorPadre = getParent();
            int ancho = contenedorPadre.getWidth();
            //se castea a int
            int alto = (int) (contenedorPadre.getHeight() * 0.12);
            return new Dimension(ancho, alto);
        }
    }

    class LienzoFooter extends JPanel{
        private JButton btnSalir = new JButton("Salir");
        public LienzoFooter (){
            super();
            this.setLayout(new BorderLayout());
            this.setBackground(new Color(37, 55, 40));
            this.setForeground(Color.WHITE);
            this.add(btnSalir, BorderLayout.EAST);
            this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            btnSalir.addActionListener(e -> {
                dispose();
            });
        }

        @Override
        public Dimension getPreferredSize(){
            Container contenedorPadre = getParent();
            int ancho = contenedorPadre.getWidth();
            //se castea a int
            int alto = (int) (contenedorPadre.getHeight() * 0.08);
            return new Dimension(ancho, alto);
        }
    }
}