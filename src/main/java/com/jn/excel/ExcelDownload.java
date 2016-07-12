package com.jn.excel;
import com.jn.excel.ExcelDownload;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by death on 2016/7/11.
 */
@Controller
@RequestMapping("/hi")
public class ExcelDownload {

    /**
     * @功能：手工构建一个简单格式的Excel
     */
    private static List<Vo> getVo() throws Exception {
        List list = new ArrayList();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");

        Vo user1 = new Vo(1l, 2l, 3, new Date());
        Vo user2 = new Vo(1l, 2l, 3, new Date());
        Vo user3 = new Vo(1l, 2l, 3, new Date());
        list.add(user1);
        list.add(user2);
        list.add(user3);

        return list;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String hi(){
        return "index";
    }




    @RequestMapping(value = "excel")
    @ResponseBody
    public void download(HttpServletResponse response){
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("百度糯米城市区域价格");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("商品ID");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("城市ID");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("商品价格");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("导出时间");
        cell.setCellStyle(style);

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        List list = null;
        try {
            list = ExcelDownload.getVo();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow((int) i + 1);
            Vo stu = (Vo) list.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue((double) stu.getProductId());
            row.createCell((short) 1).setCellValue(stu.getCityId());
            row.createCell((short) 2).setCellValue((double) stu.getPrice());
            cell = row.createCell((short) 3);
            cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(stu.getExportTime()));
        }
        // 第六步，将文件存到指定位置

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            FileOutputStream fout = new FileOutputStream("D:/students.xls");
            wb.
            wb.write(fout);
            fout.close();


            File file = new File("D:/students.xls");

            byte[] b = new byte[1024];
            int len = 0;
            inputStream = new FileInputStream(file);
            outputStream = response.getOutputStream();

            response.setContentType("application/force-download");
            String filename = file.getName();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
            response.setContentLength((int) file.length());
            len = inputStream.read(b);


            while ((len = inputStream.read(b)) != -1) {
                outputStream.write(b, 0, len);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                    inputStream = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                    outputStream = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}