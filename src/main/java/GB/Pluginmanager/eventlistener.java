package GB.Pluginmanager;

import GB.Pluginmanager.Plugin;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class eventlistener extends ListenerAdapter {

    public static ArrayList<Plugin> plugins = new ArrayList<>();

    @Override
    public void onGenericEvent(Event event) {
        int i =0;
        while (plugins.size()>i) {
            plugins.get(i).onEvent(event);
            i++;
        }
    }
}
