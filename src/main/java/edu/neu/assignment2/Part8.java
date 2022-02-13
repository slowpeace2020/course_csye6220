package edu.neu.assignment2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

@WebServlet("/part8")
public class Part8 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
       Map<String,String[]> map = req.getParameterMap();
       String[] courses=null;
       for(Map.Entry<String,String[]> entry:map.entrySet()){
           if(entry.getKey().contains("[]")){
               if(courses==null){
                   courses = new String[entry.getValue().length];
                   Arrays.fill(courses,"");
               }
               int i=0;
               for(String value : entry.getValue()){
                   courses[i++]+=value+" ";
               }
           }else {
               writer.println(entry.getKey()+": "+entry.getValue()[0]);
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
