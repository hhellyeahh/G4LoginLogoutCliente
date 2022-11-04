/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.SignUp;

import java.util.concurrent.TimeoutException;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;
import view.LogIn.LogInApplication;

/**
 *
 * @author Janam
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpControllerTest extends ApplicationTest {

    /**
     * Set up Java FX fixture for tests. This is a general approach for using a
     * unique instance of the application in the test.
     *
     * @throws java.util.concurrent.TimeoutException
     */
    @BeforeClass
    public static void ClickApplicationTest() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(LogInApplication.class);
    }

    /**
     * Test SignUp window is opened when SignUp Hyperlink is clicked
     */
    @Test
    public void Test_01_SignUpOpenedSignUpClick() {
        clickOn("#hlSignUp");
        verifyThat("#pnSignUp", isVisible());
    }

    /**
     * Test of initialize method, of class SignUpController.
     */
    @Test
    public void Test_02_Initialize() {

        verifyThat("#tfUsername", hasText(""));
        verifyThat("#lblUsername", hasText(""));

        verifyThat("#tfFullName", hasText(""));

        verifyThat("#tfEmail", hasText(""));
        verifyThat("#lblEmail", hasText(""));

        verifyThat("#pfPassword", hasText(""));
        verifyThat("#lblPassword", hasText(""));

        verifyThat("#pfRepeatPassword", hasText(""));
        verifyThat("#lblRepeatPassword", hasText(""));

        verifyThat("#btnContinue", isDisabled());
    }

    /**
     * Test that button continue is disabled if username, fullname , email,
     * password or repeatpassword are empty.
     */
    @Test
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
    public void Test_04_ContinueIsEnabled() {

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

        verifyThat("#btnContinue", isEnabled());
    }

    /**
     * Test that labels Works
     */
    @Test
    public void Test_05_LabelsWorks() {

        clickOn("#tfUsername");
        write("Janam ");
        verifyThat("#lblUsername", hasText("We don't allow spaces in this field."));
        write("Rai");
        verifyThat("#lblUsername", hasText(""));

        clickOn("#tfEmail");
        write("Lucasjanamsmile ");
        verifyThat("#lblEmail", hasText("We don't allow spaces in this field."));
        write("@gmail.com");
        verifyThat("#lblEmail", hasText(""));

        clickOn("#pfPassword");
        write("abcd* ");
        verifyThat("#lblPassword", hasText("We don't allow spaces in this field."));
        write("1234");
        verifyThat("#lblPassword", hasText(""));

        clickOn("#pfRepeatPassword");
        write("abcd* ");
        verifyThat("#lblRepeatPassword", hasText("We don't allow spaces in this field."));
        write("1234");
        verifyThat("#lblRepeatPassword", hasText(""));
    }

    /**
     * Test that signUp window is Closed when Continue BUtton is clicked
     */
    @Test
    public void Test_06_SignUpWindowClosedOnContinueClick() {

    }

    /**
     * Test that Login window is opened when Login Hyperlink is clicked
     */
    @Test
    public void Test_07_LogInWindowOpenedOnLogInClick() {

        clickOn("#hlLogin");
        verifyThat("#pnLogIn", isVisible());
    }
}
