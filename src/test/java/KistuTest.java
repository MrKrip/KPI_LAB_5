import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class KistuTest {

    @Test
    public void GetAnimeList()
    {
        given()
                .baseUri(Config.Kitsu_BASE_URL)
                .when()
                .get(Config.Anime_List)
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void GetAnimeById()
    {
        given()
                .baseUri(Config.Kitsu_BASE_URL)
                .pathParam("animeId","1")
                .when()
                .get(Config.Anime_By_Id)
                .then()
                .log().body()
                .assertThat()
                .body(Matchers.notNullValue());
    }

    @Test
    public void GetAnimeByFakeId()
    {
        given()
                .baseUri(Config.Kitsu_BASE_URL)
                .pathParam("animeId","1234567890")
                .when()
                .get(Config.Anime_By_Id)
                .then()
                .log().body()
                .statusCode(404);
    }

    @Test
    public void DeletePost()
    {
        given()
                .baseUri(Config.Kitsu_BASE_URL)
                .pathParam("animeId","167585")
                .when()
                .delete(Config.Delete_Post_By_Id)
                .then()
                .log().body()
                .statusCode(403);
    }
}
