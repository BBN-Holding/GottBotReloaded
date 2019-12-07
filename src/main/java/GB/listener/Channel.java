package GB.listener;

import GB.Handler;
import net.dv8tion.jda.client.events.relationship.FriendRequestReceivedEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.mozilla.javascript.EcmaError;

import java.util.concurrent.TimeUnit;

public class Channel extends ListenerAdapter {

    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
        if (!new Handler().getMySQL().get("server", "id", event.getGuild().getId(), "privatechannel").equals("none")) {
            if (event.getChannelJoined().getParent().getId().equals(new Handler().getMySQL().get("server", "id", event.getGuild().getId(), "privatechannel"))) {
                if (event.getChannelJoined().getName().equals("➕ Create Privatechannel")) {
                    Category category = event.getChannelJoined().getParent();
                    System.out.println("asdasdasd");
                    if (event.getGuild().getVoiceChannelsByName(event.getMember().getUser().getName()+"'s Channel", false).size()==0) {
                        String id = category.createVoiceChannel(event.getMember().getUser().getName() + "'s Channel").addPermissionOverride(event.getMember(), 3145728, 0).complete().getId();
                        event.getGuild().getController().moveVoiceMember(event.getMember(), event.getGuild().getVoiceChannelById(id)).queue();
                    } else {
                        event.getGuild().getController().moveVoiceMember(event.getMember(), event.getGuild().getVoiceChannelById(event.getGuild().getVoiceChannelsByName(event.getMember().getUser().getName()+"'s Channel", false).get(0).getId())).queue();
                    }

                } else if (event.getChannelJoined().getName().equals("\uD83D\uDE36 Wait for a Move in a Privatechannel")) {

                }
            }
        }
    }

    @Override
    public void onGuildVoiceMove(GuildVoiceMoveEvent event) {
        try{
            if (event.getChannelLeft().getParent().getId().equals(new Handler().getMySQL().get("server", "id", event.getGuild().getId(), "privatechannel"))) {
                if (!(event.getChannelLeft().getName().equals("\uD83D\uDE36 Wait for a Move in a Privatechannel")||event.getChannelLeft().getName().equals("➕ Create Privatechannel"))&&event.getChannelLeft().getMembers().size()==0) {
                    event.getChannelLeft().delete().queue();
                }
            }
        } catch (Exception ignored) {
        }
    }

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
        try {
            if (event.getChannelLeft().getParent().getId().equals(new Handler().getMySQL().get("server", "id", event.getGuild().getId(), "privatechannel"))) {
                if (!(event.getChannelLeft().getName().equals("\uD83D\uDE36 Wait for a Move in a Privatechannel")||event.getChannelLeft().getName().equals("➕ Create Privatechannel"))&&event.getChannelLeft().getMembers().size()==0) {
                    event.getChannelLeft().delete().queue();
                }
            }
        } catch (Exception ignored) {
        }
    }
}
