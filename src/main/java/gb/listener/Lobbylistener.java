package gb.listener;

import gb.GottBot;
import net.dv8tion.jda.core.entities.Icon;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.Webhook;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.webhook.WebhookClient;
import net.dv8tion.jda.webhook.WebhookMessage;
import net.dv8tion.jda.webhook.WebhookMessageBuilder;

import java.io.IOException;
import java.net.URL;

public class Lobbylistener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            if (!GottBot.getDB().get("server", "serverid", event.getGuild().getId(), "lobby").equals("none")) {
                if (GottBot.getDB().get("server", "serverid", event.getGuild().getId(), "lobbychannel").equals(event.getTextChannel().getId())) {
                    String guilds = GottBot.getDB().getAll("lobby", "guilds");
                   String[] channellist = guilds.replace("[", "").replace("]", "").replace(GottBot.getDB().get("server", "serverid", event.getGuild().getId(), "lobbychannel") + ":", "").split(":");
                    if (channellist.length!=0) {
                        for (int i = 0; (channellist.length) > i; i++) {
                            int finalI = i;
                            new Thread(() -> {
                                TextChannel textChannel = GottBot.getOneShardBot().getTextChannelById(channellist[finalI]);
                                Webhook webhook=null;
                                for (Webhook webhook2:textChannel.getWebhooks().complete()) {
                                    if (webhook2.getName().equals("GottBot")) {
                                        webhook=webhook2;
                                    }
                                }
                                if (webhook == null) {
                                    try {
                                        webhook=textChannel.createWebhook("GottBot").setAvatar(Icon.from(new URL(event.getJDA().getSelfUser().getAvatarUrl()).openStream())).complete();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                try {
                                    webhook.getManager().setName(event.getAuthor().getName()+" ("+event.getGuild().getName()+")").setAvatar(Icon.from(new URL(event.getAuthor().getDefaultAvatarUrl()).openStream())).queue();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                WebhookClient client = webhook.newClient().build();
                                WebhookMessageBuilder webhookMessageBuilder = new WebhookMessageBuilder();
                                webhookMessageBuilder.setContent(event.getMessage().getContentRaw());
                                webhookMessageBuilder.setAvatarUrl(event.getAuthor().getAvatarUrl());
                                WebhookMessage message = webhookMessageBuilder.build();
                                client.send(message);
                                client.close();
                            }).start();
                        }
                    }
                }
            }
        }
    }
}
