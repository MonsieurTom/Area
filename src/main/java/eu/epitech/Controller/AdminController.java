package eu.epitech.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import eu.epitech.AreaDao;
import eu.epitech.Model.Entity.Area;
import eu.epitech.Model.Entity.User;
import eu.epitech.UserDao;
import eu.epitech.Controller.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AdminController {

    @Autowired
    private UserDao UserDao;

	@Autowired
	private AreaDao AreaDao;

    @GetMapping("/admin")
    public String admin(Locale locale, Model model, HttpSession session) {
        // Défini username dans la view et bloque les connexions interdites
        Integer userId = (Integer)session.getAttribute("user_id");
        User me = new User();
        if (userId == null)
            return "redirect:/login";
        else {
            me = UserDao.getUser(userId);
            if (me == null) {
                model.addAttribute("errorMessage", "Cet utilisateur n'existe pas.");
                return "/home";
            }
            else {
                String email = me.getEmail();
                model.addAttribute("username", email);
                if (!email.equals("admin@admin.admin")) {
                    model.addAttribute("errorMessage", "Vous n'avez pas l'autorisation d'accéder à cette page.");
                    return "/home";
                }
            }
        }

        return "admin";
    }

    @GetMapping("/admin/users")
    public String users(Locale locale, Model model, HttpSession session) {
        // Défini username dans la view et bloque les connexions interdites
        Integer userId = (Integer)session.getAttribute("user_id");
        User me = new User();
        if (userId == null)
            return "redirect:/login";
        else {
            me = UserDao.getUser(userId);
            if (me == null) {
                model.addAttribute("errorMessage", "Cet utilisateur n'existe pas.");
                return "/home";
            }
            else {
                String email = me.getEmail();
                model.addAttribute("username", email);
                if (!email.equals("admin@admin.admin")) {
                    model.addAttribute("errorMessage", "Vous n'avez pas l'autorisation d'accéder à cette page.");
                    return "/home";
                }
            }
        }

        List<User> users = UserDao.list();
        model.addAttribute("users", users);

        return "admin/users";
    }

    @GetMapping("/admin/add-user")
    public String addUserGet(Locale locale, Model model, HttpSession session) {
        // Défini username dans la view et bloque les connexions interdites
        Integer userId = (Integer)session.getAttribute("user_id");
        User me = new User();
        if (userId == null)
            return "redirect:/login";
        else {
            me = UserDao.getUser(userId);
            if (me == null) {
                model.addAttribute("errorMessage", "Cet utilisateur n'existe pas.");
                return "/home";
            }
            else {
                String email = me.getEmail();
                model.addAttribute("username", email);
                if (!email.equals("admin@admin.admin")) {
                    model.addAttribute("errorMessage", "Vous n'avez pas l'autorisation d'accéder à cette page.");
                    return "/home";
                }
            }
        }

        return "admin/add-user";
    }

    @PostMapping("/admin/add-user")
    public String addUserPost(Locale locale, Model model, HttpSession session, @ModelAttribute("user") @Valid User userPost, BindingResult result) {
        // Défini username dans la view et bloque les connexions interdites
        Integer userId = (Integer)session.getAttribute("user_id");
        User me = new User();
        if (userId == null)
            return "redirect:/login";
        else {
            me = UserDao.getUser(userId);
            if (me == null) {
                model.addAttribute("errorMessage", "Cet utilisateur n'existe pas.");
                return "/home";
            }
            else {
                String email = me.getEmail();
                model.addAttribute("username", email);
                if (!email.equals("admin@admin.admin")) {
                    model.addAttribute("errorMessage", "Vous n'avez pas l'autorisation d'accéder à cette page.");
                    return "/home";
                }
            }
        }

        if (userPost.getEmail().isEmpty() || userPost.getPassword().isEmpty()) {
            model.addAttribute("errorMessage", "Vous devez renseigner tous les champs.");
            return "/admin/add-user";
        }
        if (UserDao.getUser(userPost.getEmail()) != null) {
            model.addAttribute("errorMessage", "Cette adresse email est déjà prise.");
            return "/admin/add-user";
        }
        userPost.setBanned(false);
        UserDao.save(userPost);

        List<User> users = UserDao.list();
        model.addAttribute("users", users);

        return "admin/users";
    }

    @GetMapping("/admin/edit-user/{id}")
    public String editUserGet(Locale locale, Model model, HttpSession session, @PathVariable("id") int id) {
        // Défini username dans la view et bloque les connexions interdites
        Integer userId = (Integer)session.getAttribute("user_id");
        User me = new User();
        if (userId == null)
            return "redirect:/login";
        else {
            me = UserDao.getUser(userId);
            if (me == null) {
                model.addAttribute("errorMessage", "Cet utilisateur n'existe pas.");
                return "/home";
            }
            else {
                String email = me.getEmail();
                model.addAttribute("username", email);
                if (!email.equals("admin@admin.admin")) {
                    model.addAttribute("errorMessage", "Vous n'avez pas l'autorisation d'accéder à cette page.");
                    return "/home";
                }
            }
        }

        User user = UserDao.getUser(id);
        if (user == null) {
            model.addAttribute("errorMessage", "Cet utilisateur n'existe pas.");
            List<User> users = UserDao.list();
            model.addAttribute("users", users);
            return "/admin/users";
        }
        model.addAttribute("user", user);

		List<Area> areas = AreaDao.getAreasByUser(id);
		model.addAttribute("areas", areas);

        return "admin/edit-user";
    }

    @PostMapping("/admin/edit-user/{id}")
    public String editUserPost(Locale locale, Model model, HttpSession session, @PathVariable("id") int id, @ModelAttribute("user") @Valid User userPost) {
        // Défini username dans la view et bloque les connexions interdites
        Integer userId = (Integer)session.getAttribute("user_id");
        User me = new User();
        if (userId == null)
            return "redirect:/login";
        else {
            me = UserDao.getUser(userId);
            if (me == null) {
                model.addAttribute("errorMessage", "Cet utilisateur n'existe pas.");
                return "/home";
            }
            else {
                String email = me.getEmail();
                model.addAttribute("username", email);
                if (!email.equals("admin@admin.admin")) {
                    model.addAttribute("errorMessage", "Vous n'avez pas l'autorisation d'accéder à cette page.");
                    return "/home";
                }
            }
        }

        User user = UserDao.getUser(id);
        if (user == null) {
            model.addAttribute("errorMessage", "Cet utilisateur n'existe pas.");
            List<User> users = UserDao.list();
            model.addAttribute("users", users);
            return "/admin/users";
        }

		if (userPost.getEmail().isEmpty() || userPost.getPassword().isEmpty()) {
			model.addAttribute("errorMessage", "Vous devez renseigner tous les champs.");
			return "/admin/edit-user/" + id;
		}
		if (UserDao.getUser(userPost.getEmail()) != null) {
			model.addAttribute("errorMessage", "Cette adresse email est déjà prise.");
			return "/admin/edit-user/" + id;
		}

        userPost.setId(id);
        if (userPost.getBanned() == null)
            userPost.setBanned(false);
        UserDao.update(userPost);

        List<User> users = UserDao.list();
        model.addAttribute("users", users);

        return "admin/users";
    }

    @GetMapping("/admin/modules")
    public String modules(Locale locale, Model model, HttpSession session) {
        // Défini username dans la view et bloque les connexions interdites
        Integer userId = (Integer)session.getAttribute("user_id");
        User me = new User();
        if (userId == null)
            return "redirect:/login";
        else {
            me = UserDao.getUser(userId);
            if (me == null) {
                model.addAttribute("errorMessage", "Cet utilisateur n'existe pas.");
                return "/home";
            }
            else {
                String email = me.getEmail();
                model.addAttribute("username", email);
                if (!email.equals("admin@admin.admin")) {
                    model.addAttribute("errorMessage", "Vous n'avez pas l'autorisation d'accéder à cette page.");
                    return "/home";
                }
            }
        }

        return "admin/modules";
    }

    @GetMapping("/admin/delete-user/{id}")
    public String deleteUser(Locale locale, Model model, HttpSession session, @PathVariable("id") int id) {
        // Défini username dans la view et bloque les connexions interdites
        Integer userId = (Integer)session.getAttribute("user_id");
        User me = new User();
        if (userId == null)
            return "redirect:/login";
        else {
            me = UserDao.getUser(userId);
            if (me == null) {
                model.addAttribute("errorMessage", "Cet utilisateur n'existe pas.");
                return "/home";
            }
            else {
                String email = me.getEmail();
                model.addAttribute("username", email);
                if (!email.equals("admin@admin.admin")) {
                    model.addAttribute("errorMessage", "Vous n'avez pas l'autorisation d'accéder à cette page.");
                    return "/home";
                }
            }
        }

        UserDao.delete(id);

        List<User> users = UserDao.list();
        model.addAttribute("users", users);

        return "admin/users";
    }

    @GetMapping("/admin/newsletter")
    public String newsletterGet(Locale locale, Model model, HttpSession session) {
        // Défini username dans la view et bloque les connexions interdites
        Integer userId = (Integer)session.getAttribute("user_id");
        User me = new User();
        if (userId == null)
            return "redirect:/login";
        else {
            me = UserDao.getUser(userId);
            if (me == null) {
                model.addAttribute("errorMessage", "Cet utilisateur n'existe pas.");
                return "/home";
            }
            else {
                String email = me.getEmail();
                model.addAttribute("username", email);
                if (!email.equals("admin@admin.admin")) {
                    model.addAttribute("errorMessage", "Vous n'avez pas l'autorisation d'accéder à cette page.");
                    return "/home";
                }
            }
        }

        return "admin/newsletter";
    }

    @PostMapping("/admin/newsletter")
    public String newsletterPost(Locale locale, Model model, HttpSession session, @RequestParam(value="object") String object, @RequestParam(value="message") String message) {
        // Défini username dans la view et bloque les connexions interdites
        Integer userId = (Integer)session.getAttribute("user_id");
        User me = new User();
        if (userId == null)
            return "redirect:/login";
        else {
            me = UserDao.getUser(userId);
            if (me == null) {
                model.addAttribute("errorMessage", "Cet utilisateur n'existe pas.");
                return "/home";
            }
            else {
                String email = me.getEmail();
                model.addAttribute("username", email);
                if (!email.equals("admin@admin.admin")) {
                    model.addAttribute("errorMessage", "Vous n'avez pas l'autorisation d'accéder à cette page.");
                    return "/home";
                }
            }
        }

        List<User> users = UserDao.list();
        model.addAttribute("users", users);

        List<String> emails = new ArrayList<>();
        for (User u : users)
        {
            if (!u.getEmail().equalsIgnoreCase("admin@admin.admin"))
                emails.add(u.getEmail());
        }

        SendEmail se = new SendEmail();
        if (se.sendEmail(emails, object, message))
        	model.addAttribute("successMessage", "Message envoyé avec succès");
		else
			model.addAttribute("errorMessage", "Erreur d'envoi du message");
        return "admin/newsletter";
    }
}
