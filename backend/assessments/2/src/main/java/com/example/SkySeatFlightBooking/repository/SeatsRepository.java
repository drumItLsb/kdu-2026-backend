package com.example.SkySeatFlightBooking.repository;

import com.example.SkySeatFlightBooking.entity.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatsRepository extends JpaRepository<Seats,String> {
    boolean existsBySeatNumber(Integer seatNumber);
    @Query(value = "SELECT * FROM Seats s where s.hold_id = ?1",nativeQuery = true)
    List<Seats> findByHoldId(String holdId);

    void deleteRowBySeatNumber(Integer seatNumber);
}