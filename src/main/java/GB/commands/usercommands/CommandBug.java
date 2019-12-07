package GB.commands.usercommands;

import GB.Handler;
import GB.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandBug implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length<=2) {
            event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("usercommands.bug.help.title", "usercomamnds.bug.help.text","", "normal", event)).queue();
        } else {
            String text = event.getMessage().getContentStripped().replaceFirst(new Handler().getMessageHandler().getprefix(event.getGuild()), "").replaceFirst("bug ", "");
            event.getJDA().getGuildById("396732579920740352").getTextChannelById("417074854701826049").sendMessage(
                    new EmbedBuilder()
                            .setAuthor(event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator(), null, event.getAuthor().getAvatarUrl())
                            .setDescription("**New Bug Detected!**\n```fix\n" + text + "```")
                            .build()
            ).queue();
            event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("util.sucess", "usercommands.bug.sucess.text","", "normal", event)).queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}
