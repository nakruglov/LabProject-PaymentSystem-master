package com.epam.lab.dto;
// Users data transfer object

public class UserDto {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String login;
    private final String password;
    private final int role;

    private UserDto(UserBuilder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.login = builder.login;
        this.password = builder.password;
        this.role = builder.role;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    @Override
    public String toString() {
        String info = "";
        info += "id: " + id + "; ";
        info += "firstName: " + firstName + "; ";
        info += "lastName: " + lastName + "; ";
        info += "login: " + login + "; ";
        info += "password: " + password + "; ";
        info += "role: " + role + "; \n";

        return info;
    }

    public static class UserBuilder {
        private int id;
        private String firstName;
        private String lastName;
        private String login;
        private String password;
        private int role;

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder setLogin(String login) {
            this.login = login;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setRole(int role) {
            this.role = role;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }

    }
}
