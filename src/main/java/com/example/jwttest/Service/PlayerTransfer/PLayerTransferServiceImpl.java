package com.example.jwttest.Service.PlayerTransfer;


import com.example.jwttest.DTO.Player.PlayerTransferDTO;
import com.example.jwttest.DTO.PlayerTransfer.TransferRequest;
import com.example.jwttest.Entity.Club;
import com.example.jwttest.Entity.PlayerTransfer;
import com.example.jwttest.Repository.PlayerTransferRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PLayerTransferServiceImpl implements PlayerTrasferService{
    private final PlayerTransferRepository playerTransferRepository;
    private ModelMapper modelMapper;


    @Override
    public void createPlayerTransfer(TransferRequest request) {

    }

    @Override
    public void updatePlayerTransfer(TransferRequest request) {

    }

    @Override
    public void deletePlayerTransfer() {

    }

    @Override
    public List<PlayerTransferDTO> getPlayerTransfers(long playerId) {
     return playerTransferRepository.findByPlayerId(playerId).stream().map((element) -> modelMapper.map(element, PlayerTransferDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PlayerTransfer> getClubTransfers(Club club) {
        return List.of();
    }

    @Override
    public List<PlayerTransfer> getTransferOfYear(Date year) {
        return List.of();
    }


}
