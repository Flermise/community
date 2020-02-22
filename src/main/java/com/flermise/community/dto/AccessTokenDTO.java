package com.flermise.community.dto;

import lombok.Data;

/**
 * ref : https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
