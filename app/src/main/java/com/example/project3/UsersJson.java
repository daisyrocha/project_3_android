package com.example.project3;

import java.util.List;

/**
 * Model class according to data from API.
 */
public class UsersJson<T> {
    List<T> userData;

    public List<T> getUserData() {
        return userData;
    }

    public void setUserData(List<T> userData) {
        this.userData = userData;
    }


    /**
     * not sure if this needs to be in a separate class
     */
    public class userData {
        String username;
        String password;
        int id;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
