package GB.commands.usercommands;

import GB.core.MessageHandler;
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
            event.getTextChannel().sendMessage(MessageHandler.getEmbed("usercommands.say.title", "usercommands.say.text", "", "normal", event)).queue();
        } else if (event.getMessage().getMentionedChannels().size()==1){
            String Message = event.getMessage().getContentRaw().replaceFirst(MessageHandler.getprefix(event.getGuild()), "").replaceFirst("say", "")
                    .replaceFirst(event.getMessage().getMentionedChannels().get(0).getAsMention(),"").replaceFirst(args[1], "");
            event.getMessage().getMentionedChannels().get(0).sendMessage(new EmbedBuilder().setTitle(args[1]).setDescription(Message).build()).queue();
        }

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}
