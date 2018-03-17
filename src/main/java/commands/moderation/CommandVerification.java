package commands.moderation;

import commands.Command;
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
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Verification - Help").setDescription("Setting up a Verification").build()).queue();
        } else {
            if (args[0].equalsIgnoreCase("setup")) {
                if (args.length < 2)
                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Verification Setup - Help").setDescription("Write a Message (you can use the gb.say Command) and than do ``gb.verification setup [MessageID] [RoleID]`` (type this in the verification Channel) (if you don't know how you get the MessageID do gb.faq messageid) (if you don't know how you get the RoleID do gb.faq roleid)").build()).queue();
                else {
                    if (!MySQL.get("server", "id", event.getGuild().getId(), "verification").equals("none")) {
                        if (event.getTextChannel().getMessageById(args[1]) != null) {
                            if (event.getGuild().getRoleById(args[2]) != null) {
                                MySQL.update("server", "verification", event.getTextChannel().getMessageById(args[1]).complete().getId(), "id", event.getGuild().getId());
                                MySQL.update("server", "verificationrole", event.getGuild().getRoleById(args[2]).getId(), "id", event.getGuild().getId());
                                event.getTextChannel().getMessageById(args[1]).complete().addReaction("✅").queue();
                                event.getTextChannel().getMessageById(args[1]).complete().addReaction("❌").queue();
                                event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Sucess").setDescription("Set the verification: \nRole: " + event.getGuild().getRoleById(args[2]).getName() + "\nMessage: " + event.getTextChannel().getMessageById(args[1]).complete().getContentRaw()).setColor(Color.green).build()).queue();
                            }
                        }
                    } else event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("VerificationMessage already exist").setDescription("You can reset the Verification Message with ``gb.verfication reset``").build()).queue();
                }
            }

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
