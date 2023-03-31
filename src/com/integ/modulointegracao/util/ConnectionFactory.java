package com.integ.modulointegracao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Cláudio Nascimento (21) 979059570
 */
public class ConnectionFactory {
    String caminho = String.format("%s//%s", System.getProperty("user.dir"), "gestor");    
    
    final String URL = "jdbc:hsqldb:file:"+caminho+";hsqldb.sqllog=0";
    final String USER = "SA";
    final String PASSWORD = "12345";

public Connection getConnect(){      
        try {       
            return  DriverManager.getConnection(URL, USER, PASSWORD);            
        } catch (SQLException ex) {
            System.out.println("Erro de conexão");
            System.out.println(caminho);
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
