package cst8277.gabe.lee.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetMessageController {

    @Autowired
    MessageService messageService;

    public GetMessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    public List<Message> getMessages() {
        return messageService.getMessages();
    }
}
