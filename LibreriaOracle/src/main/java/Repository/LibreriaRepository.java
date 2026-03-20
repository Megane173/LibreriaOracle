/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import com.mycompany.libreriaoracle.BD.ConnectionOracle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author PC1Ry
 */
public class LibreriaRepository {
    
    private Connection conn;
    
    public LibreriaRepository(){
        conn= new ConnectionOracle().getConn();
    }
    
    
    public ResultSet ejecutarQuery(String sqlQuery) throws SQLException{
        
        PreparedStatement st = conn.prepareStatement(sqlQuery);
        ResultSet rs = st.executeQuery(sqlQuery);
        return rs;
    }
}
