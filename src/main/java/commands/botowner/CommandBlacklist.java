package commands.botowner;

import commands.Command;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
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
        if (Handler.get(event.getAuthor())) {
            if (args.length < 1) {
                event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Blacklist - Help").setDescription("Do gb.blacklist add @User or gb.blacklist remove @User. DO IT JUST DO IT!").build()).queue();
            } else {
                switch (args[0].toLowerCase()) {
                    case "add":
                        User user = event.getMessage().getMentionedUsers().get(0);
                        MySQL.insert("blacklist", "id", user.getId());
                        break;

                    case "remove":
                        User usa = event.getMessage().getMentionedUsers().get(0);
                        MySQL.delete("blacklist", "id", usa.getId());
                        break;
                    case "list":
                        String out="";
                        int i=0;
                        List<String> list = MySQL.getallwithoutwhere("blacklist",  "id");
                        while (list.size()>i) {
                            out += event.getJDA().getUserById(list.get(i)).getName()+", ";
                            i++;
                        }
                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Blacklisted Users").setDescription(out).build()).queue();
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

    @Override
    public String help() {
        return null;
    }
}
