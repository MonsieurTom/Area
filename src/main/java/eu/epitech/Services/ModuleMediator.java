package eu.epitech.Services;

import eu.epitech.IModuleAction;
import eu.epitech.IModuleReaction;
import eu.epitech.Model.Entity.Area;
import eu.epitech.AreaDao;
import eu.epitech.UserDao;
import eu.epitech.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

@Service
@EnableScheduling
@EnableAsync
public class ModuleMediator {

	private ModuleLoader	_moduleLoader;
	@Autowired
	private AreaDao			_areaDao;
	@Autowired
	private UserDao			_userDao;
	@Autowired
	private AccountDao		_accountDao;

	ModuleMediator() {
		_moduleLoader = new ModuleLoader();

		this.loadModules("./Modules/");
	}

	private void	loadModules(String path)
	{
		this._moduleLoader.loadModules(path);
	}

	@Async
	@Scheduled(fixedDelay = 60000L)
	public void 	checkAction() {

		System.out.println("ping");
		for (Area area : _areaDao.list())
		{
			Consumer<String> setData = new Consumer<String>() {
				@Override
				public void accept(String s) {
					area.setTmpData(s);
				}
			};

			for (IModuleAction action : _moduleLoader.get_actionsModules())
			{
				//String	tokenA = _accountDao.getToken(area.getUserId(), action.getClass().getName());
				String tokenA = action.connectOnce(new ArrayList<>(Arrays.asList(area.getActionArgs())));
				if (action.happened(area.getActionName(), new ArrayList<>(Arrays.asList(area.getActionArgs())), setData, tokenA))
				{
					for (IModuleReaction reaction : _moduleLoader.get_reactionsModules()) {
						String	reactionName = area.getReactionName();
						if (reaction.canHandle(reactionName))
						{
							//String	tokenR = _accountDao.getToken(area.getUserId(), reaction.getClass().getName());
							String tokenR = reaction.connectOnce(new ArrayList<>(Arrays.asList(area.getReactionArgs())));
							reaction.run(reactionName, new ArrayList<>(Arrays.asList(area.getReactionArgs())), area.getTmpData(), tokenR);
							break;
						}
					}
					break;
				}
			}
		}
	}
}
