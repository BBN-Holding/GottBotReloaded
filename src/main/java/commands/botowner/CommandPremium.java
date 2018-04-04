package commands.botowner;

import commands.Command;
import core.MessageHandler;
import core.MySQL;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Embed;

public class CommandPremium implements Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Owner.get(event.getAuthor())) {
            try {
                if (args.length < 1) {
                    event.getTextChannel().sendMessage("ZU KURZ EY").queue();
                } else switch (args[0].toLowerCase()) {
                    case "add":
                        MySQL.insert("premium", "id", event.getMessage().getContentStripped().replaceFirst(MessageHandler.getprefix(event.getGuild()), "").replaceFirst("premium add", "")).replace("<", "").replace("@", "").replace(">", "").replace("!", "");
                        event.getTextChannel().sendMessage(Embed.success("Premium added", "Succesfully added Premium!").build()).queue();
                        break;
                    case "remove":
                        MySQL.delete("premium", "id", event.getMessage().getContentStripped().replaceFirst(MessageHandler.getprefix(event.getGuild()), "").replaceFirst("premium add", "").replace("<", "").replace("@", "").replace(">", "").replace("!", ""));
                        event.getTextChannel().sendMessage(Embed.success("Premium removed", "Succesfully removed Premium!").build()).queue();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
