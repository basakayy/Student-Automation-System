package automation.system;

public interface Authentication {
    boolean login(String userID, String password);
    void changePassword(String newPassword);
}
