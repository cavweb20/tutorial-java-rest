package io.github.cavweb20.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author cavweb20
 */
public class HelloWorld extends HttpServlet
{

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 5717022079191155354L;

    /**
     * 
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/plain;charset=UTF-8");
        response.setStatus(200);
        try (PrintWriter out = response.getWriter())
        {
            out.println("Hello World!");
            out.flush();
        }
    }

}
