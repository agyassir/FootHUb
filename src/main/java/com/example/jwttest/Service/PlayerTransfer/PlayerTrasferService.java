package com.example.jwttest.Service.PlayerTransfer;

import com.example.jwttest.DTO.PlayerTransfer.TransferRequest;
import com.example.jwttest.Entity.Club;
import com.example.jwttest.Entity.League;
import com.example.jwttest.Entity.Player;
import com.example.jwttest.Entity.PlayerTransfer;

import java.util.Date;
import java.util.List;

public interface PlayerTrasferService {

    void createPlayerTransfer(TransferRequest request);
    void updatePlayerTransfer(TransferRequest request);
    void deletePlayerTransfer();
    List<PlayerTransfer> getPlayerTransfers(long playerId);
    List<PlayerTransfer> getClubTransfers(Club club);
    List<PlayerTransfer> getTransferOfYear(Date year);


}
