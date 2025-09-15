package api.endpoints;

import api.utils.PropertyHelper;

public class Routes {

    private static final String BASE_URL = PropertyHelper.getBaseUrl();

    //User
    public static final String CREATE_USER = BASE_URL + "/user";
    public static final String GET_USER = BASE_URL + "/user/{username}";
    public static final String UPDATE_USER = BASE_URL + "/user/{username}";
    public static final String DELETE_USER = BASE_URL + "/user/{username}";

    //Store
    //We have store endpoints

    //Pet
    //We have pet endpoints
}
