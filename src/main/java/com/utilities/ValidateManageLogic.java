package com.utilities;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;

public class ValidateManageLogic {

    public static String validateManager(HttpServletRequest request) {

        if(request.getParameter("UPDATE")!=null && request.getParameter("UPDATE").equals("Update")) {
            return "UPDATE";
        }
        else if (request.getParameter("DELETE") != null && request.getParameter("DELETE").equals("Delete")) {
            return "DELETE";
        }
        return "ADD";
    }

    public static void navigateJS(PrintWriter out) {
        out.println("<SCRIPT type=\"text/javascript\">");
        out.println("alert(\"Record has been updated and url will be redirected\")");
        out.println("window.location.assign(\"PaginationServlet?currentPage=1&recordsPerPage=6&keyword=&direction=ASC\")");
        out.println("</SCRIPT>");
    }
}
