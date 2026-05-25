/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.loginandregistration;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Tshilidzi
 */
public class LoginandRegistrationIT {
    
    public LoginandRegistrationIT() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of main method, of class LoginandRegistration.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        LoginandRegistration.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveUserToFile method, of class LoginandRegistration.
     */
    @Test
    public void testSaveUserToFile() {
        System.out.println("saveUserToFile");
        User u = null;
        boolean expResult = false;
        boolean result = LoginandRegistration.saveUserToFile(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsernameError method, of class LoginandRegistration.
     */
    @Test
    public void testGetUsernameError() {
        System.out.println("getUsernameError");
        String username = "";
        String expResult = "";
        String result = LoginandRegistration.getUsernameError(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPasswordErrors method, of class LoginandRegistration.
     */
    @Test
    public void testGetPasswordErrors() {
        System.out.println("getPasswordErrors");
        String password = "";
        List<String> expResult = null;
        List<String> result = LoginandRegistration.getPasswordErrors(password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPhoneError method, of class LoginandRegistration.
     */
    @Test
    public void testGetPhoneError() {
        System.out.println("getPhoneError");
        String cell = "";
        String expResult = "";
        String result = LoginandRegistration.getPhoneError(cell);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkUsername method, of class LoginandRegistration.
     */
    @Test
    public void testCheckUsername() {
        System.out.println("checkUsername");
        String username = "";
        boolean expResult = false;
        boolean result = LoginandRegistration.checkUsername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkRecipientCell method, of class LoginandRegistration.
     */
    @Test
    public void testCheckRecipientCell() {
        System.out.println("checkRecipientCell");
        String cell = "";
        boolean expResult = false;
        boolean result = LoginandRegistration.checkRecipientCell(cell);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashPassword method, of class LoginandRegistration.
     */
    @Test
    public void testHashPassword() {
        System.out.println("hashPassword");
        String password = "";
        String expResult = "";
        String result = LoginandRegistration.hashPassword(password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkLogin method, of class LoginandRegistration.
     */
    @Test
    public void testCheckLogin() {
        System.out.println("checkLogin");
        String username = "";
        String password = "";
        String phone = "";
        boolean expResult = false;
        boolean result = LoginandRegistration.checkLogin(username, password, phone);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
