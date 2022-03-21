package es.antoniodominguez.jarronesarena;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Jarron extends VBox {

    // TAMAÑO ALTURA Y ANCHURA DEL JARRON    
    static final double TAM_WIDTH_JARRONES = 75;
    static final double TAM_HEIGHT_JARRONES = 50;
    
    Logica logica;
    
    // ARRAY DE RECTÁNGULOS
    Rectangle [] rect = new Rectangle[logica.tamYJarrones];
    
    // CONSTRUCTOR QUE CREA EL JARRON Y LE ASIGNA UN RECTÁNGULO EN UN BUCLE
    public Jarron(){
        for(int i = 0; i< logica.tamYJarrones; i++){
            Rectangle r = new Rectangle();
            r.setWidth(TAM_WIDTH_JARRONES);
            r.setHeight(TAM_HEIGHT_JARRONES);
            r.setStroke(Color.BLACK);
            rect[i] = r;
            this.getChildren().add(rect[i]);
        }
        
    }
}
