package GB.commands.moderation;

import GB.Handler;
import GB.commands.Command;
import GB.commands.botowner.Owner;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


public class CommandRole implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getMember().hasPermission(Permission.MANAGE_ROLES) || event.getMember().isOwner() || Owner.get(event.getAuthor()))
            try {


                if (args.length < 1&&event.getMessage().getMentionedRoles().size()!=1&&event.getMessage().getMentionedMembers().size()!=1) {
                    event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("moderation.role.title", "moderation.role.text", "", "normal", event)).queue();
                } else {
                    Role Role = event.getMessage().getMentionedRoles().get(0);
                    Member Member = event.getMessage().getMentionedMembers().get(0);
                    switch (args[0].toLowerCase()) {
                        case "add":

                            event.getGuild().getController().addSingleRoleToMember(Member, Role).queue();

                            event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("util.sucess", "moderation.role.added", Member.getUser().getAsMention(), "sucess", event)).queue();

                            break;
                        case "remove":

                            event.getGuild().getController().removeSingleRoleFromMember(Member, Role).queue();

                            event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("util.sucess", "moderation.role.removed", Member.getUser().getAsMention(), "sucess", event)).queue();

                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
