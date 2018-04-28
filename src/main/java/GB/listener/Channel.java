package GB.listener;

import GB.Handler;
import net.dv8tion.jda.client.events.relationship.FriendRequestReceivedEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.mozilla.javascript.EcmaError;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Channel extends ListenerAdapter {

    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
        if (!new Handler().getMySQL().get("server", "id", event.getGuild().getId(), "privatechannel").equals("none")) {
            createprivateTextchannel(event.getChannelJoined(), event.getGuild(), event.getMember());
        }
    }

    @Override
    public void onGuildVoiceMove(GuildVoiceMoveEvent event) {
        try{
            if (!new Handler().getMySQL().get("server", "id", event.getGuild().getId(), "privatechannel").equals("none")) {
                if (event.getChannelLeft().getParent().getId().equals(new Handler().getMySQL().get("server", "id", event.getGuild().getId(), "privatechannel"))) {
                    if (!(event.getChannelLeft().getName().equals("\uD83D\uDE36 Wait for a Move in a Privatechannel") || event.getChannelLeft().getName().equals("➕ Create Privatechannel")) && event.getChannelLeft().getMembers().size() == 0) {
                        event.getChannelLeft().delete().queue();
                        deletepermissionoverride(event.getGuild(), event.getMember());
                    }
                }
                if (event.getChannelJoined().getParent().getId().equals(new Handler().getMySQL().get("server", "id", event.getGuild().getId(), "privatechannel"))) {
                    createprivateTextchannel(event.getChannelJoined(), event.getGuild(), event.getMember());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
        if (event.getChannelLeft().getParent().getId().equals(new Handler().getMySQL().get("server", "id", event.getGuild().getId(), "privatechannel"))) {
            if (!(event.getChannelLeft().getName().equals("\uD83D\uDE36 Wait for a Move in a Privatechannel")||event.getChannelLeft().getName().equals("➕ Create Privatechannel"))&&event.getChannelLeft().getMembers().size()==0) {
                event.getChannelLeft().delete().queue();
                deletepermissionoverride(event.getGuild(), event.getMember());
            }
        }
    }

    private static void createprivateTextchannel(net.dv8tion.jda.core.entities.Channel channel, Guild guild, Member member) {
        if (channel.getParent().getId().equals(new Handler().getMySQL().get("server", "id", guild.getId(), "privatechannel"))) {
            if (channel.getName().equals("➕ Create Privatechannel")) {
                Category category = channel.getParent();
                System.out.println("asdasdasd");
                if (guild.getVoiceChannelsByName(member.getUser().getName()+"'s Channel", false).size()==0) {
                    String id = category.createVoiceChannel(member.getUser().getName() + "'s Channel").addPermissionOverride(member, 3145728, 0).complete().getId();
                    guild.getController().moveVoiceMember(member, guild.getVoiceChannelById(id)).queue();
                    guild.getVoiceChannelsByName("\uD83D\uDE36 Wait for a Move in a Privatechannel", true).get(0).createPermissionOverride(member).setDeny(Permission.VOICE_CONNECT).queue();
                    guild.getVoiceChannelsByName("➕ Create Privatechannel", true).get(0).createPermissionOverride(member).setDeny(Permission.VOICE_CONNECT).queue();
                    System.out.println(guild.getVoiceChannelsByName("\uD83D\uDE36 Wait for a Move in a Privatechannel", true).get(0).getPermissionOverrides().size());
                } else {
                    guild.getController().moveVoiceMember(member, guild.getVoiceChannelById(guild.getVoiceChannelsByName(member.getUser().getName()+"'s Channel", false).get(0).getId())).queue();
                }

            } else if (channel.getName().equals("\uD83D\uDE36 Wait for a Move in a Privatechannel")) {

            }
        }
    }

    private static void deletepermissionoverride(Guild guild, Member member) {
        guild.getVoiceChannelsByName("\uD83D\uDE36 Wait for a Move in a Privatechannel", false).get(0).getPermissionOverride(member).delete().queueAfter(2, TimeUnit.SECONDS);
        guild.getVoiceChannelsByName("➕ Create Privatechannel", false).get(0).getPermissionOverride(member).delete().queueAfter(2, TimeUnit.SECONDS);
    }
}
