package com.finance.rsbalance.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;



@Entity
@Table(name = "balance_accounts")
public class BalanceAccounts  {


    @EmbeddedId
    private BalanceAccountsId id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "creation_date")
    private Date creation_date;

    @Column(name = "update_date")
    private Date update_date;


    public BalanceAccounts(){ }

    public BalanceAccounts(BalanceAccountsId id, Double amount, Date creation_date, Date update_date) {
        this.id = id;
        this.amount = amount;
        this.creation_date = creation_date;
        this.update_date = update_date;
    }


    public BalanceAccountsId getId() {
        return id;
    }

    public void setId(BalanceAccountsId id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }


}
