package excel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
public class ReadExcel {
    public static void main(String[] args) {
        ReadExcel obj = new ReadExcel();
        // �˴�Ϊ�Ҵ���Excel·����E:/zhanhj/studysrc/jxl��
        File file = new File("/Users/leizi/Desktop/phone.xls");
        List excelList = obj.readExcel(file);
        System.out.println("list�е����ݴ�ӡ����");
        for (int i = 0; i < excelList.size(); i++) {
            List list = (List) excelList.get(i);
            for (int j = 0; j < list.size(); j++) {
                System.out.print(list.get(j));
            }
            System.out.println();
        }

    }
    // ȥ��Excel�ķ���readExcel���÷�������ڲ���Ϊһ��File����
    public List readExcel(File file) {
        try {
            // ��������������ȡExcel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl�ṩ��Workbook��
            Workbook wb = Workbook.getWorkbook(is);
            // Excel��ҳǩ����
            int sheet_size = wb.getNumberOfSheets();
            for (int index = 0; index < sheet_size; index++) {
                List<List> outerList=new ArrayList<List>();
                // ÿ��ҳǩ����һ��Sheet����
                Sheet sheet = wb.getSheet(index);
                // sheet.getRows()���ظ�ҳ��������
                for (int i = 0; i < sheet.getRows(); i++) {
                    List innerList=new ArrayList();
                    // sheet.getColumns()���ظ�ҳ��������
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        String cellinfo = sheet.getCell(j, i).getContents();
                        if(cellinfo.isEmpty()){
                            continue;
                        }
                        innerList.add(cellinfo);
                        System.out.print(cellinfo);
                    }
                    outerList.add(i, innerList);
                    System.out.println();
                }
                return outerList;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}