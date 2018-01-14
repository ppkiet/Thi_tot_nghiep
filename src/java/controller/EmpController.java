/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author PC
 */
@Controller
public class EmpController {

    private static final Log log = LogFactory.getLog(EmpController.class.getName());
    private static final boolean isDebugging = log.isDebugEnabled();

    @Autowired
    ServletContext context;

    @RequestMapping(value = "/index")
    public String Index(ModelMap model, HttpServletRequest request) {
        Model m = new Model();
        List empl = m.listEmp();
        List cityl = m.listCity();
        model.addAttribute("empl", empl);
        model.addAttribute("cityl", cityl);
        return "index";
    }

    @RequestMapping(value = "/edit")
    public String Edit(ModelMap model, HttpServletRequest request) {
        Model m = new Model();
        String id = request.getParameter("id");
        List empl = m.listEmp();
        List cityl = m.listCity();
        model.addAttribute("empl", empl);
        model.addAttribute("emp", m.getEmp(id));
        model.addAttribute("cityl", cityl);
        return "index";
    }

    @RequestMapping(value = "/insert")
    public String Insert(ModelMap model, HttpServletRequest request, @RequestParam("image") MultipartFile userImage) {

        String userImagePath = "";
        if (userImage.isEmpty()) {
            userImagePath = "https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg";
        } else {
            try {
                userImagePath = context.getRealPath("/userimages/") + "\\" + userImage.getOriginalFilename();
                log.info("Avatar Full Path: " + userImagePath);
                userImage.transferTo(new File(userImagePath));
                userImagePath = "userimages/" + userImage.getOriginalFilename();
                log.info("Avatar Relative Path: " + userImagePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Model m = new Model();
        String id = request.getParameter("id");
        String action = request.getParameter("action");
        String name = request.getParameter("name");
        String salary = request.getParameter("sal");
        String city = request.getParameter("city");
        String phone = request.getParameter("phone");
//        String image = request.getParameter("image");

        if (action.equalsIgnoreCase("insert")) {
            m.insertEmp(name, salary, city, phone, userImagePath);
        } else if (action.equalsIgnoreCase("update")) {
            m.updateEmp(name, city, salary, phone, userImagePath, id);
        }

        List empl = m.listEmp();
        List cityl = m.listCity();
        model.addAttribute("empl", empl);
        model.addAttribute("cityl", cityl);
        return "index";
    }

    @RequestMapping(value = "/delete")
    public String Delete(ModelMap model, HttpServletRequest request) {
        Model m = new Model();
        String id = request.getParameter("id");
        m.deleteEmp(id);

        List empl = m.listEmp();
        List cityl = m.listCity();
        model.addAttribute("empl", empl);
        model.addAttribute("cityl", cityl);
        return "index";
    }
}
