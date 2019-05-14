package com.example.projectui.entities;

public class CommentsPojoAdd {

    private String commentContent,userId;
    int likeCounter;
    boolean isUserLike;

    public CommentsPojoAdd(String commentContent, String userId, int likeCounter, boolean isUserLike) {
        this.commentContent = commentContent;
        this.userId = userId;
        this.likeCounter = likeCounter;
        this.isUserLike = isUserLike;
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

    public int getLikeCounter() {
        return likeCounter;
    }

    public void setLikeCounter(int likeCounter) {
        this.likeCounter = likeCounter;
    }

    public boolean isUserLike() {
        return isUserLike;
    }

    public void setUserLike(boolean userLike) {
        isUserLike = userLike;
    }
}
