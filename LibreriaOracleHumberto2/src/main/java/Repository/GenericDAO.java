/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author PC1Ry
 */
public class GenericDAO<T> {

    private Class<T> claseEntidad;
    private Connection conn;

    public GenericDAO(Class<T> claseEntidad, Connection conn) {
        this.claseEntidad = claseEntidad;
        this.conn = conn;
    }
    
//    public ResultSet getMetadatos(String sql, Object[] parametros){
//        
//        try(PreparedStatement st = conn.prepareStatement(sql);){
//            
//            for(int i=0; i<parametros.length; i++){
//                st.setObject(i+1, parametros[i]);
//            }
//            try(ResultSet rs=st.executeQuery();){
//                return rs;
//            }
//        }catch(Exception e){
//            throw new RuntimeException("Error obteniendo metadatos de la BD: \n"+e.getMessage(),e);
//        }
//    }

    //Funcion para ejecutar consultas SELECT
    public List<T> ejecutarQuery0(String sql, Object[] parametros) {

        List<T> resultado = new ArrayList();
        try (PreparedStatement st = conn.prepareStatement(sql);) {

            for (int i = 0; i < parametros.length; i++) {
                st.setObject(i + 1, parametros[i]);
            }

            try (ResultSet rs = st.executeQuery();) {

                ResultSetMetaData rsmd = rs.getMetaData();

                while (rs.next()) {
                    T objeto = claseEntidad.getDeclaredConstructor().newInstance();

                    for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {

                        setearAtributo(objeto, rsmd.getColumnLabel(i), rs.getObject(i));
                    }
                    resultado.add(objeto);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return resultado;
    }
    
    //Funcion para ejecutar consultas INSERT, UPDATE y DELETE
    public int ejecutarQuery1(String sql, Object[] parametros){
        
        try(PreparedStatement st=conn.prepareStatement(sql);){
            
            System.out.println(parametros.length);
            for(int i=0; i<parametros.length; i++){
                System.out.println(i);
                st.setObject(i+1, parametros[i]);
            }
            
            int exitoso=st.executeUpdate();
            
            return exitoso;
            
            
        }catch(Exception e){
            throw new RuntimeException("Error al realizar una modificación en la Base de datos: \n"+e.getMessage(),e);
        }
    }

    public void setearAtributo(T objeto, String nombreAtributo, Object valor) {

        try {
            
            Field field = claseEntidad.getDeclaredField(nombreAtributo);
            valor= verificarTipoVariable(field.getType(), valor);
            field.setAccessible(true);
            field.set(objeto, valor);
        } catch (Exception e) {
            throw new RuntimeException("Error en el seteo de un atributo a su objeto: "+nombreAtributo, e);
        }
    }
    
    public Object verificarTipoVariable(Class tipoAtributoObjeto, Object valor){
        
        if(valor==null){
            return null;
        }
        
        if((tipoAtributoObjeto==int.class || tipoAtributoObjeto==Integer.class)&& valor.getClass()== BigDecimal.class){
            return ((BigDecimal) valor).intValue();
        }
        return valor;
    }
}
