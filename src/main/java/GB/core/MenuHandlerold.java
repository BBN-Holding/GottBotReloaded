package core;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MenuHandlerold {

    public static MessageEmbed getMessage(String emote, MessageEmbed embed, User user, Guild guild) {
        MessageEmbed out=new EmbedBuilder().setTitle("Error").setDescription("Sorry the Emote was not found! if you are sure that it is a bug use ``gb.bug`` Thanks <3").setColor(Color.RED).build();
        // Overview
        if (embed.getTitle().equals(MessageHandler.get("Helpmenu.helpmenu", user, guild))) {
            switch (emote) {
                case "\uD83D\uDD28":
                    out = new EmbedBuilder().setTitle(MessageHandler.get("Helpmenu.moderation.title", user, guild))
                            .addField(MessageHandler.get("Helpmenu.moderation.ban.title", user, guild), MessageHandler.get("Helpmenu.moderation.ban.description", user, guild), false)
                            .addField(MessageHandler.get("Helpmenu.moderation.kick.title", user,guild), MessageHandler.get("Helpmenu.moderation.kick.description", user, guild), false)
                            .addField(MessageHandler.get("Helpmenu.moderation.prefix.title", user, guild), MessageHandler.get("Helpmenu.moderation.prefix.description", user, guild), false)
                            .addField(MessageHandler.get("Helpmenu.moderation.verification.pre", user, guild), "✅", false)
                            .build();
                    break;

                case "\uD83D\uDEE0":
                    out = new EmbedBuilder().setTitle(MessageHandler.get("Helpmenu.tools.title", user, guild))
                            .addField(MessageHandler.get("Helpmenu.tools.github.title", user, guild), MessageHandler.get("Helpmenu.tools.github.description", user, guild), true)
                            .addField(MessageHandler.get("Helpmenu.tools.ping.title", user, guild), MessageHandler.get("Helpmenu.tools.ping.description", user, guild), true)
                            .addField(MessageHandler.get("Helpmenu.tools.profile.title", user, guild), MessageHandler.get("Helpmenu.tools.profile.description", user, guild), true)
                            .build();
                    break;

                case "\uD83D\uDC65":
                    out= new EmbedBuilder().setTitle(MessageHandler.get("Helpmenu.usercommands.title", user, guild))
                            .addField(MessageHandler.get("Helpmenu.usercommands.bug.title", user, guild), MessageHandler.get("Helpmenu.usercommands.bug.description", user, guild), true)
                            .addField(MessageHandler.get("Helpmenu.usercommands.help.title", user, guild), MessageHandler.get("Helpmenu.usercommands.help.description", user, guild), true)
                            .addField(MessageHandler.get("Helpmenu.usercommands.language.title", user, guild), MessageHandler.get("Helpmenu.usercommands.language.description", user, guild), true)
                            .addField(MessageHandler.get("Helpmenu.usercommands.levelmessage.title", user, guild), MessageHandler.get("Helpmenu.usercommands.levelmessage.description", user, guild), true)
                            .addField(MessageHandler.get("Helpmenu.usercommands.say.title", user, guild), MessageHandler.get("Helpmenu.usercommands.say.description", user, guild), true)
                            .addField(MessageHandler.get("Helpmenu.usercommands.stats.title", user, guild), MessageHandler.get("Helpmenu.usercommands.stats.description", user, guild), true)
                            .build();
                    break;
            }
        } else if (embed.getTitle().equals(MessageHandler.get("Helpmenu.moderation.title", user, guild))) {
            switch (emote) {
                case "✅":
                    out = new EmbedBuilder().setTitle(MessageHandler.get("Helpmenu.moderation.verification.title", user, guild))
                            .addField("Usage", "gb.verification", false)
                            .addField("Description", "Set up a Verfixation Message", false)
                            .build();
                    break;
            }
        }
        System.out.println(embed.getTitle());
        if (embed.getTitle().replaceFirst(MessageHandler.get("Helpmenu.helpmenu.mod.title", user, guild), "").replaceFirst(MessageHandler.get("Helpmenu.helpmenu.tools.title", user, guild), "").replaceFirst(MessageHandler.get("Helpmenu.helpmenu.usercommands.title", user, guild), "").equals(MessageHandler.get("Helpmenu.helpmenu", user, guild)+" - ")&&emote.equals("\uD83D\uDD19")) {
                out = new EmbedBuilder().setTitle(MessageHandler.get("Helpmenu.helpmenu", user, guild)).setDescription(MessageHandler.get("Helpmenu.helpmenu.description", user, guild))
                        .addField(MessageHandler.get("Helpmenu.helpmenu.mod.title", user, guild), "\uD83D\uDD28", true)
                        .addField(MessageHandler.get("Helpmenu.helpmenu.tools.title", user, guild), "\uD83D\uDEE0", true)
                        .addField(MessageHandler.get("Helpmenu.helpmenu.usercommands.title", user, guild), "\uD83D\uDC65", true)
                        .build();


        } else if (emote.equals("◀")&&embed.getTitle().equals(MessageHandler.get("Helpmenu.moderation.verification.title", user, guild))) {
            out= MenuHandlerold.getMessage("\uD83D\uDD28", new EmbedBuilder().setTitle(MessageHandler.get("Helpmenu.helpmenu", user, guild)).build(), user, guild);
        }
        return out;
    }

    public static List<String> getemote(String emote, MessageEmbed embed, User user, Guild guild) {
        List<String> list = new ArrayList<>();


        if (embed.getTitle().equals(MessageHandler.get("Helpmenu.helpmenu", user, guild))&&emote.equals("\uD83D\uDD19")) {
            list.add("\uD83D\uDD28");
            list.add("\uD83D\uDEE0");
            list.add("\uD83D\uDC65");
        }

        if (embed.getTitle().equals(MessageHandler.get("Helpmenu.moderation.title", user, guild))) {
            list.add("✅");
        }

        if (embed.getTitle().contains(MessageHandler.get("Helpmenu.helpmenu", user, guild)+" - ")) {
            list.add("\uD83D\uDD19");
        }

        if (embed.getTitle().replaceFirst(MessageHandler.get("Helpmenu.helpmenu", user, guild)+" - ", "").contains(" - ")) {

                list.add("◀");

        }


        return list;
    }


}
