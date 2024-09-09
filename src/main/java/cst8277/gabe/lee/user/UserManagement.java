package cst8277.gabe.lee.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserManagement {

    @Autowired
    private LoginRepo loginRepo;
    private UserRepo userRepo;

    public UserManagement(LoginRepo loginRepo, UserRepo userRepo) {
        this.loginRepo = loginRepo;
        this.userRepo = userRepo;
    }

    @PostMapping("/user/login")
    public String login(@RequestBody Login login) {
        String email = login.getEmail();
        String password = login.getPassword();


        List<User> users = userRepo.findAll();
        Optional<User> userOptional= users
                .stream()
                .filter(user -> email.equals(user.getEmail()) && password.equals(user.getPassword()))
                .findFirst();

        if (userOptional.isPresent()) {
            loginRepo.save(login);
            return "Logged in successfully";
        }
        return "User not found";
    }


}
