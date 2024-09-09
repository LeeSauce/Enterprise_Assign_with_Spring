package cst8277.gabe.lee.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddUserController {
    @Autowired
    private UserRepo userRepo;

    @PostMapping("post/users")
    public String addUser(@RequestBody User user) {
        userRepo.save(user);
        return "User added";
    }
}
