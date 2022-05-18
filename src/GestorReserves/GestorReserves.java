
/*
MIT License

Copyright (c) 2022 Juan José Gómez Villegas

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

*/

package GestorReserves;

//Importa les llibreries de la classe
import java.util.Scanner;

/**
 * GestorReserves: Classe que instanciara la Classe Results
 * Licència MIT
 * 
 * @author Juan José Gómez Villegas
 * @version 1.0
 **/
public class GestorReserves {
    //Crea l'objecte Scanner
    private Scanner lector;
    
    /**
     * GestorReserves: Metode Constructor de la classe GestorReserves
     */
    public GestorReserves() {
        //Instancia l'objecte Scanner i estableix el patró delimitant de l'objecte Scanner en un salt de linia
        lector = new Scanner(System.in);
        lector.useDelimiter("\n");
        
        //I Executa el metode set
        set();
    }
    
    /**
     * set: Metode que mostrara un selector d'opcions
     */
    private void set() {
        //Instancia l'objecte Results
        Results result = new Results();
        String opcio;
        
        do {
            /*Demana a l'usuari una de les següents opcions:*/
            System.out.println(" Benvingut a l'Hotel ABP");
            System.out.println("=======================================================");
            System.out.println(" [1] Afegir Client");
            System.out.println(" [2] Crear Reserva");
            System.out.println(" [Quit o Exit] Sortir del Programa");
            System.out.println();
            
            System.out.print("Selecciona l'opció a realitzar: ");
            opcio = lector.next();
            System.out.println();

            //Si es Quit o Exit surt del Programa
            if (opcio.equals("Quit") || opcio.equals("quit") || opcio.equals("Exit") || opcio.equals("exit")) {
                break;
            } else {
                /*I si no, selecciona la opció seleccionada per l'usuari*/
                switch (opcio) {
                    //Opció 1, Crea un Client
                    case "1":
                        result.addClient(lector);
                        break;
                    //Opció 2, Crea una Reserva
                    case "2":
                        result.addReserva(lector);
                        break;
                    //Opció per defecte, mostra un missatge d'error
                    default:
                        System.err.println("Error: La Opció "+opcio+" no està disponible !!!");
                }
            }
        } while (!opcio.equals("Quit") || !opcio.equals("quit") || !opcio.equals("Exit") || !opcio.equals("exit"));
    }
}
