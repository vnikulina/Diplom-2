import io.qameta.allure.Step;
import com.github.javafaker.Faker;

public class User {
    private String email;
    private String password;
    private String name;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static User getRandomUser(){
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = faker.lorem().characters(8, true);
        String name = faker.name().firstName();
        return new User (email, password, name);
    }
    @Step("New user without name")
    public static User getUserWithoutName(){
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = faker.lorem().characters(8, true);
        return new User (email, password);
    }
    @Step("New user without password")
    public static User getUserWithoutPassword(){
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String name = faker.name().firstName();
        return new User (email, name);
    }
    @Step("New user without email")
    public static User getUserWithoutEmail(){
        Faker faker = new Faker();
        String password = faker.lorem().characters(8, true);
        String name = faker.name().firstName();
        return new User (password, name);
    }

    @Step("Get user email")
    public String getEmail() {
        return email;
    }

    @Step("Set user email")
    public void setEmail(String email) {
        this.email = email;
    }

    @Step("Get user password")
    public String getPassword() {
        return password;
    }

    @Step("Set user password")
    public void setPassword(String password) {
        this.password = password;
    }

    @Step("Get user name")
    public String getName() {
        return name;
    }

    @Step("Set user name")
    public void setName(String name) {
        this.name = name;
    }
}