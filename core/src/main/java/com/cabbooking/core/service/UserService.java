package com.cabbooking.core.service;

import com.cabbooking.core.model.BookedRideDetails;
import com.cabbooking.core.model.Position;
import com.cabbooking.core.model.User;

public interface UserService {

    BookedRideDetails bookRide(User user, Position start, Position end);
}
