package org.sistema.vista;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class VentanaRegistroCliente extends JFrame{
    private LienzoHeader lienzoHeader = new LienzoHeader();
    private LienzoCentral lienzoCentral = new LienzoCentral();
    private LienzoFooter lienzoFooter = new LienzoFooter();

    public VentanaRegistroCliente() throws FileNotFoundException {
        super();
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

        private JLabel lblNombre = new JLabel("Nombres:");
        private JTextField jtfNombre = new JTextField(20);
        private JLabel lblApellido = new JLabel("Apellidos:");
        private JTextField jtfApellido = new JTextField(20);
        private JLabel lblDni = new JLabel("DNI:");
        private JTextField jtfDni = new JTextField(20);
        private JLabel lblDireccion = new JLabel("Dirección:");
        private JTextField jtfDireccion = new JTextField(20);

        private JButton btnRegistrar = new JButton("Registrar");

        public LienzoCentral() {
            super();
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 8, 8, 8);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 5;
            gbc.anchor = GridBagConstraints.CENTER;
            lblRegistro.setFont(new Font("Arial", Font.BOLD, 18));
            this.add(lblRegistro, gbc);

            gbc.anchor = GridBagConstraints.WEST;
            gbc.gridwidth = 1;

            gbc.gridx = 0;
            gbc.gridy = 1;
            this.add(lblNombre, gbc);
            gbc.gridy++;
            this.add(lblApellido, gbc);
            gbc.gridy++;
            this.add(lblDni, gbc);
            gbc.gridy++;
            this.add(lblDireccion, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridheight = 7;
            this.add(Box.createHorizontalStrut(30), gbc);
            gbc.gridheight = 1;

            gbc.gridx = 2;
            gbc.gridy = 1;
            this.add(jtfNombre, gbc);
            gbc.gridy++;
            this.add(jtfApellido, gbc);
            gbc.gridy++;
            this.add(jtfDni, gbc);
            gbc.gridy++;
            this.add(jtfDireccion, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 3;
            gbc.anchor = GridBagConstraints.CENTER;
            this.add(btnRegistrar, gbc);

            btnRegistrar.addActionListener(e -> {
                String nombre = jtfNombre.getText();
                String apellido = jtfApellido.getText();
                String dni = jtfDni.getText();
                String direccion = jtfDireccion.getText();

                JOptionPane.showMessageDialog(this, "Se ha registrado correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
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