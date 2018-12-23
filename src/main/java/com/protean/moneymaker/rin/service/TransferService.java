package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.model.Transfer;

import java.util.List;

public interface TransferService {

    List<Transfer> saveAllTransfers(List<Transfer> transfers);

    Transfer saveTransfer(Transfer transfer);

    List<Transfer> getAllTransfers();

    void deleteTransfers(List<Transfer> ids);

    void deleteTransfer(Transfer id);

}
