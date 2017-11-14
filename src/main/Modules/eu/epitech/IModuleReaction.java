package eu.epitech;

import java.util.List;

public interface IModuleReaction extends IModule {
    public boolean canHandle(String reaction);
    public void run(String reaction, List<String> reactionArgs, String data, String token);
    public List<String> getHandledReactions();
}
