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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import javax.xml.stream.EventFilter;

/**
 *
 * @author unaib
 */
public class FXMLSignUpController implements Initializable {

    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfFullName;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private PasswordField pfRepeatPassword;
    @FXML
    private Hyperlink hlLogin;
    @FXML
    private Button btnContinue;
    @FXML
    private Label lblUsername;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblPassword;
    @FXML
    private Label lblRepeatPassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Tooltips
        tfUsername.setTooltip(new Tooltip("Username"));
        tfFullName.setTooltip(new Tooltip("Full name"));
        tfEmail.setTooltip(new Tooltip("Email"));
        pfPassword.setTooltip(new Tooltip("Password"));
        pfRepeatPassword.setTooltip(new Tooltip("Repeat password"));
        hlLogin.setTooltip(new Tooltip("Back to login"));

        //Set event handlers
        this.tfUsername.textProperty().addListener(this::handleFieldsTextChange);
        this.tfFullName.textProperty().addListener(this::handleFieldsTextChange);
        this.tfEmail.textProperty().addListener(this::handleFieldsTextChange);
        this.pfPassword.textProperty().addListener(this::handleFieldsTextChange);
        this.pfRepeatPassword.textProperty().addListener(this::handleFieldsTextChange);

        tfUsername.addEventFilter(KeyEvent.KEY_TYPED, evt -> {
            if (" ".equals(evt.getCharacter())) {
                evt.consume();
                lblUsername.setText("We don't allow spaces in this field.");
            }
        });
        tfEmail.addEventFilter(KeyEvent.KEY_TYPED, evt -> {
            if (" ".equals(evt.getCharacter())) {
                evt.consume();
                lblEmail.setText("We don't allow spaces in this field.");
            }
        });
        pfPassword.addEventFilter(KeyEvent.KEY_TYPED, evt -> {
            if (" ".equals(evt.getCharacter())) {
                evt.consume();
                lblPassword.setText("We don't allow spaces in this field.");
            }
        });
        pfRepeatPassword.addEventFilter(KeyEvent.KEY_TYPED, evt -> {
            if (" ".equals(evt.getCharacter())) {
                evt.consume();
                lblRepeatPassword.setText("We don't allow spaces in this field.");
            }
        });
        //Disable continue button.
        btnContinue.setDisable(true);
    }

    private void handleFieldsTextChange(ObservableValue observable,
            String oldValue,
            String newValue) {
        //Maximum character permitted in username field will be 20, fullname and email 30 and password fields 8 minimum, 24 maximum
        if (tfUsername.getText().length() > 20) {
            String s = tfUsername.getText().substring(0, 20);
            tfUsername.setText(s);
        }
        if (tfEmail.getText().length() > 30) {
            String s = tfEmail.getText().substring(0, 30);
            tfEmail.setText(s);
        }
        if (tfFullName.getText().length() > 30) {
            String s = tfFullName.getText().substring(0, 30);
            tfFullName.setText(s);
        }
        if (pfPassword.getText().length() > 24) {
            String s = pfPassword.getText().substring(0, 24);
            pfPassword.setText(s);
        }
        if (pfRepeatPassword.getText().length() > 24) {
            String s = pfRepeatPassword.getText().substring(0, 24);
            pfRepeatPassword.setText(s);
        }

        //If any of the fields are empty the continue button will be disabled. If all of them are written it will be enabled.
        if (tfUsername.getText().isEmpty() || tfFullName.getText().isEmpty() || tfEmail.getText().isEmpty() || pfPassword.getText().isEmpty() || pfRepeatPassword.getText().isEmpty()) {
            btnContinue.setDisable(true);
            pfPassword.setStyle("-fx-control-inner-background: white;");
            pfRepeatPassword.setStyle("-fx-control-inner-background: white;");
        } else {
            btnContinue.setDisable(false);
        }

        //control de textos de label
        if (!(tfUsername.getText().equals(oldValue)) || !(tfEmail.getText().equals(oldValue)) || !(pfPassword.getText().equals(oldValue)) || !(pfRepeatPassword.getText().equals(oldValue))) {
            lblUsername.setText("");
            lblEmail.setText("");
            lblPassword.setText("");
            lblRepeatPassword.setText("");
        }

        //If the password on both fields is not the same, the background of both will change to red colour and a red text will 
        //appear below the text field with a “Passwords are not the same.”, when it's the same, the background will recover the 
        //normal colour and the label will disappear. Continue button will only be enabled when both fields have the same password.
        if (!pfRepeatPassword.getText().isEmpty()) {
            if (!(pfPassword.getText().equals(pfRepeatPassword.getText()))) {
                pfPassword.setStyle("-fx-control-inner-background: #ff7f7f;");
                pfRepeatPassword.setStyle("-fx-control-inner-background: #ff7f7f;");
                lblPassword.setText("Passwords do not match.");
                btnContinue.setDisable(true);
            } else {
                pfPassword.setStyle("-fx-control-inner-background: white;");
                pfRepeatPassword.setStyle("-fx-control-inner-background: white;");
                btnContinue.setDisable(false);
            }
        }

        //If the password fields have less than 8 characters a red text will appear below the text field with a “At least 8 characters are needed.”, 
        //when the user tries to write more than 24 characters a red text will appear below the text field with a “Only 24 character maximum.”
        if (!pfPassword.getText().isEmpty()) {
            if (pfPassword.getText().length() < 8) {
                lblPassword.setText("At least 8 characters are needed.");
            }

            if (pfPassword.getText().length() > 23) {
                lblPassword.setText(" Only 24 character maximum");
            }
        }
        if (!pfRepeatPassword.getText().isEmpty()) {
            if (pfRepeatPassword.getText().length() < 8) {
                lblRepeatPassword.setText("At least 8 characters are needed.");
            } else if (pfPassword.getText().length() == 8) {
                lblRepeatPassword.setText("");
            }

            if (pfRepeatPassword.getText().length() > 23) {
                lblRepeatPassword.setText(" Only 24 character maximum");
            }
        }
        
        
    }

    /**
     * Handle Action event on Aceptar button
     *
     * @param event The action event object
     */
    @FXML
    private void handleHyperlinkLogInAction(ActionEvent event) {
        Stage stage = (Stage) this.hlLogin.getScene().getWindow();
        stage.close();
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
