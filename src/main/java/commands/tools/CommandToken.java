package commands.tools;

import commands.Command;
import core.MessageHandler;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class CommandToken implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        String Token = event.getMessage().getContentStripped().replaceFirst(MessageHandler.getprefix(event.getGuild()), "").replaceFirst("token ", "");
        if (args.length  > 0) {
            try {
                new JDABuilder(AccountType.BOT)
                        .setToken(Token).addEventListener(new ListenerAdapter() {
                    @Override
                    public void onReady(ReadyEvent bot) {
                        super.onReady(bot);
                        String name;
                        String discrim;
                        String id;
                        name = bot.getJDA().getSelfUser().getName();
                        discrim = bot.getJDA().getSelfUser().getDiscriminator();
                        id = bot.getJDA().getSelfUser().getId();
                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Login succes :white_check_mark:").setDescription("Loged in as " + name + "#" + discrim + "\n with ID: " + id).build()).queue();
                        bot.getJDA().shutdown();

                    }
                }).buildBlocking();
            } catch (Exception e) {
                event.getTextChannel().sendMessage("Token invalid").queue();
            }
        } else {
            event.getTextChannel().sendMessage("Only do gb.token (TOKEN)").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}
