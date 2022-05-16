package com.example.project3;

import java.util.List;

/**
 * Model class according to data from API.
 */
public class UsersJson<User> {
    List<User> userData;

    public List<User> getUserData() {
        return userData;
    }

    public void setUserData(List<User> userData) {
        this.userData = userData;
    }


    /**
     * not sure if this needs to be in a separate class
     */
    public class userData {
        String username;
        String password;
        List<Team> teamList;
        int user_id;


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

        public int getUserId() {
            return user_id;
        }

        public void setUserId(int user_id) {
            this.user_id = user_id;
        }
    }
}
