import com.dtflys.forest.annotation.DataVariable;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Request;

public interface HttpClient {

    @Request(
        url = "${url}${uid}",
        headers = "x-api-key:fREhtc8ysOAI2f5j",
        dataType = "text"
    )
    String getByUid(@DataVariable("url")String url, @DataVariable("uid")String uid);

    @Post(
        url = "https://tarkov-market.com/api/v1/item",
        headers = {
            "x-api-key:fREhtc8ysOAI2f5j",
            "Content-Type:application/json"
        },
        dataType = "text"
    )
    String getByKeyword(@JSONBody("q")String keyword, @JSONBody("lang")String language);
}
