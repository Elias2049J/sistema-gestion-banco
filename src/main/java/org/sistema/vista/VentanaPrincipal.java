package org.sistema.vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private VentanaClientes ventanaClientes;
    private VentanaCuenta ventanaCuenta;
    private LienzoCentral lienzoCentral;
    private LienzoFooter lienzoFooter;
    private LienzoHeader lienzoHeader;

    public VentanaPrincipal() throws HeadlessException {
        super();
        this.lienzoFooter = new LienzoFooter();
        this.lienzoCentral = new LienzoCentral();
        this.lienzoHeader = new LienzoHeader();
        this.setTitle("Sistema Bancario");
        this.setSize(960, 514);
        this.setLocationRelativeTo(rootPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(lienzoHeader, BorderLayout.NORTH);
        this.add(lienzoCentral, BorderLayout.CENTER);
        this.add(lienzoFooter, BorderLayout.SOUTH);
    }

    class LienzoHeader extends JPanel{
        private JLabel lblTitulo = new JLabel("SISTEMA DE GESTIÓN", SwingConstants.CENTER);
        private GridBagConstraints gbcHeader = new GridBagConstraints();

        public LienzoHeader (){
            super();
            this.setLayout(new GridBagLayout());
            this.setBackground(new Color(37, 55, 40));
            this.gbcHeader.insets = new Insets(10, 10, 10, 10);

            gbcHeader.gridx = 1;
            gbcHeader.gridy = 1;
            gbcHeader.weighty = 1;
            gbcHeader.weightx = 1;
            gbcHeader.gridwidth = 1;
            this.lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
            this.lblTitulo.setForeground(Color.WHITE);
            this.add(lblTitulo, gbcHeader);
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(getParent().getWidth(), 60);
        }
    }

    class LienzoCentral extends JPanel {
        private Image imagenFondo = new ImageIcon(
                getClass().getResource("/img/pantallaprincipal.png")
        ).getImage();
        private GridBagConstraints gbcCentro = new GridBagConstraints();
        private JButton btnClientes = new JButton("Clientes");
        private JButton btnCuentas = new JButton("Cuentas");
        Dimension fixedSize = new Dimension(300, 80);

        public LienzoCentral() {
            super();
            setOpaque(false);
            this.setLayout(new GridBagLayout());

            gbcCentro.gridx = 0;
            gbcCentro.gridy = 0;
            gbcCentro.weighty = 2;
            gbcCentro.weightx = 4;
            gbcCentro.gridwidth = 1;
            gbcCentro.fill = GridBagConstraints.VERTICAL;
            this.add(Box.createHorizontalStrut(10), gbcCentro);
            gbcCentro.gridx++;
            gbcCentro.weighty = 1;
            gbcCentro.weightx = 1;
            gbcCentro.gridwidth = 1;
            gbcCentro.fill = GridBagConstraints.NONE;
            btnClientes.setPreferredSize(fixedSize);
            btnClientes.setMaximumSize(fixedSize);
            btnClientes.setMinimumSize(fixedSize);
            this.add(btnClientes, gbcCentro);
            gbcCentro.gridy++;
            gbcCentro.gridwidth = 1;
            btnCuentas.setPreferredSize(fixedSize);
            btnCuentas.setMaximumSize(fixedSize);
            btnCuentas.setMinimumSize(fixedSize);
            this.add(btnCuentas, gbcCentro);

            btnClientes.addActionListener(e -> {
                ventanaClientes = new VentanaClientes();
                ventanaClientes.setVisible(true);
            });

            btnCuentas.addActionListener(e -> {
                ventanaCuenta = new VentanaCuenta();
                ventanaCuenta.setVisible(true);
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
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