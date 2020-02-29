package com.flermise.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001, "服务器丢了，请让我找找！！！"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题评论进行回复"),
    NOT_LOGIN(2003, "未登录，无法进行评论"),
    SYS_ERROR(2004, "服务器丢了，请让我找找！！！"),
    TYPE_PARAM_NOT_FOUND(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"该评论不存在或已经删除");
    private String message;
    private Integer code;


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}
