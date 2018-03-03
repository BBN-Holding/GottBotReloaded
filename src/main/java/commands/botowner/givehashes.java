package commands.botowner;

import commands.Command;
import core.MessageHandler;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class givehashes implements Command {
    Member user;
    String useruser;
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Handler.get(event.getAuthor())) {
            try {
                useruser = args[1].replace("<", "").replace("@", "").replace(">", "").replace("!", "");
                user = event.getGuild().getMemberById(useruser);

            } catch (ArrayIndexOutOfBoundsException e) {
                user = event.getMember();
            } catch (Exception e) {
                e.printStackTrace();
            }
            MySQL.update("user", "miner", args[0], "id", user.getUser().getId());
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("givehashestitel").replaceAll("gb.", MessageHandler.getprefix(event.getGuild())))
                    .setDescription(MessageHandler.get(event.getAuthor()).getString("givehashestext")).build()).queue();
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
