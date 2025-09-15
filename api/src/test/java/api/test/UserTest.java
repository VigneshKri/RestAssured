package api.test;

import api.BaseTest;
import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utils.TestDataUtil;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class UserTest extends BaseTest {

    @Test(priority = 1)
    public void userTest() {
        User payLoad = TestDataUtil.getUserData();
        Response response = UserEndPoints.createUser(payLoad);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test()
    public void getUser() {
        User payLoad = TestDataUtil.getUserData();
        UserEndPoints.createUser(payLoad);
        //Get User
        Response readResponse = UserEndPoints.readUser(payLoad.getUsername());
        Assert.assertEquals(readResponse.getStatusCode(), 200);
    }

    @Test()
    public void updateUser() {
        //Update user
        Faker faker = new Faker();
        User payLoad = TestDataUtil.getUserData();
        UserEndPoints.createUser(payLoad);

        payLoad.setFirstName(faker.name().firstName());
        payLoad.setLastName(faker.name().lastName());
        payLoad.setPhone(faker.phoneNumber().cellPhone());

        Response updateResponse = UserEndPoints.updateUser(payLoad.getUsername(), payLoad);
        Assert.assertEquals(updateResponse.getStatusCode(), 200);

        //Validate user presence after edit
        Response updatedResponse = UserEndPoints.readUser(payLoad.getUsername());
        Assert.assertEquals(updatedResponse.getStatusCode(), 200);
    }

    @Test()
    public void deleteUser() {
        User payLoad = TestDataUtil.getUserData();
        UserEndPoints.createUser(payLoad);
        //Delete user
        Response deleteResponse = UserEndPoints.deleteUser(payLoad.getUsername());
        Assert.assertEquals(deleteResponse.getStatusCode(), 200);
    }

}
