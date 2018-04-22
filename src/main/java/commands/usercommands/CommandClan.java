package commands.usercommands;

import GB.core.Handler;
import commands.Command;
import core.Main;
import core.MessageHandler;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import stuff.DATA;

import java.util.List;

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
                        if (new Handler().getMySQL().get("user", "id", event.getAuthor().getId(), "clan").equals("none")) {
                            if (Long.parseLong(new Handler().getMySQL().get("user", "id", event.getAuthor().getId(), "hashes"))>500000) {
                                if (new Handler().getMySQL().get("clan", "name", args[1], "name")==null) {
                                    // Create the clan and add the user
                                    new Handler().getMySQL().insert("clan", "Name", args[1]);
                                    new Handler().getMySQL().update("user", "clan", new Handler().getMySQL().get("clan", "name", args[1], "id"), "id", event.getAuthor().getId());
                                    Main.shardManager.getGuildById(DATA.BBNS).getController().createTextChannel(args[1]).setParent(Main.shardManager.getCategoryById("437255509951643649")).queue();
                                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Clan created").setDescription("You have now a own Clan (channel on the official BigBotNetwork server)!").build()).queue();
                                } else event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Error").setDescription("name is already in use").build()).queue();
                            } else event.getTextChannel().sendMessage(MessageHandler.getEmbed("util.error", "util.mine", "https://miner.bigbotnetwork.de", "error", event)).queue();
                        } else event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("alredy in a Clan").build()).queue();
                    } else event.getTextChannel().sendMessage(MessageHandler.getEmbed("util.error", "util.error", "", "error", event)).queue();
                    break;
                case "info":
                    if (!new Handler().getMySQL().get("user", "id", event.getAuthor().getId(), "clan").equals("none")) {
                        String Bank = new Handler().getMySQL().get("clan", "id", new Handler().getMySQL().get("user", "id", event.getAuthor().getId(), "clan"), "bank");
                        String Id = new Handler().getMySQL().get("clan", "id", new Handler().getMySQL().get("user", "id", event.getAuthor().getId(), "clan"), "id");
                        String Name = new Handler().getMySQL().get("clan", "id", new Handler().getMySQL().get("user", "id", event.getAuthor().getId(), "clan"), "name");
                        List<String> Members = new Handler().getMySQL().getall("user", "clan", Id, "id");
                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Clan Informations")
                                .addField("Name", Name, true)
                                .addField("ID", Id, true)
                                .addField("Bank", Bank, true)
                                .addField("Members", String.valueOf(Members.size()), true)
                                .build()).queue();
                    } else event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("You are not in a clan").setDescription("Join a clan or create one").build()).queue();
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
