package core;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.MessageReaction;
import net.dv8tion.jda.core.events.message.guild.react.GuildMessageReactionAddEvent;
import stuff.DATA;

import java.awt.*;

public class MenuHandler {

    public static MessageEmbed getMessage(MessageReaction.ReactionEmote emote, GuildMessageReactionAddEvent event) {
        MessageEmbed out=new EmbedBuilder().setTitle("Error").setDescription("Sorry the Emote was not found! Please use ``gb.bug`` Thanks <3").setColor(Color.RED).build();
        System.out.println(emote.getName());
        // Overwiev
        if (event.getChannel().getMessageById(event.getMessageId()).complete().getEmbeds().get(0).getTitle().equals("HelpMenu - Overview")) {
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
        } else if (event.getChannel().getMessageById(event.getMessageId()).complete().getEmbeds().get(0).getTitle().equals("HelpMenu - Moderation")) {
            switch (emote.getName()) {
                case "\uD83D\uDC4A":
                    out = new EmbedBuilder().setTitle("HelpMenu - Ban")
                            .addField("Usage", "gb.ban <@User>", false)
                            .addField("Description", "Bans a User from the Server", false)
                            .build();
                    break;

                case "\uD83D\uDD90":
                    out = new EmbedBuilder().setTitle("HelpMenu - Kick")
                            .addField("Usage", "gb.ban <@User>", false)
                            .addField("Description", "Bans a User from the Server", false)
                            .build();
                    break;
            }
        }

        return out;
    }

}
