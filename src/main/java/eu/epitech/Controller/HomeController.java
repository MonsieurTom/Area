package eu.epitech.Controller;

import java.util.Locale;

import eu.epitech.Model.Entity.User;
import eu.epitech.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private UserDao UserDao;

    @GetMapping("/")
    public String home(Locale locale, Model model, HttpSession session) {
        // Défini username dans la view :
        Integer userId = (Integer)session.getAttribute("user_id");
        User me = new User();
        if (userId != null) {
            me = UserDao.getUser(userId);
            if (me != null)
                model.addAttribute("username", me.getEmail());
        }
        return "home";
    }

}
