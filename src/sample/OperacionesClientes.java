package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class OperacionesClientes {
    Connection connection;

    public OperacionesClientes(Connection conn){
        this.connection = conn;
    }




    public ObservableList ViewClientes(){


        int id = 0;
        String nombre;
        String apellidos;
        String direccion;

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
                direccion = rs.getString("direccion");
                String full = "ID " + id + " " + nombre + " " + apellidos + " " + direccion + "\n--------";
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



    }


    /*
    * OBTENER DATOS
    */
    public Cliente getCliente(int id){
        int clienteId = 0;
        String nombre = "", apellidos = "", direccion = "";

        String query = "SELECT clienteid, nombre, apellidos, direccion " +
                "FROM cliente " +
                "WHERE clienteID = " + id;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                clienteId = rs.getInt("clienteid");
                nombre = rs.getString("nombre");
                apellidos = rs.getString("apellidos");
                direccion = rs.getString("direccion");
            }

            //System.out.println(clienteId + ", " + nombre + " " + apellidos + ", " + direccion);

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

    /*
     * INSERTAR DATOS
     */

    public int insertCliente(String nombre, String apellidos, String direccion){

        String query = "insert into cliente(nombre, apellidos, direccion) " +
                "values ('" + nombre + "', '" + apellidos + "', '" + direccion + "')";

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
