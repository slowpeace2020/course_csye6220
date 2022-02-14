package edu.neu.assignment2;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;
import java.util.List;

public class CsvDisplay extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!ServletFileUpload.isMultipartContent(req)){
            PrintWriter out = resp.getWriter();
            out.println("file type error!");
            out.flush();
            out.close();
        }

        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        fileItemFactory.setRepository(new File(System.getProperty("user.dir")));
        ServletFileUpload fileUpload =  new ServletFileUpload(fileItemFactory);
        PrintWriter out = resp.getWriter();

        try {
            List<FileItem> fileItems = fileUpload.parseRequest(req);
            if(fileItems.size()>0){
               String filePath  = processUploadField(fileItems.get(0));
                String content = readCsv(filePath);
                out.println(content);
                out.flush();
                out.close();
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }


    }

    private String processUploadField(FileItem item) {
        try {
            InputStream is = item.getInputStream();
            String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                saveDir.mkdirs();
            }

            String filename = item.getName();
            if (filename != null) {
                filename = filename.substring(filename.lastIndexOf(File.separator) + 1);
            }
            String filePath = saveDir+File.separator + filename;
            File file = new File(filePath);
            FileOutputStream fos = new FileOutputStream(file);
            int len = 0;
            byte[] b = new byte[1024];
            while ((len = is.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            fos.close();
            is.close();

            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    private String readCsv(String filePath){
        String fileDir= filePath.substring(0,filePath.lastIndexOf("/")+1);
        String tableName = filePath.substring(filePath.lastIndexOf("/")+1);
        tableName = tableName.substring(0,tableName.lastIndexOf("."));
        StringBuilder line = new StringBuilder();
        line.append("<html>\n" +
                "<head>\n" +
                "    <title>CSV Info Display</title>\n" +
                "</head>\n" +
                "<body>");
        line.append("<div id=\"csvTable\">\n" +
                "        <h1 style=\"text-align: center\">CSV info list</h1>\n" +
                "        <table border=\"1\" class=\"table table-bordered table-hover\">\n"
        );

        try {
            Class.forName("org.relique.jdbc.csv.CsvDriver");
        } catch (ClassNotFoundException e) {
            return "org.relique.jdbc.csv.CsvDriver ClassNotFoundException";
        }

        String url = "jdbc:relique:csv:" + fileDir + "?" +
                "separator=;" + "&" + "fileExtension=.csv";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM "+tableName);
            ResultSetMetaData keys= results.getMetaData();
            String column = keys.getColumnName(1);
            line.append("<tr>");
            for(String col:column.split(",")){
                line.append("<td>");
                line.append(col);
                line.append("</td>");
            }
            line.append("</tr>");
            int columns = column.split(",").length;
            while (results.next()){
                line.append("<tr>");
                String record = (String) results.getObject(1);
                String values[] = record.trim().split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)",-1);
                for(int i=0;i<columns;i++){
                    line.append("<td>");
                    if(i<values.length){
                        line.append(values[i].replace("\"",""));
                    }
                    line.append("</td>");
                }
                line.append("</tr>");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        line.append("        </table>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>");
        return line.toString();
    }

}
