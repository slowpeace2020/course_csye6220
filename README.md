question list
1. classpath contains csvjdbc.jar, but Tomcat couldn't find it?
solution : before connect csv, load driver class
example:

   '''
   
           Class.forName("org.relique.jdbc.csv.CsvDriver");
   
           String url = "jdbc:relique:csv:" + fileDir + "?" +
                   "separator=;" + "&" + "fileExtension=.csv";
           Connection conn = null;
           conn = DriverManager.getConnection(url);
           
   '''
   


2. read csv split value contains ',', cause value respond error

'''

                String values[] = value.trim().split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)",-1);

'''
