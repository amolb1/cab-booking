package com.cabbooking.core.rest;


import com.cabbooking.core.model.BookedRideDetails;
import com.cabbooking.core.model.Position;
import com.cabbooking.core.model.User;
import com.cabbooking.core.model.Vehicle;
import com.cabbooking.core.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;
    UserController(UserService userService ){
        this.userService = userService;
    }

    public BookedRideDetails bookRide(@RequestBody(required = true) User user, Position startPosition, Position endPosition) {
      return userService.bookRide(user,startPosition,endPosition);
    }

}
