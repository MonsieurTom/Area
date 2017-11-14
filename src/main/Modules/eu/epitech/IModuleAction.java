package eu.epitech;

import java.util.List;
import java.util.function.Consumer;

public interface IModuleAction extends IModule {
    public List<String> getActionsName();
    public boolean happened(String actionName, List<String> actionArgs, Consumer<String> consumer, String token);
}
