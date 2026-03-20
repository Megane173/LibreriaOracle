/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.libreriaoracle.BD;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author PC1Ry
 */

public class ConnectionOracle {
    
    
    private Connection conn = null;
    private String url, user, pass;
    
    public ConnectionOracle(){
        conectar();
    }
    
    public Connection getConn(){
        return conn;
    }
    
    public void conectar(){
        
        try{
            Class.forName("jdbc:oracle:thin:@//192.168.1.14:1521/ORCLPDB1");
            url="jdbc:oracle:thin:@//192.168.1.14:1521/ORCLPDB1";
            user="Mimir";
            pass=System.getenv("mimirPass");
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conection exitosa");
        }
        catch(Exception e){ 
            System.out.println("Error: ");
            System.out.println("-------------------");
            System.out.println(e.getMessage());
        }
    }
    
    public void desconectar(){
        
        try{
            conn.close();
        }
        catch(Exception e){
            System.out.println("Error: ");
            System.out.println("-------------------");
            System.out.println(e.getMessage());
        }
    }
}
