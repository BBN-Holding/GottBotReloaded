package commands.usercommands;

import commands.Command;
import core.MessageHandler;
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
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Say - Help").setDescription(MessageHandler.get(event.getAuthor()).getString("saytitel")).build()).queue();
        } else if (event.getMessage().getMentionedChannels().size()==1){
            String Message = event.getMessage().getContentRaw().replaceFirst(MessageHandler.getprefix(event.getGuild())+"say "+args[0]+" "+args[1], "");
            System.out.println(Message);
            event.getMessage().getMentionedChannels().get(0).sendMessage(new EmbedBuilder().setTitle(args[1]).setDescription(Message).build()).queue();
        }

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}
