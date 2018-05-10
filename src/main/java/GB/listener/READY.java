package GB.listener;

import GB.core.Main;
import GB.stuff.DATA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class READY extends ListenerAdapter {

    @Override
    public void onReady(ReadyEvent event) {
        if (Main.shardManager.getGuildById(DATA.BBNS).isAvailable()) {
            try {
                Guild guild = Main.shardManager.getGuildById(DATA.BBNS);
                List<Member> members = guild.getMembers();
                int i = 0;
                String User="";
                while (members.size() > i) {
                    User += members.get(i).getUser().getId()+" ";
                    i++;
                }
                FileWriter fw = new FileWriter("CoolGuysOnBBNServer");
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write(User);

                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else System.out.println("I think BigBotNetwork is deleted :(");
    }
}
