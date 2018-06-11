package jx.lczj.service;

import jx.lczj.dao.RoleDao;
import jx.lczj.model.T_role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 14260 on 2018/6/11.
 */


@Service
public class RoleService {

    @Resource
    RoleDao roleDao;

    public List<T_role> loadList() {
        return roleDao.loadList();
    }
}
