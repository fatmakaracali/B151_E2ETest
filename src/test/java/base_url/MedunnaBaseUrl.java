package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static utilities.AuthenticationMedunna.generateToken;

public class MedunnaBaseUrl {

    //Testi calistirdigimizda spec objesinin null gelmemesi icin setup() metodunun calistirilmasi gerekir.
    //Cucumberda her testten önce calistirmak istedigimiz metotlari Hooks classina ekleriz
    //Olusturdugumuz Hooks classini glue kisminda tanimlamamiz gerekir

    public static RequestSpecification spec ;

    public static void setUp(){
        spec = new RequestSpecBuilder()
                .setBaseUri("https://medunna.com")
                .addHeader("Authorization","Bearer "+generateToken())
                .setContentType(ContentType.JSON)
                .build();
    }
}
