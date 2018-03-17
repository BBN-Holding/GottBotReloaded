package listener;

import core.MySQL;
import net.dv8tion.jda.core.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Reaction extends ListenerAdapter {

    @Override
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
        // Verification
        if (MySQL.get("server", "id", event.getGuild().getId(), "verification").equals("none")) {
            String Message = MySQL.get("server", "id", event.getGuild().getId(), "verification");
            if (event.getMessageId().equals(Message)) {
                event.getGuild().getController().addSingleRoleToMember(event.getMember(), event.getGuild().getRoleById(MySQL.get("server", "id", event.getGuild().getId(), "verificationrole"))).queue();
            }
        }
    }
}
