package com.selectron.library.repository;

import com.selectron.library.model.Rating;
import com.selectron.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findRatingsByUser(User user);
}
