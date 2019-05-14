package com.example.projectui.entities;

public class CommentsPojo {

    private String commentContent,userId;
    int like_counter;
    boolean is_user_like;

    public CommentsPojo() {

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

    public int getLike_counter() {
        return like_counter;
    }

    public void setLike_counter(int like_counter) {
        this.like_counter = like_counter;
    }

    public boolean isIs_user_like() {
        return is_user_like;
    }

    public void setIs_user_like(boolean is_user_like) {
        this.is_user_like = is_user_like;
    }


}
