package edu.neu.assignment2;
import java.sql.*;

public class DemoDriver
{
    public static void main(String[] args) throws Exception
    {

//        String filePath = "/Users/shanchu/IdeaProjects/csye6220_hw2/out/artifacts/csye6220_hw2_war_exploded/WEB-INF/upload/store.xls";
        String filePath = "/Users/shanchu/IdeaProjects/csye6220_hw2/out/artifacts/csye6220_hw2_war_exploded/WEB-INF/upload/";

        String url = "jdbc:relique:csv:" + filePath + "?" +
                "separator=;" + "&" + "fileExtension=.csv";
         Connection conn = DriverManager.getConnection(url);


             Statement stmt = conn.createStatement();

             // Select the ID and NAME columns from sample.csv
             ResultSet results = stmt.executeQuery("SELECT * FROM parking_facilities");
             ResultSetMetaData keys= results.getMetaData();
             int columns = keys.getColumnCount();
             while (results.next()){
                 StringBuilder line = new StringBuilder();
                for(int i=1;i<=columns;i++){
                    line.append(results.getObject(i));
                    line.append(" ");
                }
                 System.out.println(line);
                break;
             }



    }
}
