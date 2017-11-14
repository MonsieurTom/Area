package eu.epitech.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import eu.epitech.IModuleAction;
import eu.epitech.IModuleReaction;
import eu.epitech.Model.Entity.Action;
import eu.epitech.Model.Entity.Argument;
import eu.epitech.Model.Entity.User;
import eu.epitech.Model.Entity.Area;
import eu.epitech.Services.ModuleLoader;
import eu.epitech.UserDao;
import eu.epitech.AreaDao;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class ModulesController {

    @Autowired
    private UserDao UserDao;

    @Autowired
    private AreaDao AreaDao;

    @GetMapping("/panel")
    public String panel(Locale locale, Model model, HttpSession session) {
        // Défini username dans la view :
        Integer userId = (Integer)session.getAttribute("user_id");
        User me = new User();
        if (userId != null) {
            me = UserDao.getUser(userId);
            if (me != null)
                model.addAttribute("username", me.getEmail());
        }
        if (userId == null)
            return "redirect:/login";

        List<Area> areas = AreaDao.getAreasByUser(userId);
        model.addAttribute("areas", areas);

        return "/panel";
    }

    @GetMapping("/panel/modules")
    public String modules(Locale locale, Model model, HttpSession session) {
        // Défini username dans la view :
        Integer userId = (Integer)session.getAttribute("user_id");
        User me = new User();
        if (userId != null) {
            me = UserDao.getUser(userId);
            if (me != null)
                model.addAttribute("username", me.getEmail());
        }
        if (userId == null)
            return "redirect:/login";

        ModuleLoader moduleLoader = new ModuleLoader();
        moduleLoader.loadModules("./Modules");

        List<IModuleAction> modulesAction = moduleLoader.get_actionsModules();
        List<Action> actions = new ArrayList<>();
        for (IModuleAction moduleAction : modulesAction) {
			List<String> actionsNames = moduleAction.getActionsName();
			for (String actionName : actionsNames) {
				Action action = new Action();
				action.name = actionName;
				action.arguments = new ArrayList<>();
				List<Pair<String, String>> arguments = moduleAction.getArguments(actionName);
				for (Pair<String, String> argument : arguments) {
					action.arguments.add(new Argument(argument.getValue0(), argument.getValue1()));
				}
				actions.add(action);
			}
		}
		model.addAttribute("actions", actions);

		List<IModuleReaction> modulesReaction = moduleLoader.get_reactionsModules();
		List<Action> reactions = new ArrayList<>();
		for (IModuleReaction moduleReaction : modulesReaction) {
			List<String> reactionsNames = moduleReaction.getHandledReactions();
			for (String reactionName : reactionsNames) {
				Action reaction = new Action();
				reaction.name = reactionName;
				reaction.arguments = new ArrayList<>();
				List<Pair<String, String>> arguments = moduleReaction.getArguments(reactionName);
				for (Pair<String, String> argument : arguments) {
					reaction.arguments.add(new Argument(argument.getValue0(), argument.getValue1()));
				}
				reactions.add(reaction);
			}
		}
		model.addAttribute("reactions", reactions);

        return "/panel/modules";
    }

    @GetMapping("/panel/add-applet")
    public String addAppletGet(Locale locale, Model model, HttpSession session) {
        // Défini username dans la view :
        Integer userId = (Integer)session.getAttribute("user_id");
        User me = new User();
        if (userId != null) {
            me = UserDao.getUser(userId);
            if (me != null)
                model.addAttribute("username", me.getEmail());
        }
        if (userId == null)
            return "redirect:/login";

		ModuleLoader moduleLoader = new ModuleLoader();
		moduleLoader.loadModules("./Modules");

		List<IModuleAction> modulesAction = moduleLoader.get_actionsModules();
		List<Action> actions = new ArrayList<>();
		for (IModuleAction moduleAction : modulesAction) {
			List<String> actionsNames = moduleAction.getActionsName();
			for (String actionName : actionsNames) {
				Action action = new Action();
				action.name = actionName;
				action.arguments = new ArrayList<>();
				List<Pair<String, String>> arguments = moduleAction.getArguments(actionName);
				for (Pair<String, String> argument : arguments) {
					action.arguments.add(new Argument(argument.getValue0(), argument.getValue1()));
				}
				actions.add(action);
			}
		}
		model.addAttribute("actions", actions);

		List<IModuleReaction> modulesReaction = moduleLoader.get_reactionsModules();
		List<Action> reactions = new ArrayList<>();
		for (IModuleReaction moduleReaction : modulesReaction) {
			List<String> reactionsNames = moduleReaction.getHandledReactions();
			for (String reactionName : reactionsNames) {
				Action reaction = new Action();
				reaction.name = reactionName;
				reaction.arguments = new ArrayList<>();
				List<Pair<String, String>> arguments = moduleReaction.getArguments(reactionName);
				for (Pair<String, String> argument : arguments) {
					reaction.arguments.add(new Argument(argument.getValue0(), argument.getValue1()));
				}
				reactions.add(reaction);
			}
		}
		model.addAttribute("reactions", reactions);

        return "/panel/add-applet";
    }

    @PostMapping("/panel/add-applet")
    public String addAppletPost(Locale locale, Model model, HttpSession session, @ModelAttribute("area") Area areaPost, BindingResult result,
								@RequestParam Map<String, String> params) {
    	// Défini username dans la view :
        Integer userId = (Integer)session.getAttribute("user_id");
        User me = new User();
        if (userId != null) {
            me = UserDao.getUser(userId);
            if (me != null)
                model.addAttribute("username", me.getEmail());
        }
        if (userId == null)
            return "redirect:/login";

        if (areaPost.getActionName() == null || areaPost.getActionName().isEmpty() || areaPost.getReactionName() == null || areaPost.getReactionName().isEmpty()) {
            model.addAttribute("errorMessage", "Vous devez renseigner tous les champs.");
			ModuleLoader moduleLoader = new ModuleLoader();
			moduleLoader.loadModules("./Modules");

			List<IModuleAction> modulesAction = moduleLoader.get_actionsModules();
			List<Action> actions = new ArrayList<>();
			for (IModuleAction moduleAction : modulesAction) {
				List<String> actionsNames = moduleAction.getActionsName();
				for (String actionName : actionsNames) {
					Action action = new Action();
					action.name = actionName;
					action.arguments = new ArrayList<>();
					List<Pair<String, String>> arguments = moduleAction.getArguments(actionName);
					for (Pair<String, String> argument : arguments) {
						action.arguments.add(new Argument(argument.getValue0(), argument.getValue1()));
					}
					actions.add(action);
				}
			}
			model.addAttribute("actions", actions);

			List<IModuleReaction> modulesReaction = moduleLoader.get_reactionsModules();
			List<Action> reactions = new ArrayList<>();
			for (IModuleReaction moduleReaction : modulesReaction) {
				List<String> reactionsNames = moduleReaction.getHandledReactions();
				for (String reactionName : reactionsNames) {
					Action reaction = new Action();
					reaction.name = reactionName;
					reaction.arguments = new ArrayList<>();
					List<Pair<String, String>> arguments = moduleReaction.getArguments(reactionName);
					for (Pair<String, String> argument : arguments) {
						reaction.arguments.add(new Argument(argument.getValue0(), argument.getValue1()));
					}
					reactions.add(reaction);
				}
			}
			model.addAttribute("reactions", reactions);
            return "/panel/add-applet";
        }

        areaPost.setTmpData("");

        int nbArgs = Integer.parseInt(params.get("nbActionArgs"));
		ArrayList<String> actionsArg = new ArrayList<>();
		for (int i = 0; i < nbArgs; i++) {
			actionsArg.add(params.get("actionArg" + i));
		}
		areaPost.setActionArgs(actionsArg.stream().toArray(String[]::new));

		nbArgs = Integer.parseInt(params.get("nbReactionArgs"));
		ArrayList<String> reactionsArg = new ArrayList<>();
		for (int i = 0; i < nbArgs; i++) {
			reactionsArg.add(params.get("reactionArg" + i));
		}
		areaPost.setReactionArgs(reactionsArg.stream().toArray(String[]::new));

		areaPost.setUserId(userId);
        AreaDao.save(areaPost);

		List<Area> areas = AreaDao.getAreasByUser(userId);
        model.addAttribute("areas", areas);

        return "/panel";
    }

    @GetMapping("/panel/edit-applet/{id}")
    public String editAppletGet(Locale locale, Model model, HttpSession session, @PathVariable("id") int id) {
        // Défini username dans la view :
        Integer userId = (Integer)session.getAttribute("user_id");
        User me = new User();
        if (userId != null) {
            me = UserDao.getUser(userId);
            if (me != null)
                model.addAttribute("username", me.getEmail());
        }
        if (userId == null)
            return "redirect:/login";

        Area area = AreaDao.getArea(id);
        if (area == null) {
			model.addAttribute("errorMessage", "Cet Applet n'existe pas.");
			List<Area> areas = AreaDao.getAreasByUser(userId);
            model.addAttribute("areas", areas);
			return "/panel";
		}
        if (area.getUserId() != userId) {
            model.addAttribute("errorMessage", "Cet Applet n'est pas à vous. Vous n'avez pas l'autorisation de le modifier.");
			List<Area> areas = AreaDao.getAreasByUser(userId);
            model.addAttribute("areas", areas);
            return "/panel";
        }

        model.addAttribute("area", area);

		ModuleLoader moduleLoader = new ModuleLoader();
		moduleLoader.loadModules("./Modules");

		List<IModuleAction> modulesAction = moduleLoader.get_actionsModules();
		List<Action> actions = new ArrayList<>();
		for (IModuleAction moduleAction : modulesAction) {
			List<String> actionsNames = moduleAction.getActionsName();
			for (String actionName : actionsNames) {
				Action action = new Action();
				action.name = actionName;
				action.arguments = new ArrayList<>();
				List<Pair<String, String>> arguments = moduleAction.getArguments(actionName);
				for (Pair<String, String> argument : arguments) {
					action.arguments.add(new Argument(argument.getValue0(), argument.getValue1()));
				}
				actions.add(action);
			}
		}
		model.addAttribute("actions", actions);

		List<IModuleReaction> modulesReaction = moduleLoader.get_reactionsModules();
		List<Action> reactions = new ArrayList<>();
		for (IModuleReaction moduleReaction : modulesReaction) {
			List<String> reactionsNames = moduleReaction.getHandledReactions();
			for (String reactionName : reactionsNames) {
				Action reaction = new Action();
				reaction.name = reactionName;
				reaction.arguments = new ArrayList<>();
				List<Pair<String, String>> arguments = moduleReaction.getArguments(reactionName);
				for (Pair<String, String> argument : arguments) {
					reaction.arguments.add(new Argument(argument.getValue0(), argument.getValue1()));
				}
				reactions.add(reaction);
			}
		}
		model.addAttribute("reactions", reactions);

        return "/panel/edit-applet";
    }

    @PostMapping("/panel/edit-applet/{id}")
    public String editAppletPost(Locale locale, Model model, HttpSession session, @PathVariable("id") int id, @ModelAttribute("area") Area areaPost, BindingResult result,
								 @RequestParam Map<String, String> params) {
        // Défini username dans la view :
        Integer userId = (Integer)session.getAttribute("user_id");
        User me = new User();
        if (userId != null) {
            me = UserDao.getUser(userId);
            if (me != null)
                model.addAttribute("username", me.getEmail());
        }
        if (userId == null)
            return "redirect:/login";

        Area area = AreaDao.getArea(id);
		if (area == null) {
			model.addAttribute("errorMessage", "Cet Applet n'existe pas.");
			List<Area> areas = AreaDao.getAreasByUser(userId);
            model.addAttribute("areas", areas);
			return "/panel";
		}
        if (area.getUserId() != userId) {
            model.addAttribute("errorMessage", "Cet Applet n'est pas à vous. Vous n'avez pas l'autorisation de le modifier.");
			List<Area> areas = AreaDao.getAreasByUser(userId);
            model.addAttribute("areas", areas);
            return "/panel";
        }

        if (areaPost.getActionName() == null || areaPost.getActionName().isEmpty() || areaPost.getReactionName() == null || areaPost.getReactionName().isEmpty()) {
            model.addAttribute("errorMessage", "Vous devez renseigner tous les champs.");
			List<Area> areas = AreaDao.getAreasByUser(userId);
            model.addAttribute("areas", areas);
            return "/panel";
        }

		areaPost.setTmpData("");

		int nbArgs = Integer.parseInt(params.get("nbActionArgs"));
		ArrayList<String> actionsArg = new ArrayList<>();
		for (int i = 0; i < nbArgs; i++)
			actionsArg.add(params.get("actionArg" + i));
		areaPost.setActionArgs(actionsArg.stream().toArray(String[]::new));

		nbArgs = Integer.parseInt(params.get("nbReactionArgs"));
		ArrayList<String> reactionsArg = new ArrayList<>();
		for (int i = 0; i < nbArgs; i++)
			reactionsArg.add(params.get("reactionArg" + i));
		areaPost.setReactionArgs(reactionsArg.stream().toArray(String[]::new));

		areaPost.setUserId(userId);
		AreaDao.save(areaPost);

		List<Area> areas = AreaDao.getAreasByUser(userId);
		model.addAttribute("areas", areas);

        return "/panel";
    }

    @GetMapping("/panel/delete-applet/{id}")
    public String deleteApplet(Locale locale, Model model, HttpSession session, @PathVariable("id") int id) {
        // Défini username dans la view :
        Integer userId = (Integer)session.getAttribute("user_id");
        User me = new User();
        if (userId != null) {
            me = UserDao.getUser(userId);
            if (me != null)
                model.addAttribute("username", me.getEmail());
        }
        if (userId == null)
            return "redirect:/login";

        Area area = AreaDao.getArea(id);
		if (area == null) {
			model.addAttribute("errorMessage", "Cet Applet n'existe pas.");
			List<Area> areas = AreaDao.getAreasByUser(userId);
            model.addAttribute("areas", areas);
			return "/panel";
		}
        if (area.getUserId() != userId) {
            model.addAttribute("errorMessage", "Cet Applet n'est pas à vous. Vous n'avez pas l'autorisation de le supprimer.");
			List<Area> areas = AreaDao.getAreasByUser(userId);
            model.addAttribute("areas", areas);
            return "/panel";
        }

        AreaDao.delete(id);

		List<Area> areas = AreaDao.getAreasByUser(userId);
        model.addAttribute("areas", areas);

        return "/panel";
    }

}
