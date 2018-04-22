package commands.moderation;

import commands.Command;
import core.MessageHandler;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Date;


public class CommandWarn implements Command{


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }
    public class Counter {
        int number = 0;
        int id;
        public Counter() {
            number++;
            id = number;
        }
    }
    @Override
    public void action(String[] args, MessageReceivedEvent event) {

                JDA shard = event.getJDA();

                Member member = event.getMember();
                User user = event.getAuthor();
                Message message = event.getMessage();
                TextChannel channel = event.getTextChannel();
                Guild guild = event.getGuild();

                if (!member.hasPermission(Permission.KICK_MEMBERS)) {
                    event.getTextChannel().sendMessage("nichts perm").queue();
                }


                User warnUser = message.getMentionedUsers().get(0);
                Member warnMember = guild.getMember(warnUser);

                if (user.getId().equals(warnUser.getId())) {
                    event.getTextChannel().sendMessage("nichts selbst warnen digga").queue();
                }

                String reason = event.getMessage().getContentStripped().replaceFirst(MessageHandler.getprefix(event.getGuild()), "").replaceFirst("warn", "");
                reason = reason.equals("") ? "No reason specified" : reason;

                Date now = new Date();

                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("Warned in " + guild.getName());
                embed.setColor(new Color(0x4286F4));
                embed.setDescription("You were warned in " + guild.getName());
                embed.addField("Reason:", reason, false);
                embed.setFooter("Warned by " + user.getName() + "#" + user.getId(), null);
                embed.setTimestamp(now.toInstant());

                try {
                    PrivateChannel warned = warnUser.openPrivateChannel().complete();
                    warned.sendMessage(embed.build()).queue();
                } catch (Exception e) {
                    event.getTextChannel().sendMessage("der idiot hat die dms aus oder so").queue();
                }

        int NextIdent = 1000;

        int mId;

        {
            mId = NextIdent;
            ++NextIdent;
        }

        LocalDateTime currentTime = LocalDateTime.now();
        new Handler().getMySQL().insert("warns", "user_id", warnUser.getId());
        new Handler().getMySQL().update("warns", "guild_id", guild.getId(),"moderator_user_id", "000000000000000000");
        new Handler().getMySQL().update("warns", "reason", reason,"moderator_user_id", "000000000000000000");
        new Handler().getMySQL().update("warns", "warn_time", String.valueOf(currentTime.toLocalDate()),"moderator_user_id", "000000000000000000");
        new Handler().getMySQL().update("warns", "moderator_user_id", user.getId(),"moderator_user_id", "000000000000000000");


                event.getTextChannel().sendMessage("Warned " + warnUser.getName() + "#" + warnUser.getDiscriminator()).queue();

            }



    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
