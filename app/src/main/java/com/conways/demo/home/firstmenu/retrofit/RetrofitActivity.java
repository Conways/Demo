package com.conways.demo.home.firstmenu.retrofit;

import android.os.Bundle;

import com.conways.demo.R;
import com.conways.demo.base.BaseTitleActivity;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by user on 2016/6/28.
 */
public class RetrofitActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_retrofit);
        super.onCreate(savedInstanceState);

        new Thread(new Runnable() {
            @Override
            public void run() {
                okhttpPost();

            }
        }).start();

    }


    private void okhttpGet() {
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(("http://192.168.1.27:8080/test_get.php?username=abc&pwd=123"))
                .build();

        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                logD("zzzzzzzzzzz failure" + request.body().toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {

                //这里并没有在UI线程里执行，更新ui时应该注意
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                        (response.body().byteStream(), "utf-8"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result = result + line;
                }
                logD("zzzzzzzzzzz seccuss" + result);
            }
        });

    }


    private void okhttpPost() {
        OkHttpClient httpClient = new OkHttpClient();
        FormEncodingBuilder encodingBuilder = new FormEncodingBuilder();
        encodingBuilder.add("username", "abc");
        encodingBuilder.add("pwd", "123");
        RequestBody requestBody = encodingBuilder.build();
        Request request = new Request.Builder()
                .url("http://192.168.1.27:8080/test_post.php")
                .post(requestBody)
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                logD("zzzzzzzzzzz failure" + request.body().toString());

            }

            @Override
            public void onResponse(Response response) throws IOException {

                //这里并没有在UI线程里执行，更新ui时应该注意
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                        (response.body().byteStream(), "utf-8"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result = result + line;
                }
                logD("zzzzzzzzzzz seccuss" + result);
            }
        });

    }


    private void httpGet() {
        try {
            URL url = new URL("http://192.168.1.27:8080/test_get.php?username=abc&pwd=123");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                    (inputStream, "utf-8"));
            inputStream.close();
            String result = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result = result + line;
            }
            logD("zzzzzzzzzzz" + result);
            connection.disconnect();

        } catch (MalformedURLException e) {
            logD("zzzzzzzzzzz" + e.getMessage());
        } catch (IOException e) {
            logD("zzzzzzzzzzz" + e.getMessage());

        }
    }


    private void httpPost() {

        try {
            URL url = new URL("http://192.168.1.27:8080/test_post.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("username", "abc");
            connection.setRequestProperty("pwd", "123");
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                    (inputStream, "utf-8"));
            inputStream.close();
            String result = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result = result + line;
            }
            logD("zzzzzzzzzzz" + result);
            connection.disconnect();

        } catch (MalformedURLException e) {
            logD("zzzzzzzzzzz" + e.getMessage());
        } catch (IOException e) {
            logD("zzzzzzzzzzz" + e.getMessage());

        }

    }

}
