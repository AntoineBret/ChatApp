package com.chatapp.ipme.chatapp.utils;

public class NewSessionManager {

    public class User {
        private Integer id;
        private String token;
        private String username;
        private String password;

        public User(Integer id, String token, String username, String password) {
            this.id = id;
            this.token = token;
            this.username = username;
            this.password = password;
        }

        public Integer getId() {
            return this.id;
        }

        public String getToken() {
            return this.token;
        }

        public String getUsername() {
            return this.username;
        }

        public String getPassword() {
            return this.password;
        }

        public void setId() {
            this.id = id;
        }

        public void setToken() {
            this.token = token;
        }

        public void setUsername() {
            this.username = username;
        }

        public void setPassword() {
            this.password = password;
        }
    }

    public class Builder {
        private Integer id;
        private String token;
        private String username;
        private String password;

        public Builder() {
            this.id = null;
            this.token = null;
            this.username = null;
            this.password = null;
        }

        public Integer getId() {
            return this.id;
        }

        public String getToken() {
            return this.token;
        }

        public String getUsername() {
            return this.username;
        }

        public String getPassword() {
            return this.password;
        }

        public Builder setId() {
            this.id = id;
            return this;
        }

        public Builder setToken() {
            this.token = token;
            return this;
        }

        public Builder setUsername() {
            this.username = username;
            return this;
        }

        public Builder setPassword() {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(id, token, username, password);
        }
    }
}
