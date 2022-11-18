/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.SignUp;

import application.Application;
import java.util.concurrent.TimeoutException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

/**
 *
 * @author Janam & UnaiB
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpControllerTest extends ApplicationTest {

    private TextField tfUsername = lookup("#tfUsername").query();

    private PasswordField pfPassword = lookup("#pfPassword").query();
    private PasswordField pfRepeatPassword = lookup("#pfRepeatPassword").query();

    private Button btnContinue = lookup("#btnContinue").query();

    private Label lblUsername = lookup("#lblUsername").query();
    private Label lblEmail = lookup("#lblEmail").query();
    private Label lblPassword = lookup("#lblPassword").query();
    private Label lblRepeatPassword = lookup("#lblRepeatPassword").query();

    /**
     * Set up Java FX fixture for tests. This is a general approach for using a
     * unique instance of the application in the test.
     *
     * @throws java.util.concurrent.TimeoutException
     */
    @BeforeClass
    public static void ClickApplicationTest() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Application.class);
    }

    /**
     * Test SignUp window is opened when SignUp Hyperlink is clicked
     */
    @Test
    //@Ignore
    public void Test_01_SignUpOpenedSignUpClick() {
        clickOn("#hlSignUp");
        verifyThat("#pnSignUp", isVisible());
    }

    /**
     * Test of initialize method, of class SignUpController.
     */
    @Test
    @Ignore
    public void Test_02_Initialize() {

        verifyThat("#tfUsername", hasText(""));
        assertEquals(lblUsername.getText(), "");

        verifyThat("#tfFullName", hasText(""));

        verifyThat("#tfEmail", hasText(""));
        assertEquals(lblEmail.getText(), "");

        verifyThat("#pfPassword", hasText(""));
        assertEquals(lblPassword.getText(), "");

        verifyThat("#pfRepeatPassword", hasText(""));
        assertEquals(lblRepeatPassword.getText(), "");

        verifyThat("#btnContinue", isDisabled());
    }

    /**
     * Test that button continue is disabled if username, fullname , email,
     * password or repeatpassword are empty.
     */
    @Test
    @Ignore
    public void Test_03_ContinueIsDisabled() {

        clickOn("#tfUsername");
        write("Lmt10hunk");
        verifyThat("#btnContinue", isDisabled());
        eraseText(9);

        clickOn("#tfFullName");
        write("Janam Rai");
        verifyThat("#btnContinue", isDisabled());
        eraseText(9);

        clickOn("#tfEmail");
        write("lucasjanamsmile@gmail.com");
        verifyThat("#btnContinue", isDisabled());
        eraseText(25);

        clickOn("#pfPassword");
        write("abcd*1234");
        verifyThat("#btnContinue", isDisabled());
        eraseText(9);

        clickOn("#pfRepeatPassword");
        write("abcd*1234");
        verifyThat("#btnContinue", isDisabled());
        eraseText(9);

        verifyThat("#btnContinue", isDisabled());
    }

    /**
     * Test that continue button is enable if username, fullname , email,
     * password and repeatpassword are filled.
     */
    @Test
    @Ignore
    public void Test_04_ContinueIsEnabled() {

        clickOn("#tfUsername");
        write("janamrai");

        clickOn("#tfFullName");
        write("Janam Rai");

        clickOn("#tfEmail");
        write("lucasjanamsmile@gmail.com");

        clickOn("#pfPassword");
        write("abcd*1234");

        clickOn("#pfRepeatPassword");
        write("abcd*1234");

        verifyThat("#btnContinue", isEnabled());

        // Delete everything
        clickOn("#tfUsername");
        eraseText(8);

        clickOn("#tfFullName");
        eraseText(9);

        clickOn("#tfEmail");
        eraseText(24);
        clickOn("#tfEmail");
        eraseText(1);

        clickOn("#pfPassword");
        eraseText(9);

        clickOn("#pfRepeatPassword");
        eraseText(9);
    }

    /**
     * Test that labels Works
     */
    @Test
    @Ignore
    public void Test_05_LabelsWorks() {

        clickOn("#tfUsername");
        write("Lmt10 ");
        assertEquals(lblUsername.getText(), "We do not allow spaces in this field.");
        write("hunk");
        assertEquals(lblUsername.getText(), "");

        clickOn("#tfEmail");
        write("lucasjanamsmile ");
        assertEquals(lblEmail.getText(), "We do not allow spaces in this field.");
        write("@gmail.com");
        assertEquals(lblEmail.getText(), "");

        clickOn("#pfPassword");
        write("abcd* ");
        assertEquals(lblPassword.getText(), "We do not allow spaces in this field.");
        write("1234");
        assertEquals(lblPassword.getText(), "");

        clickOn("#pfRepeatPassword");
        write("abcd* ");
        assertEquals(lblRepeatPassword.getText(), "We do not allow spaces in this field.");
        write("1234");
        assertEquals(lblRepeatPassword.getText(), "");

        // Delete everything
        clickOn("#tfUsername");
        eraseText(9);

        clickOn("#tfEmail");
        eraseText(24);
        clickOn("#tfEmail");
        eraseText(1);

        clickOn("#pfPassword");
        eraseText(9);

        clickOn("#pfRepeatPassword");
        eraseText(9);

    }

    /**
     * Test that signUp window is Closed when Continue Button is clicked
     */
    @Test
    //@Ignore
    public void Test_06_SignUp() {

        clickOn("#tfUsername");
        write("Lmt10hunk");

        clickOn("#tfFullName");
        write("Janam Rai");

        clickOn("#tfEmail");
        write("lucasjanamsmile@gmail.com");

        clickOn("#pfPassword");
        write("abcd*1234");

        clickOn("#pfRepeatPassword");
        write("abcd*1234");

        clickOn("#btnContinue");
        clickOn("Aceptar");
        verifyThat("#pnLogIn", isVisible());

        clickOn("#hlSignUp");
    }

    /**
     * Test that signUp window is Closed when Continue Button is clicked
     */
    @Test
    //@Ignore
    public void Test_07_UserAlreadyExistsExcetion() {

        clickOn("#tfUsername");
        write("Lmt10hunk");

        clickOn("#tfFullName");
        write("Janam Rai");

        clickOn("#tfEmail");
        write("lucasjanamsmile@gmail.com");

        clickOn("#pfPassword");
        write("abcd*1234");

        clickOn("#pfRepeatPassword");
        write("abcd*1234");

        clickOn("#btnContinue");
        verifyThat("User already exists", isVisible());
        
        clickOn("Aceptar");
    }

    /**
     * Test that Login window is opened when Login Hyperlink is clicked
     */
    @Test
    @Ignore
    public void Test_08_LogInWindowOpenedOnLogInClick() {

        clickOn("#hlLogin");
        verifyThat("#pnLogIn", isVisible());

        clickOn("#hlSignUp");
        verifyThat("#pnSignUp", isVisible());
    }
}
