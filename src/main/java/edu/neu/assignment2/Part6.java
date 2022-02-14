package edu.neu.assignment2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Part6 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        writer.println("Academic Term: "+req.getParameter("Academic Term"));
        writer.println("Academic Year: "+req.getParameter("Academic Year"));
        writer.println("status-type: "+req.getParameter("status-type"));
        writer.println("Student's Name: "+req.getParameter("Student's Name"));
        writer.println("Student's NUID: "+req.getParameter("Student's NUID:"));
        writer.println("Employee's Name: "+req.getParameter("Employee's Name"));
        writer.println("Employee's NUID: "+req.getParameter("Employee's NUID"));
        writer.println("Department: "+req.getParameter("Department"));
        writer.println("Campus Location: "+req.getParameter("Campus Location"));
        writer.println("Phone Number: "+req.getParameter("Phone Number"));
        writer.println("Supervisor's Name: "+req.getParameter("Supervisor's Name"));
        writer.println("Employee's Signature: "+req.getParameter("Employee's Signature"));
        writer.println("Signature Date: "+req.getParameter("Signature Date"));
        writer.println("HRM Approval: "+req.getParameter("HRM Approval"));
        writer.println("HRM Approval Date: "+req.getParameter("HRM Approval Date"));

        if(req.getParameterValues("courseId[]").length!=0){
            String[] courses=new String[req.getParameterValues("courseId[]").length];
            Arrays.fill(courses,"");
            int i=0;
            for(String value : req.getParameterValues("courseId[]")){
                courses[i++]+=value+" ";
            }
            i=0;
            for(String value : req.getParameterValues("courseName[]")){
                courses[i++]+=value+" ";
            }
            i=0;
            for(String value : req.getParameterValues("supervisorSignature[]")){
                courses[i++]+=value+" ";
            }
            i=0;
            for(String value : req.getParameterValues("creditHours[]")){
                courses[i++]+=value+" ";
            }
            i=0;
            for(String value : req.getParameterValues("days[]")){
                courses[i++]+=value+" ";
            }
            i=0;
            for(String value : req.getParameterValues("time[]")){
                courses[i++]+=value+" ";
            }
            writer.println("Course List: ");
            for(String course:courses){
                writer.println(course);
            }
        }
        writer.flush();
        writer.close();
    }
}
