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
    private static final String UPLOAD_DIRECTORY="csv_dir";
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
//                String content = readCsv(filePath);
                String content = readTest();
                out.println(content);
                out.flush();
                out.close();
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    private String processUploadField(FileItem item) {
        try {
            InputStream is = item.getInputStream();
            String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
            File saveDir = new File(savePath); // 既代表文件又代表目录
            if (!saveDir.exists()) {
                saveDir.mkdirs(); // 就创建一个指定的目录
            }

            String filename = item.getName();
            if (filename != null) {
                filename = filename.substring(filename.lastIndexOf(File.separator) + 1);
                //filename = FilenameUtils.getName(filename); // 效果同上            }
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
        String dir= filePath.substring(0,filePath.lastIndexOf("/")+1);
        String tableName = filePath.substring(filePath.lastIndexOf("/")+1);
        tableName = tableName.substring(0,tableName.lastIndexOf("."));
        //        String filePath = "/Users/shanchu/IdeaProjects/csye6220_hw2/out/artifacts/csye6220_hw2_war_exploded/WEB-INF/upload/store.xls";
        StringBuilder line = new StringBuilder();

        String fileDir = "/Users/shanchu/IdeaProjects/csye6220_hw2/out/artifacts/csye6220_hw2_war_exploded/WEB-INF/upload/";

        String url = "jdbc:relique:csv:" + fileDir + "?" +
                "separator=;" + "&" + "fileExtension=.csv";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();

            // Select the ID and NAME columns from sample.csv
            ResultSet results = stmt.executeQuery("SELECT * FROM "+tableName);
            ResultSetMetaData keys= results.getMetaData();
            int columns = keys.getColumnCount();
            while (results.next()){
                for(int i=1;i<=columns;i++){
                    line.append(results.getObject(i));
                    line.append(" ");
                }
                line.append("\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return line.toString();
    }

    private String readTest() throws SQLException {

//        String filePath = "/Users/shanchu/IdeaProjects/csye6220_hw2/out/artifacts/csye6220_hw2_war_exploded/WEB-INF/upload/store.xls";
            String filePath = "/Users/shanchu/IdeaProjects/csye6220_hw2/out/artifacts/csye6220_hw2_war_exploded/WEB-INF/upload/";

            String url = "jdbc:relique:csv:" + filePath + "?" +
                    "separator=;" + "&" + "fileExtension=.csv";
            Connection conn = DriverManager.getConnection(url);


            Statement stmt = conn.createStatement();

            // Select the ID and NAME columns from sample.csv
            ResultSet results = stmt.executeQuery("SELECT * FROM orders1");
            ResultSetMetaData keys= results.getMetaData();
            int columns = keys.getColumnCount();
        StringBuilder line = new StringBuilder();

        while (results.next()){
                for(int i=1;i<=columns;i++){
                    line.append(results.getObject(i));
                    line.append(" ");
                }
                System.out.println(line);
                break;
            }


        return line.toString();

    }
}
