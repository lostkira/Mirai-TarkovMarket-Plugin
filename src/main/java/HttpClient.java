import com.dtflys.forest.annotation.DataVariable;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Request;

public interface HttpClient {

    @Request(
        url = "${url}${uid}",
        headers = "x-api-key:${apiKey}",
        dataType = "text"
    )
    String getByUid(@DataVariable("apiKey")String apiKey, @DataVariable("url")String url,
                    @DataVariable("uid")String uid);

    @Post(
        url = "https://tarkov-market.com/api/v1/item",
        headers = {
            "x-api-key:${apiKey}",
            "Content-Type:application/json"
        },
        dataType = "text"
    )
    String getByKeyword(@DataVariable("apiKey")String apiKey, @JSONBody("q")String keyword,
        @JSONBody("lang")String language);
}
