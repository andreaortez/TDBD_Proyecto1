/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tdbd2_proyecto1;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrea
 */
public class Main extends javax.swing.JFrame {

    Usuario reclutante = new Usuario("rec1", "1234", "Reclutador");
    Usuario postulante = new Usuario("post1", "1234", "Postulante");
    ArrayList<Usuario> usuarios = new ArrayList();
    int tipo = 0;
    Usuario actual = new Usuario();

    public Main() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setResizable(true);

        usuarios.add(reclutante);
        usuarios.add(postulante);

        pn_DatosDelPostulante.setVisible(false);
        pn_EmpleosDisponibles.setVisible(false);
        pn_EmpleosPostulados.setVisible(false);
        Reclutador.setVisible(false);
        Postulante.setVisible(false);
        pn_DatosDelReclutador.setVisible(false);
        pn_PuestosDisponibles.setVisible(false);
        pn_vistapostulante.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Login = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tf_usuario = new javax.swing.JTextField();
        pf_contra = new javax.swing.JPasswordField();
        bt_iniciarSesión = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        Postulante = new javax.swing.JPanel();
        MenuBar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tf_buscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        bt_miPerfil = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        bt_EDisponibles = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        bt_EPostulados = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        bt_cerrarSesiónP = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        pn_DatosDelPostulante = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        pn_EmpleosDisponibles = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_EmpleosDisponibles = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        pn_EmpleosPostulados = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jt_EmpleosPostulados = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jl_name = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Reclutador = new javax.swing.JPanel();
        MenuBar1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        bt_MiPerfilE = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        bt_PDisponibles = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        bt_cerrarSesiónR = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel9 = new javax.swing.JPanel();
        jl_name1 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        pn_PuestosDisponibles = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jl_EDisponibles = new javax.swing.JList<>();
        jLabel14 = new javax.swing.JLabel();
        pn_postulantes = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jl_postulantes = new javax.swing.JList<>();
        pn_DatosDelReclutador = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        pn_vistapostulante = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jl_name2 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        bt_entrar = new javax.swing.JPanel();
        jLabel144 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 800));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Login.setBackground(new java.awt.Color(255, 255, 255));
        Login.setMinimumSize(new java.awt.Dimension(1280, 800));
        Login.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/texto login.png"))); // NOI18N
        Login.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagen login 2.png"))); // NOI18N
        Login.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 210, -1, -1));

        tf_usuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tf_usuario.setForeground(new java.awt.Color(51, 51, 51));
        Login.add(tf_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 350, 50));

        pf_contra.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pf_contra.setForeground(new java.awt.Color(51, 51, 51));
        Login.add(pf_contra, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, 350, 50));

        bt_iniciarSesión.setBackground(new java.awt.Color(11, 103, 194));
        bt_iniciarSesión.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_iniciarSesiónMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_iniciarSesiónMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_iniciarSesiónMouseExited(evt);
            }
        });

        jLabel23.setBackground(new java.awt.Color(37, 71, 106));
        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Inicia Sesión");

        javax.swing.GroupLayout bt_iniciarSesiónLayout = new javax.swing.GroupLayout(bt_iniciarSesión);
        bt_iniciarSesión.setLayout(bt_iniciarSesiónLayout);
        bt_iniciarSesiónLayout.setHorizontalGroup(
            bt_iniciarSesiónLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );
        bt_iniciarSesiónLayout.setVerticalGroup(
            bt_iniciarSesiónLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        Login.add(bt_iniciarSesión, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, 350, 40));

        jLabel24.setBackground(new java.awt.Color(55, 55, 55));
        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(55, 55, 55));
        jLabel24.setText("Contraseña");
        Login.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, -1, -1));

        jLabel25.setBackground(new java.awt.Color(55, 55, 55));
        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(55, 55, 55));
        jLabel25.setText("Usuario");
        Login.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, -1, -1));

        getContentPane().add(Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 800));

        Postulante.setBackground(new java.awt.Color(239, 238, 236));
        Postulante.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MenuBar.setBackground(new java.awt.Color(254, 254, 254));
        MenuBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo 2.png"))); // NOI18N
        MenuBar.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 240, -1));

        tf_buscar.setBackground(new java.awt.Color(233, 240, 246));
        tf_buscar.setText("Buscar");
        tf_buscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tf_buscarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tf_buscarMouseExited(evt);
            }
        });
        MenuBar.add(tf_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 250, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lupa.png"))); // NOI18N
        MenuBar.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, -1));

        bt_miPerfil.setBackground(new java.awt.Color(255, 255, 255));
        bt_miPerfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_miPerfilMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_miPerfilMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_miPerfilMouseExited(evt);
            }
        });
        bt_miPerfil.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setBackground(new java.awt.Color(55, 55, 55));
        jLabel7.setForeground(new java.awt.Color(55, 55, 55));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Mi Perfil");
        bt_miPerfil.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 80, -1));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/usuario.png"))); // NOI18N
        bt_miPerfil.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, -1));

        MenuBar.add(bt_miPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 0, 90, 70));

        bt_EDisponibles.setBackground(new java.awt.Color(255, 255, 255));
        bt_EDisponibles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_EDisponiblesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_EDisponiblesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_EDisponiblesMouseExited(evt);
            }
        });
        bt_EDisponibles.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(55, 55, 55));
        jLabel8.setForeground(new java.awt.Color(55, 55, 55));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Empleos Disponibles");
        bt_EDisponibles.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 130, -1));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edificio.png"))); // NOI18N
        bt_EDisponibles.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 130, -1));

        MenuBar.add(bt_EDisponibles, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 0, 130, 70));

        bt_EPostulados.setBackground(new java.awt.Color(255, 255, 255));
        bt_EPostulados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_EPostuladosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_EPostuladosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_EPostuladosMouseExited(evt);
            }
        });
        bt_EPostulados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setBackground(new java.awt.Color(55, 55, 55));
        jLabel10.setForeground(new java.awt.Color(55, 55, 55));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Empleos Postulados");
        bt_EPostulados.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 50, 120, -1));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/maletin.png"))); // NOI18N
        bt_EPostulados.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 10, 130, -1));

        MenuBar.add(bt_EPostulados, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 0, 130, 70));

        jSeparator1.setBackground(new java.awt.Color(55, 55, 55));
        jSeparator1.setForeground(new java.awt.Color(55, 55, 55));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        MenuBar.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, 10, 53));

        jSeparator2.setBackground(new java.awt.Color(55, 55, 55));
        jSeparator2.setForeground(new java.awt.Color(55, 55, 55));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        MenuBar.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 10, 10, 53));

        bt_cerrarSesiónP.setBackground(new java.awt.Color(255, 255, 255));
        bt_cerrarSesiónP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_cerrarSesiónPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_cerrarSesiónPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_cerrarSesiónPMouseExited(evt);
            }
        });
        bt_cerrarSesiónP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setBackground(new java.awt.Color(55, 55, 55));
        jLabel26.setForeground(new java.awt.Color(55, 55, 55));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Cerrar Sesión");
        bt_cerrarSesiónP.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 90, -1));

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cerrar-sesion.png"))); // NOI18N
        bt_cerrarSesiónP.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, -1));

        MenuBar.add(bt_cerrarSesiónP, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 0, -1, 70));

        jSeparator3.setBackground(new java.awt.Color(55, 55, 55));
        jSeparator3.setForeground(new java.awt.Color(55, 55, 55));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        MenuBar.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 10, 10, 53));

        Postulante.add(MenuBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 70));

        pn_DatosDelPostulante.setBackground(new java.awt.Color(239, 238, 236));
        pn_DatosDelPostulante.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setForeground(new java.awt.Color(55, 55, 55));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 589, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Datos Personales", jPanel4);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 589, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Datos Familiares", jPanel3);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setFocusable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 589, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Datos Sanitarios", jPanel2);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 589, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Datos Legales", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 589, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Datos Académicos", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 549, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Datos Profesionales", jPanel7);

        pn_DatosDelPostulante.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 930, 620));

        Postulante.add(pn_DatosDelPostulante, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 980, 640));

        pn_EmpleosDisponibles.setBackground(new java.awt.Color(239, 238, 236));
        pn_EmpleosDisponibles.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jt_EmpleosDisponibles.setBackground(new java.awt.Color(255, 255, 255));
        jt_EmpleosDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Puesto", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jt_EmpleosDisponibles);

        pn_EmpleosDisponibles.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 940, 570));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(55, 55, 55));
        jLabel3.setText("EMPLEOS DISPONIBLES");
        pn_EmpleosDisponibles.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        Postulante.add(pn_EmpleosDisponibles, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 980, 640));

        pn_EmpleosPostulados.setBackground(new java.awt.Color(239, 238, 236));
        pn_EmpleosPostulados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jt_EmpleosPostulados.setBackground(new java.awt.Color(255, 255, 255));
        jt_EmpleosPostulados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Puesto", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jt_EmpleosPostulados);

        pn_EmpleosPostulados.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 940, 570));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(55, 55, 55));
        jLabel12.setText("EMPLEOS POSTULADOS");
        pn_EmpleosPostulados.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        Postulante.add(pn_EmpleosPostulados, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 980, 640));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jl_name.setBackground(new java.awt.Color(55, 55, 55));
        jl_name.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jl_name.setForeground(new java.awt.Color(55, 55, 55));
        jl_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_name.setText("NOMBRE");
        jl_name.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jl_name.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jl_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 182, 280, -1));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/perfil.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 40, 280, -1));

        Postulante.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 280, 330));

        getContentPane().add(Postulante, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 800));

        Reclutador.setBackground(new java.awt.Color(239, 238, 236));
        Reclutador.setMinimumSize(new java.awt.Dimension(1280, 800));
        Reclutador.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MenuBar1.setBackground(new java.awt.Color(254, 254, 254));
        MenuBar1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo 2.png"))); // NOI18N
        MenuBar1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 240, -1));

        bt_MiPerfilE.setBackground(new java.awt.Color(255, 255, 255));
        bt_MiPerfilE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_MiPerfilEMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_MiPerfilEMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_MiPerfilEMouseExited(evt);
            }
        });
        bt_MiPerfilE.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setBackground(new java.awt.Color(55, 55, 55));
        jLabel15.setForeground(new java.awt.Color(55, 55, 55));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Mi Perfil");
        bt_MiPerfilE.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 90, -1));

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edificio.png"))); // NOI18N
        bt_MiPerfilE.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, -1));

        MenuBar1.add(bt_MiPerfilE, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 0, 90, 70));

        bt_PDisponibles.setBackground(new java.awt.Color(255, 255, 255));
        bt_PDisponibles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_PDisponiblesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_PDisponiblesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_PDisponiblesMouseExited(evt);
            }
        });
        bt_PDisponibles.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setBackground(new java.awt.Color(55, 55, 55));
        jLabel19.setForeground(new java.awt.Color(55, 55, 55));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Puestos Disponibles");
        bt_PDisponibles.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 50, 120, -1));

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/maletin.png"))); // NOI18N
        bt_PDisponibles.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 10, 130, -1));

        MenuBar1.add(bt_PDisponibles, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 0, 130, 70));

        jSeparator4.setBackground(new java.awt.Color(55, 55, 55));
        jSeparator4.setForeground(new java.awt.Color(55, 55, 55));
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        MenuBar1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 10, 10, 53));

        bt_cerrarSesiónR.setBackground(new java.awt.Color(255, 255, 255));
        bt_cerrarSesiónR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_cerrarSesiónRMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_cerrarSesiónRMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_cerrarSesiónRMouseExited(evt);
            }
        });
        bt_cerrarSesiónR.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setBackground(new java.awt.Color(55, 55, 55));
        jLabel28.setForeground(new java.awt.Color(55, 55, 55));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Cerrar Sesión");
        bt_cerrarSesiónR.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 90, -1));

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cerrar-sesion.png"))); // NOI18N
        bt_cerrarSesiónR.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, -1));

        MenuBar1.add(bt_cerrarSesiónR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 0, -1, 70));

        jSeparator5.setBackground(new java.awt.Color(55, 55, 55));
        jSeparator5.setForeground(new java.awt.Color(55, 55, 55));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        MenuBar1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 10, 10, 53));

        Reclutador.add(MenuBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 70));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jl_name1.setBackground(new java.awt.Color(55, 55, 55));
        jl_name1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jl_name1.setForeground(new java.awt.Color(55, 55, 55));
        jl_name1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_name1.setText("EMPRESA");
        jl_name1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jl_name1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel9.add(jl_name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 182, 280, -1));

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/perfil de empresa.png"))); // NOI18N
        jPanel9.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 40, 280, -1));

        Reclutador.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 280, 330));

        pn_PuestosDisponibles.setBackground(new java.awt.Color(239, 238, 236));
        pn_PuestosDisponibles.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setForeground(new java.awt.Color(1, 103, 153));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jl_EDisponibles.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jl_EDisponibles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl_EDisponiblesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jl_EDisponibles);

        jPanel18.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 75, 390, 510));

        jLabel14.setBackground(new java.awt.Color(55, 55, 55));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(55, 55, 55));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("EMPLEOS DISPONIBLES");
        jPanel18.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 390, -1));

        pn_postulantes.setBackground(new java.awt.Color(255, 255, 255));
        pn_postulantes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setBackground(new java.awt.Color(55, 55, 55));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(55, 55, 55));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("POSTULANTES");
        pn_postulantes.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 380, -1));

        jl_postulantes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jl_postulantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl_postulantesMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jl_postulantes);

        pn_postulantes.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 380, 520));

        jPanel18.add(pn_postulantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 460, 620));

        pn_PuestosDisponibles.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 940, 620));

        Reclutador.add(pn_PuestosDisponibles, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 980, 640));

        pn_DatosDelReclutador.setBackground(new java.awt.Color(239, 238, 236));
        pn_DatosDelReclutador.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 940, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        pn_DatosDelReclutador.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 940, 620));

        Reclutador.add(pn_DatosDelReclutador, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 980, 640));

        pn_vistapostulante.setBackground(new java.awt.Color(239, 238, 236));
        pn_vistapostulante.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jl_name2.setBackground(new java.awt.Color(55, 55, 55));
        jl_name2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jl_name2.setForeground(new java.awt.Color(55, 55, 55));
        jl_name2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_name2.setText("NOMBRE");
        jl_name2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jl_name2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel10.add(jl_name2, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 182, 280, -1));

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/perfil.png"))); // NOI18N
        jPanel10.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 40, 280, -1));

        pn_vistapostulante.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 280, 330));

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setForeground(new java.awt.Color(55, 55, 55));
        jTabbedPane2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 589, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Datos Personales", jPanel11);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 589, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Datos Familiares", jPanel12);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setFocusable(false);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 589, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Datos Sanitarios", jPanel13);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 589, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Datos Legales", jPanel14);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 589, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Datos Académicos", jPanel15);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 549, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Datos Profesionales", jPanel16);

        pn_vistapostulante.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 930, 620));

        bt_entrar.setBackground(new java.awt.Color(1, 103, 153));
        bt_entrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_entrarMouseClicked(evt);
            }
        });

        jLabel144.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel144.setForeground(new java.awt.Color(255, 255, 255));
        jLabel144.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel144.setText("REGRESAR");

        javax.swing.GroupLayout bt_entrarLayout = new javax.swing.GroupLayout(bt_entrar);
        bt_entrar.setLayout(bt_entrarLayout);
        bt_entrarLayout.setHorizontalGroup(
            bt_entrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel144, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );
        bt_entrarLayout.setVerticalGroup(
            bt_entrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bt_entrarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel144)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pn_vistapostulante.add(bt_entrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 370, 90, -1));

        Reclutador.add(pn_vistapostulante, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1280, 640));

        getContentPane().add(Reclutador, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_buscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_buscarMouseEntered
        if ("Buscar".equals(tf_buscar.getText())) {
            tf_buscar.setText("");
        }
    }//GEN-LAST:event_tf_buscarMouseEntered

    private void tf_buscarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_buscarMouseExited
        if (tf_buscar.getText().isEmpty()) {
            tf_buscar.setText("Buscar");
        }
    }//GEN-LAST:event_tf_buscarMouseExited

    private void bt_EDisponiblesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_EDisponiblesMouseEntered
        bt_EDisponibles.setBackground(new Color(54, 156, 225));
    }//GEN-LAST:event_bt_EDisponiblesMouseEntered

    private void bt_EDisponiblesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_EDisponiblesMouseExited
        bt_EDisponibles.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_bt_EDisponiblesMouseExited

    private void bt_EPostuladosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_EPostuladosMouseEntered
        bt_EPostulados.setBackground(new Color(54, 156, 225));
    }//GEN-LAST:event_bt_EPostuladosMouseEntered

    private void bt_EPostuladosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_EPostuladosMouseExited
        bt_EPostulados.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_bt_EPostuladosMouseExited

    private void bt_miPerfilMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_miPerfilMouseEntered
        bt_miPerfil.setBackground(new Color(54, 156, 225));
    }//GEN-LAST:event_bt_miPerfilMouseEntered

    private void bt_miPerfilMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_miPerfilMouseExited
        bt_miPerfil.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_bt_miPerfilMouseExited

    private void bt_miPerfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_miPerfilMouseClicked
        pn_EmpleosPostulados.setVisible(false);
        pn_EmpleosDisponibles.setVisible(false);

        if (pn_DatosDelPostulante.isVisible()) {
            pn_DatosDelPostulante.setVisible(false);
        } else {
            pn_DatosDelPostulante.setVisible(true);
        }
    }//GEN-LAST:event_bt_miPerfilMouseClicked

    private void bt_EDisponiblesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_EDisponiblesMouseClicked
        pn_EmpleosPostulados.setVisible(false);
        pn_DatosDelPostulante.setVisible(false);

        if (pn_EmpleosDisponibles.isVisible()) {
            pn_EmpleosDisponibles.setVisible(false);
        } else {
            pn_EmpleosDisponibles.setVisible(true);
        }
    }//GEN-LAST:event_bt_EDisponiblesMouseClicked

    private void bt_EPostuladosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_EPostuladosMouseClicked
        pn_EmpleosDisponibles.setVisible(false);
        pn_DatosDelPostulante.setVisible(false);

        if (pn_EmpleosPostulados.isVisible()) {
            pn_EmpleosPostulados.setVisible(false);
        } else {
            pn_EmpleosPostulados.setVisible(true);
        }
    }//GEN-LAST:event_bt_EPostuladosMouseClicked

    private void bt_MiPerfilEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_MiPerfilEMouseClicked
        pn_PuestosDisponibles.setVisible(false);

        if (pn_DatosDelReclutador.isVisible()) {
            pn_DatosDelReclutador.setVisible(false);
        } else {
            pn_DatosDelReclutador.setVisible(true);
        }
    }//GEN-LAST:event_bt_MiPerfilEMouseClicked

    private void bt_MiPerfilEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_MiPerfilEMouseEntered
        bt_MiPerfilE.setBackground(new Color(54, 156, 225));
    }//GEN-LAST:event_bt_MiPerfilEMouseEntered

    private void bt_MiPerfilEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_MiPerfilEMouseExited
        bt_MiPerfilE.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_bt_MiPerfilEMouseExited

    private void bt_PDisponiblesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_PDisponiblesMouseClicked
        pn_DatosDelReclutador.setVisible(false);
        pn_postulantes.setVisible(false);

        if (pn_PuestosDisponibles.isVisible()) {
            pn_PuestosDisponibles.setVisible(false);
        } else {
            pn_PuestosDisponibles.setVisible(true);
        }
    }//GEN-LAST:event_bt_PDisponiblesMouseClicked

    private void bt_PDisponiblesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_PDisponiblesMouseEntered
        bt_PDisponibles.setBackground(new Color(54, 156, 225));
    }//GEN-LAST:event_bt_PDisponiblesMouseEntered

    private void bt_PDisponiblesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_PDisponiblesMouseExited
        bt_PDisponibles.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_bt_PDisponiblesMouseExited

    private void jl_EDisponiblesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl_EDisponiblesMouseClicked
        if (evt.getClickCount() == 2) {
            pn_postulantes.setVisible(true);
        }
    }//GEN-LAST:event_jl_EDisponiblesMouseClicked

    private void jl_postulantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl_postulantesMouseClicked
        if (evt.getClickCount() == 2) {
            if (!pn_postulantes.isVisible()) {
                pn_postulantes.setVisible(true);
            }
            pn_vistapostulante.setVisible(true);

        }
    }//GEN-LAST:event_jl_postulantesMouseClicked

    private void bt_entrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_entrarMouseClicked
        pn_vistapostulante.setVisible(false);
    }//GEN-LAST:event_bt_entrarMouseClicked

    private void bt_iniciarSesiónMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_iniciarSesiónMouseClicked
        if (ValidarUsuario(tf_usuario.getText(), pf_contra.getText())) {
            switch (tipo) {
                case 1:
                    Login.setVisible(false);
                    Reclutador.setVisible(true);
                    Postulante.setVisible(false);
//                Canvas.setVisible(false);
                    break;
                case 2:
                    Login.setVisible(false);
                    Reclutador.setVisible(false);
                    Postulante.setVisible(true);
//                Canvas.setVisible(false);
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Usuario y/o contraseña incorrectas");
        }
    }//GEN-LAST:event_bt_iniciarSesiónMouseClicked

    private void bt_iniciarSesiónMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_iniciarSesiónMouseEntered
        bt_iniciarSesión.setBackground(new Color(1, 66, 130));
    }//GEN-LAST:event_bt_iniciarSesiónMouseEntered

    private void bt_iniciarSesiónMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_iniciarSesiónMouseExited
        bt_iniciarSesión.setBackground(new Color(11, 103, 194));
    }//GEN-LAST:event_bt_iniciarSesiónMouseExited

    private void bt_cerrarSesiónPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_cerrarSesiónPMouseClicked
        LimpiarSesion();
        Postulante.setVisible(false);
    }//GEN-LAST:event_bt_cerrarSesiónPMouseClicked

    private void bt_cerrarSesiónPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_cerrarSesiónPMouseEntered
        bt_cerrarSesiónP.setBackground(new Color(217, 75, 89));
    }//GEN-LAST:event_bt_cerrarSesiónPMouseEntered

    private void bt_cerrarSesiónPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_cerrarSesiónPMouseExited
        bt_cerrarSesiónP.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_bt_cerrarSesiónPMouseExited

    private void bt_cerrarSesiónRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_cerrarSesiónRMouseClicked
        LimpiarSesion();
        Reclutador.setVisible(false);
    }//GEN-LAST:event_bt_cerrarSesiónRMouseClicked

    private void bt_cerrarSesiónRMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_cerrarSesiónRMouseEntered
        bt_cerrarSesiónR.setBackground(new Color(217, 75, 89));
    }//GEN-LAST:event_bt_cerrarSesiónRMouseEntered

    private void bt_cerrarSesiónRMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_cerrarSesiónRMouseExited
        bt_cerrarSesiónR.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_bt_cerrarSesiónRMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    private boolean ValidarUsuario(String user, String contra) {
        for (Usuario u : usuarios) {
//            if (u.getUsuario().equals(admin) && u.getPassword().equals(contra)) {
//                if (u instanceof Registro) {
//                    tipo = 1;
//                } else if (u instanceof Maestro) {
//                    tipo = 2;
//                } else if (u instanceof Alumno) {
//                    tipo = 3;
//                }
//                actual = u;
//                return true;
//            }

            if (u.getUsuario().equals(user) && u.getPassword().equals(contra)) {
                if (u.getRol().equals("Reclutador")) {
                    tipo = 1;
                } else {
                    tipo = 2;
                }
                actual = u;
                return true;
            }
        }
        return false;

    }
    
    private void LimpiarSesion() {
        tf_usuario.setText("");
        pf_contra.setText("");
        actual = null;
        tipo = 0;
        Login.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Login;
    private javax.swing.JPanel MenuBar;
    private javax.swing.JPanel MenuBar1;
    private javax.swing.JPanel Postulante;
    private javax.swing.JPanel Reclutador;
    private javax.swing.JPanel bt_EDisponibles;
    private javax.swing.JPanel bt_EPostulados;
    private javax.swing.JPanel bt_MiPerfilE;
    private javax.swing.JPanel bt_PDisponibles;
    private javax.swing.JPanel bt_cerrarSesiónP;
    private javax.swing.JPanel bt_cerrarSesiónR;
    private javax.swing.JPanel bt_entrar;
    private javax.swing.JPanel bt_iniciarSesión;
    private javax.swing.JPanel bt_miPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JList<String> jl_EDisponibles;
    private javax.swing.JLabel jl_name;
    private javax.swing.JLabel jl_name1;
    private javax.swing.JLabel jl_name2;
    private javax.swing.JList<String> jl_postulantes;
    private javax.swing.JTable jt_EmpleosDisponibles;
    private javax.swing.JTable jt_EmpleosPostulados;
    private javax.swing.JPasswordField pf_contra;
    private javax.swing.JPanel pn_DatosDelPostulante;
    private javax.swing.JPanel pn_DatosDelReclutador;
    private javax.swing.JPanel pn_EmpleosDisponibles;
    private javax.swing.JPanel pn_EmpleosPostulados;
    private javax.swing.JPanel pn_PuestosDisponibles;
    private javax.swing.JPanel pn_postulantes;
    private javax.swing.JPanel pn_vistapostulante;
    private javax.swing.JTextField tf_buscar;
    private javax.swing.JTextField tf_usuario;
    // End of variables declaration//GEN-END:variables
}
