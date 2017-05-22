package com.selectron.library.service.implementations;

import com.selectron.library.model.Rating;
import com.selectron.library.model.User;
import com.selectron.library.repository.RatingRepository;
import com.selectron.library.service.interfaces.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("ratingService")
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public List<Rating> getRatingsWhereUser(User user) {
        return ratingRepository.findRatingsByUser(user);
    }
}
