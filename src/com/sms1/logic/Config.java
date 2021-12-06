/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sms1.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author adi_sptro
 */
public class Config {

    public static Connection mysqlConf;
    final static String file = "src/com/sms1/logic/config";
    static String line = null;
    static ArrayList<String> fileContents = new ArrayList<>();

    public static String getHostname() {
        try {
            FileReader fReader = new FileReader(file);
            BufferedReader bfReader = new BufferedReader(fReader);
            while ((line = bfReader.readLine()) != null) {
                fileContents.add(line);
            }
            bfReader.close();
            String hostname = fileContents.get(7);
//            System.out.println(hostname.substring(11));
            return hostname.substring(11);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return "";
        
    }
    
    public static String getDatabase(){
         try {
            FileReader fReader = new FileReader(file);
            BufferedReader bfReader = new BufferedReader(fReader);
            while ((line = bfReader.readLine()) != null) {
                fileContents.add(line);
            }
            bfReader.close();
            String hostname = fileContents.get(10);
//            System.out.println(hostname.substring(11));
            return hostname.substring(11);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return "";
    }
    
    public static String getUsername(){
         try {
            FileReader fReader = new FileReader(file);
            BufferedReader bfReader = new BufferedReader(fReader);
            while ((line = bfReader.readLine()) != null) {
                fileContents.add(line);
            }
            bfReader.close();
            String hostname = fileContents.get(8);
//            System.out.println(hostname.substring(11));
            return hostname.substring(11);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return "";
    }
    
    public static String getPassword(){
         try {
            FileReader fReader = new FileReader(file);
            BufferedReader bfReader = new BufferedReader(fReader);
            while ((line = bfReader.readLine()) != null) {
                fileContents.add(line);
            }
            bfReader.close();
            String hostname = fileContents.get(9);
//            System.out.println(hostname.substring(11));
            return hostname.substring(11);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return "";
    
    }

    public static Connection MyConfig() throws SQLException {
        try {
            String uri = "jdbc:mysql://"+getHostname()+"/"+getDatabase();
            String user = getUsername();
            String pass = getPassword();
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            mysqlConf = DriverManager.getConnection(uri, user, pass);
        } catch (SQLException e) {
            MyFunction.showErrorMessage(e.getMessage(), "Error!");
        }
        return mysqlConf;
    }

}
