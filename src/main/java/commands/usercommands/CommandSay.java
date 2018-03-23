package commands.usercommands;

import commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandSay implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length<3) {
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Say - Help").setDescription("You can use the say Command with ``gb.say [Textchannel] [Title] [Your Message]``").build()).queue();
        } else {
            int i = 2;
            String Message = "";
            while (args.length > i) {
                Message += args[i]+" ";
                i++;
            }
            event.getGuild().getTextChannelById(args[0].replaceFirst("<", "").replaceFirst("#", "").replaceFirst(">", "")).sendMessage(new EmbedBuilder().setTitle(args[1]).setDescription(Message).build()).queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

}
