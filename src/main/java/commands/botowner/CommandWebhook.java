package commands.botowner;

import commands.Command;
import core.MessageHandler;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.Webhook;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.webhook.WebhookClient;
import net.dv8tion.jda.webhook.WebhookClientBuilder;
import net.dv8tion.jda.webhook.WebhookMessage;
import net.dv8tion.jda.webhook.WebhookMessageBuilder;

import java.util.concurrent.TimeUnit;

public class CommandWebhook implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        // Hier soll der WebHook command entstehen... Danke ForYaSee für die Idee
        if (Owner.get(event.getAuthor())) {
            if (args.length >= 3 && event.getMessage().getMentionedChannels().size() == 1) {
                TextChannel otherChannel = event.getMessage().getMentionedChannels().get(0);

                try {
                    Webhook webhook;
                    webhook = otherChannel.createWebhook(args[0]).complete();

                    WebhookClientBuilder clientBuilder = webhook.newClient();
                    WebhookClient client = clientBuilder.build();


                    event.getMessage().delete().queue();
                    WebhookMessageBuilder builder = new WebhookMessageBuilder();
                    builder.setContent(event.getMessage().getContentRaw()
                            .replaceFirst(MessageHandler.getprefix(event.getGuild()), "")
                            .replaceFirst("webhook", "")
                            .replaceFirst(args[0], "")
                            .replaceFirst(args[1], ""));
                    WebhookMessage message = builder.build();
                    client.send(message);
                    String webhookid = client.getId();
                    client.close();
                    otherChannel.deleteWebhookById(webhookid).queueAfter(10, TimeUnit.SECONDS);

                } catch (NullPointerException fuck) {
                    fuck.printStackTrace();
                }
            } else {
                event.getTextChannel().sendMessage("Sorry you must write 3 args").queue();
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
