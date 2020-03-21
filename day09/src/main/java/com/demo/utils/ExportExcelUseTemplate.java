package com.demo.utils;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExportExcelUseTemplate<T> implements Serializable {

    private String tempPath;

    private String tempName;

    private String fileName;

    private String filePath;

    private Map<Integer, List<T>> map;

    private XSSFWorkbook wb;

    private ExportExcelUseTemplate(){}

    public ExportExcelUseTemplate(String tempPath,String tempName,
        String filePath,String fileName,Map<Integer,List<T>> map){
        this.tempPath = tempPath;
        this.tempName = tempName;
        this.filePath = filePath;
        this.fileName = fileName;
        this.map = map;

    }

    protected void export(){
        readTemplate();
        writeData();
        createFile();
    }
    protected void readTemplate(){
        try{
            FileInputStream fileInputStream = new FileInputStream(this.tempPath+this.tempName);
            this.wb = new XSSFWorkbook(fileInputStream);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void writeData(){
        for(Integer index:map.keySet()){
            if(map.get(index).isEmpty()){
                continue;
            }
            XSSFSheet sheet = wb.getSheetAt(index-1);//第index-1个sheet
            XSSFRow row = sheet.getRow(0);
            List<T> list = map.get(index);
            sheet.shiftRows(3,3+list.size(),list.size());//从第三行开始,结束与size+3,动态添加size行
            for(int i =0 ;i< list.size();i++){//遍历数据
                XSSFRow createRow = sheet.createRow(3+i);
                for(int j=0;j<row.getLastCellNum();j++){//遍历key再通过反射写入数据(导入模板的第一行作为数据标识)
                    try {
                        String key = row.getCell(j).getStringCellValue();
                        String value = getMethod(list.get(i),key);//根据key找到对象中对应的值
                        createRow.createCell(j).setCellValue(value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    private void createFile(){
        try{
            File dir = new File(this.filePath);
            if(!dir.exists()){
                dir.mkdir();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(this.filePath+this.fileName);
            this.wb.write(fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getMethod(T t, String key) throws Exception{
        Class<?> clazz = t.getClass();
        PropertyDescriptor pd = new PropertyDescriptor(key,clazz);
        Method getMethod = pd.getReadMethod();
        Object o = getMethod.invoke(t);
        if(o instanceof Date){
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            return o==null? "" : sd.format(o);
        }
        return  o==null? "":o.toString();
    }

}
