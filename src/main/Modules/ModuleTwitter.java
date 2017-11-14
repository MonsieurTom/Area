import org.javatuples.Pair;
import twitter4j.DirectMessage;
import twitter4j.Paging;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.auth.AccessToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import eu.epitech.IModuleReaction;
import eu.epitech.IModuleAction;

public class ModuleTwitter implements IModuleReaction, IModuleAction {
    private List<String>                            _reactions;
    private List<Function<List<String>, Boolean>>   _functions;
    private List<String>                            _actions;
    private List<Function<List<String>, Boolean>>   _functionsAction;
    private String                                  _tmpData;
    private Twitter                                 _twitter;

    public static void main(String args[])
    {
    }

    public ModuleTwitter()
    {
        _actions = new ArrayList<>();
        _actions.add("When receive message");

        _functionsAction = new ArrayList<>();
        _functionsAction.add(this::receiveMessage);

        _reactions = new ArrayList<>();
        _reactions.add("Update status");
        _reactions.add("Send message to");

        _functions = new ArrayList<>();
        _functions.add(this::updateStatus);
        _functions.add(this::sendMessage);
    }

    @Override
    public List<Pair<String, String>> getArguments(String str) {
        if (!_actions.contains(str) && !_reactions.contains(str))
            return null;
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            twitter.setOAuthConsumer("CyDp3RkY3JrJSr4tPRVtbVgay", "MEQUpJfeSOP4rdfZigKmM7ioPQX0f59hDbFkEiRHJVTvgESAq8");
            RequestToken requestToken = twitter.getOAuthRequestToken();
            List<Pair<String, String>> arguments = new ArrayList<>();
            arguments.add(new Pair<>("Pin : " + requestToken.getAuthorizationURL(), "Open the following URL and grant access to your account then enter the pin"));
            arguments.add(new Pair<>("invisible", requestToken.getToken()));
            arguments.add(new Pair<>("invisible", requestToken.getTokenSecret()));
            if (str.equals("Send message to"))
                arguments.add(new Pair<>("Recipient", "Recipient screen name"));
            if (_reactions.contains(str))
                arguments.add(new Pair<>("Constant text", "Whatever text you want to add"));
            return arguments;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public String connectOnce(List<String> args) {
        if (args == null || args.size() == 0)
            return null;
        AccessToken accessToken = null;
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            try {
                    twitter.setOAuthConsumer("CyDp3RkY3JrJSr4tPRVtbVgay", "MEQUpJfeSOP4rdfZigKmM7ioPQX0f59hDbFkEiRHJVTvgESAq8");
                    RequestToken requestToken = new RequestToken(args.get(1), args.get(2));
                    accessToken = twitter.getOAuthAccessToken(requestToken, args.get(0));
            }
            catch (IllegalStateException ie)
            {
                if (!twitter.getAuthorization().isEnabled()) {
                    return null;
                }
            }
        }
        catch (Exception e)
        {
            return null;
        }
        return accessToken.getToken() + ";" + accessToken.getTokenSecret();
    }

    public Twitter connect(String token)
    {
        try {
            _twitter = new TwitterFactory().getInstance();
            _twitter.setOAuthConsumer("CyDp3RkY3JrJSr4tPRVtbVgay", "MEQUpJfeSOP4rdfZigKmM7ioPQX0f59hDbFkEiRHJVTvgESAq8");
            AccessToken accessToken = new AccessToken(token.split(";")[0], token.split(";")[1]);
            _twitter.setOAuthAccessToken(accessToken);
            return _twitter;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public boolean receiveMessage(List<String> args)
    {
        Twitter twitter = new TwitterFactory().getInstance();
        List<DirectMessage>    messages;
        try
        {
            Paging paging = new Paging(1);
            messages = twitter.getDirectMessages(paging);
            if (messages.size() == 0)
                return (false);
            _tmpData = "From " + messages.get(0).getRecipientScreenName() + ": " + messages.get(0).getText();
            return (true);
        }
        catch (Exception e)
        {
            return (false);
        }
    }

    @Override
    public List<String> getActionsName()
    {
        return _actions;
    }

    @Override
    public boolean happened(String actionName, List<String> actionArgs, Consumer<String> consumer, String token) {
        if (!_actions.contains(actionName) || connect(token) == null)
            return false;
        boolean ret = _functionsAction.get(_actions.indexOf(actionName)).apply(actionArgs);
        consumer.accept(_tmpData);
        return (ret);
    }

    @Override
    public boolean canHandle(String reaction) {
        if (_reactions.contains(reaction))
                return true;
        return false;
    }

    @Override
    public List<String> getHandledReactions() {
        return _reactions;
    }

    @Override
    public void run(String reaction, List<String> reactionArgs, String data, String token) {
        if (!_reactions.contains(reaction))
            return ;
        if (connect(token) == null)
            return ;
        List<String> args = reactionArgs;
        args.add(data);
        _functions.get(_reactions.indexOf(reaction)).apply(args);
    }

    private boolean sendMessage(List<String> args)
    {
        try {
            _twitter.sendDirectMessage(args.get(3), args.get(4) + args.get(5));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    private boolean updateStatus(List<String> args)
    {
        try {
            _twitter.updateStatus(args.get(3) + " " + args.get(4));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
