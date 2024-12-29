package com.hackathon.inditex.repositories;

import com.hackathon.inditex.Entities.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CenterRepository extends JpaRepository<Center, Long> {

    @Query("SELECT CASE WHEN COUNT(center) > 0 THEN true ELSE false END FROM Center center WHERE center.coordinates.latitude = :latitude AND center.coordinates.longitude = :longitude")
    boolean existsByCoordinatesLatitudeAndCoordinatesLongitude(@Param("latitude") Double latitude,
                                                               @Param("longitude") Double longitude);

    @Query("SELECT center FROM Center center WHERE center.capacity LIKE %:capacity%")
    List<Center> findByCapacity(@Param("capacity") String capacity);

    @Query("SELECT center FROM Center center WHERE center IN :listCenters AND center.currentLoad < center.maxCapacity")
    List<Center> findAvailableCenters(@Param("listCenters") List<Center> listCenters);

}
