/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.logOut;

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
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    @FXML
    private Pane pnLogOut;

    public void initialize(Parent root) {
        //Tooltips
        this.tfMessage.setTooltip(new Tooltip("Message"));
        this.btnLogOut.setTooltip(new Tooltip("Log Out"));

        this.tfMessage.setText("Welcome back: " + user.getLogin() + ".");
        Scene scene = new Scene(root);

        //Modal window of LogIn.
        stage.initModality(Modality.WINDOW_MODAL);
        //Not a resizable window.
        stage.setResizable(false);
        //The window title will be ”LogOut”
        stage.setTitle("LogOut");
        //Add a leaf icon.
        stage.getIcons().add(new Image("resources/icon.png"));
        //Add scene
        stage.setScene(scene);
        //Show window
        stage.show();
    }

    //TODO botón cerrar alert
    /**
     * Handle Action event on LogOut button
     *
     * @param event The action event object
     * @throws IOException
     */
    @FXML
    public void handleLogOutButtonAction(ActionEvent event) throws IOException {

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
        this.user = user;
    }
}