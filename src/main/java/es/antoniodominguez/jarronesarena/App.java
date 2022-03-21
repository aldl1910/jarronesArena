package es.antoniodominguez.jarronesarena;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
    
    BorderPane paneRoot;
    @Override
    public void start(Stage stage) {
        
        // TAMAÑO PANTALLA
        short tamXPantalla = 640;
        short tamYPantalla = 480;
        
        // CREAR BORDERPANE
        paneRoot = new BorderPane();
        var scene = new Scene(paneRoot, tamXPantalla, tamYPantalla);
        stage.setScene(scene);
        stage.show();
        
        // LLAMADA AL CONSTRUCTOR DE LA CLASE LOGICA
        Logica logica = new Logica();
        
        // LLAMADA A MÉTODO QUE GENERA LOS NÚMEROS ALEATORIOS
        logica.colocarNumAleatorios();
        
        // LLAMADA A MÉTODO QUE CAMBIA LA POSICIÓN DE LAS COLUMNAS LLENAS POR LAS VACÍAS
        logica.cambiarColumna();
        
        // LLAMADA AL MÉTODO QUE MUESTRA EL RESULTADO DE LA COMPLETACIÓN DEL ARRAY POR CONSOLA
        logica.mostrarJarronesConsola();
        
        // LLAMADA AL CONSTRUCTOR DE LA CLASE TABLERO
        Tablero tablero = new Tablero(logica);
        
        // POSICIONA EL TABLERO EN EL CENTRO DEL BORDERPANE
        paneRoot.setCenter(tablero);
        
        // LLAMADA AL CONSTRUCTOR DE LA CLASE PANELSUPERIOR
        PanelSuperior panelSuperior = new PanelSuperior (logica, tablero);
        
        // POSICIONA EL PANELSUPERIOR EN LA PARTE SUPERIOR DEL BORDERPANE
        paneRoot.setTop(panelSuperior);
    }

    public static void main(String[] args) {
        launch();
    }

}