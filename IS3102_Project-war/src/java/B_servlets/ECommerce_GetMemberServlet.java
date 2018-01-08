/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package B_servlets;

import CorporateManagement.FacilityManagement.FacilityManagementBeanLocal;
import EntityManager.CountryEntity;
import OperationalCRM.LoyaltyAndRewards.LoyaltyAndRewardsBeanLocal;
import EntityManager.LoyaltyTierEntity;
import HelperClasses.Member;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Francis
 */

@WebServlet(urlPatterns = {"/ECommerce_GetMemberServlet"})
public class ECommerce_GetMemberServlet extends HttpServlet {



    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
            String email = session.getAttribute("memberEmail").toString();


            Member member = retrieveMemberByEmailRESTful(email);
            session.setAttribute("member", member);
            session.setAttribute("memberName", member.getName());
            

            response.sendRedirect("/IS3102_Project-war/B/SG/memberProfile.jsp");
        } catch (Exception ex) {
            out.println("\n\n " + ex.getMessage());
        }
    }

    public Member retrieveMemberByEmailRESTful(String email) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client
                .target("http://localhost:8080/IS3102_WebService-Student/webresources/entity.memberentity").path("getMember")
                .queryParam("email", email);
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("some-header", "true");
        Response response = invocationBuilder.get();
        System.out.println("status: " + response.getStatus());

        if (response.getStatus() != 200) {
            return null;
        }

        Member member = response.readEntity(Member.class);

        return member;
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
