import com.dtflys.forest.exceptions.ForestNetworkException;
import com.dtflys.forest.http.ForestResponse;

import java.util.ArrayList;

public class ItemInfo {
    public final ArrayList<ItemBean> itemBeanList;
    ItemInfo(String uid){
        HttpClient httpClient = Utils.getHttpClient();
        String result = null;
        result = httpClient.getByUid("https://tarkov-market.com/api/v1/item?uid=", uid);
        itemBeanList = Utils.parseItemBean(result);
    }

    ItemInfo(String keyword, String language){
        HttpClient httpClient = Utils.getHttpClient();
        String result = null;
        result = httpClient.getByKeyword(keyword, language);
        itemBeanList = Utils.parseItemBean(result);
    }

    public String getNameByIndex(int index){
        return itemBeanList.get(index).getName();
    }

    public int getTraderPriceByIndex(int index){
        return itemBeanList.get(index).getTraderPrice();
    }

    public String getTraderPriceCurByIndex(int index){
        return itemBeanList.get(index).getTraderPriceCur();
    }

    public int getPriceByIndex(int index){
        return itemBeanList.get(index).getPrice();
    }

    public String getTraderNameByIndex(int index){
        return itemBeanList.get(index).getTraderName();
    }
}
