package GB.commands.tools;

import GB.Handler;
import GB.MessageHandler;
import commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.GuildController;

public class CommandPrivatechannel implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length!=1) {
            event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("util.usage", "tools.privatechannel.description", "", "normal", event)).queue();
        } else {
            if (new Handler().getMySQL().get("server", "id", event.getGuild().getId(), "privatechannel").equals("none")) {
                GuildController guildController = event.getGuild().getController();
                String id = guildController.createCategory("PrivateChannel").complete().getId();
                new Handler().getMySQL().update("server", "privatechannel", id, "id", event.getGuild().getId());
                guildController.createVoiceChannel("➕ Create Privatechannel").setParent(event.getGuild().getCategoryById(id)).queue();
                guildController.createVoiceChannel("\uD83D\uDE36 Wait for a Move in a Privatechannel").setParent(event.getGuild().getCategoryById(id)).queue();
                event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("util.sucess", "tools.privatechannel.sucess", "", "sucess", event)).queue();
            } else event.getTextChannel().sendMessage("FEHLER EY").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
