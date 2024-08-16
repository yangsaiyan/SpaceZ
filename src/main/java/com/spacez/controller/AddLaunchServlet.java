package com.spacez.controller;

import com.spacez.model.entity.Launch;
import com.spacez.model.sessionbean.LaunchSessionBean;
import com.spacez.model.sessionbean.LaunchSessionBeanLocal;
import com.utilities.ValidateManageLogic;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddLaunchServlet", value = "/AddLaunchServlet")
public class AddLaunchServlet extends HttpServlet {

    @EJB
    private LaunchSessionBeanLocal lbean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List l = lbean.getAllLaunches();
        request.setAttribute("launches", l);

        List launches = (List<Launch>) request.getAttribute("launches");
        RequestDispatcher rd = request.getRequestDispatcher("/LaunchAdd.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
