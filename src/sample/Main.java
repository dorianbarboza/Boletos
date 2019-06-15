package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.util.ArrayList;


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

        primaryStage.setScene(new Scene(ventanaPrincipal, 800, 800));
        primaryStage.show();
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


        Label txtTablaCliente = new Label ("Tabla Cliente");
        gp.add(txtTablaCliente,4,1);


        // ID
        Label txtID = new Label ("ID");
        TextField campoID = new TextField();
        gp.add(txtID,2,2);
        gp.add(campoID,2,3);

        // Nombre
        Label txtNombre = new Label ("Nombre");
        TextField campoNombre = new TextField();
        gp.add(txtNombre,3,2);
        gp.add(campoNombre,3,3);

        // Apellido
        Label txtApellido = new Label ("Apellido");
        TextField campoApellido = new TextField();
        gp.add(txtApellido,4,2);
        gp.add(campoApellido,4,3);

        // Direccion
        Label txtDireccion = new Label ("Direccion");
        TextField campoDireccion = new TextField();
        gp.add(txtDireccion,5,2);
        gp.add(campoDireccion,5,3);

        // Insertar
        //BotonPersonalizado insert = new BotonPersonalizado("Nuevo");
        Button insert = new Button("Nuevo");
        insert.setPrefSize(100,1);
        gp.add(insert,2,4);



        // Eliminar
        //BotonPersonalizado delete = new BotonPersonalizado("Eliminar");
        Button delete = new Button("Eliminar");
        delete.setPrefSize(100,4);
        gp.add(delete,3,4);

        // Buscar
        //BotonPersonalizado search = new BotonPersonalizado("Buscar");
        Button search = new Button("Buscar");
        search.setPrefSize(100,1);
        gp.add(search,4,4);

        // Actualizar
        //BotonPersonalizado update = new BotonPersonalizado("Actualizar");
        Button update = new Button("Actualizar");
        update.setPrefSize(100,1);
        gp.add(update,5,4);




        /* BOTON INSERTAR */

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

        /* BOTON ELIMINAR */

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

        /* BOTON BUSCAR */

        search.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                DBManager accesoBD = null;
                try {
                    accesoBD = new DBManager();
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                OperacionesClientes search = new OperacionesClientes(accesoBD.getConnection());
                ArrayList<Cliente> arrayList;
                arrayList = search.ViewClientes();
                TableView tab = new TableView();
                TableColumn columID = new TableColumn("ID");
                columID.setCellValueFactory(new PropertyValueFactory<Cliente,String>("clienteId"));
                TableColumn columNombre = new TableColumn("Nombre");
                columNombre.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombre"));
                TableColumn columApellido = new TableColumn ("Apellido");
                columApellido.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellidos"));
                TableColumn columDireccion = new TableColumn ("Direccion");
                columDireccion.setCellValueFactory(new PropertyValueFactory<Cliente,String>("direccion"));

                tab.getColumns().addAll(columID, columNombre, columApellido, columDireccion);


                ObservableList<Cliente> list = FXCollections.observableArrayList();
                list.removeAll();
                list.addAll(arrayList);
                tab.setItems(list);
                gp.add(tab,2,6,6,6);
            }
        });

        /* BOTON ACTUALIZAR */

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
                    alert.setHeaderText("ERROR: No se pudo actualizar");
                    alert.showAndWait();

                }


            }
        });

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
