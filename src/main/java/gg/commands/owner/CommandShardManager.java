package gb.commands.owner;

import gb.GottBot;
import gb.Handler.CommandHandling.Command;
import gb.Handler.Server;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandShardManager implements Command, Server {
    static ArrayList<HashMap> hashMaps;
    @Override
    public String[] Aliases() {
        return new String[]{"shardmanager","sm", "shardcontroller", "sc"};
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length<1) {
            event.getTextChannel().sendMessage(GottBot.getMessage().getCommandTemplate(Aliases(), "gb.sm info", "Shows Information about all Shards")).queue();
        } else {
            if (!GottBot.getDev()) {
                switch (args[0].toLowerCase()) {
                    case "info":
                        if (args.length==1) {
                            EmbedBuilder embedBuilder = new EmbedBuilder().setTitle("ShardManager - Info");
                            GottBot.sendToServer().println("ShardManager - Info");
                            GottBot.sendToServer().flush();
                            try {
                                Thread.sleep(2000L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            for (HashMap<String, String> hashMap : hashMaps) {
                                embedBuilder.addField("Shard " + hashMap.get("ShardID:"), "Guilds: " + hashMap.get("Guilds:") +
                                        " Users: " + hashMap.get("Users:") + " TextChannels: " + hashMap.get("TextChannels:") +
                                        " VoiceChannels: " + hashMap.get("VoiceChannels:") + " Categories: " + hashMap.get("Categories:"), false);
                            }
                            event.getTextChannel().sendMessage(embedBuilder.build()).queue();
                        }
                        break;
                    case "stopall":
                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("ShardManager - Stop all").setDescription("Stopping all Shards").build()).queue();
                        GottBot.sendToServer().println("Stop all!");
                        GottBot.sendToServer().flush();
                        break;
                }
            } else event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Error").setDescription("Dev mode is activated").build()).queue();
        }
    }

    @Override
    public void onMessage(String Message) {
        if (Message.startsWith("ShardManager - inforesult: ")) {
            String msg = Message.replace("ShardManager - inforesult: ", "");
            hashMaps = new ArrayList<>();
            String[] shards = msg.replaceAll("\n", " ").split(",");
            for (String shard:shards) {
                String[] things = shard.split(" ");
                HashMap<String,String> thingshashmap = new HashMap<>();
                for (String thing:things) {
                    String haha="";
                    if (thing.startsWith("ShardID:")) {
                        haha="ShardID:";
                    } else if (thing.startsWith("Guilds:")) {
                        haha = "Guilds:";
                    } else if (thing.startsWith("Users:")) {
                        haha = "Users:";
                    } else if (thing.startsWith("TextChannels:")) {
                        haha = "TextChannels:";
                    } else if (thing.startsWith("VoiceChannels:")) {
                        haha = "VoiceChannels:";
                    } else if (thing.startsWith("Categories:")) {
                        haha = "Categories:";
                    }
                    thingshashmap.put(haha, thing.replace(haha, ""));
                }
                hashMaps.add(thingshashmap);
            }
        }
    }
}
