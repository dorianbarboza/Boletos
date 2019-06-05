package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.SQLException;
import componentesPersonalizados.BotonPersonalizado;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        BorderPane ventanaPrincipal = new BorderPane();
        primaryStage.setTitle("Ventana principal");

        HBox Header = _header();
        GridPane Body = _body();
        HBox Footer = _footer();

        ventanaPrincipal.setTop(Header);
        ventanaPrincipal.setCenter(Body);
        ventanaPrincipal.setBottom(Footer);

        primaryStage.setScene(new Scene(ventanaPrincipal, 1000, 1000));
        primaryStage.show();




        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();

        //DBManager accesoBD = new DBManager();
        //OperacionesClientes opCliente = new OperacionesClientes(accesoBD.getConnection());
        //Cliente regCliente = opCliente.getCliente(14);
        //System.out.println("Nuevo cliente: " + regCliente);
        // opCliente.deleteCliente(12);
        // opCliente.getCliente(12);
        //opCliente.insertCliente("Jorge", "Estrada", "Lázaro Cárdenas 123");



    }


    public HBox _header() {
        HBox hb = new HBox();
        hb.setPadding(new Insets(20));
        hb.setSpacing(15);
        Text valor = new Text("CRUD");
        valor.setFill(Color.BLACK);
        valor.getY();

        hb.getChildren().add(valor);
        return hb;
    }

    private GridPane _body() {
        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(30);
        gp.setPadding(new Insets(0,10,0,10));


        // ID

        Label txtID = new Label ("ID");
        TextField campoID = new TextField();
        gp.add(txtID,1,1);
        gp.add(campoID,1,2);

        // Nombre
        Label txtNombre = new Label ("Nombre");
        TextField campoNombre = new TextField();
        gp.add(txtNombre,2,1);
        gp.add(campoNombre,2,2);

        // Apellido
        Label txtApellido = new Label ("Apellido");
        TextField campoApellido = new TextField();
        gp.add(txtApellido,3,1);
        gp.add(campoApellido,3,2);

        // Direccion
        Label txtDireccion = new Label ("Direccion");
        TextField campoDireccion = new TextField();
        gp.add(txtDireccion,4,1);
        gp.add(campoDireccion,4,2);

        // Insertar
        BotonPersonalizado insert = new BotonPersonalizado("Nuevo");
        insert.setPrefSize(70,1);
        gp.add(insert,1,3);



        // Eliminar
        BotonPersonalizado delete = new BotonPersonalizado("Eliminar");
        delete.setPrefSize(70,3);
        gp.add(delete,2,3);

        // Buscar
        BotonPersonalizado search = new BotonPersonalizado("Buscar");
        search.setPrefSize(70,1);
        gp.add(search,3,3);

        // Actualizar
        BotonPersonalizado update = new BotonPersonalizado("Actualizar");
        update.setPrefSize(70,1);
        gp.add(update,4,3);


        /*
        * Boton Insertar
        * */
        insert.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Boton presionado NUEVO");
                String nombre = campoNombre.getText().toString();
                String apellido = campoApellido.getText().toString();
                String direccion = campoDireccion.getText().toString();
                System.out.println(nombre);
                System.out.println(apellido);
                System.out.println(direccion);


                DBManager accesoBD = null;
                try {
                    accesoBD = new DBManager();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                OperacionesClientes opCliente = new OperacionesClientes(accesoBD.getConnection());
                Cliente regCliente = opCliente.getCliente(14);
                opCliente.insertCliente(nombre, apellido, direccion);


            }
        });


        /*
         * Boton Eliminar
         * */
        delete.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Boton presionado ELIMINAR");

                int ID = Integer.parseInt(campoID.getText());

                DBManager accesoBD = null;
                try {
                    accesoBD = new DBManager();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                OperacionesClientes opCliente = new OperacionesClientes(accesoBD.getConnection());
                Cliente deleteCliente = opCliente.getCliente(14);
                opCliente.deleteCliente(ID);


            }
        });

        /*
         * Boton Buscar
         * */
        search.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Boton presionado Buscar");

                DBManager accesoBD = null;
                try {
                    accesoBD = new DBManager();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                OperacionesClientes buscarRegistro = new OperacionesClientes(accesoBD.getConnection());
                Cliente regCliente = buscarRegistro.getCliente(14);

                ListView<String> lvList = new ListView<String>();
                ObservableList<String> items = buscarRegistro.GetAllCliente();

                lvList.setItems(items);
                lvList.setMaxHeight(Control.USE_PREF_SIZE);
                gp.add(lvList,1,4);

            }
        });

        update.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Boton presionado Actualizar");
                int ID = Integer.parseInt(campoID.getText());
                String nombre = campoNombre.getText().toString();
                String apellido = campoApellido.getText().toString();
                String direccion = campoDireccion.getText().toString();
                System.out.println(ID);
                System.out.println(nombre);
                System.out.println(apellido);
                System.out.println(direccion);

                DBManager accesoBD = null;
                try {
                    accesoBD = new DBManager();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                OperacionesClientes opCliente = new OperacionesClientes(accesoBD.getConnection());
                Cliente updateCliente = opCliente.getCliente(14);
                opCliente.updateCliente(ID, nombre, apellido, direccion);


            }
        });


        return gp;
    }

    public HBox _footer() {
        HBox fb = new HBox();
        fb.setPadding(new Insets(20));
        fb.setSpacing(15);
        Text valor = new Text("Dorian Barboza");
        valor.setFill(Color.BLACK);
        valor.getY();
        fb.getChildren().add(valor);

        return fb;
    }


    public static void main(String[] args) {
        launch(args);
    }


}
