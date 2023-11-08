package com.example.ZeroHungerUdea.service;

import com.example.ZeroHungerUdea.model.HouseHoldIncome;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.example.ZeroHungerUdea.service.HouseHoldIncomeStatistics.*;

public class PDFReportGenerator {

    private static final Logger logger = LoggerFactory.getLogger(PDFReportGenerator.class);

    public static void generatePDFReport(List<HouseHoldIncome> incomeList) {
        // Nombre del archivo PDF con la fecha actual
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String currentDate = sdf.format(new Date());
        String fileName = "ReporteEstadisticoPRICA_" + currentDate + ".pdf";

        // Crear un nuevo documento PDF
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);

        try {
            // Crear el archivo PDF con el nombre generado
            PdfWriter.getInstance(document, new FileOutputStream(fileName));

            // Abrir el documento para escribir
            document.open();

            // Agregar contenido al documento
            addReportContent(document, incomeList);

            // Cerrar el documento
            document.close();

            logger.info("Informe PDF generado: " + fileName);
        } catch (DocumentException | IOException e) {
            logger.error("Error al generar el informe PDF", e);
        }
    }

    private static void addReportContent(Document document, List<HouseHoldIncome> incomeList)
            throws DocumentException {
        // Agregar título al documento
        Paragraph header = new Paragraph("Plataforma de Recursos Institucionales y Consultas Avanzadas (PRICA)");
        document.add(header);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        document.add(new Paragraph("JOSE ALFREDO MARTÍNEZ VALDÉS"));
        document.add(new Paragraph("jose.martinez7@udea.edu.co"));
        document.add(new Paragraph("Teléfono: 3017600703"));
        Paragraph dateTime = new Paragraph("Palmira(Valle del Cauca), " + currentDateTime);
        document.add(dateTime);
        document.add(new Paragraph("                                                                         "));
        document.add(new Paragraph("***************************************************************************"));

        // Agregar contenido al documento
        document.add(new Paragraph("Mostrando los datos estadísticos"));
        document.add(new Paragraph("Sum Number of Families: " + sumNumberOfFamilies(incomeList)));
        document.add(new Paragraph("S A L A R I E S: "));
        document.add(new Paragraph("Average Salary: " + calculateAverageSalary(incomeList)));
        document.add(new Paragraph("Median Salary: " + calculateMedianSalary(incomeList)));
        document.add(new Paragraph("Mode Salary: " + calculateModeSalary(incomeList)));
        document.add(new Paragraph("Minimum Salary: " + calculateMinimumSalary(incomeList)));
        document.add(new Paragraph("M E M B E R S: "));
        document.add(new Paragraph("Average Members: " + calculateAverageMembers(incomeList)));
        document.add(new Paragraph("Median Members: " + calculateMedianMembers(incomeList)));
        document.add(new Paragraph("Mode Members: " + calculateModeMembers(incomeList)));
        document.add(new Paragraph("B E D R O O M S: "));
        document.add(new Paragraph("Average Bedrooms: " + calculateAverageBedrooms(incomeList)));
        document.add(new Paragraph("Median Bedrooms: " + calculateMedianBedrooms(incomeList)));
        document.add(new Paragraph("Mode Bedrooms: " + calculateModeBedrooms(incomeList)));
        document.add(new Paragraph("M E A L S: "));
        document.add(new Paragraph("Average Meals: " + calculateAverageMeals(incomeList)));
        document.add(new Paragraph("Median Meals: " + calculateMedianMeals(incomeList)));
        document.add(new Paragraph("Mode Meals: " + calculateModeMeals(incomeList)));
    }

    // Los métodos de cálculo estadístico y otros métodos necesarios deben estar definidos en la misma clase o importados
}
