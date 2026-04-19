/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAOs.Autor;
import DAOs.ColumnaMetaDatos;
import DAOs.Editorial;
import DAOs.Libro;
import DAOs.Pais;
import DAOs.TablaMetaDatos;
import DAOs.TableModel;
import Repository.LibreriaRepository;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class LibreriaService {

    private LibreriaRepository lr = new LibreriaRepository();

    
    //Funciones que devuelven entidades!
    public List<Libro> searchBookByAuthor(String authorName) throws RuntimeException {

        return lr.searchBookByAuthor(authorName);
    }
    public List<Libro> getAllBooks() throws RuntimeException {

        return lr.getAllBooks();
    }
    public List<Autor> getAllAutor() throws RuntimeException {

        return lr.getAllAutor();
    }
    public List<Editorial> getAllEditorial() throws RuntimeException {

        return lr.getAllEditorial();
    }
    public List<Pais> getAllPais() throws RuntimeException {

        return lr.getAllPais();
    }
    
    public String[] getTablesNames(){
        List<TablaMetaDatos> resultados= lr.getTablesNames();
        
        String[] tablesNames = new String[resultados.size()];
        for(int i=0; i<resultados.size(); i++){
            tablesNames[i]=resultados.get(i).getTable_name();
        }
        return tablesNames;
    }
    
    //Funciones que no devuelven ENTIDADES!
    public String insertBook(Object[] parametros, Object[] autores){
        
        String resultado="";
        if(parametros[7].getClass()==String.class){
            
            Object[] nombreEditorial={parametros[7]};

            List<Editorial> editorial=lr.getIdEditorialByName(nombreEditorial);
            if(!editorial.isEmpty()){
                parametros[7]=editorial.get(0).getIdEDITORIAL();
            }
            else{
                throw new RuntimeException("No se encontro una Editorial con tal ID");
            }
        }
        
        //Se inserta el libro
        if(lr.insertBook(parametros)!=0){
            resultado="Insertado del libro EXITOSO";
        }else{
            resultado="El Insertado del libro FRACASO";
        }
        
        //Se obtiene ID de libro insertado
        Object[] isbnLibro={parametros[0]};
        Libro libro = lr.getLibroByISBN(isbnLibro).get(0);
        
        //Parametros de la segunda consulta
        Object[] parQuery2={libro.getIdLIBRO(), };
        for(int i=0; i<autores.length; i++){
            
            if(autores[i].getClass()==String.class){
            
                Object[] nombreAutor={autores[i]};

                List<Autor> autor=lr.getIdAutorByName(nombreAutor);
                if(!autor.isEmpty()){
                    
                    autores[i]=autor.get(0).getIdAUTOR();
                }
                else{
                    throw new RuntimeException("No se encontro un Autor con tal ID");
                }
            }
            if(lr.insertLibroXAUTOR(parametros)!=0){
                resultado+="\n-El insertado del libro por el autor: "+autores[i]+" fue EXITOSO";
            }else{
                resultado+="\n-Insertado del libro por el autor: "+autores[i]+" FRACASO";
            }
        }
        return resultado;
    }
    
    public String insertAutor(Object[] parametros){
        
        if(lr.insertAuthor(parametros)!=0){
            return "Insercion de autor exitosa";
        }else{
            return "Insercion de autor fallida";
        }
    }
    
    public String insertEditorial(Object[] parametros){
        
        if(lr.insertEditorial(parametros)!=0){
            return "Insercion de editorial exitosa";
        }else{
            return "Insercion de editorial fallida";
        }
    }
    
    public String insertPais(Object[] parametros){
        
        if(lr.insertPais(parametros)!=0){
            return "Insercion de pais exitosa";
        }else{
            return "Insercion de pais fallida";
        }
    }
    
    public String insertLibro(Object[] parametros){
        
        if(lr.insertLibro(parametros)!=0){
            return "Insercion de libro exitosa";
        }else{
            return "Insercion de libro fallida";
        }
    }
    
    //Funciones mas especificas
    
    
    public List<ColumnaMetaDatos> getColumns(String tabla){
        
        return lr.getColumnsInfo(new Object[]{tabla});
    }
    
    //Validaciones
    public String validarLibro(Object[] libro){
        
//        for(Object a:libro){
//            System.out.println(a);
//        }
        if(libro[0]==null){
            return "Error: El nombre es obligatorio";
        }else if(libro[2]==null){
            return "Error: El año del libro es obligatorio";
        }else if(libro[2].getClass()!=Integer.class){
            return "Error: El año de publicación debe ser un numero entero"; 
        }else if(libro[4]==null){
            return "Error: El idioma del libro es obligatorio";
        }else{
            return "";
        }
    }
    
    
    //Funciones que ayudan a las validaciones
    public boolean estaVacio(String input){
        
        input=input.trim();
        if(input.isEmpty()){
            return true;
        }
        return false;
    }
    
    public boolean esInt(String input){
        
        try{
            Integer.parseInt(input);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
//    public String[] getColumns(List<TableModel> resultados){
//        
//        
//        Field [] columns=resultados.get(0).getClass().getDeclaredFields();
//        String aux;
//        String []columnsString=new String[columns.length];
//        
//        for(int i=0; i<columns.length; i++){
//            aux=columns[i].getName();
//            aux=aux.substring((aux.lastIndexOf(".")+1));
//            columnsString[i]=aux;
//        }
//                
//        return columnsString;
//    }
}
