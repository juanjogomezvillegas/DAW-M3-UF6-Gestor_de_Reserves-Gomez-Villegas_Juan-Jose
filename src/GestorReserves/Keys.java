
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

/**
 * Keys: Classe que contindrà les claus per connectar-se a la base de dades
 * Licència MIT
 * 
 * @author Juan José Gómez Villegas
 * @version 1.0
 **/
public class Keys {
    //Crea les variables per guardar les claus per connectar-se a la base de dades
    private final String db_host;
    private final String db_port;
    private final String db_name;
    private final String db_user;
    private final String db_password;
    
    /**
     * Keys: Metode Constructor de la classe Keys
     */
    public Keys() {
        //Estableix les claus per connectar-se a la base de dades
        this.db_host = "192.168.0.10";
        this.db_port = "3306";
        this.db_name = "hotels";
        this.db_user = "java";
        this.db_password = "1234";
    }
    
    /**
     * getDBUrl: Metode que prepara l'Url per conectar-se a la base de dades
     * 
     * @return Url que s'utilitzara per connectar-se a la base de dades
     */
    protected String getDBUrl() {
        return "jdbc:mysql://"+getDBHost()+":"+getDBPort()+"/"+getDBName()+"?user="+getDBUser()+"&password="+getDBPassword();
    }
    
    /**
     * setDBDriver: Metode que prepara el driver per conectar-se a la base de dades
     */
    protected void setDBDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * getDBHost: Metode que retorna el host on està allotjada a la base de dades
     * 
     * @return el host on està allotjada a la base de dades
     */
    private String getDBHost() {
        return this.db_host;
    }
    
    /**
     * getDBPort: Metode que retorna el port per connectar-se a la base de dades
     * 
     * @return el port per connectar-se a la base de dades
     */
    private String getDBPort() {
        return this.db_port;
    }
    
    /**
     * getDBName: Metode que retorna el nom de la base de dades
     * 
     * @return el nom de la base de dades
     */
    private String getDBName() {
        return this.db_name;
    }
    
    /**
     * getDBUser: Metode que retorna l'usuari de la base de dades
     * 
     * @return l'usuari de la base de dades
     */
    private String getDBUser() {
        return this.db_user;
    }
    
    /**
     * getDBPassword: Metode que retorna el password de l'usuari de la base de dades
     * 
     * @return el password de l'usuari de la base de dades
     */
    private String getDBPassword() {
        return this.db_password;
    }
}
