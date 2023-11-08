package com.example.ZeroHungerUdea.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.example.ZeroHungerUdea.model.HouseHoldIncome;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportSaver {
    private static final Logger logger = LoggerFactory.getLogger(ReportSaver.class);

    private final List<HouseHoldIncome> incomeList;

    public ReportSaver(List<HouseHoldIncome> incomeList) {
        this.incomeList = incomeList;
    }

    public static void savePDFReport () {
        logger.info("Guardando el documento PDF");

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = dateFormat.format(new Date());
            String fileName = "palmira_" + timestamp + ".pdf";

            // Ruta completa del archivo PDF en el paquete "reports"
            String filePath = "src/main/java/com/example/ZeroHungerUdea/service/reports/" + fileName;

            // Crear un nuevo documento PDF
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Agregar contenido al documento (por ejemplo, un párrafo de demostración)
            Paragraph paragraph = new Paragraph("Este es un párrafo de demostración.");
            document.add(paragraph);

            // Cerrar el documento
            document.close();
            logger.info("Documento PDF guardado en: " + filePath);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            logger.error("Error al guardar el documento PDF");
        }
    }
}
