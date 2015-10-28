package pl.waw.azymut.services.comparerkiller;

import pl.waw.azymut.models.comparerkiller.OwnProduct;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OwnProductsXmlProductListParser {

    public static final String LINK_FOR_TRADE_OFFER_IN_XML = "http://eazymut.pl/modules/pricewars/offer.php?service=ceneo";
    public static final String OFFER_ELEMENT = "o";
    public static final String OFFERS_ELEMENT_ID_ATTRIBUTE = "id";
    public static final String OFFERS_ELEMENT_PRICE_ATTRIBUTE = "price";
    public static final String OFFERS_ELEMENT_CHILD_NAME_ELEMENT = "name";

    public Map<Long, OwnProduct> retriveProductListFromTradeOfferXML() {
        Document jdomDocument = getJdomDocument();
        Map<Long, OwnProduct> ownProductsList = new HashMap<>();

        if (jdomDocument != null) {

            Element rootElementOfxmlWithTradeOffer = jdomDocument.getRootElement();
            List<Element> offers = rootElementOfxmlWithTradeOffer.getChildren(OFFER_ELEMENT);

            for (int i = 0; i < offers.size(); i++) {
                OwnProduct ownProduct = getSingleProductFromOfferList(offers, i);
                ownProductsList.put(ownProduct.getId(), ownProduct);
            }
        }

        return ownProductsList;
    }

    private Document getJdomDocument() {
        SAXBuilder jdomBuilder = new SAXBuilder();
        try {
            return jdomBuilder.build(LINK_FOR_TRADE_OFFER_IN_XML);
        } catch (JDOMException e) {
            e.printStackTrace(); //todo zalogowac wyjatki
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return null;
        }
    }

    private OwnProduct getSingleProductFromOfferList(List<Element> offers, int i) {
        OwnProduct ownProduct = new OwnProduct();
        ownProduct.setId(Long.valueOf(offers.get(i).getAttributeValue(OFFERS_ELEMENT_ID_ATTRIBUTE)));
        ownProduct.setPrice(Integer.valueOf(offers.get(i).getAttributeValue(OFFERS_ELEMENT_PRICE_ATTRIBUTE)));
        ownProduct.setName(offers.get(i).getChildText(OFFERS_ELEMENT_CHILD_NAME_ELEMENT));
        return ownProduct;
    }

}
