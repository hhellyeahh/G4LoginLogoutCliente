/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

/**
 *
 * @author Leire, Zulu
 */
public class LogOutController implements Initializable {

    @FXML
    private TextField tfMessage;
    @FXML
    private Button btnLogOut;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Tooltips
        this.tfMessage.setTooltip(new Tooltip("Message"));
        this.btnLogOut.setTooltip(new Tooltip("Log Out"));
        //this.tfMessage.setText(this.);
        //Set event handlers
    }

    //TODO boton cerrar alert
    @FXML
    private void handleLogOutButtonAction(ActionEvent event) throws IOException {

        ButtonType chooseLogOut = new ButtonType("Log out", ButtonBar.ButtonData.OK_DONE);
        ButtonType chooseExit = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(AlertType.NONE, "Do you want to log out or exit the application?", chooseLogOut, chooseExit);

        alert.setTitle("Log out or exit");
       
        Optional<ButtonType> option = alert.showAndWait();
      
        
        if (option.get() == chooseLogOut) {
            Stage stage = (Stage) this.btnLogOut.getScene().getWindow();
            stage.close();

            Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));

            Scene scene2 = new Scene(root);

            Stage stage2 = new Stage();
            stage2.setResizable(false);
            stage2.setScene(scene2);
            stage2.show(); 
            
        } else if (option.get() == chooseExit) {
            Platform.exit();
        }
    }
}
