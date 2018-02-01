/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package B_servlets;

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
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author caeden
 */
@WebServlet(name = "ECommerce_AddFurnitureToListServlet", urlPatterns = {"/ECommerce_AddFurnitureToListServlet"})
public class ECommerce_AddFurnitureToListServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            String id = request.getParameter("id");
            String SKU = request.getParameter("SKU");
            double price = Double.parseDouble(request.getParameter("price"));
            String name = request.getParameter("name");
            String imageURL = request.getParameter("imageURL");
            Long countryID = (Long) session.getAttribute("countryID");
            
            ShoppingCartLineItem item = new ShoppingCartLineItem();
            item.setCountryID(countryID);
            item.setId(id);
            item.setImageURL(imageURL);
            item.setName(name);
            item.setPrice(price);
            item.setQuantity(1);
            item.setSKU(SKU);
            item.setQuantity(1);
            
            boolean check = false;
            int stock = checkStock(SKU);
            String result = "Item out of stock";
            if(stock == 0){
                
                response.sendRedirect("/IS3102_Project-war/B/SG/shoppingCart.jsp?errMsg=" + result);
            }else{
                
            
            ArrayList<ShoppingCartLineItem> shoppingCart = (ArrayList<ShoppingCartLineItem>) (session.getAttribute("shoppingCart"));
            
            if (shoppingCart == null){
                shoppingCart = new ArrayList<ShoppingCartLineItem>();
                session.setAttribute("shoppingCart", shoppingCart);
            }
            
            for (ShoppingCartLineItem e : shoppingCart){
                if(e.getSKU().equals(SKU)){
                    if(stock < (e.getQuantity() + 1)){
                        response.sendRedirect("/IS3102_Project-war/B/SG/shoppingCart.jsp?errMsg=" + result);
                        return;
                    }
                    check = true;
                    e.setQuantity(e.getQuantity()+1);
                    break;
                }
            }
            if(!check){
            shoppingCart.add(item);
            }
            
            
            response.sendRedirect("/IS3102_Project-war/B/SG/shoppingCart.jsp");
            }
        }
        
        
        
        }
        public int checkStock(String SKU){
            try {
            System.out.println("getQuantity() SKU: " + SKU);
            Client client = ClientBuilder.newClient();
            WebTarget target = client
                    .target("http://localhost:8080/IS3102_WebService-Student/webresources/entity.storeentity")
                    .path("getQuantityForAddToCart")
                    .queryParam("SKU", SKU);
            Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.get();
            System.out.println("status: " + response.getStatus());
            if (response.getStatus() != 200) {
                return 0;
            }
            String result = (String) response.readEntity(String.class);
            System.out.println("Result returned from ws: " + result);
            return Integer.parseInt(result);

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
