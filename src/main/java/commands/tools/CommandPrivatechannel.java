package commands.tools;

import commands.Command;
import core.MessageHandler;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.GuildController;

import java.util.List;

public class CommandPrivatechannel implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length!=1) {
            event.getTextChannel().sendMessage(MessageHandler.getEmbed("util.usage", "tools.privatechannel.description", "", "normal", event)).queue();
        } else {
            if (MySQL.get("server", "id", event.getGuild().getId(), "privatechannel").equals("none")) {
                GuildController guildController = event.getGuild().getController();
                String id = guildController.createCategory("PrivateChannel").complete().getId();
                MySQL.update("server", "privatechannel", id, "id", event.getGuild().getId());
                guildController.createVoiceChannel("➕ Create Privatechannel").setParent(event.getGuild().getCategoryById(id)).queue();
                guildController.createVoiceChannel("\uD83D\uDE36 Wait for a Move in a Privatechannel").setParent(event.getGuild().getCategoryById(id)).queue();
                event.getTextChannel().sendMessage(MessageHandler.getEmbed("util.sucess", "tools.privatechannel.sucess", "", "sucess", event)).queue();
            } else event.getTextChannel().sendMessage("FEHLER EY").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
