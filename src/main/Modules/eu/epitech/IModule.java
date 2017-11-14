package eu.epitech;

import org.javatuples.Pair;

import java.util.List;

public interface IModule {
    public String connectOnce(List<String> args);
    public List<Pair<String, String>> getArguments(String str);
}
