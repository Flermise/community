package com.flermise.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001, "问题无法找到！！！"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题评论进行回复"),
    NOT_LOGIN(2003, "未登录，无法进行评论"),
    SYS_ERROR(2004, "服务器丢了，请让我找找！！！"),
    TYPE_PARAM_NOT_FOUND(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"该评论不存在或已经删除"),
    COMMENT_IS_EMPTY(2007,"输入内容不能为空"),
    READ_NOTIFICATION_FAIL(2008,"你读到了别人的信息"),
    NOTIFICATION_NOT_FOUND(2009,"通知无法找到");
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
