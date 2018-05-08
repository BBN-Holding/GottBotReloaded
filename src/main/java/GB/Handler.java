package GB;

public class Handler {
    public Rethink getMySQL() {
        return new Rethink();
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

