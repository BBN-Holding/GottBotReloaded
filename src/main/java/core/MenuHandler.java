package core;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.MessageReaction;
import net.dv8tion.jda.core.events.message.guild.react.GuildMessageReactionAddEvent;
import stuff.DATA;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MenuHandler {

    public static MessageEmbed getMessage(MessageReaction.ReactionEmote emote, GuildMessageReactionAddEvent event) {
        MessageEmbed out=new EmbedBuilder().setTitle("Error").setDescription("Sorry the Emote was not found! if you are sure that it is a bug use ``gb.bug`` Thanks <3").setColor(Color.RED).build();
        Message message = event.getChannel().getMessageById(event.getMessageId()).complete();
        MessageEmbed embed = message.getEmbeds().get(0);
        // Overwiev
        if (embed.getTitle().equals("HelpMenu - Overview")) {
            switch (emote.getName()) {
                case "\uD83D\uDD28":
                    out = new EmbedBuilder().setTitle("HelpMenu - Moderation")
                            .addField("Ban", "\uD83D\uDC4A", true)
                            .addField("Kick", "\uD83D\uDD90", true)
                            .addField("Prefix", "❗", true)
                            .addField("Verification", "✅", true)
                            .build();
                    break;

                case "\uD83D\uDEE0":
                    out = new EmbedBuilder().setTitle("HelpMenu - Tools")
                            .addField("GitHub", "⌨", true)
                            .addField("Ping", "⏱", true)
                            .addField("Profile", "\uD83E\uDD21", true)
                            .build();
                    break;

                case "\uD83D\uDC65":
                    out= new EmbedBuilder().setTitle("HelpMenu - Usercommands")
                            .addField("Bug", "‼", true)
                            .addField("Help", "❓", true)
                            .addField("Language", "\uD83D\uDE31", true)
                            .addField("Levelmessage", "\uD83D\uDCF3", true)
                            .addField("Say", "\uD83D\uDCEF", true)
                            .addField("Stats", "\uD83D\uDCCB", true)
                            .build();
                    break;
            }
        } else if (embed.getTitle().equals("HelpMenu - Moderation")) {
            switch (emote.getName()) {
                case "\uD83D\uDC4A":
                    out = new EmbedBuilder().setTitle("HelpMenu - Moderation - Ban")
                            .addField("Usage", "gb.ban <@User>", false)
                            .addField("Description", "Bans a User from the Server", false)
                            .build();
                    break;

                case "\uD83D\uDD90":
                    out = new EmbedBuilder().setTitle("HelpMenu - Moderation - Kick")
                            .addField("Usage", "gb.ban <@User>", false)
                            .addField("Description", "Kick a User from the Server", false)
                            .build();
                    break;
                case "❗":
                    out = new EmbedBuilder().setTitle("HelpMenu - Moderation - Prefix")
                            .addField("Usage", "gb.prefix <new Prefix>", false)
                            .addField("Description", "edit/see the Prefix", false)
                            .build();
                    break;
                case "✅":
                    out = new EmbedBuilder().setTitle("HelpMenu - Moderation - Verification")
                            .addField("Usage", "gb.verification", false)
                            .addField("Description", "Set up a Verfixation Message", false)
                            .build();
                    break;
            }
        }
        if (embed.getTitle().contains("HelpMenu -")&&emote.getName().equals("\uD83D\uDD19")) {
                System.out.println("ok");
                out = new EmbedBuilder().setTitle("HelpMenu - Overview").setDescription("Please react with the Emotes")
                        .addField("Moderation", "\uD83D\uDD28", true)
                        .addField("Tools", "\uD83D\uDEE0", true)
                        .addField("Usercommands", "\uD83D\uDC65", true)
                        .build();

        }
        System.out.println(embed.getTitle());
        return out;
    }

    public static List<String> getemote(MessageReaction.ReactionEmote emote, GuildMessageReactionAddEvent event) {
        List<String> list = new ArrayList<>();
        Message message = event.getChannel().getMessageById(event.getMessageId()).complete();
        MessageEmbed embed = message.getEmbeds().get(0);
            switch (emote.getName()) {
                // Mod
                case "\uD83D\uDD28":
                    list.add("\uD83D\uDC4A");
                    list.add("\uD83D\uDD90");
                    list.add("❗");
                    list.add("✅");
                    break;
                // Tools
                case "\uD83D\uDEE0":
                    list.add("⌨");
                    list.add("⏱");
                    list.add("\uD83E\uDD21");
                    break;
                // UserCommands
                case "\uD83D\uDC65":
                    list.add("‼");
                    list.add("❓");
                    list.add("\uD83D\uDE31");
                    list.add("\uD83D\uDCF3");
                    list.add("\uD83D\uDCEF");
                    list.add("\uD83D\uDCCB");
                    break;
            }
        if (embed.getTitle().contains("HelpMenu - ")&&emote.getName().equals("\uD83D\uDD19")) {
                list.add("\uD83D\uDD28");
                list.add("\uD83D\uDEE0");
                list.add("\uD83D\uDC65");
        }
        if (!embed.getTitle().equals("HelpMenu - Overview")) {
            list.add("\uD83D\uDD19");
        }


        return list;
    }

}
