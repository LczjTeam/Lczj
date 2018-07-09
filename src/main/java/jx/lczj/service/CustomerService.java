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
import java.util.UUID;

@Service
public class CustomerService {
   @Resource
    CustomerDao customerDao ;


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

    /**
     * 会员登录
     * @param phone
     * @param name
     * @return
     */
    @Transactional
    public T_customer login(String phone, String name,String sex) {
        System.out.println(phone+"\n"+name+"\n"+sex);

        try {
            T_customer t_customer = customerDao.loadByPhone(phone);
            if (t_customer == null) {

                String vip = UUID.randomUUID().toString().replace("-","") ;
                System.out.println(vip.length());
                String pwd = phone;
                String birthday = "";
                String face = "heads/defautl_head.png";
                boolean ok = customerDao.add(vip, name, phone, sex, pwd, birthday, face);
                t_customer = customerDao.loadByPhone(phone);
            }

            return t_customer;

        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }

    }
}
