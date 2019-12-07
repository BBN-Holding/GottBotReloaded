package GB.commands.botowner;

import GB.Handler;
import GB.core.Main;
import GB.commands.Command;
import net.dv8tion.jda.api.entities.Game;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandGame implements Command {


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Owner.get(event.getAuthor())) {

                Main.shardManager.setGame(Game.playing(String.join(" ", args)));
                event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("Succes", "Succesfully set the playing status", "", "succes", event)).queue();
        }
    }


    @Override
    public void executed (boolean success, MessageReceivedEvent event) {

    }
}
