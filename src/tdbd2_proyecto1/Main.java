/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tdbd2_proyecto1;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import java.awt.Dimension;
import java.util.Arrays;
import java.awt.event.MouseEvent;
import java.time.Year;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

/**
 *
 * @author Andrea
 */
public class Main extends javax.swing.JFrame {

    Admin admin = new Admin();
    String userid = "", pass = "", user = "";
    ArrayList<String> atributosPersona = new ArrayList();
    ArrayList<String> datosPersona = new ArrayList();
    int pJobNumber = 0;

    //otros atributos necesarios
    String empleoid = "";

    public Main() {
        initComponents();

        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setResizable(true);

        pn_DatosDelPostulante.setVisible(false);
        pn_EmpleosDisponibles.setVisible(false);
        pn_EmpleosPostulados.setVisible(false);
        Reclutador.setVisible(false);
        Postulante.setVisible(false);
        pn_PuestosDisponibles.setVisible(false);
        pn_DatosDelPostulante.setVisible(false);
        pn_perfilPersona.setVisible(false);

        jd_ModificarPersona.setTitle("Modificar Persona");
        jd_crearEmpleo.setTitle("Añadir Puesto");
        jd_crearUsuario.setTitle("Crear Usuario");
        jd_Empresa.setTitle("Crear Nueva Empresa");
        jd_Persona.setTitle("Crear Nueva Persona");

        LlenarAtributos();
    }

   
    
    public void llenarDatosPostulante() {
        System.out.println("UserID: " + userid);

        //LLenado de Tablas 
        String s = admin.getEmpleos().toString();
        LlenarTabla(s, jt_EmpleosDisponibles, 0);
        LlenarTabla(s, jt_EmpleosPostulados, 1);

        System.out.println("-----------");
        System.out.println("&&: " + admin.getPuestos());
        //System.out.println("%%: "+Arrays.toString(admin.getPuesto(userid)));
        System.out.println("--------------");

        //Datos Personales (dp)
        String[] dp = admin.getPersonal_pf(userid);
        System.out.println("!:"+Arrays.toString(admin.getPersonal_pf(userid)));
        jl_name.setText(dp[0] + " " + dp[6]);
        jl_email.setText(dp[1]);
        jl_age.setText(dp[2]);
        jl_phone.setText(dp[3]);
        jl_nation.setText(dp[5]);
        jl_gender.setText(dp[8]);

        //Datos Familiares (df)
        if (admin.getFamiliar_pf(userid) != null) {
            String[] df = admin.getFamiliar_pf(userid);
            System.out.println("!:"+Arrays.toString(admin.getFamiliar_pf(userid)));

            if (df.length > 0) {
                lb_EcivilP.setText(df[0]);
                llenarJList(jl_correoDP, df[2]);
                lb_hijosP.setText(df[3]);
                lb_dirrecionP.setText(df[4]);
            }
        }

        //Datos Sanitarios (ds)
        if (admin.getSanitary_pf(userid) != null) {
            String[] ds = admin.getSanitary_pf(userid);
            System.out.println("!:"+Arrays.toString(admin.getSanitary_pf(userid)));
            if (ds.length>0) {
                lb_infoMed.setText(ds[0]);
                llenarJList(jl_alergias, ds[1]);
                llenarJList(jl_HM, ds[3]);
                lb_resultadoP.setText(ds[5]);
            }
        }

        //Datos Legales (dl)
        if (admin.getLegal_pf(userid) != null) {
            String[] dl = admin.getLegal_pf(userid);
            System.out.println("!:"+Arrays.toString(admin.getLegal_pf(userid)));
            if (dl.length>0) {
                llenarJList(jl_aPenales, dl[2]);
                lb_SM.setText(dl[3]);
                lb_SSN.setText(dl[4]);
            }
        }

        //Datos Academicos (da)
        if (admin.getAcademic_pf(userid) != null) {
            String[] da = admin.getAcademic_pf(userid);
            System.out.println("!:"+Arrays.toString(admin.getAcademic_pf(userid)));
            if (da.length>0) {
                lb_institucion.setText(da[0]);
                lb_NU.setText(da[1]);
                lb_Estudios.setText(da[2]);
                lb_titulos.setText(da[4]);
            }
        }

        //Datos Laborales (dlp)
        if (admin.getProfesional_pf(userid) != null) {
            String[] dlp = admin.getProfesional_pf(userid);
            System.out.println("!:"+Arrays.toString(admin.getProfesional_pf(userid)));
            if (dlp.length>0) {
                lb_AñosE.setText(dlp[0]);
                lb_LogrosP.setText(dlp[1]);
                lb_Idiomas.setText(dlp[3]);
                lb_Certificaciones.setText(dlp[4]);
                lb_ConEsp.setText(dlp[6]);
            }
        }

        //Historial Trabajo
        if (admin.getCurrentJob(userid)!=null) {
            String[]ht = admin.getCurrentJob(userid);
            System.out.println("->"+admin.getCurrentJob(userid));
            if (ht.length>0) {
                
                String job = admin.getEmpleo(ht[0]).toString();
                JSONArray ar = new JSONArray(job);
                JSONObject o = ar.getJSONObject(0);
                String s_job = "Nombre: "+o.getString("Nombre")+"   Tipo: "+o.getString("Tipo")+"   ID: "+ht[0];
                lb_TrabActual.setText(s_job);
            }
        }

        //Cuentas Familiares en la App
        System.out.println("++++++++++++++++++");
        //System.out.println("------------\n#: "+admin.getFalimiares(userid));
        if (admin.getFalimiares(userid) != null) {
            lb_EcivilP1.setText(CuentasFamiliares(admin.getFalimiares(userid).toString()));
        }
        System.out.println("++++++++++++++++++");

        //Solicitud Trabajo
        //System.out.println("*:"+Arrays.toString(admin.getSolicitud(userid)));
        if (admin.getSolicitud(userid) != null) {
            String st = (Arrays.toString(admin.getSolicitud(userid))).substring(1, Arrays.toString(admin.getSolicitud(userid)).length() - 1);
            System.out.println("!:" + st);
            if (st.length() > 0) {
                String[] st_data = st.split(", ");
                llenarJList(jl_alergias1, st_data[3]);
                llenarJList(jl_alergias2, st_data[4]);
                lb_SalarioExpectante.setText(st_data[6]);
                lb_TT.setText(st_data[2]);
                lb_TC.setText(st_data[0]);
            }
        }
    }

    public void fillFilter() {
        tf_buscar.getText();
        String[] split = tf_buscar.getText().split(",");
        String filter = "#Obj = :idd";
        HashMap<String, String> names = new HashMap<>();
        ValueMap valores = new ValueMap();
        names.put("#Obj", "Obj");
        valores = valores.withString(":idd", "empleo");
        for (int i = 0; i < split.length; i++) {
            String[] splitElements = split[i].split("=");
            if (splitElements.length == 2) {
                if (filter != "") {
                    filter += " AND ";
                }
                splitElements[0] = splitElements[0].trim();
                splitElements[1] = splitElements[1].trim();
                if (!splitElements[0].replace(" ", "").equals(splitElements[0])) {
                    String old = splitElements[0];
                    String neww = splitElements[0] = "#" + splitElements[0].replace(" ", "_");
                    names.put(neww, old);
                }
                
                if (splitElements[0].toLowerCase().equals("empresa")) {
                    filter += "PK = :id" + i;
                    valores = valores.withString(":id" + i, splitElements[1]);
                } else if (splitElements[0].toLowerCase().equals("empleo")) {

                    filter += "SK = :id" + i;
                    valores = valores.withString(":id" + i, splitElements[1]);
                } else if (splitElements[0].toLowerCase().equals("experiencia")) {

                    names.put("#years", "AñosExperiencia");
                    filter += "#years = :id" + i;
                    valores = valores.withNumber(":id" + i, Integer.parseInt(splitElements[1]));

                } else {

                    filter += splitElements[0] + " = :id" + i;
                    valores = valores.withString(":id" + i, splitElements[1]);
                    
                }
              
            }
            System.out.println(filter);
        }

        ArrayList<String> datos = admin.filtroEmpleados(filter, valores, names);
        if (datos != null) {
            System.out.println(datos.toString());
            LlenarTabla(datos.toString(), jt_EmpleosDisponibles, 0);
        } else {
            LlenarTabla(admin.getEmpleos().toString(), jt_EmpleosDisponibles, 0);
        }

    }

    public void loadPreviousJobs() {
        int i = 1;
        String trabajos = "";
        /*
        orden: Empleador, Rango (ignorar), (ignorar), Titulo
         */
        while (admin.getPreviousJob(userid, "pJob_" + Integer.toString(i)) != null) {
            if (i == 1) {
                lb_EcivilP2.setText("");
            }
            pJobNumber++;

            String[] datos = admin.getPreviousJob(userid, "pJob_" + Integer.toString(i));
            trabajos += "Titulo: " + datos[3] + "   Empleador: " + datos[0] + "\n";
            i++;
        }
        lb_EcivilP2.setText(trabajos);
    }

    public void reloadPreviousJobs() {

        String trabajos = "";
        /*
        orden: Empleador, Rango (ignorar), (ignorar), Titulo
         */
        for (int i = 0; i < pJobNumber; i++) {
            if (i == 0) {
                lb_EcivilP2.setText("");
            }
            pJobNumber++;

            String[] datos = admin.getPreviousJob(userid, "pJob_" + Integer.toString(i));
            trabajos += "Titulo: " + datos[3] + "   Empleador: " + datos[0] + "\n";
        }
        lb_EcivilP2.setText(trabajos);
    }

    public boolean validarSolicitudRequerimientos() {
        DefaultTableModel modelo = (DefaultTableModel) jt_EmpleosDisponibles.getModel();
        int row = jt_EmpleosDisponibles.getSelectedRow();
        boolean isThere = false;
        //validar antecedentes
        String[] legal = admin.getLegal_pf(userid);

        if (legal == null) {
            return false;
        }
        String antecedentes = legal[2];

        if ((boolean) modelo.getValueAt(row, 4)) {

            if (antecedentes != null) {
                if (!antecedentes.equals("") && !antecedentes.equals("Ninguno,")) {
                    return false;
                }
            }
        }
        System.out.println("c1");
        //validar Nivel Educativo
        String[] educativo = admin.getAcademic_pf(userid);
        if (educativo == null) {
            return false;
        }
        if (educationlvl(educativo[1]) < educationlvl((String) modelo.getValueAt(row, 5))) {
            return false;
        }
        System.out.println("c2");
        //validar Experiencia
        String[] profesionales = admin.getProfesional_pf(userid);
        if (profesionales == null) {
            return false;
        }
        if (Integer.parseInt(profesionales[0]) < ((int) modelo.getValueAt(row, 7))) {
            return false;
        }
        System.out.println("c3");
        //validar idiomas
        System.out.println(profesionales[3]);
        if (profesionales[3] == null) {
            return false;
        }
        String[] idiomas = profesionales[3].split(",");
        String[] tablaIdiomas = ((String) modelo.getValueAt(row, 8)).split(",");
        isThere = false;
        for (String string : tablaIdiomas) {
            for (String id : idiomas) {
                if (string.equals(id)) {
                    isThere = true;
                }
            }
        }
        if (!isThere) {
            return false;
        }
        System.out.println("c4");
        //validar certificaciones
        if (profesionales[4] == null) {
            return false;
        }
        String[] certificaciones = profesionales[4].split(",");
        String[] tablaCerti = ((String) modelo.getValueAt(row, 9)).split(",");
        isThere = false;
        for (String string : tablaCerti) {
            for (String id : certificaciones) {
                if (string.equals(id)) {
                    isThere = true;
                }
            }
        }
        if (!isThere) {
            return false;
        }
        System.out.println("c5");
        return true;
    }

    public boolean validarSolicitudSalario() {
        DefaultTableModel modelo = (DefaultTableModel) jt_EmpleosDisponibles.getModel();
        int row = jt_EmpleosDisponibles.getSelectedRow();
        String[] puestos = ((String) modelo.getValueAt(row, 1)).split(",");
        String[] solicitud = admin.getSolicitud(userid)[3].split(",");
        if (solicitud == null) {
            return false;
        }
        boolean isThere = false;
        for (String string : solicitud) {
            for (String puesto : puestos) {
                if (string.equals(puesto)) {
                    if (Double.parseDouble(admin.getPuesto(puesto)[2]) >= Double.parseDouble(solicitud[7])) {
                        isThere = true;
                    }
                }
            }
        }

        return isThere;

    }

    public int educationlvl(String edu) {
        if (edu.equals("Educacion Media")) {
            return 1;
        } else if (edu.equals("Grado")) {
            return 2;
        } else if (edu.equals("Universitario")) {
            return 3;
        } else if (edu.equals("Posgrado")) {
            return 4;
        } else if (edu.equals("Master")) {
            return 5;
        } else if (edu.equals("Doctorado")) {
            return 6;
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jd_crearEmpleo = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        tab1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jSeparator30 = new javax.swing.JSeparator();
        jLabel62 = new javax.swing.JLabel();
        jSeparator31 = new javax.swing.JSeparator();
        jLabel64 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jSeparator32 = new javax.swing.JSeparator();
        jLabel68 = new javax.swing.JLabel();
        tf_empleo = new javax.swing.JTextField();
        jSeparator33 = new javax.swing.JSeparator();
        jSeparator34 = new javax.swing.JSeparator();
        jLabel70 = new javax.swing.JLabel();
        jSeparator35 = new javax.swing.JSeparator();
        tf_tipoEmpleo = new javax.swing.JTextField();
        ff_añosE = new javax.swing.JFormattedTextField();
        jLabel80 = new javax.swing.JLabel();
        cb_modalidad = new javax.swing.JComboBox<>();
        cb_nivelEducativo = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        ta_RP = new javax.swing.JTextArea();
        rb_si = new javax.swing.JRadioButton();
        rb_no = new javax.swing.JRadioButton();
        bt_RP = new javax.swing.JCheckBox();
        jSeparator66 = new javax.swing.JSeparator();
        tf_idEmpleo = new javax.swing.JTextField();
        jLabel123 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        cb_idiomas = new javax.swing.JComboBox<>();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jSeparator37 = new javax.swing.JSeparator();
        tf_usuario_a1 = new javax.swing.JTextField();
        tf_contraseña1 = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        ff_clasesA1 = new javax.swing.JFormattedTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        jl_certificacionesAñadidas = new javax.swing.JList<>();
        bt_añadirIdioma = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jl_idiomasAñadidos = new javax.swing.JList<>();
        tf_certificaciones = new javax.swing.JTextField();
        bt_añadirCertificacion = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cb_puestos = new javax.swing.JComboBox<>();
        jLabel115 = new javax.swing.JLabel();
        bt_crearPuesto = new javax.swing.JPanel();
        jLabel116 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jl_puestosAñadidos = new javax.swing.JList<>();
        jLabel117 = new javax.swing.JLabel();
        jSeparator60 = new javax.swing.JSeparator();
        jSeparator61 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jSeparator62 = new javax.swing.JSeparator();
        jLabel118 = new javax.swing.JLabel();
        tf_puesto = new javax.swing.JTextField();
        jSeparator63 = new javax.swing.JSeparator();
        tf_sueldo = new javax.swing.JTextField();
        jLabel119 = new javax.swing.JLabel();
        tf_tipoPuesto = new javax.swing.JTextField();
        bt_añadirPuesto = new javax.swing.JPanel();
        jLabel120 = new javax.swing.JLabel();
        bt_añadirP = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        jd_crearUsuario = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        tf_Cusuario = new javax.swing.JTextField();
        jSeparator44 = new javax.swing.JSeparator();
        jLabel88 = new javax.swing.JLabel();
        tf_contraseña = new javax.swing.JTextField();
        jSeparator45 = new javax.swing.JSeparator();
        bt_empresa = new javax.swing.JRadioButton();
        bt_persona = new javax.swing.JRadioButton();
        jLabel89 = new javax.swing.JLabel();
        bt_crearU = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        ff_noCuenta = new javax.swing.JFormattedTextField();
        jLabel31 = new javax.swing.JLabel();
        bg_antecedentes = new javax.swing.ButtonGroup();
        bg_usuario = new javax.swing.ButtonGroup();
        jd_Persona = new javax.swing.JDialog();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel90 = new javax.swing.JLabel();
        tf_NombreP = new javax.swing.JTextField();
        jSeparator46 = new javax.swing.JSeparator();
        jLabel91 = new javax.swing.JLabel();
        tf_apellido = new javax.swing.JTextField();
        jSeparator47 = new javax.swing.JSeparator();
        bt_M = new javax.swing.JRadioButton();
        bt_F = new javax.swing.JRadioButton();
        jLabel92 = new javax.swing.JLabel();
        bt_guardarP = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        ff_Edad = new javax.swing.JFormattedTextField();
        jLabel93 = new javax.swing.JLabel();
        tf_correoP = new javax.swing.JTextField();
        jSeparator48 = new javax.swing.JSeparator();
        jLabel94 = new javax.swing.JLabel();
        tf_nacionalidad = new javax.swing.JTextField();
        jSeparator49 = new javax.swing.JSeparator();
        jLabel95 = new javax.swing.JLabel();
        ff_telefonoP = new javax.swing.JFormattedTextField();
        jSeparator50 = new javax.swing.JSeparator();
        jSeparator51 = new javax.swing.JSeparator();
        jLabel96 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jd_Empresa = new javax.swing.JDialog();
        pn_fondoModE = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel97 = new javax.swing.JLabel();
        tf_NombreE = new javax.swing.JTextField();
        jSeparator52 = new javax.swing.JSeparator();
        jLabel98 = new javax.swing.JLabel();
        tf_CIF = new javax.swing.JTextField();
        jSeparator53 = new javax.swing.JSeparator();
        bt_guardarE = new javax.swing.JPanel();
        lb_btModEmpresa = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        tf_director = new javax.swing.JTextField();
        jSeparator54 = new javax.swing.JSeparator();
        jLabel101 = new javax.swing.JLabel();
        tf_direccionD = new javax.swing.JTextField();
        ff_telefonoP1 = new javax.swing.JFormattedTextField();
        jSeparator56 = new javax.swing.JSeparator();
        jSeparator57 = new javax.swing.JSeparator();
        jLabel103 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        tf_correoE = new javax.swing.JTextField();
        lb_tituloJDEmpresa = new javax.swing.JLabel();
        jd_ModificarPersona = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tb_modificarP = new javax.swing.JTable();
        bt_modificarP = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        menuPostular = new javax.swing.JPopupMenu();
        Postular = new javax.swing.JMenuItem();
        bg_genero = new javax.swing.ButtonGroup();
        menuContratar = new javax.swing.JPopupMenu();
        Contratar = new javax.swing.JMenuItem();
        pn_perfilPersona = new javax.swing.JPanel();
        jl_name = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jl_email = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jl_age = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel35 = new javax.swing.JLabel();
        jl_phone = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel37 = new javax.swing.JLabel();
        jl_nation = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel39 = new javax.swing.JLabel();
        jl_gender = new javax.swing.JLabel();
        pn_DatosDelPostulante = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pn_dFamiliaresP = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        lb_EcivilP = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel43 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel45 = new javax.swing.JLabel();
        lb_hijosP = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel47 = new javax.swing.JLabel();
        lb_dirrecionP = new javax.swing.JLabel();
        jSP_correoDP = new javax.swing.JScrollPane();
        jl_correoDP = new javax.swing.JList<>();
        pn_dSanitariosP = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        lb_infoMed = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel44 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel46 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel48 = new javax.swing.JLabel();
        lb_resultadoP = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jl_HM = new javax.swing.JList<>();
        jScrollPane8 = new javax.swing.JScrollPane();
        jl_alergias = new javax.swing.JList<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
        jScrollPane6 = new javax.swing.JScrollPane();
        jl_aPenales = new javax.swing.JList<>();
        jLabel50 = new javax.swing.JLabel();
        lb_SM = new javax.swing.JLabel();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel51 = new javax.swing.JLabel();
        lb_SSN = new javax.swing.JLabel();
        pn_dAcademicosP = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        lb_institucion = new javax.swing.JLabel();
        jSeparator23 = new javax.swing.JSeparator();
        jLabel58 = new javax.swing.JLabel();
        lb_NU = new javax.swing.JLabel();
        jSeparator24 = new javax.swing.JSeparator();
        jLabel59 = new javax.swing.JLabel();
        lb_Estudios = new javax.swing.JLabel();
        jSeparator25 = new javax.swing.JSeparator();
        jLabel60 = new javax.swing.JLabel();
        lb_titulos = new javax.swing.JLabel();
        pn_dProfesionalesP = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        lb_AñosE = new javax.swing.JLabel();
        jSeparator19 = new javax.swing.JSeparator();
        jLabel53 = new javax.swing.JLabel();
        lb_LogrosP = new javax.swing.JLabel();
        jSeparator20 = new javax.swing.JSeparator();
        jLabel54 = new javax.swing.JLabel();
        lb_Idiomas = new javax.swing.JLabel();
        jSeparator21 = new javax.swing.JSeparator();
        jLabel55 = new javax.swing.JLabel();
        lb_Certificaciones = new javax.swing.JLabel();
        jSeparator22 = new javax.swing.JSeparator();
        jLabel56 = new javax.swing.JLabel();
        lb_ConEsp = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        lb_TrabActual = new javax.swing.JLabel();
        jSeparator55 = new javax.swing.JSeparator();
        jLabel110 = new javax.swing.JLabel();
        lb_EcivilP2 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel111 = new javax.swing.JLabel();
        lb_EcivilP1 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel112 = new javax.swing.JLabel();
        jSeparator58 = new javax.swing.JSeparator();
        jScrollPane13 = new javax.swing.JScrollPane();
        jl_alergias1 = new javax.swing.JList<>();
        jLabel113 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jl_alergias2 = new javax.swing.JList<>();
        jSeparator59 = new javax.swing.JSeparator();
        jLabel114 = new javax.swing.JLabel();
        lb_SalarioExpectante = new javax.swing.JLabel();
        jSeparator64 = new javax.swing.JSeparator();
        jLabel121 = new javax.swing.JLabel();
        lb_TC = new javax.swing.JLabel();
        jSeparator65 = new javax.swing.JSeparator();
        jLabel122 = new javax.swing.JLabel();
        lb_TT = new javax.swing.JLabel();
        Postulante = new javax.swing.JPanel();
        MenuBar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tf_buscar = new javax.swing.JTextField();
        lb_busqueda = new javax.swing.JLabel();
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
        bt_modificarPerfil = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        bt_miPerfil = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator36 = new javax.swing.JSeparator();
        bt_eliminarPerfil = new javax.swing.JPanel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jSeparator39 = new javax.swing.JSeparator();
        pn_EmpleosDisponibles = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_EmpleosDisponibles = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        pn_EmpleosPostulados = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jt_EmpleosPostulados = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
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
        bt_modificarPerfilE = new javax.swing.JPanel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        bt_eliminarPerfilE = new javax.swing.JPanel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jSeparator40 = new javax.swing.JSeparator();
        jSeparator41 = new javax.swing.JSeparator();
        pn_perfilEmpresa = new javax.swing.JPanel();
        jl_name1 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jSeparator26 = new javax.swing.JSeparator();
        jLabel61 = new javax.swing.JLabel();
        jl_CIF = new javax.swing.JLabel();
        jSeparator27 = new javax.swing.JSeparator();
        jLabel63 = new javax.swing.JLabel();
        jl_Director = new javax.swing.JLabel();
        jSeparator28 = new javax.swing.JSeparator();
        jLabel65 = new javax.swing.JLabel();
        jl_direccionE = new javax.swing.JLabel();
        jSeparator29 = new javax.swing.JSeparator();
        jLabel67 = new javax.swing.JLabel();
        jl_telE = new javax.swing.JLabel();
        jSeparator38 = new javax.swing.JSeparator();
        jLabel84 = new javax.swing.JLabel();
        jl_correoE = new javax.swing.JLabel();
        pn_PuestosDisponibles = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jl_EDisponibles = new javax.swing.JList<>();
        jLabel14 = new javax.swing.JLabel();
        pn_postulantes = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jl_postulantes = new javax.swing.JList<>();
        bt_regresar = new javax.swing.JPanel();
        jLabel144 = new javax.swing.JLabel();
        Login = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tf_usuario = new javax.swing.JTextField();
        pf_contra = new javax.swing.JPasswordField();
        bt_iniciarSesión = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jSeparator42 = new javax.swing.JSeparator();
        jSeparator43 = new javax.swing.JSeparator();
        bt_crearUsuario = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();

        jPanel3.setBackground(new java.awt.Color(0, 114, 177));
        jPanel3.setMinimumSize(new java.awt.Dimension(600, 725));
        jPanel3.setName(""); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(600, 725));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tab1.setBackground(new java.awt.Color(236, 240, 245));
        tab1.setForeground(new java.awt.Color(0, 0, 0));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(55, 55, 55));
        jLabel30.setText("Nombre del Empleo");
        jPanel4.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 30));
        jPanel4.add(jSeparator30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 500, 30));

        jLabel62.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(55, 55, 55));
        jLabel62.setText("Nivel Educativo");
        jPanel4.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, 30));
        jPanel4.add(jSeparator31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 500, 30));
        jPanel4.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 12, -1, -1));

        jLabel66.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(55, 55, 55));
        jLabel66.setText("Años de experiencia necesarios");
        jPanel4.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, -1, 30));
        jPanel4.add(jSeparator32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 500, 30));

        jLabel68.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(55, 55, 55));
        jLabel68.setText("Modalidad");
        jPanel4.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, 30));
        jPanel4.add(tf_empleo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 230, 30));
        jPanel4.add(jSeparator33, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 500, 30));
        jPanel4.add(jSeparator34, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 500, 30));

        jLabel70.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(55, 55, 55));
        jLabel70.setText("Antecedentes Penales");
        jPanel4.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, -1, 40));
        jPanel4.add(jSeparator35, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 500, 30));
        jPanel4.add(tf_tipoEmpleo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 230, 30));

        ff_añosE.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jPanel4.add(ff_añosE, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, 230, 30));

        jLabel80.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(55, 55, 55));
        jLabel80.setText("Tipo de Empleo");
        jPanel4.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, 30));

        cb_modalidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Presencial", "Híbrido", "Remoto" }));
        jPanel4.add(cb_modalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 230, 32));

        cb_nivelEducativo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Educación Media", "Grado", "Master", "Doctorado" }));
        jPanel4.add(cb_nivelEducativo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, 230, 32));

        ta_RP.setEditable(false);
        ta_RP.setColumns(20);
        ta_RP.setRows(5);
        jScrollPane5.setViewportView(ta_RP);

        jPanel4.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 440, 290, 70));

        rb_si.setBackground(new java.awt.Color(255, 255, 255));
        bg_antecedentes.add(rb_si);
        rb_si.setForeground(new java.awt.Color(55, 55, 55));
        rb_si.setText("Sí");
        jPanel4.add(rb_si, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 550, -1, -1));

        rb_no.setBackground(new java.awt.Color(255, 255, 255));
        bg_antecedentes.add(rb_no);
        rb_no.setForeground(new java.awt.Color(55, 55, 55));
        rb_no.setText("No");
        jPanel4.add(rb_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 550, -1, -1));

        bt_RP.setBackground(new java.awt.Color(255, 255, 255));
        bt_RP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bt_RP.setForeground(new java.awt.Color(55, 55, 55));
        bt_RP.setText("Requisitos Personales");
        bt_RP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_RPMouseClicked(evt);
            }
        });
        jPanel4.add(bt_RP, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, -1, -1));
        jPanel4.add(jSeparator66, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 500, 30));
        jPanel4.add(tf_idEmpleo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 230, 30));

        jLabel123.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel123.setForeground(new java.awt.Color(55, 55, 55));
        jLabel123.setText("ID Empleo");
        jPanel4.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, 30));

        tab1.addTab("General", jPanel4);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel6.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 12, -1, -1));

        cb_idiomas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inglés", "Español", "Mandarín", "Francés", "Portugués", "Italiano" }));
        jPanel6.add(cb_idiomas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 250, 32));

        jLabel73.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(55, 55, 55));
        jLabel73.setText("Idioma");
        jPanel6.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, -1, -1));

        jLabel74.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(55, 55, 55));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("Certificaciones");
        jPanel6.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 240, -1));
        jPanel6.add(jSeparator37, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 500, 30));
        jPanel6.add(tf_usuario_a1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 180, 200, 30));
        jPanel6.add(tf_contraseña1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 270, 200, 30));
        jPanel6.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 32, -1, -1));
        jPanel6.add(ff_clasesA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 200, 30));

        jScrollPane9.setViewportView(jl_certificacionesAñadidas);

        jPanel6.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, 140, 210));

        bt_añadirIdioma.setBackground(new java.awt.Color(195, 22, 28));
        bt_añadirIdioma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_añadirIdiomaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_añadirIdiomaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_añadirIdiomaMouseExited(evt);
            }
        });

        jLabel75.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel75.setText("Añadir Idioma");

        javax.swing.GroupLayout bt_añadirIdiomaLayout = new javax.swing.GroupLayout(bt_añadirIdioma);
        bt_añadirIdioma.setLayout(bt_añadirIdiomaLayout);
        bt_añadirIdiomaLayout.setHorizontalGroup(
            bt_añadirIdiomaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );
        bt_añadirIdiomaLayout.setVerticalGroup(
            bt_añadirIdiomaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel75, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel6.add(bt_añadirIdioma, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 130, 30));

        jScrollPane10.setViewportView(jl_idiomasAñadidos);

        jPanel6.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 140, 210));
        jPanel6.add(tf_certificaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 250, 30));

        bt_añadirCertificacion.setBackground(new java.awt.Color(195, 22, 28));
        bt_añadirCertificacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_añadirCertificacionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_añadirCertificacionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_añadirCertificacionMouseExited(evt);
            }
        });

        jLabel77.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel77.setText("Añadir Certificación");

        javax.swing.GroupLayout bt_añadirCertificacionLayout = new javax.swing.GroupLayout(bt_añadirCertificacion);
        bt_añadirCertificacion.setLayout(bt_añadirCertificacionLayout);
        bt_añadirCertificacionLayout.setHorizontalGroup(
            bt_añadirCertificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel77, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bt_añadirCertificacionLayout.setVerticalGroup(
            bt_añadirCertificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bt_añadirCertificacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.add(bt_añadirCertificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 430, 170, -1));

        tab1.addTab("Idioma/Certificaciones", jPanel6);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.add(cb_puestos, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 250, 32));

        jLabel115.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel115.setForeground(new java.awt.Color(55, 55, 55));
        jLabel115.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel115.setText("Añadir Puesto Disponible");
        jPanel1.add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 250, -1));

        bt_crearPuesto.setBackground(new java.awt.Color(195, 22, 28));
        bt_crearPuesto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_crearPuestoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_crearPuestoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_crearPuestoMouseExited(evt);
            }
        });

        jLabel116.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel116.setForeground(new java.awt.Color(255, 255, 255));
        jLabel116.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel116.setText("Crear Puesto");

        javax.swing.GroupLayout bt_crearPuestoLayout = new javax.swing.GroupLayout(bt_crearPuesto);
        bt_crearPuesto.setLayout(bt_crearPuestoLayout);
        bt_crearPuestoLayout.setHorizontalGroup(
            bt_crearPuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel116, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );
        bt_crearPuestoLayout.setVerticalGroup(
            bt_crearPuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel116, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(bt_crearPuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 490, 130, 30));

        jScrollPane15.setViewportView(jl_puestosAñadidos);

        jPanel1.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 140, 180));

        jLabel117.setForeground(new java.awt.Color(55, 55, 55));
        jLabel117.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel117.setText("o");
        jPanel1.add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 30, 20));
        jPanel1.add(jSeparator60, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 220, 20));
        jPanel1.add(jSeparator61, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 230, 20));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(55, 55, 55));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Crear Nuevo Puesto");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 540, -1));

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(55, 55, 55));
        jLabel40.setText("Nombre del Puesto");
        jPanel1.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, 30));
        jPanel1.add(jSeparator62, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 500, 30));

        jLabel118.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel118.setForeground(new java.awt.Color(55, 55, 55));
        jLabel118.setText("Sueldo");
        jPanel1.add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, -1, 30));
        jPanel1.add(tf_puesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, 230, 30));
        jPanel1.add(jSeparator63, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 500, 30));
        jPanel1.add(tf_sueldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 430, 230, 30));

        jLabel119.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel119.setForeground(new java.awt.Color(55, 55, 55));
        jLabel119.setText("Tipo de Puesto");
        jPanel1.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, -1, 30));
        jPanel1.add(tf_tipoPuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, 230, 30));

        bt_añadirPuesto.setBackground(new java.awt.Color(195, 22, 28));
        bt_añadirPuesto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_añadirPuestoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_añadirPuestoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_añadirPuestoMouseExited(evt);
            }
        });

        jLabel120.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel120.setForeground(new java.awt.Color(255, 255, 255));
        jLabel120.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel120.setText("Añadir Puesto");

        javax.swing.GroupLayout bt_añadirPuestoLayout = new javax.swing.GroupLayout(bt_añadirPuesto);
        bt_añadirPuesto.setLayout(bt_añadirPuestoLayout);
        bt_añadirPuestoLayout.setHorizontalGroup(
            bt_añadirPuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel120, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );
        bt_añadirPuestoLayout.setVerticalGroup(
            bt_añadirPuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel120, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(bt_añadirPuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 130, 30));

        tab1.addTab("Puestos", jPanel1);

        jPanel3.add(tab1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 540, 620));

        bt_añadirP.setBackground(new java.awt.Color(195, 22, 28));
        bt_añadirP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_añadirPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_añadirPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_añadirPMouseExited(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setText("Añadir Empleo");

        javax.swing.GroupLayout bt_añadirPLayout = new javax.swing.GroupLayout(bt_añadirP);
        bt_añadirP.setLayout(bt_añadirPLayout);
        bt_añadirPLayout.setHorizontalGroup(
            bt_añadirPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel69, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bt_añadirPLayout.setVerticalGroup(
            bt_añadirPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bt_añadirPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(bt_añadirP, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 670, 120, 40));

        javax.swing.GroupLayout jd_crearEmpleoLayout = new javax.swing.GroupLayout(jd_crearEmpleo.getContentPane());
        jd_crearEmpleo.getContentPane().setLayout(jd_crearEmpleoLayout);
        jd_crearEmpleoLayout.setHorizontalGroup(
            jd_crearEmpleoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jd_crearEmpleoLayout.setVerticalGroup(
            jd_crearEmpleoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jd_crearUsuario.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(11, 103, 194));
        jPanel2.setMinimumSize(new java.awt.Dimension(520, 500));
        jPanel2.setPreferredSize(new java.awt.Dimension(520, 500));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel86.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(55, 55, 55));
        jLabel86.setText("Usuario");
        jPanel17.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 30));

        tf_Cusuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel17.add(tf_Cusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 200, 30));
        jPanel17.add(jSeparator44, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 400, 30));

        jLabel88.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(55, 55, 55));
        jLabel88.setText("Contraseña");
        jPanel17.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 30));

        tf_contraseña.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel17.add(tf_contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 200, 30));
        jPanel17.add(jSeparator45, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 400, 30));

        bt_empresa.setBackground(new java.awt.Color(255, 255, 255));
        bg_usuario.add(bt_empresa);
        bt_empresa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bt_empresa.setForeground(new java.awt.Color(55, 55, 55));
        bt_empresa.setText("Empresa");
        jPanel17.add(bt_empresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, -1));

        bt_persona.setBackground(new java.awt.Color(255, 255, 255));
        bg_usuario.add(bt_persona);
        bt_persona.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bt_persona.setForeground(new java.awt.Color(55, 55, 55));
        bt_persona.setText("Persona");
        jPanel17.add(bt_persona, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, -1, -1));

        jLabel89.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(55, 55, 55));
        jLabel89.setText("Número de Cuenta");
        jPanel17.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, 30));

        bt_crearU.setBackground(new java.awt.Color(195, 22, 28));
        bt_crearU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_crearUMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_crearUMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_crearUMouseExited(evt);
            }
        });
        bt_crearU.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Crear Usuario");
        bt_crearU.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, 110, 40));

        jPanel17.add(bt_crearU, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 120, 40));

        try {
            ff_noCuenta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ff_noCuenta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel17.add(ff_noCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 200, 30));

        jPanel2.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 460, 350));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Crear Usuario");
        jPanel2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 460, -1));

        jd_crearUsuario.getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 450));

        jd_Persona.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel20.setBackground(new java.awt.Color(11, 103, 194));
        jPanel20.setMinimumSize(new java.awt.Dimension(520, 500));
        jPanel20.setPreferredSize(new java.awt.Dimension(520, 500));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel90.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(55, 55, 55));
        jLabel90.setText("Nombre");
        jPanel21.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, 30));

        tf_NombreP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel21.add(tf_NombreP, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 200, 30));
        jPanel21.add(jSeparator46, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 400, 30));

        jLabel91.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(55, 55, 55));
        jLabel91.setText("Apellido");
        jPanel21.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 30));

        tf_apellido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel21.add(tf_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 200, 30));
        jPanel21.add(jSeparator47, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 400, 30));

        bt_M.setBackground(new java.awt.Color(255, 255, 255));
        bg_genero.add(bt_M);
        bt_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bt_M.setForeground(new java.awt.Color(55, 55, 55));
        bt_M.setText("Masculino");
        jPanel21.add(bt_M, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 500, -1, -1));

        bt_F.setBackground(new java.awt.Color(255, 255, 255));
        bg_genero.add(bt_F);
        bt_F.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bt_F.setForeground(new java.awt.Color(55, 55, 55));
        bt_F.setText("Femenino");
        jPanel21.add(bt_F, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 500, -1, -1));

        jLabel92.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(55, 55, 55));
        jLabel92.setText("Edad");
        jPanel21.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, 30));

        bt_guardarP.setBackground(new java.awt.Color(195, 22, 28));
        bt_guardarP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_guardarPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_guardarPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_guardarPMouseExited(evt);
            }
        });
        bt_guardarP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Guardar Datos");
        bt_guardarP.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, 110, 40));

        jPanel21.add(bt_guardarP, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 550, 120, 40));

        ff_Edad.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        ff_Edad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel21.add(ff_Edad, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 200, 30));

        jLabel93.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(55, 55, 55));
        jLabel93.setText("Correo");
        jPanel21.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, 30));

        tf_correoP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel21.add(tf_correoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 200, 30));
        jPanel21.add(jSeparator48, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 400, 30));

        jLabel94.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(55, 55, 55));
        jLabel94.setText("Teléfono");
        jPanel21.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, 30));

        tf_nacionalidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel21.add(tf_nacionalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 430, 200, 30));
        jPanel21.add(jSeparator49, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 400, 30));

        jLabel95.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(55, 55, 55));
        jLabel95.setText("Género");
        jPanel21.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, -1, 30));

        try {
            ff_telefonoP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ff_telefonoP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel21.add(ff_telefonoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, 200, 30));
        jPanel21.add(jSeparator50, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 400, 30));
        jPanel21.add(jSeparator51, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 400, 30));

        jLabel96.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(55, 55, 55));
        jLabel96.setText("Nacionalidad");
        jPanel21.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, -1, 30));

        jPanel20.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 460, 620));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Crear Nueva Persona");
        jPanel20.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 460, -1));

        jd_Persona.getContentPane().add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 730));

        jd_Empresa.setAutoRequestFocus(false);
        jd_Empresa.setMinimumSize(new java.awt.Dimension(500, 500));
        jd_Empresa.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pn_fondoModE.setBackground(new java.awt.Color(11, 103, 194));
        pn_fondoModE.setMinimumSize(new java.awt.Dimension(520, 500));
        pn_fondoModE.setPreferredSize(new java.awt.Dimension(520, 500));
        pn_fondoModE.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel97.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(55, 55, 55));
        jLabel97.setText("Nombre");
        jPanel23.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, 30));

        tf_NombreE.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel23.add(tf_NombreE, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 200, 30));
        jPanel23.add(jSeparator52, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 400, 30));

        jLabel98.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(55, 55, 55));
        jLabel98.setText("CIF");
        jPanel23.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 30));

        tf_CIF.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel23.add(tf_CIF, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 200, 30));
        jPanel23.add(jSeparator53, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 400, 30));

        bt_guardarE.setBackground(new java.awt.Color(195, 22, 28));
        bt_guardarE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_guardarEMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_guardarEMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_guardarEMouseExited(evt);
            }
        });
        bt_guardarE.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_btModEmpresa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb_btModEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        lb_btModEmpresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_btModEmpresa.setText("Crear Perfil");
        bt_guardarE.add(lb_btModEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, 110, 40));

        jPanel23.add(bt_guardarE, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 490, 120, 40));

        jLabel100.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(55, 55, 55));
        jLabel100.setText("Director");
        jPanel23.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, 30));

        tf_director.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel23.add(tf_director, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 200, 30));
        jPanel23.add(jSeparator54, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 400, 30));

        jLabel101.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(55, 55, 55));
        jLabel101.setText("Teléfono");
        jPanel23.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, 30));

        tf_direccionD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel23.add(tf_direccionD, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 430, 200, 30));

        ff_telefonoP1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        ff_telefonoP1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel23.add(ff_telefonoP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, 200, 30));
        jPanel23.add(jSeparator56, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 400, 30));
        jPanel23.add(jSeparator57, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 400, 30));

        jLabel103.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(55, 55, 55));
        jLabel103.setText("Correo");
        jPanel23.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, 30));

        jLabel102.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(55, 55, 55));
        jLabel102.setText("Dirección");
        jPanel23.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, -1, 30));

        tf_correoE.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel23.add(tf_correoE, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 200, 30));

        pn_fondoModE.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 460, 550));

        lb_tituloJDEmpresa.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        lb_tituloJDEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        lb_tituloJDEmpresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_tituloJDEmpresa.setText("Crear Nueva Empresa");
        pn_fondoModE.add(lb_tituloJDEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 460, -1));

        jd_Empresa.getContentPane().add(pn_fondoModE, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 650));

        jd_ModificarPersona.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(0, 114, 177));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tb_modificarP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Atributos", "Datos"
            }
        ));
        tb_modificarP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_modificarPMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tb_modificarP);
        if (tb_modificarP.getColumnModel().getColumnCount() > 0) {
            tb_modificarP.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel24.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 760, 490));

        bt_modificarP.setBackground(new java.awt.Color(195, 22, 28));
        bt_modificarP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_modificarPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_modificarPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_modificarPMouseExited(evt);
            }
        });
        bt_modificarP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel82.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(255, 255, 255));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setText("Modificar Datos");
        bt_modificarP.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 120, 40));

        jPanel24.add(bt_modificarP, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 540, 140, 40));

        jPanel7.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 820, 600));

        jLabel99.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(255, 255, 255));
        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel99.setText("Modificar Persona");
        jPanel7.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 830, -1));

        jd_ModificarPersona.getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 710));

        Postular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/aplicar.png"))); // NOI18N
        Postular.setText("Postular");
        Postular.setToolTipText("");
        Postular.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PostularMouseClicked(evt);
            }
        });
        Postular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PostularActionPerformed(evt);
            }
        });
        menuPostular.add(Postular);

        Contratar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/contrato.png"))); // NOI18N
        Contratar.setText("Contratar");
        Contratar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContratarActionPerformed(evt);
            }
        });
        menuContratar.add(Contratar);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 800));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pn_perfilPersona.setBackground(new java.awt.Color(255, 255, 255));
        pn_perfilPersona.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jl_name.setBackground(new java.awt.Color(55, 55, 55));
        jl_name.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jl_name.setForeground(new java.awt.Color(55, 55, 55));
        jl_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_name.setText("NOMBRE");
        jl_name.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jl_name.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pn_perfilPersona.add(jl_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 280, -1));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/perfil.png"))); // NOI18N
        pn_perfilPersona.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 280, -1));

        jl_email.setForeground(new java.awt.Color(55, 55, 55));
        jl_email.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_email.setText("Correo del usuario");
        pn_perfilPersona.add(jl_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 280, -1));
        pn_perfilPersona.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 260, 10));
        pn_perfilPersona.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 260, 10));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 114, 177));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Correo");
        pn_perfilPersona.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 280, -1));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 114, 177));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Edad");
        pn_perfilPersona.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 280, -1));

        jl_age.setForeground(new java.awt.Color(55, 55, 55));
        jl_age.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_age.setText("Edad del usuario");
        pn_perfilPersona.add(jl_age, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 280, -1));
        pn_perfilPersona.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 260, 10));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 114, 177));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Teléfono");
        pn_perfilPersona.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 280, -1));

        jl_phone.setForeground(new java.awt.Color(55, 55, 55));
        jl_phone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_phone.setText("Teléfono del usuario");
        pn_perfilPersona.add(jl_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 280, -1));
        pn_perfilPersona.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 260, 10));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 114, 177));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Nacionalidad");
        pn_perfilPersona.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 280, -1));

        jl_nation.setForeground(new java.awt.Color(55, 55, 55));
        jl_nation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_nation.setText("Nacionalidad del usuario");
        pn_perfilPersona.add(jl_nation, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 280, -1));
        pn_perfilPersona.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 260, 10));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 114, 177));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Género");
        pn_perfilPersona.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 280, -1));

        jl_gender.setForeground(new java.awt.Color(55, 55, 55));
        jl_gender.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_gender.setText("Género del usuario");
        pn_perfilPersona.add(jl_gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 280, -1));

        getContentPane().add(pn_perfilPersona, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 280, 560));

        pn_DatosDelPostulante.setBackground(new java.awt.Color(239, 238, 236));
        pn_DatosDelPostulante.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setForeground(new java.awt.Color(55, 55, 55));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        pn_dFamiliaresP.setBackground(new java.awt.Color(255, 255, 255));
        pn_dFamiliaresP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 114, 177));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("Estado Civil");
        pn_dFamiliaresP.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 280, -1));

        lb_EcivilP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_EcivilP.setForeground(new java.awt.Color(55, 55, 55));
        lb_EcivilP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_EcivilP.setText("Estado Civil del usuario");
        pn_dFamiliaresP.add(lb_EcivilP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 280, -1));
        pn_dFamiliaresP.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 880, 10));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 114, 177));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel43.setText("Correo de Dependientes");
        pn_dFamiliaresP.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 280, -1));
        pn_dFamiliaresP.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 880, 10));

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 114, 177));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setText("Hijos");
        pn_dFamiliaresP.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 280, -1));

        lb_hijosP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_hijosP.setForeground(new java.awt.Color(55, 55, 55));
        lb_hijosP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_hijosP.setText("Hijos del usuario");
        pn_dFamiliaresP.add(lb_hijosP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 280, -1));
        pn_dFamiliaresP.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 880, 10));

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 114, 177));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("Dirección");
        pn_dFamiliaresP.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 280, -1));

        lb_dirrecionP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_dirrecionP.setForeground(new java.awt.Color(55, 55, 55));
        lb_dirrecionP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_dirrecionP.setText("Dirección del usuario");
        pn_dFamiliaresP.add(lb_dirrecionP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 280, -1));

        jl_correoDP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jl_correoDP.setModel(new DefaultListModel ());
        jSP_correoDP.setViewportView(jl_correoDP);

        pn_dFamiliaresP.add(jSP_correoDP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 880, 190));

        jTabbedPane1.addTab("Datos Familiares", pn_dFamiliaresP);

        pn_dSanitariosP.setBackground(new java.awt.Color(255, 255, 255));
        pn_dSanitariosP.setFocusable(false);
        pn_dSanitariosP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 114, 177));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel42.setText("Información de Medicamentos");
        pn_dSanitariosP.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 370, -1));

        lb_infoMed.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_infoMed.setForeground(new java.awt.Color(55, 55, 55));
        lb_infoMed.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_infoMed.setText("Info Medico del usuario");
        pn_dSanitariosP.add(lb_infoMed, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 280, -1));
        pn_dSanitariosP.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 880, 10));

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 114, 177));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel44.setText("Alergias");
        pn_dSanitariosP.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 280, -1));
        pn_dSanitariosP.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 880, 10));

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 114, 177));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel46.setText("Historial Médico");
        pn_dSanitariosP.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 280, -1));
        pn_dSanitariosP.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 880, 10));

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(0, 114, 177));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel48.setText("Resultado Pruebas");
        pn_dSanitariosP.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 280, -1));

        lb_resultadoP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_resultadoP.setForeground(new java.awt.Color(55, 55, 55));
        lb_resultadoP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_resultadoP.setText("RP del usuario");
        pn_dSanitariosP.add(lb_resultadoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 280, -1));

        jl_HM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jl_HM.setModel(new DefaultListModel ());
        jScrollPane7.setViewportView(jl_HM);

        pn_dSanitariosP.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 880, 120));

        jl_alergias.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jl_alergias.setModel(new DefaultListModel ());
        jScrollPane8.setViewportView(jl_alergias);

        pn_dSanitariosP.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 880, 120));

        jTabbedPane1.addTab("Datos Sanitarios", pn_dSanitariosP);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 114, 177));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel49.setText("Antecedentes Penales");
        jPanel5.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 280, -1));
        jPanel5.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 880, 10));

        jl_aPenales.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jl_aPenales.setModel(new DefaultListModel ());
        jScrollPane6.setViewportView(jl_aPenales);

        jPanel5.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 880, 220));

        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(0, 114, 177));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel50.setText("Servicio Militar");
        jPanel5.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 280, -1));

        lb_SM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_SM.setForeground(new java.awt.Color(55, 55, 55));
        lb_SM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_SM.setText("SM del usuario");
        jPanel5.add(lb_SM, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 280, -1));
        jPanel5.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 880, 10));

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(0, 114, 177));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setText("SSN");
        jPanel5.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 280, -1));

        lb_SSN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_SSN.setForeground(new java.awt.Color(55, 55, 55));
        lb_SSN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_SSN.setText("SSN del usuario");
        jPanel5.add(lb_SSN, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 280, -1));

        jTabbedPane1.addTab("Datos Legales", jPanel5);

        pn_dAcademicosP.setBackground(new java.awt.Color(255, 255, 255));
        pn_dAcademicosP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 114, 177));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("Institución");
        pn_dAcademicosP.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 280, -1));

        lb_institucion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_institucion.setForeground(new java.awt.Color(55, 55, 55));
        lb_institucion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_institucion.setText("Institución del usuario");
        pn_dAcademicosP.add(lb_institucion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 280, -1));
        pn_dAcademicosP.add(jSeparator23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 880, 10));

        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(0, 114, 177));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel58.setText("Nivel Universitario");
        pn_dAcademicosP.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 280, -1));

        lb_NU.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_NU.setForeground(new java.awt.Color(55, 55, 55));
        lb_NU.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_NU.setText("NU del usuario");
        pn_dAcademicosP.add(lb_NU, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 280, -1));
        pn_dAcademicosP.add(jSeparator24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 880, 10));

        jLabel59.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(0, 114, 177));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel59.setText("Estudios");
        pn_dAcademicosP.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 280, -1));

        lb_Estudios.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_Estudios.setForeground(new java.awt.Color(55, 55, 55));
        lb_Estudios.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_Estudios.setText("Estudios del usuario");
        pn_dAcademicosP.add(lb_Estudios, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 280, -1));
        pn_dAcademicosP.add(jSeparator25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 880, 10));

        jLabel60.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 114, 177));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel60.setText("Títulos");
        pn_dAcademicosP.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 280, -1));

        lb_titulos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_titulos.setForeground(new java.awt.Color(55, 55, 55));
        lb_titulos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_titulos.setText("Títulos del usuario");
        pn_dAcademicosP.add(lb_titulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 280, -1));

        jTabbedPane1.addTab("Datos Académicos", pn_dAcademicosP);

        pn_dProfesionalesP.setBackground(new java.awt.Color(255, 255, 255));
        pn_dProfesionalesP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setForeground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pn_dProfesionalesP.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(0, 114, 177));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setText("Años de Experiencia");
        pn_dProfesionalesP.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 280, -1));

        lb_AñosE.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_AñosE.setForeground(new java.awt.Color(55, 55, 55));
        lb_AñosE.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_AñosE.setText("AE del usuario");
        pn_dProfesionalesP.add(lb_AñosE, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 280, -1));
        pn_dProfesionalesP.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 880, 10));

        jLabel53.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(0, 114, 177));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel53.setText("Logros Profesionales");
        pn_dProfesionalesP.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 280, -1));

        lb_LogrosP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_LogrosP.setForeground(new java.awt.Color(55, 55, 55));
        lb_LogrosP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_LogrosP.setText("Logros del usuario");
        pn_dProfesionalesP.add(lb_LogrosP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 280, -1));
        pn_dProfesionalesP.add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 880, 10));

        jLabel54.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(0, 114, 177));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel54.setText("Idiomas");
        pn_dProfesionalesP.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 280, -1));

        lb_Idiomas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_Idiomas.setForeground(new java.awt.Color(55, 55, 55));
        lb_Idiomas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_Idiomas.setText("Idiomas del usuario");
        pn_dProfesionalesP.add(lb_Idiomas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 280, -1));
        pn_dProfesionalesP.add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 880, 10));

        jLabel55.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(0, 114, 177));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel55.setText("Certificaciones");
        pn_dProfesionalesP.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 280, -1));

        lb_Certificaciones.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_Certificaciones.setForeground(new java.awt.Color(55, 55, 55));
        lb_Certificaciones.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_Certificaciones.setText("Certificaciones del usuario");
        pn_dProfesionalesP.add(lb_Certificaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 280, -1));
        pn_dProfesionalesP.add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 880, 10));

        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(0, 114, 177));
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel56.setText("Conocimientos Específicos");
        pn_dProfesionalesP.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 310, -1));

        lb_ConEsp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_ConEsp.setForeground(new java.awt.Color(55, 55, 55));
        lb_ConEsp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_ConEsp.setText("CE del usuario");
        pn_dProfesionalesP.add(lb_ConEsp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 280, -1));

        jTabbedPane1.addTab("Datos Laborales/Profesionales", pn_dProfesionalesP);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel76.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(0, 114, 177));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel76.setText("Trabajo Actual");
        jPanel9.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 280, -1));

        lb_TrabActual.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_TrabActual.setForeground(new java.awt.Color(55, 55, 55));
        lb_TrabActual.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_TrabActual.setText("Ninguno");
        jPanel9.add(lb_TrabActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 880, -1));
        jPanel9.add(jSeparator55, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 880, 10));

        jLabel110.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(0, 114, 177));
        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel110.setText("Trabajos Anteriores");
        jPanel9.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 280, -1));

        lb_EcivilP2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_EcivilP2.setForeground(new java.awt.Color(55, 55, 55));
        lb_EcivilP2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_EcivilP2.setText("Trabajos Anteriores del usuario");
        lb_EcivilP2.setToolTipText("");
        lb_EcivilP2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lb_EcivilP2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel9.add(lb_EcivilP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 870, 390));

        jTabbedPane1.addTab("Historial de Trabajo", jPanel9);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel111.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(0, 114, 177));
        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel111.setText("Cuentas Familiares");
        jPanel10.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 280, -1));

        lb_EcivilP1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_EcivilP1.setForeground(new java.awt.Color(55, 55, 55));
        lb_EcivilP1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_EcivilP1.setText("Supongo que listen nombre, apellido y el id, maybe el correo");
        jPanel10.add(lb_EcivilP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 880, -1));

        jTabbedPane1.addTab("Cuentas Familiares en la App", jPanel10);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel112.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(0, 114, 177));
        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel112.setText("Puestos Aceptables");
        jPanel11.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 280, -1));
        jPanel11.add(jSeparator58, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 880, 10));

        jl_alergias1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jl_alergias1.setModel(new DefaultListModel ());
        jScrollPane13.setViewportView(jl_alergias1);

        jPanel11.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 880, 90));

        jLabel113.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(0, 114, 177));
        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel113.setText("Puestos Inaceptables");
        jPanel11.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 280, -1));

        jl_alergias2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jl_alergias2.setModel(new DefaultListModel ());
        jScrollPane14.setViewportView(jl_alergias2);

        jPanel11.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 880, 90));
        jPanel11.add(jSeparator59, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 880, 10));

        jLabel114.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(0, 114, 177));
        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel114.setText("Salario Expectante");
        jPanel11.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 280, -1));

        lb_SalarioExpectante.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_SalarioExpectante.setForeground(new java.awt.Color(55, 55, 55));
        lb_SalarioExpectante.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_SalarioExpectante.setText("SE del usuario");
        jPanel11.add(lb_SalarioExpectante, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 280, -1));
        jPanel11.add(jSeparator64, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 880, 10));

        jLabel121.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel121.setForeground(new java.awt.Color(0, 114, 177));
        jLabel121.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel121.setText("Tipo de Contrato");
        jPanel11.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 280, -1));

        lb_TC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_TC.setForeground(new java.awt.Color(55, 55, 55));
        lb_TC.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_TC.setText("TC del usuario");
        jPanel11.add(lb_TC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 280, -1));
        jPanel11.add(jSeparator65, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 880, 10));

        jLabel122.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel122.setForeground(new java.awt.Color(0, 114, 177));
        jLabel122.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel122.setText("Tipo de Trabajo");
        jPanel11.add(jLabel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 280, -1));

        lb_TT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_TT.setForeground(new java.awt.Color(55, 55, 55));
        lb_TT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_TT.setText("TT del usuario");
        jPanel11.add(lb_TT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 280, -1));

        jTabbedPane1.addTab("Solicitud de Trabajo", jPanel11);

        pn_DatosDelPostulante.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 930, 620));

        getContentPane().add(pn_DatosDelPostulante, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 980, 640));

        Postulante.setBackground(new java.awt.Color(239, 238, 236));
        Postulante.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MenuBar.setBackground(new java.awt.Color(254, 254, 254));
        MenuBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo acortado.png"))); // NOI18N
        MenuBar.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 70, 70));

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
        MenuBar.add(tf_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 250, 30));

        lb_busqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lupa.png"))); // NOI18N
        lb_busqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_busquedaMouseClicked(evt);
            }
        });
        MenuBar.add(lb_busqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, -1, -1));

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
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/silla-giratoria.png"))); // NOI18N
        bt_EDisponibles.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 130, -1));

        MenuBar.add(bt_EDisponibles, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 130, 70));

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
        bt_EPostulados.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 120, -1));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/maletin.png"))); // NOI18N
        bt_EPostulados.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 120, -1));

        MenuBar.add(bt_EPostulados, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 120, 70));

        jSeparator1.setBackground(new java.awt.Color(55, 55, 55));
        jSeparator1.setForeground(new java.awt.Color(55, 55, 55));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        MenuBar.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 10, 53));

        jSeparator2.setBackground(new java.awt.Color(55, 55, 55));
        jSeparator2.setForeground(new java.awt.Color(55, 55, 55));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        MenuBar.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 10, 10, 53));

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

        bt_modificarPerfil.setBackground(new java.awt.Color(255, 255, 255));
        bt_modificarPerfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_modificarPerfilMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_modificarPerfilMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_modificarPerfilMouseExited(evt);
            }
        });
        bt_modificarPerfil.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel71.setBackground(new java.awt.Color(55, 55, 55));
        jLabel71.setForeground(new java.awt.Color(55, 55, 55));
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel71.setText("Modificar Perfil");
        bt_modificarPerfil.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 100, -1));

        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editar-perfil.png"))); // NOI18N
        bt_modificarPerfil.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 100, -1));

        MenuBar.add(bt_modificarPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 0, 100, 70));

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
        bt_miPerfil.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 90, -1));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/usuario.png"))); // NOI18N
        bt_miPerfil.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, -1));

        MenuBar.add(bt_miPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 0, -1, 70));

        jSeparator36.setBackground(new java.awt.Color(55, 55, 55));
        jSeparator36.setForeground(new java.awt.Color(55, 55, 55));
        jSeparator36.setOrientation(javax.swing.SwingConstants.VERTICAL);
        MenuBar.add(jSeparator36, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 10, 10, 53));

        bt_eliminarPerfil.setBackground(new java.awt.Color(255, 255, 255));
        bt_eliminarPerfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_eliminarPerfilMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_eliminarPerfilMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_eliminarPerfilMouseExited(evt);
            }
        });
        bt_eliminarPerfil.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel104.setBackground(new java.awt.Color(55, 55, 55));
        jLabel104.setForeground(new java.awt.Color(55, 55, 55));
        jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel104.setText("Eliminar Perfil");
        bt_eliminarPerfil.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 90, -1));

        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel105.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/quitar-usuario.png"))); // NOI18N
        bt_eliminarPerfil.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, -1));

        MenuBar.add(bt_eliminarPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 0, 90, 70));

        jSeparator39.setBackground(new java.awt.Color(55, 55, 55));
        jSeparator39.setForeground(new java.awt.Color(55, 55, 55));
        jSeparator39.setOrientation(javax.swing.SwingConstants.VERTICAL);
        MenuBar.add(jSeparator39, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 10, 10, 53));

        Postulante.add(MenuBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 70));

        pn_EmpleosDisponibles.setBackground(new java.awt.Color(239, 238, 236));
        pn_EmpleosDisponibles.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jt_EmpleosDisponibles.setBackground(new java.awt.Color(255, 255, 255));
        jt_EmpleosDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Empresa ID", "Puesto", "Requisitos Personales", "Antecedentes", "Nivel Educativo", "Tipo de Trabajo", "Experiencia", "Idiomas Requeridos", "Certificaciones Requeridos", "Modalidad", "Empleo ID"
            }
        ));
        jt_EmpleosDisponibles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_EmpleosDisponiblesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jt_EmpleosDisponibles);

        pn_EmpleosDisponibles.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 920, 570));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(55, 55, 55));
        jLabel3.setText("EMPLEOS DISPONIBLES");
        pn_EmpleosDisponibles.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 280, -1));

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
        jt_EmpleosPostulados.setEnabled(false);
        jScrollPane2.setViewportView(jt_EmpleosPostulados);

        pn_EmpleosPostulados.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 920, 570));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(55, 55, 55));
        jLabel12.setText("EMPLEOS POSTULADOS");
        pn_EmpleosPostulados.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        Postulante.add(pn_EmpleosPostulados, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 980, 640));

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
        jLabel15.setText("Añadir Empleo");
        bt_MiPerfilE.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 90, -1));

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/silla-giratoria.png"))); // NOI18N
        bt_MiPerfilE.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, -1));

        MenuBar1.add(bt_MiPerfilE, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 0, 90, 70));

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

        MenuBar1.add(bt_PDisponibles, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 130, 70));

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
        MenuBar1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 10, 10, 53));

        bt_modificarPerfilE.setBackground(new java.awt.Color(255, 255, 255));
        bt_modificarPerfilE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_modificarPerfilEMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_modificarPerfilEMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_modificarPerfilEMouseExited(evt);
            }
        });
        bt_modificarPerfilE.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel106.setBackground(new java.awt.Color(55, 55, 55));
        jLabel106.setForeground(new java.awt.Color(55, 55, 55));
        jLabel106.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel106.setText("Modificar Perfil");
        bt_modificarPerfilE.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 100, -1));

        jLabel107.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel107.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editar-perfil.png"))); // NOI18N
        bt_modificarPerfilE.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 100, -1));

        MenuBar1.add(bt_modificarPerfilE, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 0, 100, 70));

        bt_eliminarPerfilE.setBackground(new java.awt.Color(255, 255, 255));
        bt_eliminarPerfilE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_eliminarPerfilEMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_eliminarPerfilEMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_eliminarPerfilEMouseExited(evt);
            }
        });
        bt_eliminarPerfilE.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel108.setBackground(new java.awt.Color(55, 55, 55));
        jLabel108.setForeground(new java.awt.Color(55, 55, 55));
        jLabel108.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel108.setText("Eliminar Perfil");
        bt_eliminarPerfilE.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 90, -1));

        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel109.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/quitar-usuario.png"))); // NOI18N
        bt_eliminarPerfilE.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, -1));

        MenuBar1.add(bt_eliminarPerfilE, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 0, 90, 70));

        jSeparator40.setBackground(new java.awt.Color(55, 55, 55));
        jSeparator40.setForeground(new java.awt.Color(55, 55, 55));
        jSeparator40.setOrientation(javax.swing.SwingConstants.VERTICAL);
        MenuBar1.add(jSeparator40, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 10, 10, 53));

        jSeparator41.setBackground(new java.awt.Color(55, 55, 55));
        jSeparator41.setForeground(new java.awt.Color(55, 55, 55));
        jSeparator41.setOrientation(javax.swing.SwingConstants.VERTICAL);
        MenuBar1.add(jSeparator41, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 10, 10, 53));

        Reclutador.add(MenuBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 70));

        pn_perfilEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        pn_perfilEmpresa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jl_name1.setBackground(new java.awt.Color(55, 55, 55));
        jl_name1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jl_name1.setForeground(new java.awt.Color(55, 55, 55));
        jl_name1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_name1.setText("EMPRESA");
        jl_name1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jl_name1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pn_perfilEmpresa.add(jl_name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 280, -1));

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/perfil de empresa.png"))); // NOI18N
        pn_perfilEmpresa.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 280, -1));
        pn_perfilEmpresa.add(jSeparator26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 260, 10));

        jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(1, 103, 153));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setText("CIF");
        pn_perfilEmpresa.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 280, -1));

        jl_CIF.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jl_CIF.setForeground(new java.awt.Color(55, 55, 55));
        jl_CIF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_CIF.setText("CIF de la empresa");
        pn_perfilEmpresa.add(jl_CIF, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 280, -1));
        pn_perfilEmpresa.add(jSeparator27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 260, 10));

        jLabel63.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(1, 103, 153));
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setText("Director");
        pn_perfilEmpresa.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 280, -1));

        jl_Director.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jl_Director.setForeground(new java.awt.Color(55, 55, 55));
        jl_Director.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_Director.setText("Director de la empresa");
        pn_perfilEmpresa.add(jl_Director, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 280, -1));
        pn_perfilEmpresa.add(jSeparator28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 260, 10));

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(1, 103, 153));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel65.setText("Dirección");
        pn_perfilEmpresa.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 280, -1));

        jl_direccionE.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jl_direccionE.setForeground(new java.awt.Color(55, 55, 55));
        jl_direccionE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_direccionE.setText("Dirección de la empresa");
        pn_perfilEmpresa.add(jl_direccionE, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 280, -1));
        pn_perfilEmpresa.add(jSeparator29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 260, 10));

        jLabel67.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(1, 103, 153));
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel67.setText("Teléfono");
        pn_perfilEmpresa.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 280, -1));

        jl_telE.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jl_telE.setForeground(new java.awt.Color(55, 55, 55));
        jl_telE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_telE.setText("Teléfono de la empresa");
        pn_perfilEmpresa.add(jl_telE, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 280, -1));
        pn_perfilEmpresa.add(jSeparator38, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 260, 10));

        jLabel84.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(1, 103, 153));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("Correo");
        pn_perfilEmpresa.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 280, -1));

        jl_correoE.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jl_correoE.setForeground(new java.awt.Color(55, 55, 55));
        jl_correoE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_correoE.setText("Correo de la empresa");
        pn_perfilEmpresa.add(jl_correoE, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 280, -1));

        Reclutador.add(pn_perfilEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 280, 560));

        pn_PuestosDisponibles.setBackground(new java.awt.Color(239, 238, 236));
        pn_PuestosDisponibles.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setForeground(new java.awt.Color(1, 103, 153));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jl_EDisponibles.setModel(new DefaultListModel());
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

        jl_postulantes.setModel(new DefaultListModel());
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

        bt_regresar.setBackground(new java.awt.Color(1, 103, 153));
        bt_regresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_regresarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_regresarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_regresarMouseExited(evt);
            }
        });

        jLabel144.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel144.setForeground(new java.awt.Color(255, 255, 255));
        jLabel144.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel144.setText("REGRESAR");

        javax.swing.GroupLayout bt_regresarLayout = new javax.swing.GroupLayout(bt_regresar);
        bt_regresar.setLayout(bt_regresarLayout);
        bt_regresarLayout.setHorizontalGroup(
            bt_regresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel144, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );
        bt_regresarLayout.setVerticalGroup(
            bt_regresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bt_regresarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel144)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Reclutador.add(bt_regresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 670, -1, -1));

        getContentPane().add(Reclutador, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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
        Login.add(pf_contra, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 350, 50));

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

        Login.add(bt_iniciarSesión, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 520, 350, 40));

        jLabel24.setBackground(new java.awt.Color(55, 55, 55));
        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(55, 55, 55));
        jLabel24.setText("Contraseña");
        Login.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, -1, -1));

        jLabel25.setBackground(new java.awt.Color(55, 55, 55));
        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(55, 55, 55));
        jLabel25.setText("Usuario");
        Login.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, -1, -1));

        jLabel85.setForeground(new java.awt.Color(55, 55, 55));
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel85.setText("o");
        Login.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 580, 30, 20));
        Login.add(jSeparator42, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 590, 160, 20));
        Login.add(jSeparator43, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 590, 160, 20));

        bt_crearUsuario.setBackground(new java.awt.Color(11, 103, 194));
        bt_crearUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_crearUsuarioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_crearUsuarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_crearUsuarioMouseExited(evt);
            }
        });

        jLabel87.setBackground(new java.awt.Color(37, 71, 106));
        jLabel87.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel87.setText("Crear Usuario");

        javax.swing.GroupLayout bt_crearUsuarioLayout = new javax.swing.GroupLayout(bt_crearUsuario);
        bt_crearUsuario.setLayout(bt_crearUsuarioLayout);
        bt_crearUsuarioLayout.setHorizontalGroup(
            bt_crearUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel87, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );
        bt_crearUsuarioLayout.setVerticalGroup(
            bt_crearUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel87, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        Login.add(bt_crearUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 620, 350, 40));

        getContentPane().add(Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 800));

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
        AbrirJD(jd_crearEmpleo);
    }//GEN-LAST:event_bt_MiPerfilEMouseClicked

    private void bt_MiPerfilEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_MiPerfilEMouseEntered
        bt_MiPerfilE.setBackground(new Color(54, 156, 225));
    }//GEN-LAST:event_bt_MiPerfilEMouseEntered

    private void bt_MiPerfilEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_MiPerfilEMouseExited
        bt_MiPerfilE.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_bt_MiPerfilEMouseExited

    private void bt_PDisponiblesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_PDisponiblesMouseClicked
        pn_postulantes.setVisible(false);
        llenarEmpleosDisponibles();
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

        if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {
            if (jl_EDisponibles.getSelectedIndex() != -1) {
                pn_postulantes.setVisible(true);
                refillPostulantes();
            }

        } else if (evt.getButton() == MouseEvent.BUTTON3) {

            DefaultListModel empleos = (DefaultListModel) jl_EDisponibles.getModel();

            String emp = (String) empleos.getElementAt(jl_EDisponibles.getSelectedIndex());
            String[] array = emp.replace(" ", "").split(",");
            String empid = array[0].split(":")[1];
            ArrayList<String> hired = admin.getContratados(empid);
            String item = "PERSONAS CONTRATADAS: \n";

            this.empleoid = empid;
            JSONArray ar = new JSONArray(hired.toString());
            for (int j = 0; j < ar.length(); j++) {
                JSONObject o = ar.getJSONObject(j);
                String[] hiredd = admin.getPersonal_pf(o.getString("PK"));
                item += o.getString("PK") + " - " + hiredd[0] + "\n";

            }
            JOptionPane.showMessageDialog(this, item);
        }
    }//GEN-LAST:event_jl_EDisponiblesMouseClicked

    private void jl_postulantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl_postulantesMouseClicked
        if (evt.getClickCount() == 2) {
            pn_PuestosDisponibles.setVisible(false);
            pn_DatosDelPostulante.setVisible(true);
            pn_perfilEmpresa.setVisible(false);
            pn_perfilPersona.setVisible(true);
            bt_regresar.setVisible(true);
            if (!pn_postulantes.isVisible()) {
                pn_postulantes.setVisible(true);
            }
        }

        if (evt.isMetaDown()) {
            menuContratar.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jl_postulantesMouseClicked

    private void bt_regresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_regresarMouseClicked
        pn_PuestosDisponibles.setVisible(true);
        pn_DatosDelPostulante.setVisible(false);
        pn_perfilEmpresa.setVisible(true);
        pn_perfilPersona.setVisible(false);
        bt_regresar.setVisible(false);
    }//GEN-LAST:event_bt_regresarMouseClicked

    private void bt_iniciarSesiónMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_iniciarSesiónMouseClicked
        userid = admin.login("usr_" + tf_usuario.getText(), pf_contra.getText());
        user = tf_usuario.getText();
        pass = pf_contra.getText();

        if (userid != null) {
            String[] split = userid.split("_");
            //System.out.println(split[0]);
            if (split[0].equals("user")) {
                Login.setVisible(false);
                Reclutador.setVisible(false);
                Postulante.setVisible(true);
                pn_perfilPersona.setVisible(true);
                llenarDatosPostulante();
                loadPreviousJobs();
            } else {
                Login.setVisible(false);
                Reclutador.setVisible(true);
                Postulante.setVisible(false);
                bt_regresar.setVisible(false);
                llenarDatosEmpresa();
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
        pn_perfilPersona.setVisible(false);
        pn_DatosDelPostulante.setVisible(false);
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

    private void bt_añadirPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_añadirPMouseClicked
        crearEmpleo();
        vaciarEmpleo();
    }//GEN-LAST:event_bt_añadirPMouseClicked

    private void bt_crearUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_crearUsuarioMouseClicked
        AbrirJD(jd_crearUsuario);
    }//GEN-LAST:event_bt_crearUsuarioMouseClicked

    private void bt_crearUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_crearUsuarioMouseEntered
        bt_crearUsuario.setBackground(new Color(1, 66, 130));
    }//GEN-LAST:event_bt_crearUsuarioMouseEntered

    private void bt_crearUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_crearUsuarioMouseExited
        bt_crearUsuario.setBackground(new Color(11, 103, 194));
    }//GEN-LAST:event_bt_crearUsuarioMouseExited

    private void bt_regresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_regresarMouseEntered
        bt_regresar.setBackground(new Color(1, 66, 130));
    }//GEN-LAST:event_bt_regresarMouseEntered

    private void bt_regresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_regresarMouseExited
        bt_regresar.setBackground(new Color(1, 103, 153));
    }//GEN-LAST:event_bt_regresarMouseExited

    private void bt_añadirIdiomaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_añadirIdiomaMouseClicked
        DefaultListModel modelito = new DefaultListModel();
        if (jl_idiomasAñadidos.getModel().getSize() > 0) {
            modelito = (DefaultListModel) jl_idiomasAñadidos.getModel();
        }
        modelito.addElement(cb_idiomas.getSelectedItem().toString());
        jl_idiomasAñadidos.setModel(modelito);
    }//GEN-LAST:event_bt_añadirIdiomaMouseClicked

    private void bt_añadirCertificacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_añadirCertificacionMouseClicked
        DefaultListModel modelito = new DefaultListModel();
        if (jl_certificacionesAñadidas.getModel().getSize() > 0) {
            modelito = (DefaultListModel) jl_certificacionesAñadidas.getModel();
        }
        modelito.addElement(tf_certificaciones.getText());
        jl_certificacionesAñadidas.setModel(modelito);
    }//GEN-LAST:event_bt_añadirCertificacionMouseClicked

    private void bt_crearUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_crearUMouseClicked
        jd_crearUsuario.setVisible(false);
        if (bt_empresa.isSelected()) {
            lb_tituloJDEmpresa.setText("Crear Nueva Empresa");
            lb_btModEmpresa.setText("Crear Perfil");
            pn_fondoModE.setBackground(new Color(11, 103, 194));
            if (crearUsuario()) {
                AbrirJD(jd_Empresa);
            } else {
                JOptionPane.showMessageDialog(this, "Ya existe una cuenta con ese id");
            }

        } else {
            if (crearUsuario()) {
                AbrirJD(jd_Persona);
            } else {
                JOptionPane.showMessageDialog(this, "Ya existe una cuenta con ese id");
            }
        }

        vaciarUsuario();
    }//GEN-LAST:event_bt_crearUMouseClicked

    private void tb_modificarPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_modificarPMouseClicked
        if (evt.getClickCount() == 2) {
            int pos = tb_modificarP.getSelectedRow();
            //aqui les recomiendo tener una arraylist de TODOS los atributos de personas para que lo 
            //listen en la tabla y obtengan la pos para editar el atributo.
        }
    }//GEN-LAST:event_tb_modificarPMouseClicked

    private void bt_RPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_RPMouseClicked
        if (bt_RP.isSelected()) {
            ta_RP.setEditable(true);
        } else {
            ta_RP.setEditable(false);
        }
    }//GEN-LAST:event_bt_RPMouseClicked

    private void bt_modificarPerfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_modificarPerfilMouseClicked
        ModifyFillTable();
        AbrirJD(jd_ModificarPersona);
    }//GEN-LAST:event_bt_modificarPerfilMouseClicked

    private void bt_modificarPerfilMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_modificarPerfilMouseEntered
        bt_modificarPerfil.setBackground(new Color(54, 156, 225));
    }//GEN-LAST:event_bt_modificarPerfilMouseEntered

    private void bt_modificarPerfilMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_modificarPerfilMouseExited
        bt_modificarPerfil.setBackground(new Color(254, 254, 254));
    }//GEN-LAST:event_bt_modificarPerfilMouseExited

    private void bt_modificarPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_modificarPMouseEntered
        bt_modificarP.setBackground(new Color(153, 0, 0));
    }//GEN-LAST:event_bt_modificarPMouseEntered

    private void bt_modificarPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_modificarPMouseExited
        bt_modificarP.setBackground(new Color(195, 22, 28));
    }//GEN-LAST:event_bt_modificarPMouseExited

    private void bt_guardarEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_guardarEMouseEntered
        bt_guardarE.setBackground(new Color(153, 0, 0));
    }//GEN-LAST:event_bt_guardarEMouseEntered

    private void bt_guardarEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_guardarEMouseExited
        bt_guardarE.setBackground(new Color(195, 22, 28));
    }//GEN-LAST:event_bt_guardarEMouseExited

    private void bt_guardarPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_guardarPMouseEntered
        bt_guardarP.setBackground(new Color(153, 0, 0));
    }//GEN-LAST:event_bt_guardarPMouseEntered

    private void bt_guardarPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_guardarPMouseExited
        bt_guardarP.setBackground(new Color(195, 22, 28));
    }//GEN-LAST:event_bt_guardarPMouseExited

    private void bt_crearUMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_crearUMouseEntered
        bt_crearU.setBackground(new Color(153, 0, 0));
    }//GEN-LAST:event_bt_crearUMouseEntered

    private void bt_crearUMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_crearUMouseExited
        bt_crearU.setBackground(new Color(195, 22, 28));
    }//GEN-LAST:event_bt_crearUMouseExited

    private void bt_añadirPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_añadirPMouseEntered
        bt_añadirP.setBackground(new Color(153, 0, 0));
    }//GEN-LAST:event_bt_añadirPMouseEntered

    private void bt_añadirPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_añadirPMouseExited
        bt_añadirP.setBackground(new Color(195, 22, 28));
    }//GEN-LAST:event_bt_añadirPMouseExited

    private void bt_añadirCertificacionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_añadirCertificacionMouseEntered
        bt_añadirCertificacion.setBackground(new Color(153, 0, 0));
    }//GEN-LAST:event_bt_añadirCertificacionMouseEntered

    private void bt_añadirCertificacionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_añadirCertificacionMouseExited
        bt_añadirCertificacion.setBackground(new Color(195, 22, 28));
    }//GEN-LAST:event_bt_añadirCertificacionMouseExited

    private void bt_añadirIdiomaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_añadirIdiomaMouseEntered
        bt_añadirIdioma.setBackground(new Color(153, 0, 0));
    }//GEN-LAST:event_bt_añadirIdiomaMouseEntered

    private void bt_añadirIdiomaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_añadirIdiomaMouseExited
        bt_añadirIdioma.setBackground(new Color(195, 22, 28));
    }//GEN-LAST:event_bt_añadirIdiomaMouseExited

    private void bt_eliminarPerfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_eliminarPerfilMouseClicked
        int r = JOptionPane.showConfirmDialog(Postulante, "Desea eliminar su cuenta?", "Eliminar Cuenta", YES_NO_OPTION);
        if (r == 0) {
            //codigo para borrarla
            admin.deleteUser("usr_" + user, pass, userid);
            admin.deletePostulante(userid);
            LimpiarSesion();
            JOptionPane.showMessageDialog(this, "¡Cuenta Eliminada con Éxito!");
            Postulante.setVisible(false);
            pn_DatosDelPostulante.setVisible(false);
            pn_perfilPersona.setVisible(false);

        }
    }//GEN-LAST:event_bt_eliminarPerfilMouseClicked

    private void bt_eliminarPerfilMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_eliminarPerfilMouseEntered
        bt_eliminarPerfil.setBackground(new Color(54, 156, 225));
    }//GEN-LAST:event_bt_eliminarPerfilMouseEntered

    private void bt_eliminarPerfilMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_eliminarPerfilMouseExited
        bt_eliminarPerfil.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_bt_eliminarPerfilMouseExited

    private void bt_modificarPerfilEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_modificarPerfilEMouseClicked
        lb_tituloJDEmpresa.setText("Modificar Perfil");
        lb_btModEmpresa.setText("Modificar Perfil");
        pn_fondoModE.setBackground(new Color(1, 103, 153));
        setearModifEmpresa();
        AbrirJD(jd_Empresa);
    }//GEN-LAST:event_bt_modificarPerfilEMouseClicked

    private void bt_modificarPerfilEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_modificarPerfilEMouseEntered
        bt_modificarPerfilE.setBackground(new Color(54, 156, 225));
    }//GEN-LAST:event_bt_modificarPerfilEMouseEntered

    private void bt_modificarPerfilEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_modificarPerfilEMouseExited
        bt_modificarPerfilE.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_bt_modificarPerfilEMouseExited

    private void bt_eliminarPerfilEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_eliminarPerfilEMouseClicked
        int r = JOptionPane.showConfirmDialog(Reclutador, "Desea eliminar su cuenta?", "Eliminar Cuenta", YES_NO_OPTION);
        if (r == 0) {
            admin.deleteUser("emp_" + user, pass, userid);
            admin.delete(userid);
            JOptionPane.showMessageDialog(this, "¡Cuenta Eliminada con Éxito!");
            LimpiarSesion();
            Reclutador.setVisible(false);
        }
    }//GEN-LAST:event_bt_eliminarPerfilEMouseClicked

    private void bt_eliminarPerfilEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_eliminarPerfilEMouseEntered
        bt_eliminarPerfilE.setBackground(new Color(54, 156, 225));
    }//GEN-LAST:event_bt_eliminarPerfilEMouseEntered

    private void bt_eliminarPerfilEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_eliminarPerfilEMouseExited
        bt_eliminarPerfilE.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_bt_eliminarPerfilEMouseExited

    private void bt_guardarEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_guardarEMouseClicked
        if (lb_btModEmpresa.getText().equals("Crear Perfil")) {
            //meten codigo para crear el nuevo perfil
            crearEmpresa();
        } else {
            // el botón modifica el perfil de la empresa
            modificarEmpresa();
        }
        vaciarEmpresa();
    }//GEN-LAST:event_bt_guardarEMouseClicked

    private void jt_EmpleosDisponiblesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_EmpleosDisponiblesMouseClicked
        if (evt.isMetaDown()) {
            menuPostular.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jt_EmpleosDisponiblesMouseClicked

    private void PostularMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PostularMouseClicked
        
    }//GEN-LAST:event_PostularMouseClicked

    private void bt_crearPuestoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_crearPuestoMouseClicked
        crearPuesto();
        vaciarPuesto();
    }//GEN-LAST:event_bt_crearPuestoMouseClicked

    private void bt_crearPuestoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_crearPuestoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_crearPuestoMouseEntered

    private void bt_crearPuestoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_crearPuestoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_crearPuestoMouseExited

    private void bt_añadirPuestoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_añadirPuestoMouseClicked
        DefaultListModel modelito = new DefaultListModel();
        if (jl_puestosAñadidos.getModel().getSize() > 0) {
            modelito = (DefaultListModel) jl_puestosAñadidos.getModel();
        }
        modelito.addElement(cb_puestos.getSelectedItem().toString());
        jl_puestosAñadidos.setModel(modelito);
    }//GEN-LAST:event_bt_añadirPuestoMouseClicked

    private void bt_añadirPuestoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_añadirPuestoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_añadirPuestoMouseEntered

    private void bt_añadirPuestoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_añadirPuestoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_añadirPuestoMouseExited

    private void bt_guardarPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_guardarPMouseClicked
        crearPersona();
        vaciarPersona();
    }//GEN-LAST:event_bt_guardarPMouseClicked

    private void lb_busquedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_busquedaMouseClicked
        fillFilter();
    }//GEN-LAST:event_lb_busquedaMouseClicked

    private void ContratarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContratarActionPerformed
        DefaultListModel modelo = (DefaultListModel) jl_postulantes.getModel();
        String item = (String) modelo.getElementAt(jl_postulantes.getSelectedIndex());
        String userID = item.replace(" ", "").split(":")[1];
        int i = 0;
        while (admin.getPreviousJob(userID, "pJob_" + (i + 1)) != null) {
            i++;
        }

        //String year = Integer.toString(date.getYear());
        Object[] values = {userID, empleoid, Year.now().getValue()};
        if (admin.updateJob(userID, i, values) != -1) {
            JOptionPane.showMessageDialog(this, "Empleado Contratado");
            modelo.clear();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario no se puede contratar");
        }


    }//GEN-LAST:event_ContratarActionPerformed

    private void PostularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PostularActionPerformed
        DefaultTableModel model = (DefaultTableModel) jt_EmpleosDisponibles.getModel();
        if (jt_EmpleosDisponibles.getSelectedColumn() != -1) {
            if (validarSolicitudRequerimientos()) {
                if (!validarSolicitudSalario()) {
                    String[] job = admin.getCurrentJob(userid);
                    if (job[0].equals((String) model.getValueAt(jt_EmpleosDisponibles.getSelectedRow(), 11))) {
                        JOptionPane.showMessageDialog(null, "Usted ya tiene este empleo");
                    } else {
                        int x = JOptionPane.showConfirmDialog(this, "El trabajo no cumple con el salario deseado.\nDesea aplicar igual?");
                        if (x == JOptionPane.YES_OPTION) {

                            admin.solicitarEmpleo(userid, (String) model.getValueAt(jt_EmpleosDisponibles.getSelectedRow(), 11));
                            LlenarTabla(admin.getEmpleos().toString(), jt_EmpleosPostulados, 1);
                        }
                    }

                } else {

                    admin.solicitarEmpleo(userid, (String) model.getValueAt(jt_EmpleosDisponibles.getSelectedRow(), 11));
                    LlenarTabla(admin.getEmpleos().toString(), jt_EmpleosPostulados, 1);
                }

            } else {
                JOptionPane.showMessageDialog(null, "No cumple con los requisitos para este empleo.");
            }
        }
    }//GEN-LAST:event_PostularActionPerformed

    private void bt_modificarPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_modificarPMouseClicked
        DefaultTableModel model = (DefaultTableModel) tb_modificarP.getModel();
        
        
        int rows = model.getRowCount();
        for (int i = 0; i < rows; i++) {
            String data = model.getValueAt(i, 1).toString();
            datosPersona.add(data);
        }
        System.out.println("*******************");
        for (int i = 0; i < datosPersona.size(); i++) {
            System.out.println(i+": "+datosPersona.get(i));
        }
        System.out.println("*******************");
        String [] nomCompleto = jl_name.getText().split(" ");
        //String[] keys = {"PK", "Apellido", "Correo", "Edad", "Nacionalidad", "Nombre", "Sexo", "Telefono"};
        
        String[]dp = {userid,nomCompleto[1],datosPersona.get(1),datosPersona.get(0),jl_nation.getText(),
            nomCompleto[0],jl_gender.getText(),datosPersona.get(2)};
        admin.createPersonal_pf(dp, 1);
        //String[] keys = {"PK", "Correos_de_Dependientes", "Direccion", "Estado_Civil", "Hijos"};
        Object[]df = {userid,StringtoArray(datosPersona.get(4)),datosPersona.get(6),datosPersona.get(3),
            datosPersona.get(5)};
        admin.createFamiliar_pf(df, 1);
        
        //{"PK", "Alergias", "Historial_Medico", "Info_Meds", "Resultado_pruebas"};
        Object[]ds = {userid,StringtoArray(datosPersona.get(8)),datosPersona.get(9),datosPersona.get(7),
            datosPersona.get(10)};
        admin.createSanitary_pf(ds, 1);
        
        //{"PK", "Antecedentes", "Servicio Militar", "SSN"};
        Object[]dl={userid,StringtoArray(datosPersona.get(11)),StringtoBool(datosPersona.get(12)),datosPersona.get(13)};
        admin.createLegal_pf(dl, 1);
        
        //{"PK", "Estudios_Realizados", "Institución egresada", "Nivel_Estudio", "Titulos"};
        Object[]da = {userid,StringtoArray(datosPersona.get(16)),datosPersona.get(14),
            datosPersona.get(15),StringtoArray(datosPersona.get(17))};
        admin.createAcademic_pf(da, 1);
        
        //{"PK", "Años", "Certificaciones", "Conocimientos_Especificos", "Idiomas", "Logros_Profesionales"}
        Object[]dlp = {userid,datosPersona.get(18),StringtoArray(datosPersona.get(21)),StringtoArray(datosPersona.get(22)),
            StringtoArray(datosPersona.get(20)),StringtoArray(datosPersona.get(19))};
        admin.createProfessional_pf(dlp, 1);

        admin.añadirFamiliar(userid, datosPersona.get(23));
        
        //{"PK", "Puestos_Aceptables", "Puestos_Inaceptables", "Salario", "Tipo_Contrato", "Tipo_trabajo"}
        //tipo de trabajo, inaceptable, aceptable, salario, user,Tipo de COntrato
        Object[]st = {userid,
            datosPersona.get(26),
            StringtoArray(datosPersona.get(27)),
            StringtoArray(datosPersona.get(28)),
            
            datosPersona.get(24),
            datosPersona.get(25)};
        admin.createSolicitud(st, 1);

        llenarDatosPostulante();
        JOptionPane.showMessageDialog(null, "Datos modificados correctamente");
    }//GEN-LAST:event_bt_modificarPMouseClicked

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

    private void LimpiarSesion() {
        tf_usuario.setText("");
        pf_contra.setText("");
        Login.setVisible(true);
    }

    public static void AbrirJD(JDialog JD) {
        JD.setModal(true);
        JD.pack();
        JD.setLocationRelativeTo(null);
        JD.setResizable(false);
        JD.setVisible(true);
    }

    public void llenarJList(JList list, String cadena) {
        String[] mails = cadena.split(",");
        DefaultListModel modelo
                = (DefaultListModel) list.getModel();
        modelo.removeAllElements();
        for (String mail : mails) {
            modelo.addElement((String) mail);
        }
        list.setModel(modelo);
    }

    public String getEmpDisp() {
        String s = admin.getEmpleos().toString();
        System.out.println("->" + s);
        JSONArray ar = new JSONArray(s);
        for (int j = 0; j < ar.length(); j++) {
            JSONObject o = ar.getJSONObject(j);
            System.out.println("Nombre: " + o.getString("Nombre"));
            System.out.println("ID: " + o.getString("PK"));
            System.out.println("Idiomas: " + o.getJSONArray("Idiomas"));
            //System.out.println("$:"+o);
        }
        return "";
    }

    public String ArraytoString(JSONArray ar) {
        String s = "";
        for (int i = 0; i < ar.length(); i++) {
            s += ar.getString(i);
            if (i < ar.length() - 1) {
                s += ",";
            }
        }
        return s;
    }
    
    public ArrayList<String> StringtoArray(String s) {
        String[] ar = s.split(",");
        ArrayList<String> AL = new ArrayList();
        for (int i = 0; i < ar.length; i++) {
            AL.add(ar[i]);
        }
        return AL;
    }
    
    public boolean StringtoBool(String s){
        return ("si".equals(s)||"Si".equals(s));
    }

    public void llenarEmpleosDisponibles() {
        try {
            DefaultListModel modelito = new DefaultListModel();
            String[] businessData = admin.getEmpresa(userid);

            String[] empleosEmpresa = admin.getEmpleo(businessData[0], businessData[1]);

            for (int i = 0; i < empleosEmpresa.length; i++) {
                modelito.addElement(empleosEmpresa[i]);
            }

            jl_EDisponibles.setModel(modelito);
        } catch (Exception e) {
        }
    }

    public void llenarDatosEmpresa() {
        String[] businessData = admin.getEmpresa(userid);
        jl_name1.setText(businessData[0]);
        jl_correoE.setText(businessData[1]);
        jl_CIF.setText(businessData[2]);
        jl_telE.setText(businessData[3]);
        jl_Director.setText(businessData[4]);
        jl_direccionE.setText(businessData[6]);
        /*for (int i = 0; i < businessData.length; i++) {
            System.out.print("i:"
                    + i);
            System.out.println("[" + businessData[i] + "]");
        }*/
        llenarEmpleos();
    }

    public void llenarEmpleos() {
        ArrayList<String> empleos = admin.getEmpleos();
        DefaultListModel modelo = (DefaultListModel) jl_EDisponibles.getModel();
        JSONArray ar = new JSONArray(empleos.toString());
        for (int j = 0; j < ar.length(); j++) {
            JSONObject o = ar.getJSONObject(j);
            String item = "";
            item += "id: " + o.getString("SK") + ", ";
            item += "Nombre: " + o.getString("Nombre") + "\n";
            modelo.add(j, item);
        }
    }

    public void refillPostulantes() {
        DefaultListModel list = (DefaultListModel) jl_postulantes.getModel();
        DefaultListModel empleos = (DefaultListModel) jl_EDisponibles.getModel();
        list.clear();

        String emp = (String) empleos.getElementAt(jl_EDisponibles.getSelectedIndex());
        String[] array = emp.replace(" ", "").split(",");
        String empid = array[0].split(":")[1];
        ArrayList<String> solicitudes = admin.getSolicitudes(empid);
        this.empleoid = empid;
        JSONArray ar = new JSONArray(solicitudes.toString());
        for (int j = 0; j < ar.length(); j++) {
            JSONObject o = ar.getJSONObject(j);
            String item = "";
            item += "PK: " + o.getString("PK");
            list.add(j, item);
        }

    }

    public boolean crearUsuario() {
        try {
            String rol = "";
            String username = tf_Cusuario.getText();
            String contra = tf_contraseña.getText();
            String cuenta = ff_noCuenta.getText().replace(",", "");

            System.out.println("cuenta: " + cuenta);
            if (bt_empresa.isSelected()) {
                rol = "empresa";
            } else if (bt_persona.isSelected()) {
                rol = "usuario";
            }
            return admin.createUser(username, contra, "user_" + cuenta, rol);
        } catch (Exception e) {
            return false;
        }
    }

    public void crearEmpresa() {
        try {
            String[] datos = new String[7];

            datos[0] = "emp_" + ff_noCuenta.getText().replace(",", "");
            datos[1] = tf_CIF.getText(); //Cif
            datos[2] = tf_correoE.getText(); //Correo
            datos[3] = tf_direccionD.getText(); //Dirección
            datos[4] = tf_director.getText(); //Director
            datos[5] = tf_NombreE.getText(); //Nombre
            datos[6] = ff_telefonoP1.getText(); //Teléfono

            admin.createEmpresa(datos, 0);
        } catch (Exception e) {
        }
    }

    public void modificarEmpresa() {
        try {
            String datos[] = new String[7];

            String[] data = admin.getEmpresa(userid);

            datos[0] = data[0];
            datos[1] = tf_CIF.getText(); //Cif
            datos[2] = tf_correoE.getText(); //Correo
            datos[3] = tf_direccionD.getText(); //Dirección
            datos[4] = tf_director.getText(); //Director
            datos[5] = tf_NombreE.getText(); //Nombre
            datos[6] = ff_telefonoP1.getText(); //Teléfono

            admin.createEmpresa(datos, 1);
        } catch (Exception e) {
        }
    }

    public void crearEmpleo() {
        try {

            String[] datos = new String[11];

            String antecedentes = "";
            if (rb_si.isSelected()) {
                antecedentes = "true";
            } else if (rb_no.isSelected()) {
                antecedentes = "false";
            }

            datos[2] = antecedentes; //Antecedentes
            datos[3] = ff_añosE.getText(); //AñosExperiencia
            datos[4] = convertirAString(datosTablaComboBoxs(jl_certificacionesAñadidas)); //Certificaciones
            datos[5] = convertirAString(datosTablaComboBoxs(jl_idiomasAñadidos)); //Idiomas
            datos[5] = cb_nivelEducativo.getSelectedItem().toString(); //Nivel Educativo
            datos[6] = tf_empleo.getText(); //Nombre Empleo
            datos[7] = convertirAString(datosTablaComboBoxs(jl_puestosAñadidos)); //Puestos
            datos[8] = ta_RP.getText(); //Requisitos
            datos[9] = tf_tipoEmpleo.getText();//Tipo

            //String modalidad = cb_modalidad.getSelectedItem().toString();
            admin.createEmpleo(datos, 0);

        } catch (Exception e) {
        }
    }

    public String[] datosTablaComboBoxs(JList lista) {
        String[] datos = new String[lista.getModel().getSize()];
        try {
            for (int i = 0; i < lista.getModel().getSize(); i++) {
                datos[i] = (String) lista.getModel().getElementAt(i);
            }

        } catch (Exception e) {
        }
        return datos;
    }

    

    public String convertirAString(String[] arr) {
        String cadena = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr.length == i + 1) {
                cadena += arr[i];
            } else {
                cadena += arr[i] + ",";
            }
        }
        return cadena;
    }

    public void vaciarEmpresa() {
        tf_NombreE.setText("");
        tf_CIF.setText("");
        tf_director.setText("");
        tf_correoE.setText("");
        ff_telefonoP1.setText("");
        tf_direccionD.setText("");
    }

    public void vaciarEmpleo() {
        rb_si.setSelected(false);
        rb_no.setSelected(false);

        jl_certificacionesAñadidas.setModel(new DefaultListModel());
        jl_idiomasAñadidos.setModel(new DefaultListModel());
        jl_puestosAñadidos.setModel(new DefaultListModel());

        cb_nivelEducativo.setSelectedIndex(0);
        cb_modalidad.setSelectedIndex(0);

        tf_empleo.setText("");
        ff_añosE.setText("");
        ta_RP.setText("");
        tf_tipoEmpleo.setText("");
    }

    public void vaciarPersona() {
        tf_NombreP.setText("");
        tf_apellido.setText("");
        ff_Edad.setText("");
        tf_correoP.setText("");
        ff_telefonoP.setText("");
        tf_nacionalidad.setText("");
        bt_M.setSelected(false);
        bt_F.setSelected(false);
    }

    public void vaciarUsuario() {
        tf_Cusuario.setText("");
        tf_contraseña.setText("");
        ff_noCuenta.setText("");
        bt_empresa.setSelected(false);
        bt_persona.setSelected(false);
    }

    public void setearModifEmpresa() {
        String[] datos = admin.getEmpresa(userid);

        tf_CIF.setText(datos[1]); //Cif
        tf_correoE.setText(datos[2]); //Correo
        tf_direccionD.setText(datos[3]); //Dirección
        tf_director.setText(datos[4]); //Director
        tf_NombreE.setText(datos[5]); //Nombre
        ff_telefonoP1.setText(datos[6]); //Teléfono
    }

    public boolean crearPersona() {

        try {
            String[] values = new String[8];

            values[0] = "user_" + ff_noCuenta.getText().replace(",", "");
            values[1] = tf_apellido.getText();
            values[2] = tf_correoP.getText();
            values[3] = ff_Edad.getText();
            values[4] = tf_nacionalidad.getText();
            values[5] = tf_NombreP.getText();
            String genero = "";
            if (bt_M.isSelected()) {
                genero = "M";
            } else if (bt_F.isSelected()) {
                genero = "F";
            }
            values[6] = genero;
            values[7] = ff_telefonoP.getText();

            return admin.createPersonal_pf(values, 0);
        } catch (Exception e) {
            return false;
        }
    }

    

    

    

    public void crearPuesto() {
        try {
            String nombrePuesto = tf_puesto.getText();
            String tipoPuesto = tf_tipoPuesto.getText();
            String sueldo = tf_sueldo.getText();
            String puestosNumber = numeroRandom();


            String[] datos = new String[5];
            datos[0] = puestosNumber;
            datos[1] = tf_puesto.getText();
            datos[2] = tf_tipoPuesto.getText();
            datos[3] = tf_sueldo.getText();

            cb_puestos.addItem(convertirAString(datos));
            admin.createPuesto(Integer.parseInt(puestosNumber), nombrePuesto, tipoPuesto, Double.parseDouble(sueldo));
        } catch (Exception e) {
        }
    }

    public void vaciarPuesto() {
        tf_puesto.setText("");
        tf_tipoPuesto.setText("");
        tf_sueldo.setText("");
    }

    

    public String numeroRandom() {
        String strNumero = "";
        Random rand = new Random();
        for (int i = 0; i < 8; i++) {
            strNumero += rand.nextInt(9);
        }
        
        return strNumero;
    }

    
     

    

    public void LlenarTabla(String datos, JTable table, int flag) {
        boolean addrow = true;
        JSONArray ar = new JSONArray(datos);
        System.out.println("Datos: " + datos);
        try {
            table.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{
                "ID Puestos", "Empresa ID", "Puesto", "Requisitos Personales", "Antecedentes", "Nivel Educativo", "Tipo",
                "Experiencia(años)", "Idiomas", "Certificados", "Modalidad", "Empleo ID"}));
            for (int j = 0; j < ar.length(); j++) {
                JSONObject o = ar.getJSONObject(j);
                if (flag == 1) {//1 -> empleos postulados - vista postulantes
                    ArrayList<String> empPost = getEmpresasPostuladas(admin.getSolicitudesDeUsuario(userid).toString());
                    for (int i = 0; i < empPost.size(); i++) {
                        if (o.getString("SK").equals(empPost.get(i))) {
                            addrow = true;
                            break;
                        } else {
                            addrow = false;
                        }
                    }
                } else {
                    addrow = true;
                }
                if (addrow) {
                    Object[] row = {ArraytoString(o.getJSONArray("Puestos")), o.getString("PK"),
                        o.getString("Nombre"), ArraytoString(o.getJSONArray("Requisitos_Personales")),
                        o.getBoolean("Antecedentes"), o.getString("Nivel Educativo"), o.getString("Tipo"),
                        o.getInt("AñosExperiencia"), ArraytoString(o.getJSONArray("Idiomas")),
                        ArraytoString(o.getJSONArray("Certificaciones")), o.getString("Modalidad"), o.getString("SK")};
                    DefaultTableModel modelo = (DefaultTableModel) table.getModel();
                    modelo.addRow(row);
                    table.setModel(modelo);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String empPost(String emp) {
        ArrayList<String> empPost = getEmpresasPostuladas(admin.getSolicitudesDeUsuario(userid).toString());
        String s = admin.getEmpleos().toString();
        JSONArray ar = new JSONArray(s);
        for (int j = 0; j < ar.length(); j++) {
            JSONObject o = ar.getJSONObject(j);

            System.out.println("SK: " + o.getString("SK"));
        }
        for (int i = 0; i < empPost.size(); i++) {

        }
        return null;
    }

    public ArrayList<String> getEmpresasPostuladas(String emp) {
        JSONArray ar = new JSONArray(emp);
        ArrayList<String> empresas = new ArrayList();
        for (int j = 0; j < ar.length(); j++) {
            JSONObject o = ar.getJSONObject(j);
            empresas.add(o.getString("SK"));
        }
        return empresas;
    }

    public String CuentasFamiliares(String data) {
        System.out.println(data);
        JSONArray ar = new JSONArray(data);
        String user = "";
        for (int j = 0; j < ar.length(); j++) {
            JSONObject o = ar.getJSONObject(j);
            if (o.getString("SK").equals(userid)) {
                user = o.getString("PK");
            } else if (o.getString("PK").equals(userid)) {
                user = o.getString("SK");
            } else {
                System.out.println("Ojito: No hay coincidencias con el user");
            }
        }
        if (!"".equals(user)) {
            System.out.println("Datos: "+Arrays.toString(admin.getPersonal_pf(user)));
            String [] dp = admin.getPersonal_pf(user);
            String s = "Nombre: "+dp[0]+" "+dp[6]+"     "
                    +"Correo: "+dp[1]+"     "
                    +"ID: "+dp[7]+"     ";
            return s; 
        }else{
            return "";
        }

    }

    public void LlenarAtributos() {
        atributosPersona.add("Edad");
        atributosPersona.add("Correo");
        atributosPersona.add("Teléfono");
        atributosPersona.add("Estado Civil");
        atributosPersona.add("Correo Dependientes");
        atributosPersona.add("Hijos");
        atributosPersona.add("Dirección");
        atributosPersona.add("Información de Medicamentos");
        atributosPersona.add("Alergias");
        atributosPersona.add("Historial Médico");
        atributosPersona.add("Resultado de Pruebas");
        atributosPersona.add("Antecedentes Penales");
        atributosPersona.add("Servicio Militar");
        atributosPersona.add("SSN");
        atributosPersona.add("Institución");
        atributosPersona.add("Nivel Universitario");
        atributosPersona.add("Estudios");
        atributosPersona.add("Títulos");
        atributosPersona.add("Años de Experiencia");
        atributosPersona.add("Logros Profesionales");
        atributosPersona.add("Idiomas");
        atributosPersona.add("Certificaciones");
        atributosPersona.add("Conocimientos Específicos");
        atributosPersona.add("Cuentas Familiares");
        atributosPersona.add("Tipo de Contrato");
        atributosPersona.add("Tipo de Trabajo");
        atributosPersona.add("Puestos Aceptables");
        atributosPersona.add("Puestos Inaceptables");
        atributosPersona.add("Salario Expectante");
    }

    public void ModifyFillTable() {
        try {
            tb_modificarP.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{
                "Atributos", "Datos"}));
            for (int i = 0; i < atributosPersona.size(); i++) {
                Object[] row = {atributosPersona.get(i), ""};
                DefaultTableModel modelo = (DefaultTableModel) tb_modificarP.getModel();
                modelo.addRow(row);
                tb_modificarP.setModel(modelo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Contratar;
    private javax.swing.JPanel Login;
    private javax.swing.JPanel MenuBar;
    private javax.swing.JPanel MenuBar1;
    private javax.swing.JPanel Postulante;
    private javax.swing.JMenuItem Postular;
    private javax.swing.JPanel Reclutador;
    private javax.swing.ButtonGroup bg_antecedentes;
    private javax.swing.ButtonGroup bg_genero;
    private javax.swing.ButtonGroup bg_usuario;
    private javax.swing.JPanel bt_EDisponibles;
    private javax.swing.JPanel bt_EPostulados;
    private javax.swing.JRadioButton bt_F;
    private javax.swing.JRadioButton bt_M;
    private javax.swing.JPanel bt_MiPerfilE;
    private javax.swing.JPanel bt_PDisponibles;
    private javax.swing.JCheckBox bt_RP;
    private javax.swing.JPanel bt_añadirCertificacion;
    private javax.swing.JPanel bt_añadirIdioma;
    private javax.swing.JPanel bt_añadirP;
    private javax.swing.JPanel bt_añadirPuesto;
    private javax.swing.JPanel bt_cerrarSesiónP;
    private javax.swing.JPanel bt_cerrarSesiónR;
    private javax.swing.JPanel bt_crearPuesto;
    private javax.swing.JPanel bt_crearU;
    private javax.swing.JPanel bt_crearUsuario;
    private javax.swing.JPanel bt_eliminarPerfil;
    private javax.swing.JPanel bt_eliminarPerfilE;
    private javax.swing.JRadioButton bt_empresa;
    private javax.swing.JPanel bt_guardarE;
    private javax.swing.JPanel bt_guardarP;
    private javax.swing.JPanel bt_iniciarSesión;
    private javax.swing.JPanel bt_miPerfil;
    private javax.swing.JPanel bt_modificarP;
    private javax.swing.JPanel bt_modificarPerfil;
    private javax.swing.JPanel bt_modificarPerfilE;
    private javax.swing.JRadioButton bt_persona;
    private javax.swing.JPanel bt_regresar;
    private javax.swing.JComboBox<String> cb_idiomas;
    private javax.swing.JComboBox<String> cb_modalidad;
    private javax.swing.JComboBox<String> cb_nivelEducativo;
    private javax.swing.JComboBox<String> cb_puestos;
    private javax.swing.JFormattedTextField ff_Edad;
    private javax.swing.JFormattedTextField ff_añosE;
    private javax.swing.JFormattedTextField ff_clasesA1;
    private javax.swing.JFormattedTextField ff_noCuenta;
    private javax.swing.JFormattedTextField ff_telefonoP;
    private javax.swing.JFormattedTextField ff_telefonoP1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jSP_correoDP;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator27;
    private javax.swing.JSeparator jSeparator28;
    private javax.swing.JSeparator jSeparator29;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator30;
    private javax.swing.JSeparator jSeparator31;
    private javax.swing.JSeparator jSeparator32;
    private javax.swing.JSeparator jSeparator33;
    private javax.swing.JSeparator jSeparator34;
    private javax.swing.JSeparator jSeparator35;
    private javax.swing.JSeparator jSeparator36;
    private javax.swing.JSeparator jSeparator37;
    private javax.swing.JSeparator jSeparator38;
    private javax.swing.JSeparator jSeparator39;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator40;
    private javax.swing.JSeparator jSeparator41;
    private javax.swing.JSeparator jSeparator42;
    private javax.swing.JSeparator jSeparator43;
    private javax.swing.JSeparator jSeparator44;
    private javax.swing.JSeparator jSeparator45;
    private javax.swing.JSeparator jSeparator46;
    private javax.swing.JSeparator jSeparator47;
    private javax.swing.JSeparator jSeparator48;
    private javax.swing.JSeparator jSeparator49;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator50;
    private javax.swing.JSeparator jSeparator51;
    private javax.swing.JSeparator jSeparator52;
    private javax.swing.JSeparator jSeparator53;
    private javax.swing.JSeparator jSeparator54;
    private javax.swing.JSeparator jSeparator55;
    private javax.swing.JSeparator jSeparator56;
    private javax.swing.JSeparator jSeparator57;
    private javax.swing.JSeparator jSeparator58;
    private javax.swing.JSeparator jSeparator59;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator60;
    private javax.swing.JSeparator jSeparator61;
    private javax.swing.JSeparator jSeparator62;
    private javax.swing.JSeparator jSeparator63;
    private javax.swing.JSeparator jSeparator64;
    private javax.swing.JSeparator jSeparator65;
    private javax.swing.JSeparator jSeparator66;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JDialog jd_Empresa;
    private javax.swing.JDialog jd_ModificarPersona;
    private javax.swing.JDialog jd_Persona;
    private javax.swing.JDialog jd_crearEmpleo;
    private javax.swing.JDialog jd_crearUsuario;
    private javax.swing.JLabel jl_CIF;
    private javax.swing.JLabel jl_Director;
    private javax.swing.JList<String> jl_EDisponibles;
    private javax.swing.JList<String> jl_HM;
    private javax.swing.JList<String> jl_aPenales;
    private javax.swing.JLabel jl_age;
    private javax.swing.JList<String> jl_alergias;
    private javax.swing.JList<String> jl_alergias1;
    private javax.swing.JList<String> jl_alergias2;
    private javax.swing.JList<String> jl_certificacionesAñadidas;
    private javax.swing.JList<String> jl_correoDP;
    private javax.swing.JLabel jl_correoE;
    private javax.swing.JLabel jl_direccionE;
    private javax.swing.JLabel jl_email;
    private javax.swing.JLabel jl_gender;
    private javax.swing.JList<String> jl_idiomasAñadidos;
    private javax.swing.JLabel jl_name;
    private javax.swing.JLabel jl_name1;
    private javax.swing.JLabel jl_nation;
    private javax.swing.JLabel jl_phone;
    private javax.swing.JList<String> jl_postulantes;
    private javax.swing.JList<String> jl_puestosAñadidos;
    private javax.swing.JLabel jl_telE;
    private javax.swing.JTable jt_EmpleosDisponibles;
    private javax.swing.JTable jt_EmpleosPostulados;
    private javax.swing.JLabel lb_AñosE;
    private javax.swing.JLabel lb_Certificaciones;
    private javax.swing.JLabel lb_ConEsp;
    private javax.swing.JLabel lb_EcivilP;
    private javax.swing.JLabel lb_EcivilP1;
    private javax.swing.JLabel lb_EcivilP2;
    private javax.swing.JLabel lb_Estudios;
    private javax.swing.JLabel lb_Idiomas;
    private javax.swing.JLabel lb_LogrosP;
    private javax.swing.JLabel lb_NU;
    private javax.swing.JLabel lb_SM;
    private javax.swing.JLabel lb_SSN;
    private javax.swing.JLabel lb_SalarioExpectante;
    private javax.swing.JLabel lb_TC;
    private javax.swing.JLabel lb_TT;
    private javax.swing.JLabel lb_TrabActual;
    private javax.swing.JLabel lb_btModEmpresa;
    private javax.swing.JLabel lb_busqueda;
    private javax.swing.JLabel lb_dirrecionP;
    private javax.swing.JLabel lb_hijosP;
    private javax.swing.JLabel lb_infoMed;
    private javax.swing.JLabel lb_institucion;
    private javax.swing.JLabel lb_resultadoP;
    private javax.swing.JLabel lb_tituloJDEmpresa;
    private javax.swing.JLabel lb_titulos;
    private javax.swing.JPopupMenu menuContratar;
    private javax.swing.JPopupMenu menuPostular;
    private javax.swing.JPasswordField pf_contra;
    private javax.swing.JPanel pn_DatosDelPostulante;
    private javax.swing.JPanel pn_EmpleosDisponibles;
    private javax.swing.JPanel pn_EmpleosPostulados;
    private javax.swing.JPanel pn_PuestosDisponibles;
    private javax.swing.JPanel pn_dAcademicosP;
    private javax.swing.JPanel pn_dFamiliaresP;
    private javax.swing.JPanel pn_dProfesionalesP;
    private javax.swing.JPanel pn_dSanitariosP;
    private javax.swing.JPanel pn_fondoModE;
    private javax.swing.JPanel pn_perfilEmpresa;
    private javax.swing.JPanel pn_perfilPersona;
    private javax.swing.JPanel pn_postulantes;
    private javax.swing.JRadioButton rb_no;
    private javax.swing.JRadioButton rb_si;
    private javax.swing.JTextArea ta_RP;
    private javax.swing.JTabbedPane tab1;
    private javax.swing.JTable tb_modificarP;
    private javax.swing.JTextField tf_CIF;
    private javax.swing.JTextField tf_Cusuario;
    private javax.swing.JTextField tf_NombreE;
    private javax.swing.JTextField tf_NombreP;
    private javax.swing.JTextField tf_apellido;
    private javax.swing.JTextField tf_buscar;
    private javax.swing.JTextField tf_certificaciones;
    private javax.swing.JTextField tf_contraseña;
    private javax.swing.JTextField tf_contraseña1;
    private javax.swing.JTextField tf_correoE;
    private javax.swing.JTextField tf_correoP;
    private javax.swing.JTextField tf_direccionD;
    private javax.swing.JTextField tf_director;
    private javax.swing.JTextField tf_empleo;
    private javax.swing.JTextField tf_idEmpleo;
    private javax.swing.JTextField tf_nacionalidad;
    private javax.swing.JTextField tf_puesto;
    private javax.swing.JTextField tf_sueldo;
    private javax.swing.JTextField tf_tipoEmpleo;
    private javax.swing.JTextField tf_tipoPuesto;
    private javax.swing.JTextField tf_usuario;
    private javax.swing.JTextField tf_usuario_a1;
    // End of variables declaration//GEN-END:variables
}
