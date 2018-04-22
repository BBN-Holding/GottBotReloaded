package GB;

public class Handler {
    public MySQL getMySQL() {
        return new MySQL();
    }
    public Error getError() {
        return new Error();
    }
    public MenuHandler getMenuHandler() {
        return new MenuHandler();
    }
    public MessageHandler getMessageHandler() {
        return new MessageHandler();
    }
}

