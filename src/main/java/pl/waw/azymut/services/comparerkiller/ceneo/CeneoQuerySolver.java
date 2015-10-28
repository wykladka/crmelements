package pl.waw.azymut.services.comparerkiller.ceneo;

import pl.waw.azymut.models.comparerkiller.OwnProduct;
import pl.waw.azymut.models.comparerkiller.ceneo.dto.CeneoBid;
import pl.waw.azymut.models.comparerkiller.ceneo.dto.CeneoOffer;
import pl.waw.azymut.models.comparerkiller.ceneo.entity.BindingForOwnProductWithMultipleCeneos;
import pl.waw.azymut.models.comparerkiller.ceneo.entity.BindingGroup;
import pl.waw.azymut.models.comparerkiller.ceneo.entity.BindingRule;
import pl.waw.azymut.services.comparerkiller.OwnProductsXmlProductListParser;

import java.io.IOException;
import java.util.*;

//todo to bardziej group solver
public class CeneoQuerySolver {

    private Map<Long, OwnProduct> ownProductList;
    private BindingGroup bindingGroup;
    private BindingRule bindingRule;
    private List<BindingForOwnProductWithMultipleCeneos> bindingForOwnProductWithMultipleCeneosList;

    public Map<OwnProduct, Set<CeneoBid>> run(BindingGroup bindingGroup) throws IOException {
        Map<OwnProduct, Set<CeneoBid>> resultSet = new HashMap<>();

        this.bindingGroup = bindingGroup;
        populateOwnProductList();
        bindingRule = bindingGroup.getBindingRule();
        bindingForOwnProductWithMultipleCeneosList = bindingGroup.getBindingList(); // z tego mam swoje id

        OwnProduct ownProduct;
        CeneoOfferResolver ceneoOfferResolver = new CeneoOfferResolver();
        CeneoOffer ceneoOffer = new CeneoOffer();

        CeneoOfferToBidsParser cp = new CeneoOfferToBidsParser();

        //operuje na calej grupie
        for (BindingForOwnProductWithMultipleCeneos b : bindingForOwnProductWithMultipleCeneosList) {

            //dostaje pojedyncze ceneo
            for (Long x : b.getCeneoIdList()) {
                ceneoOffer.addBids(cp.parse("http://ceneo.pl/" + x));
            }
            Set<CeneoBid> tempBid = new HashSet<>();
            tempBid = ceneoOfferResolver.runOfferResolver(ceneoOffer, bindingRule);
            resultSet.put(ownProductList.get(new Long(b.getOwnProductId())), tempBid);
        } // dostaje jedno ceneo offer, a mam k... cala trzodke

        return resultSet;
    }

    private void populateOwnProductList() {
        OwnProductsXmlProductListParser opxplp = new OwnProductsXmlProductListParser();
        ownProductList = opxplp.retriveProductListFromTradeOfferXML();
    }


}
