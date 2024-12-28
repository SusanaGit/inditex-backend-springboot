package com.hackathon.inditex.repositories;

import com.hackathon.inditex.Entities.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CenterRepository extends JpaRepository<Center, Long> {

    boolean existsByCoordinatesLatitudeAndCoordinatesLongitude(Double latitude, Double longitude);

    @Query("SELECT center FROM Center center WHERE center.capacity LIKE %:capacity%")
    List<Center> findByCapacity(@Param("capacity") String capacity);

}
