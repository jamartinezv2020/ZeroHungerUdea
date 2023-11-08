package com.example.ZeroHungerUdea;

import com.example.ZeroHungerUdea.model.HouseHoldIncome;
import com.example.ZeroHungerUdea.repository.HouseHoldIncomeFileRepositoryImpl;
import com.example.ZeroHungerUdea.repository.HouseHoldIncomeRepository;
import com.example.ZeroHungerUdea.service.ChartGenerator;
import com.example.ZeroHungerUdea.service.PDFReportGenerator;

import java.io.IOException;
import java.util.List;

import static com.example.ZeroHungerUdea.service.HouseHoldIncomeStatistics.*;

public class ApplicationRunner {

    public static List<HouseHoldIncome> incomeList;

    public static void main(String[] args) throws IOException {
// Puedes reemplazar estos valores con los datos adecuados
        double averageSalary = 50.0; // Por ejemplo
        double averageMembers = 30.0; // Por ejemplo
        double averageBedrooms = 2.5; // Por ejemplo
        double averageMeals = 2.0; // Por ejemplo

        ChartGenerator chartGenerator = new ChartGenerator();

        chartGenerator.generatePieChart("Salary Chart", chartGenerator.createPieDataset(averageSalary, 100 - averageSalary, 0), "salary_chart.png");
        chartGenerator.generatePieChart("Members Chart", chartGenerator.createPieDataset(averageMembers, 100 - averageMembers, 0), "members_chart.png");
        chartGenerator.generatePieChart("Bedrooms Chart", chartGenerator.createPieDataset(averageBedrooms, 100 - averageBedrooms, 0), "bedrooms_chart.png");
        chartGenerator.generatePieChart("Meals Chart", chartGenerator.createPieDataset(averageMeals, 100 - averageMeals, 0), "meals_chart.png");


        HouseHoldIncomeRepository incomeRepository = new HouseHoldIncomeFileRepositoryImpl ();
        List<HouseHoldIncome> incomeList = incomeRepository.findAllHouseHoldIncome();
        new ReportSaver (incomeList);
        //centralTendencyMeasures ();
        //generatePDFReportStatistic(incomeList);
        //statistics(incomeList);
        //generateReportPDF (incomeList);
        //HouseHoldIncomeStatistics.generatePDFReportStatistic (incomeList);
         // HouseHoldIncomeStatistics.generateReportPDF (incomeList);
        //HouseHoldIncomeStatistics.statistics (incomeList);
        printStatistics (incomeList);
        exportStatisticsToPDF (incomeList);
        PDFReportGenerator.generatePDFReport (incomeList);

    }
}


