public class UserAccountData {
    private String email;
    private String password;

    public UserAccountData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserAccountData() {
    }

    public static UserAccountData from(User user) {
        return new UserAccountData(user.getEmail(), user.getPassword());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}