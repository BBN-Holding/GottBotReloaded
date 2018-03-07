package commands;


import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import stuff.SECRETS;
import java.io.IOException;
import java.util.HashMap;
import java.util.Timer;



public class question implements Command{




    private static String Header = "<p><strong>Bugreport</strong><br><br><strong>Bug report by ";
    private static String Sufix = " </strong><br><br><strong>Description</strong><br><br></p>";


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }



    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        try {
            GitHub gitHub = GitHub.connectUsingOAuth(SECRETS.TOKEN);
            GHRepository repository = gitHub.getOrganization("BigBotNetwork").getRepository("GottBotReloaded");
            repository.createIssue("Frage").body(Header + "Der User ist " + event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator() + Sufix + event.getMessage().getContentDisplay()).label("Question").create();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void executed(boolean success, MessageReceivedEvent event) {

    }

    public String help() {
        return null;
    }

    private static class Titel {
        private final String title;
        private final User author;
        private final TextChannel channel;
        private final String message;


        private Titel(String title, User author, TextChannel channel, String message) {
            this.title = title;
            this.author = author;
            this.channel = channel;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public String getTitle() {
            return title;
        }

        public User getAuthor() {
            return author;
        }

        public TextChannel getChannel() {
            return channel;
        }
    }

}

