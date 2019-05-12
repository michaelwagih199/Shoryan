package com.example.projectui.entities;

public class CommentsPojoAdd {
    private String commentContent,userId;

    public CommentsPojoAdd(String commentContent, String userId) {
        this.commentContent = commentContent;
        this.userId = userId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
