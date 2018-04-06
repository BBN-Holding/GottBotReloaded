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

    public static MessageEmbed getMessage(String emote, MessageEmbed embed) {
        MessageEmbed out=new EmbedBuilder().setTitle("Error").setDescription("Sorry the Emote was not found! if you are sure that it is a bug use ``gb.bug`` Thanks <3").setColor(Color.RED).build();
        // Overview
        if (embed.getTitle().equals("HelpMenu")) {
            switch (emote) {
                case "\uD83D\uDD28":
                    out = new EmbedBuilder().setTitle("HelpMenu - Moderation")
                            .addField("gb.ban <@User>", "Bans the User from the Guild [Ban Permission required]", false)
                            .addField("gb.kick <@User>", "Kicks the User from the Guild [Kick Permission required]", false)
                            .addField("gb.prefix <New Prefix>", "Edit the Prefix from the Bot [Manage Server Permission required]", false)
                            .addField("Verification", "✅", false)
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
            switch (emote) {
                case "✅":
                    out = new EmbedBuilder().setTitle("HelpMenu - Moderation - Verification")
                            .addField("Usage", "gb.verification", false)
                            .addField("Description", "Set up a Verfixation Message", false)
                            .build();
                    break;
            }
        } else if (embed.getTitle().equals("HelpMenu - Tools")) {
            switch (emote) {

                case "⌨":
                    out = new EmbedBuilder().setTitle("HelpMenu - Tools - Github")
                            .addField("Usage", "gb.github <GitHub Name>", false)
                            .addField("Description", "Link your Github Account. Then your Accountname is in your ``gb.profile``", false)
                            .build();
                    break;

                case "⏱":
                    out = new EmbedBuilder().setTitle("HelpMenu - Tools - Ping")
                            .addField("Usage", "gb.ping", false)
                            .addField("Description", "Shows the Bots Ping", false)
                            .build();
                    break;

                case "\uD83E\uDD21":
                    out = new EmbedBuilder().setTitle("HelpMenu - Tools - Profie")
                            .addField("Usage", "gb.profile [@User (optional)]", false)
                            .addField("Description", "Show Your/Users Profile", false)
                            .build();
                    break;

            }
        } else if (embed.getTitle().equals("HelpMenu - Usercommands")) {
            switch (emote) {
                case "‼":
                    out = new EmbedBuilder().setTitle("HelpMenu - Usercommands - Bug")
                            .addField("Usage", "gb.bug <Bug (min 3 args)>", false)
                            .addField("Description", "Report a Bug to the Developers", false)
                            .build();
                    break;
                case "❓":
                    out= new EmbedBuilder().setTitle("HelpMenu - Usercommands - Help")
                            .addField("Usage", "gb.help", false)
                            .addField("Description", "Open the HelpMenu", false)
                            .build();
                    break;
                case "\uD83D\uDE31":
                    out = new EmbedBuilder().setTitle("HelpMenu - Usercommands - Help")
                            .addField("Usage", "gb.language <en|de|...> (try gb.language list)",false)
                            .addField("Description", "Change your Language", false)
                            .build();
                    break;
                case "\uD83D\uDCF3":
                    out = new EmbedBuilder().setTitle("HelpMenu - Usercommands - Levelmessage")
                            .addField("Usage", "gb.levelmessage <true|false>", false)
                            .addField("Description", "Toggle the Levelupmessage", false)
                            .build();
                    break;
                case "\uD83D\uDCEF":
                    out = new EmbedBuilder().setTitle("HelpMenu - Usercommands - Say")
                            .addField("Usage", "gb.say <#textchannel> <Title> <Message>", false)
                            .addField("Description", "write a EmbedMessage in the Mentioned Channel", false)
                            .build();
                    break;
                case "\uD83D\uDCCB":
                    out = new EmbedBuilder().setTitle("HelpMenu - Usercommands - Stats")
                            .addField("Usage", "gb.stats", false)
                            .addField("Description", "Show you Stats from the GottBot", false)
                            .build();
                    break;
            }
        }
        if (embed.getTitle().contains("HelpMenu - Moderation - ")&&emote.equals("◀")) {
            out = getMessage("\uD83D\uDD28", new EmbedBuilder().setTitle("HelpMenu").build());
        } else if (embed.getTitle().contains("HelpMenu - Tools - ")&&emote.equals("◀")) {
            out = getMessage("\uD83D\uDEE0", new EmbedBuilder().setTitle("HelpMenu").build());
        } else if (embed.getTitle().contains("HelpMenu - Usercommands - ")&&emote.equals("◀")) {
            out = getMessage("\uD83D\uDC65", new EmbedBuilder().setTitle("HelpMenu").build());
        }

        if (embed.getTitle().replaceFirst("Moderation", "").replaceFirst("Tools", "").replaceFirst("Usercommands", "").equals("HelpMenu - ")&&emote.equals("\uD83D\uDD19")) {
                out = new EmbedBuilder().setTitle("HelpMenu").setDescription("Please react with the Emotes. Use the :arrow_backward: or the :back: emoji to see the overview")
                        .addField("Moderation", "\uD83D\uDD28", true)
                        .addField("Tools", "\uD83D\uDEE0", true)
                        .addField("Usercommands", "\uD83D\uDC65", true)
                        .build();


        }
        return out;
    }

    public static List<String> getemote(String emote, MessageEmbed embed) {
        List<String> list = new ArrayList<>();

            switch (emote) {
                // Mod
                case "\uD83D\uDD28":
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

 
        if (embed.getTitle().equals("HelpMenu")&&emote.equals("\uD83D\uDD19")) {
            list.add("\uD83D\uDD28");
            list.add("\uD83D\uDEE0");
            list.add("\uD83D\uDC65");
        } else if (embed.getTitle().equals("HelpMenu - Moderation")&&emote.equals("◀")) {

                list = getemote("\uD83D\uDD28", new EmbedBuilder().setTitle("HelpMenu -").build());
        } else if (embed.getTitle().equals("HelpMenu - Tools")&&emote.equals("◀")) {
                list = getemote("\uD83D\uDEE0", new EmbedBuilder().setTitle("HelpMenu -").build());
        } else if (embed.getTitle().equals("HelpMenu - Usercommands")&&emote.equals("◀")) {
            list = getemote("\uD83D\uDC65", new EmbedBuilder().setTitle("HelpMenu -").build());
        }


        if (embed.getTitle().contains("HelpMenu -")) {
            list.add("\uD83D\uDD19");
        }

        if (embed.getTitle().replaceFirst("HelpMenu - ", "").contains(" - ")) {

                list.add("◀");

        }


        return list;
    }


}
