/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.LogIn;

import application.Application;
import java.util.concurrent.TimeoutException;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 * Testing class for Login view and controller
 * Tests login view behavior using TestFX framework
 * @author Leire
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LogInControllerTest extends ApplicationTest {
    
    private TextField tfUsername = lookup("#tfUsername").query();
    
    private Label lblUsername = lookup("#lblUsername").query();
    
    private PasswordField pfPassword = lookup("#pfPassword").query();
    
    private Label lblPassword = lookup("#lblPassword").query();
    
    private Button btnLogIn = lookup("#btnLogIn").query();
    
    private Pane pnLogOut;
    
    private Pane pnSignUp;

    /**
     * Starts application to be tested
     * @param stage Primary Stage object
     * @throws Exception  if there is any error
     */
    @Override
    public void start(Stage stage) throws Exception {
        new Application().start(stage);
    }

    /**
     * Set up Java FX fixture for tests. This is a general approach for using a 
     * unique instance of the application in the test.
     * @throws java.util.concurrent.TimeoutException
     */
    @BeforeClass
    public static void ClickApplicationTest() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Application.class);
    }

    /**
     * Test of initial state of LogIn view.
     */
    @Test
    public void test1_LogInViewInitialState() {
        assertEquals(tfUsername.getText(), "");
        assertEquals(lblUsername.getText(), "");
        assertEquals(pfPassword.getText(), "");
        assertEquals(lblPassword.getText(), "");
        verifyThat("#btnLogIn", isDisabled());       
    }

    /**
     * Test that button Log In is enabled.
     */
    @Test
    public void test2_LogInButtonIsEnabled() {
        clickOn("#tfUsername");
        write("LeireCarrasco");
        verifyThat("#btnLogIn", isDisabled());
        clickOn("#pfPassword");
        write("abcd*1234");
        verifyThat("#btnLogIn", isEnabled());
    }

    /**
     * Test that button Log In is disabled when user and password fields are not
     * full.
     */
    @Test
    public void test3_LogInButtonIsDisabled() {
        clickOn("#tfUsername");
        write("LeireCarrasco");
        verifyThat("#btnLogIn", isDisabled());
        eraseText(13);
        clickOn("#pfPassword");
        write("abcd*1234");
        verifyThat("#btnLogIn", isDisabled());
        eraseText(9);
        verifyThat("#btnLogIn", isDisabled());
    }

    /**
     * Test that labels works.
     */
    @Test
    public void test4_LabelControl() {
        clickOn("#tfUsername");
        write("Leire ");
        verifyThat("#lblUsername", hasText("We don't allow spaces in this field."));
        write("Carrasco");

        verifyThat("#lblUsername", hasText(""));

        clickOn("#pfPassword");
        write("abcd* ");
        verifyThat("#lblPassword", hasText("We don't allow spaces in this field."));
        write("1234");

        verifyThat("#lblPassword", hasText(""));
    }

    /**
     * Test LogOut view is opened when button Log In is clicked
     */
    @Test
    public void test5_LogOutOpenedLogInClick() {
        clickOn("#tfUsername");
        write("JanamRai");
        clickOn("#pfPassword");
        write("abcd*1234");
        clickOn("#btnLogIn");
        verifyThat("#pnLogOut", isVisible());
    }
    
    /**
     * Test LogOut view is not opened when button Log In is clicked
     */
    @Test
    public void test6_LogOutOpenedLogInClick() {
        clickOn("#tfUsername");
        write("LeireCarrasco");
        clickOn("#pfPassword");
        write("abcd*1234");
        clickOn("#btnLogIn");
        verifyThat("", isVisible());
        clickOn("Aceptar");
    }

    /**
     * Test LogOut view is opened when button Log In is clicked
     */
    @Test
    public void test7_SignUpOpenedSignUpClick() {
        clickOn("#hlSignUp");
        verifyThat("#pnSignUp", isVisible());
    }
}
