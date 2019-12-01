package file;

import java.io.File;
 
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
public class testMain {
 
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            File file = new File("/Users/leizi/Desktop/phone.xls"); // 创建文件对象
            Workbook wb = Workbook.getWorkbook(file); // 从文件流中获取Excel工作区对象（WorkBook）
            Sheet sheet = wb.getSheet(0); // 从工作区中取得页（Sheet）
            for (int i = 1; i < sheet.getRows(); i++) { // 循环打印Excel表中的内容
                for (int j = 0; j < sheet.getColumns(); j++) { 
                    Cell cell = sheet.getCell(j, i); 
                    System.out.printf(cell.getContents()+" ");
                } 
                System.out.println(); 
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
}