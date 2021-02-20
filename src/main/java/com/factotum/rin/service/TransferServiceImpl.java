package com.factotum.rin.service;

import com.factotum.rin.model.Transfer;
import com.factotum.rin.repository.TransferRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    public TransferServiceImpl(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public List<Transfer> saveAllTransfers(List<Transfer> transfers) {
        List<Transfer> savedTransfers = new ArrayList<>();
        transferRepository.saveAll(transfers).forEach(savedTransfers::add);
        return savedTransfers;
    }

    @Override
    public Transfer saveTransfer(Transfer transfer) {
        return transferRepository.save(transfer);
    }

    @Override
    public List<Transfer> getAllTransfers() {

        List<Transfer> transfers = new ArrayList<>();
        transferRepository.findAll().forEach(transfers::add);

        return transfers;
    }

    @Override
    public void deleteTransfers(List<Transfer> transfers) {
        transferRepository.deleteAll(transfers);
    }

    @Override
    public void deleteTransfer(Transfer transfer) {
        transferRepository.delete(transfer);
    }
}
