package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndPoints {

    public static Response createUser(User payload) {
        return given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.CREATE_USER);
    }

    /**
     * Read the created user data <b>username</b> must match with the path param in endpoint
     *
     * @param userName {@link String}
     * @return {@link Response}
     */
    public static Response readUser(String userName) {
        return given()
                .pathParam("username", userName)
                .when()
                .get(Routes.GET_USER);
    }

    public static Response updateUser(String userName, User payload) {
        return given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("username", userName)
                .body(payload)
                .when()
                .put(Routes.UPDATE_USER);
    }

    public static Response deleteUser(String userName) {
        return given()
                .pathParam("username", userName)
                .when()
                .delete(Routes.DELETE_USER);
    }
}
