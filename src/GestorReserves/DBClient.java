
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
import java.sql.PreparedStatement;

/**
 * DBClient: Classe que fara les consultes contra la taula client de la base de dades
 * Licència MIT
 * 
 * @author Juan José Gómez Villegas
 * @version 1.0
 **/
public class DBClient {
    //Crea l'objecte Database
    private final Database connectDB;
    //Crea la variable table
    private final String table;
    
    /**
     * DBClient: Metode Constructor de la classe DBClient
     */
    public DBClient() {
        //Instancia l'objecte Database
        this.connectDB = new Database();
        //Estableix la taula contra la que farem les consultes (la taula client)
        this.table = "client";
    }
    
    /**
     * get: Metode que obtindrà l'id d'un client a partir del seu email
     * 
     * @param email email del client a buscar
     * @return l'id del client o cadena buida
     */
    public String get(String email) {
        //Obre la Connexió a la base de dades
        this.connectDB.setOpenConnection();
        
        String idClient = "";
        
        //Si no es produeix cap excepció sql
        try {
            //Prepara la consulta a la base de dades amb estaments preparats
            PreparedStatement stm = this.connectDB.getConnection().prepareStatement("SELECT A.client_id FROM client A WHERE A.client_email = ?");
            stm.setString(1, email);
            
            //Executa la consulta a la base de dades
            ResultSet result = stm.executeQuery();
            int count = 0;
            
            //I recorre tots els registres
            while (result.next()) {
                //Incrementant la variable count i guardant el id obtingut
                count++;
                idClient = result.getString("client_id");
            }
            
            //I Si tot va bé, la variable count sempre serà 1
            
            //Comprova si la variable count no es major que 0 
            if (!(count > 0)) {
                //Si es compleix en l'id estableix cadena buida
                idClient = "";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        //Tanca la Connexió a la base de dades
        this.connectDB.setCloseConnection();
        
        return idClient;
    }
    
    /**
     * add: Metode que afegira un nou client a la base de dades
     * 
     * @param name nom del client
     * @param lastname cognoms del client
     * @param phone telefon del client
     * @param email email del client
     */
    public void add(String name, String lastname, String phone, String email) {
        //Obre la Connexió a la base de dades
        this.connectDB.setOpenConnection();
        
        //Si no es produeix cap excepció sql
        try {
            //Prepara l'insert a la base de dades amb estaments preparats
            PreparedStatement stm = this.connectDB.getConnection().prepareStatement("INSERT INTO "+this.table
                    +" (client_nom,client_cognoms,client_telefon,client_email) VALUES (?,?,?,?);");
            stm.setString(1, name);
            stm.setString(2, lastname);
            stm.setString(3, phone);
            stm.setString(4, email);
            
            //Executa l'insert a la base de dades
            Boolean result = stm.execute();
            
            //I si tot va bé mostra que s'ha creat correctament
            if (!result) {
                System.out.println("El Client "+name+" "+lastname+", s'ha afegit correctament.");
            //Si no, mostra un missatge d'error
            } else {
                System.err.println("No s'ha pogut afegir al Client "+name+" "+lastname+".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        //Tanca la Connexió a la base de dades
        this.connectDB.setCloseConnection();
    }
}
