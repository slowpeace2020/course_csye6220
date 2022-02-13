package edu.neu.assignment2;

import edu.neu.assignment2.entity.Order;
import edu.neu.assignment2.entity.People;
import edu.neu.assignment2.entity.Returns;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class ExcelReadSheet {
    public static final String SAMPLE_XLSX_FILE_PATH = "/Users/shanchu/IdeaProjects/csye6220_homework2/target/csye6220_homework2/WEB-INF/upload/store.xlsx";

    public static void main(String[] args) throws IOException, InvalidFormatException {
        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        /*
           =============================================================
           Iterating over all the sheets in the workbook (Multiple ways)
           =============================================================
        */

        // 1. You can obtain a sheetIterator and iterate over it
        ExcelReadSheet test = new ExcelReadSheet();
        test.readExcel("/Users/shanchu/IdeaProjects/csye6220_homework2/target/csye6220_homework2/WEB-INF/upload/store.xlsx");
    }


    private void readExcel(String filePath) {
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


            if(sheetName.contains("Orders")){
                List<Order> list =  readOrderExcel(sheet);
                System.out.println(list);
//                req.setAttribute("orders",list);
            }else  if(sheetName.contains("Returns")){

                List<Returns> list =  readReturnsExcel(sheet);
                System.out.println(list);

//                req.setAttribute("returns",list);

            }else  if(sheetName.contains("People")){
                List<People> list =  readPeopleExcel(sheet);
                System.out.println(list);

//                req.setAttribute("people",list);
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
            order.setName(getCellValue(row.getCell(1)));
            order.setRegion(getCellValue(row.getCell(2)));
            rowList.add(order);
        }

        return rowList;
    }

    private String getCellValue(Cell cell){
        DataFormatter dataFormatter = new DataFormatter();
        String cellValue = dataFormatter.formatCellValue(cell);
        return cellValue;
    }

}
