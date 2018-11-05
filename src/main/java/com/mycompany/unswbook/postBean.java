package com.mycompany.unswbook;
import java.util.*;
import java.sql.Timestamp;

public class postBean {
    public int postID;
    public String ownerName;
    public String content;
    public String photo;
    public ArrayList<Integer> likesList;
    public ArrayList<String>  likesYourNameList;
    public Timestamp time;

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public ArrayList<Integer> getLikesList() {
        return likesList;
    }

    public void setLikesList(ArrayList<Integer> likesList) {
        this.likesList = likesList;
    }

    public ArrayList<String> getLikesYourNameList() {
        return likesYourNameList;
    }

    public void setLikesYourNameList(ArrayList<String> likesYourNameList) {
        this.likesYourNameList = likesYourNameList;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
