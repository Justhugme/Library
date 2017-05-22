package com.selectron.library.service.interfaces;

import com.selectron.library.model.Rating;
import com.selectron.library.model.User;

import java.util.List;

public interface RatingService {
    List<Rating> getRatingsWhereUser(User user);
}
