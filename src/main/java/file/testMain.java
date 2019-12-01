package file;

import java.io.File;
 
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
public class testMain {
 
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            File file = new File("/Users/leizi/Desktop/phone.xls"); // �����ļ�����
            Workbook wb = Workbook.getWorkbook(file); // ���ļ����л�ȡExcel����������WorkBook��
            Sheet sheet = wb.getSheet(0); // �ӹ�������ȡ��ҳ��Sheet��
            for (int i = 1; i < sheet.getRows(); i++) { // ѭ����ӡExcel���е�����
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