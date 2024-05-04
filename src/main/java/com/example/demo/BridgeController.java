package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class BridgeController {
    @GetMapping("/svc/offcam/pub/FoodInfo")
    public String getFoodInfo(@RequestParam int page,
                              @RequestParam String AUTH_KEY) throws IOException {
        StringBuilder rawMenu = new StringBuilder();

        String apiUrl = "https://api.cnu.ac.kr/svc/offcam/pub/FoodInfo?page="+page+"&AUTH_KEY="+AUTH_KEY;

        // URL 생성
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // 응답 코드 확인
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) response.append(line);

            reader.close();
            rawMenu.append(response);
        }

        return rawMenu.toString();
    }

    @GetMapping("/svc/offcam/pub/WifiAllInfo")
    public String getFoodInfo(@RequestParam String AUTH_KEY) throws IOException {
        String apiUrl = "https://api.cnu.ac.kr/svc/offcam/pub/WifiAllInfo?AUTH_KEY=" + AUTH_KEY;

        // URL 생성
        URL url = new URL(apiUrl);
        // HttpURLConnection 설정
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // 응답 코드 확인
        int responseCode = connection.getResponseCode();
        String json = new String();

        // 응답 내용 읽기
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            json = response.toString();
        }

        return json;
    }
}
