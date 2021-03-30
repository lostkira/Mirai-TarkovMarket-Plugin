import com.dtflys.forest.exceptions.ForestNetworkException;
import com.dtflys.forest.http.ForestResponse;

import java.util.ArrayList;

public class Main {
    private static final String url = "https://tarkov-market.com/api/v1/item?uid=";
    private static final String uid = "5ac84494-465a-424a-b36e-fe22869ba5ec";
    private static final String DEFAULT_LANGUAGE = "cn";
    private static final String apiKey = "YOUR_API_KEY";

    public static void main(String[] args){
        HttpClient httpClient = Utils.getHttpClient();

        String result = null;
        try {
            result = httpClient.getByKeyword(apiKey ,"火药", DEFAULT_LANGUAGE);
        }catch (ForestNetworkException e){
            int status = e.getStatusCode();
            ForestResponse response = e.getResponse();
            System.out.println("StatusCode: " + status);
            System.out.println(response.getContent());
            System.out.println(response.getRequest());
//            System.out.println("请求出错");
        }catch (Exception exception){
            System.out.println("请求错误");
        }

        ArrayList<ItemBean> itemBeanList = Utils.parseItemBean(result);

        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < itemBeanList.size(); i++){
            temp.append("物品名：" + itemBeanList.get(i).getName());
            temp.append("\n当前市场价格：" + itemBeanList.get(i).getPrice() + itemBeanList.get(i).getTraderPriceCur());
            temp.append("\n\n");
        }
        System.out.println(temp);

//        System.out.println(itemBeanList.get(0).getName());
//        System.out.println(itemBeanList.get(0).getTraderPrice() + itemBeanList.get(0).getTraderPriceCur());

        System.out.println("EOF");
        System.exit(0);
    }

}
