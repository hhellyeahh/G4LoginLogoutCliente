/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.LogIn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.LogIn.LogInController;

/**
 *
 * @author Leire, Zulu
 */
public class LogInApplication extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Carga el documento FXML y obtiene un objeto Parent
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        //Crea una escena a partir del Parent
        Scene scene = new Scene(root);
        //Establece la escena en el escenario (Stage) y la muestra
        stage.setResizable(false);
        stage.setTitle("SignIn");
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
