/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package B_servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author caeden
 */
public class ECommerce_AddFurnitureToListServletTest {
    
    public ECommerce_AddFurnitureToListServletTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of processRequest method, of class ECommerce_AddFurnitureToListServlet.
     */


    /**
     * Test of checkStock method, of class ECommerce_AddFurnitureToListServlet.
     */
    @Test
    public void testCheckStock() {
        System.out.println("checkStock");
        String SKU = "F_BM_21";
        ECommerce_AddFurnitureToListServlet instance = new ECommerce_AddFurnitureToListServlet();
        int expResult = 70;
        int result = instance.checkStock(SKU);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    
}
