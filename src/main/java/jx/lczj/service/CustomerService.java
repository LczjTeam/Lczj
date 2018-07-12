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
    CustomerDao customerDao;


    /**
     * 获取会员列表
     *
     * @return
     */
    public List<T_customer> loadCustomer() {

        return customerDao.loadCustomer();
    }

    /**
     * 获取会员信息
     *
     * @param phone
     * @return
     */
    public T_customer loadByPhone(String phone) {
        return customerDao.loadByPhone(phone);
    }

    /**
     * 重置密码
     *
     * @param phone
     * @return
     */
    public boolean resetPwd(String phone) {
        return customerDao.resetPwd(phone);
    }

    /**
     * 会员登录
     *
     * @param phone
     * @param name
     * @return
     */
    @Transactional
    public T_customer login(String phone, String name, String sex) {
        System.out.println(phone + "\n" + name + "\n" + sex);

        try {
            T_customer t_customer = customerDao.loadByPhone(phone);
            if (t_customer == null) {

                String vip = UUID.randomUUID().toString().replace("-", "");
                System.out.println(vip.length());
                String pwd = phone;
                String birthday = "";
                String face = "heads/defautl_head.png";
                boolean ok = customerDao.add(vip, name, phone, sex, pwd, birthday, face);
                t_customer = customerDao.loadByPhone(phone);
            }

            return t_customer;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 编辑更新
     *
     * @param name
     * @param phone
     * @param sex
     * @param birthday
     * @return
     */
    @Transactional
    public boolean update(String name, String phone, String sex, String birthday) {
        try {
            boolean ok = customerDao.update(name, phone, sex, birthday);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 更新密码
     *
     * @param phone
     * @param pwd
     * @return
     */
    @Transactional
    public boolean update(String phone, String pwd) {
        try {
            boolean ok = customerDao.updatePwd(phone, pwd);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 更新密码
     *
     * @param phone
     * @param pwd
     * @return
     */
    @Transactional
    public boolean updatePwd(String phone, String pwd) {
        try {
            boolean ok = customerDao.updatePwd(phone, pwd);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 更新头像
     * @param phone
     * @param face
     * @return
     */
    @Transactional
    public boolean updateFace(String phone, String face) {
        try {
            boolean ok = customerDao.updateFace(phone, face);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}