package GB.Handler;

import GB.GottBot;

import java.util.Collection;
import java.util.LinkedList;

public class Info {

    public String getPrefix(String id) {
        return GottBot.getDB().get("server", "serverid", id, "CommandPrefix");
    }

    public String getShards() {
        String Shards=GottBot.getDB().getAll("info", "Shards");
        return Shards;
    }

    public String getTotalShards() {
        return GottBot.getDB().getAll("info", "Total");
    }

    public String getMaxShards() {
        return GottBot.getDB().getAll("info", "MaxShards");
    }

    public Collection<Integer> getstartShards() {
        int l=Integer.parseInt(getMaxShards());
        int l2=0;
        Collection<Integer> shards=new LinkedList<Integer>();
        int l3=0;
        int max=Integer.parseInt(GottBot.getConfig().Shards);
        while (l>l2&&max>l3) {
            if (!getShards().contains(String.valueOf(l2))) {
                shards.add(l2);
                l3++;
            }
            l2++;
        }
        return shards;
    }
}
