package commands.moderation;

import commands.Command;
import commands.botowner.Owner;
import core.MessageHandler;
import core.MySQL;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandLog implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) { return false;}

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getAuthor().getId() == event.getGuild().getOwner().getUser().getId() || event.getMember().hasPermission(Permission.MANAGE_SERVER) || Owner.get(event.getAuthor())) {

            try {
                if (args.length < 2) {
                    event.getTextChannel().sendMessage(MessageHandler.getEmbed("To short", "The command was to short as example use: ``%prefix%log leave disable``", "", "error", event)).complete();
                    } else if (args.length == 2) {
                    switch (args[0]) {
                        case "channel":
                            if (event.getMessage().getMentionedChannels().size() != 1)
                                event.getTextChannel().sendMessage(MessageHandler.getEmbed("Only one", "Please mention only one channel", "", "error", event)).complete();
                            MySQL.update("log", "logchannel", event.getMessage().getMentionedChannels().get(0).getId(), "serverid", event.getGuild().getId());
                            event.getTextChannel().sendMessage(MessageHandler.getEmbed("Succes", "Succesfully set log channel!", "", "succed", event)).complete();
                        case "command":
                            if (args[0].equals("true") || args[0].equals("false")) {
                                MySQL.update("log", "command", args[0], "id", event.getGuild().getId());
                                event.getTextChannel().sendMessage(MessageHandler.getEmbed("Succesfull", "Succesfully enabled/disabled the command log!", "", "succes", event)).complete();
                            } else {
                                event.getTextChannel().sendMessage(MessageHandler.getEmbed("Wrong arguments", "Please do ``%prefix%log command true``", "", "error", event)).complete();
                            }
                        case "mod":
                            if (args[0].equals("true") || args[0].equals("false")) {
                                MySQL.update("log", "mod-log", args[0], "id", event.getGuild().getId());
                                event.getTextChannel().sendMessage(MessageHandler.getEmbed("Succesfull", "Succesfully enabled/disabled the mod log!", "", "error", event)).complete();
                            } else {
                                event.getTextChannel().sendMessage(MessageHandler.getEmbed("Wrong arguments", "Please do ``%prefix%log command true``", "", "error", event)).complete();
                            }
                        case "voice":
                            if (args[0].equals("true") || args[0].equals("false")) {
                                MySQL.update("log", "voice", args[0], "id", event.getGuild().getId());
                                event.getTextChannel().sendMessage(MessageHandler.getEmbed("Succesfull", "Succesfully enabled/disabled the voice log!", "", "error", event)).complete();
                            } else {
                                event.getTextChannel().sendMessage(MessageHandler.getEmbed("Wrong arguments", "Please do ``%prefix%log command true``", "", "error", event)).complete();
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
