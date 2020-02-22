package com.flermise.community.provider;


import com.alibaba.fastjson.JSON;
import com.flermise.community.dto.AccessTokenDTO;
import com.flermise.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO AccessToken){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();


            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(AccessToken));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String sbody = response.body().string();
                String token = sbody.split("&")[0].split("=")[1];
                return token;
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+ accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String sbody = response.body().string();
            GithubUser githubUser = JSON.parseObject(sbody, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
        }
        return null;
    }

}
