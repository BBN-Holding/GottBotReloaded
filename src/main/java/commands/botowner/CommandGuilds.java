package commands.botowner;

import commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandGuilds implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (Handler.get(event.getAuthor())) {

            int jaso = event.getJDA().getGuilds().size();
            jaso=jaso/20;
            jaso++;
            int server;
            int site;
            if (args.length>0) {
                 server = Integer.parseInt(args[0])*20;
                 site = Integer.parseInt(args[0]);
            } else {
                 server = 0;
                 site = 1;
            }
            int i = 0;
            String out ="";
            while (event.getJDA().getGuilds().size()>server&&i<20) {
                Guild guild = event.getJDA().getGuilds().get(server);
                out += String.valueOf(server+1)+". "+guild.getName()+" | User: "+guild.getMembers().size()+" ("+guild.getId()+")";
                i++;
                server++;
            }

            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Guilds").setDescription("Site: "+site+"/"+jaso+"\nAll Guilds: "+event.getJDA().getGuilds().size()+"\n``"+out+"``").build()).queue();
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
