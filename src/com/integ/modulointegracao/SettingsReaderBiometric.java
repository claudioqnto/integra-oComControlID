/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integ.modulointegracao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.integ.modulointegracao.exception.InvalidSessionException;
import org.json.JSONObject;

/**
 *
 * @author CLÁUDIO
 */
public class SettingsReaderBiometric {

    /**
     * Método que seta a configuração de hora no leitor biométirco de acordo com
     * a hora da estação onde está sendo executado a aplicação.
     *
     * @return
     */
    public String setupClock(AccessReader acessReader) throws IOException, InvalidSessionException {
        URL url;
        try {
            url = new URL("http://" + acessReader.ip + "/set_system_time.fcgi?session=" + acessReader.getRetorno());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type", "application/json");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            String where = String.format("{\"day\":%S,\"month\":%s,\"year\":%s,\"hour\":%s,\"minute\":%s,\"second\":%s}", Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.MONTH) + 1, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND)); //;
            JSONObject obj = new JSONObject(where);
            String json = obj.toString();
            os.write(json.getBytes());
            acessReader.verifySession(conn);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            System.out.println("Hor\u00e1rio atualizado.");
        } catch (MalformedURLException ex) {
            Logger.getLogger(SettingsReaderBiometric.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
