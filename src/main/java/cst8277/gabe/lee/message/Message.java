package cst8277.gabe.lee.message;

import cst8277.gabe.lee.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "MESSAGE")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long messageId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "message")
    private String message;

    public Message() {
        // Default constructor
    }

    public Message(long messageId, User user, String message) {
        this.messageId = messageId;
        this.user = user;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", user=" + user +
                ", message='" + message + '\'' +
                '}';
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

