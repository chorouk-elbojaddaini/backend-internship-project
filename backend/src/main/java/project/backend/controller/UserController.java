package project.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.dao.UserDAO;
import project.backend.service.UserService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserDAO user){
        try {
            UserDAO newUsr = userService.addUser(user);
            return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find")
    public Optional<UserDAO> findUserById(@RequestParam Long id){
        return userService.findUserById(id);
    }


    @GetMapping
    public List<UserDAO> getAllUsers(){
        return userService.getAllUsers();
    }


    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return "User deleted successfully";
        } else {
            return "User not found";
        }
    }

    @PutMapping("/update/{id}")
    public UserDAO updateUser(@RequestBody UserDAO user,@PathVariable("id") Long id){
        return userService.updateUser(user,id);
    }
}
