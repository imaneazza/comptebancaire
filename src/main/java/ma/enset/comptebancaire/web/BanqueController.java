package ma.enset.comptebancaire.web;

import ma.enset.comptebancaire.services.IBanqueMetier;
import ma.enset.comptebancaire.entities.Compte;
import ma.enset.comptebancaire.entities.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BanqueController {
    @Autowired
    private IBanqueMetier banqueMetier;
    @RequestMapping("/operations" )
    public String index(){
        return "operations";
    }
    @RequestMapping("/consultercompte")
    public String consulter(Model model , String codeCompte ,
         @RequestParam(name = "page" ,defaultValue = "0") int page
            , @RequestParam(name = "size" ,defaultValue = "5") int size){
        model.addAttribute("codeCompte",codeCompte);
         try{
            Compte cp=banqueMetier.consulterCompte(codeCompte);
             Page<Operation> operationPage=banqueMetier.listOperation(codeCompte,page,4);
             model.addAttribute("listeoperations",operationPage.getContent());
            int[] pages =new int[operationPage.getTotalPages()];
            model.addAttribute("pages",pages);
             model.addAttribute("compte",cp);
            }catch (Exception e){
            model.addAttribute("exception",e);
        }

        return "operations";
    }
    @RequestMapping(value = "/saveOperation" ,method = RequestMethod.POST)
public String saveOperation(Model model, String typeOperation,String codeCompte
            ,double montant , String codeCompte2){
       try{
           if(typeOperation.equals("VERS")){
               banqueMetier.verser(codeCompte,montant);
           }
           else if(typeOperation.equals("RETR")){
               banqueMetier.retirer(codeCompte,montant);
           }
           else {
               banqueMetier.virement(codeCompte,codeCompte2,montant);
           }
       }catch (Exception e){
           return "redirect:/consultercompte?codeCompte="+codeCompte+
                   "&error="+e.getMessage();
       }

        return "redirect:/consultercompte?codeCompte="+codeCompte;
}

}
