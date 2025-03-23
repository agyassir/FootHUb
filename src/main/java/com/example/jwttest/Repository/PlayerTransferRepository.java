package com.example.jwttest.Repository;

import com.example.jwttest.Entity.PlayerTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerTransferRepository extends JpaRepository<PlayerTransfer, Long> {
}
