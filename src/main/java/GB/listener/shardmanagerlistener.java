package GB.listener;

import GB.GottBot;
import net.dv8tion.jda.core.entities.Icon;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.Webhook;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.webhook.WebhookClient;
import net.dv8tion.jda.webhook.WebhookClientBuilder;
import net.dv8tion.jda.webhook.WebhookMessage;
import net.dv8tion.jda.webhook.WebhookMessageBuilder;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class shardmanagerlistener extends ListenerAdapter {
    static ArrayList<TextChannel> textChannels = new ArrayList<>();
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {

        }
    }
}
