package commands.botowner;

import commands.Command;
import core.Main;
import net.dv8tion.jda.bot.sharding.ShardManager;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandShard implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Owner.get(event.getAuthor())) {
            if (args.length<1) {
                event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Shard info")
                        .addField("Total Shards", String.valueOf(Main.shardManager.getShardsTotal()), true)
                        .addField("Running Shards", String.valueOf(Main.shardManager.getShardsRunning()), true)
                        .addField("Queued Shards", String.valueOf(Main.shardManager.getShardsQueued()), true)
                        .build()
                ).queue();
            } else {
                switch (args[0]) {
                    case "info":
                    case "i":
                        if (args.length>1) {
                            if (Main.shardManager.getShardsTotal()>=Integer.parseInt(args[2])) {
                                JDA shard = Main.shardManager.getShardById(args[2]);
                                if (args[1].equalsIgnoreCase("o")||args[1].equalsIgnoreCase("overview")) {
                                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Shardinfo - Shard "+args[1]+" - Overview")
                                            .addField("Shardid", String.valueOf(shard.getShardInfo().getShardId()),true)
                                            .addField("Servers", String.valueOf(shard.getGuilds().size()), true)
                                            .addField("User", String.valueOf(shard.getUsers().size()), true)
                                            .build()
                                    ).queue();
                                } else if (args[1].equalsIgnoreCase("guilds")||args[1].equalsIgnoreCase("g")) {
                                    int i=0;
                                    String Guilds="";
                                    while (shard.getGuilds().size()>i) {
                                        Guilds+=shard.getGuilds().get(i).getName()+"("+shard.getGuilds().get(i).getId()+")\n";
                                        i++;
                                    }
                                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Shardinfo - Shard "+args[2]+" - "+args[1]).setDescription("Guilds Total: "+shard.getGuilds().size()+"\n"+Guilds).build()).queue();
                                }
                            }
                        }
                        break;
                        // manage
                    // gb.s m r 1
                    case "manage":
                    case "m":
                        if (args.length==3) {
                            if (args[1].equalsIgnoreCase("r")||args[2].equalsIgnoreCase("restart")) {
                                    event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("restarted Shard "+ args[2]).build()).queue();
                                    Main.shardManager.restart(Integer.parseInt(args[2]));
                            } else if (args[1].equalsIgnoreCase("stop")) {
                                event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Request send").setDescription("Shutdown request to Shard "+args[2]+" was send.").build()).queue();
                                Main.shardManager.getShardById(args[2]).shutdown();
                            } else if (args[1].equalsIgnoreCase("start")) {
                                Main.shardManager.start(Integer.parseInt(args[2]));
                            }
                        }
                        break;
                }
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
