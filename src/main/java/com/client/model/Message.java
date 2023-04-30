package com.client.model;

public record Message(long uuid, String userName, String text, long timestamp, String imageLink) {
}
