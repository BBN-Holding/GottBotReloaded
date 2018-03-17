package listener;

import core.MySQL;
import net.dv8tion.jda.core.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Reaction extends ListenerAdapter {

    @Override
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
        // Verification
        if (!MySQL.get("server", "id", event.getGuild().getId(), "verification").equals("none")) {
            String Message = MySQL.get("server", "id", event.getGuild().getId(), "verification");
            if (event.getMessageId().equals(Message)) {
                System.out.println(event.getReaction().getReactionEmote().getName());
                if (event.getReaction().getReactionEmote().getName().equals("✅")) {
                    event.getGuild().getController().addSingleRoleToMember(event.getMember(), event.getGuild().getRoleById(MySQL.get("server", "id", event.getGuild().getId(), "verificationrole"))).queue();
                } else if (event.getReaction().getReactionEmote().getName().equals("❌")) {
                    if (event.getJDA().getRoles().get(0).canInteract(event.getMember().getRoles().get(0)))
                    event.getGuild().getController().kick(event.getMember()).queue();
                }
            }
        }
    }
}
