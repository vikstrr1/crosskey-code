package com.example.mortgagecalculator.service;

import org.springframework.stereotype.Service;
import com.example.mortgagecalculator.model.Prospect;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProspectReaderService {

    private static final String FILE_PATH = "/app/prospects.txt";

    public List<Prospect> readProspectsFromFile() {
        List<Prospect> prospects = new ArrayList<>();
        boolean firstLineSkipped = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (!firstLineSkipped) {
                    firstLineSkipped = true;
                    continue;
                }

                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (parts.length == 4) {
                    Prospect prospect = new Prospect(removeQuotes(parts[0].trim()),Double.parseDouble(parts[1].trim()),Double.parseDouble(parts[2].trim()), Integer.parseInt(parts[3].trim()));
                    /*prospect.setCustomerName(removeQuotes(parts[0].trim()));
                    prospect.setLoanAmount(Double.parseDouble(parts[1].trim()));
                    prospect.setYearlyInterestRate(Double.parseDouble(parts[2].trim()));
                    prospect.setLoanTermInYears(Integer.parseInt(parts[3].trim()));*/
                    prospects.add(prospect);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return prospects;
    }

    public void saveProspectsToFile(List<Prospect> newProspects) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            // If the file is empty, add the header
            if (Files.size(Path.of(FILE_PATH)) == 0) {
                writer.write("Customer,Total loan,Interest,Years");
                writer.newLine();
            }

            // Write only the newly added prospect to the file
            for (Prospect prospect : newProspects) {
                writer.write(String.format("%s,%s,%s,%s%n",
                        prospect.getCustomerName(),
                        prospect.getLoanAmount(),
                        prospect.getYearlyInterestRate(),
                        prospect.getLoanTermInYears()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed (e.g., log the error, show a message to the user)
        }
    }

    private String removeQuotes(String input) {
        return input.replaceAll("^\"|\"$", "").replaceAll(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", " ");
    }
}
