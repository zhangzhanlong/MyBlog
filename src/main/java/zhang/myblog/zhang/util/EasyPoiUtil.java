package zhang.myblog.zhang.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Date: 2018/7/23 12:08
 * @Description: Excle 文件导入导出Util(easypoi)
 */

public abstract class EasyPoiUtil {

    /**
     * 导入Excel
     *
     * @param TitleRows
     * @param HeadRows
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> importExcel(Integer TitleRows, Integer HeadRows, InputStream file) throws Exception {
        ImportParams params = new ImportParams();

        params.setTitleRows(TitleRows);
        params.setHeadRows(HeadRows);
        List<Map<String, Object>> list = ExcelImportUtil.importExcel(file, Map.class, params);
        return list;
    }

    //文件方式
    public static List<Map<String, Object>> importExcel(Integer TitleRows, Integer HeadRows, File file) throws Exception {
        ImportParams params = new ImportParams();

        params.setTitleRows(TitleRows);
        params.setHeadRows(HeadRows);
        List<Map<String, Object>> list = ExcelImportUtil.importExcel(file, Map.class, params);
        return list;
    }

    /**
     * 数据转excel并下载
     * 有实体类的情况
     *
     * @param response
     * @param list
     * @param excelname
     * @param pojoClass
     */

    public static void DownloadExcel(HttpServletResponse response, List<?> list, String excelname, Class<?> pojoClass) {

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), pojoClass, list);
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            String fn = new String(excelname.getBytes(), "ISO8859_1");
            response.setHeader("Content-disposition", "attachment; filename=" + fn + ".xls");
            workbook.write(outputStream);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 数据类型为Map的导出
     *
     * @param response
     * @param excelname
     * @param map
     * @param title
     */

    public static void DownloadExcel(HttpServletResponse response, String excelname, List<Map<String, Object>> map,
                                     Map<String, String> title) {

        try {
            List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();

            Set<String> keySet = title.keySet();
            int i = 0;
            for (String key : keySet) {
                String value = title.get(key);
                System.out.println(key + "------------");

                ExcelExportEntity excelExportEntity = new ExcelExportEntity(key, value);
                excelExportEntity.setOrderNum(i);
                entity.add(excelExportEntity);
                i++;
            }

            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), entity, map);
            OutputStream outputStream = null;
            outputStream = response.getOutputStream();
            String fn = new String(excelname.getBytes(), "ISO8859_1");
            response.setHeader("Content-disposition", "attachment; filename=" + fn + ".xls");
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
