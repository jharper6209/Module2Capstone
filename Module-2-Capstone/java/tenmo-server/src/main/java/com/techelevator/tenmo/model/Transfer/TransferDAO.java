package com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDAO {

    public Transfer sendTransfer(Transfer transfer);
    public List<Transfer> findAll();
    public Long getNextTransferId();
    public Transfer searchTransferById(Long id);
    public List<Transfer> searchTransferByUserId(Long id);
}
