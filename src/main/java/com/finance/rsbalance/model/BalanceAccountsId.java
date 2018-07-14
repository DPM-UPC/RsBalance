package com.finance.rsbalance.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class BalanceAccountsId implements Serializable {

    private static final long serialVersionUID = 1L;

    /* @Id */
    @Column(name = "monthly_period")
    private Long monthly_period;

    /* @Id */
    @Column(name = "user_business_id")
    private Integer user_business_id;

    /* @Id */
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

    public void setMonthly_period(Long monthly_period) {
        this.monthly_period = monthly_period;
    }

    public Integer getUser_business_id() {
        return user_business_id;
    }

    public void setUser_business_id(Integer user_business_id) {
        this.user_business_id = user_business_id;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if(!(obj instanceof BalanceAccountsId)) {
            return false;
        }
        BalanceAccountsId _balance = (BalanceAccountsId) obj;
        return Objects.equals(getMonthly_period(), _balance.getMonthly_period()) && Objects.equals(getUser_business_id(), _balance.getUser_business_id()) && Objects.equals(getAccount_id(), _balance.getAccount_id());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getMonthly_period(), getUser_business_id(), getAccount_id());
    }


}
