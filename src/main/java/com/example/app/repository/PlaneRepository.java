package com.example.app.repository;

import com.example.app.model.Plane;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;


public interface PlaneRepository extends JpaRepository<Plane, Long> {

    @Query("SELECT plane FROM Plane plane WHERE plane.departureTime >= :today")
    Page<Plane> findPlanesFromNow(@Param("today") LocalDate today, Pageable pageable);
}
