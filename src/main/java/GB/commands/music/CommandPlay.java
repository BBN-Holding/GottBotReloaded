package GB.commands.music;

import GB.Handler;
import GB.commands.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.net.URL;

public class CommandPlay implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        String content = event.getMessage().getContentRaw().replaceFirst(new Handler().getMessageHandler().getprefix(event.getGuild()), "").replaceFirst("play", "");

        if(content.trim().isEmpty()) {
            event.getTextChannel().sendMessage("geb was ein :D").queue();
            return;
        }

        try {
            new URL(content);
        } catch(Exception e) {
            if(content.startsWith("soundcloud")) content = ("scsearch: " + content).replace("soundcloud ", "");
            else content = "ytsearch: " + content;
        }


    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
