package GB.commands.owner;

import GB.GottBot;
import GB.Handler.CommandHandling.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandGuilds implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        // TODO: Test it
        int page;
        if (args.length>0)
            page=Integer.parseInt(args[0]);
        else
            page=1;
        if (GottBot.getOneshardbot().getGuilds().size()>(page-1)*10) {
            String guilds = "";
            int i = (page-1)*10;
            while (GottBot.getOneshardbot().getGuilds().size() > i) {
                Guild guild= GottBot.getOneshardbot().getGuilds().get(i);
                guilds += "``" + guild.getName()+"`` ("+guild.getId()+")";
                i++;
            }
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Guilds")
                    .setDescription("Page: " + page + "\nGuilds: " + event.getJDA().getGuilds().size() + "\n" + guilds)
                    .build()).queue();
        } else event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Error").setDescription("I havent so much pages...").build()).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
