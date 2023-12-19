package model;

public class User {
    private int userId;
    private String userRole;
    private String userName;
    private String userEmail;
    private String password;

    public User(int userId, String userRole, String userName, String userEmail, String password) {
        this.userId = userId;
        this.userRole = userRole;
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
