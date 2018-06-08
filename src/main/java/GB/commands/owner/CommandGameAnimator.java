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
        // TODO: Vllt noch ein add game oder so ka
        if (args.length<1) {
            event.getTextChannel().sendMessage(GottBot.getMessage().getCommandTemplate(Aliases(), "gb.gameanimator info | gb.gameanimator setgame [Game (z.b. ONLINE:WATCHING:to youuuu)] | gb.gameanimator toggle", "Control the GameAnimator")).queue();
        } else {
            if (!GottBot.getDev()) {
                switch (args[0]) {
                    case "info":
                        GottBot.sendToServer().println("GameAni - Info");
                        GottBot.sendToServer().flush();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("GameAni - Info")
                                .addField("On", resultsarrray.get(0),false)
                                .addField("Current Game", resultsarrray.get(1),false)
                                .addField("All Games", resultsarrray.get(2),false)
                                .build()
                        ).queue();
                        break;

                    case "toggle":
                        GottBot.sendToServer().println("GameAni - Toggle");
                        GottBot.sendToServer().flush();
                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Success").setDescription("Toggled GameAnimator").build()).queue();
                        break;
                    case "setgame":
                        GottBot.sendToServer().println("GameAni - SetGame - "+GottBot.getMessage().getWithoutprefixandcommand(event.getMessage().getContentRaw(), Aliases(), event.getGuild()).replace("setgame ",""));
                        GottBot.sendToServer().flush();
                        System.out.println("GameAni - SetGame - "+GottBot.getMessage().getWithoutprefixandcommand(event.getMessage().getContentRaw(), Aliases(), event.getGuild()).replace("setgame ",""));
                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Succes").setDescription("Successfully set game").build()).queue();
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
