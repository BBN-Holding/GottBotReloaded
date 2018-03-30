package commands.tools;

import commands.Command;
import core.MessageHandler;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.json.JSONObject;
import stuff.DATA;
import stuff.SECRETS;

import java.awt.*;
import java.time.format.DateTimeFormatter;

import static core.JSONhandler.readJsonFromUrl;

public class CommandProfile implements Command {
    String Nick;
    String Game;
    Member user;
    String useruser;
    String Punkte;
    String Level;
    String Progress;
    String ProgressMax;
    int TempProgress;
    int viertel;
    String LevelPlus;

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        try {
            Member user;
            if (event.getMessage().getMentionedMembers().size() == 1) {
                user = event.getMessage().getMentionedMembers().get(0);
            } else user = event.getMember();
            if (user.getGame() == null) Game = MessageHandler.get(event.getAuthor()).getString("profilegame");
            else Game = "" + user.getGame().getName();
            if (user.getNickname() == null) Nick = MessageHandler.get(event.getAuthor()).getString("profilenick");
            else Nick = user.getNickname();
            int i = 0;
            String Rollen = "";
            int end = user.getRoles().size() - 1;
            while (i < user.getRoles().size()) {
                if (i < end) {
                    Rollen += user.getRoles().get(i).getName() + ", ";
                    i++;
                } else {
                    Rollen += user.getRoles().get(i).getName();
                    i++;
                }
            }

            JSONObject json = readJsonFromUrl("https://api.coinhive.com/user/balance?name=" + user.getUser().getId() + "&secret=" + SECRETS.COINHIVESECRET);
            Long mined = json.getLong("total");
            String withdrawn = MySQL.get("user", "id", user.getUser().getId(), "withdrawnhashes");
            String hashes = MySQL.get("user", "id", user.getUser().getId(), "hashes");

            Punkte = MySQL.get("user", "ID", user.getUser().getId(), "xp");
            Level = MySQL.get("user", "ID", user.getUser().getId(), "level");
            TempProgress = Integer.parseInt(MySQL.get("user", "ID", user.getUser().getId(), "level")) + 1;
            viertel = Integer.parseInt(MySQL.get("lvl", "lvl", String.valueOf(TempProgress), "xp")) / 8;
            ProgressMax = MySQL.get("lvl", "lvl", String.valueOf(TempProgress), "xp");
            LevelPlus = (Integer.parseInt(Level) + 1) + "";
            //unter 25% viertel=2
            String mid_full = event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full", true).get(0).getAsMention();
            String mid_empty = event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty", true).get(0).getAsMention();
            if (viertel > Integer.parseInt(Punkte)) {
                Progress = event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_start_empty", true).get(0).getAsMention() + "" +
                        mid_empty+mid_empty+mid_empty+mid_empty+mid_empty+mid_empty + "" +
                        event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_end_empty", true).get(0).getAsMention();
            } else if (((viertel * 2) > Integer.parseInt(Punkte)) && (viertel <= Integer.parseInt(Punkte))) {
                Progress = event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_start_full", true).get(0).getAsMention() + "" +
                        mid_empty+mid_empty+mid_empty+mid_empty+mid_empty+mid_empty+
                        event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_end_empty", true).get(0).getAsMention();
            } else if (((viertel * 3) > Integer.parseInt(Punkte)) && ((viertel * 2) <= Integer.parseInt(Punkte))) {
                Progress = event.getGuild().getEmotesByName("progbar_start_full", true).get(0).getAsMention() + "" +
                        mid_full +
                        mid_empty+mid_empty+mid_empty+mid_empty+mid_empty+
                        event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_end_empty", true).get(0).getAsMention();
            } else if (((viertel * 4) > Integer.parseInt(Punkte)) && ((viertel * 3) <= Integer.parseInt(Punkte))) {
                Progress = event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_start_full", true).get(0).getAsMention() + "" +
                        mid_full+mid_full+
                        mid_empty+mid_empty+mid_empty+mid_empty+
                        event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_end_empty", true).get(0).getAsMention();
            } else if (((viertel * 5) > Integer.parseInt(Punkte)) && ((viertel * 4) <= Integer.parseInt(Punkte))) {
                Progress = event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_start_full", true).get(0).getAsMention() + "" +
                        mid_full+mid_full+mid_full+
                        mid_empty+mid_empty+mid_empty+
                        event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_end_empty", true).get(0).getAsMention();
            } else if (((viertel * 6) > Integer.parseInt(Punkte)) && ((viertel * 5) <= Integer.parseInt(Punkte))) {
                Progress = event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_start_full", true).get(0).getAsMention() + "" +
                        mid_full+mid_full+mid_full+mid_full +
                        mid_empty+mid_empty+
                        event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_end_empty", true).get(0).getAsMention();
            } else if (((viertel * 7) > Integer.parseInt(Punkte)) && ((viertel * 6) <= Integer.parseInt(Punkte))) {
                Progress = event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_start_full", true).get(0).getAsMention() + "" +
                        mid_full+mid_full+mid_full+mid_full+mid_full+
                        mid_empty+
                        event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_end_empty", true).get(0).getAsMention();
            } else if (((viertel * 8) > Integer.parseInt(Punkte)) && ((viertel * 7) <= Integer.parseInt(Punkte))) {
                Progress = event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_start_full", true).get(0).getAsMention() + "" +
                        mid_full+mid_full+mid_full+mid_full+mid_full+mid_full+
                        event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_end_empty", true).get(0).getAsMention();
            } else if (ProgressMax.equals(Punkte)) {
                Progress = event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_start_full", true).get(0).getAsMention() + "" +
                        mid_full+mid_full+mid_full+mid_full+mid_full+mid_full+
                        event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_end_full", true).get(0).getAsMention();
            }
            String Github = MySQL.get("user", "ID", user.getUser().getId(), "github");
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("profiletitel"))
                    .addField("Name", user.getUser().getName(), false)
                    .addField("Nickname", Nick, false)
                    .addField("Game", Game, false)
                    .addField("Roles", Rollen, false)
                    .addField("Joined", user.getJoinDate().format(DateTimeFormatter.ofPattern("dd.MM.yy, HH:mm:ss")), false)
                    .addField("Created", event.getMessage().getAuthor().getCreationTime().format(DateTimeFormatter.ofPattern("dd.MM.yy, HH:mm:ss")), false)
                    .addField("Status", user.getOnlineStatus().toString(), false)
                    .addField("GitHub", Github, false)
                    .addField("Level", Level, false)
                    .addField("XP", Punkte, false)
                    .addField("Levelprogress", Progress, false)
                    .addField("Hashes mined", String.valueOf(mined), false)
                    .addField("Withdrawn", withdrawn, false)
                    .addField("Hashes", hashes, false)
                    .setColor(Color.CYAN).setThumbnail(user.getUser().getAvatarUrl()).build()).queue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}