package com.example.ZeroHungerUdea;

import com.example.ZeroHungerUdea.model.HouseHoldIncome;
import com.example.ZeroHungerUdea.repository.HouseHoldIncomeFileRepositoryImpl;
import com.example.ZeroHungerUdea.repository.HouseHoldIncomeRepository;
import com.example.ZeroHungerUdea.service.ChartGenerator;
import com.example.ZeroHungerUdea.service.PDFReportGenerator;
import com.example.ZeroHungerUdea.service.ReportSaver;
import org.apache.pdfbox.pdmodel.PDDocument;


import java.io.IOException;
import java.util.List;

import static com.example.ZeroHungerUdea.service.HouseHoldIncomeStatistics.*;

public class ApplicationRunner {

    public static void main(String[] args) {
        try {
            List<HouseHoldIncome> incomeList = loadHouseHoldIncomeData();

            ChartGenerator chartGenerator = new ChartGenerator();
            generateCharts(chartGenerator);

            printStatistics(incomeList);
            exportStatisticsToPDF(incomeList);

            ReportSaver reportSaver = new ReportSaver (incomeList);

        } catch (IOException e) {
            handleIOException(e);
        }
    }

    private static List<HouseHoldIncome> loadHouseHoldIncomeData() throws IOException {
        HouseHoldIncomeRepository incomeRepository = new HouseHoldIncomeFileRepositoryImpl();
        return incomeRepository.findAllHouseHoldIncome();
    }

    private static void generateCharts(ChartGenerator chartGenerator) {
        double averageSalary = 50.0;
        double averageMembers = 30.0;
        double averageBedrooms = 2.5;
        double averageMeals = 2.0;

        generatePieChart(chartGenerator, "Salary Chart", averageSalary, "salary_chart.png");
        generatePieChart(chartGenerator, "Members Chart", averageMembers, "members_chart.png");
        generatePieChart(chartGenerator, "Bedrooms Chart", averageBedrooms, "bedrooms_chart.png");
        generatePieChart(chartGenerator, "Meals Chart", averageMeals, "meals_chart.png");
    }

    private static void generatePieChart(ChartGenerator chartGenerator, String chartTitle, double value, String filename) {
        chartGenerator.generatePieChart(chartTitle, chartGenerator.createPieDataset(value, 100 - value, 0), filename);
    }

    private static void printStatistics(List<HouseHoldIncome> incomeList) {
        // Implement your statistical printing logic here
    }

    private static void exportStatisticsToPDF(List<HouseHoldIncome> incomeList) {
        PDFReportGenerator.generatePDFReport(incomeList);
    }
    private static void exportReportSaverToPDF(List<HouseHoldIncome> incomeList){
        ReportSaver.savePDFReport ();
    }
    private static void handleIOException(IOException e) {
        // Handle the IOException, e.g., log it or display an error message
        e.printStackTrace();
    }
}
