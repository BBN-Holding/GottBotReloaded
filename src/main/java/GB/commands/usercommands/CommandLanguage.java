package GB.commands.usercommands;

import GB.Handler;
import GB.commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandLanguage implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length<1) {
            try {
                event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("usercommands.language.title", "usercommands.language.text","", "normal", event)).queue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (args[0].equalsIgnoreCase("list")) {
            try {
                String out=new Handler().getMySQL().getallstring("language", "name").replaceAll(" ", ", ");
                event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("usercommands.language.list.title", "usercommands.language.list.description", out,"normal", event)).queue();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (args[0].length()==2) {
            try {

                if (new Handler().getMySQL().getallstring("language", "name").contains(args[0].toLowerCase())) {
                    new Handler().getMySQL().update("user", "language", args[0].toLowerCase(), "ID", event.getAuthor().getId());
                    event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("usercommands.language.edit.title", "usercommands.language.edit.description","", "sucess", event)).queue();
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
