/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

/**
 *
 * @author Usuario
 */
public class Libro implements TableModel{
    
    private int idLIBRO;
    private String isbnLIBRO;
    private String nombreLIBRO;
    private int anioPublicacionLIBRO;
    private String generoLIBRO;
    private String idiomaLIBRO;
    private String descripcionLIBRO;
    private int idEditorialLIBRO;

    public Libro() {
    }

    public int getIdLIBRO() {
        return idLIBRO;
    }

    public void setIdLIBRO(int idLIBRO) {
        this.idLIBRO = idLIBRO;
    }

    public String getIsbnLIBRO() {
        return isbnLIBRO;
    }

    public void setIsbnLIBRO(String isbnLIBRO) {
        this.isbnLIBRO = isbnLIBRO;
    }

    public String getNombreLIBRO() {
        return nombreLIBRO;
    }

    public void setNombreLIBRO(String nombreLIBRO) {
        this.nombreLIBRO = nombreLIBRO;
    }

    public int getAnioPublicacionLIBRO() {
        return anioPublicacionLIBRO;
    }

    public void setAnioPublicacionLIBRO(int anioPublicacionLIBRO) {
        this.anioPublicacionLIBRO = anioPublicacionLIBRO;
    }

    public String getGeneroLIBRO() {
        return generoLIBRO;
    }

    public void setGeneroLIBRO(String generoLIBRO) {
        this.generoLIBRO = generoLIBRO;
    }

    public String getIdiomaLIBRO() {
        return idiomaLIBRO;
    }

    public void setIdiomaLIBRO(String idiomaLIBRO) {
        this.idiomaLIBRO = idiomaLIBRO;
    }

    public String getDescripcionLIBRO() {
        return descripcionLIBRO;
    }

    public void setDescripcionLIBRO(String descripcionLIBRO) {
        this.descripcionLIBRO = descripcionLIBRO;
    }

    public int getIdEditorialLIBRO() {
        return idEditorialLIBRO;
    }

    public void setIdEditorialLIBRO(int idEditorialLIBRO) {
        this.idEditorialLIBRO = idEditorialLIBRO;
    }
    
    
    
    @Override
    public String toString() {
        return "-idLIBRO=" + idLIBRO + ", isbnLIBRO=" + isbnLIBRO 
                + "\n nombreLibro=" + nombreLIBRO + ", añoPublicacion=" + anioPublicacionLIBRO 
                + "\n generoLIBRO=" + generoLIBRO + ", idiomaLIBRO=" + idiomaLIBRO 
                + "\n descripcionLIBRO=" + descripcionLIBRO;
    }
    
    @Override
    public Object[] getAtributosArray(){
        return new Object[] {idLIBRO,isbnLIBRO, nombreLIBRO,anioPublicacionLIBRO, generoLIBRO, idiomaLIBRO, descripcionLIBRO};
    }
    
    
}
