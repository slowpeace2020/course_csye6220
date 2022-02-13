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

@WebServlet("/part5")
public class ExcelDisplay extends HttpServlet {
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

        try {
            List<FileItem> fileItems = fileUpload.parseRequest(req);
            if(fileItems.size()>0){
                String filePath  = processUploadField(fileItems.get(0));
                 readExcel(filePath,req);

            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }


        req.getRequestDispatcher("/uploadpages/excel_display.jsp").forward(req,resp);

    }

    private void readExcel(String filePath,HttpServletRequest req) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        /*
           =============================================================
           Iterating over all the sheets in the workbook (Multiple ways)
           =============================================================
        */

        // 1. You can obtain a sheetIterator and iterate over it
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        System.out.println("Retrieving Sheets using Iterator");
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            System.out.println("=> " + sheet.getSheetName());
            String sheetName = sheet.getSheetName();

            if(sheetName.contains("Orders")){                List<Order> list =  readOrderExcel(sheet);
                req.setAttribute("orders",list);
            }else  if(sheetName.contains("Returns")){
                List<Returns> list =  readReturnsExcel(sheet);
                req.setAttribute("returns",list);

            }else  if(sheetName.contains("People")){
                List<People> list =  readPeopleExcel(sheet);
                req.setAttribute("people",list);
            }
        }

    }


    private List<Order> readOrderExcel(Sheet sheet){
        List<Order> rowList = new ArrayList<>();
        Integer totalRows = 0;
        totalRows = sheet.getPhysicalNumberOfRows();	// 获取工作表的总行数
        for (int i = 1; i < totalRows; i++) {
            Row row = sheet.getRow(i);	// 获取行对象
            // 去除空行
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
        totalRows = sheet.getPhysicalNumberOfRows();	// 获取工作表的总行数
        for (int i = 1; i < totalRows; i++) {
            Row row = sheet.getRow(i);	// 获取行对象
            // 去除空行
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
        totalRows = sheet.getPhysicalNumberOfRows();	// 获取工作表的总行数
        for (int i = 1; i < totalRows; i++) {
            Row row = sheet.getRow(i);	// 获取行对象
            // 去除空行
            if (row == null) {
                continue;
            }

            People order = new People();
            order.setName(getCellValue(row.getCell(0)));
            order.setRegion(getCellValue(row.getCell(1)));
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


}

