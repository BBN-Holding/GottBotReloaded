package gb.commands.usercommands;

import gb.GottBot;
import gb.Handler.CommandHandling.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandQuote implements Command {
    @Override
    public String[] Aliases() {
        return new String[]{"quote", "q"};
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length<1) {
            event.getTextChannel().sendMessage(GottBot.getMessage().getCommandTemplate(Aliases(), "gb.quote [MessageID]", "Quote a Message")).queue();
        } else {
            Message msg = event.getTextChannel().getMessageById(args[0]).complete();
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Quote from "+msg.getAuthor().getName()).setDescription("```"+msg.getContentRaw()+"```").build()).queue();
            event.getMessage().delete().queue();
        }
    }
}
