package com.hackathon.inditex.repositories;

import com.hackathon.inditex.Entities.Center;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CenterRepository extends JpaRepository<Center, Long> {

    boolean existsByCoordinatesLatitudeAndCoordinatesLongitude(Double latitude, Double longitude);

}
