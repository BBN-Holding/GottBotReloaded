package commands.usercommands;

import commands.Command;
import core.Main;
import core.MessageHandler;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import stuff.DATA;

public class CommandClan implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length==0) {
            event.getTextChannel().sendMessage(MessageHandler.getEmbed("util.usage", "usercommands.clan.description", "", "normal", event)).queue();
        } else {
            switch (args[0].toLowerCase()) {
                case "create":
                    if (args.length==2) {
                        if (MySQL.get("user", "id", event.getAuthor().getId(), "clan").equals("none")) {
                            if (Long.parseLong(MySQL.get("user", "id", event.getAuthor().getId(), "hashes"))>500000) {
                                if (!args[1].equalsIgnoreCase("Placeholder")) {
                                    // Create the clan and add the user
                                    String id = MySQL.get("clan", "Name", "Placeholder", "id");
                                    MySQL.insert("clan", "Name`,`id", args[1] + "','" + id);
                                    MySQL.update("clan", "id", String.valueOf(Long.parseLong(id)+1), "Name", "Placeholder");
                                    MySQL.update("user", "clan", id, "id", event.getAuthor().getId());
                                    Main.shardManager.getGuildById(DATA.BBNS).getController().createTextChannel(args[1]).setParent(Main.shardManager.getCategoryById("437255509951643649")).queue();
                                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Clan created").setDescription("You have now a own Clan (channel on the official BigBotNetwork server)!").build()).queue();
                                }
                            } event.getTextChannel().sendMessage(MessageHandler.getEmbed("util.error", "util.mine", "https://miner.bigbotnetwork.de", "error", event)).queue();
                        } event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("alredy in a Clan").build()).queue();
                    } else event.getTextChannel().sendMessage(MessageHandler.getEmbed("util.error", "util.error", "", "error", event)).queue();
                    break;
                case "invite":

                    break;
                case "accept":

                    break;
                case "kick":

                    break;
                case "delete":

                break;
                case "stats":

                    break;
                case "list":

                    break;
                case "bank":

                    break;
                case "best":

                    break;
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
