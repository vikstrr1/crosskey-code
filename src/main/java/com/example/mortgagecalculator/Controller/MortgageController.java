// MortgageController.java
package com.example.mortgagecalculator.Controller;

import org.springframework.ui.Model;
import com.example.mortgagecalculator.model.Prospect;
import com.example.mortgagecalculator.service.MortgageCalculatorService;
import com.example.mortgagecalculator.service.ProspectReaderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/")
public class MortgageController {

    private final MortgageCalculatorService mortgageCalculatorService;
    private final ProspectReaderService prospectReaderService;

    public MortgageController(MortgageCalculatorService mortgageCalculatorService,
                              ProspectReaderService prospectReaderService) {
        this.mortgageCalculatorService = mortgageCalculatorService;
        this.prospectReaderService = prospectReaderService;
    }

    @GetMapping("/")
    public String redirectToMortgage() {
        // Redirect to "/mortgage/"
        return "redirect:/mortgage/";
    }

    @GetMapping("/mortgage/")
    public String home() {
        return "home";
    }
    @GetMapping("/mortgage/calculate")
    public String calculateMonthlyPayments(Model model) {
        List<Prospect> prospects = prospectReaderService.readProspectsFromFile();
        List<String> monthlyPayments = mortgageCalculatorService.calculateMonthlyPayments(prospects);

        // Add any necessary attributes to the model
        model.addAttribute("monthlyPayments", monthlyPayments);

        // Return the name of the HTML template (calculate.html)
        return "calculate";
    }

   

    @GetMapping("/mortgage/prospects")
    public String showResults(Model model) {
        List<Prospect> prospects = prospectReaderService.readProspectsFromFile();

        // Add the list of prospects to the model
        model.addAttribute("prospects", prospects);

        // Return the name of the HTML template (results.html)
        
        return "prospects";
    }

    @PostMapping("/mortgage/addProspect")
    public String addProspect(Prospect newProspect, Model model) {
        // Perform any necessary validation or processing for the new prospect
        // For example, you might want to validate input fields, save to a database, etc.
        // Save the updated list of prospects back to the file
        // Create a new list containing only the new prospect
        List<Prospect> newProspects = Collections.singletonList(newProspect);

    // Save the new prospect to the file
        prospectReaderService.saveProspectsToFile( newProspects);

        
        // Add the new prospect to the list of prospects
        List<Prospect> prospects = prospectReaderService.readProspectsFromFile();
        

        
        // Update the model with the updated list of prospects
        model.addAttribute("prospects", prospects);
    
        // Redirect to the prospects page to display the updated list
        return "redirect:/mortgage/prospects";
    }
}