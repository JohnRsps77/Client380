package com.client.model;

import javafx.scene.image.Image;

public class User {

    private String username;
    private String email;
    private Image profileImage;
    private String profileImageLink;
    private final long uuid;

    public User(String username, String email, String imageLink, Image profileImage, long uuid) {
        this.username = username;
        this.email = email;
        this.profileImageLink = imageLink;
        this.profileImage = profileImage;
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Image getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
    }

    public long getUuid() {
        return uuid;
    }

    public String getProfileImageLink() {
        return profileImageLink;
    }
}
