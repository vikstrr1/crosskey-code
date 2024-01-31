package com.example.mortgagecalculator.Controller;

import org.springframework.ui.Model;
import com.example.mortgagecalculator.model.Prospect;
import com.example.mortgagecalculator.service.MortgageCalculatorService;
import com.example.mortgagecalculator.service.ProspectReaderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        return "redirect:/mortgage/calculate";
    }

    @GetMapping("/mortgage/")
    public String home() {
        return "redirect:/mortgage/calculate";
    }

    @GetMapping("/mortgage/calculate")
    public String calculateMonthlyPayments(Model model) {
        List<Prospect> prospects = prospectReaderService.readProspectsFromDatabase();
        List<String> monthlyPayments = mortgageCalculatorService.calculateMonthlyPayments(prospects);

        // Add any necessary attributes to the model
        model.addAttribute("monthlyPayments", monthlyPayments);

        // Return the name of the HTML template (calculate.html)
        return "calculate";
    }

    @GetMapping("/mortgage/prospects")
    public String showResults(Model model) {
        List<Prospect> prospects = prospectReaderService.readProspectsFromDatabase();

        // Add the list of prospects to the model
        model.addAttribute("prospects", prospects);

        // Return the name of the HTML template (results.html)
        return "prospects";
    }

    @GetMapping("/mortgage/prospects/{id}")
    public String viewProspect(@PathVariable String id, Model model) {
        // Find the prospect by ID
        Optional<Prospect> optionalProspect = prospectReaderService.readProspectById(id);

        // Check if the prospect exists
        if (optionalProspect.isPresent()) {
            // Add the prospect to the model
            model.addAttribute("prospect", optionalProspect.get());

            // Return the name of the HTML template (prospect.html)
            return "prospect";
        } else {
            // Redirect to the prospects page if the prospect is not found
            return "redirect:/mortgage/prospects";
        }
    }

    @PostMapping("/mortgage/addProspect")
    public String addProspect(Prospect newProspect, Model model) {
        // Perform any necessary validation or processing for the new prospect
        // For example, you might want to validate input fields, save to a database, etc.
        // Save the updated list of prospects back to the file
        // Create a new list containing only the new prospect
        List<Prospect> newProspects = Collections.singletonList(newProspect);

        // Save the new prospect to the database
        prospectReaderService.saveProspectsToDatabase(newProspects);

        // Add the new prospect to the list of prospects
        List<Prospect> prospects = prospectReaderService.readProspectsFromDatabase();

        // Update the model with the updated list of prospects
        model.addAttribute("prospects", prospects);

        // Redirect to the prospects page to display the updated list
        return "redirect:/mortgage/calculate";
    }
}
