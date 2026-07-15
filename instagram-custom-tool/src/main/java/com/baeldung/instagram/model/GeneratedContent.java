package com.baeldung.instagram.model;

public class GeneratedContent {

    private ContentType contentType;
    private String topic;
    private String content;
    private String instagramHandle;

    public GeneratedContent(ContentType contentType, String topic, String content, String instagramHandle) {
        this.contentType = contentType;
        this.topic = topic;
        this.content = content;
        this.instagramHandle = instagramHandle;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getInstagramHandle() {
        return instagramHandle;
    }

    public void setInstagramHandle(String instagramHandle) {
        this.instagramHandle = instagramHandle;
    }

}
