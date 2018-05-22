package GB.Handler;

import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class Logger {
    java.util.logging.Logger logger = java.util.logging.Logger.getLogger("Logger");
    public void setup() {
        try {
            FileHandler fh;
            fh = new FileHandler("Gott.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            log("Logger setup is finished!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void log(String Message) {
        logger.info(Message);
    }

}
