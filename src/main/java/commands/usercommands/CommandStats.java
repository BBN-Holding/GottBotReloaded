package commands.usercommands;

import commands.Command;
import core.MySQL;
import core.commandHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandStats implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        int i=0;
        long User=0;
        while (event.getJDA().getGuilds().size()>i) {
            User=User+event.getJDA().getGuilds().get(i).getMembers().size();
            i++;
        }
        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Stats")
                .addField("Commands used", MySQL.get1("stats",  "1", "command"),true)
                .addField("Messages Received", MySQL.get1("stats", "1", "message"),true)
                .addField("Servers", String.valueOf(event.getJDA().getGuilds().size()), true)
                .addField("User",String.valueOf(User), true)
                .addField("Listeners", String.valueOf(event.getJDA().getRegisteredListeners().size()), true)
                .addField("Commands", String.valueOf(commandHandler.commands.size()), true)
                .addField("registered User", String.valueOf(MySQL.getallwithoutwhere("user", "id").size()), true)
                .addField("registered Server", String.valueOf(MySQL.getallwithoutwhere("server", "id").size()), true)
                .setThumbnail(event.getJDA().getSelfUser().getEffectiveAvatarUrl())
                .build()
        ).queue();

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }


}
