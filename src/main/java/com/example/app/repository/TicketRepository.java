package com.example.app.repository;

import com.example.app.model.Plane;
import com.example.app.model.Ticket;
import com.example.app.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface TicketRepository extends Repository<Ticket, Long> {

    @Query("SELECT ticket FROM Ticket ticket JOIN Plane plane ON plane.id=ticket.plane.id WHERE plane.id = :planeId")
    List<Ticket> findTicketsByPlaneId(@Param("planeId") Long planeId);

    @Modifying
    @Transactional
    @Query(value = "insert into Ticket (plane_id, user_id, price, deleted) values(:planeId, :userId, 1000, false)", nativeQuery = true)
    void createTickets(@Param("planeId") Long planeId, @Param("userId") Long userId);

    @Query("SELECT ticket FROM Ticket ticket WHERE ticket.deleted = false")
    List<Ticket> findAll();

    @Query("SELECT ticket FROM Ticket ticket WHERE ticket.id = :id")
    Ticket findTicketById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "update Ticket t set t.plane_id = :plane_id, t.user_id = :user_id, t.price = :price, t.deleted = :deleted WHERE t.id = :id", nativeQuery = true)
    void updateTicket(@Param("plane_id") Plane plane_id, @Param("user_id") User user_id, @Param("price") BigDecimal price, @Param("deleted") boolean deleted, @Param("id") Long id);


}




