package com.cookandroid.bottom_setting;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
// 새로운 Thread를 이용하여 웹에 정보 요청
public class ApiMemberProfile extends Thread{
    // Class Member 변수 설정
    // 웹에 정보 요청을 위한 변수
    String token;
    String header;
    String apiURL = "https://openapi.naver.com/v1/nid/me";
    // 받아온 정보를 JSONObject 파일로 변환
    JSONObject profile;
    JSONObject response;
    // Class 생성자
    ApiMemberProfile(String token) {
        this.token = token;
        this.header = "Bearer " + token; // Bearer 다음에 공백 추가
    }
    // 웹에 사용자의 Profile 정보 요청
    public void run() {
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        String responseBody = get(apiURL,requestHeaders);

        try {
            profile = new JSONObject(responseBody);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if (profile != null) {
                this.response = (JSONObject) profile.get("response");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    // Class에서 받아온 Profile 정보를 타 Class에 반환
    public JSONObject return_profile() {
        return this.response;
    }

    private String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }
            // 웹어서 Code를 받아와 연결
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                InputStreamReader streamReader = new InputStreamReader(con.getInputStream(), "UTF-8");
                return readBody(streamReader);
            } else { // 에러 발생
                return readBodyerror(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }
    // 웹과 연결
    private HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            return httpURLConnection;
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }
    // 웹에서 받아온 정보를 String 형태롤 받아와 저장
    private String readBody(InputStreamReader streamReader){

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
    // 웹에서 정보를 받아올 때 Error가 날 경우
    private String readBodyerror(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}