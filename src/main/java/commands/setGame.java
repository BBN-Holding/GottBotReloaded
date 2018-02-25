package commands;

import core.Main;
import commands.Command;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;


public class setGame implements Command {


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length >= 2) {
            StringBuilder builder = new StringBuilder();
            for (int x = 1; x < args.length; x++) {
                builder.append(args[x] + " ");
            }
            event.getJDA().getPresence().setGame(Game.playing(builder.toString()));
            event.getTextChannel().sendMessage(event.getAuthor().getAsMention() + " neuer game Status:  is " + builder.toString()).queue();
        } else {
            event.getTextChannel().sendMessage("Ja irgendwas falsch halt ey").queue();
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