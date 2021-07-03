package com.techelevator.tenmo.model.Transfer;

import com.techelevator.tenmo.model.account.JDBCAccountDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JDBCTransferDAO implements TransferDAO{

    private JdbcTemplate theDatabase;
    private JDBCAccountDAO accountDAO;


    public JDBCTransferDAO(DataSource dataSource){
        this.theDatabase = new JdbcTemplate(dataSource);

    }

//
//    public TransferDAO

    public Transfer sendTransfer(Long senderId, Long receiverId, Double amount) {
        if (amount <= accountDAO.viewBalance(senderId).getBalance()) {

            String sqlInsert = "Insert into transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount)  " +
                    "values (?, ?, ?, ?, ?)";
            theDatabase.update(sqlInsert, 2L, 2L, senderId, receiverId, amount);
            SqlRowSet nextId = theDatabase.queryForRowSet("select nextval('seq_transfer_id')");
            Transfer transfer = new Transfer();                                                                                //May need to reorder lines 32-37(Transfer transfer
            if (nextId.next()) {                                                                                               // - til String sqlsearch
                transfer.setTransferId(nextId.getLong(1));
            } else {
                throw new RuntimeException("There was no next Transfer");
            }
            String sqlSearch = "SELECT * FROM transfers WHERE transfer_id = ?";
            SqlRowSet results = theDatabase.queryForRowSet(sqlSearch, transfer.getTransferId());
            while (results.next()) {
                mapRowToTransfer(results, transfer);
            }

            return transfer;
        } else {
            return null;
        }
    }


//    public Transfer getTransferInfo(long id){    }


    //


    private Transfer mapRowToTransfer(SqlRowSet rowSet) {
        Transfer transfer = new Transfer();
        transfer.setAccountFrom(rowSet.getLong("account_from"));
        transfer.setAccountTo(rowSet.getLong("account_to"));
        transfer.setAmount(rowSet.getDouble("amount"));
 //       transfer.setTransferId(rowSet.getLong("transferId"));
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


