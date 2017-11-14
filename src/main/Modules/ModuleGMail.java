import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.javatuples.Pair;
import org.simplejavamail.converter.EmailConverter;
import org.simplejavamail.email.Email;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.config.ServerConfig;
import org.simplejavamail.mailer.config.TransportStrategy;
import javax.mail.*;

import eu.epitech.IModuleReaction;

public class ModuleGMail implements IModuleReaction {
    private List<String>                    _reactions;
    private List<Function<List<String>, Boolean>> _functions;

    public static void main(String args[])
    {}

    public ModuleGMail()
    {
        _reactions = new ArrayList<>();
        _reactions.add("Send me an email");

        _functions = new ArrayList<>();
        _functions.add(this::sendEmail);
    }

    @Override
    public List<String> getHandledReactions() {
        return _reactions;
    }

    @Override
    public boolean canHandle(String reaction)
    {
        if (_reactions.contains(reaction))
            return true;
        return false;
    }

    @Override
    public String connectOnce(List<String> args) {
        return "";
    }

    @Override
    public List<Pair<String, String>> getArguments(String str)
    {
        List<Pair<String, String>> args = new ArrayList<>();
        args.add(new Pair<>("Your Address mail", "ex: area@gmail.com"));
        args.add(new Pair<>("Subject of the mail", ""));
        args.add(new Pair<>("Content of the mail", ""));
        return args;
    }

    @Override
    public void run(String reaction, List<String> reactionArgs, String data, String token)
    {
        if (!_reactions.contains(reaction))
            return ;
        List<String> args = reactionArgs;
        args.add(data);
        _functions.get(_reactions.indexOf(reaction)).apply(args);
    }

    private boolean sendEmail(List<String> args)
    {
        try {
            Email email = new Email();
            email.setFromAddress("area51", "area51.java@gmail.com");
            email.addNamedToRecipients("area51.java@gmail.com", args.get(0));
            email.setSubject(args.get(1));
            email.setText(args.get(2) + "\n" + args.get(3));

            new Mailer("smtp.gmail.com", 587, "area51.java@gmail.com", "apdvesfjyeuuytoy", TransportStrategy.SMTP_TLS).sendMail(email);
            return (true);
        }
        catch (Exception e)
        {
            return (false);
        }
    }
}
