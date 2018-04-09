package commands.moderation;

import commands.Command;
import commands.botowner.Owner;
import core.MessageHandler;
import core.MySQL;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Embed;

public class CommandLog implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) { return false;}

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getAuthor().getId() == event.getGuild().getOwner().getUser().getId() || event.getMember().hasPermission(Permission.MANAGE_SERVER) || Owner.get(event.getAuthor())) {

            try {
                if (args.length < 2) {
                    new MessageBuilder().setEmbed(Embed.error("To short", "The command was to short as example use: ``" + MessageHandler.getprefix(event.getGuild()) + "log leave disable``").build()).build();
                    } else if (args.length == 2) {
                    switch (args[0]) {
                        case "channel":
                            if (event.getMessage().getMentionedChannels().size() != 1)
                                new MessageBuilder().setEmbed(Embed.error("Only one", "Please mention only one channel").build()).build();
                            MySQL.update("log", "logchannel", event.getMessage().getMentionedChannels().get(0).getId(), "serverid", event.getGuild().getId());
                            new MessageBuilder().setEmbed(Embed.success("Succes", "Succesfully set log channel!").build()).build();
                        case "command":
                            if (args[0].equals("true") || args[0].equals("false")) {
                                MySQL.update("log", "command", args[0], "id", event.getGuild().getId());
                                new MessageBuilder().setEmbed(Embed.success("Succesfull", "Succesfully enabled/disabled the command log!").build()).build();
                            } else {
                                new MessageBuilder().setEmbed(Embed.error("Wrong arguments", "Please do ``" + MessageHandler.getprefix(event.getGuild()) + "log command true``").build()).build();
                            }
                        case "mod":
                            if (args[0].equals("true") || args[0].equals("false")) {
                                MySQL.update("log", "mod-log", args[0], "id", event.getGuild().getId());
                                new MessageBuilder().setEmbed(Embed.success("Succesfull", "Succesfully enabled/disabled the mod log!").build()).build();
                            } else {
                                new MessageBuilder().setEmbed(Embed.error("Wrong arguments", "Please do ``" + MessageHandler.getprefix(event.getGuild()) + "log command true``").build()).build();
                            }
                        case "voice":
                            if (args[0].equals("true") || args[0].equals("false")) {
                                MySQL.update("log", "voice", args[0], "id", event.getGuild().getId());
                                new MessageBuilder().setEmbed(Embed.success("Succesfull", "Succesfully enabled/disabled the voice log!").build()).build();
                            } else {
                                new MessageBuilder().setEmbed(Embed.error("Wrong arguments", "Please do ``" + MessageHandler.getprefix(event.getGuild()) + "log command true``").build()).build();
                            }
                    }
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            event.getTextChannel().sendMessage("No permissions").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}
