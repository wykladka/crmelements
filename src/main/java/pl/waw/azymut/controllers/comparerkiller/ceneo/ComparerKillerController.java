package pl.waw.azymut.controllers.comparerkiller.ceneo;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.waw.azymut.models.comparerkiller.OwnProduct;
import pl.waw.azymut.models.comparerkiller.ceneo.entity.BindingForOwnProductWithMultipleCeneos;
import pl.waw.azymut.services.comparerkiller.ComparerKillerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.waw.azymut.services.comparerkiller.ceneo.CeneoKillerService;

import java.util.List;

@Controller
public class ComparerKillerController {

    private final ComparerKillerService comparerKillerService;
    private final CeneoKillerService ceneoKillerService;

    @Autowired //todo polaczyc comparerkiller service z ceneokiller service
    public ComparerKillerController(ComparerKillerService comparerKillerService,
                                    CeneoKillerService ceneoKillerService) {
        this.comparerKillerService = comparerKillerService;
    }

    @RequestMapping(value = "/comparerKiller/showAllOwnProducts")
    public String showAllProducts(Model model) {
        List<OwnProduct> ownProductList = comparerKillerService.getAllOwnProducts();
        model.addAttribute("ownproductlist", ownProductList);
        return "showallownproducts";
    }

    @RequestMapping(value = "/runReportGroup")
    public String runReportGroup(@RequestParam(value = "id") String id, Model model) {
        return null;
    }

    @RequestMapping(value = "/simpleBindings", method = RequestMethod.GET) //po polsku powiarz produkt z ceneo
    public String showBindings(@ModelAttribute("model") ModelMap model) {
        model.put("simpleBindingList", ceneoKillerService.getAllSimpleBindings());
        return "simplebindings";
    }

    @RequestMapping(value = "/createSimpleBinding", method = RequestMethod.POST) //po polsku powiarz produkt z ceneo
    public String createSimpleBinding(@ModelAttribute("simpleBinding") BindingForOwnProductWithMultipleCeneos simpleBinding) {
        ceneoKillerService.addSimpleBinding(simpleBinding);
        return "redirect:simpleBindings";
    }

//    @RequestMapping(value = "/createRuleForProduct", method = RequestMethod.POST)
//    public String createRuleForProduct(@ModelAttribute(""))
}