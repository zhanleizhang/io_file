package excel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";
    
    public static void main(String[] args) {
        
        Map<String, String> dataMap=new HashMap<String, String>();
        dataMap.put("BankName", "BankName");
        dataMap.put("Addr", "Addr");
        dataMap.put("Phone", "Phone");
        List<Map> list=new ArrayList<Map>();
        list.add(dataMap);
        writeExcel(list, 3, "/Users/leizi/Desktop/phone1.xls");
        
    }

    public static void writeExcel(List<Map> dataList, int cloumnCount,String finalXlsxPath){
        OutputStream out = null;
        try {
            // ��ȡ������
            int columnNumCount = cloumnCount;
            // ��ȡExcel�ĵ�
            File finalXlsxFile = new File(finalXlsxPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            // sheet ��Ӧһ������ҳ
            Sheet sheet = workBook.getSheetAt(0);
            /**
             * ɾ��ԭ�����ݣ�����������
             */
            int rowNumber = sheet.getLastRowNum();    // ��һ�д�0��ʼ��
            System.out.println("ԭʼ�������������������У�" + rowNumber);
            for (int i = 1; i <= rowNumber; i++) {
                Row row = sheet.getRow(i);
                sheet.removeRow(row);
            }
            // �����ļ��������������ӱ����������У���������sheet�������κβ�����������Ч
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
            /**
             * ��Excel��д������
             */
            for (int j = 0; j < dataList.size(); j++) {
                // ����һ�У��ӵڶ��п�ʼ������������
                Row row = sheet.createRow(j + 1);
                // �õ�Ҫ�����ÿһ����¼
                Map dataMap = dataList.get(j);
                String name = dataMap.get("BankName").toString();
                String address = dataMap.get("Addr").toString();
                String phone = dataMap.get("Phone").toString();
                for (int k = 0; k <= columnNumCount; k++) {
                // ��һ����ѭ��
                Cell first = row.createCell(0);
                first.setCellValue(name);
        
                Cell second = row.createCell(1);
                second.setCellValue(address);
        
                Cell third = row.createCell(2);
                third.setCellValue(phone);
                }
            }
            // �����ļ��������׼��������ӱ����������У���������sheet�������κβ�����������Ч
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("���ݵ����ɹ�");
    }

    /**
     * �ж�Excel�İ汾,��ȡWorkbook
     * @param file
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){     //Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }
}