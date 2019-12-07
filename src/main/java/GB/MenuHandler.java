package GB;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MenuHandler {

    public MessageEmbed getMessage(String emote, MessageEmbed embed, User user, Guild guild) {
        MessageEmbed out=new EmbedBuilder().setTitle("GB.Error").setDescription("Sorry the Emote was not found! if you are sure that it is a bug use ``gb.bug`` Thanks <3").setColor(Color.RED).build();
        // Overview
        if (embed.getTitle().equals(new Handler().getMessageHandler().get("Helpmenu.helpmenu", user, guild))) {
            switch (emote) {
                case "\uD83D\uDD28":
                    out = new EmbedBuilder().setTitle(new Handler().getMessageHandler().get("Helpmenu.moderation.title", user, guild))
                            .addField(new Handler().getMessageHandler().get("Helpmenu.moderation.ban.title", user, guild), new Handler().getMessageHandler().get("Helpmenu.moderation.ban.description", user, guild), false)
                            .addField(new Handler().getMessageHandler().get("Helpmenu.moderation.kick.title", user,guild), new Handler().getMessageHandler().get("Helpmenu.moderation.kick.description", user, guild), false)
                            .addField(new Handler().getMessageHandler().get("Helpmenu.moderation.prefix.title", user, guild), new Handler().getMessageHandler().get("Helpmenu.moderation.prefix.description", user, guild), false)
                            .addField(new Handler().getMessageHandler().get("Helpmenu.moderation.verification.pre", user, guild), "✅", false)
                            .build();
                    break;

                case "\uD83D\uDEE0":
                    out = new EmbedBuilder().setTitle(new Handler().getMessageHandler().get("Helpmenu.tools.title", user, guild))
                            .addField(new Handler().getMessageHandler().get("Helpmenu.tools.github.title", user, guild), new Handler().getMessageHandler().get("Helpmenu.tools.github.description", user, guild), true)
                            .addField(new Handler().getMessageHandler().get("Helpmenu.tools.ping.title", user, guild), new Handler().getMessageHandler().get("Helpmenu.tools.ping.description", user, guild), true)
                            .addField(new Handler().getMessageHandler().get("Helpmenu.tools.profile.title", user, guild), new Handler().getMessageHandler().get("Helpmenu.tools.profile.description", user, guild), true)
                            .build();
                    break;

                case "\uD83D\uDC65":
                    out= new EmbedBuilder().setTitle(new Handler().getMessageHandler().get("Helpmenu.usercommands.title", user, guild))
                            .addField(new Handler().getMessageHandler().get("Helpmenu.usercommands.bug.title", user, guild), new Handler().getMessageHandler().get("Helpmenu.usercommands.bug.description", user, guild), true)
                            .addField(new Handler().getMessageHandler().get("Helpmenu.usercommands.help.title", user, guild), new Handler().getMessageHandler().get("Helpmenu.usercommands.help.description", user, guild), true)
                            .addField(new Handler().getMessageHandler().get("Helpmenu.usercommands.language.title", user, guild), new Handler().getMessageHandler().get("Helpmenu.usercommands.language.description", user, guild), true)
                            .addField(new Handler().getMessageHandler().get("Helpmenu.usercommands.levelmessage.title", user, guild), new Handler().getMessageHandler().get("Helpmenu.usercommands.levelmessage.description", user, guild), true)
                            .addField(new Handler().getMessageHandler().get("Helpmenu.usercommands.say.title", user, guild), new Handler().getMessageHandler().get("Helpmenu.usercommands.say.description", user, guild), true)
                            .addField(new Handler().getMessageHandler().get("Helpmenu.usercommands.stats.title", user, guild), new Handler().getMessageHandler().get("Helpmenu.usercommands.stats.description", user, guild), true)
                            .build();
                    break;
            }
        } else if (embed.getTitle().equals(new Handler().getMessageHandler().get("Helpmenu.moderation.title", user, guild))) {
            switch (emote) {
                case "✅":
                    out = new EmbedBuilder().setTitle(new Handler().getMessageHandler().get("Helpmenu.moderation.verification.title", user, guild))
                            .addField("Usage", "gb.verification", false)
                            .addField("Description", "Set up a Verfixation Message", false)
                            .build();
                    break;
            }
        }
        System.out.println(embed.getTitle());
        if (embed.getTitle().replaceFirst(new Handler().getMessageHandler().get("Helpmenu.helpmenu.mod.title", user, guild), "").replaceFirst(new Handler().getMessageHandler().get("Helpmenu.helpmenu.tools.title", user, guild), "").replaceFirst(new Handler().getMessageHandler().get("Helpmenu.helpmenu.usercommands.title", user, guild), "").equals(new Handler().getMessageHandler().get("Helpmenu.helpmenu", user, guild)+" - ")&&emote.equals("\uD83D\uDD19")) {
            out = new EmbedBuilder().setTitle(new Handler().getMessageHandler().get("Helpmenu.helpmenu", user, guild)).setDescription(new Handler().getMessageHandler().get("Helpmenu.helpmenu.description", user, guild))
                    .addField(new Handler().getMessageHandler().get("Helpmenu.helpmenu.mod.title", user, guild), "\uD83D\uDD28", true)
                    .addField(new Handler().getMessageHandler().get("Helpmenu.helpmenu.tools.title", user, guild), "\uD83D\uDEE0", true)
                    .addField(new Handler().getMessageHandler().get("Helpmenu.helpmenu.usercommands.title", user, guild), "\uD83D\uDC65", true)
                    .build();


        } else if (emote.equals("◀")&&embed.getTitle().equals(new Handler().getMessageHandler().get("Helpmenu.moderation.verification.title", user, guild))) {
            out= new Handler().getMenuHandler().getMessage("\uD83D\uDD28", new EmbedBuilder().setTitle(new Handler().getMessageHandler().get("Helpmenu.helpmenu", user, guild)).build(), user, guild);
        }
        return out;
    }

    public java.util.List<String> getemote(String emote, MessageEmbed embed, User user, Guild guild) {
        List<String> list = new ArrayList<>();


        if (embed.getTitle().equals(new Handler().getMessageHandler().get("Helpmenu.helpmenu", user, guild))&&emote.equals("\uD83D\uDD19")) {
            list.add("\uD83D\uDD28");
            list.add("\uD83D\uDEE0");
            list.add("\uD83D\uDC65");
        }

        if (embed.getTitle().equals(new Handler().getMessageHandler().get("Helpmenu.moderation.title", user, guild))) {
            list.add("✅");
        }

        if (embed.getTitle().contains(new Handler().getMessageHandler().get("Helpmenu.helpmenu", user, guild)+" - ")) {
            list.add("\uD83D\uDD19");
        }

        if (embed.getTitle().replaceFirst(new Handler().getMessageHandler().get("Helpmenu.helpmenu", user, guild)+" - ", "").contains(" - ")) {

            list.add("◀");

        }


        return list;
    }


}
