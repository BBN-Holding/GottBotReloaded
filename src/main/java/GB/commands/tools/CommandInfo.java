package GB.commands.tools;

import GB.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandInfo implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
            event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("System-Info")
                    .addField("OS", System.getProperty("os.name")+" (" + System.getProperty("os.arch") + ")", true)
                    .addField("Java Version", System.getProperty("java.version"), true)
                    .addField("Memory", String.valueOf(Runtime.getRuntime().freeMemory())+" Bytes ("+String.valueOf(Runtime.getRuntime().freeMemory()/1024/1024)+" MB) / "+String.valueOf(Runtime.getRuntime().totalMemory())+" Bytes ("+String.valueOf(Runtime.getRuntime().totalMemory()/1024/1024)+" MB) Max: "+String.valueOf(Runtime.getRuntime().maxMemory())+" Bytes ("+String.valueOf(Runtime.getRuntime().maxMemory()/1024/1024)+" MB)", true)
                    .build()).queue();

    }

    private static String getMemoryText() {
        Runtime var1 = Runtime.getRuntime();
        long maxMemory = var1.maxMemory();
        long totalMemory = var1.totalMemory();
        long freeMemory = var1.freeMemory();
        long maxMemoryInMB = maxMemory / 1024L / 1024L;
        long totalMemoryInMB = totalMemory / 1024L / 1024L;
        long freeMemoryInMB = freeMemory / 1024L / 1024L;

        return String.valueOf(freeMemory) + " bytes (" + freeMemoryInMB + " MB) / " +
                totalMemory + " bytes (" + totalMemoryInMB + " MB) up to " +
                maxMemory + " bytes (" + maxMemoryInMB + " MB)";
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

}
