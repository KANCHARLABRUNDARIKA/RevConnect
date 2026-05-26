package model;

public class Post {
    private int id;
    private int userId;
    private String content;
    private String hashtags;
    private String createdAt;
    private Integer originalPostId;
    private boolean repost;
    public Post() {
    }
    public Post(int userId, String content, String hashtags) {
        this.userId = userId;
        this.content = content;
        this.hashtags = hashtags;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getHashtags() {
        return hashtags;
    }
    public void setHashtags(String hashtags) {
        this.hashtags = hashtags;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    public Integer getOriginalPostId() {
        return originalPostId;
    }

    public void setOriginalPostId(Integer originalPostId) {
        this.originalPostId = originalPostId;
    }

}