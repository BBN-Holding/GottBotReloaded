package GB.commands.tools;

import GB.Handler;
import GB.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandBuilder implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length==0) {
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Hilfe lohl").build()).queue();
        } else {
            switch (args[0].toLowerCase()) {
                case "minecraft":
                        String msg = event.getMessage().getContentRaw().replaceFirst(new Handler().getMessageHandler().getprefix(event.getGuild()),"").replaceFirst("builder minecraft ", "");
                        String[] strings = msg.split("[|]");
                        if (strings.length>=3) {
                            String url = "https://www.minecraftskinstealer.com/achievement/a.php?i=ITEM&h=TITLE&t=TEXT";
                            url = url.replaceFirst("ITEM", strings[0]).replaceFirst("TITLE", strings[1].replaceAll(" ", "+"))
                                    .replaceFirst("TEXT", strings[2].replaceAll(" ", "+"));
                            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Your Minecraft achievement: ").setImage(url).build()).queue();
                        }
                    break;
                case "road":
                    String Message = event.getMessage().getContentRaw().replaceFirst(new Handler().getMessageHandler().getprefix(event.getGuild()),"").replaceFirst("builder road ", "");
                    String[] Woah = Message.split("[|]");
                    if (Woah.length>=3) {
                        String LINK = "http://www.customroadsign.com/generate.php?line1=1LINE&line2=2LINE&line3=3LINE&line4=4LINE";
                        LINK = LINK.replaceFirst("1LINE", Woah[0].replaceAll(" ", "+")).replaceFirst("2LINE", Woah[1].replaceAll(" ", "+"))
                                .replaceFirst("3LINE", Woah[2].replaceAll(" ", "+")).replaceFirst("4LINE", Woah[3].replaceAll(" ", "+"));
                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Your road Sign: ").setImage(LINK).build()).queue();
                    }
                    break;
                case "eye":

                    String Messagelol = event.getMessage().getContentRaw().replaceFirst(new Handler().getMessageHandler().getprefix(event.getGuild()),"").replaceFirst("builder eye ", "");
                    String[] Woahlol = Messagelol.split("[|]");
                        String LINKEY = "http://www.eyechartmaker.com/generate.php?line1=1LINE";
                        LINKEY = LINKEY.replaceFirst("1LINE", Woahlol[0].replaceAll(" ", "+"));
                        System.out.println(LINKEY);
                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Your Eye Test: ").setImage(LINKEY).build()).queue();
                    break;
                case "medal":
                    String Lohl = event.getMessage().getContentRaw().replaceFirst(new Handler().getMessageHandler().getprefix(event.getGuild()),"").replaceFirst("builder medal ", "");
                    String[] AHAHA = Lohl.split("[|]");
                    if (AHAHA.length>=3) {
                        String mirfallsenkeinestringnamenmehrein = "http://www.getamedal.com/generate.php?top1=1LINE&top2=2LINE&top3=3LINE&top4=4LINE&sp=";
                        mirfallsenkeinestringnamenmehrein = mirfallsenkeinestringnamenmehrein.replaceFirst("1LINE", AHAHA[0].replaceAll(" ", "+")).replaceFirst("2LINE", AHAHA[1].replaceAll(" ", "+"))
                                .replaceFirst("3LINE", AHAHA[2].replaceAll(" ", "+")).replaceFirst("4LINE", AHAHA[3].replaceAll(" ", "+"));
                        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Your Medal: ").setImage(mirfallsenkeinestringnamenmehrein).build()).queue();
                    }
                    break;
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
