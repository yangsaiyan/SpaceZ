package com.spacez.controller;

import com.spacez.model.entity.Launch;
import com.spacez.model.sessionbean.LaunchSessionBean;
import com.spacez.model.sessionbean.LaunchSessionBeanLocal;
import com.utilities.ValidateManageLogic;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LaunchController", value = "/LaunchController")
public class LaunchController extends HttpServlet {

    @EJB
    private LaunchSessionBeanLocal Lbean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        try{
            Launch l = Lbean.findLaunch(id);
            request.setAttribute("Ln", l);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/LaunchUpdate.jsp");
            dispatcher.forward(request, response);

        }catch (EJBException ex){

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//("Launch_ID", "Mission_Name", "SpaceCraft_Model", "Date", "Time", "Description","Is_Launch")
        String lid = request.getParameter("id");
        String name = request.getParameter("missionName");
        String model = request.getParameter("model");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String desc = request.getParameter("description");
        String is_launch = request.getParameter("is_launch");
        String[] save = {lid, name, model, date, time, desc, is_launch};
        PrintWriter out = response.getWriter();
        try {
            if (ValidateManageLogic.validateManager(request).equals("UPDATE")) {
                Lbean.updateLaunch(save);
            }
            else if (ValidateManageLogic.validateManager(request).equals("DELETE")) {
                Lbean.deleteLaunch(lid);
            } else {
                Lbean.addLaunch(save);
            }
            ValidateManageLogic.navigateJS(out);

        } catch (EJBException ex) {
            ex.printStackTrace();
            response.getWriter().println("Exception occurred: " + ex.getMessage());
        }
    }
}
