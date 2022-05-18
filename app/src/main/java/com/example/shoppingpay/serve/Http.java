package com.example.shoppingpay.serve;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Http {
    Context context;
    private String url, method="GET", data="null", response=null;
    private Integer statusCode;
    private LocalStrorage localStrorage;

    public Http(Context context, String url) {
        this.context = context;
        this.url = url;
        localStrorage=new LocalStrorage(context);
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getResponse() {
        return response;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void send(){
        try {
            URL sUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) sUrl.openConnection();
            connection.setRequestMethod(method);
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestProperty("X-Requested-With","XMLHttpRequest");
            if(!method.equals("GET")){
                connection.setDoOutput(true);
            }
            if(data != null){
                OutputStream os = connection.getOutputStream();
                os.write(data.getBytes());
                os.flush();
                os.close();
            }
            statusCode=connection.getResponseCode();

            InputStreamReader isr;
            if (statusCode>=200&&statusCode<=299){
                //if success reponse
                isr = new InputStreamReader(connection.getInputStream());
            }else {
                //if erro reponese                isr = new InputStreamReader(connection.getErrorStream());
            }

            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line= br.readLine())!=null){
                sb.append(line);
                response=sb.toString();
            }


        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
