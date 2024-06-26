/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tdbd2_proyecto1;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.policy.actions.DynamoDBv2Actions;
import com.amazonaws.regions.Regions;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import com.amazonaws.services.dynamodbv2.model.DeleteItemRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.Credentials;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author darie
 */
public class Admin {

    private AmazonDynamoDB client;
    private Table table;
    private AmazonDynamoDBClient dynamo;

    public Admin() {

        client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
        client = AmazonDynamoDBClientBuilder.defaultClient();

    
    }

    //CREATES
    //x == 0 -> crear
    //x == otro numero -> modificar
    //creates de postulante
    public boolean createUser(String username, String password, String user_id, String obj) {
        if(this.login(user_id, password) != null){
            return false;
        }
        if(obj.equals("usuario")){
            if(this.getPersonal_pf(user_id) != null){
                return false;
            }
        }else {
            if(this.getEmpresa(user_id) != null){
                return false;
            }
        }
        
        HashMap<String, AttributeValue> values = new HashMap<String, AttributeValue>();
        values.put("PK", new AttributeValue("usr_" + username));
        values.put("SK", new AttributeValue(password));
        values.put("Nombre", new AttributeValue(user_id));
        values.put("Obj", new AttributeValue(obj));
        try {
            client.putItem("Centro_De_Empleo", values);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean createPersonal_pf(String[] values, int x) {
        //ejemplo de formato
        //String[] values = {"user_12247420", "Andino", "dmafgames@gmail.com", "19","Aleman", "Diego", "Hombre", "(504) 9999-3221"};
        if (getPersonal_pf(values[0]) != null && x == 0) {
            //caso de q el usuario ya exista
            return false;
        }
        HashMap<String, AttributeValue> keyValues = new HashMap<String, AttributeValue>();
        String[] keys = {"PK", "Apellido", "Correo", "Edad", "Nacionalidad", "Nombre", "Sexo", "Telefono"};
        keyValues.put("SK", new AttributeValue("personal_pf"));

        for (int i = 0; i < keys.length; i++) {

            keyValues.put(keys[i], new AttributeValue((String) values[i]));

        }

        //ejecutar el create
        try {
            client.putItem("Centro_De_Empleo", keyValues);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean createFamiliar_pf(Object[] values, int x) {
        /*
        EJEMPLO DE INPUT VALIDO:
        ArrayList<String> correos = new ArrayList<>();
        correos.add("Indiantechsupport@gmail.com");
        correos.add("akem@gmail.com");
        Object[] values = {"user_12247420", correos, "dmaf casa 1123", "divorciado", "0"};
        System.out.println(createFamiliar_pf(values));
         */
        if (getFamiliar_pf((String) values[0]) != null && x == 0) {
            //caso de q el usuario ya exista
            return false;
        }
        HashMap<String, AttributeValue> keyValues = new HashMap<String, AttributeValue>();
        String[] keys = {"PK", "Correos_de_Dependientes", "Direccion", "Estado_Civil", "Hijos"};
        keyValues.put("SK", new AttributeValue("familiar_pf"));
        Object[] a = new Object[4];

        for (int i = 0; i < keys.length; i++) {
            if (i != 1) {
                keyValues.put(keys[i], new AttributeValue((String) values[i]));
            } else {
                keyValues.put(keys[i], new AttributeValue((ArrayList<String>) values[i]));
            }
        }

        //ejecutar el create
        try {
            client.putItem("Centro_De_Empleo", keyValues);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean createLegal_pf(Object[] values, int x) {
        /*
        EJEMPLO DE INPUT VALIDO:
        ArrayList<String> antecedentes = new ArrayList<>();
        antecedentes.add("robo(armado)");
        antecedentes.add("asalto");
        Object[] values = {"user_12247420", antecedentes, false, "divorciado", "0"};
        System.out.println(createLegal_pf(values));
         */
        if (getLegal_pf((String) values[0]) != null && x == 0) {
            //caso de q el usuario ya exista
            return false;
        }
        HashMap<String, AttributeValue> keyValues = new HashMap<String, AttributeValue>();
        String[] keys = {"PK", "Antecedentes", "Servicio_Militar", "SSN"};
        keyValues.put("SK", new AttributeValue("legal_pf"));
        Object[] a = new Object[4];
        
        for (int i = 0; i < keys.length; i++) {
            if (i == 1) {
                keyValues.put(keys[i], new AttributeValue((ArrayList<String>) values[i]));
            } else if (i == 2) {
                keyValues.put(keys[i], (new AttributeValue()).withBOOL((Boolean) values[2]));
            } else {
                keyValues.put(keys[i], new AttributeValue((String) values[i]));
            }
        }
       print(keys);
       print(values);
        //ejecutar el create
        try {
            Item item = new Item();
            client.putItem("Centro_De_Empleo", keyValues);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean createSanitary_pf(Object[] values, int x) {
        /*
        EJEMPLO
        ArrayList<String> alergias = new ArrayList<>();
        alergias.add("polvo");
        
        Object[] values = {"user_12247420", alergias, "tenia cancer", "no toma meds", "prueba positiva covid"};
        System.out.println(createSanitary_pf(values));
         */
        if (getSanitary_pf((String) values[0]) != null && x == 0) {
            //caso de q el usuario ya exista
            return false;
        }
        HashMap<String, AttributeValue> keyValues = new HashMap<String, AttributeValue>();
        String[] keys = {"PK", "Alergias", "Historial_Medico", "Info_Meds", "Resultado_pruebas"};

        keyValues.put("SK", new AttributeValue("sanitary_pf"));

        print(keys);
        print(values);

        //ejecutar el create
        try {
            Item item = new Item();
            client.putItem("Centro_De_Empleo", keyValues);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean createProfessional_pf(Object[] values, int x) {
        /*
        ArrayList<String> certificaciones = new ArrayList<>();
        certificaciones.add("curso cs50");
        ArrayList<String> conocimientos = new ArrayList<>();
        conocimientos.add("tocar guitarra");
        conocimientos.add("manejo de arduinos");
        ArrayList<String> idiomas = new ArrayList<>();
        idiomas.add("frances");
        idiomas.add("español");
        ArrayList<String> logros = new ArrayList<>();
      logros.add("fundar compañia dmaf games");

        Object[] values = {"user_12247420", "3", certificaciones, conocimientos, idiomas, logros};
        System.out.println(createProfessional_pf(values));
         */
        if (getProfesional_pf((String) values[0]) != null && x == 0) {
            //caso de q el usuario ya exista
            return false;
        }
        HashMap<String, AttributeValue> keyValues = new HashMap<String, AttributeValue>();
        String[] keys = {"PK", "Años", "Certificaciones", "Conocimientos_Especificas", "Idiomas", "Logros_Profesionales"};
        keyValues.put("SK", new AttributeValue("profesional_pf"));
        Object[] a = new Object[4];

        for (int i = 0; i < keys.length; i++) {
            if (i == 2 || i == 3 || i == 4 || i == 5) {
                keyValues.put(keys[i], new AttributeValue((ArrayList<String>) values[i]));

            } else {
                keyValues.put(keys[i], new AttributeValue((String) values[i]));
            }
        }
        print(keys);
        print(values);

        //ejecutar el create
        try {
            Item item = new Item();
            client.putItem("Centro_De_Empleo", keyValues);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean createAcademic_pf(Object[] values, int x) {
        /*
        EJEMPLO:
        ArrayList<String> estudios = new ArrayList<>();
        estudios.add("investigacion programacion");
       
        ArrayList<String> titulos = new ArrayList<>();
        titulos.add("Ingeniero en Sistemas");
        
       

        Object[] values = {"user_12247420", estudios, "Unitec TGU", "Universitario", titulos};
        System.out.println(createAcademic_pf(values));
         */
        if (getAcademic_pf((String) values[0]) != null && x == 0) {
            //caso de q el usuario ya exista
            return false;
        }
        HashMap<String, AttributeValue> keyValues = new HashMap<String, AttributeValue>();
        String[] keys = {"PK", "Estudios_Realizados", "Institución egresada", "Nivel_Estudio", "Titulos"};
        keyValues.put("SK", new AttributeValue("academic_pf"));

        for (int i = 0; i < keys.length; i++) {
            if (i == 1 || i == 4) {
                keyValues.put(keys[i], new AttributeValue((ArrayList<String>) values[i]));

            } else {
                keyValues.put(keys[i], new AttributeValue((String) values[i]));
            }
        }

        //ejecutar el create
        try {
            Item item = new Item();
            client.putItem("Centro_De_Empleo", keyValues);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean createSolicitud(Object[] values, int x) {
        /*
        EJEMPLO:
        
        ArrayList<String> puestosA = new ArrayList<>();
        puestosA.add("puesto_1");
        ArrayList<String> puestosI = new ArrayList<>();
        puestosI.add("puesto_2");
        Object[] values = {"user_12247420", puestosA,puestosI, "3221.3", "Fijo", "Administrativo"};
        System.out.println(createSolicitud(values));
         */
        if (getSolicitud((String) values[0]) != null && x == 0) {
            //caso de q el usuario ya exista
            return false;
        }
        HashMap<String, AttributeValue> keyValues = new HashMap<String, AttributeValue>();
        String[] keys = {"PK", "Puestos_Aceptables", "Puestos_Inaceptables", "Salario", "Tipo_Contrato", "Tipo_trabajo"};
        keyValues.put("SK", new AttributeValue("solicitud"));

        for (int i = 0; i < keys.length; i++) {
            if (i == 1 || i == 2) {
                keyValues.put(keys[i], new AttributeValue(((ArrayList<String>) values[i])));
            } 
            else{
                keyValues.put(keys[i], new AttributeValue((String)values[i]));
            }
        }

        //ejecutar el create
        try {
            print(keys);
            print(values);

            client.putItem("Centro_De_Empleo", keyValues);
            return true;
        } catch (Exception e) {
       
            return false;
        }
    }

    //create de Empresa
    public boolean createEmpresa(Object[] values, int x) {

        if (getEmpresa((String) values[0]) != null && x == 0) {
            //caso de q el usuario ya exista
            return false;
        }
        HashMap<String, AttributeValue> keyValues = new HashMap<String, AttributeValue>();
        String[] keys = {"PK", "CIF", "Telefono", "Direccion", "Director", "Correo","Nombre"};
        keyValues.put("SK", new AttributeValue("perfil"));

        for (int i = 0; i < keys.length; i++) {

            keyValues.put(keys[i], new AttributeValue((String) values[i]));

        }

        //ejecutar el create
        try {

            client.putItem("Centro_De_Empleo", keyValues);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean createEmpleo(Object[] values, int x) {
        if (getEmpleo((String) values[0], (String) values[1]) != null && x == 0) {
            return false;
        }
        HashMap<String, AttributeValue> keyValues = new HashMap<String, AttributeValue>();
        String[] keys = {"PK", "SK", "Antecedentes", "AñosExperiencia", "Certificaciones", "Idiomas", "Nivel Educativo", "Nombre", "Puestos", "Requisitos_Personales", "Tipo"};

        for (int i = 0; i < keys.length; i++) {
            if (i == 2) {
<<<<<<< HEAD
                keyValues.put(keys[i], (new AttributeValue()).withBOOL((Boolean) values[i]));
            } else if (i == 4 || i == 5 || i == 8) {
                keyValues.put(keys[i], new AttributeValue((ArrayList<String>) values[i]));

            } else {
=======
                keyValues.put(keys[i], new AttributeValue("true"));
            }  else {
>>>>>>> 998e34922267efdf306ec38e33fc8a45a9ba07fc
                keyValues.put(keys[i], new AttributeValue((String) values[i]));
            }
        }
        keyValues.put("Obj", new AttributeValue("empleo"));
        //ejecutar el create
        try {
            Item item = new Item();
            client.putItem("Centro_De_Empleo", keyValues);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //otros creates 
    public int createPuesto(int puestosNumber, String name, String type, Double sueldo) {
        puestosNumber++;
        String pk = "puesto_" + puestosNumber;
        HashMap<String, AttributeValue> keyValues = new HashMap<String, AttributeValue>();

        keyValues.put("PK", new AttributeValue(pk));
        keyValues.put("SK", new AttributeValue("descripcion"));
        keyValues.put("Obj", new AttributeValue("puesto"));
        keyValues.put("Nombre", new AttributeValue(name));
        keyValues.put("Sueldo", new AttributeValue(Double.toString(sueldo)));
        keyValues.put("Tipo", new AttributeValue(type));
        //ejecutar el create

        client.putItem("Centro_De_Empleo", keyValues);
        return puestosNumber;
    }

    //UPDATE
    //este funciona como create si es su primer trabajo, y un create de trabajos anteriores
    //el jobNumber es (pJobNumber+1)
    public int updateJob(String user, int jobNumber, Object[] values) {
        HashMap<String, AttributeValue> keyValues;
        String[] array = getCurrentJob(user);
        //convertir trabajo actual en trabajo anterior
        if (array != null) {
            keyValues = new HashMap<String, AttributeValue>();
            keyValues.put("PK", new AttributeValue(user));
            jobNumber++;
            keyValues.put("SK", new AttributeValue("pJob_" + Integer.toString(jobNumber)));
            keyValues.put("Rango", new AttributeValue(array[3] + "-" + Integer.toString((int) values[2])));
            ArrayList<String> empleo = getEmpleo(array[0]);
             JSONArray ar = new JSONArray(empleo.toString());
            
            keyValues.put("Titulo", new AttributeValue(ar.getJSONObject(0).getString("Nombre")));
            client.putItem("Centro_De_Empleo", keyValues);
        }
        /*
        orden: empleo (el id), (ignorar), (ignorar), año inicial, empresa (el id tambien)
         */
        keyValues = new HashMap<String, AttributeValue>();
        String[] keys = {"PK", "empleo_id", "Initial_Year"};
        keyValues.put("SK", new AttributeValue("emp_actual"));
        HashMap<String, AttributeValue> deletion = new HashMap<String, AttributeValue>();
        deletion.put("PK", new AttributeValue((String) values[0]));
        deletion.put("SK", new AttributeValue((String) values[1]));
        for (int i = 0; i < keys.length; i++) {
            if (i != 2) {
                keyValues.put(keys[i], new AttributeValue((String) values[i]));
            } else {
                keyValues.put(keys[i], new AttributeValue(Integer.toString((int) values[i])));
            }
        }
        client.deleteItem("Centro_De_Empleo", deletion);
        //ejecutar el create
        try {

            client.putItem("Centro_De_Empleo", keyValues);
            return jobNumber;
        } catch (Exception e) {
           
            return -1;
        }
    }

    //GETTERS
    //gets de postulante
    public String[] getPersonal_pf(String par) {
        //en este hashmap se pone <nombre de atributo>-<valor>
        HashMap<String, AttributeValue> queue = new HashMap<String, AttributeValue>();
        queue.put("PK", new AttributeValue(par));
        queue.put("SK", new AttributeValue("personal_pf"));
       
        Map<String, AttributeValue> results = client.getItem("Centro_De_Empleo", queue).getItem();
        if (results == null) {
            return null;
        }
        String[] datos = new String[results.size()];
        int i = 0;

        Object[] keys = results.keySet().toArray();
        for (String key : results.keySet()) {
            String data = results.get(key).getS();
            if (data == null) {
                data = results.get(key).getN();
            }
            datos[i] = data;
            i++;
        }

        /*
        orden: nombre, correo, edad, telefono(string), (ignorar),nacionalidad, apellido, (ignorar), sexo
         */
        return datos;
    }
    //public void Personal_pf_Data(String par){}

    public String[] getFamiliar_pf(String par) {
        //en este hashmap se pone <nombre de atributo>-<valor>
        HashMap<String, AttributeValue> queue = new HashMap<String, AttributeValue>();
        queue.put("PK", new AttributeValue(par));
        queue.put("SK", new AttributeValue("familiar_pf"));

        Map<String, AttributeValue> results = client.getItem("Centro_De_Empleo", queue).getItem();
        if (results == null) {
            return null;
        }
        String[] datos = new String[results.size()];
        int i = 0;

        Object[] keys = results.keySet().toArray();
        for (String key : results.keySet()) {
            String data = results.get(key).getS();
            if (data == null) {
                data = results.get(key).getN();
            }
            if (data == null) {
                ArrayList<String> correos = (ArrayList) results.get(key).getSS();
                data = "";
                for (String c : correos) {
                    data += c;
                    data += ",";
                }
            }
            datos[i] = data;
            i++;
        }

        /*
        orden: Estado civil,(ignorar), correos de dependientes (los puse como una string con cada correo separado por una coma. alli haganle split), Hijos, direccion, (ignorar)
         */
        return datos;

    }

    public String[] getLegal_pf(String par) {
        //en este hashmap se pone <nombre de atributo>-<valor>
        HashMap<String, AttributeValue> queue = new HashMap<String, AttributeValue>();
        queue.put("PK", new AttributeValue(par));
        queue.put("SK", new AttributeValue("legal_pf"));
       
        

        Map<String, AttributeValue> results = client.getItem("Centro_De_Empleo", queue).getItem();
        if (results == null) {
            return null;
        }
        String[] datos = new String[results.size()];
        int i = 0;

        Object[] keys = results.keySet().toArray();
        for (String key : results.keySet()) {
            String data = results.get(key).getS();
            if (data == null) {
                data = results.get(key).getN();
            }
            if (data == null) {
                Boolean b = true;
                b = results.get(key).getBOOL();
                if (b != null) {
                    if (b) {
                        data = "si";
                    } else {
                        data = "no";
                    }
                }
            }
            if (data == null) {
                ArrayList<String> correos = (ArrayList) results.get(key).getSS();
                data = "";
                for (String c : correos) {
                    data += c;
                    data += ",";
                }
            }
            datos[i] = data;
            i++;
        }

        /*
        orden: (ignorar), (ignorar), antecedentes penales (1 string con comas),Servicio Militar,  SSN
         */
        return datos;

    }

    public String[] getSanitary_pf(String par) {
        //en este hashmap se pone <nombre de atributo>-<valor>
        HashMap<String, AttributeValue> queue = new HashMap<String, AttributeValue>();
        queue.put("PK", new AttributeValue(par));
        queue.put("SK", new AttributeValue("sanitary_pf"));
      

        Map<String, AttributeValue> results = client.getItem("Centro_De_Empleo", queue).getItem();
        if (results == null) {
            return null;
        }
        String[] datos = new String[results.size()];
        int i = 0;

        Object[] keys = results.keySet().toArray();
        for (String key : results.keySet()) {
            String data = results.get(key).getS();
            if (data == null) {
                data = results.get(key).getN();
            }
            if (data == null) {
                Boolean b = true;
                b = results.get(key).getBOOL();
                if (b != null) {
                    if (b) {
                        data = "si";
                    } else {
                        data = "no";
                    }
                }
            }
            if (data == null) {
                ArrayList<String> correos = (ArrayList) results.get(key).getSS();
                data = "";
                for (String c : correos) {
                    data += c;
                    data += ",";
                }
            }
            datos[i] = data;
            i++;
        }

        /*
        orden: info de medicamentos (string), alergias (1 string con comas), (ignorar), historial medico (1 string con comas), (ignorar),resultado pruebas (string)
         */
        return datos;

    }

    public String[] getProfesional_pf(String par) {
        //en este hashmap se pone <nombre de atributo>-<valor>
        HashMap<String, AttributeValue> queue = new HashMap<String, AttributeValue>();
        queue.put("PK", new AttributeValue(par));
        queue.put("SK", new AttributeValue("profesional_pf"));


        Map<String, AttributeValue> results = client.getItem("Centro_De_Empleo", queue).getItem();
        if (results == null) {
            return null;
        }
        String[] datos = new String[results.size()];
        int i = 0;

        Object[] keys = results.keySet().toArray();
        for (String key : results.keySet()) {
            String data = results.get(key).getS();
            if (data == null) {
                data = results.get(key).getN();
            }
            if (data == null) {
                Boolean b = true;
                b = results.get(key).getBOOL();
                if (b != null) {
                    if (b) {
                        data = "si";
                    } else {
                        data = "no";
                    }
                }
            }
            if (data == null) {
                ArrayList<String> correos = (ArrayList) results.get(key).getSS();
                data = "";
                for (String c : correos) {
                    data += c;
                    data += ",";
                }
            }
            datos[i] = data;
            i++;
        }

        /*
        orden: Años, Logros profesionales(String), (ignorar), Idiomas (String), Certificaciones (String), (ignorar), conocimientos especificos (string)
         */
        return datos;

    }

    public String[] getAcademic_pf(String par) {
        //en este hashmap se pone <nombre de atributo>-<valor>
        HashMap<String, AttributeValue> queue = new HashMap<String, AttributeValue>();
        queue.put("PK", new AttributeValue(par));
        queue.put("SK", new AttributeValue("academic_pf"));
      

        Map<String, AttributeValue> results = client.getItem("Centro_De_Empleo", queue).getItem();
        if (results == null) {
            return null;
        }
        String[] datos = new String[results.size()];
        int i = 0;

        Object[] keys = results.keySet().toArray();
        for (String key : results.keySet()) {
            String data = results.get(key).getS();
            if (data == null) {
                data = results.get(key).getN();
            }
            if (data == null) {
                Boolean b = true;
                b = results.get(key).getBOOL();
                if (b != null) {
                    if (b) {
                        data = "si";
                    } else {
                        data = "no";
                    }
                }
            }
            if (data == null) {
                ArrayList<String> correos = (ArrayList) results.get(key).getSS();
                data = "";
                for (String c : correos) {
                    data += c;
                    data += ",";
                }
            }
            datos[i] = data;
            i++;
        }

        /*
        orden: Institucion, Nivel Universitario, Estudios (string), (ignorar), Titulos (string), (ignorar)
         */
        return datos;

    }

    public String[] getSolicitud(String par) {
        //en este hashmap se pone <nombre de atributo>-<valor>

        HashMap<String, AttributeValue> queue = new HashMap<String, AttributeValue>();
        queue.put("PK", new AttributeValue(par));
        queue.put("SK", new AttributeValue("solicitud"));
     

        Map<String, AttributeValue> results = client.getItem("Centro_De_Empleo", queue).getItem();
        if (results == null) {
            return null;
        }
        String[] datos = new String[results.size()];
        int i = 0;

        Object[] keys = results.keySet().toArray();
        for (String key : results.keySet()) {
            String data = results.get(key).getS();
            if (data == null) {
                data = results.get(key).getN();
            }
            if (data == null) {
                Boolean b = true;
                b = results.get(key).getBOOL();
                if (b != null) {
                    if (b) {
                        data = "si";
                    } else {
                        data = "no";
                    }
                }
            }
            if (data == null) {
                ArrayList<String> correos = (ArrayList) results.get(key).getSS();
                data = "";
                for (String c : correos) {
                    data += c;
                    data += ",";
                }
            }
            datos[i] = data;
            i++;
        }

        /*
        orden: Tipo Contrato, (ignorar), Tipo_trabajo, Puestos aceptables(string), Puestos inaceptables (string), (ignorar), salario
         */
 /*IMPORTANTE
        Las strings de puestos aceptables e inaceptables devuelven ids de puestos separados por comas
        una vez que se haga el split, se debe usar getPuesto() para conseguir la info de cada puesto individual
       
         */
        return datos;

    }

    public String[] getCurrentJob(String par) {
        //en este hashmap se pone <nombre de atributo>-<valor>
        HashMap<String, AttributeValue> queue = new HashMap<String, AttributeValue>();
        queue.put("PK", new AttributeValue(par));
        queue.put("SK", new AttributeValue("emp_actual"));
  
        Map<String, AttributeValue> results = client.getItem("Centro_De_Empleo", queue).getItem();
        
        if (results == null) {
            return null;
        }

        String[] datos = new String[results.size()];
        int i = 0;

        Object[] keys = results.keySet().toArray();
        for (String key : results.keySet()) {
            String data = results.get(key).getS();

            datos[i] = data;
            i++;
        }

        /*
        orden: empleo (el id), (ignorar), año inicial, 
         */
        return datos;

    }

    public String[] getPreviousJob(String postulante, String jobNumber) {
        //en este hashmap se pone <nombre de atributo>-<valor>
        HashMap<String, AttributeValue> queue = new HashMap<String, AttributeValue>();
        queue.put("PK", new AttributeValue(postulante));
        queue.put("SK", new AttributeValue(jobNumber));
        

        Map<String, AttributeValue> results = client.getItem("Centro_De_Empleo", queue).getItem();
        if (results == null) {
            return null;
        }
        String[] datos = new String[results.size()];
        int i = 0;

        Object[] keys = results.keySet().toArray();
        for (String key : results.keySet()) {
            String data = results.get(key).getS();

            datos[i] = data;
            i++;
        }

        /*
        orden: Empleador, Rango (ignorar), (ignorar), Titulo
         */
        //si se tiene más de un trabajo previo se debe correr este metodo 1 vez por trabajo, cambiando el job number
        return datos;

    }

    //gets de Empresa
    public String[] getEmpresa(String par) {
        //en este hashmap se pone <nombre de atributo>-<valor>
        HashMap<String, AttributeValue> queue = new HashMap<String, AttributeValue>();
        queue.put("PK", new AttributeValue(par));
        queue.put("SK", new AttributeValue("perfil"));
       

        Map<String, AttributeValue> results = client.getItem("Centro_De_Empleo", queue).getItem();
        if (results == null) {
            return null;
        }
        String[] datos = new String[results.size()];
        int i = 0;

        Object[] keys = results.keySet().toArray();
        for (String key : results.keySet()) {
            String data = results.get(key).getS();

            datos[i] = data;
            i++;
        }

        /*
        orden: Nombre, CIF, Telefono,(ignorar), Director, Direccion, (ignorar)
         */
        return datos;

    }

    public String[] getEmpleo(String empresa, String empId) {
        //en este hashmap se pone <nombre de atributo>-<valor>
        HashMap<String, AttributeValue> queue = new HashMap<String, AttributeValue>();
        queue.put("PK", new AttributeValue(empresa));
        queue.put("SK", new AttributeValue(empId));
     

        Map<String, AttributeValue> results = client.getItem("Centro_De_Empleo", queue).getItem();
        if (results == null) {
            return null;
        }
        String[] datos = new String[results.size()];
        int i = 0;

        Object[] keys = results.keySet().toArray();
        for (String key : results.keySet()) {
            String data = results.get(key).getS();
            if (data == null) {
                data = results.get(key).getN();
            }
            if (data == null) {

                Boolean b = true;
                b = results.get(key).getBOOL();
                if (b != null) {
                    if (b) {
                        data = "si";
                    } else {
                        data = "no";
                    }
                }
            }
            if (data == null) {
                ArrayList<String> correos = (ArrayList) results.get(key).getSS();
                data = "";
                for (String c : correos) {
                    data += c;
                    data += ",";
                }
            }
            datos[i] = data;
            i++;
        }

        /*
        ok, preparense que este es largo
        orden: 
        Nombre (osea titulo de empleo), nivel educativo, Tipo de empleo, años de experiencia necesarios, (ingorar), (ignorar), idiomas(string),
        Certificaciones (string), puestos (String, otra vez solo almacena el id), (ignorar), requisitos personales (String), 
        Antecedentes(si es un no, osea false, el postulante no debe tener antecedentes. si es si, (osea true), al empleador no le importa si los tiene)
         */
        return datos;

    }

    //otros gets
    public String[] getPuesto(String par) {
        //en este hashmap se pone <nombre de atributo>-<valor>
        HashMap<String, AttributeValue> queue = new HashMap<String, AttributeValue>();
        queue.put("PK", new AttributeValue(par));
        queue.put("SK", new AttributeValue("descripcion"));
      

        Map<String, AttributeValue> results = client.getItem("Centro_De_Empleo", queue).getItem();

        String[] datos = new String[results.size()];
        int i = 0;

        Object[] keys = results.keySet().toArray();
        for (String key : results.keySet()) {
            String data = results.get(key).getS();

            datos[i] = data;
            if(key.equals("Sueldo")){
                datos[i] = results.get(key).getN();
            }
            i++;
        }

        /*
        orden: nombre, tipo, (ignorar), (ignorar), (ignorar)
         */
        return datos;

    }

    public String login(String user, String pass) {
        HashMap<String, AttributeValue> queue = new HashMap<String, AttributeValue>();
        queue.put("PK", new AttributeValue(user));
        queue.put("SK", new AttributeValue(pass));

        Map<String, AttributeValue> results = client.getItem("Centro_De_Empleo", queue).getItem();
        if (results != null) {
        
            return results.get(results.keySet().toArray()[0]).getS();
        } else {
            return null;
        }

    }

    //DELETES
    public boolean deletePostulante(String par) {
        QuerySpec query = new QuerySpec().withKeyConditionExpression("PK= :v_id").withValueMap(new ValueMap().withString(":v_id", par));
        ScanSpec query2 = new ScanSpec().withFilterExpression("SK = :v_id").withValueMap(new ValueMap().withString(":v_id", par));

        Table table = new Table(client, "Centro_De_Empleo");
        ItemCollection<QueryOutcome> results = table.query(query);
        ItemCollection<ScanOutcome> results2 = table.scan(query2);

        try {
            for (Item result : results) {
                
                HashMap<String, AttributeValue> map = new HashMap<>();
                map.put("PK", new AttributeValue(par));
                map.put("SK", new AttributeValue(result.getJSON("SK").replace("\"", "")));

                client.deleteItem("Centro_De_Empleo", map);
            }

            for (Item result : results2) {

                HashMap<String, AttributeValue> map = new HashMap<>();
                map.put("PK", new AttributeValue(par));
                map.put("SK", new AttributeValue(result.getJSON("SK").replace("\"", "")));

                client.deleteItem("Centro_De_Empleo", map);

            }

            return true;
        } catch (Exception E) {
    
            return false;
        }
    }

    //DELETES
    public boolean delete(String par) {
        QuerySpec query = new QuerySpec().withKeyConditionExpression("PK= :v_id").withValueMap(new ValueMap().withString(":v_id", par));

        Table table = new Table(client, "Centro_De_Empleo");
        ItemCollection<QueryOutcome> results = table.query(query);

        try {
            for (Item result : results) {
          
                HashMap<String, AttributeValue> map = new HashMap<>();
                map.put("PK", new AttributeValue(par));
                map.put("SK", new AttributeValue(result.getJSON("SK").replace("\"", "")));

                client.deleteItem("Centro_De_Empleo", map);
            }

            return true;
        } catch (Exception E) {
        
            return false;
        }
    }

    public boolean deleteUser(String user, String pass, String userid) {

        HashMap<String, AttributeValue> map = new HashMap<>();
        map.put("PK", new AttributeValue(user));
        map.put("SK", new AttributeValue(pass));

        try {
            client.deleteItem("Centro_De_Empleo", map);
            //deletePostulante(userid);

            return true;
        } catch (Exception E) {
         
            return false;
        }
    }

    //OTROS METODOS
    public ArrayList<String> getEmpleos(String par) {
        QuerySpec query = new QuerySpec().withKeyConditionExpression("PK= :v_id AND Obj = empleo").withValueMap(new ValueMap().withString(":v_id", par));

        Table table = new Table(client, "Centro_De_Empleo");
        ItemCollection<QueryOutcome> results = table.query(query);
        ArrayList<String> empleos = new ArrayList<>();
        try {
            for (Item result : results) {
                empleos.add(result.toJSON());
            }

            return empleos;
        } catch (Exception E) {
         
            return null;
        }
    }
    
    public ArrayList<String> getContratados(String par) {
        ScanSpec query = new ScanSpec().withFilterExpression("SK = :v_id AND empleo_id = :id").withValueMap(new ValueMap().withString(":v_id", "emp_actual").withString(":id", par));

        Table table = new Table(client, "Centro_De_Empleo");
        ItemCollection<ScanOutcome> results = table.scan(query);
        ArrayList<String> empleos = new ArrayList<>();
        try {
            for (Item result : results) {
                empleos.add(result.toJSON());
            }

            return empleos;
        } catch (Exception E) {

            return null;
        }
    }
    public ArrayList<String> getEmpleos() {
        ScanSpec query2 = new ScanSpec().withFilterExpression("Obj = :v_id").withValueMap(new ValueMap().withString(":v_id", "empleo"));
     
        Table table = new Table(client, "Centro_De_Empleo");

        ItemCollection<ScanOutcome> results2 = table.scan(query2);
        ArrayList<String> respuesta = new ArrayList<>();
        try {

            for (Item result : results2) {
                respuesta.add(result.toJSON());
            }

        } catch (Exception E) {
           return null;

        }

        return respuesta;
    }

    public ArrayList<String> getEmpleo(String empleoid) {
//este busca un empleo por medio de su id
        ScanSpec query2 = new ScanSpec().withFilterExpression("SK = :id AND Obj = :v_id").withValueMap(new ValueMap().withString(":id", empleoid).withString(":v_id", "empleo"));
        
        Table table = new Table(client, "Centro_De_Empleo");

        ItemCollection<ScanOutcome> results2 = table.scan(query2);
        ArrayList<String> respuesta = new ArrayList<>();
        try {

            for (Item result : results2) {
                respuesta.add(result.toJSON());
            }

        } catch (Exception E) {
      
        }

        return respuesta;
    }
    
    

    public ArrayList<String> getPuestos() {

        ScanSpec query2 = new ScanSpec().withFilterExpression("Obj = :v_id").withValueMap(new ValueMap().withString(":v_id", "puesto"));

        Table table = new Table(client, "Centro_De_Empleo");

        ItemCollection<ScanOutcome> results2 = table.scan(query2);
        ArrayList<String> respuesta = new ArrayList<>();
        try {

            for (Item result : results2) {

                respuesta.add(result.toJSON());

            }

        } catch (Exception E) {
      
        }

        return respuesta;
    }

    public ArrayList<String> getEmpresas() {
        ScanSpec query2 = new ScanSpec().withFilterExpression("Obj = :v_id").withValueMap(new ValueMap().withString(":v_id", "empresa"));

        Table table = new Table(client, "Centro_De_Empleo");

        ItemCollection<ScanOutcome> results2 = table.scan(query2);
        ArrayList<String> respuesta = new ArrayList<>();
        try {

            for (Item result : results2) {

                respuesta.add(result.toJSON());

            }

        } catch (Exception E) {
        

        }

        return respuesta;
    }

    public ArrayList<String> getUsers() {
        ScanSpec query2 = new ScanSpec().withFilterExpression("Obj = :v_id").withValueMap(new ValueMap().withString(":v_id", "usuario"));

        Table table = new Table(client, "Centro_De_Empleo");

        ItemCollection<ScanOutcome> results2 = table.scan(query2);
        ArrayList<String> respuesta = new ArrayList<>();
        try {

            for (Item result : results2) {

                respuesta.add(result.toJSON());

            }

        } catch (Exception E) {
     

        }

        return respuesta;
    }

    public ArrayList<String> getSolicitudes(String empleoId) {
        ScanSpec query2 = new ScanSpec().withFilterExpression("SK = :id AND Obj = :v_id").withValueMap(new ValueMap().withString(":id", empleoId).withString(":v_id", "solicitud"));

        Table table = new Table(client, "Centro_De_Empleo");

        ItemCollection<ScanOutcome> results2 = table.scan(query2);
        ArrayList<String> respuesta = new ArrayList<>();
        try {

            for (Item result : results2) {

                respuesta.add(result.toJSON());

            }

        } catch (Exception E) {
           

        }

        return respuesta;
    }

    public ArrayList<String> getSolicitudesDeUsuario(String userid) {
        ScanSpec query2 = new ScanSpec().withFilterExpression("PK = :id AND Obj = :v_id").withValueMap(new ValueMap().withString(":id", userid).withString(":v_id", "solicitud"));

        Table table = new Table(client, "Centro_De_Empleo");

        ItemCollection<ScanOutcome> results2 = table.scan(query2);
        ArrayList<String> respuesta = new ArrayList<>();
        try {

            for (Item result : results2) {

                respuesta.add(result.toJSON());

            }

        } catch (Exception E) {

        }

        return respuesta;
    }

    public ArrayList<String> getFalimiares(String userId) {
        ScanSpec query2 = new ScanSpec().withFilterExpression("PK = :id AND Tipo = :v_id").withValueMap(new ValueMap().withString(":id", userId).withString(":v_id", "familia"));

        Table table = new Table(client, "Centro_De_Empleo");

        ItemCollection<ScanOutcome> results2 = table.scan(query2);
        ArrayList<String> respuesta = new ArrayList<>();
        try {

            for (Item result : results2) {

                respuesta.add(result.toJSON());

            }

        } catch (Exception E) {

        }

        return respuesta;
    }

    public boolean solicitarEmpleo(String user_id, String empleo_id) {
        HashMap<String, AttributeValue> values = new HashMap<String, AttributeValue>();
        values.put("PK", new AttributeValue(user_id));
        values.put("SK", new AttributeValue(empleo_id));
        values.put("Obj", new AttributeValue("solicitud"));
        try {
            client.putItem("Centro_De_Empleo", values);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean añadirFamiliar(String user1, String user2) {
        HashMap<String, AttributeValue> datos = new HashMap<String, AttributeValue>();
        datos.put("PK", new AttributeValue(user1));
        datos.put("SK", new AttributeValue(user2));
        datos.put("Obj", new AttributeValue("familia"));
        try {
            client.putItem("Centro_De_Empleo", datos);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<String> filtroEmpleados(String queue, ValueMap valores, HashMap names) {
        try {

            ScanSpec query2 = new ScanSpec().withFilterExpression(queue).withValueMap(valores).withNameMap(names);

            Table table = new Table(client, "Centro_De_Empleo");

            ItemCollection<ScanOutcome> results2 = table.scan(query2);
            ArrayList<String> respuesta = new ArrayList<>();

            for (Item result : results2) {

                respuesta.add(result.toJSON());

            }
            return respuesta;
        } catch (Exception E) {
            
            return null;
        }
    }

    //metodo print solo para pruebas
    public void print(Object[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
            
        }
        System.out.println("");
    }
    public void print(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
            
        }
        System.out.println("");
    }

    public void print(ArrayList<String> arr) {
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i) + " ");
        }
        System.out.println("");
    }

}
