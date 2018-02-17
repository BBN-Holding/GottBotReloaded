package commands;


import core.MessageHandler;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;

public class cmd_bug implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }


    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length < 3) {
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Usage").setDescription("bug [message] (min. 3 args)").setColor(Color.RED).build()).queue();
        } else {
            System.out.println("asd");
            String text = event.getMessage().getContentRaw();
            event.getJDA().getGuildById("396732579920740352").getTextChannelById("411641204002783232").sendMessage(
                    new EmbedBuilder()
                            .setAuthor(event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator(), null, event.getAuthor().getAvatarUrl())
                            .setDescription("**New Bug Detected!**\n```fix\n" + text + "```")
                            .build()
            ).queue();
            System.out.println("lol");
            //User Feedback
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.Titel).setDescription(MessageHandler.Message).setColor(Color.green).build()).queue();

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
