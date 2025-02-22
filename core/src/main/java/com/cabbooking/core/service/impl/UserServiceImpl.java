package com.cabbooking.core.service.impl;

import com.cabbooking.core.model.BookedRideDetails;
import com.cabbooking.core.model.Position;
import com.cabbooking.core.model.User;
import com.cabbooking.core.service.AreaService;
import com.cabbooking.core.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private AreaService areaService;

    UserServiceImpl(AreaService areaService) {
        this.areaService = areaService;
    }

    @Override
    public BookedRideDetails bookRide(User user, Position start, Position end) {

          // find area code for start.
          // get list of all drivers for area code and city -redis
          // find nearest one and assign the ride to him
         // share details back

         return null;
    }



   


}
