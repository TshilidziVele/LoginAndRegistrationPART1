/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import java.io.File;
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
public class JsonHelperIT {
    
    public JsonHelperIT() {
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
     * Test of escape method, of class JsonHelper.
     */
    @Test
    public void testEscape() {
        System.out.println("escape");
        String s = "";
        String expResult = "";
        String result = JsonHelper.escape(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unescape method, of class JsonHelper.
     */
    @Test
    public void testUnescape() {
        System.out.println("unescape");
        String s = "";
        String expResult = "";
        String result = JsonHelper.unescape(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readFile method, of class JsonHelper.
     */
    @Test
    public void testReadFile() {
        System.out.println("readFile");
        File file = null;
        String expResult = "";
        String result = JsonHelper.readFile(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of extractObjects method, of class JsonHelper.
     */
    @Test
    public void testExtractObjects() {
        System.out.println("extractObjects");
        String json = "";
        List<String> expResult = null;
        List<String> result = JsonHelper.extractObjects(json);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseString method, of class JsonHelper.
     */
    @Test
    public void testParseString() {
        System.out.println("parseString");
        String json = "";
        String key = "";
        String expResult = "";
        String result = JsonHelper.parseString(json, key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseInt method, of class JsonHelper.
     */
    @Test
    public void testParseInt() {
        System.out.println("parseInt");
        String json = "";
        String key = "";
        int expResult = 0;
        int result = JsonHelper.parseInt(json, key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
