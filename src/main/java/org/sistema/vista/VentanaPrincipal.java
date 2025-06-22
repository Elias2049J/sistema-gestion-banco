package org.sistema.vista;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class VentanaPrincipal extends JFrame {

    private VentanaClientes ventanaClientes;
    private VentanaRegistroCliente ventanaRegistroCliente;
    private LienzoCentral lienzoCentral = new LienzoCentral();
    private LienzoFooter lienzoFooter = new LienzoFooter();

    public VentanaPrincipal() throws HeadlessException {
        super();
        this.setTitle("Sistema Bancario");
        this.setSize(600, 500);
        this.setLocationRelativeTo(rootPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(lienzoCentral, BorderLayout.CENTER);
        this.add(lienzoFooter, BorderLayout.SOUTH);
    }

    class LienzoCentral extends JPanel {
        private JButton btnRegistro = new JButton("Registrar Cliente");
        private JButton btnVerCliente = new JButton("Gestionar Clientes");
        public LienzoCentral() {
            super();
            this.setLayout(new GridBagLayout());
            setBackground(new Color(240, 245, 255));
            GridBagConstraints gbcPadre = new GridBagConstraints();
            gbcPadre.gridy = 1;
            gbcPadre.fill = GridBagConstraints.BOTH;
            gbcPadre.weighty = 1;

            JPanel panelClientes = new JPanel(new GridBagLayout());
            panelClientes.setBackground(Color.WHITE);
            panelClientes.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(200,200,200)));
            GridBagConstraints gbcP = new GridBagConstraints();
            gbcP.insets = new Insets(10, 10, 10, 10);
            gbcP.gridx = 0;
            gbcP.fill = GridBagConstraints.HORIZONTAL;
            panelClientes.add(btnRegistro);
            panelClientes.add(btnVerCliente);

            gbcPadre.gridx = 0;
            gbcPadre.weightx = 1;
            this.add(panelClientes, gbcPadre);
            gbcPadre.gridx = 1;

            btnVerCliente.addActionListener(e-> {
                ventanaClientes = new VentanaClientes();
                ventanaClientes.setVisible(true);
            });

            btnRegistro.addActionListener(e-> {
                try {
                    ventanaRegistroCliente = new VentanaRegistroCliente();
                    ventanaRegistroCliente.setVisible(true);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
    }

    class LienzoFooter extends JPanel{
        private JButton btnSalir = new JButton("Salir");
        private JButton btnAyuda = new JButton("Ayuda");
        public LienzoFooter (){
            super();
            this.setLayout(new FlowLayout(FlowLayout.RIGHT));
            this.setBackground(new Color(37, 168, 63));
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