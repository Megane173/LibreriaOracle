/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

/**
 *
 * @author PC1Ry
 */
public class Pais implements TableModel{
    
    
    private int idPAIS;
    private String nombrePAIS;
    private String sufijoTelefonicoPAIS;
    private String descPAIS;
    
    public Pais(){}

    @Override
    public String toString() {
        return "-idPAIS=" + idPAIS + ", nombrePAIS=" + nombrePAIS + 
               "\n sufijoTelefonicoPais=" + sufijoTelefonicoPAIS + 
               "\n descPais=" + descPAIS;
    }
    
    @Override
    public Object[] getAtributosArray(){
        return new Object[] {idPAIS, nombrePAIS, sufijoTelefonicoPAIS, descPAIS};
    }
    
}
