package GB.commands.owner;

import GB.GottBot;
import GB.Handler.CommandHandling.Command;
import GB.Handler.Server;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CommandGameAnimator implements Command, Server {
    static ArrayList<String> resultsarrray;
    @Override
    public String[] Aliases() {
        return new String[]{"gameanimator", "ga"};
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length<1) {
            event.getTextChannel().sendMessage(GottBot.getMessage().getCommandTemplate(Aliases(), "gb.gameanimator", "Control the GameAnimator")).queue();
        } else {
            if (!GottBot.getDev()) {
                switch (args[0]) {
                    case "info":

                        break;

                    case "toggle":

                        break;
                }
            } else event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Disable Dev mode plsss").setColor(Color.RED).build()).queue();
        }
    }

    @Override
    public void onMessage(String Message) {
        if (Message.startsWith("GameAni - Inforesult: ")) {
            String result = Message.replaceFirst("GameAni - Inforesult: ", "");
            String[] results = result.split("///");
            resultsarrray = new ArrayList<>(Arrays.asList(results));
        }
    }
}
