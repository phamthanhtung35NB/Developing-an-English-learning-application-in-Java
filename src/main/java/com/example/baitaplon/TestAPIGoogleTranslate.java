package com.example.baitaplon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class TestAPIGoogleTranslate {

    public static void main(String[] args) throws IOException {
        while (true) {
            String text = "Hello world!";
            Scanner sc = new Scanner(System.in);
            text = sc.nextLine();
            //Translated text: Hallo Welt!
            System.out.println("Translated text: " + translate("vi", "en", text));

        }
    }

    private static String translate(String langFrom, String langTo, String text) throws IOException {
        // INSERT YOU URL HERE
        String urlStr = "https://script.google.com/macros/s/AKfycbyVeA0Z-oCUPgZsH7nIXe-i8h_RrAEWtLJofqHcqbIRvWsPlze6g826UE69BpLUgiU/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
//        System.out.println(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

}