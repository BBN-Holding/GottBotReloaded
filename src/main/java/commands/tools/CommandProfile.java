package commands.tools;

import commands.Command;
import core.MessageHandler;
import core.MySQL;
import core.UserSQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import stuff.DATA;

import java.awt.*;
import java.time.format.DateTimeFormatter;

public class CommandProfile implements Command {
    private String Nick;
    private String Game;
    private Member user;
    private String useruser;
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
            useruser = args[0].replace("<", "").replace("@", "").replace(">", "").replace("!","");
            user = event.getGuild().getMemberById(useruser);
            if (useruser.equals(event.getMember().getUser().getId())) {
                event.getTextChannel().sendMessage("Was bringt es sich selbst zu hinzuschreiben?? egal... mach es nächstes mal einfach mit gb.CommandProfile :wink: ").queue();
            }

        }catch ( ArrayIndexOutOfBoundsException e) {
            user = event.getMember();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user.getGame() == null) Game = MessageHandler.get(event.getAuthor()).getString("profilegame");
        else Game  = ""+user.getGame().getName();
        if (user.getNickname() == null) Nick = MessageHandler.get(event.getAuthor()).getString("profilenick");
        else Nick = user.getNickname();
        int i=0;
        String Rollen="";
        int end = user.getRoles().size()-1;
        while (i<user.getRoles().size()) {
            if (i<end) {
                Rollen += user.getRoles().get(i).getName() + ", ";
                i++;
            } else {
                Rollen += user.getRoles().get(i).getName();
                i++;
            }
        }

        try {
                Punkte=MySQL.get("user", "ID", user.getUser().getId(), "xp");
                Level=MySQL.get("user", "ID", user.getUser().getId(), "level");
                TempProgress=Integer.parseInt(MySQL.get("user", "ID", user.getUser().getId(),"level"))+1;
                    viertel=Integer.parseInt(MySQL.get("lvl", "lvl", String.valueOf(TempProgress), "xp"))/8;
                    ProgressMax=MySQL.get("lvl", "lvl", String.valueOf(TempProgress), "xp");
                    LevelPlus=(Integer.parseInt(Level)+1)+"";
                    //unter 25% viertel=2
                    if (viertel>Integer.parseInt(Punkte)) {
                        Progress=event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_start_empty",true).get(0).getAsMention()+"" +
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_end_empty",true).get(0).getAsMention();
                    } else if (((viertel*2)>Integer.parseInt(Punkte))&&(viertel<=Integer.parseInt(Punkte))) {
                        Progress=event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_start_full",true).get(0).getAsMention()+"" +
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_end_empty",true).get(0).getAsMention();
                    } else if (((viertel*3)>Integer.parseInt(Punkte))&&((viertel*2)<=Integer.parseInt(Punkte))) {
                        Progress=event.getGuild().getEmotesByName("progbar_start_full",true).get(0).getAsMention()+"" +
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_end_empty",true).get(0).getAsMention();
                    } else if (((viertel*4)>Integer.parseInt(Punkte))&&((viertel*3)<=Integer.parseInt(Punkte))) {
                        Progress=event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_start_full",true).get(0).getAsMention()+"" +
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_end_empty",true).get(0).getAsMention();
                    } else if (((viertel*5)>Integer.parseInt(Punkte))&&((viertel*4)<=Integer.parseInt(Punkte))) {
                        Progress=event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_start_full",true).get(0).getAsMention()+"" +
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_end_empty",true).get(0).getAsMention();
                    } else if (((viertel*6)>Integer.parseInt(Punkte))&&((viertel*5)<=Integer.parseInt(Punkte))) {
                        Progress=event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_start_full",true).get(0).getAsMention()+"" +
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_end_empty",true).get(0).getAsMention();
                    } else if (((viertel*7)>Integer.parseInt(Punkte))&&((viertel*6)<=Integer.parseInt(Punkte))) {
                        Progress=event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_start_full",true).get(0).getAsMention()+"" +
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_empty",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_end_empty",true).get(0).getAsMention();
                    } else if (((viertel*8)>Integer.parseInt(Punkte))&&((viertel*7)<=Integer.parseInt(Punkte))) {
                    Progress=event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_start_full",true).get(0).getAsMention()+"" +
                            event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                            event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                            event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                            event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                            event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                            event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                            event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_end_empty",true).get(0).getAsMention();
                    } else if (ProgressMax.equals(Punkte)) {
                        Progress=event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_start_full",true).get(0).getAsMention()+"" +
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_mid_full",true).get(0).getAsMention()+""+
                                event.getJDA().getGuildById(DATA.BBNS).getEmotesByName("progbar_end_full",true).get(0).getAsMention();
                    }
        } catch (Exception e) {
            e.printStackTrace();
        }

        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(MessageHandler.get(event.getAuthor()).getString("profiletitel"))
                .addField("Name", user.getUser().getName(),true)
                .addField("Nickname", Nick, true)
                .addField("Game", Game, true)
                .addField("Roles", Rollen, true)
                .addField("Joined", user.getJoinDate().format(DateTimeFormatter.ofPattern("dd.MM.yy, HH:mm:ss")), true)
                .addField("Created", event.getMessage().getAuthor().getCreationTime().format(DateTimeFormatter.ofPattern("dd.MM.yy, HH:mm:ss")), true)
                .addField("Status", user.getOnlineStatus().toString(), true)
                .addField("GitHub", MySQL.get("user", "ID", user.getUser().getId(), "github"), true)
                .addField("Premium", (UserSQL.isPremium(useruser)) ? "has premium" : "has no premium", true)
                .addField("Level", Level, true)
                .addField("XP", Punkte, true)
                .addField("Levelprogress", Progress, true)
                .setColor(Color.CYAN).setThumbnail(user.getUser().getAvatarUrl()).build()).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}
