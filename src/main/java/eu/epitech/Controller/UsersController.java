package eu.epitech.Controller;

import java.util.Locale;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import eu.epitech.Model.Entity.User;
import eu.epitech.UserService;
import eu.epitech.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao UserDao;

    @GetMapping("/signup")
    public String signupGet(Locale locale, Model model, HttpSession session) {
        // Défini username dans la view :
        Integer userId = (Integer)session.getAttribute("user_id");
        User me = new User();
        if (userId != null) {
            me = UserDao.getUser(userId);
            if (me != null)
                model.addAttribute("username", me.getEmail());
        }

        if (userId != null)
            return "redirect:/panel";
        return "/signup";
    }

    @PostMapping("/signup")
    public String signupPost(Locale locale, Model model, HttpSession session, @ModelAttribute("user") @Valid User userPost, BindingResult result) {
        if (userPost.getEmail().isEmpty() || userPost.getPassword().isEmpty()) {
            model.addAttribute("errorMessage", "Vous devez renseigner tous les champs.");
            return "/signup";
        }
        if (UserDao.getUser(userPost.getEmail()) != null) {
            model.addAttribute("errorMessage", "Cette adresse email est déjà prise.");
            return "/signup";
        }
        UserDao.save(userPost);
        User userTmp = UserDao.getUser(userPost.getEmail());
        session.setAttribute("user_id", userTmp.getId());

        // Défini username dans la view :
        Integer userId = (Integer)session.getAttribute("user_id");
        User me = new User();
        if (userId != null) {
            me = UserDao.getUser(userId);
            if (me != null)
                model.addAttribute("username", me.getEmail());
        }
        return "redirect:/panel";
    }

    @GetMapping("/login")
    public String loginGet(Locale locale, Model model, HttpSession session) {
        // Défini username dans la view :
        Integer userId = (Integer)session.getAttribute("user_id");
        User me = new User();
        if (userId != null) {
            me = UserDao.getUser(userId);
            if (me != null)
                model.addAttribute("username", me.getEmail());
        }

        if (userId != null)
            return "redirect:/panel";
        return "/login";
    }

    @PostMapping("/login")
    public String loginPost(Locale locale, @ModelAttribute("user") @Valid User userPost,
                            BindingResult result, Model model, HttpSession session) {
        if (userPost.getEmail().isEmpty() || userPost.getPassword().isEmpty()) {
            model.addAttribute("errorMessage", "Vous devez renseigner tous les champs.");
            return "/login";
        }
        User userTmp = UserDao.getUser(userPost.getEmail());
        if (userTmp == null || !userTmp.getPassword().equals(userPost.getPassword())) {
            model.addAttribute("errorMessage", "Identifiants incorrects.");
            return "/signup";
        }
        if (userTmp.getBanned()) {
            model.addAttribute("errorMessage", "Ce compte a été banni.");
            return "/login";
        }
        session.setAttribute("user_id", userTmp.getId());

        // Défini username dans la view :
        Integer userId = (Integer)session.getAttribute("user_id");
        User me = new User();
        if (userId != null) {
            me = UserDao.getUser(userId);
            if (me != null)
                model.addAttribute("username", me.getEmail());
        }
        return "redirect:/panel";
    }

    @GetMapping("/logout")
    public String logout(Locale locale, Model model, HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
