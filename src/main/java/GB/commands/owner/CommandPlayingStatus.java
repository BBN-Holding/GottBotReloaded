package GB.commands.owner;

import GB.GottBot;
import GB.Handler.CommandHandling.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandPlayingStatus implements Command {
    @Override
    public String[] Aliases() {
        return new String[]{"playing", "playingstatus", "ps"};
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length<1) {
            event.getTextChannel().sendMessage(GottBot.getMessage().getCommandTemplate(Aliases(), "gb.playing [Playing Status]", "Set the playing status")).queue();
        } else {
            String game="";
            for (String arg:args) {
                game+=arg+" ";
            }
            event.getJDA().getPresence().setGame(Game.playing(game));
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Sucess").setDescription("Sucessfully set Status").build()).queue();
        }
    }
}
