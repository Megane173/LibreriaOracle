/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DAOs.Autor;
import DAOs.Editorial;
import DAOs.Libro;
import DAOs.Pais;
import DAOs.TablasMetaDatos;
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
    
    private Connection conn= new ConnectionOracle().getConn();;
    private GenericDAO gL = new GenericDAO(Libro.class, conn);
    private GenericDAO gA = new GenericDAO(Autor.class, conn);
    private GenericDAO gE = new GenericDAO(Editorial.class, conn);
    private GenericDAO gP = new GenericDAO(Pais.class, conn);
    private GenericDAO gTMD = new GenericDAO(TablasMetaDatos.class, conn);
    
    public LibreriaRepository(){}
    
    //-------------------------------------------------------------------------
    //Metodos que usan SELECT
    public List<Libro> getAllBooks(){
        
        String sql="SELECT idLIBRO AS \"idLIBRO\", isbnLIBRO AS \"isbnLIBRO\",";
        sql+=" nombreLIBRO AS \"nombreLIBRO\", anioPublicacionLIBRO \"anioPublicacionLIBRO\",";
        sql+=" generoLIBRO AS \"generoLIBRO\", idiomaLIBRO \"idiomaLIBRO\",";
        sql+=" descripcionLIBRO AS \"descripcionLIBRO\" FROM libro";
        return gL.ejecutarQuery0(sql, new Object[0]);
    }
    
    public List<Libro> getLibroByISBN(Object[] parametros){
        
        String sql="SELECT idLIBRO FROM LIBRO";
        sql+=" WHERE isbnLIBRO=?";
        return gL.ejecutarQuery0(sql, parametros);
    }
    
    public List<TablasMetaDatos> getTablesNames(){
        String sql="SELECT table_name AS \"table_name\" FROM user_tables";
        
        return gTMD.ejecutarQuery0(sql, new Object[0]);
    }
    
    public List<Pais> getAllPais(){
        
        String sql="SELECT idPais AS \"idPAIS\", nombrePAIS AS \"nombrePAIS\",";
        sql+=" sufijoTelefonicoPAIS AS \"sufijoTelefonicoPAIS\", descPAIS AS \"descPAIS\"";
        sql+=" FROM Pais";
        return gP.ejecutarQuery0(sql, new Object[0]);
    }
    
    public List<Editorial> getIdEditorialByName(Object[] parametros){
        
        String sql="SELECT idEDITORIAL AS \"idEDITORIAL\" FROM Editorial";
        sql+=" WHERE UPPER(nombreEDITORIAL)=UPPER(?)";
        
        return gE.ejecutarQuery0(sql, parametros);
    }
    
    public List<Autor> getIdAutorByName(Object[] parametros){
        
        String sql="SELECT idAUTOR AS \"idAUTOR\" FROM Autor";
        sql+=" WHERE UPPER(nombreAUTOR)=UPPER(?)";
        
        return gA.ejecutarQuery0(sql, parametros);
    }
    
    public List<Editorial> getAllEditorial(){
        
        String sql="SELECT idEDITORIAL AS \"idEDITORIAL\", nombreEDITORIAL AS \"nombreEDITORIAL\",";
        sql+=" anioFundadoEDITORIAL AS \"anioFundadoEDITORIAL\", paisSedeCentralEDITORIAL AS \"paisSedeCentralEDITORIAL\"";
        sql+=" FROM Editorial";
        return gE.ejecutarQuery0(sql, new Object[0]);
    }
    
    public List<Autor> getAllAutor(){
        
        String sql="SELECT idAUTOR AS \"idAUTOR\", nombreAUTOR AS \"nombreAUTOR\",";
        sql+=" nacionalidadAUTOR AS \"nacionalidadAUTOR\", anioNacimientoAUTOR AS \"anioNacimientoAUTOR\",";
        sql+=" descripcionAUTOR AS \"descripcionAUTOR\"";
        sql+=" FROM Autor";
        return gA.ejecutarQuery0(sql, new Object[0]);
    }
    
    public List<Libro> searchBookByAuthor(String authorName) throws RuntimeException {

        String sql = "SELECT * FROM LIBRO WHERE idLIBRO = ";
        sql += "(SELECT idLIBRO FROM LIBROXAUTOR WHERE ";
        sql += "idAUTOR= (SELECT idAUTOR FROM AUTOR WHERE ";
        sql += "nombreAUTOR = ?))";
        
        Object[] parametros={authorName};
        
        return gL.ejecutarQuery0(sql, parametros);
    }
    
    //-------------------------------------------------------------------------
    //Metodos que NO usan SELECT
    public int insertBook(Object[] parametros){
        
        String sql="INSERT INTO LIBRO ";
        sql+="(isbnLibro, nombreLibro, anioPublicacionLibro, generoLibro, idiomaLibro, descripcionLibro)";
        sql+=" VALUES (?,?,?,?,?,?)";

        return gL.ejecutarQuery1(sql, parametros);
    }
    
    public int insertLibroXAUTOR(Object[] parametros){
        
        String sql="INSERT INTO LIBRO ";
        sql+="(idLibro, idAutor)";
        sql+=" VALUES (?,?)";
        
        return gL.ejecutarQuery1(sql, parametros);
    }
    
    public int insertAuthor(Object[] parametros){
        
        String sql="INSERT INTO Autor ";
        sql+="(nombreAUTOR, nacionalidadAUTOR, anioNacimientoAUTOR, descripcionAUTOR)";
        sql+=" VALUES (?,?,?,?)";

        return gL.ejecutarQuery1(sql, parametros);
    }
    
    public int insertEditorial(Object[] parametros){
        
        String sql="INSERT INTO Editorial ";
        sql+="(nombreEDITORIAL, aniofundadoeditorial, paisSedeCentralEDITORIAL)";
        sql+=" VALUES (?,?,?)";

        return gL.ejecutarQuery1(sql, parametros);
    }
    
    public int insertPais(Object[] parametros){
        
        String sql="INSERT INTO Pais ";
        sql+="(nombrePAIS, sufijoTelefonicoPais, descPais)";
        sql+=" VALUES (?,?,?)";

        return gL.ejecutarQuery1(sql, parametros);
    }

    
}
