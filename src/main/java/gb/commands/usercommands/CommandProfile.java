package gb.commands.usercommands;

import gb.GottBot;
import gb.Handler.CommandHandling.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import java.time.format.DateTimeFormatter;

public class CommandProfile implements Command {


    @Override
    public String[] Aliases() {
        return new String[]{"profile", "info"};
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length == 1 && args[0].equals("beta")) {
            try {
                BufferedImage bufferedImage=ImageIO.read(new File("Unbenannt.jpg"));
                Graphics2D graphics2D = bufferedImage.createGraphics();
                // edit Image
                // Name
                graphics2D.setFont(new Font("Dosis", Font.PLAIN, 75));
                graphics2D.drawString(event.getAuthor().getName(), 425, 220);
                graphics2D.setFont(new Font("Dosis", Font.PLAIN, 50));
                graphics2D.drawString("#"+event.getAuthor().getDiscriminator(), 425, 270);
                // Image
                BufferedImage bufferedImage1 = ImageIO.read(new URL(event.getAuthor().getAvatarUrl()).openStream());
                graphics2D.drawImage(bufferedImage1, 220, 75, bufferedImage1.getWidth(), bufferedImage1.getHeight(), null);
                // Build image
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", os);
                event.getTextChannel().sendFile(new ByteArrayInputStream(os.toByteArray()), "test.jpg").queue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // TODO: Translate
            Member member;
            if (event.getMessage().getMentionedMembers().size() == 1) {
                member = event.getMessage().getMentionedMembers().get(0);
            } else {
                member = event.getMember();
            }
            String roles = "";
            if (member.getRoles().size() == 0) roles = "none";
            else {
                for (int i = 0; (member.getRoles().size() - 1) > i; i++) {
                    roles += member.getRoles().get(i).getName() + ", ";
                }
                roles += member.getRoles().get(member.getRoles().size() - 1).getName();
            }
            String nickname;
            if (member.getNickname() == null)
                nickname = "none";
            else nickname = member.getNickname();
            String game;
            if (member.getGame() == null)
                game = "none";
            else game = member.getGame().getName();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss dd.MM.yyyy");
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle(GottBot.getMessage().getString("commands.user.profile.title", event).replace("%member%", member.getUser().getName() + "#" + member.getUser().getDiscriminator()))
                    .addField("Name", member.getUser().getName(), true)
                    .addField("Nickname", nickname, true)
                    .addField("Mention", member.getAsMention(), true)
                    .addField("ID", member.getUser().getId(), true)
                    .addField("Game", game, true)
                    .addField("Joined DiscordServer", member.getJoinDate().format(dateTimeFormatter), true)
                    .addField("Joined Discord", member.getUser().getCreationTime().format(dateTimeFormatter), true)
                    .addField("Onlinestatus", member.getOnlineStatus().getKey(), true)
                    .addField("Roles", roles, true)
                    .setColor(Color.CYAN)
                    .setThumbnail(member.getUser().getAvatarUrl())
                    .build()
            ).queue();
        }
    }
}
