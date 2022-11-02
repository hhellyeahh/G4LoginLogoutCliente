/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.LogOut;

import classes.User;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 *
 * @author Leire
 */
public class LogOutControllerTest {
    private User user;
    
    public LogOutControllerTest() {
    }

    /**
     * Test of initialize method, of class LogOutController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        Parent root = null;
        LogOutController instance = new LogOutController();
        instance.initialize(root);
        
        verifyThat("#tfMessage", hasText("Welcome back: " + user.getLogin() + "."));
        verifyThat("#btnLogOut", isEnabled());
        
        fail("The test case is a prototype.");
    }
    
    public void testHandleLogOutButtonAction() {
        System.out.println("handleLogOutButtonAction");
        ActionEvent event = null;
        LogOutController instance = new LogOutController();
       // instance.handleLogOutButtonAction(event);
        
        verifyThat("OK_DONE", NodeMatchers.isVisible());
        verifyThat("CANCEL_CLOSE", NodeMatchers.isVisible());
        
        
        fail("The test case is a prototype.");
    }
    

    /**
     * Test of setStage method, of class LogOutController.
     */
    @Test
    public void testSetStage() {
        System.out.println("setStage");
        Stage stage = null;
        LogOutController instance = new LogOutController();
        instance.setStage(stage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initData method, of class LogOutController.
     */
    @Test
    public void testInitData() {
        System.out.println("initData");
        User user = null;
        LogOutController instance = new LogOutController();
        instance.initData(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
