package cn.stylefeng.guns.sys.modular.demo;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.*;


public class ExcelUtils {

    private final static String excel2003L = ".xls";
    private final static String excel2007U = ".xlsx";
    private final static String excelCsv = ".csv";

    public static List<String> queryTopCell(InputStream is, String fileName, String caseCode,HttpServletRequest request) {
        Workbook workbook = null;
        //sheet页name
        Sheet sheet = null;
        String SheetName="";
        Row row = null;
        Cell cell = null;//验证文件格式
        try {
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            if (suffix.equals(excel2003L)) {
                workbook = new HSSFWorkbook(is);
            } else if (suffix.equals(excel2007U)) {
                workbook = new XSSFWorkbook(is);
            } else if (suffix.equals(excelCsv)) {
//                workbook = new Csv
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Error ee) {
            ee.printStackTrace();
        } finally {
            System.out.print("");
        }

        List<String> cellList= new ArrayList<String>();
        //只取第一个sheet页
        for (int i = 0; i < 1; i++) { //workbook.getNumberOfSheets()
            //获取第一个sheet
            sheet = workbook.getSheetAt(i);
            if (sheet == null) continue;
            SheetName=sheet.getSheetName();

            cellList.add(caseCode); //0
            cellList.add(fileName); //1
            cellList.add(SheetName); //2
            //获取第一行
            row=sheet.getRow(0);
            //遍历第一行

            int o =0;
            Iterator<Cell> it=row.iterator();
            while(it.hasNext())
            {
                cell=it.next();
                o++;
                if (cell == null) continue;
                String c=String.valueOf(cell);
                System.out.println("第"+o+"个:"+c);
                cellList.add(c);
            }
        }
        return cellList;
    }

}
