package es.antoniodominguez.jarronesarena;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Jarron extends VBox {
    static final double TAM_WIDTH_JARRONES = 75;
    static final double TAM_HEIGHT_JARRONES = 50;
    Logica logica;
    Rectangle [] rect = new Rectangle[logica.tamYJarrones];
    //Rectangle rect4;
    public Jarron(){
//        rect[3].setWidth(TAM_WIDTH_JARRONES);
//        rect[3].setHeight(TAM_HEIGHT_JARRONES);
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
