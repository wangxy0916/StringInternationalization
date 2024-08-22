package com.wxy.stringinternationalization;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 源文件目录
     */
    public static final String PATH_SOURCE = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "com.wxy.stringInternationalization" + File.separator + "source" + File.separator;
    /**
     * 目标文件目录
     */
    public static final String PATH_TARGET = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "com.wxy.stringInternationalization" + File.separator + "target" + File.separator;

    private Button btnExcel2Xml, btnXml2Excel;

    /**
     * 表格的列数，Excel每一行的列数应当是一样的
     */
    private int columnNum;


    /**
     * 表格的行数，从0开始计，Excel每一列的行数应当是一样的
     */
    private int rowNum;

    /**
     * 正在读写的列数
     */
    private int currentColumnNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"}, 1);

    }

    private void initViews() {
        btnExcel2Xml = findViewById(R.id.btn1);
        btnXml2Excel = findViewById(R.id.btn2);
        btnExcel2Xml.setOnClickListener(this);
        btnXml2Excel.setOnClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            FileUtil.createDir(PATH_SOURCE);
            FileUtil.createDir(PATH_TARGET);
        }
    }




    private FileWriter writer;
    private void excel2xml(){
        //----------------------------xlsx格式的解析-------------------------
        try {
        //创建工作簿对象
        FileInputStream fileInputStream = new FileInputStream(PATH_SOURCE + File.separator + "test.xlsx");
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
        //获取工作簿下的sheet个数
            int sheetNum = xssfWorkbook.getNumberOfSheets();
            Log.d("wxyy", "sheet个数 = " + sheetNum);


//            xmlFile = new File(PATH_TARGET + row.getCell(k).toString() + ".xml");

//            String s = "<string name=\"" + row.getCell(0) + "\">" + row.getCell(k) + "</string>" + "\n";
//            try{
//                writer = new FileWriter(xmlFile, true);
//                writer.append(s);
//                writer.flush();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (null != writer)
//                    writer.close();
//            }

            //遍历所有sheet
            for (int i = 0; i < sheetNum; i++){
                XSSFSheet sheet = xssfWorkbook.getSheetAt(i);
                Log.d("wxyy", "-----------------sheet:" + sheet.getSheetName() + "-----------------");
                //获取最后一行的下标，即总行数。此处从0开始,这里从第一行开始到最后一行，包含中间的空行
                int maxRow = sheet.getLastRowNum();
                Log.d("wxyy", "最大行数 = " + (maxRow + 1));
                //遍历每一行
                for (int j = 0; j <= maxRow; j++){
                    //获取指定行，从0开始，如果这一行没有数据返回null则跳过
                    XSSFRow row = sheet.getRow(j);
                    if (row == null){
                        continue;
                    }
                    //获取指定行的总列数，这里返回的是有内容的单元格个数,包含空字符串的单元格
                    int columnNum = row.getPhysicalNumberOfCells();
                    Log.d("wxyy", "第" + (j + 1) + "行共" + columnNum + "列");
                    //遍历每一列
                    for (int k = 0; k < 10; k++){
                        //获取指定行的指定列，从0开始,如果是空单元格返回null，编辑后清空的单元格返回""
                        XSSFCell cell = row.getCell(k);
                        Log.d("wxyy", "内容：" + cell);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //----------------------------xls格式的解析---------------------
//        try {
//            //创建工作簿
//            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream("E://java17//java_practise//poiReadExcel//src//main//resources//chart.xls"));
//            //获取工作簿下sheet的个数
//            int sheetNum = hssfWorkbook.getNumberOfSheets();
//            Log.d("wxyy", "该excel文件中总共有：" + sheetNum + "个sheet");
//            //遍历工作簿中的所有数据
//            for (int i = 0; i < sheetNum; i++) {
//                //读取第i个工作表
//                Log.d("wxyy", "读取第" + (i + 1) + "个sheet");
//                HSSFSheet sheet = hssfWorkbook.getSheetAt(i);
//                //获取最后一行的num，即总行数。此处从0开始
//                int maxRow = sheet.getLastRowNum();
//                for (int row = 0; row <= maxRow; row++) {
//                    //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
//                    int maxRol = sheet.getRow(row).getLastCellNum();
//                    Log.d("wxyy", "--------第" + row + "行的数据如下--------");
//                    for (int rol = 0; rol < maxRol; rol++) {
//                        Log.d("wxyy",sheet.getRow(row).getCell(rol) + "  ");
//                    }
//                    Log.d("wxyy", "------------------------------------");
//                }
//            }
//        }catch (Exception e){
//        }
    }





    private void excel2xmlByEasy(){
//// 读取 Excel 文件
//        String filePath = PATH_SOURCE + File.separator + "test.xlsx";
//
//
//        EasyExcel.read(filePath, LanguageBean.class, new DemoDataListener()).sheet().doRead();

    }


    /**
     * 写入XML
     * @param filePath
     * @param id
     * @param text
     */
    private void writeXML(String filePath, String id, String text){
        File file = new File(filePath);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                excel2xml();
                break;
            case R.id.btn2:
                excel2xmlByEasy();
                break;
        }
    }
}