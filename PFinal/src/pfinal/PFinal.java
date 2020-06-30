/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pfinal;

import java.io.BufferedWriter;
import javafx.scene.image.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author Rodrigo
 */
public class PFinal extends Application {
    
    Stage window;
    Scene sceneInicio, sceneJuego, sceneNiveles, scenePuntos;
    
    File file;
    ArrayList<Jugador> listaOrd = new ArrayList<Jugador>();
    
    static Celda[][] tablero = new Celda[9][9];
    Jugador p1;
    
    public static void addTextLimiter(final TextField tf, final int maxLength) {
    tf.textProperty().addListener((final ObservableValue<? extends String> ov, final String oldValue, final String newValue) -> {
        if (tf.getText().length() > maxLength) {
            String s = tf.getText().substring(0, maxLength);
            tf.setText(s);
        }
        });
    }
    
    @Override
    public void start(Stage primaryStage) throws InterruptedException, FileNotFoundException {
        
        //final Stage window = new Stage();
        //Scene innerScene = new Scene(new Label("BIENVENIDO"));
        //window.setScene(innerScene);
        
        window = primaryStage;
        
        window.setTitle("SUDOKU");
                
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(0);
        grid.setVgap(1);
        grid.setPadding(new Insets(25,25,25,25));
        
        HBox hb[] = new HBox[9];
        //Celda[][] tablero = new Celda[9][9];
        
        
        //ESCENA INICIO
        
        VBox inicio = new VBox(20);
        inicio.setSpacing(5);
        inicio.setAlignment(Pos.CENTER);
        Button iniciar = new Button("INICIA");
        Label anuncio = new Label("Para iniciar ingresa tu nombre:");
        TextField jugador = new TextField();
        jugador.setMaxWidth(200);
        jugador.setAlignment(Pos.CENTER);
        inicio.getChildren().add(anuncio);
        inicio.getChildren().add(jugador);
        inicio.getChildren().add(iniciar);
        sceneInicio = new Scene(inicio,400,200);
        
        
        
        iniciar.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent e) {
               p1 = new Jugador(jugador.getText());
               window.setScene(sceneNiveles);
               window.setX(400);
               window.setY(100);
            }
        });
        
        //ESCENA DIFICULTADES
        Label select = new Label("Selecciona nivel de dificultad");
        Image facil = new Image("file:facil.png");
        Image medio = new Image("file:medio.png");
        Image dificil = new Image("file:dificil.png");
        
        ImageView ivF = new ImageView();
        ImageView ivM = new ImageView();
        ImageView ivD = new ImageView();
        
        ivF.setImage(facil);
        ivM.setImage(medio);
        ivD.setImage(dificil);
        
        ivF.setFitWidth(100);
        ivF.setPreserveRatio(true);
        
        ivM.setFitWidth(100);
        ivM.setPreserveRatio(true);
        
        ivD.setFitWidth(100);
        ivD.setPreserveRatio(true);
        
        Button fa = new Button("Facil");
        Button me = new Button("Medio");
        Button di = new Button("Dificil");
        
        Button cont = new Button("Continuar");
        cont.setDisable(true);
        
        cont.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent e) {
               llenaTablero(file); 
               window.setScene(sceneJuego);
               window.setX(350);
               window.setY(50);
            }
        });
        
        fa.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent e) {
               file=new File("completado.txt");
               p1.setNivel("FACIL");
               select.setText("Nivel Seleccionado: Facil");
               cont.setDisable(false);
            }
        });
        
        me.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent e) {
               file=new File("2.txt");
               p1.setNivel("MEDIO");
               select.setText("Nivel Seleccionado: Medio");
               cont.setDisable(false);
            }
        });
        
        di.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent e) {
               file=new File("3.txt");
               p1.setNivel("DIFICIL");
               select.setText("Nivel Seleccionado: Dificil");
               cont.setDisable(false);
            }
        });
        
        
        HBox h1 = new HBox(20);
        h1.getChildren().add(select);
        h1.setAlignment(Pos.CENTER);
        
        HBox hIma = new HBox(20);
        hIma.getChildren().addAll(ivF, ivM, ivD);
        hIma.setAlignment(Pos.CENTER);
        
        HBox hBot = new HBox(50);
        hBot.getChildren().addAll(fa, me, di);
        hBot.setAlignment(Pos.CENTER);
        
        HBox hCont = new HBox(2);
        hCont.getChildren().add(cont);
        hCont.setAlignment(Pos.CENTER);
        
        GridPane gridNivel = new GridPane();
        gridNivel.setAlignment(Pos.CENTER);
        gridNivel.setHgap(0);
        gridNivel.setVgap(20);
        gridNivel.setPadding(new Insets(25,25,25,25));
        
        gridNivel.add(h1, 0, 0);
        gridNivel.add(hIma, 0, 1);
        gridNivel.add(hBot, 0, 2);
        gridNivel.add(hCont, 0, 3);
        
        sceneNiveles = new Scene(gridNivel,400,300);
        
        //ESCENA JUEGO
        Label lFila = new Label("Fil: ");
        Label lCol = new Label("Col: ");
        Label lVal = new Label("Val: ");
        TextField cordX = new TextField();
        cordX.setMaxWidth(30);
        addTextLimiter(cordX,1);
        TextField cordY = new TextField();
        cordY.setMaxWidth(30);
        addTextLimiter(cordY,1);
        TextField valCord = new TextField();
        valCord.setMaxWidth(30);
        addTextLimiter(valCord,1);
        TextField res = new TextField();
        res.setMaxWidth(140);
        Label totalMov = new Label();
        Button asignVal = new Button("Asignar");
        Button revisar = new Button("Revisar");
        
        totalMov.setFont(Font.font("Arial", 15));
        totalMov.setTextAlignment(TextAlignment.CENTER);
        
        int i,j;
        for(i=0; i<9; i++)
        {
            hb[i]=new HBox(10);
            hb[i].setSpacing(1);
            for(j=0; j<9; j++)
            {
                tablero[i][j]=new Celda(0);
                Tooltip tt = new Tooltip();
                tt.setText(String.valueOf(i) + ", " + String.valueOf(j));
                tablero[i][j].setTooltip(tt);
                hb[i].getChildren().add(tablero[i][j]);
                if(j==2 || j==5)
                {
                    Separator sp = new Separator();
                    sp.setOrientation(Orientation.VERTICAL);
                    sp.setVisible(false);
                    hb[i].getChildren().add(sp);
                }
            }
            grid.add(hb[i], 0, i);
            if(i==2 || i==5 || i==8)
            {
                Separator sp = new Separator();
                sp.setOrientation(Orientation.HORIZONTAL);
                sp.setMinHeight(55);
                sp.setVisible(false);
                grid.add(sp, 0, i);
            }
        }
        
        Separator sp1 = new Separator();
        sp1.setOrientation(Orientation.VERTICAL);
        sp1.setVisible(false);
        Separator sp2 = new Separator();
        sp2.setOrientation(Orientation.VERTICAL);
        sp2.setVisible(false);
        Separator sp3 = new Separator();
        sp3.setOrientation(Orientation.VERTICAL);
        sp3.setVisible(false);
        Separator sp4 = new Separator();
        sp4.setOrientation(Orientation.VERTICAL);
        sp4.setVisible(false);
        
        HBox controles = new HBox();
        
        controles.getChildren().add(lFila);
        controles.getChildren().add(cordY);
        controles.getChildren().add(sp1);
        controles.getChildren().add(lCol);
        controles.getChildren().add(cordX);
        controles.getChildren().add(sp2);
        controles.getChildren().add(lVal);
        controles.getChildren().add(valCord);
        controles.getChildren().add(sp3);
        controles.getChildren().add(asignVal);
        controles.getChildren().add(sp4);
        controles.getChildren().add(revisar);
        controles.getChildren().add(res);
        controles.getChildren().add(totalMov);
        
        grid.add(controles, 0, i);
        
        //scene puntuacion
        Label puntos = new Label("MEJORES PUNTUACIONES");
        ListView lista = new ListView();
        VBox punt = new VBox(10);
        Button regresa = new Button("Reinicia");
        
        
        regresa.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent e) {
                int n,m,i,j;
                window.setScene(sceneInicio);

                n=tablero.length;
                m=tablero[0].length;
                for(i=0;i<n;i++)
                    for(j=0;j<m;j++){
                        tablero[i][j].setVal(0);
                        celdaNull(i, j);
                        celdaToF(i, j);
                        tablero[i][j].setEditable(true);
                        tablero[i][j].setText("");
                        tablero[i][j].setStyle("-fx-background-color: white;");
                        
                    }
                
                cordX.setText("");
                cordY.setText("");
                valCord.setText("");
                res.setText("");
                totalMov.setText("");
                
                listaOrd.clear();
            }
        });  
        
        punt.setAlignment(Pos.CENTER);
        punt.getChildren().addAll(puntos,lista,regresa);
        
        GridPane gridPuntos = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(0);
        grid.setVgap(1);
        grid.setPadding(new Insets(25,25,25,25));
        
        gridPuntos.add(punt, 0, 1);
        
        scenePuntos = new Scene(gridPuntos,200,400);
        
        asignVal.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent e) {
                p1.sumaMovimientos();
                totalMov.setText(String.valueOf(p1.getMovimientos()));
                String sX,sY,sV;
                int x,y,val;
                sX=cordY.getText();
                x=Integer.valueOf(sX);
                sY=cordX.getText();
                y=Integer.valueOf(sY);
                sV=valCord.getText();
                val=Integer.valueOf(sV);
                if(tablero[x][y].isEditable()){
                    tablero[x][y].setVal(val);
                    celdaToF(x,y);
                }
                else{
                    AlertBox.display("Error", "La casilla [" + x + ", " + y + "] NO es editable");
                    System.out.println("NO SE PUEDE EDITAR");
                }
            }
        });
        
        revisar.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent e) {
            p1.sumaMovimientos();    
            totalMov.setText(String.valueOf(p1.getMovimientos()));
            int n,m,i,j;
            boolean r;
            n=tablero.length;
            m=tablero[0].length;
            i=0;
            r=true;
             
           for(i=0;i<n;i++){
                
                for(j=0;j<m;j++){
                    if(tablero[i][j].getValid()==false){
                        r=false;
                        if(tablero[i][j].isEditable() && tablero[i][j].getVal()!=0){
                            tablero[i][j].setStyle("-fx-background-color: red;");
                        }
                    }
                    else if(tablero[i][j].getValid()==true && tablero[i][j].isEditable() && tablero[i][j].getVal()!=0){
                        tablero[i][j].setStyle("-fx-background-color: white;");
                    }
                }
            }
             
            if(!r)
                res.setText("Intentalo de nuevo");
            else{
                res.setText("Completado");
                listaOrd.add(p1);
                asignVal.disableProperty();
                window.setScene(scenePuntos);
                window.setX(400);
                window.setY(200);
                file =  new File("puntuaciones.txt");
                
                Scanner lec;
                String nombree, dific;
                int punt;
                
                try{
                    lec=new Scanner(file);
                    while(lec.hasNext()){
                        nombree=lec.next();
                        dific=lec.next();
                        punt=lec.nextInt();
                        listaOrd.add(new Jugador(nombree, dific, punt));
                    }
                    
                    lec.close();
                    Collections.sort(listaOrd);
                    
                    //almacena nuevo valor en archivo
                    FileWriter fw = new FileWriter("puntuaciones.txt", true);
                    BufferedWriter writer = new BufferedWriter(fw);
                    writer.write(p1.toString());
                    writer.newLine();
                    writer.close();
                    
                    //agrega los elementos ordenados
                    for (j = 0; j < listaOrd.size(); j++) {
                        lista.getItems().add(listaOrd.get(j).toString());
                    }
                }
                catch(FileNotFoundException p){
                } catch (IOException ex) {
                    Logger.getLogger(PFinal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }   
            }
        });
        
        sceneJuego = new Scene(grid);
        
        
        
        
        //inicia simulacion
        window.setScene(sceneInicio);
        window.show();
      
    }
    
    //METODOS y FUNCIONES
    
    public static void llenaTablero(File file){
        Scanner lec;
        int i,j,fila,col,val,n,m,k;
        
         
        //LECTURA de DATOS
        try{
            lec=new Scanner(file);
            while(lec.hasNext()){
                fila=lec.nextInt();
                col=lec.nextInt();
                val=lec.nextInt();
                tablero[fila][col].setVal(val);
            }
            lec.close();
        }
        catch(Exception e){
        }
        
         
        //ASIGNACION de VALORES para las CELDAS
        n=tablero.length;
        m=tablero[0].length;
        for(i=0;i<n;i++)
            for(j=0;j<m;j++){
                celdaNull(i, j);
                celdaToF(i, j);
                if(tablero[i][j].getVal()==0)
                    tablero[i][j].setText("");
                else if (tablero[i][j].getVal()!=0){
                    tablero[i][j].setEditable(false);
                    tablero[i][j].setStyle("-fx-background-color: yellow;");
                }
        }
     }
    
    public static boolean checaCol(int fila, int col){
        int n,i,val;
        boolean res;
        n=tablero.length;
        res=true;
        i=0;
        val=tablero[fila][col].getVal();
        while(i<n && res){
            if(tablero[i][col].getVal()==val && i!=fila)
                res=false;
            i++;
        }
        return res;
    }
     
    public static boolean checaFila(int fila, int col){
        int m,j,val;
        boolean res;
        m=tablero[0].length;
        res=true;
        j=0;
        val=tablero[fila][col].getVal();
        while(j<m && res){
            if(tablero[fila][j].getVal()==val && j!=col)
                res=false;
            j++;
        }
        return res;
    }
     
    public static void celdaToF(int fila, int col){
        boolean res1, res2;
        res1=checaFila(fila,col);
        res2=checaCol(fila,col);
        if(tablero[fila][col].getVal()==0)
            tablero[fila][col].setValidF();
        else
            if(res1 && res2)
                tablero[fila][col].setValidT();
            else
                tablero[fila][col].setValidF();
    }
     
    public static void celdaNull(int fila, int col){
        if(tablero[fila][col].getVal()==0)
            tablero[fila][col].setValidF();
        else
            tablero[fila][col].setValidT();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);        
    }
    
}
