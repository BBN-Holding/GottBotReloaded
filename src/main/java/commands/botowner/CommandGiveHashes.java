package commands.botowner;

import commands.Command;
import core.MessageHandler;
import core.MySQL;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Embed;

public class CommandGiveHashes implements Command {
    Member user;
    String useruser;
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Owner.get(event.getAuthor())) {
            try {
                useruser = args[1].replace("<", "").replace("@", "").replace(">", "").replace("!", "");
                user = event.getGuild().getMemberById(useruser);

            } catch (ArrayIndexOutOfBoundsException e) {
                user = event.getMember();
            } catch (Exception e) {
                e.printStackTrace();
            }
            MySQL.update("user", "miner", args[0], "id", user.getUser().getId());
            event.getTextChannel().sendMessage(Embed.normal(MessageHandler.get(event.getAuthor()).getString("givehashestitel"), MessageHandler.get(event.getAuthor()).getString("givehashestext")).build()).queue();

        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

}
