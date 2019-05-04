package com.epam.lab.dto;

public class AccountDto {
    private int id;
    private long balance;
    private int idHolder;
    private int status;

    public void setId(int id) {
        this.id = id;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public void setIdHolder(int idHolder) {
        this.idHolder = idHolder;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    private AccountDto(int newId, Long newBalance, int newIdHolder, int newStatus) {
        this.id = newId;
        this.balance = newBalance;
        this.idHolder = newIdHolder;
        this.status = newStatus;
    }

    public int getId() {
        return id;
    }

    public long getBalance() {
        return balance;
    }

    public int getIdHolder() {
        return idHolder;
    }

    public int getStatus() {
        return status;
    }

    public static AccountDTOBuilder builder() {
        return new AccountDTOBuilder ();
    }

    public static class AccountDTOBuilder {
        private int newId;
        private Long newBalance;
        private int newIdHolder;
        private int newStatus;

        public AccountDTOBuilder() {
        }

        public AccountDTOBuilder setId(int id) {
            this.newId = id;
            return this;
        }

        public AccountDTOBuilder setBalance(long balance) {
            this.newBalance = balance;
            return this;
        }

        public AccountDTOBuilder setIdHolder(int idHolder) {
            this.newIdHolder = idHolder;
            return this;
        }

        public AccountDTOBuilder setStatus(int status) {
            this.newStatus = status;
            return this;
        }

        public AccountDto build() {
            return new AccountDto(newId, newBalance, newIdHolder, newStatus);
        }
    }
}
