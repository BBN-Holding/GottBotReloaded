package commands.moderation;

import commands.Command;
import commands.botowner.Owner;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;


public class CommandRole implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getMember().hasPermission(Permission.MANAGE_ROLES) || event.getMember().isOwner() || Owner.get(event.getAuthor()))
            try {

                Role Role = event.getMessage().getMentionedRoles().get(0);
                Member Member = event.getMessage().getMentionedMembers().get(0);

                if (args.length < 1) {
                    event.getTextChannel().sendMessage("ZU KURZ EY").queue();
                } else switch (args[0].toLowerCase()) {
                    case "add":

                        event.getGuild().getController().addSingleRoleToMember(Member, Role).queue();

                        event.getTextChannel().sendMessage(":white_check_mark: Succesfully added role to " + Member.getUser().getAsMention()).queue();

                        break;
                    case "remove":

                        event.getGuild().getController().removeSingleRoleFromMember(Member, Role).queue();

                        event.getTextChannel().sendMessage(":white_check_mark: Succesfully reomved role from " + Member.getUser().getAsMention()).queue();

                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
