import lombok.AccessLevel;
import lombok.Getter;

@Getter(value = AccessLevel.PUBLIC)
public class ItemBean {
    private String uid;
    private String bsgId;
    private String name;
    private String shortName;
    private int price;
    private int basePrice;
    private int avg24hPrice;
    private int avg7daysPrice;
    private String traderName;
    private int traderPrice;
    private String traderPriceCur;
    private String updated;
    private int slots;
    private float diff24h;
    private float diff7days;
    private String icon;
    private String link;
    private String wikiLink;
    private String img;
    private String imgBig;
    private String reference;
}
