package client;

public class Client {
    private final String login;
    private final String password;

    public Client(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }
}
