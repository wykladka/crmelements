package pl.waw.azymut.models.comparerkiller.ceneo.dto;

public class CeneoBid implements Comparable<CeneoBid> {

    private Integer ceneoOfferId;
    private Integer bidHeight;
    private String shopId;
    private String urlToOffer;

    public CeneoBid(Integer ceneoOfferId, Integer bidHeight, String shopId, String urlToOffer) {
        this.ceneoOfferId = ceneoOfferId;
        this.bidHeight = bidHeight;
        this.shopId = shopId;
        this.urlToOffer = urlToOffer;
    }

    public Integer getCeneoOfferId() {
        return ceneoOfferId;
    }

    public Integer getBidHeight() {
        return bidHeight;
    }

    public String getShopId() {
        return shopId;
    }

    public String getUrlToOffer() {
        return urlToOffer;
    }

    public int compareTo(CeneoBid ceneoBid) {
        return (bidHeight < ceneoBid.getBidHeight()) ? -1 : (bidHeight > ceneoBid.getBidHeight()) ? 1 : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CeneoBid ceneoBid = (CeneoBid) o;

        if (!ceneoOfferId.equals(ceneoBid.ceneoOfferId)) return false;
        if (!bidHeight.equals(ceneoBid.bidHeight)) return false;
        if (!shopId.equals(ceneoBid.shopId)) return false;
        return urlToOffer.equals(ceneoBid.urlToOffer);
    }

    @Override
    public int hashCode() {
        int result = ceneoOfferId.hashCode();
        result = 31 * result + bidHeight.hashCode();
        result = 31 * result + shopId.hashCode();
        result = 31 * result + urlToOffer.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CeneoBid{" +
                "ceneoOfferId=" + ceneoOfferId +
                ", bidHeight=" + bidHeight +
                ", shopId='" + shopId + '\'' +
                ", urlToOffer='" + urlToOffer + '\'' +
                '}';
    }

}