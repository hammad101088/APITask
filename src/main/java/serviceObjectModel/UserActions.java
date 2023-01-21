package serviceObjectModel;

import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserActions {

    //****** variables ******//
    SHAFT.API driver;

    public UserActions(SHAFT.API driver){
        this.driver = driver;
    }

    //****** keywords and methods ******//

    /**
     * create user to get token.
     * @param nickName
     * @param email
     * @return the whole response to extract user info (i.e. token)
     */
    public Response createUser(String nickName, String email){
        String body = """
                {
                  "nickname": \"""" +nickName+ """
                \",
                  "email": \"""" +email+ """
                \"
                }
                """;
        SHAFT.Report.report("Create user with the following data: {nickName = "+nickName+", email = "+email+"}");
        return driver.post("api/v1/user").setContentType(ContentType.JSON).setRequestBody(body).perform();
    }
}
