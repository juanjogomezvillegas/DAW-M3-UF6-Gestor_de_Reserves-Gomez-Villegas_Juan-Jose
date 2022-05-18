
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
 * Results: Classe que instanciara les clases DBClient i DBReserva
 * Licència MIT
 * 
 * @author Juan José Gómez Villegas
 * @version 1.0
 **/
public class Results {
    //Crea els objectes DBClient i DBReserva
    private final DBClient client;
    private final DBReserva reserva;
    
    /**
     * Results: Metode Constructor de la classe Results
     */
    public Results() {
        //Instancia els objectes DBClient i DBReserva
        this.client = new DBClient();
        this.reserva = new DBReserva();
    }
    
    /**
     * addClient: Metode que afegeix un client nou preguntant les dades a l'usuari
     * 
     * @param lector objecte Scanner
     */
    public void addClient(Scanner lector) {
        //estableix el patró delimitant de l'objecte Scanner en un salt de linia
        lector.useDelimiter("\n");
        
        //Pregunta el nom a l'usuari
        System.out.print("Entra el Nom: ");
        String name = lector.next();
        System.out.println();
        
        //Pregunta els cognoms a l'usuari
        System.out.print("Entra els Cognoms: ");
        String lastname = lector.next();
        System.out.println();
        
        //Pregunta el telefon a l'usuari
        System.out.print("Entra el Telèfon: ");
        String phone = lector.next();
        System.out.println();
        
        String email = "";
        boolean correcte = false;
        
        //Pregunta el email a l'usuari
        do {
            System.out.print("Entra el Correu Electrònic: ");
            email = lector.next();
            System.out.println();
            //I valida si el email es correcte fent servir el metode isEmail de la classe Utils
            if (Utils.isEmail(email)) {
                break;
            } else {
                System.err.println("Error: "+email+" no es un Correu Electrònic.");
            }
        } while(!correcte);
        
        //Crea el Client
        this.client.add(name, lastname, phone, email);
    }
    
    /**
     * addReserva: Metode que afegeix una reserva nova preguntant les dades a l'usuari
     * 
     * @param lector objecte Scanner
     */
    public void addReserva(Scanner lector) {
        //estableix el patró delimitant de l'objecte Scanner en un salt de linia
        lector.useDelimiter("\n");
        
        //Crea les variables necessaries per crear la reserva
        String idClient;
        String mailClient;
        String dataEntrada;
        String dataSortida;
        String idHotel;
        String idHabitacio;
        
        boolean correcte = false;
        
        //Pregunta el email a l'usuari
        do {
            System.out.print("Entra el Correu Electrònic: ");
            mailClient = lector.next();
            System.out.println();
            //I valida si el email es correcte fent servir el metode isEmail de la classe Utils
            if (Utils.isEmail(mailClient)) {
                break;
            } else {
                System.err.println("Error: "+mailClient+" no es un Correu Electrònic.");
            }
        } while(!correcte);
        
        //Obté l'id del client a partir del seu email
        idClient = this.client.get(mailClient);
        
        //Si no troba l'id mostra que el client no existeix
        if (idClient.equals("")) {
            System.err.println("El Client amb el mail "+mailClient+" no existeix.");
        } else {
            //Si el client existeix
            
            //Mostra una selecció de tots els hotels, i l'usuari seleccionara només un
            this.reserva.allHotels();
            System.out.print("Selecciona un Hotel: ");
            idHotel = lector.next();
            System.out.println();
            
            //Mostra una selecció de totes les habitacions de l'hotel seleccionat, i l'usuari seleccionara només una
            this.reserva.allHabitacions(idHotel);
            System.out.print("Selecciona una Habitació: ");
            idHabitacio = lector.next();
            System.out.println();
            
            //Pregunta a l'usuari la data d'entrada
            System.out.print("Entra la Data d'Entrada: ");
            dataEntrada = lector.next();
            System.out.println();

            //Pregunta a l'usuari la data de sortida
            System.out.print("Entra la Data de Sortida: ");
            dataSortida = lector.next();
            System.out.println();

            //Mostra els dies disponibles a partir de la data d'entrada, la data de sortida i l'habitació seleccionada
            this.reserva.allDisponibles(idHabitacio, dataEntrada, dataSortida);
            
            //I Pregunta si vols reservar els dies disponibles
            System.out.print("Vols Reservar [\"s\" / \"n\"]? ");
            String isreserva = lector.next();
            System.out.println();
            
            //Si diu que si (s)
            if (isreserva.equals("s")) {
                //Calcula l'import a pagar per tots els dies
                String Import = this.reserva.getImport(idHabitacio, dataEntrada, dataSortida);
                
                //Crea la Reserva
                this.reserva.add(dataEntrada, dataSortida, idHabitacio, idHotel, Import);
                
                //Obté l'id de la reserva nova
                String idReserva = this.reserva.getIdReserva(idHotel, idHabitacio, dataEntrada, dataSortida);
                
                //I crea un nou registre a la taula entre Reserva i Client
                this.reserva.addClientReserva(idReserva, idClient);
            }
        }
    }
}
