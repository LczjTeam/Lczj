package jx.lczj.service;

import jx.lczj.dao.AdminDao;
import jx.lczj.dao.NewsDao;
import jx.lczj.model.T_color;
import jx.lczj.model.T_news;
import jx.lczj.viewmodel.AdminVo;
import jx.lczj.viewmodel.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by 14260 on 2018/6/23.
 */

@Service
public class NewsService {


    @Resource
    private NewsDao newsDao ;

    @Resource
    private AdminDao adminDao;


    /**
     * 发布文章
     * @param item
     * @param title
     * @param keyword
     * @param top
     * @param publish
     * @param file
     * @param content
     * @param request
     * @param session
     * @return
     */
    @Transactional
    public NewsVo add(int item, String title, String keyword, String top, String publish, MultipartFile file, String content, HttpServletRequest request, HttpSession session) {

        try {


            AdminVo avo = (AdminVo) session.getAttribute("admin");
            System.out.println("开始");
            System.out.println(file == null);
            System.out.println(file.getOriginalFilename().equals(""));
            String path = request.getSession().getServletContext().getRealPath("stories");

            SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datetime = format0.format((new Date()).getTime());

            String ctimes = System.currentTimeMillis() + "";

            String photoName = "";
            //检测是否you图片
            if (file != null && !file.getOriginalFilename().equals("")) {
                //保存图片
                System.out.println("开始，保存");
                photoName = avo.getT_admin().getAdmin() + ctimes + ".png";
                System.out.println(photoName);
                System.out.println(path);
                File editFile = new File(path, photoName);
                if (!editFile.exists()) {
                    editFile.mkdirs();
                }

                //保存
                file.transferTo(editFile);

            }

            //保存content
            String contentFile = avo.getT_admin().getAdmin() + ctimes + "-content.html";
            File cf = new File(contentFile);
            if (!cf.exists()) {
                cf.mkdirs();
            }
            BufferedWriter brc = new BufferedWriter(new OutputStreamWriter(new FileOutputStream((path + "/" + contentFile)), "UTF-8"));
            brc.write(content);
            brc.close();


            //保存为html
            String str = "<!DOCTYPE html>" +
                    "<html>" +
                    "" +
                    "<head>" +
                    "" +
                    "    <meta charset=\"utf-8\">" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                    "    <title>" + title + "</title>" +
                    "    <link href=\"css/bootstrap.min.css?v=3.3.5\" rel=\"stylesheet\">" +
                    "    <link href=\"css/font-awesome.min.css?v=4.4.0\" rel=\"stylesheet\">" +
                    "    <link href=\"css/animate.min.css\" rel=\"stylesheet\">" +
                    "    <link href=\"css/style.min.css?v=4.0.0\" rel=\"stylesheet\"><base target=\"_blank\">" +
                    "</head>" +
                    "<body class=\"gray-bg\">" +
                    "    <div class=\"wrapper wrapper-content  animated fadeInRight article\" style=\"padding-top:0px;\">" +
                    "        <div class=\"row\">" +
                    "            <div class=\"col-lg-10 col-sm-12 col-lg-offset-1 \"  style=\"padding:5px 5px 5px 5px;\">" +
                    "                <div class=\"ibox\">" +
                    "                    <div class=\"ibox-content\" style=\"padding:15px 15px 15px 15px;\" >" +
                    "                       " +
                    "                        <div class=\"text-center article-title\">" +
                    "                            <h1 style=\"font-size:28px;\">" + title + " </h1>" +
                    "                        </div> " +
                    "            <div style=\"height:40px;width:100%;\" >" +
                    "               <div class=\"pull-right\">" +
                    "                  <button class=\"btn btn-white btn-xs\" type=\"button\">发布人：" + avo.getT_admin().getName() + "</button>" +
                    "                   <button class=\"btn btn-white btn-xs\" type=\"button\">时间：" + datetime + "</button>" +
                    "               </div>" +
                    "            </div>" + content + "</div>" +
                    "                </div>" +
                    "            </div>" +
                    "        </div>" +
                    "    </div>" +
                    "    <script src=\"js/jquery.min.js?v=2.1.4\"></script>" +
                    "    <script src=\"js/bootstrap.min.js?v=3.3.5\"></script>" +
                    "    <script src=\"js/content.min.js?v=1.0.0\"></script>" +
                    "</body>" +
                    "" +
                    "</html>";


            System.out.println(str);
            String fileName = avo.getT_admin().getAdmin() + ctimes + ".html";
            File f = new File(path);
            if (!f.exists()) {
                f.mkdirs();
            }
            BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream((path + "/" + fileName)), "UTF-8"));
            br.write(str);
            br.close();

            String code = avo.getT_admin().getAdmin() + ctimes;
            boolean o = newsDao.add(code,
                    title,
                    datetime,
                    avo.getT_admin().getAdmin(),
                    keyword,
                    fileName,
                    item,
                    (top.equals("否") ? "0" : "1"),
                    (publish.equals("否") ? "0" : "1"),
                    photoName
            );


            T_news t_news = newsDao.loadByCode(code);
            NewsVo nvo = new NewsVo();
            nvo.setT_admin(avo.getT_admin());
            nvo.setT_news(t_news);
            return nvo;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * 通过银行编号 获取眼镜二三事
     * @param session
     * @return
     */
    public List<NewsVo> list1(HttpSession session,int items) {
        try {

            AdminVo avo = (AdminVo) session.getAttribute("admin");

            List<NewsVo> lnvs = new ArrayList<NewsVo>();
            List<T_news> nls = newsDao.listByAdmin(avo.getT_admin().getAdmin(),items);

            for (T_news t : nls) {
                NewsVo nvo = new NewsVo();
                nvo.setT_admin(avo.getT_admin());
                nvo.setT_news(t);
                lnvs.add(nvo);
            }
            return lnvs;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 删除文章
     * @param code
     * @return
     */
    @Transactional
    public boolean delete(String code,HttpServletRequest request) {

        try {
            String path = request.getSession().getServletContext().getRealPath("stories");

            T_news t_news = newsDao.loadByCode(code);

            //删除content文件
            File cf = new File(path+"/"+t_news.getCode()+"-content.html");
            if (cf.exists()) {
                cf.delete();
            }

            //删除html文件
            File f = new File(path+"/"+t_news.getFilename());
            if (f.exists()) {
                f.delete();
            }
            //删除图片
            File ph = new File(path+"/"+t_news.getPhoto());
            if (ph.exists()) {
                ph.delete();
            }

            return newsDao.delete(code);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());

        }
    }

    /**
     * 获取文章信息
     * @param code
     * @return
     */
    public NewsVo loadById(String code){
        NewsVo nvo = new NewsVo();
        T_news t = newsDao.loadByCode(code);
        nvo.setT_admin(adminDao.loadById(t.getAuthor()));
        nvo.setT_news(t);
       return  nvo;
    }

    /**
     *修改
     * @param code
     * @param title
     * @param keyword
     * @param top
     * @param publish
     * @param file
     * @param content
     * @param request
     * @param session
     * @return
     */
    public NewsVo update(String code,int items, String title, String keyword, String top, String publish, MultipartFile file, String content, HttpServletRequest request, HttpSession session) {


        try {
            T_news t_news = newsDao.loadByCode(code);

            AdminVo avo = (AdminVo) session.getAttribute("admin");
            System.out.println("开始");
            System.out.println(file == null);
            System.out.println(file.getOriginalFilename().equals(""));
            String path = request.getSession().getServletContext().getRealPath("stories");

            SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datetime = format0.format(t_news.getIssue_date());

            String photoName = t_news.getPhoto()==null ? "" :  t_news.getPhoto();
            String ctimes = System.currentTimeMillis() + "";

            //检测是否you图片
            if (file != null && !file.getOriginalFilename().equals("")) {

                //删除原有图片
                File oldF = new File(path, photoName);
                if (!photoName.equals("") && oldF.exists()) {
                    oldF.delete();
                }

                //保存图片
                photoName = avo.getT_admin().getAdmin()+ctimes+".png";
                System.out.println(photoName);
                System.out.println(path);
                File editFile = new File(path, photoName);
                if (!editFile.exists()) {
                    editFile.mkdirs();
                }
                //保存
                file.transferTo(editFile);

            }

            //删除原有content文件
            String[] ss = t_news.getFilename().split("\\.");
            System.out.println(ss);


            File oF = new File(path, ss[0]+"-content.html");
            if (oF.exists()) {
                oF.delete();
            }

            //保存content
            String contentFile = avo.getT_admin().getAdmin()+ctimes+ "-content.html";
            File cf = new File(contentFile);
            if (!cf.exists()) {
                cf.mkdirs();
            }
            BufferedWriter brc = new BufferedWriter(new OutputStreamWriter(new FileOutputStream((path + "/" + contentFile)), "UTF-8"));
            brc.write(content);
            brc.close();

            //删除原有的html
            File fft = new File(path, t_news.getFilename());
            if (fft.exists()) {
                fft.delete();
            }


            //保存为html
            String str = "<!DOCTYPE html>" +
                    "<html>" +
                    "" +
                    "<head>" +
                    "" +
                    "    <meta charset=\"utf-8\">" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                    "    <title>" + title + "</title>" +
                    "    <link href=\"css/bootstrap.min.css?v=3.3.5\" rel=\"stylesheet\">" +
                    "    <link href=\"css/font-awesome.min.css?v=4.4.0\" rel=\"stylesheet\">" +
                    "    <link href=\"css/animate.min.css\" rel=\"stylesheet\">" +
                    "    <link href=\"css/style.min.css?v=4.0.0\" rel=\"stylesheet\"><base target=\"_blank\">" +
                    "</head>" +
                    "<body class=\"gray-bg\">" +
                    "    <div class=\"wrapper wrapper-content  animated fadeInRight article\"  style=\"padding-top:0px;\">" +
                    "        <div class=\"row\">" +
                    "            <div class=\"col-lg-10 col-sm-12 col-lg-offset-1\"  style=\"padding:5px 5px 5px 5px;\">" +
                    "                <div class=\"ibox\">" +
                    "                    <div class=\"ibox-content\" style=\"padding:15px 15px 15px 15px;\" >" +
                    "                       " +
                    "                        <div class=\"text-center article-title\">" +
                    "                            <h1 style=\"font-size:28px;\">" + title + " </h1>" +
                    "                        </div> " +
                    "            <div style=\"height:40px;width:100%;\" >" +
                    "               <div class=\"pull-right\">" +
                    "                  <button class=\"btn btn-white btn-xs\" type=\"button\">发布人：" + avo.getT_admin().getName() + "</button>" +
                    "                   <button class=\"btn btn-white btn-xs\" type=\"button\">时间：" + datetime + "</button>" +
                    "               </div>" +
                    "            </div>" + content + "</div>" +
                    "                </div>" +
                    "            </div>" +
                    "        </div>" +
                    "    </div>" +
                    "    <script src=\"js/jquery.min.js?v=2.1.4\"></script>" +
                    "    <script src=\"js/bootstrap.min.js?v=3.3.5\"></script>" +
                    "    <script src=\"js/content.min.js?v=1.0.0\"></script>" +
                    "</body>" +
                    "" +
                    "</html>";


            System.out.println(str);
            String fileName = avo.getT_admin().getAdmin()+ctimes + ".html";
            File f = new File(path);
            if (!f.exists()) {
                f.mkdirs();
            }
            BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream((path + "/" + fileName)), "UTF-8"));
            br.write(str);
            br.close();


            boolean o = newsDao.update(code,
                    title,
                    datetime,
                    avo.getT_admin().getAdmin(),
                    keyword,
                    fileName,
                    items,
                    (top.equals("否") ? "0" : "1"),
                    (publish.equals("否") ? "0" : "1"),
                    photoName
            );

            t_news = newsDao.loadByCode(code);
            NewsVo nvo = new NewsVo();
            nvo.setT_admin(avo.getT_admin());
            nvo.setT_news(t_news);
            return nvo;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


    /**
     * 通过类别获取文章
     * @param items
     * @return
     */
    public List<NewsVo> list(int items) {
        try {


            List<NewsVo> lnvs = new ArrayList<NewsVo>();
            List<T_news> nls = newsDao.list(items);
            for (T_news t : nls) {
                NewsVo nvo = new NewsVo();
                nvo.setT_admin(adminDao.loadById(t.getAuthor()));
                nvo.setT_news(t);
                lnvs.add(nvo);
            }
            return lnvs;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean deletePhoto(String code, HttpServletRequest request) {

        try {
            String path = request.getSession().getServletContext().getRealPath("stories");

            T_news t_news = newsDao.loadByCode(code);

            //删除图片
            File ph = new File(path+"/"+t_news.getPhoto());
            if (ph.exists()) {
                ph.delete();
            }

            return newsDao.deletePhoto(code);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());

        }
    }
}
