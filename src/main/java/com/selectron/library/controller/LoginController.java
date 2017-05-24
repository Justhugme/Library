package com.selectron.library.controller;

import com.selectron.library.model.Rating;
import com.selectron.library.model.User;
import com.selectron.library.service.interfaces.RatingService;
import com.selectron.library.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private RatingService ratingService;

    @RequestMapping(value = {"/", "/user"}, method = RequestMethod.GET)
    public ModelAndView welcome() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView();
        if (auth.isAuthenticated()) {
            User user = userService.findUserByEmail(auth.getName());
            modelAndView.addObject("user",user);
            modelAndView.setViewName("User/HomePage");
        } else
            modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }


    @RequestMapping(value = "/user/home", method = RequestMethod.GET)
    public ModelAndView homeUser() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
//        List<Rating> rate = ratingService.getRatingsWhereUser(user);
        List<Rating> rate = new ArrayList<>(user.getRatings());
        rate.sort((o1, o2) -> -(o1.getRating().compareTo(o2.getRating())));
        modelAndView.addObject("user", user);
        modelAndView.addObject("rate", rate);
        modelAndView.setViewName("User/HomePage");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    @RequestMapping(value = "/user/ChangeHomePage", method = RequestMethod.GET)
    public ModelAndView changeHomePage() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("user", user);
        System.out.printf("Get method heere");
        modelAndView.setViewName("User/ChangeHomePage");
        return modelAndView;
    }

    @RequestMapping(value = "/user/ChangeHomePage", method = RequestMethod.POST)
    public ModelAndView changedHomePage(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user2 = userService.findUserByEmail(auth.getName());
        if (userExists.getId() != user2.getId()) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("User/ChangeHomePage");
        } else {

            user2.setName(user.getName());
            user2.setLastName(user.getLastName());
            user2.setPassword(user.getPassword());
            user2.setEmail(user.getEmail());
            userService.saveUser(user2);
            modelAndView.addObject("user", user);
            modelAndView.setViewName("User/HomePage");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.setViewName("/login");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/user/About", method = RequestMethod.GET)
    public ModelAndView userAbout() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/User/About");
        return modelAndView;
    }

    @RequestMapping(value = "/About", method = RequestMethod.GET)
    public ModelAndView about() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/About");
        return modelAndView;
    }
}
