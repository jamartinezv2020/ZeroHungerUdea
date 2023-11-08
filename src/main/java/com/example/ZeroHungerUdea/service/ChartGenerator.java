package com.example.ZeroHungerUdea.service;

import com.example.ZeroHungerUdea.model.HouseHoldIncome;
import com.example.ZeroHungerUdea.repository.HouseHoldIncomeFileRepositoryImpl;
import com.example.ZeroHungerUdea.repository.HouseHoldIncomeRepository;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.RingPlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ChartGenerator {
    private final HouseHoldIncomeRepository incomeRepository = new HouseHoldIncomeFileRepositoryImpl();

    private static final Logger logger = LoggerFactory.getLogger(ChartGenerator.class);

    public void generatePieChart(String title, PieDataset dataset, String filename) {
        JFreeChart chart = ChartFactory.createRingChart(title, dataset, true, true, false);
        RingPlot plot = (RingPlot) chart.getPlot();

        plot.setSectionDepth(0.35);
        plot.setSectionOutlinePaint(new Color(0, 0, 0, 0));
        plot.setSectionOutlineStroke(new BasicStroke(0f));

        try {
            String reportDirectory = System.getProperty("user.dir") + File.separator + "report";
            File reportDir = new File(reportDirectory);

            if (!reportDir.exists()) {
                reportDir.mkdir();
            }

            ChartUtilities.saveChartAsPNG(new File(reportDirectory + File.separator + filename), chart, 500, 300);
        } catch (IOException e) {
            logger.error("Error al guardar el gráfico: " + e.getMessage());
        }
    }

    public static PieDataset createPieDataset(double value1, double value2, double value3) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Value 1", value1);
        dataset.setValue("Value 2", value2);
        dataset.setValue("Value 3", value3);
        return dataset;
    }

    public static void main(String[] args) {
        ChartGenerator chartGenerator = new ChartGenerator();
        List incomeList = (List) chartGenerator.incomeRepository.findAllHouseHoldIncome();

        double averageSalary = HouseHoldIncomeStatistics.calculateAverageSalary((java.util.List<HouseHoldIncome>) incomeList);
        double averageMembers = HouseHoldIncomeStatistics.calculateAverageMembers((java.util.List<HouseHoldIncome>) incomeList);
        double averageBedrooms = HouseHoldIncomeStatistics.calculateAverageBedrooms((java.util.List<HouseHoldIncome>) incomeList);
        double averageMeals = HouseHoldIncomeStatistics.calculateAverageMeals((java.util.List<HouseHoldIncome>) incomeList);

        chartGenerator.generatePieChart("Salary Chart", createPieDataset(averageSalary, 100 - averageSalary, 0), "salary_chart.png");
        chartGenerator.generatePieChart("Members Chart", createPieDataset(averageMembers, 100 - averageMembers, 0), "members_chart.png");
        chartGenerator.generatePieChart("Bedrooms Chart", createPieDataset(averageBedrooms, 100 - averageBedrooms, 0), "bedrooms_chart.png");
        chartGenerator.generatePieChart("Meals Chart", createPieDataset(averageMeals, 100 - averageMeals, 0), "meals_chart.png");
    }
}

