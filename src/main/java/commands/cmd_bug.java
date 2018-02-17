package commands;


import util.EmbedUtil;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.EmbedBuilder;

public class cmd_bug implements Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }


    @Override
    public Message action(String[] args, MessageReceivedEvent event) {
        if (args.length < 3) {
            return new MessageBuilder().setEmbed(EmbedUtil.info("Usage", "bug [message] (min. 3 args)").build()).build();
        }
        String text = event.getMessage().getContentRaw();
        event.getJDA().getTextChannelById("411641204002783232").sendMessage(
                new EmbedBuilder()
                        .setAuthor(event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator(), null, event.getAuthor().getAvatarUrl())
                        .setDescription("**New Bug Detected!**\n```fix\n" + text + "```")
                        .build()
        ).queue();
        //User Feedback
        return new MessageBuilder().setEmbed(EmbedUtil.success("Bug reported", "Successfully send the bug to the developers.").build()).build();


    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
