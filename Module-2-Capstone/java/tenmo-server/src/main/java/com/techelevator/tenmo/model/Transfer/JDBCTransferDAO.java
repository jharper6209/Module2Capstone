package com.techelevator.tenmo.model.Transfer;

import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.account.JDBCAccountDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCTransferDAO implements TransferDAO{

    private JdbcTemplate theDatabase;
    private JDBCAccountDAO accountDAO;


    public JDBCTransferDAO(DataSource dataSource){
        this.theDatabase = new JdbcTemplate(dataSource);

    }

//
//    public TransferDAO

    public Transfer sendTransfer(Transfer transfer) {

            String sqlInsert = "Insert into transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount)  " +
                    "values (?, ?, ?, ?, ?)";
            theDatabase.update(sqlInsert, transfer.getTransferTypeId(), transfer.getTransferStatusId(),
                    transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());

            return transfer;

    }
/*
    public Long getTransferId() {
        SqlRowSet nextId = theDatabase.queryForRowSet("select nextval('seq_transfer_id')");
        Long id = null;
        if (nextId.next()) {                                                                                               // - til String sqlsearch
            id = nextId.getLong(1);
        } else {
            throw new RuntimeException("There was no next Transfer");
        }
        return id;
    }
*/
    public Long getNextTransferId() {
        SqlRowSet nextTransferId = theDatabase.queryForRowSet("select nextval('seq_transfer_id')");

        if (nextTransferId.next()) {
            return  nextTransferId.getLong(1);
        } else {
            throw new RuntimeException("There was no next Transfer Option");
        }
    }

       public List<Transfer> findAll() {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "select * from transfers";

        SqlRowSet results = theDatabase.queryForRowSet(sql);
        while(results.next()) {
            Transfer transfer = mapRowToTransfer(results);
            transfers.add(transfer);
        }

        return transfers;
    }


    public Transfer searchTransferById(Long id) {
        String sqlTransfer = "Select * From transfers Where transfer_id = ?";
        SqlRowSet sqlSearch = theDatabase.queryForRowSet(sqlTransfer, id);
        Transfer transfer = new Transfer();
        while (sqlSearch.next()) {
            transfer = mapRowToTransfer(sqlSearch);
        }
        return transfer;
    }

    public List<Transfer> searchTransferByUserId(Long id) {
        List<Transfer> theTransfers = new ArrayList<Transfer>();
        String sqlTransfer = "Select * From transfers Where account_from = ? or account_to = ?";
        SqlRowSet sqlSearch = theDatabase.queryForRowSet(sqlTransfer, id, id);
        while (sqlSearch.next()) {
            theTransfers.add(mapRowToTransfer(sqlSearch));
        }
        return theTransfers;
    }

    private Transfer mapRowToTransfer(SqlRowSet rowSet) {
        Transfer transfer = new Transfer();
        transfer.setAccountFrom(rowSet.getLong("account_from"));
        transfer.setAccountTo(rowSet.getLong("account_to"));
        transfer.setAmount(rowSet.getDouble("amount"));
        transfer.setTransferId(rowSet.getLong("transfer_Id"));
        transfer.setTransferStatusId(rowSet.getLong("transfer_status_id"));
        transfer.setTransferTypeId(rowSet.getLong("transfer_type_id"));
        return transfer;
    }

    private Transfer mapRowToTransfer(SqlRowSet rowSet, Transfer transfer) {
        transfer.setAccountFrom(rowSet.getLong("account_from"));
        transfer.setAccountTo(rowSet.getLong("account_to"));
        transfer.setAmount(rowSet.getDouble("amount"));
        transfer.setTransferStatusId(rowSet.getLong("transfer_status_id"));
        transfer.setTransferTypeId(rowSet.getLong("transfer_type_id"));
        return transfer;
    }

}


