package eu.epitech.Services;

import eu.epitech.IModule;
import eu.epitech.IModuleAction;
import eu.epitech.IModuleReaction;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;
import java.util.jar.Manifest;


public class ModuleLoader {
    private List<IModuleAction>     _actionsModules;
	private List<IModuleReaction>   _reactionsModules;
    private List<String>			_actionClassesNames;
	private List<String>			_reactionClassesNames;
	private static List<URL> 		_urls;

	static public ClassLoader		classLoader;

    public	ModuleLoader() {
        _actionsModules = new ArrayList<IModuleAction>();
        _reactionsModules = new ArrayList<IModuleReaction>();
        _actionClassesNames = new ArrayList<String>();
        _reactionClassesNames = new ArrayList<String>();
        _urls = new ArrayList<URL>();
	}

	private static List<String>	getModulesClasses(String path)
	{
		List<String> modulesNames = new ArrayList<String>();

		try {
			File[] files = new File(path).listFiles(new ModuleFilter());

			for (File file : files) {
				JarFile jarFile = null;
				try {
					jarFile = new JarFile(file);

					Manifest manifest = jarFile.getManifest();

					modulesNames.add(manifest.getMainAttributes().getValue("Main-Class"));
					_urls.add(file.toURI().toURL());
				}
				catch (Exception e)
				{
					System.err.println("Error while reading module " + file.getName());
					e.printStackTrace();
				}
				finally {
					if (jarFile != null)
					{
						try
						{ jarFile.close(); }
						catch (Exception e)
						{ e.printStackTrace(); }
					}
				}

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		AccessController.doPrivileged(new PrivilegedAction<Object>(){
			@Override
			public Object run() {
				classLoader = new URLClassLoader(
						_urls.toArray(new URL[_urls.size()]),
						ModuleLoader.class.getClassLoader());

				return null;
			}
		});

		return modulesNames;
	}

	private void	createModuleInstance(String path)
	{
		for(String moduleName : getModulesClasses(path)) {
			try {
				Class<?> moduleClass = Class.forName(moduleName, true, classLoader);

				System.out.println("LOADING " + moduleName);
				if(eu.epitech.IModule.class.isAssignableFrom(moduleClass)){
					Class<IModule> castedClass = (Class<IModule>) moduleClass;

					IModule module = castedClass.newInstance();
					System.out.println("Found module");
					if (module instanceof IModuleAction) {
						System.out.println("Found ACTION");
						_actionsModules.add((IModuleAction) module);
						_actionClassesNames.add(moduleName);
					}
					if (module instanceof IModuleReaction) {
						System.out.println("Found REACTION");
						_reactionsModules.add((IModuleReaction) module);
						_reactionClassesNames.add(moduleName);
					}
				}
			}
			catch (Exception e)
			{
				System.err.println("Can t create instance for moduleClass : " + moduleName);
				e.printStackTrace();
			}
		}
	}

    public void loadModules(String path) {
		//this.getModulesClasses(path);
		this.createModuleInstance(path);
    }

	public List<IModuleAction> get_actionsModules() {
		return _actionsModules;
	}

	public List<IModuleReaction> get_reactionsModules() {
		return _reactionsModules;
	}

	public List<String> get_actionClassesNames() {
		return _actionClassesNames;
	}

	public List<String> get_reactionClassesNames() {
		return _reactionClassesNames;
	}

	public static ClassLoader getClassLoader() {
		return classLoader;
	}

	private static class ModuleFilter implements FileFilter {
		@Override
		public boolean accept(File file) {
			return file.getName().endsWith(".jar");
		}
	}
}
