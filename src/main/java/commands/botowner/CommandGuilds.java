package commands.botowner;

import commands.Command;
import core.MessageHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Embed;

public class CommandGuilds implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Owner.get(event.getAuthor())) {

            int jaso = event.getJDA().getGuilds().size();
            jaso=jaso/20;
            jaso++;
            int server;
            int site;
            if (args.length>0) {
                 server = Integer.parseInt(args[0])*20;
                 site = Integer.parseInt(args[0]);
            } else {
                 server = 0;
                 site = 1;
            }
            int i = 0;
            String out ="";
            while (event.getJDA().getGuilds().size()>server&&i<20) {
                Guild guild = event.getJDA().getGuilds().get(server);
                out += String.valueOf(server+1) + ". " + guild.getName() + " | User: " + guild.getMembers().size() + " (" + guild.getId() + ")\n";
                i++;
                server++;
            }

            new MessageBuilder().setEmbed(Embed.normal("Guilds", "Site: " + site + "/" + jaso + "\nAll Guilds: " + event.getJDA().getGuilds().size() + "\n``" + out + "``").build()).build();


        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

}
