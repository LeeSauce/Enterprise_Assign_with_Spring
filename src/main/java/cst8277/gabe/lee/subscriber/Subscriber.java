package cst8277.gabe.lee.subscriber;

import cst8277.gabe.lee.user.User;
import jakarta.persistence.*;

@Entity
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sub_id", nullable = false)
    private User subscriber;

    @ManyToOne
    @JoinColumn(name = "pub_id", nullable = false)
    private User publisher;

    public Subscriber(Long id, User subscriber, User publisher) {
        this.id = id;
        this.subscriber = subscriber;
        this.publisher = publisher;
    }

    public Subscriber() {

    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "id=" + id +
                ", subscriber=" + subscriber +
                ", publisher=" + publisher +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }

    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }
}
