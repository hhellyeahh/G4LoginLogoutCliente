/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.logIn;

import view.logOut.LogOutController;
import classes.*;
import factories.FactoryClient;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import view.signUp.SignUpController;

/**
 *Controller UI class for Login view in users' management application. It contains 
 * event handlers and initialization code for the view defined in Login.fxml file.
 * @author LeireyZulu
 */
public class LogInController {

    private Stage stage;
    private static final Logger LOGGER = Logger.getLogger("view");

    @FXML
    private TextField tfUsername;
    @FXML
    private Label lblUsername;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Label lblPassword;
    @FXML
    private Hyperlink hlSignUp;
    @FXML
    private Button btnLogIn;
    @FXML
    private Pane pnLogIn;
    

    /**
     * Method for initializing Login Stage. 
     * @param root The Parent object representing root node of view graph.
     */
    public void initialize(Parent root) {
        LOGGER.info("initializing the window");

        //Tooltips
        tfUsername.setTooltip(new Tooltip("Username"));
        pfPassword.setTooltip(new Tooltip("Password"));
        hlSignUp.setTooltip(new Tooltip("Go to sign up"));
    
        //Set event handlers
        this.tfUsername.textProperty().addListener(this::handleFieldsTextChange);
        this.pfPassword.textProperty().addListener(this::handleFieldsTextChange);
        
        /**
         * this event is to ask for confirmation whenever a user press the 
         * "X" button on the stage in order to ask for confirmation
         * if the user press yes the app will close, if its says no, this event
         * will be consumed and the app will continue normally
         */
        stage.setOnCloseRequest((WindowEvent evt) -> {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(AlertType.NONE, "Do you want to log out or exit the application?", yes, no);
        alert.setTitle("Do you wish to exit?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == yes) {
                 Platform.exit();
        } else if (option.get() == no) {
            evt.consume();
        }
   
    
});

        /**
         * Do not allow input spaces, when the user writes a space a red text
         * will appear below the text field with a “We don't allow spaces in
         * this field.”, when another letter is written the text will disappear.
         */
        tfUsername.addEventFilter(KeyEvent.KEY_TYPED, evt -> {
            if (" ".equals(evt.getCharacter())) {
                evt.consume();
                lblUsername.setText("We don't allow spaces in this field.");
            }
        });

        pfPassword.addEventFilter(KeyEvent.KEY_TYPED, evt -> {
            if (" ".equals(evt.getCharacter())) {
                evt.consume();
                lblPassword.setText("We don't allow spaces in this field.");
            }
        });

        //Focus
        tfUsername.requestFocus();

        //Disable login button.
        this.btnLogIn.setDisable(true);
        LOGGER.info("window initialized");
    }

    /**
     * Text changed event handler. It validates that user and password fields 
     * has any content to enable/disable LogIn button.
     * @param observable The value being observed.
     * @param oldValue The old value of the observable.
     * @param newValue The new value of the observable.
     */
    private void handleFieldsTextChange(ObservableValue observable,
            String oldValue,
            String newValue) {

        /**
         * Maximum character permitted in username field will be 20, fullname
         * and email 30 and password fields 8 minimum, 24 maximum
         */
        if (tfUsername.getText().length() > 20) {
            tfUsername.setText(tfUsername.getText().substring(0, 20));
        }
        if (pfPassword.getText().length() > 24) {
            pfPassword.setText(pfPassword.getText().substring(0, 24));
        }
        //If any of these are empty the continue button will be disabled. 
        //If all of them are written it will be enabled.
        if (!(this.tfUsername.getText().equals(oldValue)) || !(this.pfPassword.getText().equals(oldValue))) {
            this.lblUsername.setText("");
            this.lblPassword.setText("");
        }

        //Enable login button.
        if (tfUsername.getText().isEmpty() || this.pfPassword.getText().isEmpty()) {
            this.btnLogIn.setDisable(true);
        } else {
            this.btnLogIn.setDisable(false);
        }

    }

    /**
     * Handle Action event on SignUp Hyperlink
     *
     * @param event The Action event object
     */
    @FXML
    private void handleSignUpHyperlinkAction(ActionEvent event) {
        LOGGER.info("Probando a abrir ventana de registro");
        try {
            Stage stage = new Stage();
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("/view/signUp/SignUp.fxml"));
            Parent root = (Parent) loader.load();
            SignUpController controller = (SignUpController) loader.getController();
            controller.setStage(stage);
            controller.initialize(root);

            LOGGER.info("ventana de registro abierta");

        } catch (IOException ex) {
            showErrorAlert("No se ha podido abrir la ventana");
            LOGGER.log(Level.SEVERE,
                    ex.getMessage());

        }
    }

    /**
     * Handle Action event on LogIn Button
     *
     * @param event The Action event object
     */
    @FXML
    private void handleLogInButtonAction(ActionEvent event) throws IOException, Exception {

        try {
            
            User loginUser = new User();
            loginUser.setLogin(tfUsername.getText());
            loginUser.setPassword(pfPassword.getText());
            LOGGER.info("Logging in user: " + tfUsername.getText()); // CHANGED THE LOGGER 
            LoginLogout clientLoginLogout = null;

            try {
                clientLoginLogout = FactoryClient.getLoginLogout();
                loginUser = clientLoginLogout.logIn(loginUser);

            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        LOGGER.info("User: " + tfUsername.getText() + " successfully logged in"); //LOG INFO WHEN A USER LOGS IN SUCCESFULLY

            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/logOut/LogOut.fxml"));

            Parent root = (Parent) loader.load();

            LogOutController controller = (LogOutController) loader.getController();

            controller.setStage(stage);

            controller.initData(loginUser);

            controller.initialize(root);

            tfUsername.setText("");
            pfPassword.setText("");
        } catch (Exception ex) {
            showErrorAlert(ex.getMessage());
            LOGGER.log(Level.SEVERE, ex.getMessage());

        }
    }

    /**
     * Show error alert
     * @param errorMsg Receive error string
     */
    protected void showErrorAlert(String errorMsg) {
        //Shows error dialog.
        LOGGER.severe(errorMsg); //LOGGED A SEVERE WITH THE ERRORMSG
        Alert alert = new Alert(Alert.AlertType.ERROR, errorMsg, ButtonType.OK);
        alert.showAndWait();
    }

    /**
     * Return the stage
     * @param stage 
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    

}
