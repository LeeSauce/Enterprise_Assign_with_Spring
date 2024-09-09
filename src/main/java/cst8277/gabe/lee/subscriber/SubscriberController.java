package cst8277.gabe.lee.subscriber;

import cst8277.gabe.lee.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubscriberController {
    @Autowired
    private SubscriberService subscriberService;

    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @PostMapping("/subscribe")
    public String subscribe(@RequestBody Subscriber subscriber) {
        return subscriberService.subscribe(subscriber);
    }

    @PostMapping("/get/subs")
    public List<User> getSubs(@RequestParam long subscriberId) {
        return subscriberService.getAllSubs(subscriberId);
    }


}
