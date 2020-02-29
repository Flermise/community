package com.flermise.community.controller;

import com.flermise.community.dto.CommentCreateDTO;
import com.flermise.community.dto.ResultDTO;
import com.flermise.community.exception.CustomizeErrorCode;
import com.flermise.community.exception.CustomizeException;
import com.flermise.community.model.Comment;
import com.flermise.community.model.User;
import com.flermise.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {



    @Autowired
    private CommentService commentService;
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if (user == null){
            return ResultDTO.errorOf(new CustomizeException(CustomizeErrorCode.NOT_LOGIN));
        }
        if (commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())){
            return ResultDTO.errorOf(new CustomizeException(CustomizeErrorCode.COMMENT_IS_EMPTY));
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setComment(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(user.getId());
        comment.setFavCount(0L);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
