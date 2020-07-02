package com.rul.blog.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private Integer userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private Integer userRole;

    @JsonProperty(value = "userId")
    public Integer getUserId() {
        return userId;
    }

    @JsonProperty(value = "userId")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonProperty(value = "userName")
    public String getUserName() {
        return userName;
    }

    @JsonProperty(value = "username")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonProperty(value = "userEmail")
    public String getUserEmail() {
        return userEmail;
    }

    @JsonProperty(value = "userEmail")
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @JsonProperty(value = "userPassword")
    public String getUserPassword() {
        return userPassword;
    }

    @JsonProperty(value = "userPassword")
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @JsonProperty(value = "userRole")
    public Integer getUserRole() {
        return userRole;
    }

    @JsonProperty(value = "userRole")
    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userRole=" + userRole +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userEmail, user.userEmail) &&
                Objects.equals(userPassword, user.userPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userEmail, userPassword);
    }
}
