package cst8277.gabe.lee.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetMessageController {

    @Autowired
    MessageService messageService;

    public GetMessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("get/message")
    public List<Message> getMessages() {
        return messageService.getMessages();
    }
    @PostMapping("/post/message")
    public String postMessage(@RequestBody Message message) {
        return messageService.postMessage(message);
    }
}
