package cst8277.gabe.lee.message;

import cst8277.gabe.lee.user.Login;
import cst8277.gabe.lee.user.LoginRepo;
import cst8277.gabe.lee.user.User;
import cst8277.gabe.lee.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class MessageService {

    @Autowired
    private UserRepo userRepository;
    private MessageRepo messageRepository;
    private LoginRepo loginRepository;
    @Autowired
    private MessageRepo messageRepo;

    public MessageService(UserRepo userRepository, MessageRepo messageRepository, LoginRepo loginRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.loginRepository = loginRepository;
    }

    //Get all messages
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    public String postMessage(@RequestBody Message message) {
        long userId = message.getUser().getId();
        if(!validateToken(userId))return "User not validated, message was not posted";
        if(!canPost(userId)) return "User cannot post";
        messageRepo.save(message);
        return "Message posted";
    }

    private boolean validateToken(long id){
        List<User> users = userRepository.findAll();

        Optional<User> userOptional = users
                .stream()
                .filter(user -> user.getId() == id)
                .findFirst();
        if (userOptional.isEmpty()) {
            return false;
        }
        User user = userOptional.get();
        String email = user.getEmail();

        List<Login> logins = loginRepository.findAll();

        Optional<Login> loginOptional  = logins
                .stream()
                .filter(login -> Objects.equals(login.getEmail(), email))
                .findFirst();
        return loginOptional.isPresent();
    }

    private boolean canPost(long id) {
        List<User> users = userRepository.findAll();

        Optional<User> userOptional = users
                .stream()
                .filter(user -> user.getId() == id)
                .findFirst();
        if (userOptional.isEmpty()) {
            return false;
        }
        User user = userOptional.get();
        return user.getRole().contains("publisher");
    }

}
