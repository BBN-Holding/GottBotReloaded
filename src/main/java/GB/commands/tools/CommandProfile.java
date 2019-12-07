package GB.commands.tools;

import GB.Handler;
import GB.MessageHandler;
import GB.stuff.SECRETS;
import GB.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.JSONObject;
import GB.stuff.DATA;

import java.time.format.DateTimeFormatter;

import static GB.core.JSONhandler.readJsonFromUrl;

public class CommandProfile implements Command {
    private String Nick;
    private String Game;
    private String Punkte;
    private String Level;
    private String Progress;
    private String ProgressMax;
    private int TempProgress;
    private int viertel;
    private String LevelPlus;

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
            Guild guild = event.getGuild();
            if (user.getGame() == null) Game = new Handler().getMessageHandler().get("tools.profile.nogame", user.getUser(), guild);
            else Game = "" + user.getGame().getName();
            if (user.getNickname() == null) Nick = new Handler().getMessageHandler().get("tools.profile.nonick", user.getUser(), guild);
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

            String withdrawn = new Handler().getMySQL().get("user", "id", user.getUser().getId(), "withdrawnhashes");
            String hashes = new Handler().getMySQL().get("user", "id", user.getUser().getId(), "hashes");

            Punkte = new Handler().getMySQL().get("user", "ID", user.getUser().getId(), "xp");
            Level = new Handler().getMySQL().get("user", "ID", user.getUser().getId(), "level");
            TempProgress = Integer.parseInt(new Handler().getMySQL().get("user", "ID", user.getUser().getId(), "level")) + 1;
            viertel = Integer.parseInt(new Handler().getMySQL().get("lvl", "lvl", String.valueOf(TempProgress), "xp")) / 8;
            ProgressMax = new Handler().getMySQL().get("lvl", "lvl", String.valueOf(TempProgress), "xp");
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
            MessageHandler messageHandler = new Handler().getMessageHandler();
            String Github = new Handler().getMySQL().get("user", "ID", user.getUser().getId(), "github");
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(messageHandler.get("tools.profile.title", user.getUser(), guild))
                    .addField(messageHandler.get("tools.profile.1", user.getUser(), guild), user.getUser().getName(), false)
                    .addField(messageHandler.get("tools.profile.2", user.getUser(), guild), Nick, false)
                    .addField(messageHandler.get("tools.profile.3", user.getUser(), guild), Game, false)
                    .addField(messageHandler.get("tools.profile.4", user.getUser(), guild), Rollen, false)
                    .addField(messageHandler.get("tools.profile.5", user.getUser(), guild), user.getJoinDate().format(DateTimeFormatter.ofPattern("dd.MM.yy, HH:mm:ss")), false)
                    .addField(messageHandler.get("tools.profile.6", user.getUser(), guild), event.getMessage().getAuthor().getCreationTime().format(DateTimeFormatter.ofPattern("dd.MM.yy, HH:mm:ss")), false)
                    .addField(messageHandler.get("tools.profile.7", user.getUser(), guild), user.getOnlineStatus().toString(), false)
                    .addField(messageHandler.get("tools.profile.8", user.getUser(), guild), Github, false)
                    .addField(messageHandler.get("tools.profile.9", user.getUser(), guild), Level, false)
                    .addField(messageHandler.get("tools.profile.10", user.getUser(), guild), Punkte, false)
                    .addField(messageHandler.get("tools.profile.11", user.getUser(), guild), Progress, false)
                    //.addField(messageHandler.get("tools.profile.12", user.getUser(), guild), String.valueOf(mined), false)
                    .addField(messageHandler.get("tools.profile.13", user.getUser(), guild), withdrawn, false)
                    //.addField(messageHandler.get("tools.profile.14", user.getUser(), guild), String.valueOf(mined-Long.parseLong(withdrawn)), false)
                    .addField(messageHandler.get("tools.profile.15", user.getUser(), guild), hashes, false)
                    .setColor(java.awt.Color.CYAN).setThumbnail(user.getUser().getAvatarUrl()).build()).queue();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}
