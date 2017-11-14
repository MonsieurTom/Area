import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.time.*;

import eu.epitech.IModuleAction;

public class ModuleTime implements IModuleAction {
    private List<String>                            _actions;
    private List<Function<List<String>, Boolean>>   _functions;
    private String                                  _tmpData;

    public static void main(String args[])
    {}

    @Override
    public List<Pair<String, String>> getArguments(String str)
    {
        List<Pair<String, String>> args = new ArrayList<>();
        if (str.equals("Every day at"))
            args.add(new Pair<>("Hour", "ex:12"));
        args.add(new Pair<>("Minute", "ex:15"));
        return args;
    }

    @Override
    public String connectOnce(List<String> args) {
        return "";
    }

    private boolean dayAt(List<String> time)
    {
        LocalTime currentTime = LocalTime.now();
        LocalTime compareTime = LocalTime.parse(time.get(0)+":"+time.get(1)+":00");
        _tmpData = currentTime.toString();
        if (currentTime.getHour() == compareTime.getHour() && currentTime.getMinute() == compareTime.getMinute())
            return (true);
        return (false);
    }

    private boolean hourAt(List<String> time)
    {
        LocalTime currentTime = LocalTime.now();
        LocalTime compareTime = LocalTime.parse("10:"+time.get(0)+":00");
        _tmpData = currentTime.toString();
        if (currentTime.getMinute() == compareTime.getMinute())
            return (true);
        return (false);
    }

    public ModuleTime()
    {
        _actions = new ArrayList<>();
        _actions.add("Every day at");
        _actions.add("Every hour at");

        _functions = new ArrayList<>();
        _functions.add(this::dayAt);
        _functions.add(this::hourAt);
    }

    @Override
    public List<String> getActionsName() {
        return _actions;
    }

    @Override
    public boolean happened(String actionName, List<String> actionArgs, Consumer<String> consumer, String token)
    {
        if (!_actions.contains(actionName))
            return false;
        boolean ret = _functions.get(_actions.indexOf(actionName)).apply(actionArgs);
        consumer.accept(_tmpData);
        return (ret);
    }
}
