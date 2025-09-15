package api.utils;

import api.payload.User;
import com.github.javafaker.Faker;

public class TestDataUtil {
    static Faker faker = new Faker();

    public static User getUserData() {
        User payLoad = new User();
        payLoad.setId(faker.idNumber().hashCode());
        payLoad.setUsername(faker.name().username());
        payLoad.setFirstName(faker.name().firstName());
        payLoad.setLastName(faker.name().lastName());
        payLoad.setEmail(faker.internet().safeEmailAddress());
        payLoad.setPassword(faker.internet().password(5, 10));
        payLoad.setPhone(faker.phoneNumber().cellPhone());
        return payLoad;
    }
}
