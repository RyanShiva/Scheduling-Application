package Model;

/** This class supplies the logic for a User object.*/
public class User {

    private int userId;
    private String userName;
    private String password;

    private static User currentUser = new User(1, "test", "test");

    /** This is the User Constructor. This constructor creates a new User object with parameters for the object attributes.
     * @param userId user ID
     * @param userName username
     * @param password password*/
    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    /** This is the userId Getter. This method returns the User ID.
     * @return User ID*/
    public int getUserId() {
        return userId;
    }

    /** This is the userId Setter. This method sets the User ID.
     * @param userId User ID*/
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** This is the userName Getter. This method returns the User username.
     * @return User username*/
    public String getUserName() {
        return userName;
    }

    /** This is the userName Setter. This method sets the User username.
     * @param userName User username*/
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** This is the password Getter. This method returns the User password.
     * @return User password*/
    public String getPassword() {
        return password;
    }

    /** This is the password Setter. This method sets the User password.
     * @param password User password*/
    public void setPassword(String password) {
        this.password = password;
    }

    /** This is the currentUser Getter. This method returns the User that is currently logged in.
     * @return User that is currently logged in*/
    public static User getCurrentUser() {
        return currentUser;
    }

    /** This is the currentUser Setter. This method sets the User that is currently logged in.
     * @param currentUser User that is currently logged in*/
    public static void setCurrentUser(User currentUser) {
        User.currentUser = currentUser;
    }
}
