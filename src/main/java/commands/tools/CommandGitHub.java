package commands.tools;

import commands.Command;
import core.MessageHandler;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
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
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Github - Usage").setDescription("gb.github <list | <Name>>\nträgt dein Namen in dein Profil ein").build()).queue();
        } else {
            if (args[0].equals("list")) {

                String Liste = MySQL.getallstring("user", "github");
                String AlleMit = Liste.replaceAll("none", "");

                event.getTextChannel().sendMessage(AlleMit).queue();

            } else {
                MySQL.update("user", "github", args[0], "id", event.getAuthor().getId());
                event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("githubtitel").replaceAll("gb.", MessageHandler.getprefix(event.getGuild())))
                        .setDescription(MessageHandler.get(event.getAuthor()).getString("githubdescription")).build()).queue();
            }
        }

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}
