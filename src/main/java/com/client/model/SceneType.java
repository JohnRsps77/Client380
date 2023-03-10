package com.client.model;

public enum SceneType {
    LOGIN_SCENE("views/login-view.fxml"),
    MAIN_SCENE("views/main-view.fxml"),
    SIGNUP_SCENE("views/signup-view.fxml");

    private final String path;

    SceneType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
