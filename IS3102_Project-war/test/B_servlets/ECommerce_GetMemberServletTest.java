/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package B_servlets;

import HelperClasses.Member;
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
public class ECommerce_GetMemberServletTest {
    
    public ECommerce_GetMemberServletTest() {
    }
    
    
    /**
     * Test of processRequest method, of class ECommerce_GetMemberServlet.
     */
    
    /**
     * Test of retrieveMemberByEmailRESTful method, of class ECommerce_GetMemberServlet.
     */
    @Test
    public void testRetrieveMemberByEmailRESTful() {
        System.out.println("retrieveMemberByEmailRESTful");
        String email = "superman@hotmail.com";
        ECommerce_GetMemberServlet instance = new ECommerce_GetMemberServlet();
        String expResult = "superman@hotmail.com";
        String result = instance.retrieveMemberByEmailRESTful(email).getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of doGet method, of class ECommerce_GetMemberServlet.
     */
    
}
