package jx.lczj.service;

import jx.lczj.dao.CustomerDao;
import jx.lczj.model.T_customer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Service
public class CustomerService {
   @Resource
    CustomerDao customerDao;


    /**
     * 获取会员列表
     * @return
     */
    public List<T_customer> loadCustomer() {

        return customerDao.loadCustomer();
    }

    /**
     * 获取会员信息
     * @param customer
     * @return
     */
    public T_customer loadByVip(String customer) {
        return customerDao.loadByVip(customer);
    }

    /**
     * 重置密码
     * @param vip
     * @return
     */
    public boolean resetPwd(String vip) {
        return customerDao.resetPwd(vip) ;
    }

}
