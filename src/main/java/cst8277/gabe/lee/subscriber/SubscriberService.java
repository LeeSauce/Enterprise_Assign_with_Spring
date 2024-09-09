package cst8277.gabe.lee.subscriber;

import cst8277.gabe.lee.user.Login;
import cst8277.gabe.lee.user.LoginRepo;
import cst8277.gabe.lee.user.User;
import cst8277.gabe.lee.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubscriberService {

    @Autowired
    private SubscriberRepo subscriberRepo;
    private UserRepo userRepo;
    private LoginRepo loginRepo;

    public SubscriberService (SubscriberRepo subscriberRepo, UserRepo userRepo, LoginRepo loginRepo) {
        this.subscriberRepo = subscriberRepo;
        this.userRepo = userRepo;
        this.loginRepo = loginRepo;
    }

    public String subscribe(@RequestBody Subscriber subscriber) {
        long subscriberId = subscriber.getSubscriber().getId();
        long publisherId = subscriber.getPublisher().getId();

        Optional<User> publisher = getUser(publisherId);
        if(publisher.isEmpty()) return "User does not exist";

        if(!isAuthorized(subscriberId)) return "User is not authorized to perform this action";
        subscriberRepo.save(subscriber);
        return "Subscribed!";
    }

    public List<User> getAllSubs(@RequestParam long subscriberId) {
        if (!isAuthorized(subscriberId)) return null;

        List<Subscriber> subs = subscriberRepo.findAll().stream()
                .filter(sub -> sub.getSubscriber().getId() == subscriberId)
                .toList();
        if (subs.isEmpty()) return null;

        List<Long> publisherIds = new ArrayList<>();

        for(Subscriber sub : subs) {
            publisherIds.add(sub.getPublisher().getId());
        }
        return userRepo.findAllById(publisherIds);
    }

    private boolean isAuthorized(long id) {
        Optional<User> userOptional = getUser(id);
        if(userOptional.isEmpty()) return false;

        User user = userOptional.get();
        String email = user.getEmail();

        List<Login> logins = loginRepo.findAll();

        Optional<Login> loginOptional = logins.stream().filter(
               login -> Objects.equals(login.getEmail(), email)
        ).findFirst();

        if(loginOptional.isEmpty()) return false;

        return (user.getRole().equals("subscriber") || user.getRole().equals("publisher")
        );
    }

    private Optional<User> getUser(long id){
        List<User> users = userRepo.findAll();

        return users
                .stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

}
