/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.LogOut;

import classes.User;
import java.io.IOException;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.LogIn.IncorrectUserException;

/**
 *
 * @author Leire, Zulu
 */
public class LogOutController {

    private Stage stage;
    private User user;

    @FXML
    private TextField tfMessage;
    @FXML
    private Button btnLogOut;

    public void initialize(Parent root) {
        //Tooltips
        this.tfMessage.setTooltip(new Tooltip("Message"));
        this.btnLogOut.setTooltip(new Tooltip("Log Out"));

        this.tfMessage.setText("Welcome back: " + user.getLogin() + ".");
        Scene scene = new Scene(root);

        stage.initModality(Modality.WINDOW_MODAL);

        stage.setResizable(false);
        stage.setTitle("LogOut");
        stage.getIcons().add(new Image("resources/login/icon.png"));
        stage.setScene(scene);
        stage.show();
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
        } else if (option.get() == chooseExit) {
            Platform.exit();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initData(User user) {
        try {
            if (user == null) {
                throw new IncorrectUserException();
            }
            this.user = user;
        } catch (IncorrectUserException iue) {

        }
    }
}
