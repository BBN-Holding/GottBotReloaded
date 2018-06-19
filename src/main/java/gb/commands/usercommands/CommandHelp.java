package gb.commands.usercommands;

import gb.GottBot;
import gb.Handler.CommandHandling.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

public class CommandHelp implements Command {
    @Override
    public String[] Aliases() {
        return new String[]{"help", "h"};
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        EmbedBuilder embedBuilder = new EmbedBuilder().setTitle("Help").setDescription("Here are all commands that i've learned").setColor(Color.GREEN);
        boolean owner=false;
        if (GottBot.getConfig().getOwnerID().contains(event.getAuthor().getId())) owner=true;
        for (String list:GottBot.getCommandlists()) {
            if (owner||!list.equals("owner")) {
                String helplist = "";
                for (Command cmd : GottBot.getCommands().get(list)) {
                    helplist += "``" + cmd.Aliases()[0] + "`` ";
                }
                embedBuilder.addField(list, helplist, false);
            }
        }

        event.getTextChannel().sendMessage(
            embedBuilder.build()
        ).queue();
    }
}
