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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC1Ry
 */
public class LibreriaRepository {
    
    private Connection conn;
    
    public LibreriaRepository(){
        conn= new ConnectionOracle().getConn();
    }
    
    
    public Object ejecutarQuery(String sqlQuery, Object[] parametros, String identidad) throws SQLException{
        
        PreparedStatement st = conn.prepareStatement(sqlQuery);
        
        for(int i=0; i<parametros.length; i++){
            st.setObject(i+1, parametros[i]);
        }
        boolean isSelect = st.execute(sqlQuery);
        if(isSelect){
            ResultSet rs=st.getResultSet();
            ArrayList<Object> resultado= new ArrayList();
            while(rs.next()){
                
                
            }
        }
        else{
            int rows= st.getUpdateCount();
            System.out.println("Filas afectadas: "+rows);
        }
    }
}
