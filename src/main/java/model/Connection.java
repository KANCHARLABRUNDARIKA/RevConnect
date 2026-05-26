package model;

public class Connection {

    private int id;
    private int senderId;
    private int receiverId;
    private String status;

    public Connection(){}
    public Connection(int senderId, int receiverId, String status){
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.status = status;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}