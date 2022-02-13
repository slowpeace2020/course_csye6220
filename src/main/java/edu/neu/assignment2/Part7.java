package edu.neu.assignment2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

@WebServlet("/part7")
public class Part7 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration em = req.getParameterNames();
        StringBuilder sb1 = new StringBuilder();
        String[] courses=null;
        PrintWriter writer = resp.getWriter();

        while (em.hasMoreElements()){
            String str = (String) em.nextElement();
            sb1.append(str+": "+req.getParameter(str)+"</br>");

            if(str.contains("[]")){
                if(courses==null){
                    courses = new String[req.getParameterValues(str).length];
                    Arrays.fill(courses,"");
                }
                int i=0;
                for(String value : req.getParameterValues(str)){
                    courses[i++]+=value+" ";
                }

            }else {
                writer.println(str+": "+req.getParameter(str));
            }
        }

        if(courses!=null){
            writer.println("Course List:");
            for(String str:courses){
                writer.println(str);
            }
        }

        writer.flush();
        writer.close();

    }
}
