package com.epam.lab.dto;

import java.sql.Timestamp;

public class TransactionDto {
    private final int id;
    private final int idSourceCard;
    private final int idDestinationCard;
    private final Timestamp timestamp;
    private final int amount;

    private TransactionDto(Builder builder) {
        this.id = builder.id;
        this.idSourceCard = builder.idSourceCard;
        this.idDestinationCard = builder.idDestinationCard;
        this.timestamp = builder.timestamp;
        this.amount = builder.amount;
    }

    public int getId() {
        return id;
    }

    public int getIdSourceCard() {
        return idSourceCard;
    }

    public int getIdDestinationCard() {
        return idDestinationCard;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public int getAmount() {
        return amount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private int idSourceCard;
        private int idDestinationCard;
        private Timestamp timestamp;
        private int amount;

        public Builder() {
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setIdSourceCard(int idSourceCard) {
            this.idSourceCard = idSourceCard;
            return this;
        }

        public Builder setIdDestinationCard(int idDestinationCard) {
            this.idDestinationCard = idDestinationCard;
            return this;
        }

        public Builder setTimestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public TransactionDto build() {
            return new TransactionDto(this);
        }
    }
}
