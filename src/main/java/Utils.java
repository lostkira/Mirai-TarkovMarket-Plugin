import com.dtflys.forest.config.ForestConfiguration;
import com.dtflys.forest.converter.json.ForestGsonConverter;
import com.dtflys.forest.ssl.SSLUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;

public class Utils {
    public static final String uid = "5ac84494-465a-424a-b36e-fe22869ba5ec";

    public static HttpClient getHttpClient(){
        ForestConfiguration forestConfiguration = ForestConfiguration.configuration();
        forestConfiguration.setSslProtocol(SSLUtils.TLS_1_2);
        forestConfiguration.setJsonConverter(new ForestGsonConverter());
        return forestConfiguration.createInstance(HttpClient.class);
    }

    public static ArrayList<ItemBean> parseItemBean(String result){
        JsonArray jsonArray = JsonParser.parseString(result).getAsJsonArray();
        Gson gson = new Gson();
        ArrayList<ItemBean> itemBeanList = new ArrayList<ItemBean>();
        for (JsonElement item : jsonArray){
            ItemBean itemBean = gson.fromJson(item, ItemBean.class);
            itemBeanList.add(itemBean);
        }
        return itemBeanList;
    }
}
