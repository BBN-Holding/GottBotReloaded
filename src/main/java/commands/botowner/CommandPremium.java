package commands.botowner;

import commands.Command;
import core.MessageHandler;
import core.MySQL;
import core.UserSQL;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Embed;

public class CommandPremium implements Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Owner.get(event.getAuthor())) {
            String check;

            try {
                if (args.length < 1) {
                    event.getTextChannel().sendMessage("ZU KURZ EY").queue();
                } else switch (args[0].toLowerCase()) {
                    case "add":
                        MySQL.insert("premium", "id", event.getMessage().getContentStripped().replaceFirst(MessageHandler.getprefix(event.getGuild()), "").replaceFirst("premium add", "")).replace("<", "").replace("@", "").replace(">", "").replace("!", "");
                        new MessageBuilder().setEmbed(Embed.success("Premium added", "Succesfully added Premium!").build()).build();
                        break;
                    case "remove":
                        MySQL.delete("premium", "id", event.getMessage().getContentStripped().replaceFirst(MessageHandler.getprefix(event.getGuild()), "").replaceFirst("premium add", "").replace("<", "").replace("@", "").replace(">", "").replace("!", ""));
                        new MessageBuilder().setEmbed(Embed.success("Premium removed", "Succesfully removed Premium!").build()).build();
                        break;
                    case "check":
                        try {
                            if (MySQL.get("premium", "id", event.getMessage().getMentionedMembers().get(0).getUser().getId(), "id")==null) {
                                new MessageBuilder().setEmbed(Embed.success("Premium active",  event.getMessage().getMentionedMembers().get(0).getUser().getAsMention() + " has premium").build()).build();
                            }
                        } catch (NullPointerException e) {
                            new MessageBuilder().setEmbed(Embed.error("No premium", "This user has no premium").build()).build();
                        }

                        UserSQL.isPremium(event.getMessage().getMentionedUsers().get(0).getId());
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
