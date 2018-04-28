package GB.commands.tools;

import GB.commands.Command;
import GB.Handler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommnadAchievment implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length!=0&&event.getMessage().getContentRaw().contains("|")) {
            String msg = event.getMessage().getContentRaw().replaceFirst(new Handler().getMessageHandler().getprefix(event.getGuild()),"").replaceFirst("achievement ", "");
            String[] strings = msg.split("[ | ]");
            String url = "https://www.minecraftskinstealer.com/achievement/a.php?i=ITEM&h=TITLE&t=TEXT";
            url = url.replaceFirst("ITEM", strings[0]).replaceFirst("TITLE", strings[1].replaceAll(" ", "+"))
                    .replaceFirst("TEXT", strings[2].replaceAll(" ", "+"));
            event.getTextChannel().sendMessage(new EmbedBuilder().setImage(url).build()).queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
