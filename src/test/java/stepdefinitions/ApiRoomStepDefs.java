package stepdefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import pojos.RoomPojo;

import static base_url.MedunnaBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class ApiRoomStepDefs {

    Response response;
    RoomPojo expectedData;

    @Given("Get Request gonderilir")
    public void get_request_gonderilir() {

        //Set the Url
        spec.pathParams("first", "api", "second", "rooms", "third", 72086);

        // Set the expected data
        expectedData  = new RoomPojo(506209, "TWIN", false, 200.00, "cincik gibi oda");


        // Send the request and get the response
        response = given(spec).when().get("{first}/{second}/{third}");



        //HttpResponseException 401' li almamizin sebebi pom.xml dosyasinda
        // <argLine>-Duser.language=en</argLine> satirinin eksik olmasi



    }
    @Then("Body dogrulanir")
    public void body_dogrulanir() throws JsonProcessingException {

        //ObjectMapper jakson databinttan cagir
        RoomPojo actualData = new ObjectMapper().readValue(response.asString(), RoomPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getRoomNumber(),actualData.getRoomNumber());
        assertEquals(expectedData.getRoomType(),actualData.getRoomType());
        assertEquals(expectedData.getDescription(),actualData.getDescription());
        assertEquals(expectedData.isStatus(), actualData.isStatus());
        assertEquals(expectedData.getPrice(), actualData.getPrice());


    }


}
