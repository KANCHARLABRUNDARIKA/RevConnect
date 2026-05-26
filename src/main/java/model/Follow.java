package model;

public class Follow {

    private int id;
    private int followerId;
    private int followingId;

    public Follow(){}

    public Follow(int followerId, int followingId){
        this.followerId = followerId;
        this.followingId = followingId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    public int getFollowingId() {
        return followingId;
    }

    public void setFollowingId(int followingId) {
        this.followingId = followingId;
    }
}