package GB.commands.moderation;

import GB.GottBot;
import GB.Handler.CommandHandling.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandPortal implements Command {
    @Override
    public String[] Aliases() {
        return new String[]{"portal"};
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        /*

        TODO: add portal table
        TODO: translate
        TODO: invite system

        */
        if (args.length<1) {
            event.getTextChannel().sendMessage(GottBot.getMessage().getCommandTemplate(Aliases(), "gb.portal open|info|connect|close|public", "Opens a portal to another Server")).queue();
        } else {
            switch (args[0].toLowerCase()) {
                case "open":
                            // adding to DB
                            if (GottBot.getDB().get("portal", "serverid", event.getGuild().getId(), "connected") == null) {
                                GottBot.getDB().insertPortal(event.getGuild().getId());
                            }
                            // create Channel if not exist
                            if (GottBot.getDB().get("portal", "serverid", event.getGuild().getId(), "channel").equals("none")) {
                                String portalchannel = event.getGuild().getController().createTextChannel("gb-portal").complete().getId();
                                GottBot.getDB().update("portal", "serverid",event.getGuild().getId(),
                                        GottBot.getDB().getR().hashMap("channel", portalchannel));
                            }
                            // set connected true
                            GottBot.getDB().update("portal", "serverid", event.getGuild().getId(), GottBot.getDB().getR().hashMap("connected", "true"));
                            event.getTextChannel().sendMessage("opened portal").queue();
                    break;
                case "public":
                    if (GottBot.getDB().get("portal", "serverid", event.getGuild().getId(), "connected").equals("true")) {
                        String edit;
                        if (GottBot.getDB().get("portal", "serverid", event.getGuild().getId(), "public").equals("true")) {
                            edit="false";
                        } else edit="true";
                        GottBot.getDB().update("portal", "serverid", event.getGuild().getId(),
                                GottBot.getDB().getR().hashMap("public", edit));
                        event.getTextChannel().sendMessage("set publicy to "+edit).queue();
                    } else event.getTextChannel().sendMessage("first connect with gb.portal open").queue();
                    break;
                case "info":
                    if (GottBot.getDB().get("portal", "serverid", event.getGuild().getId(), "connected").equals("true")) {
                        String out = "";
                        String connectedServer=GottBot.getDB().get("portal", "serverid", event.getGuild().getId(), "connectedservers");
                        String[] servers=connectedServer.split(":");
                        for (String server:servers) {
                            Guild guild = GottBot.getShardManager().getGuildById(server);
                            out+=guild.getName()+" ("+guild.getId()+")  ";
                        }
                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Portal - Info")
                                .addField("connectedportals", out, false)
                                .build()
                        ).queue();
                    } else event.getTextChannel().sendMessage("first connect with gb.portal open").queue();
                    break;
                case "connect":
                    if (args.length == 2) {
                        if (GottBot.getShardManager().getGuildById(args[1]) != null) {
                            if (GottBot.getDB().get("portal", "serverid", event.getGuild().getId(), "connected").equals("true")) {
                                if (GottBot.getDB().get("portal", "serverid", event.getGuild().getId(), "public").equals("true")) {
                                    String id=event.getGuild().getId();
                                    String id2=args[1];
                                    for (int i =0; 2>i; i++) {
                                        GottBot.getDB().update("portal", "serverid", id,
                                                GottBot.getDB().getR()
                                                        .hashMap("connectedservers",
                                                                GottBot.getDB().get("portal", "serverid", id, "connectedservers")
                                                                        + id2+":"));
                                        String temp = id;
                                        id=id2;
                                        id2=temp;
                                    }
                                    event.getJDA().getTextChannelById(
                                            GottBot.getDB().get("portal", "serverid", event.getGuild().getId(), "channel"))
                                            .sendMessage("connected to server ``"+GottBot.getShardManager().getGuildById(args[1]).getName()+"``").queue();
                                    GottBot.getShardManager().getTextChannelById(GottBot.getDB().get("portal", "serverid", args[1], "channel"))
                                            .sendMessage("server  ``"+event.getGuild().getName()+"`` joined").queue();
                                } else event.getTextChannel().sendMessage("you can only join with an invite...").queue();
                            } else event.getTextChannel().sendMessage("first open connection with gb.portal open").queue();
                        } else event.getTextChannel().sendMessage("This Guild not exist").queue();
                    } else event.getTextChannel().sendMessage("look in the help :facepalm:").queue();
                    break;
            }
        }
    }
}
