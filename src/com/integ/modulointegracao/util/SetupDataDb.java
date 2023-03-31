/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integ.modulointegracao.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class SetupDataDb {

    private String login;
    private String senha;
    private String ip;
    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }
    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }
    /**
     * @return the Senha
     */
    public String getSenha() {
        return senha;
    }
    /**
     * @param Senha the Senha to set
     */
    public void setSenha(String Senha) {
        this.senha = Senha;
    }
    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }
    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }
    public void update(String login, String senha, String ip) throws SQLException {
        Connection con = new ConnectionFactory().getConnect();
        String query = "update dados set login=?,senha=?,ip=? where id=0";
        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setString(1, login);
        pstm.setString(2, senha);
        pstm.setString(3, ip);
        pstm.execute();
        pstm.close();
        con.close();
    }
    public  static void apagarLog() {
        String caminho = String.format("%s\\%s", System.getProperty("user.dir"), "gestor.log");
        System.out.println(caminho);
        Writer out;
        try {
            out = new FileWriter(caminho);
            out.write("");
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(SetupDataDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void buscarDados() throws SQLException {
        Connection con = new ConnectionFactory().getConnect();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM PUBLIC.DADOS");
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            this.login = rs.getString("login");
            this.senha = rs.getString("senha");
            this.ip = rs.getString("ip");

           // System.out.println(this.getLogin() + " " + this.getSenha() + " " + this.getIp());
        }

    }
}
