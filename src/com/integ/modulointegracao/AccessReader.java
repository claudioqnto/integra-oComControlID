package com.integ.modulointegracao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import com.integ.modulointegracao.exception.InvalidSessionException;
import com.integ.modulointegracao.util.SetupDataDb;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.integ.modulointegracao.util.Utilities;
import com.integ.modulointegracao.view.AlterarDados;

/**
 * Cláudio 21 979059570
 */
public class AccessReader {
    String contador;
    private String output;
    private String retorno;
    private String entrega;
    private List<Object> list = new ArrayList();
    Utilities utilities = new Utilities();
    String ip = "ipDoControlID";
    String login = "userdoControlId";
    String senha = "senhadoControlID";

    public String getOutput() {
        return output;
    }

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }

    public String getEntrega() {
        return entrega;
    }

    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }

    /**
     * Método Logar() efetua o acesso ao Leitor biométrico retornando uma sessão
     * válida
     *
     * @return
     */
    public String logar() {
        try {
            URL url = new URL("http://" + ip + "/login.fcgi");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type", "application/json");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            JsonObject obj = Json.createObjectBuilder().add("login", login).add("password", senha).build();
            String json = obj.toString();
            os.write(json.getBytes());
            verifySession(conn);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((output = br.readLine()) != null) {
                JSONObject objSesion = new JSONObject(output);
                String session = objSesion.toMap().get("session").toString();
                System.out.println("Logado com a session -> " + session);
                this.setRetorno(session);
            }
            conn.disconnect();
        } catch (IOException | InvalidSessionException | JSONException e) {
            System.out.println("Verifique se o leitor está conectado ou as configurações de IP, Login e/ou Senha estão corretas.");
        }
        return output;
    }

    /**
     * Método listarUltimoRegistro() efetua o acesso ao Leitor biométrico
     * retornando uma string de 11 digitos podendo ser nip ou cpf (apenas
     * numeros) do usuario.
     *
     * @return
     */
    public String listarUltimoRegistro() {
        JSONObject objDateTime = null;
        try {
            URL url = new URL("http://" + ip + "/load_objects.fcgi?session=" + this.getRetorno());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type", "application/json");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            String request = String.format("{\"join\":\"LEFT\",\"object\":\"access_logs\",\"fields\":[\"id\",\"time\",\"user_id\",\"portal_id\",\"event\"],\"where\":[{\"field\":\"id\",\"value\":%s,\"operator\":\">\",\"connector\":\") AND (\"}],\"order\":[\"id\",\"descending\"],\"finish\":true,\"offset\":0}", retornoCounts());
            JSONObject obj = new JSONObject(request);
            String json = obj.toString();
            os.write(json.getBytes());
            verifySession(conn);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            System.out.println("Último registro: ");
            while ((output = br.readLine()) != null) {
                objDateTime = new JSONObject(output);
                JSONArray jsonArray = objDateTime.getJSONArray("access_logs");
                this.list.add(jsonArray.getJSONObject(0));
                entrega = String.format("%011d", Integer.parseInt(jsonArray.getJSONObject(0).optString("user_id")));
            }
        } catch (Exception e) {
            System.out.println(utilities.hourMoment() + " Erro ao listar o último registro!" + e);
        }
        return entrega;
    }

    /**
     * Método retornoCounts() efetua o acesso ao Leitor biométrico retornando um
     * contator, ou seja, fica ouvindo o leitor buscando evento de acesso.
     *
     * @return
     */
    public int retornoCounts() {
        JSONObject objDateTime = null;
        String session = null;
        Integer count = null;
        try {
            URL url = new URL("http://" + ip + "/load_objects.fcgi?session=" + this.getRetorno());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type", "application/json");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            contador = "{\"join\":\"LEFT\",\"object\":\"access_logs\",\"fields\":[\"COUNT(*)\"],\"where\":[],\"order\":[\"id\"],\"offset\":0}";
            os.write(contador.getBytes());
            verifySession(conn);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            output = br.readLine();
            JSONObject objSesion = new JSONObject(output);
            JSONArray jsonArray = objSesion.getJSONArray("access_logs");
            session = jsonArray.getJSONObject(0).optString("COUNT(*)");
            count = Integer.parseInt(session);
            count = count - 1;
            System.out.println("Return counts: " + count);
        } catch (InvalidSessionException e) {
            System.out.println("Erro InvalidSession");
            System.out.println(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "/" + Calendar.getInstance().get(Calendar.MONTH) + "/" + Calendar.getInstance().get(Calendar.YEAR) + " às " + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE) + ":" + Calendar.getInstance().get(Calendar.SECOND));
        } catch (IOException | NumberFormatException | JSONException e) {
            System.out.println("Erro no método contador!");
            System.out.println(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "/" + Calendar.getInstance().get(Calendar.MONTH) + "/" + Calendar.getInstance().get(Calendar.YEAR) + " às " + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE) + ":" + Calendar.getInstance().get(Calendar.SECOND));
        }
        return count;
    }

    /**
     * Método verifySession() verifica erro caso a resposta http do leitor
     * biométrico seja diferente de 200 .
     *
     * @param conn
     */
    public void verifySession(HttpURLConnection conn) throws IOException, InvalidSessionException {
        if (conn.getResponseCode() != 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            String result = "";
            System.out.println("Erro de conexao ao equipamento! #200 \n");
            while ((output = br.readLine()) != null) {
                result += output;
            }
            throw new InvalidSessionException("Failed : " + result + " HORA: " + Calendar.getInstance().get(Calendar.HOUR));
        }
    }

    /**
     * Método logout() encerra a conexão e sessão válida com o leitor
     * biométrico.
     */
    public void logout() {
        try {
            URL url = new URL("http://" + ip + "/logout.fcgi?session=" + getRetorno());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type", "application/json");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            String json = "";
            os.write(json.getBytes());
            verifySession(conn);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            output = br.readLine();
            if (output.equals("{}")) {
                output = "Sessão Encerrada!";
                System.out.println(getRetorno());
                conn.disconnect();
            }
            conn.disconnect();
        } catch (Exception e) {
            System.out.println(utilities.hourMoment() + " | Erro de Logout. Descrição técnica " + e);
        }
    }

    /**
     * Método que verifica se a Sessão é válida(true).
     *
     * @return
     */
    public boolean validSession() {
        boolean valida = false;
        try {
            URL url = new URL("http://" + ip + "/session_is_valid.fcgi?session=" + getRetorno());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type", "application/json");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            String json = "";
            os.write(json.getBytes());
            verifySession(conn);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            output = br.readLine();
            JSONObject objSesion = new JSONObject(output);
            //JSONArray jsonArray = objSesion.getJSONArray("session_is_valid");
            valida = objSesion.getBoolean("session_is_valid");
            //boolean valida = jsonArray.getJSONObject(0).getBoolean("session_is_valid");
            //System.out.println(valida);
        } catch (Exception e) {
            System.out.println("ERRO VALIDACAO -" + "Data-hora:" + utilities.hourMoment() + "\nDescição técnica:" + e);
        }
        return valida;
    }

    public void checkAccess() {
        SetupDataDb setupDataDb = new SetupDataDb();
        try {
            setupDataDb.buscarDados();
        } catch (SQLException ex) {
            Logger.getLogger(AccessReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (setupDataDb.getIp().equals("") || setupDataDb.getIp() == null) {
            AlterarDados alteraDados = new AlterarDados();
            alteraDados.setVisible(true);            
        } else {
            try {
                populaDados();
                logar();
            } catch (SQLException ex) {
                Logger.getLogger(AccessReader.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

    }

    public void populaDados() throws SQLException {
        SetupDataDb configuraDados = new SetupDataDb();
        configuraDados.buscarDados();
        this.ip = configuraDados.getIp();
        this.login = configuraDados.getLogin();
        this.senha = configuraDados.getSenha();
    }
}
