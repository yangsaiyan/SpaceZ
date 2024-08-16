package com.spacez.controller;

import com.spacez.model.entity.Launch;
import com.spacez.model.sessionbean.LaunchSessionBeanLocal;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PaginationServlet", value = "/PaginationServlet")
public class PaginationServlet extends HttpServlet {

    @EJB
    private LaunchSessionBeanLocal Lbean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int currentPage = Integer.valueOf(request.getParameter("currentPage"));
        int recordsPerPage = 6;
        int nOfPages = 0;
        String direction = request.getParameter("direction");
        String keyword = request.getParameter("keyword");
        int rows = Lbean.getNumberOfRows(keyword);
        try {

            nOfPages = Lbean.getNumberOfRows(keyword)/recordsPerPage;


            if(rows/recordsPerPage != 0){

                nOfPages++;

            }

            if(Lbean.getNumberOfRows(keyword) % recordsPerPage == 0){
                nOfPages = Lbean.getNumberOfRows(keyword)/recordsPerPage;
            }

            if(currentPage > nOfPages && nOfPages != 0){

                currentPage = nOfPages;

            }

            List<Launch> lists = Lbean.readLaunch(currentPage, recordsPerPage, keyword, direction);
            request.setAttribute("launches", lists);

        }catch(EJBException ex){
            ex.printStackTrace();
        }

        request.setAttribute("nOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        request.setAttribute("keyword", keyword);
        request.setAttribute("direction", direction);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/pagination.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);

    }
}
