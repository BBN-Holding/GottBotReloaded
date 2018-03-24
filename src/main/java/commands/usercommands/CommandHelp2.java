package commands.usercommands;

import commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandHelp2 implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Help")
                .addField("Botowner", "``blacklist`` ``Clyde`` ``Eval`` ``GiveHashes`` ``Guild`` ``Guilds`` ``Invite`` ``Leave`` ``Registerserver`` ``registeruser`` ``setlevel`` ``setxp`` ``Stop`` ``Test``", false)
                .addField("Moderation", "``ban`` ``kick`` ``prefix`` ``verification``", false)
                .addField("Tools", "``github`` ``ping`` ``profile``", false)
                .addField("User", "``bug`` ``help`` ``language`` ``levelmessage`` ``question`` ``say`` ``stats``", false)
                .build()).queue();

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
