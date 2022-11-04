/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.LogIn.LogInController;

/**
 *
 * @author Leire
 */
public class Application extends javafx.application.Application{
    
    /**
     * 
     * @param stage
     * @throws Exception 
     */
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LogIn/LogIn.fxml"));
        
        Parent root = (Parent) loader.load();
        
        LogInController controller = (LogInController) loader.getController();

        controller.setStage(stage);

        controller.initialize(root);
        
        Scene scene = new Scene(root);

        stage.setResizable(false);
        stage.setTitle("LogIn");
        stage.getIcons().add(new Image("/resources/login/icon.png"));

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
