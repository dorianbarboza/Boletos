package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.sql.*;
import java.util.ArrayList;

public class OperacionesClientes {
    Connection connection;

    public OperacionesClientes(Connection conn){
        this.connection = conn;
    }

    public ArrayList<Cliente> ViewClientes() {

        String query = "SELECT * FROM cliente ";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<Cliente> ListaCliente = new ArrayList<>();
            while (rs.next()) {
                IntegerProperty clienteId = new SimpleIntegerProperty( rs.getInt("clienteid"));
                StringProperty nombre = new SimpleStringProperty( rs.getString("nombre"));
                StringProperty apellidos = new SimpleStringProperty( rs.getString("apellidos"));
                StringProperty direccion = new SimpleStringProperty( rs.getString("direccion"));
                ListaCliente.add(new Cliente(clienteId , nombre, apellidos, direccion));


            }
            return ListaCliente;
        }
        catch (java.sql.SQLException ex){

            ex.printStackTrace();
            System.out.println("SQLException:_"+ ex.getMessage());
            System.out.println("SQLState:_" + ex.getSQLState());
            System.out.println("VendorError:_" + ex.getErrorCode());

            return null;
        }


    }


    /*
    * OBTENER DATOS
    */
    public Cliente getCliente(int id){
        IntegerProperty clienteId = new SimpleIntegerProperty(0);
        StringProperty nombre =  new SimpleStringProperty(""), apellidos =  new SimpleStringProperty(""), direccion =  new SimpleStringProperty("");


        String query = "SELECT clienteid, nombre, apellidos, direccion " +
                        "FROM cliente " +
                "WHERE clienteID = " + id;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                clienteId = new SimpleIntegerProperty( rs.getInt("clienteid"));
                nombre = new SimpleStringProperty(rs.getString("nombre"));
                apellidos = new SimpleStringProperty(rs.getString("apellidos"));
                direccion = new SimpleStringProperty(rs.getString("direccion"));
            }


            return new Cliente(clienteId, nombre, apellidos, direccion);
        }
        catch (java.sql.SQLException ex){


            ex.printStackTrace();
            System.out.println("SQLException:␣" + ex.getMessage());
            System.out.println("SQLState:␣" + ex.getSQLState());
            System.out.println("VendorError:␣" + ex.getErrorCode());

            return null;
        }
    }

    /*
     * BORRAR DATOS
     */

    public int deleteCliente(int id){

        String query = "delete from cliente where clienteID = " + id;

        int numRegs = 0;
        try {
            Statement stmt = connection.createStatement();
            numRegs = stmt.executeUpdate(query);

            System.out.println("Cantidad de registros afectados: " + numRegs);
        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:␣" + ex.getMessage());
            System.out.println("SQLState:␣" + ex.getSQLState());
            System.out.println("VendorError:␣" + ex.getErrorCode());
        }

        return numRegs;
    }

    /*
     * INSERTAR DATOS
     */

    public int insertCliente(String nombre, String apellidos, String direccion){

        String query = "insert into cliente(nombre, apellidos, direccion) " +
                "values ('" + nombre + "', '" + apellidos + "', '" + direccion + "')";



        int numRegs = 0;
        try {
            Statement stmt = connection.createStatement();
            numRegs = stmt.executeUpdate(query);

            System.out.println("Registros afectados: " + numRegs);

        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:␣" + ex.getMessage());
            System.out.println("SQLState:␣" + ex.getSQLState());
            System.out.println("VendorError:␣" + ex.getErrorCode());
        }

        return numRegs;
    }

    /* ACTUAIZAR DATOS */
    public int updateCliente(int id, String nombre, String apellidos, String direccion){

        String query = "UPDATE cliente " + "SET  nombre='" + nombre + "', apellidos='" + apellidos + "', direccion = '" + direccion + "'" +
                "WHERE clienteID = " + id;
        

        int numRegs = 0;
        try {
            Statement stmt = connection.createStatement();
            numRegs = stmt.executeUpdate(query);

            System.out.println("Registros afectados: " + numRegs);

        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:␣" + ex.getMessage());
            System.out.println("SQLState:␣" + ex.getSQLState());
            System.out.println("VendorError:␣" + ex.getErrorCode());
        }

        return numRegs;
    }






}
