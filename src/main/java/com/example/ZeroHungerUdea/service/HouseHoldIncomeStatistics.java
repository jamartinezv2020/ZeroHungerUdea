package com.example.ZeroHungerUdea.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import com.example.ZeroHungerUdea.model.HouseHoldIncome;
import com.example.ZeroHungerUdea.repository.HouseHoldIncomeInMemoryRepositoryImpl;
import com.example.ZeroHungerUdea.repository.HouseHoldIncomeRepository;
import com.example.ZeroHungerUdea.repository.HouseHoldIncomeUsingFileRepositoryImpl;
import com.example.ZeroHungerUdea.repository.HouseHoldIncomeFileRepositoryImpl;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class HouseHoldIncomeStatistics {

    public static final Logger logger = LoggerFactory.getLogger(HouseHoldIncomeUsingFileRepositoryImpl.class);

   /* public static double formatDouble (double value) {
        return Double.parseDouble (String.format ("%.3f", value));
    }

*/
    public static double calculateAverageSalary (List<HouseHoldIncome> incomeList) {
        logger.info("Calculando el salario promedio");
        return incomeList.stream ()
                .mapToDouble (HouseHoldIncome::salario)
                .average ()
                .orElse (0.0);
    }

    public static double calculateAverageMembers (List<HouseHoldIncome> incomeList) {
        logger.info("Calculando la conformación promedio del núcleo familiar");
        return incomeList.stream ()
                .mapToDouble (HouseHoldIncome::miembros)
                .average ()
                .orElse (0.0);
    }

    public static double calculateAverageBedrooms (List<HouseHoldIncome> incomeList) {
        logger.info("Calculando el proemdio de habitaciones por familias");
        return incomeList.stream ()
                .mapToDouble (HouseHoldIncome::habitaciones)
                .average ()
                .orElse (0.0);
    }

    public static int sumNumberOfFamilies (List<HouseHoldIncome> incomeList) {
        logger.info("Calculando el total de familias");
        int size = incomeList.stream ()
                .toList ().size ();
        return size;

    }

    public static double calculateAverageMeals (List<HouseHoldIncome> incomeList) {
        logger.info("Calculando el proemdio de comidas diarias por familias");
        return incomeList.stream ()
                .mapToDouble (HouseHoldIncome::comidas)
                .average ()
                .orElse (0.0);
    }

    public static double calculateMedianSalary (List<HouseHoldIncome> incomeList) {
        logger.info("Calculando la mediana de salarios");
        List<Double> salaries = incomeList.stream ()
                .map (HouseHoldIncome::salario)
                .sorted ()
                .collect (Collectors.toList ());

        int size = salaries.size ();
        if (size % 2 == 0) {
            int mid = size / 2;
            return (salaries.get (mid - 1) + salaries.get (mid)) / 2.0;
        } else {
            return salaries.get (size / 2);
        }
    }

    public static Double calculateMedianMembers (List<HouseHoldIncome> incomeList) {
        logger.info("Calculando la mediana del total de miembros de una familia");
        List<Integer> members = incomeList.stream ()
                .map (HouseHoldIncome::miembros)
                .sorted ()
                .toList ();

        double size = members.size ();
        if (size % 2 == 0) {
            double mid = size / 2;
            return (members.get ((int) (mid - 1)) + members.get ((int) mid)) / 2.0;
        } else {
            return Double.valueOf (members.get ((int) (size / 2)));
        }
    }

    public static Double calculateMedianBedrooms (List<HouseHoldIncome> incomeList) {
        logger.info("Calculando la mediana de habitaciones por familias");
        List<Integer> bedrooms = incomeList.stream ()
                .map (HouseHoldIncome::habitaciones)
                .sorted ()
                .toList ();

        double size = bedrooms.size ();
        if (size % 2 == 0) {
            double mid = size / 2;
            return (bedrooms.get ((int) (mid - 1)) + bedrooms.get ((int) mid)) / 2.0;
        } else {
            return Double.valueOf (bedrooms.get ((int) (size / 2)));
        }
    }

    public static Double calculateMedianMeals (List<HouseHoldIncome> incomeList) {
        logger.info("Calculando la mediana de comidas por familias");
        List<Integer> meals = incomeList.stream ()
                .map (HouseHoldIncome::comidas)
                .sorted ()
                .toList ();

        double size = meals.size ();
        if (size % 2 == 0) {
            double mid = size / 2;
            return (meals.get ((int) (mid - 1)) + meals.get ((int) mid)) / 2.0;
        } else {
            return Double.valueOf (meals.get ((int) (size / 2)));
        }
    }

    public static double calculateModeSalary (List<HouseHoldIncome> incomeList) {
        logger.info("Calculando la moda de salarios por familias");
        Map<Double, Long> salaryCounts = incomeList.stream ()
                .collect (Collectors.groupingBy (HouseHoldIncome::salario, Collectors.counting ()));

        long maxCount = salaryCounts.values ().stream ().max (Long::compareTo).orElse (0L);

        return salaryCounts.entrySet ().stream ()
                .filter (entry -> entry.getValue () == maxCount)
                .map (Map.Entry::getKey)
                .findFirst ()
                .orElse (0.0);
    }

    public static int calculateModeMembers (List<HouseHoldIncome> incomeList) {
        Map<Integer, Long> memberCounts = incomeList.stream ()
                .collect (Collectors.groupingBy (HouseHoldIncome::miembros, Collectors.counting ()));

        long maxCount = memberCounts.values ().stream ().max (Long::compareTo).orElse (0L);

        return memberCounts.entrySet ().stream ()
                .filter (entry -> entry.getValue () == maxCount)
                .map (Map.Entry::getKey)
                .findFirst ()
                .orElse (0);
    }


    public static int calculateModeBedrooms (List<HouseHoldIncome> incomeList) {
        Map<Integer, Long> bedroomCounts = incomeList.stream ()
                .collect (Collectors.groupingBy (HouseHoldIncome::habitaciones, Collectors.counting ()));

        long maxCount = bedroomCounts.values ().stream ().max (Long::compareTo).orElse (0L);

        return bedroomCounts.entrySet ().stream ()
                .filter (entry -> entry.getValue () == maxCount)
                .map (Map.Entry::getKey)
                .findFirst ()
                .orElse (0);
    }

    public static int calculateModeMeals (List<HouseHoldIncome> incomeList) {
        Map<Integer, Long> mealCounts = incomeList.stream ()
                .collect (Collectors.groupingBy (HouseHoldIncome::comidas, Collectors.counting ()));

        long maxCount = mealCounts.values ().stream ().max (Long::compareTo).orElse (0L);

        return mealCounts.entrySet ().stream ()
                .filter (entry -> entry.getValue () == maxCount)
                .map (Map.Entry::getKey)
                .findFirst ()
                .orElse (0);
    }

    public static double calculateMinimumSalary (List<HouseHoldIncome> incomeList) {
        return incomeList.stream ()
                .mapToDouble (HouseHoldIncome::salario)
                .min ()
                .orElse (0.0);
    }

    public static double calculateMaximumSalary (List<HouseHoldIncome> incomeList) {
        return incomeList.stream ()
                .mapToDouble (HouseHoldIncome::salario)
                .max ()
                .orElse (0.0);
    }


    public static void savePDFReport (PDDocument document) {
        try {
            document.save ("informe.pdf");
            document.close ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }


    public static void generateStatisticsCharts (List<HouseHoldIncome> incomeList) {
        logger.info("Generando las gráficas estadísticas");
        try {
            OutputStream chartImageStream = new FileOutputStream ("chart.png");
            JFreeChart chart = null;
            ChartUtils.saveChartAsPNG (new File ("chart.png"), chart, 600, 400);
            System.out.println ("Statistics charts generated successfully.");
            // Resto del código para generar las gráficas
            DefaultCategoryDataset dataset = new DefaultCategoryDataset ();
            dataset.addValue (calculateAverageSalary (incomeList), "Statistics", "Average Salary");
            dataset.addValue (calculateMedianSalary (incomeList), "Statistics", "Median Salary");
            dataset.addValue (calculateModeSalary (incomeList), "Statistics", "Mode Salary");

            chart = ChartFactory.createBarChart (
                    "Income Statistics", "Statistic", "Value", dataset, PlotOrientation.VERTICAL, true, true, false
            );
        } catch (IOException e) {
            logger.error("Error al analizar los datos de ingresos del hogar: " + e.getMessage ());
            System.err.println ("Error generating statistics charts: " + e.getMessage ());
        }
    }

    public static void centralTendencyMeasures () throws IOException {
        logger.info("Generando las gráficas estadísticas");
        HouseHoldIncomeUsingFileRepositoryImpl repository = new HouseHoldIncomeFileRepositoryImpl ();
        //HouseHoldIncomeUsingFileRepositoryImpl repository = new HouseHoldIncomeUsingFileRepositoryImpl();
        List<HouseHoldIncome> incomeList = repository.findAllHouseHoldIncome ();
        // Generar el informe en PDF
        generatePDFReport (incomeList);
        generateReportPDF (incomeList);

        // Generar gráficos estadísticos
        generateStatisticsCharts (incomeList);
        // Central Tendency Measures
        statistics (incomeList);
        logger.info("Ya tenemos las medidas de tendencia central");
    }


    private static void generatePDFReport (List<HouseHoldIncome> incomeList) {
        logger.info("Generando los reportes en PDF");
    }

    public static void statistics (List<HouseHoldIncome> incomeList) {
        logger.info("Mostrando los datos estadísticos");
        System.out.println ("Sum Number of Families: " + sumNumberOfFamilies (incomeList));
        System.out.println ("S A L A R I E S: ");
        System.out.println ("Average Salary: " + calculateAverageSalary (incomeList));
        System.out.println ("Median Salary: " + calculateMedianSalary (incomeList));
        System.out.println ("Mode Salary: " + calculateModeSalary (incomeList));
        System.out.println ("Minimum Salary: " + calculateMinimumSalary (incomeList));
        System.out.println ("Minimum Salary: " + calculateMinimumSalary (incomeList));
        System.out.println ("M E M B E R S: ");
        System.out.println ("Average Members: " + calculateAverageMembers (incomeList));
        System.out.println ("Median Members: " + calculateMedianMembers (incomeList));
        System.out.println ("Mode Members: " + calculateModeMembers (incomeList));
        System.out.println ("B E D R O O M S: ");
        System.out.println ("Average Bedrooms: " + calculateAverageBedrooms (incomeList));
        System.out.println ("Median Bedrooms: " + calculateMedianBedrooms (incomeList));
        System.out.println ("Mode Bedrooms: " + calculateModeBedrooms (incomeList));
        System.out.println ("M E A L S: ");
        System.out.println ("Average Meals: " + calculateAverageMeals (incomeList));
        System.out.println ("Median Meals: " + calculateMedianMeals (incomeList));
        System.out.println ("Mode Meals: " + calculateModeMeals (incomeList));

    }

    public static void generateReportPDF (List<HouseHoldIncome> incomeList) {
        try {
            System.out.println ("Income statistics PDF report generated successfully.");
            HouseHoldIncomeUsingFileRepositoryImpl repository = new HouseHoldIncomeFileRepositoryImpl ();
            //HouseHoldIncomeUsingFileRepositoryImpl repository = new HouseHoldIncomeUsingFileRepositoryImpl();
            incomeList = repository.findAllHouseHoldIncome ();
            PDDocument document = new PDDocument ();
            PDPage page = new PDPage (PDRectangle.A4);
            document.addPage (page);
            PDPageContentStream contentStream = new PDPageContentStream (document, page);

            // Generate statistics
            double averageSalary = calculateAverageSalary (incomeList);
            double medianSalary = calculateMedianSalary (incomeList);
            double modeSalary = calculateModeSalary (incomeList);
            double minimumSalary = calculateMinimumSalary (incomeList);
            double maximumSalary = calculateMaximumSalary (incomeList);

            // Create a dataset for a bar chart (example)
            DefaultCategoryDataset dataset = new DefaultCategoryDataset ();
            dataset.addValue (averageSalary, "Statistics", "Average Salary");
            dataset.addValue (medianSalary, "Statistics", "Median Salary");
            dataset.addValue (modeSalary, "Statistics", "Mode Salary");

            JFreeChart chart = ChartFactory.createBarChart (
                    "Income Statistics", "Statistic", "Value", dataset, PlotOrientation.VERTICAL, true, true, false
            );

            // Generate the chart image
            OutputStream chartImageStream = new FileOutputStream ("chart.png");
            ChartUtils ChartUtilities = null;
            ChartUtilities.writeChartAsPNG (chartImageStream, chart, 600, 400);

            // Add statistics and chart to the PDF
            contentStream.beginText ();
            contentStream.setFont (new PDType1Font (Standard14Fonts.FontName.HELVETICA), 16);
            contentStream.newLineAtOffset (100, 700);
            contentStream.showText ("Income Statistics Report");
            contentStream.newLineAtOffset (0, -20);
            contentStream.setFont (new PDType1Font (Standard14Fonts.FontName.HELVETICA), 12);
            contentStream.showText ("Average Salary: " + averageSalary);
            contentStream.newLineAtOffset (0, -20);
            contentStream.showText ("Median Salary: " + medianSalary);
            contentStream.newLineAtOffset (0, -20);
            contentStream.showText ("Mode Salary: " + modeSalary);
            contentStream.endText ();

            // Insert the chart image into the PDF
            PDImageXObject chartImage = PDImageXObject.createFromFile ("chart.png", document);
            contentStream.drawImage (chartImage, 100, 400);

            contentStream.close ();

            /* Save the PDF to a file */
            document.save ("IncomeStatisticsReport.pdf");
            document.close ();
        } catch (IOException e) {
            System.err.println ("Error generating the PDF report: " + e.getMessage ());
        }
    }
}

