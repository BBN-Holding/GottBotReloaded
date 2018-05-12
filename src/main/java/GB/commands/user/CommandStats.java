package GB.commands.user;

import GB.GottBot;
import GB.Handler.CommandHandling.Command;
import GB.Handler.CommandHandling.commandHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandStats implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        // TODO: create in info Message and commands and link a Listener (DB)
            String commands = String.valueOf(commandHandler.commands.size());
            String listener = String.valueOf(event.getJDA().getRegisteredListeners().size());
            String user = String.valueOf(GottBot.getOneshardbot().getUsers().size());
            String server = String.valueOf(GottBot.getOneshardbot().getGuilds().size());
            String message = GottBot.getDB().getAll("Info","Message");
            String commandsused = GottBot.getDB().getAll("Info", "Commands");
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Statistics")
                    .addField("Commands", commands, true)
                    .addField("Listener", listener, true)
                    .addField("User", user, true)
                    .addField("Server", server, true)
                    .addField("Message Received", message, true)
                    .addField("Commands used", commandsused, true)
            .build()).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
