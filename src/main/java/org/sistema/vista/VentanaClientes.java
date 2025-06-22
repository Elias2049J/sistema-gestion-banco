package org.sistema.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaClientes extends JFrame{
    private LienzoCentral lienzoCentral = new LienzoCentral();
    private LienzoHeader lienzoHeader = new LienzoHeader(lienzoCentral);
    private LienzoFooter lienzoFooter = new LienzoFooter();

    public VentanaClientes() throws HeadlessException {
        super();
        this.setTitle("Sección Clientes");
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
        private JButton btnInicio = new JButton("Inicio");
        private JButton btnClientes = new JButton("Clientes");
        private JButton btnCuentas = new JButton("Cuentas");
        private LienzoCentral lienzoCentral;

        public LienzoHeader (LienzoCentral lienzoCentral){
            super();
            this.setLayout(new GridBagLayout());
            this.setBackground(new Color(0, 137, 1));
            this.gbcHeader.insets = new Insets(10, 10, 10, 10);
            this.lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
            this.lblTitulo.setForeground(Color.WHITE);
            this.btnInicio.setBackground(new Color(0x064706));
            this.btnInicio.setForeground(new Color(0xFFFEFE));
            this.btnClientes.setBackground(new Color(0x064706));
            this.btnClientes.setForeground(new Color(0xFFFEFE));
            this.btnCuentas.setBackground(new Color(0x064706));
            this.btnCuentas.setForeground(new Color(0xFFFEFE));
            this.add(lblTitulo, gbcHeader);
            this.add(btnInicio, gbcHeader);
            this.add(btnClientes, gbcHeader);
            this.add(btnCuentas, gbcHeader);

            btnInicio.addActionListener(e -> {
                lienzoCentral.removeAll();
            });
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
        private String[] columnas = {"IdCliente", "DNI", "Nombres", "Apellidos", "Dirección"};
        private Object[][] datos = new Object[5][4];
        private DefaultTableModel modeloTabla = new DefaultTableModel();
        private GridBagConstraints gbcSuperior = new GridBagConstraints();
        // panel inferior de botones
        private JPanel panelBtns = new JPanel();
        private JButton btnGuardar = new JButton("Guardar Cambios");
        private JButton btnDescartar = new JButton("Descatar Cambios");
        private JButton btnRegCli = new JButton("Registrar Cliente");
        private GridBagConstraints gbcInferior = new GridBagConstraints();

        public LienzoCentral() {
            super();
            this.setLayout(new BorderLayout());
            //panel tabla
            this.panelTabla.setLayout(new GridBagLayout());
            this.gbcSuperior.insets = new Insets(10, 10, 10, 10);
            this.gbcSuperior.weightx = 1; this.gbcSuperior.weighty = 1;
            this.gbcSuperior.fill = GridBagConstraints.BOTH;
            this.modeloTabla.setDataVector(datos, columnas);
            this.tablaDatos.setModel(modeloTabla);
            this.scpTabla.setViewportView(tablaDatos);
            this.panelTabla.add(scpTabla, gbcSuperior);
            this.add(panelTabla, BorderLayout.CENTER);
            //panel btns
            this.panelBtns.setLayout(new GridBagLayout());
            this.gbcInferior.insets = new Insets(10, 10, 10, 10);
            this.btnGuardar.setBackground(new Color(0x333533));
            this.btnGuardar.setForeground(new Color(0xFFFEFE));
            this.btnDescartar.setBackground(new Color(0x333533));
            this.btnDescartar.setForeground(new Color(0xFFFEFE));
            this.btnRegCli.setBackground(new Color(0x333533));
            this.btnRegCli.setForeground(new Color(0xFFFEFE));
            this.panelBtns.add(btnGuardar, gbcInferior);
            this.panelBtns.add(btnDescartar, gbcInferior);
            this.panelBtns.add(btnRegCli, gbcInferior);
            this.add(panelBtns, BorderLayout.SOUTH);
        }
    }

    class LienzoFooter extends JPanel{
        private JButton btnSalir = new JButton("Salir");
        private JButton btnAyuda = new JButton("Ayuda");
        public LienzoFooter (){
            super();
            this.setLayout(new FlowLayout(FlowLayout.RIGHT));
            this.setBackground(new Color(0, 137, 1));
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