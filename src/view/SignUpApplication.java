/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author unaib
 */
public class SignUpApplication extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Carga el documento FXML y obtiene un objeto Parent
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        //Crea una escena a partir del Parent
        Scene scene = new Scene(root);
        //Establece la escena en el escenario (Stage) y la muestra
        stage.setResizable(false);
        stage.setTitle("SignUp");
        stage.getIcons().add(new Image("resources/img/icon.png"));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
