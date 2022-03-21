package es.antoniodominguez.jarronesarena;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class Tablero extends HBox {
    Logica logica;
    PanelSuperior panelSuperior;
   //Jarron jarron;
    
    int posX = 17;
    byte multiplicador = 0;
    byte espaciado = 7;
    byte columnaPressed;
    byte columnaReleased;
    int numSeleccionado1;
    int numSeleccionado2;
    int fila=0;
    static Jarron [] numJarron;
    byte num;
    int filaPressed;
    int filaReleased;
    
    public Tablero(Logica logica) {
        this.logica = logica;
        
        this.setMinWidth(Jarron.TAM_WIDTH_JARRONES * logica.tamXJarrones);
        this.setMinHeight(Jarron.TAM_HEIGHT_JARRONES * logica.tamYJarrones); 
        this.setMaxWidth(Jarron.TAM_WIDTH_JARRONES * logica.tamXJarrones);
        this.setMaxHeight(Jarron.TAM_HEIGHT_JARRONES * logica.tamYJarrones); 
        this.setSpacing(espaciado);
        
        
        iniciarTablero();
       
        
        this.setOnMousePressed((event) -> {

            columnaPressed = (byte)(event.getX() / (Jarron.TAM_WIDTH_JARRONES + espaciado));
            filaPressed = this.logica.buscarFila(columnaPressed);
            if(filaPressed == -1){
                filaPressed = 0;
            } else {
                filaPressed++;
            }
           // logica.buscarFila(columnaPressed);

            //System.out.println("Columna click" + columnaPressed);
            numSeleccionado1 = logica.jarrones[columnaPressed][filaPressed];
            //System.out.println("Numero seleccionado1 " + numSeleccionado1);
            logica.jarrones[columnaPressed][filaPressed] = logica.VACIO;
            //logica.cambiarNum(columnaPressed, numSeleccionado1);

            numJarron[columnaPressed].rect[filaPressed].setFill(Color.WHITE);
//            System.out.println("Columna presionada: " + columnaPressed);
//            System.out.println("Fila presionada: " + filaPressed);
//            System.out.println("Contenido " + numSeleccionado1);


        });

        this.setOnMouseReleased((event) -> {

            columnaReleased = (byte)(event.getX() / (Jarron.TAM_WIDTH_JARRONES + espaciado));
            //colocarFicha(columna); 
            //System.out.println("Columna click" + columnaReleased);
            filaReleased = this.logica.buscarFila(columnaReleased);

            int filaSiguiente = filaReleased + 1;

            if (filaReleased == -1){
                int fila = this.logica.buscarFila(columnaPressed);
                if(fila == -1){
                    fila = 0;
                }
                logica.jarrones[columnaPressed][fila] = numSeleccionado1;
                cambiarColor(numJarron[columnaPressed], fila, numSeleccionado1);
            }

            numSeleccionado2 = logica.jarrones[columnaReleased][filaReleased];
            //System.out.println("Numero seleccionado2 " + numSeleccionado2);
            //System.out.println("Fila siguiente " + filaSiguiente);
            //System.out.println("Fila Released " + filaReleased);



            if(numSeleccionado1 != 0  && numSeleccionado2 == 0 ){
                if(filaSiguiente == 4){
                    filaSiguiente = 3;
                }

                if(logica.jarrones[columnaReleased][filaSiguiente] == logica.VACIO ||
                        logica.jarrones[columnaReleased][filaSiguiente] == numSeleccionado1){
                    logica.cambiarNum(columnaReleased, numSeleccionado1);
                    cambiarColor(numJarron[columnaReleased], filaReleased, numSeleccionado1);
                    logica.mostrarJarronesConsola();
                    logica.comprobarVertical(columnaReleased);
                } else {
                    int fila = this.logica.buscarFila(columnaPressed);
                    if(fila == -1){
                        fila = 0;
                    }
                    logica.jarrones[columnaPressed][fila] = numSeleccionado1;
                    cambiarColor(numJarron[columnaPressed], fila, numSeleccionado1);
                }

            }
        });
        
    }
    
    public void iniciarTablero (){
        numJarron = new Jarron[6];
        for(int iJarron=0; iJarron < logica.tamXJarrones; iJarron++){
            Jarron jarron = new Jarron();
            numJarron[iJarron]= jarron;
           
            
            //System.out.println("Numero de numJarron" + numJarron);
            for(int fila=0; fila < logica.tamYJarrones; fila++){
                int contenido= 0;
                //System.out.println("Fila "+ fila);
                contenido = Logica.jarrones[iJarron][fila];
                //System.out.println("Contenido " + contenido);
                cambiarColor(jarron, fila, contenido);
                //jarron.setLayoutX(posX * multiplicador);
                multiplicador++;
            } 
            this.getChildren().add(jarron);
            //this.getChildren().remo
        }
       
    }
    
    public void elimiarTablero(){
        for(int c=0; c<6;c++){
            this.getChildren().remove(numJarron[c]);
        }
//        //this.getChildren().removeAll(numJarron);
        System.out.println(this.getChildren().size());
    }
    
    
    public void reinicioTablero(){
        if(Logica.finPartida == true){
            PanelSuperior.tiempo = Logica.TIEMPO_PREDEFINIDO ;
            PanelSuperior.tiempo =  PanelSuperior.tiempo - logica.restante;
        }
        logica = new Logica();
        logica.colocarNumAleatorios();
        logica.cambiarColumna();
        logica.mostrarJarronesConsola();
        iniciarTablero();
        Logica.finPartida = false;
        
    }
    
    public void cambiarColor( Jarron jarron, int fila, int contenido ){
        
        switch(contenido){
            case 0:
                //System.out.println("Has entrado en el caso 0");
                jarron.rect[fila].setFill(Color.WHITE);
                break;
            case 1:
                //System.out.println("Has entrado en el caso 1");
                jarron.rect[fila].setFill(Color.BLUE);
                break;
            case 2:
                //System.out.println("Has entrado en el caso 2");
                jarron.rect[fila].setFill(Color.RED);
                break;
            case 3:
                //System.out.println("Has entrado en el caso 3");
                jarron.rect[fila].setFill(Color.GREEN);
                break;
            case 4:
                //System.out.println("Has entrado en el caso 4");
                jarron.rect[fila].setFill(Color.ORANGE);
                break;
        }
    }
}
