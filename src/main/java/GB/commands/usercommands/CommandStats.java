package GB.commands.usercommands;

import GB.Handler;
import GB.core.Main;
import GB.core.commandHandler;
import GB.commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandStats implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        User user = event.getAuthor();
        Guild guild = event.getGuild();
        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Stats")
                .addField(new Handler().getMessageHandler().get("usercommands.stats.stats1",user,guild), new Handler().getMySQL().getfirst("stats",  "1", "command"),true)
                .addField(new Handler().getMessageHandler().get("usercommands.stats.stats2",user,guild), new Handler().getMySQL().getfirst("stats", "1", "message"),true)
                .addField(new Handler().getMessageHandler().get("usercommands.stats.stats3",user,guild), String.valueOf(Main.shardManager.getGuilds().size()), true)
                .addField(new Handler().getMessageHandler().get("usercommands.stats.stats4",user,guild), String.valueOf(Main.shardManager.getUsers().size()), true)
                .addField(new Handler().getMessageHandler().get("usercommands.stats.stats5",user,guild), String.valueOf(Main.jda.getRegisteredListeners().size()), true)
                .addField(new Handler().getMessageHandler().get("usercommands.stats.stats6",user,guild), String.valueOf(commandHandler.commands.size()), true)
                .addField(new Handler().getMessageHandler().get("usercommands.stats.stats7",user,guild), String.valueOf(new Handler().getMySQL().getallwithoutwhere("user", "id").size()), true)
                .addField(new Handler().getMessageHandler().get("usercommands.stats.stats8",user,guild), String.valueOf(new Handler().getMySQL().getallwithoutwhere("server", "id").size()), true)
                .setThumbnail(Main.jda.getSelfUser().getEffectiveAvatarUrl())
                .build()
        ).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}
