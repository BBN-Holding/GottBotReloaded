package GB.listener;

import GB.Handler;
import GB.MessageHandler;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.List;

public class Reaction extends ListenerAdapter {

    @Override
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
        // Verification
        if (!event.getUser().isBot()) {
            if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_HISTORY)) {
                    if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_MANAGE)) {
                        if (event.getGuild().getSelfMember().hasPermission(Permission.MANAGE_ROLES)) {
                        if (!new Handler().getMySQL().get("server", "id", event.getGuild().getId(), "verification").equals("none")) {
                            String Message = new Handler().getMySQL().get("server", "id", event.getGuild().getId(), "verification");
                            if (event.getMessageId().equals(Message)) {
                                if (event.getReaction().getReactionEmote().getName().equals("✅")) {
                                    event.getGuild().getController().addSingleRoleToMember(event.getMember(), event.getGuild().getRoleById(new Handler().getMySQL().get("server", "id", event.getGuild().getId(), "verificationrole"))).queue();
                                } else if (event.getReaction().getReactionEmote().getName().equals("❌")) {
                                    if (event.getJDA().getRoles().get(0).canInteract(event.getMember().getRoles().get(0)))
                                        event.getGuild().getController().kick(event.getMember()).queue();
                                }
                            }
                        }
                        }
                    }
                }
            }
        }
        // Help Menu
        if (!event.getUser().isBot()) {
            if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_HISTORY)) {
                    if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_MANAGE)) {
                        if (event.getChannel().getMessageById(event.getMessageId()).complete().getEmbeds().size() == 1)
                            if (new Handler().getMySQL().get("helpmenu", "message", event.getMessageId(), "id").equals(event.getUser().getId())) {
                                if (event.getChannel().getMessageById(event.getMessageId()).complete().getEmbeds().get(0).getTitle().contains(new Handler().getMessageHandler().get("Helpmenu.helpmenu", event.getUser(),event.getGuild()))) {
                                    try {
                                        Message message = event.getChannel().getMessageById(event.getMessageId()).complete();
                                        message.clearReactions().queue();
                                        message.editMessage(new Handler().getMenuHandler().getMessage(event.getReactionEmote().getName(), event.getChannel().getMessageById(event.getMessageId()).complete().getEmbeds().get(0), event.getUser(), event.getGuild())).queue();
                                        List<String> list = new Handler().getMenuHandler().getemote(event.getReactionEmote().getName(), event.getChannel().getMessageById(event.getMessageId()).complete().getEmbeds().get(0), event.getUser(), event.getGuild());
                                        while (list.size() > 0) {
                                            message.addReaction(list.get(0)).queue();
                                            list.remove(0);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                    }
                }
            }
        }
    }
}
