package jx.lczj.service;

import jx.lczj.dao.CustomerDao;
import jx.lczj.dao.NewCustomerDao;
import jx.lczj.model.T_customer;
import jx.lczj.model.T_newcustomer;
import jx.lczj.utils.HttpRequest;
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
    CustomerDao  customerDao;

   @Resource
    NewCustomerDao newCustomerDao ;


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
    public T_customer login(String phone, String name, String sex,String headurl,HttpServletRequest request) {
        System.out.println(phone + "\n" + name + "\n" + sex);

        try {
            T_customer t_customer = customerDao.loadByPhone(phone);
            if (t_customer == null) {
                //String path = request.getSession().getServletContext().getRealPath("customerheads");

                String vip = UUID.randomUUID().toString().replace("-", "");
                int voucher = 0;
                T_newcustomer t_newcustomer = newCustomerDao.loadByPhone(phone);
                if(t_newcustomer!=null){
                    vip = t_newcustomer.getNewcustomer();
                    voucher = t_newcustomer.getPrize();
                    boolean ok = newCustomerDao.update(phone, 1);
                }

                System.out.println(vip.length());
                String pwd = phone;
                String birthday = "";
                String face = "default_head.png";
                //String face = vip+System.currentTimeMillis()+".png";
                boolean ok = customerDao.add(vip, name, phone, sex, pwd, birthday, face,voucher);
                t_customer = customerDao.loadByPhone(phone);
                //HttpRequest.downLoadFromUrl(headurl,face,path);

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
     * @return
     */
    @Transactional
    public String updateFace(MultipartFile file, String phone, HttpServletRequest request) {
        try {
            T_customer t_customer = customerDao.loadByPhone(phone);

            String times = System.currentTimeMillis()+"";
            //更新数据库
            boolean ok = customerDao.updateFace(phone, t_customer.getVip()+times+ ".png");
            //保存头像
            String path = request.getSession().getServletContext().getRealPath("customerheads");
            System.out.println(path);
            File targetFile = new File(path,t_customer.getVip()+times+ ".png");

            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            file.transferTo(targetFile);
            //删除原来头像
            if(t_customer.getFace()!=null &&  !t_customer.getFace().equals(""));
            File oldFile = new File(path, t_customer.getFace());
            if(oldFile .exists()){
                oldFile.delete();
            }
            return t_customer.getVip()+times+ ".png";
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}