package commands.usercommands;

import commands.Command;
import core.MessageHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

public class CommandBug implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length<=2) {
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("bugtitel"))
                    .setDescription(MessageHandler.get(event.getAuthor()).getString("bugtext").replace("gb.", MessageHandler.getprefix(event.getGuild()))).setColor(Color.RED).build()).queue();
        } else {

            String text = event.getMessage().getContentStripped().replaceFirst(MessageHandler.getprefix(event.getGuild()), "").replaceFirst("bug ", "");
            event.getJDA().getGuildById("396732579920740352").getTextChannelById("417074854701826049").sendMessage(
                    new EmbedBuilder()
                            .setAuthor(event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator(), null, event.getAuthor().getAvatarUrl())
                            .setDescription("**New Bug Detected!**\n```fix\n" + text + "```")
                            .build()
            ).queue();
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("bugsucesstitel"))
                    .setDescription(MessageHandler.get(event.getAuthor()).getString("bugsucesstext")).setColor(Color.green).build()).queue();

        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
