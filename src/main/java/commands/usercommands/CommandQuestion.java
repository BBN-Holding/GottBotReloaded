package commands.usercommands;


import commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import stuff.SECRETS;
import java.io.IOException;




public class CommandQuestion implements Command {




    private static String Header = "<p><strong>User have a question</strong><br><br><strong>Question by ";
    private static String Sufix = " </strong><br><br><strong>Description</strong><br><br></p>";


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }



    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        try {
            GitHub gitHub = GitHub.connectUsingOAuth(SECRETS.GITHUB_TOKEN);
            GHRepository repository = gitHub.getOrganization("BigBotNetwork").getRepository("GottBotReloaded");
            repository.createIssue("Frage").body(Header + "Question from " + event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator() + Sufix + event.getMessage().getContentDisplay()).label("Question").create();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }



}
