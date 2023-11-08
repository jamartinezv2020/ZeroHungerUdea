package com.example.ZeroHungerUdea.service;

import com.example.ZeroHungerUdea.model.HouseHoldIncome;
import com.example.ZeroHungerUdea.repository.HouseHoldIncomeFileRepositoryImpl;
import com.example.ZeroHungerUdea.repository.HouseHoldIncomeUsingFileRepositoryImpl;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HouseHoldIncomeStatistics {

    public static final Logger logger = LoggerFactory.getLogger(HouseHoldIncomeUsingFileRepositoryImpl.class);

    // ...
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



    public static void exportStatisticsToPDF(List<HouseHoldIncome> incomeList) {
        // Obtén la fecha actual para incluirla en el nombre del archivo
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String currentDate = sdf.format(new Date());

        // Nombre del archivo PDF con la fecha actual
        String fileName = "ReporteEstadistico_" + currentDate + ".pdf";

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            logger.info("Mostrando los datos estadísticos");

            // Agrega los resultados al informe
            addResultToPDF(document, "Sum Number of Families: " + sumNumberOfFamilies(incomeList));
            addResultToPDF(document, "S A L A R I E S:");
            addResultToPDF(document, "Average Salary: " + calculateAverageSalary(incomeList));
            addResultToPDF(document, "Median Salary: " + calculateMedianSalary(incomeList));
            addResultToPDF(document, "Mode Salary: " + calculateModeSalary(incomeList));
            addResultToPDF(document, "Minimum Salary: " + calculateMinimumSalary(incomeList));
            addResultToPDF(document, "Maximum Salary: " + calculateMaximumSalary(incomeList));
            addResultToPDF(document, "M E M B E R S:");
            addResultToPDF(document, "Average Members: " + calculateAverageMembers(incomeList));
            addResultToPDF(document, "Median Members: " + calculateMedianMembers(incomeList));
            addResultToPDF(document, "Mode Members: " + calculateModeMembers(incomeList));
            addResultToPDF(document, "B E D R O O M S:");
            addResultToPDF(document, "Average Bedrooms: " + calculateAverageBedrooms(incomeList));
            addResultToPDF(document, "Median Bedrooms: " + calculateMedianBedrooms(incomeList));
            addResultToPDF(document, "Mode Bedrooms: " + calculateModeBedrooms(incomeList));
            addResultToPDF(document, "M E A L S:");
            addResultToPDF(document, "Average Meals: " + calculateAverageMeals(incomeList));
            addResultToPDF(document, "Median Meals: " + calculateMedianMeals(incomeList));
            addResultToPDF(document, "Mode Meals: " + calculateModeMeals(incomeList));

            document.close();
            logger.info("Informe exportado como " + fileName);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void addResultToPDF(Document document, String text) throws DocumentException {
        document.add(new Paragraph(text));
    }

    public static void generatePDFReport(List<HouseHoldIncome> incomeList) {
        logger.info("Generando informe en PDF");

        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.setFont (new PDType1Font (Standard14Fonts.FontName.HELVETICA), 16);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Informe de Estadísticas de Ingresos");
            contentStream.endText();
            contentStream.setFont (new PDType1Font (Standard14Fonts.FontName.HELVETICA), 16);
            float yPosition = 650;
            float lineSpacing = 20;

            contentStream.newLineAtOffset(50, yPosition);
            contentStream.showText("Suma del número de familias: " + sumNumberOfFamilies(incomeList));
            yPosition -= lineSpacing;

            contentStream.newLineAtOffset(0, -lineSpacing);
            contentStream.showText("SALARIOS:");
            yPosition -= lineSpacing;

            contentStream.newLineAtOffset(0, -lineSpacing);
            contentStream.showText("Promedio de salario: " + calculateAverageSalary(incomeList));
            yPosition -= lineSpacing;

            contentStream.newLineAtOffset(0, -lineSpacing);
            contentStream.showText("Mediana de salario: " + calculateMedianSalary(incomeList));
            yPosition -= lineSpacing;

            contentStream.newLineAtOffset(0, -lineSpacing);
            contentStream.showText("Moda de salario: " + calculateModeSalary(incomeList));
            yPosition -= lineSpacing;

            contentStream.newLineAtOffset(0, -lineSpacing);
            contentStream.showText("Salario mínimo: " + calculateMinimumSalary(incomeList));
            yPosition -= lineSpacing;

            // Repite el mismo patrón para MEMBROS, DORMITORIOS y COMIDAS aquí.

            contentStream.close();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = dateFormat.format(new Date());
            String fileName = "ReportesEstadisticos_" + timestamp + ".pdf";

            // Guarda el informe en el directorio "reports" (asegúrate de que el directorio exista)
            String filePath = "reports/" + fileName;

            document.save(filePath);
            document.close();

            logger.info("Informe en PDF generado exitosamente: " + fileName);
        } catch (IOException e) {
            logger.error("Error generando el informe en PDF: " + e.getMessage());
        }
    }


    public static class ReportSaver {

        private static final Logger logger = LoggerFactory.getLogger (ReportSaver.class);
        private static final String REPORTS_DIRECTORY = "reports"; // Nombre del directorio para almacenar los informes

        public ReportSaver (List<HouseHoldIncome> incomeList) {
        }

        public static void savePDFReport (PDDocument document) {
            logger.info ("Guardando el documento PDF");

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyyMMdd_HHmmss");
                String timestamp = dateFormat.format (new Date ());
                String fileName = "palmira_" + timestamp + ".pdf";

                // Verificar si el directorio "reports" existe, si no, crearlo
                File reportsDirectory = new File (REPORTS_DIRECTORY);
                if (!reportsDirectory.exists ()) {
                    reportsDirectory.mkdir ();
                }

                // Ruta completa del archivo PDF en el directorio "reports"
                String filePath = REPORTS_DIRECTORY + File.separator + fileName;

                document.save (filePath);
                document.close ();
                logger.info ("Documento PDF guardado en: " + filePath);
            } catch (IOException e) {
                e.printStackTrace ();
                logger.error ("Error al guardar el documento PDF");
            }
        }
    }


    public static void centralTendencyMeasures () throws IOException {
        logger.info("Generando las gráficas estadísticas");
        HouseHoldIncomeUsingFileRepositoryImpl repository = new HouseHoldIncomeFileRepositoryImpl ();
        //HouseHoldIncomeUsingFileRepositoryImpl repository = new HouseHoldIncomeUsingFileRepositoryImpl();
        List<HouseHoldIncome> incomeList = repository.findAllHouseHoldIncome ();
        // Generar el informe en PDF
        //generatePDFReport (incomeList);
        HouseHoldIncomeStatistics.generatePDFReport (incomeList);
        generatePDFReportStatistic(incomeList);
        // Generar gráficos estadísticos
        //generateStatisticsCharts (incomeList);
        // Central Tendency Measures
        statistics (incomeList);
        logger.info("Ya tenemos las medidas de tendencia central");
    }
//inicio

    public static void printStatistics(List<HouseHoldIncome> incomeList) {
        logger.info("Mostrando los datos estadísticos");
        System.out.println("Sum Number of Families: " + sumNumberOfFamilies(incomeList));
        System.out.println("S A L A R I E S:");
        System.out.println("Average Salary: " + calculateAverageSalary(incomeList));
        System.out.println("Median Salary: " + calculateMedianSalary(incomeList));
        System.out.println("Mode Salary: " + calculateModeSalary(incomeList));
        System.out.println("Minimum Salary: " + calculateMinimumSalary(incomeList));
        System.out.println("Maximum Salary: " + calculateMaximumSalary(incomeList));
        System.out.println("M E M B E R S:");
        System.out.println("Average Members: " + calculateAverageMembers(incomeList));
        System.out.println("Median Members: " + calculateMedianMembers(incomeList));
        System.out.println("Mode Members: " + calculateModeMembers(incomeList));
        System.out.println("B E D R O O M S:");
        System.out.println("Average Bedrooms: " + calculateAverageBedrooms(incomeList));
        System.out.println("Median Bedrooms: " + calculateMedianBedrooms(incomeList));
        System.out.println("Mode Bedrooms: " + calculateModeBedrooms(incomeList));
        System.out.println("M E A L S:");
        System.out.println("Average Meals: " + calculateAverageMeals(incomeList));
        System.out.println("Median Meals: " + calculateMedianMeals(incomeList));
        System.out.println("Mode Meals: " + calculateModeMeals(incomeList));
    }


    //final
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
    public static void generatePDFReportStatistic(List<HouseHoldIncome> incomeList) {
        logger.info("Generando informe en PDF");

        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont (new PDType1Font (Standard14Fonts.FontName.HELVETICA), 16);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Informe de Estadísticas de Ingresos");
            contentStream.endText();
            contentStream.setFont (new PDType1Font (Standard14Fonts.FontName.HELVETICA), 12);
            float yPosition = 650;  // Posición inicial para el contenido
            float lineSpacing = 20;  // Espaciado entre líneas

            contentStream.newLineAtOffset(50, yPosition);
            contentStream.showText("Suma del número de familias: " + sumNumberOfFamilies(incomeList));
            yPosition -= lineSpacing;

            contentStream.newLineAtOffset(0, -lineSpacing);
            contentStream.showText("SALARIOS:");
            yPosition -= lineSpacing;

            contentStream.newLineAtOffset(0, -lineSpacing);
            contentStream.showText("Promedio de salario: " + calculateAverageSalary(incomeList));
            yPosition -= lineSpacing;

            contentStream.newLineAtOffset(0, -lineSpacing);
            contentStream.showText("Mediana de salario: " + calculateMedianSalary(incomeList));
            yPosition -= lineSpacing;

            contentStream.newLineAtOffset(0, -lineSpacing);
            contentStream.showText("Moda de salario: " + calculateModeSalary(incomeList));
            yPosition -= lineSpacing;

            contentStream.newLineAtOffset(0, -lineSpacing);
            contentStream.showText("Salario mínimo: " + calculateMinimumSalary(incomeList));
            yPosition -= lineSpacing;

            // Repite el mismo patrón para MEMBROS, DORMITORIOS y COMIDAS aquí.

            contentStream.close();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = dateFormat.format(new Date());
            String fileName ="reporte_" + timestamp + ".pdf";

            document.save(fileName);
            document.close();

            System.out.println("Informe en PDF generado exitosamente: " + fileName);
        } catch (IOException e) {
            System.err.println("Error generando el informe en PDF: " + e.getMessage());
        }
    }


}

