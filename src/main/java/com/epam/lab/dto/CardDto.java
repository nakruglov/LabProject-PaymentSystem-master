package com.epam.lab.dto;

public class CardDto {

    private final int id;
    private final int idHolder;
    private final int status;
    private final int idAccount;

    private CardDto(CardsBuilder cardsBuilder) {
        this.id = cardsBuilder.id;
        this.idHolder = cardsBuilder.idHolder;
        this.status = cardsBuilder.status;
        this.idAccount = cardsBuilder.idAccount;
    }

    public static CardsBuilder builder() {
        return new CardsBuilder();
    }

    public int getId() {
        return id;
    }

    public int getIdHolder() {
        return idHolder;
    }

    public int getStatus() {
        return status;
    }

    public int getIdAccount() {
        return idAccount;
    }


    public static class CardsBuilder {

        private int id;

        private int idHolder;
        private int status;
        private int idAccount;

        public CardsBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public CardsBuilder setIdHolder(int idHolder) {
            this.idHolder = idHolder;
            return this;
        }

        public CardsBuilder setStatus(int status) {
            this.status = status;
            return this;
        }

        public CardsBuilder setIdAccount(int idAccount) {
            this.idAccount = idAccount;
            return this;
        }

        public CardDto build() {
            return new CardDto(this);
        }


    }

    @Override
    public String toString() {
        return "CardDto{" +
                "id=" + id +
                ", idHolder=" + idHolder +
                ", status=" + status +
                ", idAccount=" + idAccount +
                '}';
    }
}
