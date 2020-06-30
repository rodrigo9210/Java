/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pfinal;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Rodrigo
 */
public class AlertBox {
    
    public static void display(String titulo, String mensaje){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(titulo);
        window.setMinWidth(250);
        window.setMinHeight(200);
        
        Label label1 = new Label(mensaje);
        Button boton = new Button("OK");
        boton.setOnAction(e -> window.close());
        
        VBox v1 = new VBox(20);
        v1.getChildren().addAll(label1,boton);
        v1.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(v1);
        window.setScene(scene);
        window.showAndWait();
    }
    
}
