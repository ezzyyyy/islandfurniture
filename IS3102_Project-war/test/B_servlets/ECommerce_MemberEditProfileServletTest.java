/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package B_servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
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
public class ECommerce_MemberEditProfileServletTest {
    
    public ECommerce_MemberEditProfileServletTest() {
    }
    
    
    @Test
    public void testUpdateMemberByIDRESTful() {
        System.out.println("updateMemberByIDRESTful");
        Long id = (long)1;
        String name = "asa";
        String phone = "344444";
        String address = "hjxhcak";
        String password = null;
        int securityQuestion = 1;
        String securityAnswer = "holyppp";
        Integer age = 12;
        Integer income = 1212;
        ECommerce_MemberEditProfileServlet instance = new ECommerce_MemberEditProfileServlet();
        String expResult = "success";
        String result = instance.updateMemberByIDRESTful(id, name, phone, address, securityQuestion, securityAnswer, age, income,password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    
}
