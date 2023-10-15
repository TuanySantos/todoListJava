package br.com.tuanybrito.todoListJava.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository iUserRepository;
    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel user){
       var userFind = this.iUserRepository.findByUserName(user.getUserName());
        if(userFind != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe!");
        }
        var passwordHashred = BCrypt.withDefaults()
                .hashToString(12, user.getPassword().toCharArray());
        user.setPassword(passwordHashred);
         this.iUserRepository.save(user);
         return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }


}
