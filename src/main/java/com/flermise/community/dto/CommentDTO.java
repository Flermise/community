package com.flermise.community.dto;

import com.flermise.community.model.User;
import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long favCount;
    private Integer commentCount;
    private String comment;
    private User user;
}
