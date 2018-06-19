package gb.commands.usercommands;

import gb.GottBot;
import gb.Handler.CommandHandling.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.time.format.DateTimeFormatter;

public class CommandProfile implements Command {


    @Override
    public String[] Aliases() {
        return new String[]{"profile", "info"};
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        // TODO: Translate
        Member member;
        if (event.getMessage().getMentionedMembers().size()==1) {
            member = event.getMessage().getMentionedMembers().get(0);
        } else {
            member = event.getMember();
        }
        String roles="";
        if (member.getRoles().size()==0) roles="none";
        else {
            for (int i = 0; (member.getRoles().size() - 1) > i; i++) {
                roles += member.getRoles().get(i).getName() + ", ";
            }
            roles += member.getRoles().get(member.getRoles().size() - 1).getName();
        }
        String nickname;
        if (member.getNickname()==null)
            nickname="none";
        else nickname=member.getNickname();
        String game;
        if (member.getGame()==null)
            game="none";
        else game=member.getGame().getName();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss dd.MM.yyyy");
        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(GottBot.getMessage().getString("commands.user.profile.title", event).replace("%member%", member.getUser().getName()+"#"+member.getUser().getDiscriminator()))
                .addField("Name", member.getUser().getName(), true)
                .addField("Nickname", nickname , true)
                .addField("Mention", member.getAsMention(), true)
                .addField("ID", member.getUser().getId(), true)
                .addField("Game", game, true)
                .addField("Joined DiscordServer", member.getJoinDate().format(dateTimeFormatter), true)
                .addField("Joined Discord", member.getUser().getCreationTime().format(dateTimeFormatter), true)
                .addField("Onlinestatus", member.getOnlineStatus().getKey(), true)
                .addField("Roles", roles, true)
                .setColor(Color.CYAN)
                .setThumbnail(member.getUser().getAvatarUrl())
                .build()
        ).queue();
    }
}
