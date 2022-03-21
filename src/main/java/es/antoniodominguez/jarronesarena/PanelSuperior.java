package es.antoniodominguez.jarronesarena;

import javafx.animation.KeyFrame;
import javafx.scene.control.Button;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class PanelSuperior extends HBox {
    
    Button botonReinicio = new Button("Reinicio");
    Text textTiempo = new Text("Tiempo: ");
    Text textContador;
    //Label labelContador;
    static int tiempo = Logica.TIEMPO_PREDEFINIDO;
    int contador = tiempo;
    static Timeline temporizador;
    
    
    //okButton.setDefaultButton(true);
    public PanelSuperior (Logica logica, Tablero tablero){
        
        
        botonReinicio.setOnAction(event -> {
            tablero.elimiarTablero();
//            logica.reiniciarPartida();
            tablero.reinicioTablero();
            logica.finPartida= false;
            contador = tiempo;
            temporizador(logica);
            
           // tablero.iniciarTablero();
           // tablero.elimiarTablero();
            
            //tablero.cambiarColor(jarron, 0, 0);
            
        });
        this.setSpacing(10);
        
        this.getChildren().add(textTiempo);
        this.getChildren().add(botonReinicio);
        temporizador(logica);
    }
    
    public void temporizador(Logica logica){
        
        temporizador = new Timeline(
            new KeyFrame (Duration.seconds(1.000), (ActionEvent t) -> {
                contador--;
                //textContador.setText(String.valueOf(contador));
                //labelContador.setText();
                //System.out.println(labelContador);
                if (contador == 0 ){
                    temporizador.stop();
                    System.out.println("Has perdido");
                } else if(logica.finPartida == true){
                    temporizador.stop();
                }
                System.out.println("Contador " + contador);
                
        }));
        //
        
        //this.getChildren().add(textContador);
        temporizador.setCycleCount(Timeline.INDEFINITE);
        temporizador.play();   
    }
    


}
