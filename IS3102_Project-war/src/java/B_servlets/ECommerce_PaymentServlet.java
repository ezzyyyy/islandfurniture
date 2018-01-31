/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package B_servlets;

import HelperClasses.SalesRecord;
import HelperClasses.ShoppingCartLineItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author caeden
 */
@WebServlet(name = "ECommerce_PaymentServlet", urlPatterns = {"/ECommerce_PaymentServlet"})
public class ECommerce_PaymentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            ArrayList<ShoppingCartLineItem> shoppingCart = (ArrayList<ShoppingCartLineItem>) (session.getAttribute("shoppingCart"));
            long countryID = (long)session.getAttribute("countryID");
            long memberID = (long)session.getAttribute("memberID");
            double amount = Double.parseDouble(request.getParameter("amount"));
            long storeID = 0;
            long salesrecordID = 0;
            String result = "Error Occur";
            for(ShoppingCartLineItem item : shoppingCart){
                long newstoreID = getStoreID(item.getSKU());
                if(newstoreID != storeID){
                    salesrecordID = createECommerceTransactionRecord(memberID,amount,countryID,newstoreID);
                    if (salesrecordID == 0){
                        response.sendRedirect("/IS3102_Project-war/B/SG/shoppingCart.jsp?errMsg=" + result);
                        break;
                    }
                    storeID = newstoreID;
                }
                int ch = createECommerceLineItemRecord(salesrecordID,item);
                if (ch == 0){
                    response.sendRedirect("/IS3102_Project-war/B/SG/shoppingCart.jsp?errMsg=" + result);
                    break;
                }
            }
            shoppingCart.clear();
            result = "payment successful";
            response.sendRedirect("/IS3102_Project-war/B/SG/shoppingCart.jsp?success=" + result);
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private long getStoreID(String SKU){
        Client client = ClientBuilder.newClient();
            WebTarget target = client
                    .target("http://localhost:8080/IS3102_WebService-Student/webresources/entity.commerce")
                    .path("checkStore")
                    .queryParam("SKU", SKU);
            Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.get();
            System.out.println("status: " + response.getStatus());
            String storeID = (String)response.readEntity(String.class);
            return Long.parseLong(storeID);
    }
    
    private long createECommerceTransactionRecord(long memberID, double amountPaid, long countryID, long storeID){
        Client client = ClientBuilder.newClient();
            WebTarget target = client
                    .target("http://localhost:8080/IS3102_WebService-Student/webresources/entity.commerce")
                    .path("createECommerceTransactionRecord");
            SalesRecord sr = new SalesRecord(memberID,amountPaid,countryID,storeID);
            Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
            invocationBuilder.header("some-header", "true");
            
            Response response = invocationBuilder.put(Entity.entity(sr, MediaType.APPLICATION_JSON));
            System.out.println("status: " + response.getStatus());
            if (response.getStatus() != 200) {
                
                return 0;
            }
            String salesrecordID = (String)response.readEntity(String.class);
            //long salesrecordID = response.readEntity(Long.class);
            return Long.parseLong(salesrecordID);
    }
    
    private int createECommerceLineItemRecord(long salesrecordID, ShoppingCartLineItem item){
        Client client = ClientBuilder.newClient();
            WebTarget target = client
                    .target("http://localhost:8080/IS3102_WebService-Student/webresources/entity.commerce")
                    .path("createECommerceLineItemRecord")
                    .queryParam("salesrecordID", salesrecordID);
            Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
            invocationBuilder.header("some-header", "true");
            Response response = invocationBuilder.put(Entity.entity(item, MediaType.APPLICATION_JSON));
            System.out.println("status: " + response.getStatus());
            if (response.getStatus() != 200) {
                return 0;
            }
            
            return 1;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
