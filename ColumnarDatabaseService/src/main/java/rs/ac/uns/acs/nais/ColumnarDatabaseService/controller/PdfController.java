package rs.ac.uns.acs.nais.ColumnarDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.service.PdfGenerationService;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/pdf")
public class PdfController {
    @Autowired
    private PdfGenerationService pdfGenerationService;

    @GetMapping("/generate/{year}/{month}")
    public ResponseEntity<byte[]> generateIncomeReport(@PathVariable Integer year, @PathVariable Integer month) throws IOException {
        byte[] pdfBytes = pdfGenerationService.generateIncomeReport(year, month);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "income-report.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }

}
