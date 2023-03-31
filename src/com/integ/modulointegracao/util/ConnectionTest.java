/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integ.modulointegracao.util;

import java.sql.SQLException;
import com.integ.modulointegracao.AccessReader;

/**
 *
 * @author CLAUDIO
 */
public class ConnectionTest {
    public static void main(String[] args) throws SQLException {
SetupDataDb c = new SetupDataDb();
c.update("", "", "");

c.buscarDados();


    }
}


