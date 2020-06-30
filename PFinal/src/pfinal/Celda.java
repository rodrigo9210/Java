/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pfinal;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Rodrigo
 */
public class Celda extends Label{
 
    //ATRIBUTOS
    private int val;
    private boolean valid;
    private boolean editable;
     
    //CONSTRUCTOR
    public Celda(int val){
        this.val=val;
        this.editable=true;
        super.setStyle("-fx-border-color: black;");
        super.setStyle("-fx-background-color: white;");
        super.setMinWidth(50);
        super.setMinHeight(50);
        super.setFont(Font.font("Arial", 20));
        super.setAlignment(Pos.CENTER);
        super.setText(String.valueOf(val));
    }
     
    //GETs y SETs
    public int getVal(){
        return val;
    }
     
    public void setVal(int val){
        setText(String.valueOf(val));
        this.val=val;
    }
     
    public boolean getValid(){
        return valid;
    }
     
    public void setValidT(){
        valid=true;
    }
     
    public void setValidF(){
        valid=false;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    
    //METODOS y FUNCIONES
    public boolean equals(Celda otra){
        boolean res;
        if(otra.getVal()==val)
            res=true;
        else
            res=false;
        return res;
    }
     
    public int compareTo(Celda otra){
        int res;
        if(otra.getVal()==val)
            res=0;
        else
            if(otra.getVal()>val)
                res=-1;
            else
                res=1;
        return res;
    }
     
    public String toString(){
        String string;
        string=String.valueOf(val);
        return string;
    }
     
}//CLASS
