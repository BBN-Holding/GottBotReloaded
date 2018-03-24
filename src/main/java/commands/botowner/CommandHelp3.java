package commands.botowner;

import commands.Command;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import stuff.DATA;

public class CommandHelp3 implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        Message message = event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("HelpMenu - Overview").setDescription("Please react with the Emotes")
                .addField("Moderation", "\uD83D\uDD28", true)
                .addField("Tools", "\uD83D\uDEE0", true)
                .addField("Usercommands", "\uD83D\uDC65", true)
                .build()).complete();
        MySQL.insert("helpmenu", "id`,`message", event.getAuthor().getId()+"','"+message.getId());
        message.addReaction("\uD83D\uDD28").queue();
        message.addReaction("\uD83D\uDEE0").queue();
        message.addReaction("\uD83D\uDC65").queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
