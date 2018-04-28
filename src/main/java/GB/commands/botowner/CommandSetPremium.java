package GB.commands.botowner;

import GB.Handler;
import GB.commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Date;

public class CommandSetPremium implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Owner.get(event.getAuthor())) {
            if (args.length<1) {
                event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Help - SetPremium").setDescription("gb.setpremium @user reset - reseted premium\ngb.setpremium @User set <Java new Date().getTime>\ngb.setpremium @User check").build()).queue();

            } else if (event.getMessage().getMentionedMembers().size()==1&&args.length>=2) {
                switch (args[1]) {
                    case "reset":
                        new Handler().getMySQL().update("user", "premium", "none", "id", event.getMessage().getMentionedUsers().get(0).getId());
                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Sucessfully reset Premium for " + event.getMessage().getMentionedUsers().get(0).getName()).build()).queue();
                        event.getMessage().getMentionedUsers().get(0).openPrivateChannel().complete().sendMessage(new EmbedBuilder().setTitle("Premium expired").setDescription("Buy new Premium with gb.premium buy").build()).queue();

                        break;
                    case "set":
                        new Handler().getMySQL().update("user", "premium", args[2], "id", event.getMessage().getMentionedUsers().get(0).getId());
                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Sucessfully set Premium for " + event.getMessage().getMentionedUsers().get(0).getName()).build()).queue();
                        break;
                    case "check":
                        String status;
                        if (new Handler().getMySQL().get("user", "id", event.getAuthor().getId(), "premium").equals("none"))
                            status = "none";
                        else {
                            Date date = new Date();
                            date.setTime(Long.parseLong(new Handler().getMySQL().get("user", "id", event.getAuthor().getId(), "premium")));
                            status = "until " + date.toGMTString();
                        }
                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Premium").setDescription(event.getMessage().getMentionedUsers().get(0).getName() + "'s Premium status is: ``" + status + "``").build()).queue();
                        break;
                }

            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
