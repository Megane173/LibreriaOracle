/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Repository.LibreriaRepository;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class LibreriaService {

    private LibreriaRepository lr = new LibreriaRepository();

    public String searchBookByAuthor(String authorName) throws SQLException {

        String sql = "SELECT * FROM LIBRO WHERE idLIBRO = ";
        sql += "(SELECT idLIBRO FROM LIBROXAUTOR WHERE ";
        sql += "idAUTOR= (SELECT idAUTOR FROM AUTOR WHERE ";
        sql += "nombreAUTOR = ?))";
        
        ResultSet result= lr.ejecutarQuery(sql);
        String resultado="";
        
        while(result.next()){
            
            resultado+="Libro: "+result.getString("nombreLIBRO");
            resultado+="Año publicacion: "+result.getInt("anioPublicacionLIBRO");
            resultado+="\nIdioma: "+result.getString("idiomaLIBRO");
            resultado+="Editorial: "+result.getString("editorialLIBRO");
            resultado+="\nDescripción: "+result.getString("descLIBRO");
        }
        
        return resultado;
    }
    
    public String searchBookByEditorial(String editorialName) throws SQLException {

        String sql = "SELECT * FROM LIBRO WHERE idLIBRO = ";
        sql += "(SELECT idLIBRO FROM LIBROXEDITORIAL WHERE ";
        sql += "idEDITOR= (SELECT idEDITORIAL FROM EDITORIAL WHERE ";
        sql += "nombreEDITORIAL = ?))";
        
        ResultSet result= lr.ejecutarQuery(sql);
        String resultado="";
        
        while(result.next()){
            
            resultado+="Libro: "+result.getString("nombreLIBRO");
            resultado+="Año publicacion: "+result.getInt("anioPublicacionLIBRO");
            resultado+="\nIdioma: "+result.getString("idiomaLIBRO");
            resultado+="Editorial: "+result.getString("editorialLIBRO");
            resultado+="\nDescripción: "+result.getString("descLIBRO");
        }
        
        return resultado;
    }
}
