package com.client.model;

import javafx.scene.image.Image;

import java.util.List;

public record GroupChat(String name, Image image, List<Message> messageList) {
}
