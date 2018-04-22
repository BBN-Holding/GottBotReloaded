package commands.moderation;

import GB.Handler;
import GB.core.MessageHandler;
import commands.Command;
import commands.botowner.Owner;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandVerification implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getAuthor().getId()==event.getGuild().getOwner().getUser().getId() || event.getMember().hasPermission(Permission.MANAGE_SERVER) || Owner.get(event.getAuthor())) {

            if (args.length < 1) {
                event.getTextChannel().sendMessage(MessageHandler.getEmbed("moderation.verification.title", "moderation.verification.text", "", "normal", event)).queue();
            } else {
                if (args[0].equalsIgnoreCase("setup")) {
                    if (args.length < 2) {
                        event.getTextChannel().sendMessage(MessageHandler.getEmbed("moderation.verification.setup", "moderation.verification.setup2", "", "normal", event)).queue();
                    } else {
                        if (new Handler().getMySQL().get("server", "id", event.getGuild().getId(), "verification").equals("none")) {
                            if (event.getTextChannel().getMessageById(args[1]) != null) {
                                if (event.getGuild().getRoleById(args[2]) != null) {
                                    new Handler().getMySQL().update("server", "verification", event.getTextChannel().getMessageById(args[1]).complete().getId(), "id", event.getGuild().getId());
                                    new Handler().getMySQL().update("server", "verificationrole", event.getGuild().getRoleById(args[2]).getId(), "id", event.getGuild().getId());
                                    event.getTextChannel().getMessageById(args[1]).complete().addReaction("✅").queue();
                                    event.getTextChannel().getMessageById(args[1]).complete().addReaction("❌").queue();
                                    String zusatz = event.getGuild().getRoleById(args[2]).getName()+" - "+event.getTextChannel().getMessageById(args[1]).complete().getContentRaw();
                                    event.getTextChannel().sendMessage(MessageHandler.getEmbed("util.sucess", "moderation.verification.set", zusatz, "sucess", event)).queue();
                                }
                            }
                        } else
                            event.getTextChannel().sendMessage(MessageHandler.getEmbed("util.error", "moderation.verification.alreadyexist", "", "error", event)).queue();
                    }
                } else if (args[0].equalsIgnoreCase("reset")) {
                    new Handler().getMySQL().update("server", "verification", "none", "id", event.getGuild().getId());
                    new Handler().getMySQL().update("server", "verificationrole", "none", "id", event.getGuild().getId());
                    event.getTextChannel().sendMessage(MessageHandler.getEmbed("util.sucess", "moderation.verification.reset", "", "normal", event)).queue();
                }
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}
