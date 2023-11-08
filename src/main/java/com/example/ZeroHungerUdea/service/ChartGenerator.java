package com.example.ZeroHungerUdea.service;
import com.example.ZeroHungerUdea.model.HouseHoldIncome;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.chart.plot.ThermometerPlot;
import org.jfree.chart.plot.WaferMapPlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import com.example.ZeroHungerUdea.model.HouseHoldIncome;
import com.example.ZeroHungerUdea.repository.HouseHoldIncomeFileRepositoryImpl;
import com.example.ZeroHungerUdea.repository.HouseHoldIncomeRepository;
import com.example.ZeroHungerUdea.repository.HouseHoldIncomeUsingFileRepositoryImpl;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.io.File;
import java.io.IOException;

import static com.example.ZeroHungerUdea.service.HouseHoldIncomeStatistics.*;

public class ChartGenerator {
    HouseHoldIncomeRepository incomeRepository = new HouseHoldIncomeFileRepositoryImpl ();
    List<HouseHoldIncome> incomeList = incomeRepository.findAllHouseHoldIncome();

    public static final Logger logger = LoggerFactory.getLogger(HouseHoldIncomeUsingFileRepositoryImpl.class);
    public static void generatePieChart(String title, PieDataset dataset, String filename) {
        JFreeChart chart = ChartFactory.createRingChart(title, dataset, true, true, false);
        RingPlot plot = (RingPlot) chart.getPlot();

// Oculta las líneas divisorias de sección
        plot.setSectionDepth(0.35); // Ajusta la profundidad como desees

// Define el color de fondo transparente para las secciones
        plot.setSectionOutlinePaint(new Color(0, 0, 0, 0)); // Establece el color transparente
        plot.setSectionOutlineStroke(new BasicStroke (0f)); // Establece el ancho de línea a cero


        try {
            // Obtén la ruta absoluta hacia el directorio "report" en tu proyecto
            String reportDirectory = System.getProperty("user.dir") + File.separator + "report";

            // Crea el directorio "report" si no existe
            File reportDir = new File(reportDirectory);
            if (!reportDir.exists()) {
                reportDir.mkdir();
            }

            // Guarda el gráfico en el directorio "report"
            ChartUtilities.saveChartAsPNG(new File(reportDirectory + File.separator + filename), chart, 500, 300);
        } catch (IOException e) {
            e.printStackTrace();
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
        // Obtén los valores de salario, miembros, habitaciones y comidas desde tus métodos
        List<HouseHoldIncome> incomeList = null; // Obtén la lista de ingresos de alguna manera
        double averageSalary = calculateAverageSalary(incomeList);
        double averageMembers = calculateAverageMembers(incomeList);
        double averageBedrooms = calculateAverageBedrooms(incomeList);
        double averageMeals = calculateAverageMeals(incomeList);

        // Genera y guarda los diagramas circulares para cada estadística
        generatePieChart("Salary Chart", createPieDataset(averageSalary, 100 - averageSalary, 0), "salary_chart.png");
        generatePieChart("Members Chart", createPieDataset(averageMembers, 100 - averageMembers, 0), "members_chart.png");
        generatePieChart("Bedrooms Chart", createPieDataset(averageBedrooms, 100 - averageBedrooms, 0), "bedrooms_chart.png");
        generatePieChart("Meals Chart", createPieDataset(averageMeals, 100 - averageMeals, 0), "meals_chart.png");
    }
}
