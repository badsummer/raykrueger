package com.example;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * DOCUMENT ME!
 *
 * @author Ray Krueger
 */
public class UrlTest {
    public static void main(String[] args) throws Exception {

        while (true) {
            try {
                Thread.sleep(3000);
                long start = System.currentTimeMillis();
                HttpURLConnection urlConnection = createUrl();
                int code = urlConnection.getResponseCode();
                long time = System.currentTimeMillis() - start;
                System.out.println(code + " (" + time + "ms)");
            } catch (IOException e) {
                System.err.println(e);
            }
        }

    }

    private static HttpURLConnection createUrl() throws IOException {
        URL url = new URL("http://fenco.freeforums.org/");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("HEAD");
        urlConnection.setConnectTimeout(1000);
        urlConnection.setReadTimeout(1000);
        return urlConnection;
    }
}
