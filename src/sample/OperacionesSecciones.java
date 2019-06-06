package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.lang.String;

public class OperacionesSecciones {


    Connection connection;

    public OperacionesSecciones(Connection conn){
        this.connection = conn;
    }



/*
    public ObservableList ViewClientes(){


        int id = 0;
        String nombre = "";
        String apellidos = "";
        String direccion = "";

        //String query = "SELECT clienteID, nombre, apellidos, direccion" + "FROM cliente ";
        String query = "SELECT *" + "FROM cliente ";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ObservableList<String> items = FXCollections.observableArrayList();
            while (rs.next()) {
                id = rs.getInt("clienteID");
                nombre = rs.getString("nombre");
                apellidos = rs.getString("apellidos");
                String full = "ID "+ id + " "  + nombre + " " + apellidos +"\n_____________";
                items.add(full);
            }
            return items;

        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:␣" + ex.getMessage());
            System.out.println("SQLState:␣" + ex.getSQLState());
            System.out.println("VendorError:␣" + ex.getErrorCode());

            return null;
        }



    }*/


    /*
     * OBTENER DATOS
     */

    /*
    public Seccion getSeccion(int id){
        int seccionId = 0;
        String descripcion = "", precio =  "";

        String query = "SELECT seccionid, descripcion, precio " +
                "FROM seccion " +
                "WHERE seccionID = " + id;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                seccionId = rs.getInt("seccionid");
                descripcion = rs.getString("descripcion");
                precio = rs.getInt("precio");
            }

            System.out.println(clienteId + ", " + nombre + " " + apellidos + ", " + direccion);

            return new Seccion(seccionId, descripcion, precio);
        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:␣" + ex.getMessage());
            System.out.println("SQLState:␣" + ex.getSQLState());
            System.out.println("VendorError:␣" + ex.getErrorCode());

            return null;
        }
    }*/


    /*
     * BORRAR DATOS
     */
/*
    public int deleteCliente(int id){
        int clienteId;
        String nombre, apellidos, direccion;

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
*/
    /*
     * INSERTAR DATOS
     */

    public int insertSeccion(String descripcion, int precio){

        String query = "insert into seccion(descripcion, precio) " +
                "values ('" + descripcion + "', '" + precio + "')";

      /*  insert into cliente(nombre, apellidos, direccion
                values ('Jorge', 'Estrada', 'Lázaro Cárdenas 123')  */

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
