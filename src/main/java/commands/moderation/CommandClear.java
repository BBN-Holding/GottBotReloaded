package commands.moderation;

import commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageHistory;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.List;

public class CommandClear implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
            if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_MANAGE)) {
                if (args.length != 1)
                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Clear - Help").setDescription("gb.clear [Messages]").build()).queue();
                else {

                        Thread t = new Thread(() -> {
                            try {
                                int l = Integer.parseInt(args[0]);
                                event.getMessage().delete().queue();
                                if (l > 1) {
                                    while (l > 100) {
                                        MessageHistory history = new MessageHistory(event.getTextChannel());
                                        List<Message> msgs;
                                        msgs = history.retrievePast(100).complete();
                                        event.getTextChannel().deleteMessages(msgs).queue();
                                        l = l - 100;
                                    }
                                    if (l < 100 && l > 1) {
                                        MessageHistory history = new MessageHistory(event.getTextChannel());
                                        List<Message> msgs;
                                        msgs = history.retrievePast(l).complete();
                                        event.getTextChannel().deleteMessages(msgs).queue();
                                    }
                                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Deleted Messages").setDescription("Sucessfully deleted " + args[0] + " messages").build()).queue();

                                }
                            } catch (IllegalArgumentException e) {
                                event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("The Messages are too old sorry :(").setColor(Color.RED).build()).queue();
                            }
                        });
                        t.setName("messageclear");
                        t.start();

                }
            } else event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Missing Permissions").setDescription("I need the Manage Message Permission").build()).queue();
        } else event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Missing Permissions").setDescription("You need the Manage Message Permission").build()).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
