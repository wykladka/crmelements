package pl.waw.azymut.services.comparerkiller.ceneo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.waw.azymut.models.comparerkiller.ceneo.dto.CeneoOffer;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CeneoOfferResolverTest {

    //todo zmienic nazyw na first test page, second itd...
    private static Document fourteenBidsPage;
    private static Document sevenBidsPage;
    private static Document fourBidsPage;
    private CeneoOfferToBidsParser ceneoOfferToBidsParser = new CeneoOfferToBidsParser();
    private CeneoOfferResolver ceneoOfferResolver; //todo czemu test sie tak nazywa jak z tego nie korzystam? :(

    @BeforeClass //todo wyciagnac metode stad - dublownaie
    public static void setUp() throws IOException {
        File file;
        URL url;

        url = CeneoOfferResolverTest.class.getResource("/testresources/etrex10samples/pierwszy7poz.html");
        file = new File(url.getFile());
        sevenBidsPage = Jsoup.parse(file, "UTF-8", "http://www.ceneo.pl/27523419");

        url = CeneoOfferResolverTest.class.getResource("/testresources/etrex10samples/drugi4poz.html");
        file = new File(url.getFile());
        fourBidsPage = Jsoup.parse(file, "UTF-8", "http://www.ceneo.pl/17697262");

        url = CeneoOfferResolverTest.class.getResource("/testresources/etrex10samples/trzeci14poz.html");
        file = new File(url.getFile());
        fourteenBidsPage = Jsoup.parse(file, "UTF-8", "http://www.ceneo.pl/14009616");
    }

    @Test
    public void properAmountOfBidsInOffer() {

        //given
        CeneoOffer ceneoOffer = new CeneoOffer();
        ceneoOffer.addBids(ceneoOfferToBidsParser.getBidsFromTestResource(fourBidsPage));
        ceneoOffer.addBids(ceneoOfferToBidsParser.getBidsFromTestResource(sevenBidsPage));
        ceneoOffer.addBids(ceneoOfferToBidsParser.getBidsFromTestResource(fourteenBidsPage));

        //when //todo czy to sa magi number i mam je wyrzucic na gore?
        Integer expectedTotalNumberOfBids = 25;
        Integer expectedNumberOfBidsForFourBidsPage = 4;
        Integer expectedNumberOfBidsForSevenBidsPage = 7;
        Integer expectedNumberOfBidsForFourteeBidsPage = 14;

        Integer totalNumberOfBids = ceneoOffer.getNumberOfBids();
        Integer numberOfBidsForFourBidsPage = ceneoOfferToBidsParser.getBidsFromTestResource(fourBidsPage).size();
        Integer numberOfBidsForSevenBidsPage = ceneoOfferToBidsParser.getBidsFromTestResource(sevenBidsPage).size();
        Integer numberOfBidsForFourteenBidsPage = ceneoOfferToBidsParser.getBidsFromTestResource(fourteenBidsPage).size();

        //then
        assertEquals(expectedTotalNumberOfBids, totalNumberOfBids);
        assertEquals(expectedNumberOfBidsForFourBidsPage, numberOfBidsForFourBidsPage);
        assertEquals(expectedNumberOfBidsForSevenBidsPage, numberOfBidsForSevenBidsPage);
        assertEquals(expectedNumberOfBidsForFourteeBidsPage, numberOfBidsForFourteenBidsPage);
    }


    @Test
    public void shouldReturnProperNumberOfBids() {

        //given
        CeneoOffer ceneoOffer = new CeneoOffer();
        ceneoOffer.addBids(ceneoOfferToBidsParser.getBidsFromTestResource(fourBidsPage));
        ceneoOffer.addBids(ceneoOfferToBidsParser.getBidsFromTestResource(sevenBidsPage));
        ceneoOffer.addBids(ceneoOfferToBidsParser.getBidsFromTestResource(fourteenBidsPage));

        //when
//        Integer expectedNumberOfLowestbids = 1;
//        Integer numberOfLowestBids = ceneoOffer.getBids();

        //then

    }

}