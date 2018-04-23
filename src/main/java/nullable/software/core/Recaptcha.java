package nullable.software.core;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Recaptcha {
    private String recaptchaEndpoint = "https://www.google.com/recaptcha/api/siteverify";
    private String recaptchaSecret;
    private String recaptchaResponse;

    public Recaptcha(String recaptchaSecret, String recaptchaResponse) {
        this.recaptchaSecret = recaptchaSecret;
        this.recaptchaResponse = recaptchaResponse;
    }

    public boolean verify() {
        URL url;
        HttpsURLConnection conn;
        OutputStream os;
        BufferedWriter writer;
        try {
            url = new URL(recaptchaEndpoint);
        } catch (MalformedURLException e) {
            return false;
        }
        try {
            conn = (HttpsURLConnection) url.openConnection();
        } catch (IOException e) {
            return false;
        }
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        try {
            conn.setRequestMethod("POST");
        } catch (ProtocolException e) {
            return false;
        }
        conn.setDoInput(true);
        conn.setDoOutput(true);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("secret", this.recaptchaSecret));
        params.add(new BasicNameValuePair("response", this.recaptchaResponse));
        try {
            os = conn.getOutputStream();
        } catch (IOException e) {
            return false;
        }
        try {
            writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return false;
        }
        try {
            writer.write(getQuery(params));
        } catch (IOException e) {
            return false;
        }
        try {
            writer.flush();
        } catch (IOException e) {
            return false;
        }
        try {
            writer.close();
        } catch (IOException e) {
            return false;
        }
        try {
            os.close();
        } catch (IOException e) {
            return false;
        }
        try {
            conn.connect();
        } catch (IOException e) {
            return false;
        }
        return false;
    }

    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params)
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
