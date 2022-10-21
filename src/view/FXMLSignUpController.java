/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.xml.stream.EventFilter;

/**
 *
 * @author unaib
 */
public class FXMLSignUpController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtFullName;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtRepeatPassword;
    @FXML
    private Hyperlink hlLogin;
    @FXML
    private Button btnContinue;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Tooltips
        txtUsername.setTooltip(new Tooltip("Username"));
        txtFullName.setTooltip(new Tooltip("Full name"));
        txtEmail.setTooltip(new Tooltip("Email"));
        txtPassword.setTooltip(new Tooltip("Password"));
        txtRepeatPassword.setTooltip(new Tooltip("Repeat password"));
        hlLogin.setTooltip(new Tooltip("Back to login"));

        //Set event handlers
        this.txtUsername.textProperty().addListener(this::handleFieldsTextChange);
        this.txtFullName.textProperty().addListener(this::handleFieldsTextChange);
        this.txtEmail.textProperty().addListener(this::handleFieldsTextChange);
        this.txtPassword.textProperty().addListener(this::handleFieldsTextChange);
        this.txtRepeatPassword.textProperty().addListener(this::handleFieldsTextChange);

        txtUsername.addEventFilter(KeyEvent.KEY_TYPED, evt -> {
            if (" ".equals(evt.getCharacter())) {
                evt.consume();
            }
        });
        txtEmail.addEventFilter(KeyEvent.KEY_TYPED, evt -> {
            if (" ".equals(evt.getCharacter())) {
                evt.consume();
            }
        });
        txtPassword.addEventFilter(KeyEvent.KEY_TYPED, evt -> {
            if (" ".equals(evt.getCharacter())) {
                evt.consume();
            }
        });
        txtRepeatPassword.addEventFilter(KeyEvent.KEY_TYPED, evt -> {
            if (" ".equals(evt.getCharacter())) {
                evt.consume();
            }
        });
        //Disable continue button.
        btnContinue.setDisable(true);
    }

    private void handleFieldsTextChange(ObservableValue observable,
            String oldValue,
            String newValue) {
        //If any of these are empty the continue button will be disabled. If all of them are written it will be enabled.

    }

    /**
     * Handle Action event on Aceptar button
     *
     * @param event The action event object
     */
    @FXML
    private void handleHyperlinkLogInAction(ActionEvent event) {

    }

    /**
     * Handle Action event on Continue button
     *
     * @param event The action event object
     */
    @FXML
    private void handleButtonContinueAction(ActionEvent event) throws Exception {
        //Carga el documento FXML y obtiene un objeto Parent
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        //Crea una escena a partir del Parent
        Scene scene = new Scene(root);
        //Establece la escena en el escenario (Stage) y la muestra
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("SignUp");
        stage.getIcons().add(new Image("resources/img/icon.png"));
        stage.setScene(scene);
        stage.show();

    }
}
