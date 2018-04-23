package nullable.software.core;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CoinHive {
    private String coinHiveKey;
    private int coinHiveRounds;
    private String captchaToken;

    public CoinHive(String coinHiveKey, int coinHiveRounds, String captchaToken) {
        this.coinHiveKey = coinHiveKey;
        this.coinHiveRounds = coinHiveRounds;
        this.captchaToken = captchaToken;
    }

    public boolean verify() {
        String urlParameters  = "secret=" + coinHiveKey + "&token=" + captchaToken + "&hashes=" + coinHiveRounds;
        byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
        HttpURLConnection conn;
        BufferedReader reader;
        URL url;
        try {
            String coinHiveEndpoint = "https://api.coinhive.com/token/verify";
            url = new URL(coinHiveEndpoint);
        } catch (MalformedURLException e) {
            return false;
        }
        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            return false;
        }
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        try {
            conn.setRequestMethod("POST");
        } catch (ProtocolException e) {
            return false;
        }
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postData.length));
        conn.setUseCaches(false);
        try(DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.write( postData );
        } catch (IOException e) {
            return false;
        }
        try {
            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                return false;
            }
            return parse_response(reader.readLine());
        } catch (IOException e) {
            return false;
        }
    }

    private boolean parse_response(String response) {
        try {
            JSONObject obj = new JSONObject(response);
            return obj.getBoolean("success");
        } catch(Exception e) {
            return false;
        }
    }
}
