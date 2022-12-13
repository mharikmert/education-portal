package portal.backend.app.controller;

import portal.backend.app.model.User;
import portal.backend.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RedirectionController {
    private final UserService userService;

    @Autowired
    public RedirectionController(UserService userService) {
        this.userService = userService;
    }

    // renders admin menu page with given id
    @GetMapping("/{userType}-menu/{id}")
    public ModelAndView redirectUser(@PathVariable String userType, @PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            ModelAndView mav = new ModelAndView();
            mav.addObject(user);
            mav.setViewName("User Types/" + userType + "-menu");
            return mav;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        /*
         * TO DO
         * Editing on error pages
         * Handling illegal path variables
         */
    }
}
