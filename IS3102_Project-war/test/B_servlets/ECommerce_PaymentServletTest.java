/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package B_servlets;

import HelperClasses.ShoppingCartLineItem;
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
public class ECommerce_PaymentServletTest {
    
    public ECommerce_PaymentServletTest() {
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

    @Test
    public void testGetStoreID() {
        System.out.println("getStoreID");
        String SKU = "F_BM_21";
        ECommerce_PaymentServlet instance = new ECommerce_PaymentServlet();
        long expResult = 59;
        long result = instance.getStoreID(SKU);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
/*
    @Test
    public void testCreateECommerceTransactionRecord() {
        System.out.println("createECommerceTransactionRecord");
        long memberID = 0L;
        double amountPaid = 100.0;
        long countryID = 0L;
        long storeID = 59;
        ECommerce_PaymentServlet instance = new ECommerce_PaymentServlet();
        long expResult = 0L;
        long result = instance.createECommerceTransactionRecord(memberID, amountPaid, countryID, storeID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createECommerceLineItemRecord method, of class ECommerce_PaymentServlet.
     */
    @Test
    public void testCreateECommerceLineItemRecord() {
        System.out.println("createECommerceLineItemRecord");
        long salesrecordID = 6301;
        ShoppingCartLineItem item = new ShoppingCartLineItem();
        item.setId("69");
        item.setQuantity(3);
        ECommerce_PaymentServlet instance = new ECommerce_PaymentServlet();
        int expResult = 1;
        int result = instance.createECommerceLineItemRecord(salesrecordID, item);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //Afail("The test case is a prototype.");
    }
    
}
