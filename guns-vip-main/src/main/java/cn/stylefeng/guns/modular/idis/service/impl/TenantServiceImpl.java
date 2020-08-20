package cn.stylefeng.guns.modular.idis.service.impl;

import cn.stylefeng.guns.modular.idis.entity.Tenant;
import cn.stylefeng.guns.modular.idis.mapper.TenantMapper;
import cn.stylefeng.guns.modular.idis.service.TenantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 租户管理 服务实现类
 * </p>
 *
 * @author hanqw
 * @since 2019-10-18
 */
@Service
@Slf4j
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements TenantService {

}
