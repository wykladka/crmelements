package pl.waw.azymut.services.comparerkiller.ceneo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.waw.azymut.models.comparerkiller.ceneo.dto.CeneoBid;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CeneoOfferToBidsParser {

     /*
    Klasa pozwala sparsowac link do danego produktu ceneo i wyciagnac wszystkie bidy
     */

    //TODO sprawdzic jak sie zachowuje w przypadku gdy dany bid sklada sie z kilku podproduktow

    public static final String PRODUCT_OFFER_CLASS_TAG = "product-offer";
    public static final String DATA_SHOP_ATTRIBUTEE_TAG = "data-shop";
    public static final String DATA_OFFER_PRICE_ATTRIBUTE_TAG = "data-offer-price";
    public static final String GROSZE_SEPARATOR = ".";
    public static final String DATA_OFFER_ATTRIBUTE_TAG = "data-offer";
    public static final String HREF_ATTRIBUTE = "href";
    public static final int CELL_PRICE_CELL = 9;
    public static final int CELL_STORE_LOGO_CELL_LINK_TO_PRODUCT = 1;

    Document doc;
    Set<CeneoBid> bids;

    //todo zamienic na private
    //todo fluidui ??

    public Set<CeneoBid> parse (String ceneoUrl) throws IOException {
        doc = Jsoup.connect(ceneoUrl).get();
        parse(doc);
        return bids;
    }

    public void parse(Document doc) {
        bids = new HashSet<CeneoBid>();
        for (Element el : getBidBlocksFromHtml(doc)) {
            bids.add(createCeneoBidFromElement(el));
            System.out.println(createCeneoBidFromElement(el).toString());  //todo logeer
        }
    }

    public Elements getBidBlocksFromHtml(Document doc) {
        return doc.getElementsByClass(PRODUCT_OFFER_CLASS_TAG);
    }

    public String getShopId(Element element) {
        return element.attr(DATA_SHOP_ATTRIBUTEE_TAG);
    }

    public Integer getOfferId(Element element) {
        return Integer.valueOf(element.attr(DATA_OFFER_ATTRIBUTE_TAG));
    }

    public String getUrlToOffer(Element element) {
        return element
                .childNode(CELL_PRICE_CELL)
                .childNode(CELL_STORE_LOGO_CELL_LINK_TO_PRODUCT)
                .attr(HREF_ATTRIBUTE);
    }

    public Integer getBidHeight(Element element) {
        return getPriceInGroszeFromString(element.attr(DATA_OFFER_PRICE_ATTRIBUTE_TAG));
    }

    public Integer getPriceInGroszeFromString(String amount) {
        StringBuilder sb = new StringBuilder(amount);
        sb.replace(sb.indexOf(GROSZE_SEPARATOR), sb.indexOf(GROSZE_SEPARATOR) + 1, "");
        return Integer.valueOf(sb.toString());
    }

    public CeneoBid createCeneoBidFromElement(Element e) {
        return new CeneoBid(
                getOfferId(e),
                getBidHeight(e),
                getShopId(e),
                getUrlToOffer(e));
    }

    public Set<CeneoBid> getBidsFromUrl(String url) throws IOException {
        parse(url);
        return bids;
    }

    //metoda dla testow danych z plikow lokalnie
    public Set<CeneoBid> getBidsFromTestResource(Document doc) {
        parse(doc);
        return bids;
    }

}
