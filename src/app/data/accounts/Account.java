package app.data.accounts;

import java.io.Serializable;

public class Account implements Serializable
{
    private static final long serialVersionUID = 1L;
    private final String login;
    private final String password;
    private final String serwer;
    private final String web;

    public Account(String login, String password, String serwer, String web) {
        this.login = login;
        this.password = password;
        this.serwer = serwer;
        this.web = web;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getSerwer() {
        return serwer;
    }

    public String getWeb() {
        return web;
    }


    @Override
    public boolean equals(Object obj) {
        Account a = (Account) obj;

        return this.login.equals(a.getLogin()) && this.serwer.equals(a.getSerwer()) && this.password.equals(a.getPassword());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
