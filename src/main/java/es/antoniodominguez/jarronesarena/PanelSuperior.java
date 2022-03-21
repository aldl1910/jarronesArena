package es.antoniodominguez.jarronesarena;

import javafx.animation.KeyFrame;
import javafx.scene.control.Button;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class PanelSuperior extends HBox {
    
    // BOTÓN REINICIO
    Button botonReinicio = new Button("Reinicio");
    
    // TEXT TIEMPO
    Text textTiempo = new Text("Tiempo: ");
    
    // TEXT CONTADOR MUESTRA EL TIEMPO RESTANTE
    Text textContador = new Text("0");
    
    // VARIABLE QUE AJUSTA EL TIEMPO POR CADA PARTIDA
    static int tiempo = Logica.TIEMPO_PREDEFINIDO;
    
    // VARIABLE QUE CONTIENE EL TIEMPO DE LA PARTIDA
    int contador = tiempo;
    
    // TIMELINE PARA EL TEMPORIZADOR
    static Timeline temporizador;
    
    
    public PanelSuperior (Logica logica, Tablero tablero){
        
        // ASIGNAMOS EL EVENTO AL BOTÓN REINCIO
        botonReinicio.setOnAction(event -> {
            tablero.elimiarTablero();
            tablero.reinicioTablero();
            logica.finPartida= false;
            contador = tiempo;
            temporizador(logica);
            System.out.println("Has reiniciado la partida!!");
        });
        
        // LE DAMOS ESPACIO A LOS ELEMENTOS DEL HBOX
        this.setSpacing(10);
        
        // AÑADIMOS LOS ELEMTOS AL HBOX
        this.getChildren().add(textTiempo);
        this.getChildren().add(textContador);
        this.getChildren().add(botonReinicio);
        
        // LLAMAMOS AL MÉTODO TEMPORIZADOR
        temporizador(logica);
    }
    
    // MÉTODO QUE CONTIENE UN TIMELINE QUE FUNCIONA COMO TEMPORIZADOR
    public void temporizador(Logica logica){
        
        // GENERA EL TIMELINE
        temporizador = new Timeline(
            new KeyFrame (Duration.seconds(1.000), (ActionEvent t) -> {
                
                // ASIGNAMOS EL VALOR DE CONTADOR AL TEXTCONTADOR
                textContador.setText(String.valueOf(contador));
                
                // SI CONTADOR LLEGA A 0 EL TIMELINE SE DETIENE
                // SI LA VARIABLE DE LA CLASE LOGICA FINPARTIDA ES VERDADERO 
                    // TAMBIÉN SE DETIENE
                if (contador == 0 ){
                    temporizador.stop();
                    System.out.println("Has perdido!!!");
                } else if(logica.finPartida == true){
                    temporizador.stop();
                }
                 contador--;                
            })
        );
        
        // INICIAMOS EL TIMELINE
        temporizador.setCycleCount(Timeline.INDEFINITE);
        temporizador.play();   
    }
    


}
