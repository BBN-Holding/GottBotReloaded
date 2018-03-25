package listener;

import core.MenuHandler;
import core.MySQL;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Reaction extends ListenerAdapter {

    @Override
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
        // Verification
        if (!MySQL.get("server", "id", event.getGuild().getId(), "verification").equals("none")) {
            String Message = MySQL.get("server", "id", event.getGuild().getId(), "verification");
            if (event.getMessageId().equals(Message)) {
                if (event.getReaction().getReactionEmote().getName().equals("✅")) {
                    event.getGuild().getController().addSingleRoleToMember(event.getMember(), event.getGuild().getRoleById(MySQL.get("server", "id", event.getGuild().getId(), "verificationrole"))).queue();
                } else if (event.getReaction().getReactionEmote().getName().equals("❌")) {
                    if (event.getJDA().getRoles().get(0).canInteract(event.getMember().getRoles().get(0)))
                        event.getGuild().getController().kick(event.getMember()).queue();
                }
            }
        }
        // Help Menu
        if (!event.getUser().isBot()) {
            if (event.getChannel().getMessageById(event.getMessageId()).complete().getEmbeds().size() == 1)
                if (event.getChannel().getMessageById(event.getMessageId()).complete().getEmbeds().get(0).getTitle().contains("HelpMenu")) {
                    try {
                        if (MySQL.get("helpmenu", "message", event.getMessageId(), "id").equals(event.getUser().getId())) {
                            Message message = event.getChannel().getMessageById(event.getMessageId()).complete();
                            message.clearReactions().queue();
                            message.editMessage(MenuHandler.getMessage(event.getReactionEmote().getName(),event.getChannel().getMessageById(event.getMessageId()).complete().getEmbeds().get(0) )).queue();
                            List<String> list = MenuHandler.getemote(event.getReactionEmote().getName(), event.getChannel().getMessageById(event.getMessageId()).complete().getEmbeds().get(0));
                            while (list.size() > 0) {
                                message.addReaction(list.get(0)).queue();
                                list.remove(0);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}