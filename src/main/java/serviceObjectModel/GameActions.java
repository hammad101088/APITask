package serviceObjectModel;

import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GameActions {

    //****** variables ******//
    SHAFT.API driver;

    public GameActions(SHAFT.API driver){
        this.driver = driver;
    }

    //****** keywords and methods ******//
    /**
     * connect to existing game or create new one.
     * @param userToken
     * @return the whole response to extract game info (i.e. game token)
     */
    public Response createGame(String userToken){
        String body = """
                {
                "userToken": \"""" +userToken+ """
                \"}
                """;
        SHAFT.Report.report("connect to game or create new game using the following data: {user token = "+userToken+"}");
        return driver.post("api/v1/connect").setContentType(ContentType.JSON).setRequestBody(body).perform();
    }

    /**
     * play and make move.
     * @param userToken
     * @param gameToken
     * @param positionX
     * @param positionY
     * @return the whole response
     **/
    public Response makeMove(String userToken, String gameToken, int positionX, int positionY){
        String body = """
                {
                  "userToken": \"""" +userToken+ """
                \",
                  "gameToken": \"""" +gameToken+ """
                \",
                  "positionX": """ +positionX+ """
                ,
                  "positionY": """+positionY+ """
                }
                """;
        SHAFT.Report.report("play and make move using the following data: {user token = "+userToken+", game token = "+gameToken+"," +
                " position X = "+positionX+", position y = "+positionY+"}");
        return driver.post("api/v1/play").setContentType(ContentType.JSON).setRequestBody(body).perform();
    }
}
