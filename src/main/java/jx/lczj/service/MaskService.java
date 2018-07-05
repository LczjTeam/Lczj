package jx.lczj.service;

import jx.lczj.dao.MaskDao;
import jx.lczj.model.T_mask;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MaskService {

    @Resource
    MaskDao maskDao;
    public List<T_mask> loadList() {
        return maskDao.loadList();
    }

    public boolean add(int mask, String name) {
        return maskDao.add(mask,name);
    }

    public boolean delete(String mask) {
        return maskDao.delete(mask);
    }

    public boolean update(int mask, String name) {
        return maskDao.update(mask,name);
    }
}
