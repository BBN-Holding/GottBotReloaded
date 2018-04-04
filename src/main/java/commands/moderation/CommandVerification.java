package commands.moderation;

import commands.Command;
import core.MessageHandler;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

public class CommandVerification implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length<1) {
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("verificationhelp")).setDescription(MessageHandler.get(event.getAuthor()).getString("verificationhelp2").replace("gb", MessageHandler.getprefix(event.getGuild()))).build()).queue();
        } else {
            if (args[0].equalsIgnoreCase("setup")) {
                if (!(args.length >=4)) {
                    System.out.println(args.length);
                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("verificationsetup")).setDescription(MessageHandler.get(event.getAuthor()).getString("verificationsetup2").replace("gb.", MessageHandler.getprefix(event.getGuild()))).build()).queue();
                } else {
                    if (args[1].equalsIgnoreCase("text")) {
                        if (MySQL.get("server", "id", event.getGuild().getId(), "verification").equals("none")) {
                            if (event.getTextChannel().getMessageById(args[2]) != null) {
                                if (event.getGuild().getRoleById(args[3]) != null) {
                                    MySQL.update("server", "verificationart", "text", "id", event.getGuild().getId());
                                    MySQL.update("server", "verification", event.getGuild().getTextChannelById(args[2]).getId(), "id", event.getGuild().getId());
                                    MySQL.update("server", "verificationrole", event.getGuild().getRoleById(args[3]).getId(), "id", event.getGuild().getId());
                                    MySQL.update("server", "verificationmessage", args[4], "id", event.getGuild().getId());
                                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Sucess").setDescription("Set the verification: \nRole: " + event.getGuild().getRoleById(args[3]).getName() + "\nMessage: " + args[4]).setColor(Color.green).build()).queue();
                                }
                            }
                        } else
                            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("VerificationMessage already exist").setDescription("You can reset the Verification Message with ``gb.verfication reset``").build()).queue();
                    } else if (args[1].equalsIgnoreCase("reaction")) {
                        if (MySQL.get("server", "id", event.getGuild().getId(), "verification").equals("none")) {
                            if (event.getTextChannel().getMessageById(args[2]) != null) {
                                if (event.getGuild().getRoleById(args[3]) != null) {
                                    MySQL.update("server", "verificationart", "reaction", "id", event.getGuild().getId());
                                    MySQL.update("server", "verification", event.getTextChannel().getMessageById(args[2]).complete().getId(), "id", event.getGuild().getId());
                                    MySQL.update("server", "verificationrole", event.getGuild().getRoleById(args[3]).getId(), "id", event.getGuild().getId());
                                    event.getTextChannel().getMessageById(args[2]).complete().addReaction("✅").queue();
                                    event.getTextChannel().getMessageById(args[2]).complete().addReaction("❌").queue();
                                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Sucess").setDescription("Set the verification: \nRole: " + event.getGuild().getRoleById(args[3]).getName() + "\nMessage: " + event.getTextChannel().getMessageById(args[1]).complete().getContentRaw()).setColor(Color.green).build()).queue();
                                }
                            }
                        } else
                            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("VerificationMessage already exist").setDescription("You can reset the Verification Message with ``gb.verfication reset``").build()).queue();
                    }
                }
            } else if (args[0].equalsIgnoreCase("reset")) {
                MySQL.update("server", "verification", "none", "id", event.getGuild().getId());
                MySQL.update("server", "verificationrole", "none", "id", event.getGuild().getId());
                MySQL.update("server", "verificationart", "none", "id", event.getGuild().getId());
                event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Verification resetted").setDescription("Verfication Message sucessfully resetted").build()).queue();
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}
