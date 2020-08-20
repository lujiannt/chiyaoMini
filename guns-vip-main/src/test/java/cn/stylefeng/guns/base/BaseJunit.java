package cn.stylefeng.guns.base;

import cn.stylefeng.guns.GunsApplication;
import net.sf.jasperreports.engine.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


/**
 * 基础测试类
 *
 * @author stylefeng
 * @Date 2017/5/21 16:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GunsApplication.class)
@WebAppConfiguration
//@Transactional //打开的话测试之后数据可自动回滚
public class BaseJunit {
    private static final String path = "F:/JasperPrint/";
    private static final String jasperPath = "jasper/";
    private static final String pdfPath = "pdf/";

    @Autowired
    WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Before
    public void initDatabase() {
    }

    @Test
    public void test1() throws Exception {
        //1.读取.japser文件，构建输入流
        InputStream in = webApplicationContext.getServletContext().getResourceAsStream("test1_sql11.jasper");
        //2.构建Print对象，用于让模块结合数据
        //注意：JavaBean的属性名称和模版的Fileds的名称一致的
        HashMap parameters = new HashMap();
        parameters.put("name", "zxczxcz张三");
        parameters.put("url", "www.baidu.com");

        //第三个参数：JavaBean作为数据源，使用JRBeanCollectionDataSource对象来填充
        JasperPrint print = JasperFillManager.fillReport(in, parameters, new JREmptyDataSource());

        //3.使用Exporter导出PDF
        JasperExportManager.exportReportToPdfStream(print, new FileOutputStream("F:\\ABC.PDF"));
    }

//    @Test
//    public void test2() throws Exception {
//        printJasper();
//    }
//
//    public static void printJasper() {
//        String jasperName = "test1_sql11.jasper";
//        String pdfName = "test1_sql11.pdf";
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("name", "18068161321");
//        params.put("url", "https://www.baidu.com");
//        try {
//            JRDataSource dataSource = new JREmptyDataSource();
//            JasperPrint jasperPrint = JasperFillManager.fillReport(path + jasperPath + jasperName, params, dataSource);
//            JasperExportManager.exportReportToPdfFile(jasperPrint, path + pdfPath + pdfName);
//        } catch (JRException e) {
//            e.printStackTrace();
//        }
//    }
}
