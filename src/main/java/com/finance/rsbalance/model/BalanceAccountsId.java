package com.finance.rsbalance.model;

import javax.persistence.Column;
import java.io.Serializable;

/**
 *
 */
public class BalanceAccountsId implements Serializable {

    @Column(name = "monthly_period")
    private Long monthly_period;

    @Column(name = "user_business_id")
    private Integer user_business_id;

    @Column(name = "account_id")
    private Integer account_id;

    public BalanceAccountsId() {
    }

    public BalanceAccountsId(Long monthly_period, Integer user_business_id, Integer account_id) {
        this.monthly_period = monthly_period;
        this.user_business_id = user_business_id;
        this.account_id = account_id;
    }

    public Long getMonthly_period() {

        return monthly_period;
    }

    public Integer getUser_business_id() {

        return user_business_id;
    }

    public Integer getAccount_id() {

        return account_id;
    }
}
