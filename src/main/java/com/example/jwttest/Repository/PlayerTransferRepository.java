package com.example.jwttest.Repository;

import com.example.jwttest.Entity.PlayerTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerTransferRepository extends JpaRepository<PlayerTransfer, Long> {
    @Query(value = "SELECT * FROM player_transfer WHERE player_id = :playerId ORDER BY transfer_date DESC",
            nativeQuery = true)
    List<PlayerTransfer> findByPlayerId(Long playerId);
}
