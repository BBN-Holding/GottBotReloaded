package commands;

import core.MessageHandler;
import core.MySQL;
import listener.Message;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.List;

public class Clan implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length<1) {
            event.getTextChannel().sendMessage(new EmbedBuilder().setDescription(MessageHandler.get(event.getAuthor()).getString("clantext").replaceAll("gb.", MessageHandler.getprefix(event.getGuild())))
                    .setTitle(MessageHandler.get(event.getAuthor()).getString("clantitel")).build()).queue();
        } else {
            switch (args[0].toLowerCase()) {
                case "list":
                    System.out.println("TEST");
                    int i = 0;
                    String out = MessageHandler.get(event.getAuthor()).getString("clanlisttext");
                    List<String> List = MySQL.getall("clan", "guildid", event.getGuild().getId(), "name");
                    while (List.size() - 1 >= i) {
                        if (List.size() - 1 > i) {
                            out += List.get(i) + ", ";
                        } else out += List.get(i);
                        i++;
                    }
                    event.getTextChannel().sendMessage(new EmbedBuilder().setDescription(out).setTitle(MessageHandler.get(event.getAuthor()).getString("clanlisttitel")).build()).queue();
                    out = "";
                    List.clear();
                    break;

                case "create":

                    if (MySQL.get("user", "ID", event.getAuthor().getId(), "clan").equals("none")) {
                        if (args.length<2) {

                        } else {
                            if (Integer.parseInt(MySQL.get("user", "id", event.getAuthor().getId(), "miner"))>=1024) {
                                Long TextChannel  = event.getGuild().getController().createTextChannel(args[1]).complete().getIdLong();
                                int id =0;
                                while (MySQL.getall("clan", "1", "", "id").size()>=id) {
                                    id++;
                                }
                                MySQL.insert("clan", "name`, `id`, `textchannel",args[1]+"', '"+id+"', '"+TextChannel);
                                event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("clancreatesucesstitel"))
                                        .setDescription(MessageHandler.get(event.getAuthor()).getString("clancreatesucesstext")).build()).queue();
                            } else {
                                event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("minertitel"))
                                        .setDescription(MessageHandler.get(event.getAuthor()).getString("minertext")
                                                .replaceAll("gb.", MessageHandler.getprefix(event.getGuild()))).build()).queue();
                            }
                        }
                    }

                    break;

                case "join":
                    if (MySQL.get("user", "ID", event.getAuthor().getId(), "clan").equals("none")) {
                        if (args.length < 2) {
                            event.getTextChannel().sendMessage(
                                    new EmbedBuilder()
                                            .setDescription(MessageHandler.get(event.getAuthor()).getString("clanjointext").replaceAll("gb.", MessageHandler.getprefix(event.getGuild())))
                                            .setTitle(MessageHandler.get(event.getAuthor()).getString("clanjointitel")).build()).queue();
                        } else {
                            if (MySQL.get("clan", "name", args[1], "open").equals("false")) {
                                MySQL.insert("requests", "name`,`user", args[1] + "`,`" + event.getAuthor().getId());
                                event.getGuild().getTextChannelsByName(args[1], true).get(0).sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("clanjoinrequesttitel"))
                                        .setDescription(MessageHandler.get(event.getAuthor()).getString("clanjoinrequesttext").replaceAll("User", event.getAuthor().getAsMention()).replaceAll("gb.", MessageHandler.getprefix(event.getGuild()))).build()).queue();
                            } else if (MySQL.get("clan", "name", args[1], "open").equals("true")) {
                                String Channelid=MySQL.get("clan", "name", args[1], "id");
                                MySQL.update("user", "clan", Channelid , "ID", event.getAuthor().getId());
                                event.getGuild().getTextChannelById(Channelid).sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("clanjoineventtitel"))
                                .setDescription(MessageHandler.get(event.getAuthor()).getString("clanjoineventtext")).build()).queue();
                            }
                        }
                    }
                    break;

                case "accept":

                    break;
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
