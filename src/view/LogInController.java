/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author 2dam
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Leire, Zulu
 */
public class LogInController implements Initializable {

    @FXML
    private TextField tfUsername;
    @FXML
    private TextField pfPassword;
    @FXML
    private Hyperlink hlSignUp;
    @FXML
    private Button btnAccept;

    /**
     * Hanle Action event on Aceptar Button
     *
     * @param event The Action event object
     */
    @FXML
    private void handleAcceptButtonAction(ActionEvent event) {

        try {
            if (this.tfUsername.getText().isEmpty() || this.pfPassword.getText().isEmpty()) {
                throw new Exception("The username and password fields are empty.");
            } else {
                Parent root = FXMLLoader.load(getClass().getResource("LogOut.fxml"));

                Scene scene = new Scene(root);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).showAndWait();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
