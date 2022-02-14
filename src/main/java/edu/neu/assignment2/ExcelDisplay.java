package edu.neu.assignment2;
import edu.neu.assignment2.entity.Order;
import edu.neu.assignment2.entity.People;
import edu.neu.assignment2.entity.Returns;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet("/part5.xls")
public class ExcelDisplay extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!ServletFileUpload.isMultipartContent(req)){
            PrintWriter out = resp.getWriter();
            out.print("file type error!");
            out.flush();
            out.close();
        }

        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        fileItemFactory.setRepository(new File(System.getProperty("user.dir")));
        ServletFileUpload fileUpload =  new ServletFileUpload(fileItemFactory);

        try {
            List<FileItem> fileItems = fileUpload.parseRequest(req);
            if(fileItems.size()>0){
                String filePath  = processUploadField(fileItems.get(0));
                 readExcel(filePath,resp);
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    private void readExcel(String filePath,HttpServletResponse resp) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        PrintWriter out = null;
        try {
             out = resp.getWriter();
             out.print("<html>\n" +
                     "<head>\n" +
                     "    <title>Excel Info Display</title>\n" +
                     "</head>\n" +
                     "<body>");
        } catch (IOException e) {
            e.printStackTrace();
        }


        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        System.out.print("Retrieving Sheets using Iterator");
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            System.out.print("=> " + sheet.getSheetName());
            String sheetName = sheet.getSheetName();

            if(sheetName.contains("Orders")){
                out.print("<div id=\"orderTable\"></div>\n" +
                        "    <h3 style=\"text-align: center\">order info list</h3>\n" +
                        "    <table border=\"1\" class=\"table table-bordered table-hover\">\n" +
                        "        <tr class=\"success\">\n" +
                        "            <th>Row Id</th>\n" +
                        "            <th>Order Id</th>\n" +
                        "            <th>Order Date</th>\n" +
                        "            <th>Ship Date</th>\n" +
                        "            <th>Ship Mode</th>\n" +
                        "            <th>Customer Id</th>\n" +
                        "            <th>Customer Name</th>\n" +
                        "            <th>Segment</th>\n" +
                        "            <th>Country</th>\n" +
                        "            <th>City</th>\n" +
                        "            <th>State</th>\n" +
                        "            <th>Postal Code</th>\n" +
                        "            <th>Region</th>\n" +
                        "            <th>Product Id</th>\n" +
                        "            <th>Category</th>\n" +
                        "            <th>Sub-Category</th>\n" +
                        "            <th>Product Name</th>\n" +
                        "            <th>Sales</th>\n" +
                        "            <th>Quality</th>\n" +
                        "            <th>Discount</th>\n" +
                        "            <th>Profit</th>\n" +
                        "        </tr>");
                List<Order> list =  readOrderExcel(sheet);
                int index =1;
                for(Order order:list){
                    out.print("<tr>");
                    out.print("<td>");
                    out.print(index++);
                    out.print("</td>\n");
                    out.println("<td>" +order.getOrderID()+ "</td>\n" +
                            "<td>" +order.getOrderDate()+ "</td>\n" +
                            "<td>" +order.getShipDate()+      "</td>\n" +
                            "<td>" +order.getShipMode()+"</td>\n" +
                            "<td>" +order.getCustomerID()+"</td>\n" +
                            "<td>" +order.getCustomerName()+"</td>\n" +
                            "<td>" +order.getSegment()+"</td>\n" +
                            "<td>" +order.getCountry()+"</td>\n" +
                            "<td>" +order.getCity()+"</td>\n" +
                            "<td>" +order.getState()+"</td>\n" +
                            "<td>" +order.getPostalCode()+"</td>\n" +
                            "<td>" +order.getRegion()+"</td>\n" +
                            "<td>" +order.getProductID()+"</td>\n" +
                            "<td>" +order.getCategory()+"</td>\n" +
                            "<td>" +order.getSubCategory()+"</td>\n" +
                            "<td>" +order.getProductName()+"</td>\n" +
                            "<td>" +order.getSales()+"</td>\n" +
                            "<td>" +order.getQuantity()+"</td>\n" +
                            "<td>" +order.getDiscount()+"</td>\n" +
                            "<td>"+order.getProfit()+"</td>\n") ; 
                    out.print("</tr>");
                }

                out.println("    </table>\n" +
                        "</div>");
            }else  if(sheetName.contains("Returns")){
                List<Returns> list =  readReturnsExcel(sheet);
                int index=1;
                out.print("<div id=\"returnTable\">\n" +
                        "    <h3 style=\"text-align: center\">return info list</h3>\n" +
                        "    <table border=\"1\" class=\"table table-bordered table-hover\">\n" +
                        "        <tr class=\"success\">\n" +
                        "            <th>Id</th>\n" +
                        "            <th>orderId</th>\n" +
                        "            <th>returned</th>\n" +
                        "        </tr>");
                for(Returns people:list){
                    out.print("<tr>");
                    out.print("<td>");
                    out.print(index++);
                    out.print("</td>\n");
                    out.println("<td>" +people.getOrderId()+ "</td>\n" +
                            "<td>" +people.getReturned()+ "</td>\n");
                    out.print("</tr>");
                }
                out.println("    </table>\n" +
                        "</div>");

            }else  if(sheetName.contains("People")){
                out.print("<div id=\"peopleTable\">\n" +
                        "        <h3 style=\"text-align: center\">people info list</h3>\n" +
                        "        <table border=\"1\" class=\"table table-bordered table-hover\">\n" +
                        "            <tr class=\"success\">\n" +
                        "                <th>Id</th>\n" +
                        "                <th>Name</th>\n" +
                        "                <th>Region</th>\n" +
                        "            </tr>");
                List<People> list =  readPeopleExcel(sheet);
                int index=1;
                for(People people:list){
                    out.print("<tr>");
                    out.print("<td>");
                    out.print(index++);
                    out.print("</td>\n");
                    out.println("<td>" +people.getName()+ "</td>\n" +
                            "<td>" +people.getRegion()+ "</td>\n");
                    out.print("</tr>");
                }
                out.println("    </table>\n" +
                        "</div>");
            }
        }

        out.print("</body>\n" +
                "</html>");
    }


    private List<Order> readOrderExcel(Sheet sheet){
        List<Order> rowList = new ArrayList<>();
        Integer totalRows = 0;
        totalRows = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < totalRows; i++) {
            Row row = sheet.getRow(i);
            // skip empty row
            if (row == null) {
                continue;
            }

            Order order = new Order();
            order.setOrderID(getCellValue(row.getCell(1)));
            order.setOrderDate(getCellValue(row.getCell(2)));
            order.setShipDate(getCellValue(row.getCell(3)));
            order.setShipMode(getCellValue(row.getCell(4)));
            order.setCustomerID(getCellValue(row.getCell(5)));
            order.setCustomerName(getCellValue(row.getCell(6)));
            order.setSegment(getCellValue(row.getCell(7)));
            order.setCountry(getCellValue(row.getCell(8)));
            order.setCity(getCellValue(row.getCell(9)));
            order.setState(getCellValue(row.getCell(10)));
            order.setPostalCode(getCellValue(row.getCell(11)));
            order.setRegion(getCellValue(row.getCell(12)));
            order.setProductID(getCellValue(row.getCell(13)));
            order.setCategory(getCellValue(row.getCell(14)));
            order.setSubCategory(getCellValue(row.getCell(15)));
            order.setProductName(getCellValue(row.getCell(16)));
            order.setSales(getCellValue(row.getCell(17)));
            order.setQuantity(getCellValue(row.getCell(18)));
            order.setDiscount(getCellValue(row.getCell(19)));
            order.setProfit(getCellValue(row.getCell(20)));
            rowList.add(order);
        }

        return rowList;
    }
    private List<Returns> readReturnsExcel(Sheet sheet){
        List<Returns> rowList = new ArrayList<>();
        Integer totalRows = 0;
        totalRows = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < totalRows; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }

            Returns order = new Returns();
            order.setReturned(getCellValue(row.getCell(0)));
            order.setOrderId(getCellValue(row.getCell(1)));
            rowList.add(order);
            rowList.add(order);
        }

        return rowList;
    }
    private List<People> readPeopleExcel(Sheet sheet){
        List<People> rowList = new ArrayList<>();
        Integer totalRows = 0;
        totalRows = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < totalRows; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }

            People order = new People();
            order.setName(getCellValue(row.getCell(0)));
            order.setRegion(getCellValue(row.getCell(1)));
            if(order.getName()==null||order.getName().length()==0){
                continue;
            }
            rowList.add(order);
        }

        return rowList;
    }


    private String getCellValue(Cell cell){
        DataFormatter dataFormatter = new DataFormatter();
        String cellValue = dataFormatter.formatCellValue(cell);
        return cellValue;
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


}

