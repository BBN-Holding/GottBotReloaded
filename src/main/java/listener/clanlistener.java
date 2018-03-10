package listener;

import core.MySQL;
import net.dv8tion.jda.core.events.channel.category.CategoryDeleteEvent;
import net.dv8tion.jda.core.events.channel.text.TextChannelDeleteEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class clanlistener extends ListenerAdapter {

    @Override
    public void onCategoryDelete(CategoryDeleteEvent event) {
        if (event.getCategory().getIdLong()== Long.parseLong(MySQL.get("clan", "id", event.getGuild().getId(), "clancategory"))) {

            MySQL.update("server", "clancategory", "none", "id", event.getGuild().getId());

        }
    }

    @Override
    public void onTextChannelDelete(TextChannelDeleteEvent event) {
        if (MySQL.get("clan", "textchannel", event.getChannel().getId(), "name")!=null) {
            MySQL.delete("clan", "textchannel", event.getChannel().getId());
        }
    }
}
