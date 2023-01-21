import com.shaft.driver.SHAFT;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import serviceObjectModel.GameActions;
import serviceObjectModel.UserActions;

import java.util.Date;

public class TestFiveInRowGame {

    //****** variables ******//
    SHAFT.API driver;
    SHAFT.TestData.JSON testData;
    UserActions userActions;
    GameActions gameActions;
    Date date = new Date();
    String randomUniqueValue = date.getTime() +"";


    //****** test setup ******//
    @BeforeClass
    public void beforeClass(){
        //you can update the following baseUrl form "src/main/resources/properties/URLs.properties"
        driver = new SHAFT.API(System.getProperty("baseUrl"));
        testData = new SHAFT.TestData.JSON("src/test/resources/testData/testData.json");
        userActions = new UserActions(driver);
        gameActions = new GameActions(driver);
    }

    //****** Tests ******//
    @Description("""
            steps
            - create user and get user token
            - create game and get game token
            - make move
            - validate that the move has been made successfully
            """)
    @org.testng.annotations.Test(description = "test valid scenario")
    public void testValidScenario(){
        userActions.createUser(
                testData.getTestData("testValidScenario.firstUser.nickName") + randomUniqueValue,
                testData.getTestData("testValidScenario.firstUser.email") + randomUniqueValue+ "@email.com");
        driver.assertThatResponse().extractedJsonValue("statusCode").isEqualTo(201).withCustomReportMessage("validate that user is created successfully").perform();
        String firstUserToken = driver.getResponseJSONValue("userToken");

        gameActions.createGame(firstUserToken);
        driver.assertThatResponse().extractedJsonValue("statusCode").isEqualTo(201).withCustomReportMessage("validate that game is created successfully").perform();
        String gameToken = driver.getResponseJSONValue("gameToken");

        gameActions.makeMove(firstUserToken, gameToken, 2, 0);
        driver.assertThatResponse().extractedJsonValue("statusCode").isEqualTo(201).withCustomReportMessage("validate that the move is performed successfully").perform();
    }

    @Description("""
            steps
            - create user and get user token
            - create game and get game token
            - make move
            - make move at the same coordinated
            - validate that user can't perform a move to used coordinates
            """)
    @org.testng.annotations.Test(description = "test invalid scenario - make move using used coordinates- shouldn't be accepted")
    public void testInValidScenario_makeMoveUsingUsedCoordinates_shouldNotBeAccepted(){
        userActions.createUser(
                testData.getTestData("testInValidScenario_makeMoveUsingUsedCoordinates_shouldNotBeAccepted.firstUser.nickName") + randomUniqueValue,
                testData.getTestData("testInValidScenario_makeMoveUsingUsedCoordinates_shouldNotBeAccepted.firstUser.email") + randomUniqueValue+ "@email.com");
        driver.assertThatResponse().extractedJsonValue("statusCode").isEqualTo(201).withCustomReportMessage("validate that user is created successfully").perform();
        String firstUserToken = driver.getResponseJSONValue("userToken");

        gameActions.createGame(firstUserToken);
        driver.assertThatResponse().extractedJsonValue("statusCode").isEqualTo(201).withCustomReportMessage("validate that game is created successfully").perform();
        String gameToken = driver.getResponseJSONValue("gameToken");

        gameActions.makeMove(firstUserToken, gameToken, 2, 0);
        driver.assertThatResponse().extractedJsonValue("statusCode").isEqualTo(201).withCustomReportMessage("validate that move is performed successfully").perform();

        gameActions.makeMove(firstUserToken, gameToken, 2, 0);
        driver.assertThatResponse().extractedJsonValue("statusCode").isEqualTo(409).withCustomReportMessage("validate that user can't perform a move to used coordinated").perform();
    }

    @Description("""
            steps
            - create user and get user token
            - create another user and get user token
            - create game for first user and get game token
            - make move using second user
            - validate that second user is not allowed to make moves in this game
            """)
    @org.testng.annotations.Test(description = "test invalid scenario - play with invalid user - shouldn't be accepted")
    public void testInValidScenario_playWithInvalidUser_shouldNotBeAccepted(){
        userActions.createUser(
                testData.getTestData("testInValidScenario_playWithInvalidUser_shouldNotBeAccepted.firstUser.nickName") + randomUniqueValue,
                testData.getTestData("testInValidScenario_playWithInvalidUser_shouldNotBeAccepted.firstUser.email") + randomUniqueValue+ "@email.com");
        driver.assertThatResponse().extractedJsonValue("statusCode").isEqualTo("201").withCustomReportMessage("validate that first user is created successfully").perform();
        String firstUserToken = driver.getResponseJSONValue("userToken");

        userActions.createUser(
                testData.getTestData("testInValidScenario_playWithInvalidUser_shouldNotBeAccepted.secondUser.nickName") + randomUniqueValue,
                testData.getTestData("testInValidScenario_playWithInvalidUser_shouldNotBeAccepted.secondUser.email") + randomUniqueValue+ "@email.com");
        driver.assertThatResponse().extractedJsonValue("statusCode").isEqualTo("201").withCustomReportMessage("validate that second user is created successfully").perform();
        String secondUserToken = driver.getResponseJSONValue("userToken");

        gameActions.createGame(firstUserToken);
        driver.assertThatResponse().extractedJsonValue("statusCode").isEqualTo("201").withCustomReportMessage("validate that game is created successfully for the first user").perform();
        String gameToken = driver.getResponseJSONValue("gameToken");

        gameActions.makeMove(secondUserToken, gameToken, 2, 0);
        driver.assertThatResponse().extractedJsonValue("statusCode").isEqualTo("401").withCustomReportMessage("validate that second user is not allowed to make a move").perform();
    }

}
