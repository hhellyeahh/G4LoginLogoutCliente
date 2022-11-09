/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.LogIn;

/**
 *
 * @author Leire, Zulu
 */
import view.LogOut.LogOutController;
import classes.*;
import factories.FactoryClient;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.SignUp.SignUpController;

/**
 *
 * @author Leire, Zulu
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
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void handleFieldsTextChange(ObservableValue observable,
            String oldValue,
            String newValue) {
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
            loader = new FXMLLoader(getClass().getResource("../SignUp/SignUp.fxml"));
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
            LOGGER.info("inicio de envio información al servidor");
            User loginUser = new User();
            loginUser.setLogin(tfUsername.getText());
            loginUser.setPassword(pfPassword.getText());

            LoginLogout clientLoginLogout = null;

            try {
                clientLoginLogout = FactoryClient.getLoginLogout();
                loginUser = clientLoginLogout.logIn(loginUser);

            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }

            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../LogOut/LogOut.fxml"));

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

    protected void showErrorAlert(String errorMsg) {
        //Shows error dialog.
        Alert alert = new Alert(Alert.AlertType.ERROR, errorMsg, ButtonType.OK);
        alert.showAndWait();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
