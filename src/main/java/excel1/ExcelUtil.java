package excel1;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtil {
    /**
     * @���ܣ��ֹ�����һ���򵥸�ʽ��Excel
     */
    public static void createExcel(Map<String, List<String>> map, String[] strArray) {
        // ��һ��������һ��webbook����Ӧһ��Excel�ļ�
        HSSFWorkbook wb = new HSSFWorkbook();
        // �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        sheet.setDefaultColumnWidth(20);// Ĭ���п�
        // ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������short
        HSSFRow row = sheet.createRow((int) 0);
        // ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����
        HSSFCellStyle style = wb.createCellStyle();
        // ����һ�����и�ʽ
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // ���excel title
        HSSFCell cell = null;
        for (int i = 0; i < strArray.length; i++) {
            cell = row.createCell((short) i);
            cell.setCellValue(strArray[i]);
            cell.setCellStyle(style);
        }

        // ���岽��д��ʵ������ ʵ��Ӧ������Щ���ݴ����ݿ�õ�,list���ַ�����˳����������strArray�е�˳��һ��
        int i = 0;
        for (String str : map.keySet()) {
            row = sheet.createRow((int) i + 1);
            List<String> list = map.get(str);

            // ���Ĳ���������Ԫ�񣬲�����ֵ
            for (int j = 0; j < strArray.length; j++) {
                row.createCell((short) j).setCellValue(list.get(j));
            }

            // �����������ļ��浽ָ��λ��
            try {
                FileOutputStream fout = new FileOutputStream("/Users/leizi/Desktop/Members.xls");
                wb.write(fout);
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}