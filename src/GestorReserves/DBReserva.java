
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 * DBReserva: Classe que fara les consultes contra la resta de taules de la base de dades
 * Licència MIT
 * 
 * @author Juan José Gómez Villegas
 * @version 1.0
 **/
public class DBReserva {
    //Crea l'objecte Database
    private final Database connectDB;
    
    /**
     * DBReserva: Metode Constructor de la classe DBReserva
     */
    public DBReserva() {
        //Instancia l'objecte Database
        this.connectDB = new Database();
    }
    
    /**
     * allHotels: Metode que mostra tots els hotels de la base de dades
     */
    public void allHotels() {
        //Obre la Connexió a la base de dades
        this.connectDB.setOpenConnection();
        
        //Si no es produeix cap excepció sql
        try {
            //Prepara i executa la consulta a la base de dades
            Statement stm = this.connectDB.getConnection().createStatement();
            ResultSet result = stm.executeQuery("SELECT A.* FROM hotel A");
            
            System.out.println(" Id | Nom | Adreça | Telèfon | Categoria ");
            System.out.println("============================================================================================");
            //I recorre tots els registres
            while (result.next()) {
                //Mostrant les dades dels hotels per pantalla
                System.out.println(" "+result.getInt("hotel_id")+" | "
                        +result.getString("hotel_nom")+" | "+result.getString("hotel_adresa")+" | "
                        +result.getString("hotel_telefon")+" | "+result.getString("hotel_categoria")+" ");
            }
            System.out.println("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        //Tanca la Connexió a la base de dades
        this.connectDB.setCloseConnection();
    }
    
    /**
     * allHabitacions: Metode que mostra totes les habitacions de l'hotel seleccionat
     * 
     * @param idHotel id de l'hotel seleccionat
     */
    public void allHabitacions(String idHotel) {
        //Obre la Connexió a la base de dades
        this.connectDB.setOpenConnection();
        
        //Si no es produeix cap excepció sql
        try {
            //Prepara la consulta a la base de dades amb estaments preparats
            PreparedStatement stm = this.connectDB.getConnection().prepareStatement("SELECT A.* FROM habitacio A WHERE A.habitacio_hotel_id = ?");
            stm.setString(1, idHotel);
            
            //Executa la consulta a la base de dades
            ResultSet result = stm.executeQuery();
            
            System.out.println(" Id | Nom | Número d'Ocupants ");
            System.out.println("============================================================================================");
            //I recorre tots els registres
            while (result.next()) {
                //Mostrant les dades de les habitacions per pantalla
                System.out.println(" "+result.getInt("habitacio_id")+" | "
                        +result.getString("habitacio_nom")+" | "+result.getString("habitacio_ocupants")+" ");
            }
            System.out.println("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        //Tanca la Connexió a la base de dades
        this.connectDB.setCloseConnection();
    }
    
    /**
     * allDisponibles: Metode que mostra tots els dies disponibles de l'habitació seleccionada entre la data d'entrada i de sortida seleccionades
     * 
     * @param idHabitacio id de l'habitacio seleccionada
     * @param dataEntrada data d'entrada
     * @param dataSortida data de sortida
     */
    public void allDisponibles(String idHabitacio, String dataEntrada, String dataSortida) {
        //Obre la Connexió a la base de dades
        this.connectDB.setOpenConnection();
        
        //Si no es produeix cap excepció sql
        try {
            //Prepara la consulta a la base de dades amb estaments preparats
            PreparedStatement stm = this.connectDB.getConnection().prepareStatement("SELECT A.* FROM preu A "
                    +"WHERE A.habitacio_id = ? AND A.data BETWEEN ? AND ? AND A.preu_habitacio IS NOT NULL AND A.disponibles > 0 AND A.tancat ='N';");
            stm.setString(1, idHabitacio);
            stm.setString(2, dataEntrada);
            stm.setString(3, dataSortida);
            
            //Executa la consulta a la base de dades
            ResultSet result = stm.executeQuery();
            
            System.out.println(" Data | Preu | Disponibles ");
            System.out.println("============================================================================================");
            //I recorre tots els registres
            while (result.next()) {
                //Mostrant els dies disponibles per pantalla
                System.out.println(" "+result.getString("data")+" | "
                        +result.getFloat("preu_habitacio")+" | "+result.getInt("disponibles")+" ");
            }
            System.out.println("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        //Tanca la Connexió a la base de dades
        this.connectDB.setCloseConnection();
    }
    
    /**
     * getImport: Metode que calculara l'import de tots els dies disponibles
     * Accepta els mateixos parametres que el metode allDisponibles
     * 
     * @param idHabitacio id de l'habitacio seleccionada
     * @param dataEntrada data d'entrada
     * @param dataSortida data de sortida
     * @return l'import de tots els dies disponibles
     */
    public String getImport(String idHabitacio, String dataEntrada, String dataSortida) {
        //Obre la Connexió a la base de dades
        this.connectDB.setOpenConnection();
        
        String Import = "";
        
        //Si no es produeix cap excepció sql
        try {
            //Prepara la consulta a la base de dades amb estaments preparats
            PreparedStatement stm = this.connectDB.getConnection().prepareStatement("SELECT sum(A.preu_habitacio) AS import FROM preu A "
                    +"WHERE A.habitacio_id = ? AND A.data BETWEEN ? AND ? AND A.preu_habitacio IS NOT NULL AND A.disponibles > 0 AND A.tancat ='N';");
            stm.setString(1, idHabitacio);
            stm.setString(2, dataEntrada);
            stm.setString(3, dataSortida);
            
            //Executa la consulta a la base de dades
            ResultSet result = stm.executeQuery();
            
            //I recorre tots els registres
            while (result.next()) {
                //Guardant l'import obtingut
                Import = result.getString("import");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        //Tanca la Connexió a la base de dades
        this.connectDB.setCloseConnection();
        
        return Import;
    }
    
    /**
     * getIdReserva: Metode que obtindrà l'id de la reserva nova
     * 
     * @param idHotel id de l'hotel seleccionat
     * @param idHabitacio id de l'habitacio seleccionada
     * @param dataEntrada data d'entrada
     * @param dataSortida data de sortida
     * @return l'id de la reserva nova
     */
    public String getIdReserva(String idHotel, String idHabitacio, String dataEntrada, String dataSortida) {
        //Obre la Connexió a la base de dades
        this.connectDB.setOpenConnection();
        
        String id = "";
        
        //Si no es produeix cap excepció sql
        try {
            //Prepara la consulta a la base de dades amb estaments preparats
            PreparedStatement stm = this.connectDB.getConnection().prepareStatement("SELECT A.reserva_id FROM reserva A"
                    +" WHERE A.reserva_hotel_id = ? AND A.reserva_habitacio_id = ?"
                    +" AND A.reserva_data_entrada = ? AND A.reserva_data_sortida = ?;");
            stm.setString(1, idHotel);
            stm.setString(2, idHabitacio);
            stm.setString(3, dataEntrada);
            stm.setString(4, dataSortida);
            
            //Executa la consulta a la base de dades
            ResultSet result = stm.executeQuery();
            
            //I recorre tots els registres
            while (result.next()) {
                //Giuardant l'id obtingut
                id = result.getString("reserva_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        //Tanca la Connexió a la base de dades
        this.connectDB.setCloseConnection();
        
        return id;
    }
    
    /**
     * add: Metode que afegira una nova reserva a la base de dades
     * 
     * @param dataEntrada data d'entrada
     * @param dataSortida data de sortida
     * @param idHabitacio id de l'habitacio seleccionada
     * @param idHotel id de l'hotel seleccionat
     * @param Import import dels dies disponibles (calculat)
     */
    public void add(String dataEntrada, String dataSortida, String idHabitacio, String idHotel, String Import) {
        //Obre la Connexió a la base de dades
        this.connectDB.setOpenConnection();
        
        //Si no es produeix cap excepció sql
        try {
            //Prepara l'insert a la base de dades amb estaments preparats
            PreparedStatement stm = this.connectDB.getConnection().prepareStatement("INSERT INTO reserva"
                    +" (reserva_data_entrada,reserva_data_sortida,reserva_habitacio_id,reserva_hotel_id,reserva_import) VALUES (?,?,?,?,?);");
            stm.setString(1, dataEntrada+" 00:00:00");
            stm.setString(2, dataSortida+" 00:00:00");
            stm.setString(3, idHabitacio);
            stm.setString(4, idHotel);
            stm.setString(5, Import);

            //Executa l'insert a la base de dades
            Boolean result = stm.execute();
            
            //I si tot va bé mostra que s'ha creat correctament
            if (!result) {
                System.out.println("La Reserva s'ha afegit correctament.");
            //Si no, mostra un missatge d'error
            } else {
                System.err.println("No s'ha pogut afegir la Reserva.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        //Tanca la Connexió a la base de dades
        this.connectDB.setCloseConnection();
    }
    
    /**
     * addClientReserva: Metode que afegira un nou registre a la taula intermitja entre Reserva i Client de la base de dades
     * 
     * @param idReserva id de la reserva nova
     * @param idClient id del client que ha creat la reservs
     */
    public void addClientReserva(String idReserva, String idClient) {
        //Obre la Connexió a la base de dades
        this.connectDB.setOpenConnection();
        
        //Si no es produeix cap excepció sql
        try {
            //Prepara l'insert a la base de dades amb estaments preparats
            PreparedStatement stm = this.connectDB.getConnection().prepareStatement("INSERT INTO reserva_client"
                    +" (reserva_id,client_id) VALUES (?,?);");
            stm.setString(1, idReserva);
            stm.setString(2, idClient);

            //Executa l'insert a la base de dades
            Boolean result = stm.execute();
            
            //I si tot va bé mostra que s'ha creat correctament
            if (!result) {
                System.out.println("La Reserva s'ha afegit al Client correctament.");
            //Si no, mostra un missatge d'error
            } else {
                System.err.println("No s'ha pogut afegir la Reserva al Client.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        //Tanca la Connexió a la base de dades
        this.connectDB.setCloseConnection();
    }
}
