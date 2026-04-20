/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

/**
 *
 * @author Usuario
 */
public class Editorial implements TableModel{
    
    private int idEDITORIAL;
    private String nombreEDITORIAL;
    private int anioFundadoEDITORIAL;
    private Integer paisSedeCentralEDITORIAL;
    
    public Editorial(){}

    public int getIdEDITORIAL() {
        return idEDITORIAL;
    }

    public void setIdEDITORIAL(int idEDITORIAL) {
        this.idEDITORIAL = idEDITORIAL;
    }

    public String getNombreEDITORIAL() {
        return nombreEDITORIAL;
    }

    public void setNombreEDITORIAL(String nombreEDITORIAL) {
        this.nombreEDITORIAL = nombreEDITORIAL;
    }

    public int getAnioFundadoEDITORIAL() {
        return anioFundadoEDITORIAL;
    }

    public void setAnioFundadoEDITORIAL(int anioFundadoEDITORIAL) {
        this.anioFundadoEDITORIAL = anioFundadoEDITORIAL;
    }

    public int getPaisSedeCentralEDITORIAL() {
        return paisSedeCentralEDITORIAL;
    }

    public void setPaisSedeCentralEDITORIAL(int paisSedeCentralEDITORIAL) {
        this.paisSedeCentralEDITORIAL = paisSedeCentralEDITORIAL;
    }

    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-idEDITORIAL=").append(idEDITORIAL);
        sb.append("\n nombre=").append(nombreEDITORIAL);
        sb.append("\n añoFundado=").append(anioFundadoEDITORIAL);
        sb.append(", paisSedeCentral=").append(paisSedeCentralEDITORIAL);
        return sb.toString();
    }
    
    @Override
    public Object[] getAtributosArray(){
        return new Object[] {idEDITORIAL, nombreEDITORIAL, anioFundadoEDITORIAL, paisSedeCentralEDITORIAL};
    }
    
}
