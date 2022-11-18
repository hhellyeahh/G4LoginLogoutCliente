/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.signUp;

import classes.*;
import factories.FactoryClient;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.logIn.LogInController;

/**
 *
 * @author unaijanam
 */
public class SignUpController {

    public Stage stage;
    private static final Logger LOGGER = Logger.getLogger("view");

    private static final String USERNAME_REGEX = "^[a-zñÑA-Z0-9]*$";
    private static final String FULLNAME_REGEX = "^[a-zA-Z]{1,} [a-zA-Z]{1,}$";
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD_REGEX = "^[A-Za-z\\d@$!%*#?&]{8,}$";
    private boolean passwordFieldCorrect = false;
    private boolean allFieldsFill = false;
    
    private User user;

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
    @FXML
    private Pane pnSignUp;
    @FXML
    private Label labelSignUp;

    /**
     * The Parent object representing root node of view graph.
     *
     * @param root
     */
    public void initialize(Parent root) {
        Scene scene = new Scene(root);
        //Not a resizable window.
        stage.setResizable(false);
        //Modal window of LogIn.
        stage.initModality(Modality.APPLICATION_MODAL);
        //The window title will be ”SignUp”
        stage.setTitle("SignUp");
        //Add a leaf icon.
        stage.getIcons().add(new Image("resources/icon.png"));
        //Add scene
        stage.setScene(scene);
        //Show window
        stage.show();

        //For each field add tooltips with the same name as the prompt text.
        tfUsername.setTooltip(new Tooltip("Username"));
        tfFullName.setTooltip(new Tooltip("Name and Surname"));
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
        this.hlLogin.setOnAction(this::handleHyperlinkLogInAction);
        this.btnContinue.setOnAction(this::handleButtonContinueAction);

        /**
         * Do not allow input spaces, when the user writes a space a red text
         * will appear below the text field with a “We don't allow spaces in
         * this field.”, when another letter is written the text will disappear.
         */
        tfUsername.addEventFilter(KeyEvent.KEY_TYPED, evt -> {
            if (" ".equals(evt.getCharacter())) {
                evt.consume();
                lblUsername.setText("We do not allow spaces in this field.");
            }
        });
        tfEmail.addEventFilter(KeyEvent.KEY_TYPED, evt -> {
            if (" ".equals(evt.getCharacter())) {
                evt.consume();
                lblEmail.setText("We do not allow spaces in this field.");
            }
        });
        pfPassword.addEventFilter(KeyEvent.KEY_TYPED, evt -> {
            if (" ".equals(evt.getCharacter())) {
                evt.consume();
                lblPassword.setText("We do not allow spaces in this field.");
            }
        });
        pfRepeatPassword.addEventFilter(KeyEvent.KEY_TYPED, evt -> {
            if (" ".equals(evt.getCharacter())) {
                evt.consume();
                lblRepeatPassword.setText("We do not allow spaces in this field.");
            }
        });
        //Disable continue button.
        btnContinue.setDisable(true);

        //Set hand cursor on Continue button
        btnContinue.setCursor(Cursor.HAND);
    }

    /**
     * returns stage
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     *
     * Validates that user, fullname, email, password, and repeatpassword fields has any content to enable/disable
     * continue button.
     *
     * @param observable The value being observed.
     * @param oldValue The old value of the observable.
     * @param newValue The new value of the observable.
     */
    public void handleFieldsTextChange(ObservableValue observable,
            String oldValue,
            String newValue) {
        /**
         * Maximum character permitted in username field will be 20, fullname
         * and email 30 and password fields 8 minimum, 24 maximum
         */
        if (tfUsername.getText().length() > 20) {
            tfUsername.setText(tfUsername.getText().substring(0, 20));
        }
        if (tfEmail.getText().length() > 30) {
            tfEmail.setText(tfEmail.getText().substring(0, 30));
        }
        if (tfFullName.getText().length() > 30) {
            tfFullName.setText(tfFullName.getText().substring(0, 30));
        }
        if (pfPassword.getText().length() > 24) {
            pfPassword.setText(pfPassword.getText().substring(0, 24));
        }
        if (pfRepeatPassword.getText().length() > 24) {
            pfRepeatPassword.setText(pfRepeatPassword.getText().substring(0, 24));
        }

        //Control label texts, when another letter is written the text will disappear.
        if (!(tfUsername.getText().equals(oldValue)) || !(tfEmail.getText().equals(oldValue)) || !(pfPassword.getText().equals(oldValue)) || !(pfRepeatPassword.getText().equals(oldValue))) {
            lblUsername.setText("");
            lblEmail.setText("");
            lblPassword.setText("");
            lblRepeatPassword.setText("");
        }

        /**
         * If the password on both fields is not the same, the background of
         * both will change to red color and a red text will appear below the
         * text field with a “Passwords are not the same.”, when it's the same,
         * the background will recover the normal color and the label will
         * disappear. Continue button will only be enabled when both fields have
         * the same password.
         */
        if (!pfRepeatPassword.getText().isEmpty()) {
            if (!(pfPassword.getText().equals(pfRepeatPassword.getText()))) {
                pfPassword.setStyle("-fx-control-inner-background: #ff7f7f;");
                pfRepeatPassword.setStyle("-fx-control-inner-background: #ff7f7f;");
                lblPassword.setText("Passwords do not match.");
                passwordFieldCorrect = false;
            } else {
                pfPassword.setStyle("-fx-control-inner-background: white;");
                pfRepeatPassword.setStyle("-fx-control-inner-background: white;");
                passwordFieldCorrect = true;
            }
        }

        /**
         * If the password fields have less than 8 characters a red text will
         * appear below the text field with an “At least 8 characters are
         * needed”.
         */
        if (!pfPassword.getText().isEmpty()) {
            if (pfPassword.getText().length() < 8) {
                lblPassword.setText("At least 8 characters are needed.");
            }
        }
        if (!pfRepeatPassword.getText().isEmpty()) {
            if (pfRepeatPassword.getText().length() < 8) {
                lblRepeatPassword.setText("At least 8 characters are needed.");
            } else if (pfPassword.getText().length() == 8) {
                lblRepeatPassword.setText("");
            }
        }

        /**
         * If any of the fields are empty the continue button will be disabled.
         * If all of them are written it will be enabled.
         */
        if (tfUsername.getText().isEmpty() || tfFullName.getText().isEmpty() || tfEmail.getText().isEmpty() || pfPassword.getText().isEmpty() || pfRepeatPassword.getText().isEmpty()) {
            allFieldsFill = false;
            pfPassword.setStyle("-fx-control-inner-background: white;");
            pfRepeatPassword.setStyle("-fx-control-inner-background: white;");
        } else {
            allFieldsFill = true;
        }

        // Enable Continue button when all fields are fill and passwords are the same 
        if (passwordFieldCorrect && allFieldsFill) {
            btnContinue.setDisable(false);
        } else {
            btnContinue.setDisable(true);
        }
    }

    /**
     * Handle Action event on Hyperlink Login button
     *
     * @param event The action event object
     */
    private void handleHyperlinkLogInAction(ActionEvent event) {

        //Close the SignUp and open the login window.
        try {
            Stage stage = (Stage) this.hlLogin.getScene().getWindow();
            stage.close();
        } catch (Exception ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
            Alert windowNotFoundAlert = new Alert(Alert.AlertType.ERROR);
            windowNotFoundAlert.setHeaderText(null);
            windowNotFoundAlert.setTitle("Error");
            windowNotFoundAlert.setContentText("Ventana no encontrada");
            windowNotFoundAlert.showAndWait();
        }
    }

    /**
     * Handle Action event on Continue button
     *
     * @param event The action event object
     */
    public void handleButtonContinueAction(ActionEvent event) {
        try {
            //The username (text field) and full name text field will not allow special characters
            if (!this.tfUsername.getText().matches(USERNAME_REGEX)) {
                throw new Exception("Username field do not admit special characters.");
            }
            /**
             * The fullname text field and full name text field will not allow
             * special characters, on the other hand it will be checked that
             * there are no spaces before or after, and there is only name and
             * first surname.
             */
            if (!this.tfFullName.getText().trim().matches(FULLNAME_REGEX)) {
                throw new Exception("Fullname field should be <name (space) surname>.");
            }
            //Email text field will be validated with an email pattern.
            if (!this.tfEmail.getText().matches(EMAIL_REGEX)) {
                throw new Exception("Email field does not correspond. \nExample: example@example.example");
            }
            //Password field and repeat password password field will allow special characters.
            if (!this.pfPassword.getText().matches(PASSWORD_REGEX)) {
                throw new Exception("The password can only use this special characters [@$!%*#?&]");
            }

            //The information of all text fields will be collected, validated, and stored in an object of type User.
            User newUser = new User();
            newUser.setLogin(tfUsername.getText().toLowerCase());
            newUser.setFullName(tfFullName.getText().trim());
            newUser.setEmail(tfEmail.getText());
            newUser.setPassword(pfPassword.getText());
            newUser.setPrivilege(UserPrivilege.USER);
            newUser.setStatus(UserStatus.ENABLE);

            LoginLogout clientLoginLogout = null;

            try {
                clientLoginLogout = FactoryClient.getLoginLogout();
                clientLoginLogout.signUp(newUser);

            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
                //LOGGER.log(Level.SEVERE, ex.getMessage());
            }

            //It will show an alert that the user signed up correctly. We will close this window and open the login window.
            new Alert(Alert.AlertType.INFORMATION, "User created correctly", ButtonType.OK).showAndWait();            
            
            //Close the stage
            Stage stage = (Stage) this.btnContinue.getScene().getWindow();
            stage.close();
            
        } catch (Exception e) {
            //If there is any error, the exception that has been received will be managed by an alert.
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).showAndWait();
        }
    }
}
