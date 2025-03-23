package com.example.jwttest.Service;

import com.example.jwttest.Entity.PlayerTransfer;
import com.example.jwttest.Repository.PlayerTransferRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferService {
    private final PlayerTransferRepository transferRepository;

    public TransferService(PlayerTransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    public List<PlayerTransfer> getAllTransfers() {
        return transferRepository.findAll();
    }

    public PlayerTransfer getTransferById(Long id) {
        return transferRepository.findById(id).orElse(null);
    }

    public PlayerTransfer saveTransfer(PlayerTransfer transfer) {
        return transferRepository.save(transfer);
    }

    public void deleteTransfer(Long id) {
        transferRepository.deleteById(id);
    }
}

