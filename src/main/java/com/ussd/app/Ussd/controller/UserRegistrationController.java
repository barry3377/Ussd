package com.ussd.app.Ussd.controller;

import com.ussd.app.Ussd.Dto.UserRegistrationDto;
import com.ussd.app.Ussd.entities.User;
import com.ussd.app.Ussd.repository.RoleRepository;
import com.ussd.app.Ussd.repository.UserRepository;
import com.ussd.app.Ussd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class UserRegistrationController {
 //   private UserService userService;
//
//   public UserRegistrationController(UserService userService) {
//        this.userService = userService;
//    }
//    @ModelAttribute
//    public  UserRegistrationDto userRegistrationDto(){
//        return  new UserRegistrationDto();
//    }
//    @GetMapping
//    public   String showRegistration(Model model){
//        model.addAttribute("user", new UserRegistrationDto());
//        return "/registration";
//    }
//    @PostMapping
//    public   String RegistrationUserCount(@ModelAttribute("user")UserRegistrationDto userRegistrationDto){
//        userService.save(userRegistrationDto);
//        return  "redirect :/registration?success";
//    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @GetMapping(value="/users")
    public  String ListUser(Model model){
        List<User> users=userRepository.findAll();
        model.addAttribute("users",users);
        return "user/users";
    }



    @GetMapping(value="/new/user")
    public  String addUser(Model model){

        model.addAttribute("roles",roleRepository.findAll());
        User user=new User();
        model.addAttribute("user ",user);

        return "user/addUser.html";
    }

    @PostMapping(value="/saveUser")
    public  String addUsers(User user, Model model, String rpassword){
        BCryptPasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
        String encoderPassword=passwordEncoder.encode(user.getPassword());
        user.setPassword(encoderPassword);
       /* if(user.getPassword() != rpassword){
            String  heure= "pa"
        }*/
        userRepository.save(user);

        List<User> users = userRepository.findAll();

        model.addAttribute("users",     userRepository.findAll());
        return "user/users";
    }
    @GetMapping(value="/edituser/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id).get();
        model.addAttribute("user",user);
        model.addAttribute("roles",roleRepository.findAll());
        return "user/editUser";

    }
    @GetMapping(value="/deleteuser/{id}")
    public String deleteHopital(@PathVariable("id") long id, Model model) {
        userRepository.deleteById(id);

        return  "redirect:/users";
    }

}
