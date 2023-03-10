package com.client.managers;

import javafx.scene.image.Image;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

public class ImageManager {

    private static final String CLIENT_ID = "Client-ID 26750b1542decce";
    private static final String UPLOAD_URL = "https://api.imgur.com/3/image";

    private final HashMap<String, Image> cachedImages = new HashMap<>();

    public Response uploadImage(String data) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("image",data)
                .build();

        Request request = new Request.Builder()
                .url(UPLOAD_URL)
                .method("POST", body)
                .addHeader("Authorization", CLIENT_ID)
                .build();

        return client.newCall(request).execute();
    }

    public String getImageLink(String response) {
        JSONObject jsonObject = new JSONObject(response);
        JSONObject array = jsonObject.getJSONObject("data");
        return array.getString("link");
    }

    public Image getFXImage(String link)  {
        Image image;
        if((image = cachedImages.get(link)) != null) {
            return image;
        }
        try {
            URL url = new URL(link);
            InputStream in = url.openStream();
            image = new Image(in, 128, 128, false, false);
            cachedImages.put(link, image);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
