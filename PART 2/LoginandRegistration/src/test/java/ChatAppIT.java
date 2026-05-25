/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

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
public class ChatAppIT {
    
    public ChatAppIT() {
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
     * Test of main method, of class ChatApp.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        ChatApp.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveUserToFile method, of class ChatApp.
     */
    @Test
    public void testSaveUserToFile() {
        System.out.println("saveUserToFile");
        User u = null;
        boolean expResult = false;
        boolean result = ChatApp.saveUserToFile(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsernameError method, of class ChatApp.
     */
    @Test
    public void testGetUsernameError() {
        System.out.println("getUsernameError");
        String username = "";
        String expResult = "";
        String result = ChatApp.getUsernameError(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPasswordErrors method, of class ChatApp.
     */
    @Test
    public void testGetPasswordErrors() {
        System.out.println("getPasswordErrors");
        String password = "";
        List<String> expResult = null;
        List<String> result = ChatApp.getPasswordErrors(password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPhoneError method, of class ChatApp.
     */
    @Test
    public void testGetPhoneError() {
        System.out.println("getPhoneError");
        String cell = "";
        String expResult = "";
        String result = ChatApp.getPhoneError(cell);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRecipientError method, of class ChatApp.
     */
    @Test
    public void testGetRecipientError() {
        System.out.println("getRecipientError");
        String cell = "";
        String expResult = "";
        String result = ChatApp.getRecipientError(cell);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkRecipientCell method, of class ChatApp.
     */
    @Test
    public void testCheckRecipientCell() {
        System.out.println("checkRecipientCell");
        String cell = "";
        boolean expResult = false;
        boolean result = ChatApp.checkRecipientCell(cell);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashPassword method, of class ChatApp.
     */
    @Test
    public void testHashPassword() {
        System.out.println("hashPassword");
        String password = "";
        String expResult = "";
        String result = ChatApp.hashPassword(password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveUserToJson method, of class ChatApp.
     */
    @Test
    public void testSaveUserToJson() {
        System.out.println("saveUserToJson");
        User u = null;
        boolean expResult = false;
        boolean result = ChatApp.saveUserToJson(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadUserFromJson method, of class ChatApp.
     */
    @Test
    public void testLoadUserFromJson() {
        System.out.println("loadUserFromJson");
        User expResult = null;
        User result = ChatApp.loadUserFromJson();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

