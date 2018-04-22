package GB.commands.tools;

import GB.MessageHandler;
import commands.Command;
import GB.Handler;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandGitHub implements Command{

    Member user;
    String useruser;


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length!=1) {
            event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("tools.github.title", "tools.github.text", "", "normal", event)).queue();
        } else {
            if (args[0].equals("list")) {

                new Handler().getMySQL();
                String Liste = new Handler().getMySQL().getallstring("user", "github");
                String AlleMit = Liste.replaceAll("none", "");

                event.getTextChannel().sendMessage(AlleMit).queue();

            } else {
                new Handler().getMySQL().update("user", "github", args[0], "id", event.getAuthor().getId());
                event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("util.sucess", "tools.github.set", "", "sucess", event)).queue();
            }
        }

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}
