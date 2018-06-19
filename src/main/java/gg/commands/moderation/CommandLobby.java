package gb.commands.moderation;

import gb.GottBot;
import gb.Handler.CommandHandling.Command;
import com.rethinkdb.RethinkDB;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandLobby implements Command {
    @Override
    public String[] Aliases() {
        return new String[]{"lobby", "chat"};
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length<1) {
            event.getTextChannel().sendMessage(GottBot.getMessage().getCommandTemplate(Aliases(), "gb.lobby connect|disconnect", "Join the Lobby")).queue();
        } else {
            switch (args[0].toLowerCase()) {
                case "connect":
                    if (GottBot.getDB().get("server", "serverid", event.getGuild().getId(), "lobby").equals("none")) {
                        GottBot.getDB().update("server", "serverid", event.getGuild().getId(), GottBot.getDB().getR().hashMap("lobby", "true"));
                        Channel channel;
                        if (event.getGuild().getTextChannelsByName("GBLobby", true).size()==0) {
                            channel = event.getGuild().getController().createTextChannel("GBLobby").complete();
                        } else channel = event.getGuild().getTextChannelsByName("GBLobby", true).get(0);
                        GottBot.getDB().update("server", "serverid", event.getGuild().getId(), RethinkDB.r.hashMap("lobbychannel", channel.getId()));
                        GottBot.getDB().update("lobby", "lobbyid", "1",
                                GottBot.getDB().getR().hashMap("guilds",
                                        GottBot.getDB().get("lobby", "lobbyid", "1", "guilds").replace("]", "")+
                                channel.getId()+":]"));
                        event.getTextChannel().sendMessage("Joined Lobby").queue();
                    }
                    break;

                case "disconnect":
                    if (!GottBot.getDB().get("server", "serverid", event.getGuild().getId(), "lobby").equals("none")) {
                        GottBot.getDB().update("server", "serverid", event.getGuild().getId(), GottBot.getDB().getR().hashMap("lobby", "none"));
                        GottBot.getDB().update("lobby", "lobbyid", "1",
                                GottBot.getDB().getR().hashMap("guilds",
                                        GottBot.getDB().get("lobby", "lobbyid", "1", "guilds").replace(event.getGuild().getTextChannelsByName("GBLobby", true).get(0).getId()+":", "")));
                        event.getTextChannel().sendMessage("Leaved Lobby").queue();
                    }
                    break;
            }
        }
    }
}
