package commands.botowner;

import commands.Command;
import core.MessageHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Embed;

public class CommandStop implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Owner.get(event.getAuthor())) {
            event.getMessage().getTextChannel().sendMessage(new EmbedBuilder().setDescription(":white_check_mark:  Bot herruntergefahren  :white_check_mark: ").build()).queue();
            new MessageBuilder().setEmbed(Embed.success("", ":white_check_mark:  Bot herruntergefahren  :white_check_mark: ").build()).build();
            System.exit(0);
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}