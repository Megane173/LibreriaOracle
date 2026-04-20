/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

/**
 *
 * @author PC1Ry
 */
public class ColumnaMetaDatos {
    
    private String column_name;
    private int column_length;
    private String data_type;
    
    public ColumnaMetaDatos(){}

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public int getColumn_length() {
        return column_length;
    }

    public void setColumn_length(int column_length) {
        this.column_length = column_length;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }
    
}
