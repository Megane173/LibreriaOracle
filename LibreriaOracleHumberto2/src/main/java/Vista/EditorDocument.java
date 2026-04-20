/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author PC1Ry
 */
public class EditorDocument extends PlainDocument{
    
    private int limite;
    private boolean isInt;
    
    public EditorDocument(int limite, boolean isInt){
        this.limite=limite;
        this.isInt=isInt;
    }
    
    @Override
    public void insertString(int offs, String str, AttributeSet e) throws BadLocationException{
        
        
        if(str != null && (getText(0, getLength()).getBytes().length+str.getBytes().length)<=limite){
            if(isInt){
                if(str.matches("\\d*")){
                    super.insertString(offs, str, e);
                }
            }
            else{
                super.insertString(offs, str, e);
            }
        }
    }
    
}
