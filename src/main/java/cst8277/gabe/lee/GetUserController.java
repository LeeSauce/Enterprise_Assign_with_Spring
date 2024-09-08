package cst8277.gabe.lee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetUserController {

    @Autowired
    private UserRepo userRepository;

    @GetMapping("/get/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
