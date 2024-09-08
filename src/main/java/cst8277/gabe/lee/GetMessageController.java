package cst8277.gabe.lee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetMessageController {

    @Autowired
    private MessageRepo messageRepo;
    @GetMapping("get/message")
    public List<Message> getMessages() {
        return messageRepo.findAll();
    }
}
