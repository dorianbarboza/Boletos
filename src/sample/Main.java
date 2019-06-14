package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.SQLException;
import componentesPersonalizados.BotonPersonalizado;

import static java.awt.Color.BLACK;

// TABLE VIEW
import javafx.scene.control.TableView;


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
        hb.setStyle("-fx-background-color:BLACK");
        Text textoEncabezado = new Text("CRUD");
        textoEncabezado.setFill(Color.WHITE);
        textoEncabezado.setFont(Font.font("Arial"));

        textoEncabezado.getY();

        hb.getChildren().add(textoEncabezado);
        return hb;
    }

    private GridPane _body() {
        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(30);
        gp.setPadding(new Insets(0,10,0,10));
        gp.setStyle("-fx-background-color:POWDERBLUE");


        // Table view

        TableView table = new TableView();
        table.setEditable(true);
        TableColumn clienteID = new TableColumn("ID");
        TableColumn nombre = new TableColumn("Nombre");
        TableColumn apellidos = new TableColumn("Apellidos");
        TableColumn direccion = new TableColumn("Direccion");
        table.getColumns().addAll(clienteID, nombre, apellidos, direccion);
        //table.setPrefSize(1000,1000);
        gp.add(table,1,5);

        /*
         *  TABLA CLIENTE
         * */

        Label txtTablaCliente = new Label ("Tabla Cliente");
        gp.add(txtTablaCliente,3,1);


        // ID
        Label txtID = new Label ("ID");
        TextField campoID = new TextField();
        gp.add(txtID,1,2);
        gp.add(campoID,1,3);

        // Nombre
        Label txtNombre = new Label ("Nombre");
        TextField campoNombre = new TextField();
        gp.add(txtNombre,2,2);
        gp.add(campoNombre,2,3);

        // Apellido
        Label txtApellido = new Label ("Apellido");
        TextField campoApellido = new TextField();
        gp.add(txtApellido,3,2);
        gp.add(campoApellido,3,3);

        // Direccion
        Label txtDireccion = new Label ("Direccion");
        TextField campoDireccion = new TextField();
        gp.add(txtDireccion,4,2);
        gp.add(campoDireccion,4,3);

        // Insertar
        //BotonPersonalizado insert = new BotonPersonalizado("Nuevo");
        Button insert = new Button("Nuevo");
        insert.setPrefSize(100,1);
        gp.add(insert,1,4);



        // Eliminar
        //BotonPersonalizado delete = new BotonPersonalizado("Eliminar");
        Button delete = new Button("Eliminar");
        delete.setPrefSize(100,4);
        gp.add(delete,2,4);

        // Buscar
        //BotonPersonalizado search = new BotonPersonalizado("Buscar");
        Button search = new Button("Buscar");
        search.setPrefSize(100,1);
        gp.add(search,3,4);

        // Actualizar
        //BotonPersonalizado update = new BotonPersonalizado("Actualizar");
        Button update = new Button("Actualizar");
        update.setPrefSize(100,1);
        gp.add(update,4,4);




        /*
         * Boton Insertar
         * */
        insert.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Boton presionado NUEVO");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);

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

                alert.setTitle("Aviso: Registrar");

                if (nombre.trim().length() > 0) {
                    OperacionesClientes opCliente = new OperacionesClientes(accesoBD.getConnection());
                    Cliente regCliente = opCliente.getCliente(14);
                    opCliente.insertCliente(nombre, apellido, direccion);
                    alert.setHeaderText("Exitoso");
                    alert.setContentText("Registrado");
                    alert.showAndWait();
                }
                else {
                    alert.setHeaderText("ERROR");
                    alert.setContentText("No se pudo registrar");
                    alert.showAndWait();
                }


            }
        });


        /*
         * Boton Eliminar
         * */
        delete.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Boton presionado ELIMINAR");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                DBManager accesoBD = null;
                try {
                    accesoBD = new DBManager();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                alert.setTitle("Aviso: Eliminar");

                if(campoID.getText().trim().length() > 0) {
                    int ID = Integer.parseInt(campoID.getText());
                    OperacionesClientes delete = new OperacionesClientes(accesoBD.getConnection());

                    delete.deleteCliente(ID);
                    alert.setHeaderText("Exitoso");
                    alert.setContentText("Registro eliminado");
                    alert.showAndWait();
                }
                else {
                    alert.setHeaderText("ERROR");
                    alert.setContentText("No se puedo eliminar");
                    alert.showAndWait();

                }


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
                ObservableList<String> items = buscarRegistro.ViewClientes();

                lvList.setItems(items);
                lvList.setMaxHeight(Control.USE_PREF_SIZE);
                gp.add(lvList,5,9);

            }
        });

        update.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Boton presionado Actualizar");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                DBManager accesoBD = null;
                try {
                    accesoBD = new DBManager();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                alert.setTitle("Aviso: Actualizar");


                if (campoNombre.getText().trim().length() > 0) {
                    String nombre = campoNombre.getText().toString();
                    String apellido = campoApellido.getText().toString();
                    String direccion = campoDireccion.getText().toString();
                    int ID = Integer.parseInt(campoID.getText());

                    OperacionesClientes edit = new OperacionesClientes(accesoBD.getConnection());
                    edit.updateCliente(ID, nombre, apellido, direccion);
                    alert.setHeaderText("Exitoso");
                    alert.setContentText("Registro actualizado");
                    alert.showAndWait();
                }
                else {
                    alert.setHeaderText("ERROR");
                    alert.setContentText("No se pudo actualizar");
                    alert.showAndWait();

                }


            }
        });

        /*
         *  TABLA SECCION
         * */
        /*
        Label txtTablaSeccion = new Label ("Tabla Seccion");
        gp.add(txtTablaSeccion,3,5);

        // ID
        Label txtSeccionID = new Label ("ID");
        TextField campoSeccionID = new TextField();
        gp.add(txtSeccionID,1,6);
        gp.add(campoSeccionID,1,7);

        // Descripcion
        Label txtDescripcion = new Label ("Descripcion");
        TextField campoDescripcion = new TextField();
        gp.add(txtDescripcion,2,6);
        gp.add(campoDescripcion,2,7);

        // Precio
        Label txtPrecio = new Label ("Precio");
        TextField campoPrecio = new TextField();
        gp.add(txtPrecio,3,6);
        gp.add(campoPrecio,3,7);

        // Insertar
        BotonPersonalizado insertSeccion = new BotonPersonalizado("Nuevo");
        insertSeccion.setPrefSize(70,1);
        gp.add(insertSeccion,1,8);



        // Eliminar
        BotonPersonalizado deleteSeccion = new BotonPersonalizado("Eliminar");
        deleteSeccion.setPrefSize(70,4);
        gp.add(deleteSeccion,2,8);

        // Buscar
        BotonPersonalizado searchSeccion = new BotonPersonalizado("Buscar");
        searchSeccion.setPrefSize(70,1);
        gp.add(searchSeccion,3,8);

        // Actualizar
        BotonPersonalizado updateSeccion = new BotonPersonalizado("Actualizar");
        updateSeccion.setPrefSize(70,1);
        gp.add(updateSeccion,4,8);

        */




        //  Boton InsertarSeccion

        /*
        insertSeccion.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {


                System.out.println("Boton presionado INSERTAR SECCION");
                String descripcion = campoDescripcion.getText().toString();
                int precio = Integer.parseInt(campoPrecio.getText());


                DBManager accesoBD = null;
                try {
                    accesoBD = new DBManager();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                OperacionesClientes opCliente = new OperacionesClientes(accesoBD.getConnection());
                Cliente regCliente = opCliente.getCliente(14);
                opCliente.insertSeccion(descripcion, precio);





            }
        });


        //  Boton EliminarSeccion

        deleteSeccion.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {


            }
        });


         // Boton BuscarSeccion

        searchSeccion.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {


            }
        });


          //  Boton ActualizarSeccion

        updateSeccion.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {


            }
        });

        */



        return gp;
    }

    public HBox _footer() {
        HBox fb = new HBox();
        fb.setPadding(new Insets(20));
        fb.setSpacing(15);
        fb.setStyle("-fx-background-color:BLACK");
        Text textoFooter = new Text("Dorian Barboza");
        textoFooter.setFill(Color.WHITE);
        textoFooter.getY();
        fb.getChildren().add(textoFooter);

        return fb;
    }


    public static void main(String[] args) {
        launch(args);
    }


}
