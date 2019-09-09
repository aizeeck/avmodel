package browser;

import config.Configuration;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import units.VirtaContext;

import java.io.IOException;
import java.util.Map;

public class Browser {
    private static final String LOGIN_URL = Configuration.getInstance().getProperty("LOGIN_PAGE");
    private static final String VMAINHOST = Configuration.getInstance().getProperty("VMAINHOST");
    static Map<String, String> cookies;

    public Document getPage (String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url)
                    .cookies(cookies)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    public Document getPage (String url, String ref) {
        Document document = null;
        try {
            document = Jsoup.connect(url)
                    .referrer(ref)
                    .cookies(cookies)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    public boolean login(String username, String password) {
        Document document;
        Connection.Response response;

        try {
            response = Jsoup.connect(LOGIN_URL).execute();
            cookies = response.cookies();
            response = Jsoup.connect(LOGIN_URL)
                    .data(
                        "userData[login]",      username,
                        "userData[password]",   password,
                        "userData[lang]",       "ru")
                    .cookies(cookies)
                    .method(Connection.Method.POST)
                    .execute();
            document = response.parse();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        cookies = response.cookies();

        boolean isLogedIn = document.outerHtml().contains("My company");
        if (isLogedIn) {
            new VirtaContext();
        }
        return isLogedIn;
    }


    public static void sendPost(String url, String ref, Map<String, String> postParams) throws IOException {

        Connection.Response loggedInResponse = Jsoup.connect(url)
                .data(postParams)
                .cookies(cookies)
                .header("Connection", "keep-alive")
                .header("Host", VMAINHOST)
                .header("Referer", ref)
                .method(Connection.Method.POST)
                .execute();
        String s = loggedInResponse.body();
    }
}
