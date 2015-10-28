package pl.waw.azymut.services.comparerkiller.ceneo;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import pl.waw.azymut.models.comparerkiller.ceneo.entity.BindingRule;
import pl.waw.azymut.models.comparerkiller.ceneo.dto.CeneoBid;
import pl.waw.azymut.models.comparerkiller.ceneo.dto.CeneoOffer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CeneoOfferResolver {

    private CeneoOffer ceneoOffer;
    private Multimap<Integer, CeneoBid> bidsOffersMultiMap;

    public Set<CeneoBid> runOfferResolver(CeneoOffer ceneoOffer, BindingRule bindingRule) {
        this.ceneoOffer = ceneoOffer;
        mapOffersToBidHeightAndOffer();
        return getCeneoBids(bindingRule);
    }

    private Set<CeneoBid> getCeneoBids(BindingRule bindingRule) {
        Integer significatDifference = bindingRule.getSignificantDifference();
       // todo rzucic wyjatkiem jesli potrzbny signifikant difference a nie jest podany
        switch (bindingRule.getComparisonCondition()) {
            case EQUALS_TO:
                return getBidsEqualTo(significatDifference);
            case EQUALS_OR_HIGHER_TO:
                return getBidsHigherOrEqualTo(significatDifference);
            case EQUALS_OR_LOWER_TO:
                return getBidsLowerOrEqualTo(significatDifference);
            case LOWER_THAN:
                return getBidsLowerThen(significatDifference);
            case HIGHER_THEN:
                return getBidsHigherThen(significatDifference);
            case LOWEST:
                return getLowestBids();
            case HIGHEST:
                return getHighestBids();
            default:
                return null;
        }
    }

    private void mapOffersToBidHeightAndOffer() {
        bidsOffersMultiMap = ArrayListMultimap.create();
        for (CeneoBid bid : ceneoOffer.getBids()) {
            bidsOffersMultiMap.put(bid.getBidHeight(), bid); //todo nazwa
        }
    }

    public Set<CeneoBid> getLowestBids() {
        Integer i = Collections.min(bidsOffersMultiMap.keySet());
        return new HashSet<CeneoBid>(bidsOffersMultiMap.get(i));
    }

    public Set<CeneoBid> getHighestBids() {
        Integer i = Collections.max(bidsOffersMultiMap.keySet());
        return new HashSet<CeneoBid>(bidsOffersMultiMap.get(i));
    }

    public Set<CeneoBid> getBidsHigherThen(Integer requestedBid) {
        Set<CeneoBid> result = new HashSet<CeneoBid>();

        for (Integer offerBid : bidsOffersMultiMap.keySet()) {
            if (offerBid > requestedBid) result.addAll(bidsOffersMultiMap.get(offerBid));
        }
        return result;
    }

    public Set<CeneoBid> getBidsLowerThen(Integer requestedBid) {
        Set<CeneoBid> result = new HashSet<CeneoBid>();

        for (Integer offerBid : bidsOffersMultiMap.keySet()) {
            if (offerBid < requestedBid) result.addAll(bidsOffersMultiMap.get(offerBid));
        }
        return result;
    }

    public Set<CeneoBid> getBidsEqualTo(Integer bidHeight) {
        return new HashSet<CeneoBid>(bidsOffersMultiMap.get(bidHeight));
    }

    public Set<CeneoBid> getBidsHigherOrEqualTo(Integer requestedBid) {
        Set<CeneoBid> result = new HashSet<CeneoBid>();

        for (Integer offerBid : bidsOffersMultiMap.keySet()) {
            if (offerBid >= requestedBid) result.addAll(bidsOffersMultiMap.get(offerBid));
        }
        return result;
    }

    public Set<CeneoBid> getBidsLowerOrEqualTo(Integer requestedBid) {
        Set<CeneoBid> result = new HashSet<CeneoBid>();

        for (Integer offerBid : bidsOffersMultiMap.keySet()) {
            if (offerBid <= requestedBid) result.addAll(bidsOffersMultiMap.get(offerBid));
        }
        return result;
    }
}