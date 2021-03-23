import com.dtflys.forest.exceptions.ForestNetworkException;
import com.dtflys.forest.http.ForestResponse;

import java.util.ArrayList;

public class ItemInfo {
    public final ArrayList<ItemBean> itemBeanList;
    ItemInfo(String uid){
        HttpClient httpClient = Utils.getHttpClient();

        String result = null;
        try {
            result = httpClient.getByUid("https://tarkov-market.com/api/v1/item?uid=", uid);
        }catch (ForestNetworkException e){
            int status = e.getStatusCode();
            ForestResponse response = e.getResponse();
            System.out.println("StatusCode: " + status);
            System.out.println(response.getContent());
            System.out.println(response.getRequest());
        }catch (Exception exception){
            System.out.println(exception);
        }
        itemBeanList = Utils.parseItemBean(result);
    }

    ItemInfo(String keyword, String language){
        HttpClient httpClient = Utils.getHttpClient();
        String result = null;
        try {
            result = httpClient.getByKeyword(keyword, language);
        }catch (ForestNetworkException e){
            int status = e.getStatusCode();
            ForestResponse response = e.getResponse();
            System.out.println("StatusCode: " + status);
            System.out.println(response.getContent());
            System.out.println(response.getRequest());
        }catch (Exception exception){
            System.out.println(exception);
        }
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
