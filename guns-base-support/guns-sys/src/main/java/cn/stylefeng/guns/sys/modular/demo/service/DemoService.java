package cn.stylefeng.guns.sys.modular.demo.service;

import cn.stylefeng.guns.base.db.model.params.DatabaseInfoParam;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.demo.entity.Demo;
import cn.stylefeng.guns.sys.modular.demo.mapper.DemoMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.PositionParam;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Service
public class DemoService extends ServiceImpl<DemoMapper, Demo>  {
    @Resource
    private DemoMapper demoMapper;

    public LayuiPageInfo listDemos(Demo param) {
        Page pageContext = getPageContext();
        IPage page = baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    public void delete() {
        demoMapper.delete();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }
}
