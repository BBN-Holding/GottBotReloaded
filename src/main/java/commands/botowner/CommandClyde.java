package commands.botowner;



import core.MessageHandler;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.webhook.WebhookClient;
import net.dv8tion.jda.webhook.WebhookClientBuilder;
import net.dv8tion.jda.webhook.WebhookMessage;
import net.dv8tion.jda.core.entities.Webhook;
import net.dv8tion.jda.webhook.WebhookMessageBuilder;
import commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandClyde implements Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {


            TextChannel otherChannel = event.getMessage().getTextChannel();

            try {
                Webhook webhook = null;

                for (Webhook hook : otherChannel.getWebhooks().complete()) {
                    if (hook.getName().equals("Clydе")) {
                        webhook = hook;
                        break;
                    }
                }
                if (webhook == null) {
                    webhook = otherChannel.createWebhook("Clydе").complete();
                }
                WebhookClientBuilder clientBuilder = webhook.newClient();
                WebhookClient client = clientBuilder.build();


                event.getMessage().delete().queue();
                WebhookMessageBuilder builder = new WebhookMessageBuilder();
                builder.setContent(event.getMessage().getContentRaw().replaceFirst(MessageHandler.getprefix(event.getGuild()), "").replaceFirst("clyde", ""));
                builder.setAvatarUrl("https://cdn.discordapp.com/avatars/419613495881891841/f0454b649c2f2faaf4f6e2ff12a5d954.webp?size=256");
                WebhookMessage message = builder.build();
                client.send(message);
                client.close();

            } catch (NullPointerException fuck) {
                fuck.printStackTrace();
            }


    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

}
