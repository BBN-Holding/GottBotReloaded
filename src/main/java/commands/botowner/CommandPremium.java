package commands.botowner;

import commands.Command;
import core.MessageHandler;
import core.MySQL;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandPremium implements Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Owner.get(event.getAuthor())) {
            String check;

            try {
                if (args.length < 1) {
                    event.getTextChannel().sendMessage("ZU KURZ EY").queue();
                } else switch (args[0].toLowerCase()) {
                    case "add":
                        MySQL.insert("premium", "id", event.getMessage().getContentStripped().replaceFirst(MessageHandler.getprefix(event.getGuild()), "").replaceFirst("premium add", "")).replace("<", "").replace("@", "").replace(">", "").replace("!", "");
                        break;
                    case "remove":
                        MySQL.delete("premium", "id", event.getMessage().getContentStripped().replaceFirst(MessageHandler.getprefix(event.getGuild()), "").replaceFirst("premium add", "").replace("<", "").replace("@", "").replace(">", "").replace("!", ""));
                        break;
                    case "check":
                        try {
                            check = MySQL.get("premium", "id", event.getMessage().getContentStripped().replaceFirst(MessageHandler.getprefix(event.getGuild()), "").replaceFirst("premium add", "").replace("<", "").replace("@", "").replace(">", "").replace("!", ""), "id");
                            event.getTextChannel().sendMessage("Der liebe <@" + check + "> ist ez Premium").queue();
                        } catch (NullPointerException e) {
                            event.getTextChannel().sendMessage(e + " \nist der error also hat der kein Premium").queue();
                        }
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
