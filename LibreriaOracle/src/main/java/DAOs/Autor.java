/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

/**
 *
 * @author Usuario
 */
public class Autor implements TableModel{
    
    private int idAUTOR;
    private String nombreAUTOR;
    private int nacionalidadAUTOR;
    private int anioNacimientoAUTOR;
    private String descripcionAUTOR;
    
    public Autor(){}

    public int getIdAUTOR() {
        return idAUTOR;
    }

    public void setIdAUTOR(int idAUTOR) {
        this.idAUTOR = idAUTOR;
    }

    public String getNombreAUTOR() {
        return nombreAUTOR;
    }

    public void setNombreAUTOR(String nombreAUTOR) {
        this.nombreAUTOR = nombreAUTOR;
    }

    public int getNacionalidadAUTOR() {
        return nacionalidadAUTOR;
    }

    public void setNacionalidadAUTOR(int nacionalidadAUTOR) {
        this.nacionalidadAUTOR = nacionalidadAUTOR;
    }

    public int getAnioNacimientoAUTOR() {
        return anioNacimientoAUTOR;
    }

    public void setAnioNacimientoAUTOR(int anioNacimientoAUTOR) {
        this.anioNacimientoAUTOR = anioNacimientoAUTOR;
    }

    public String getDescripcionAUTOR() {
        return descripcionAUTOR;
    }

    public void setDescripcionAUTOR(String descripcionAUTOR) {
        this.descripcionAUTOR = descripcionAUTOR;
    }

    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("-idAUTOR=").append(idAUTOR);
        sb.append("\n nameAUTOR=").append(nombreAUTOR);
        sb.append("\n nacionalidad=").append(nacionalidadAUTOR);
        sb.append(", añoNacimiento=").append(anioNacimientoAUTOR);
        sb.append("\n descripcion=").append(descripcionAUTOR);
        return sb.toString();
    }
    
    @Override
    public Object[] getAtributosArray(){
        return new Object[] {idAUTOR, nombreAUTOR, nacionalidadAUTOR, anioNacimientoAUTOR, descripcionAUTOR};
    }
    
    
}
