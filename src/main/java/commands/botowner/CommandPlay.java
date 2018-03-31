package commands.botowner;

import commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class CommandPlay implements Command {


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Owner.get(event.getAuthor())) {

            EmbedBuilder succes = new EmbedBuilder();
                event.getMessage().delete().queue();
                event.getJDA().getPresence().setGame(Game.playing(String.join(" ", args)));
                event.getJDA().getPresence().setStatus(OnlineStatus.ONLINE);
                succes.setTitle("Set Game Status to");
                succes.setColor(Color.GREEN);
                succes.setDescription(String.join("", args));
                event.getTextChannel().sendMessage(succes.build()).queue(msg -> {
                    msg.delete().queueAfter(20, TimeUnit.SECONDS);
                });
        }
    }


    @Override
    public void executed (boolean success, MessageReceivedEvent event) {

    }
}
