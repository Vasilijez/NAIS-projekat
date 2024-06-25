package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
//import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.ExpenseDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.IncomeDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.TicketSalesPerTypeDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.TicketTypeAndMatchIdDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Expense;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.IncomeRepository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.TicketSaleRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PdfGenerationService {


    @Autowired
    IncomeService incomeService;

    @Autowired
    ExpenseService expenseService;

    @Autowired
    TicketSaleService ticketSaleService;

    public byte[] generateIncomeReport(Integer year, Integer month) throws IOException {
        List<IncomeDTO> incomes = incomeService.getIncomesForYearAndMonth(year, month);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        com.itextpdf.kernel.pdf.PdfDocument pdfDoc = new com.itextpdf.kernel.pdf.PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Income Report for " + month + "/" + year));
        document.add(new Paragraph("\n"));

        Table table = new Table(UnitValue.createPercentArray(new float[]{1, 3, 2, 2}));
        table.setWidth(UnitValue.createPercentValue(100));

        table.addHeaderCell(new Cell().add(new Paragraph("Date")));
        table.addHeaderCell(new Cell().add(new Paragraph("Description")));
        table.addHeaderCell(new Cell().add(new Paragraph("Source")));
        table.addHeaderCell(new Cell().add(new Paragraph("Amount($)")));

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (IncomeDTO income : incomes) {
            table.addCell(new Cell().add(new Paragraph(formatter.format(income.getIncomeCreationTimestamp()))));
            table.addCell(new Cell().add(new Paragraph(income.getDescription())));
            table.addCell(new Cell().add(new Paragraph(income.getSource())));
            table.addCell(new Cell().add(new Paragraph(income.getAmount().toString())));
        }

        document.add(table);

        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));

        document.add(new Paragraph("Salaries expenses logs"));
        document.add(new Paragraph("\n"));

        List<ExpenseDTO> expenseDTOS = expenseService.getAllByCategory("SALARIES");

        Table tableSalaries = new Table(UnitValue.createPercentArray(new float[]{1, 2, 3, 2}));
        tableSalaries.setWidth(UnitValue.createPercentValue(100));

        tableSalaries.addHeaderCell(new Cell().add(new Paragraph("Date")));
        tableSalaries.addHeaderCell(new Cell().add(new Paragraph("Category")));
        tableSalaries.addHeaderCell(new Cell().add(new Paragraph("Description")));
        tableSalaries.addHeaderCell(new Cell().add(new Paragraph("Amount($)")));

        for (ExpenseDTO dto: expenseDTOS) {
            tableSalaries.addCell(new Cell().add(new Paragraph(formatter.format(dto.expenseCreationTimestamp))));
            tableSalaries.addCell(new Cell().add(new Paragraph(dto.getCategory())));
            tableSalaries.addCell(new Cell().add(new Paragraph(dto.getDescription())));
            tableSalaries.addCell(new Cell().add(new Paragraph(dto.getAmount().toString())));
        }

        document.add(tableSalaries);

        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));


        document.add(new Paragraph("Ticket Sales Per Type For Highest Earning Match\n"));

        TicketTypeAndMatchIdDTO table3Data = ticketSaleService.getHighestPayingMatch();

        document.add(new Paragraph("Match ID: " + table3Data.matchId + "\n"));

        Table tableTicketsByType = new Table(UnitValue.createPercentArray(new float[]{2, 2}));
        tableSalaries.setWidth(UnitValue.createPercentValue(50));

        tableTicketsByType.addHeaderCell(new Cell().add(new Paragraph("Ticket Type")));
        tableTicketsByType.addHeaderCell(new Cell().add(new Paragraph("Amount Sold")));


        for (TicketSalesPerTypeDTO dto: table3Data.ticketsPerType) {
            tableTicketsByType.addCell(new Cell().add(new Paragraph(dto.ticketType)));
            tableTicketsByType.addCell(new Cell().add(new Paragraph(dto.getCount().toString())));
        }

        document.add(tableTicketsByType);

        document.close();

        return byteArrayOutputStream.toByteArray();

    }


}
