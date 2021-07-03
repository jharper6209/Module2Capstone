package com.techelevator.tenmo.model.Transfer;

import com.techelevator.tenmo.model.account.Account;
import com.techelevator.tenmo.model.account.JDBCAccountDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JDBCTransferDTODAO implements TransferDTODAO{

    private JdbcTemplate theDatabase;
    private JDBCAccountDAO accountDAO;


    public JDBCTransferDTODAO(DataSource dataSource){
        this.theDatabase = new JdbcTemplate(dataSource);

    }

    public TransferDTO create(TransferDTO transferDTO, Account accountFrom, Account accountTo, Double amount) {
        transferDTO.getAccountFrom();
        transferDTO.getAccountTo();
        return transferDTO;
    }
}
