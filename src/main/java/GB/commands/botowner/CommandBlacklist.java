package GB.commands.botowner;

import GB.Handler;
import GB.core.Main;
import GB.commands.Command;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.List;

public class CommandBlacklist implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Owner.get(event.getAuthor())) {
            if (args.length < 1) {
                event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("botowner.blacklist.title2", "botowner.blacklist.text2", "", "error", event)).queue();
            } else {
                switch (args[0].toLowerCase()) {
                    case "add":
                        User user = event.getMessage().getMentionedUsers().get(0);
                        new Handler().getMySQL().insert("blacklist", "id", user.getId());
                        event.getTextChannel().sendMessage(":white_check_mark:").queue();
                        break;

                    case "remove":
                        User usa = event.getMessage().getMentionedUsers().get(0);
                        new Handler().getMySQL().delete("blacklist", "id", usa.getId());
                        event.getTextChannel().sendMessage(":white_check_mark:").queue();
                        break;
                    case "list":
                        String out="";
                        int i=0;
                        List<String> list = new Handler().getMySQL().getallwithoutwhere("blacklist",  "id");
                        while (list.size()>i) {
                            out += Main.shardManager.getUserById(list.get(i)).getName()+", ";
                            i++;
                        }
                        event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("botowner.blacklist.title", "botowner.blacklist.text", out, "normal", event)).queue();
                        break;
                }
            }
            if (event.getGuild().getMemberById(event.getJDA().getSelfUser().getId()).hasPermission(Permission.MESSAGE_MANAGE)) {
                event.getMessage().delete().queue();
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

}
