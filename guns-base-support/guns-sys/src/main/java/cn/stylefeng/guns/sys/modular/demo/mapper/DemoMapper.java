package cn.stylefeng.guns.sys.modular.demo.mapper;

import cn.stylefeng.guns.sys.modular.demo.entity.Demo;
import cn.stylefeng.guns.sys.modular.system.model.params.PositionParam;
import cn.stylefeng.guns.sys.modular.system.model.result.PositionResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

public interface DemoMapper extends BaseMapper<Demo> {

    Page<PositionResult> customPageList(@Param("page") Page page, @Param("param") Demo demo);

    int delete();
}
