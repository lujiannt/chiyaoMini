package cn.stylefeng.guns.sys.modular.demo.controller;

import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.base.db.model.params.DatabaseInfoParam;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.sys.modular.demo.ExcelUtils;
import cn.stylefeng.guns.sys.modular.demo.entity.Demo;
import cn.stylefeng.guns.sys.modular.demo.service.DemoService;
import cn.stylefeng.guns.sys.modular.system.model.params.PositionParam;
import cn.stylefeng.guns.sys.modular.system.service.LoginLogService;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/demo")
public class DemoController {

    private static String PREFIX = "/modular/demo/";

    @Resource
    private DemoService demoService;

    @RequestMapping("")
    public String index() {
        return PREFIX + "demo.html";
    }

    @RequestMapping("/map1")
    public String map1() {
        return PREFIX + "map1.html";
    }

    @RequestMapping("/map2")
    public String map2() {
        return PREFIX + "map2.html";
    }

    @RequestMapping("/pdf")
    public String pdf() {
        return PREFIX + "pdf.html";
    }


    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(@RequestParam(value = "condition", required = false) String condition) {

        Demo param = new Demo();
        if (StringUtils.isNoneBlank(condition)) {
            param.setName(condition);
        }
        return demoService.listDemos(param);
    }

    @RequestMapping("/del")
    @ResponseBody
    public ResponseData delete() {
        demoService.delete();
        return ResponseData.success();
    }


    @RequestMapping("/upload")
    @ResponseBody
    public ResponseData uploadExcel(@RequestPart("file") MultipartFile file, HttpServletRequest request) {
        String name = file.getOriginalFilename();
        request.getSession().setAttribute("upFile", name);
        String fileSavePath = ConstantsContext.getFileUploadPath();
        try {

            file.transferTo(new File(fileSavePath + name));

            List<String> topCells = ExcelUtils.queryTopCell(new FileInputStream(fileSavePath + name), name, "demo", null);
            System.out.println(topCells.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("fileId", IdWorker.getIdStr());
        return ResponseData.success(0, "上传成功", map);
    }

    @RequestMapping("/exportReport")
    public void exportReport(HttpSession session, HttpServletResponse response) throws Exception {
        //1.读取.japser文件，构建输入流
        InputStream in = session.getServletContext().getResourceAsStream("F:\\test1_sql11.jasper");

        //2.构建Print对象，用于让模块结合数据
        //注意：JavaBean的属性名称和模版的Fileds的名称一致的
        HashMap parameters = new HashMap();
        parameters.put("name", "用户昵称");
        parameters.put("url", "https://www.baidu.com");

        //第三个参数：JavaBean作为数据源，使用JRBeanCollectionDataSource对象来填充
        JasperPrint print = JasperFillManager.fillReport(in, parameters, new JREmptyDataSource());

        //3.使用Exporter导出PDF
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
    }

}
