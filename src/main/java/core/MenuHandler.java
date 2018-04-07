package core;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.react.GuildMessageReactionAddEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MenuHandler {

    public static MessageEmbed getMessage(String emote, MessageEmbed embed, User user) {
        MessageEmbed out=new EmbedBuilder().setTitle("Error").setDescription("Sorry the Emote was not found! if you are sure that it is a bug use ``gb.bug`` Thanks <3").setColor(Color.RED).build();
        ResourceBundle msg = MessageHandler.get(user);
        // Overview
        if (embed.getTitle().equals(msg.getString("Helpmenu.helpmenu"))) {
            switch (emote) {
                case "\uD83D\uDD28":
                    out = new EmbedBuilder().setTitle(msg.getString("Helpmenu.moderation.title"))
                            .addField(msg.getString("Helpmenu.moderation.ban.title"), msg.getString("Helpmenu.moderation.ban.description"), false)
                            .addField(msg.getString("Helpmenu.moderation.kick.title"), msg.getString("Helpmenu.moderation.kick.description"), false)
                            .addField(msg.getString("Helpmenu.moderation.prefix.title"), msg.getString("Helpmenu.moderation.prefix.description"), false)
                            .addField(msg.getString("Helpmenu.moderation.verification.pre"), "✅", false)
                            .build();
                    break;

                case "\uD83D\uDEE0":
                    out = new EmbedBuilder().setTitle(msg.getString("Helpmenu.tools.title"))
                            .addField(msg.getString("Helpmenu.tools.github.title"), msg.getString("Helpmenu.tools.github.description"), true)
                            .addField(msg.getString("Helpmenu.tools.ping.title"), msg.getString("Helpmenu.tools.ping.description"), true)
                            .addField(msg.getString("Helpmenu.tools.profile.title"), msg.getString("Helpmenu.tools.profile.description"), true)
                            .build();
                    break;

                case "\uD83D\uDC65":
                    out= new EmbedBuilder().setTitle(msg.getString("Helpmenu.usercommands.title"))
                            .addField(msg.getString("Helpmenu.usercommands.bug.title"), msg.getString("Helpmenu.usercommands.bug.description"), true)
                            .addField(msg.getString("Helpmenu.usercommands.help.title"), msg.getString("Helpmenu.usercommands.help.description"), true)
                            .addField(msg.getString("Helpmenu.usercommands.language.title"), msg.getString("Helpmenu.usercommands.language.description"), true)
                            .addField(msg.getString("Helpmenu.usercommands.levelmessage.title"), msg.getString("Helpmenu.usercommands.levelmessage.description"), true)
                            .addField(msg.getString("Helpmenu.usercommands.say.title"), msg.getString("Helpmenu.usercommands.say.description"), true)
                            .addField(msg.getString("Helpmenu.usercommands.stats.title"), msg.getString("Helpmenu.usercommands.stats.description"), true)
                            .build();
                    break;
            }
        } else if (embed.getTitle().equals(msg.getString("Helpmenu.moderation.title"))) {
            switch (emote) {
                case "✅":
                    out = new EmbedBuilder().setTitle(msg.getString("Helpmenu.moderation.verification.title"))
                            .addField("Usage", "gb.verification", false)
                            .addField("Description", "Set up a Verfixation Message", false)
                            .build();
                    break;
            }
        }
        System.out.println(embed.getTitle());
        if (embed.getTitle().replaceFirst(msg.getString("Helpmenu.helpmenu.mod.title"), "").replaceFirst(msg.getString("Helpmenu.helpmenu.tools.title"), "").replaceFirst(msg.getString("Helpmenu.helpmenu.usercommands.title"), "").equals(msg.getString("Helpmenu.helpmenu")+" - ")&&emote.equals("\uD83D\uDD19")) {
                out = new EmbedBuilder().setTitle(msg.getString("Helpmenu.helpmenu")).setDescription(msg.getString("Helpmenu.helpmenu.description"))
                        .addField(msg.getString("Helpmenu.helpmenu.mod.title"), "\uD83D\uDD28", true)
                        .addField(msg.getString("Helpmenu.helpmenu.tools.title"), "\uD83D\uDEE0", true)
                        .addField(msg.getString("Helpmenu.helpmenu.usercommands.title"), "\uD83D\uDC65", true)
                        .build();


        } else if (emote.equals("◀")&&embed.getTitle().equals(msg.getString("Helpmenu.moderation.verification.title"))) {
            out= MenuHandler.getMessage("\uD83D\uDD28", new EmbedBuilder().setTitle(MessageHandler.get(user).getString("Helpmenu.helpmenu")).build(), user);
        }
        return out;
    }

    public static List<String> getemote(String emote, MessageEmbed embed, User user) {
        ResourceBundle msg = MessageHandler.get(user);
        List<String> list = new ArrayList<>();


        if (embed.getTitle().equals(msg.getString("Helpmenu.helpmenu"))&&emote.equals("\uD83D\uDD19")) {
            list.add("\uD83D\uDD28");
            list.add("\uD83D\uDEE0");
            list.add("\uD83D\uDC65");
        }

        if (embed.getTitle().equals(msg.getString("Helpmenu.moderation.title"))) {
            list.add("✅");
        }

        if (embed.getTitle().contains(msg.getString("Helpmenu.helpmenu")+" - ")) {
            list.add("\uD83D\uDD19");
        }

        if (embed.getTitle().replaceFirst(msg.getString("Helpmenu.helpmenu")+" - ", "").contains(" - ")) {

                list.add("◀");

        }


        return list;
    }


}
