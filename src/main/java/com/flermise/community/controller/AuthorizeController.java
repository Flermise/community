package com.flermise.community.controller;


import com.flermise.community.Model.User;
import com.flermise.community.dto.AccessTokenDTO;
import com.flermise.community.dto.GithubUser;
import com.flermise.community.mapper.UserMapper;
import com.flermise.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.url}")
    private String redirectUrl;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDTO accessToken = new AccessTokenDTO();
        accessToken.setCode(code);
        accessToken.setRedirect_uri(redirectUrl);
        accessToken.setClient_id(clientId);
        accessToken.setClient_secret(clientSecret);
        accessToken.setState(state);
        String accessTok = githubProvider.getAccessToken(accessToken);
        GithubUser githubUser = githubProvider.getUser(accessTok);
        if (githubUser != null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userMapper.insert(user);
            response.addCookie(new Cookie("token",token));
            //登录成功：重定向至index页面
            return "redirect:/";
        }else{
            return "redirect:/";
        }

    }
}
