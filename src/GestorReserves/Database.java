
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database: Classe que s'encarregara d'obrir i tancar la connexió amb la base de dades, i també de retornar l'objecte de connexió a la base de dades
 * Licència MIT
 * 
 * @author Juan José Gómez Villegas
 * @version 1.0
 **/
public class Database {
    //Crea els objectes Keys i Connection
    private final Keys keys;
    private Connection connection;
    
    /**
     * Database: Metode Constructor de la classe Database
     */
    public Database() {
        //Instancia els objectes Keys i Connection
        this.keys = new Keys();
        this.connection = null;
    }
    
    /**
     * setOpenConnection: Metode que Obrira la Connexió amb la Base de Dades
     */
    protected void setOpenConnection() {
        try {
            this.connection = DriverManager.getConnection(keys.getDBUrl());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * setCloseConnection: Metode que Tancara la Connexió amb la Base de Dades
     */
    protected void setCloseConnection() {
        try {
            if (connection != null) {
                this.connection.close();
                this.connection = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * getConnection: Metode que Retornara la Connexió amb la Base de Dades
     */
    protected Connection getConnection() {
        return this.connection;
    }
}
