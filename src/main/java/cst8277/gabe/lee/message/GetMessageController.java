package cst8277.gabe.lee.message;

import cst8277.gabe.lee.subscriber.SubscriberController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GetMessageController {

    @Autowired
    MessageService messageService;
    SubscriberController subscriberController;

    public GetMessageController(MessageService messageService, SubscriberController subscriberController) {
        this.messageService = messageService;
        this.subscriberController = subscriberController;
    }

    @GetMapping("get/message")
    public List<Message> getMessages() {
        return messageService.getMessages();
    }
    @PostMapping("/post/message")
    public String postMessage(@RequestBody Message message) {
        return messageService.postMessage(message);
    }

    @PostMapping("/find/message")
    public List<Message> getMessage(@RequestParam long subscriptionId) {
        return messageService.getMessagesByUserId(subscriptionId, subscriberController);
    }

    @PostMapping("/find/message_by_publisher")
    public List<Message> getMessage(@RequestParam long subscriptionId,
                                    @RequestParam long publisherId) {
        return messageService.getMessagesByUserId(subscriptionId, publisherId);
    }
}
