/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author imalhasaranga
 */
public class CreateProxy {

    private String url;
    private String port;
    private String username;
    private String password;

    public CreateProxy(String URL, String port, String username, String password) {
        this.url = URL;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public void createNormalProxy() {


        System.setProperty("http.proxySet", "true");
        System.setProperty("java.net.useSystemProxies", "true");
        System.setProperty("http.proxyHost", url);
        System.setProperty("http.proxyPort", port);
        System.setProperty("http.proxyUser", username);
        System.setProperty("http.proxyPassword", password);

    }

    public void CreateAuthProxy() {



        System.setProperty("http.proxySet", "true");
        System.setProperty("java.net.useSystemProxies", "true");
        System.setProperty("http.proxyHost", url);
        System.setProperty("http.proxyPort", port);
        final String authUser = username;
        final String authPassword = password;


        Authenticator.setDefault(
                new Authenticator() {
                    @Override
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                authUser, authPassword.toCharArray());
                    }
                });
        System.setProperty("http.proxyUser", authUser);
        System.setProperty("http.proxyPassword", authPassword);


       


    }

    public void DisableProxy() {
        System.getProperties().put("proxySet", "false");
        System.getProperties().put("http.proxySet", "false");
        try {
            System.setProperty("http.proxyHost", null);
        } catch (Exception e) {
        }
    }
}
