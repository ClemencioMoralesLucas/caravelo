package application.pojos;

import application.pojos.utils.BeanTest;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.*;

/**
 * Created by Clemencio Morales Lucas.
 */
public class UserTest extends BeanTest<User> {

    private static final String USERNAME = "Solid_Snake";
    private static final String SALT = "9234923482ShadowMoses9213949";
    private static final String PASSWORD = "B1G_B0sS";

    /**
     * @README: Thanks to BeanTest, we can keep coverage at maximum without effort
     * following the approach shown in this unit test
     */
    @Override
    protected User getBean() {
        return new User(USERNAME, SALT, PASSWORD);
    }
}