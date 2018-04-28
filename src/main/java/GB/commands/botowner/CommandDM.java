package GB.commands.botowner;

import GB.Handler;
import GB.core.Main;
import GB.commands.Command;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandDM implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Owner.get(event.getAuthor())) {
            if (event.getMessage().getMentionedUsers().size() == 0) {
                Member User = event.getMessage().getGuild().getMember(event.getMessage().getMentionedUsers().get(0));
                String Content = event.getMessage().getContentStripped().replaceFirst(new Handler().getMessageHandler().getprefix(event.getGuild()), "").replaceFirst("dm", "").replaceFirst(String.valueOf(User), "");

                PrivateChannel channel = User.getUser().openPrivateChannel().complete();
                channel.sendMessage(Content).queue();
                event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("botowner.dm.text", "botowner.dm.title", "", "normal", event)).queue();
            } else {
                User User = Main.jda.getUserById(args[0]);
                String Content = event.getMessage().getContentStripped().replaceFirst(new Handler().getMessageHandler().getprefix(event.getGuild()), "").replaceFirst("dm", "").replaceFirst(String.valueOf(User), "");

                PrivateChannel channel = User.openPrivateChannel().complete();
                channel.sendMessage(Content).queue();
                event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("botowner.dm.text", "botowner.dm.title", "", "normal", event)).queue();

            }


        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
