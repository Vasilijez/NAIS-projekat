package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
//import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.*;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Expense;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.IncomeRepository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.TicketSaleRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

@Service
public class PdfGenerationService {


    @Autowired
    IncomeService incomeService;

    @Autowired
    ExpenseService expenseService;

    @Autowired
    TicketSaleService ticketSaleService;

    public byte[] generateIncomeReport(Integer year, Integer month, LocalDateTime startDate, LocalDateTime endDate) throws IOException {
        List<IncomeDTO> incomes = incomeService.getIncomesForYearAndMonth(2022, month);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        com.itextpdf.kernel.pdf.PdfDocument pdfDoc = new com.itextpdf.kernel.pdf.PdfDocument(writer);
        Document document = new Document(pdfDoc);

        Paragraph header = new Paragraph("Financial Reports For Year 2022");
        header.setFontSize(20);
        header.setBold();
        header.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(header);
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));

        Paragraph header1 = new Paragraph("Income Report for " + month + "/" + 2022);
        header1.setFontSize(16);
        header1.setBold();
        header1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(header1);
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

        table.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(table);

        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));

        Paragraph header2 = new Paragraph("Monthly Income In year 2022");
        header2.setFontSize(16);
        header2.setBold();
        header2.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(header2);
        document.add(new Paragraph("\n"));

        List<MonthlyIncomeDTO> monthlyIncome = incomeService.getMonthlyIncomeBy(2022);
        Table monthlyIncomeTable = new Table(UnitValue.createPercentArray(new float[]{2, 2}));
        monthlyIncomeTable.setWidth(UnitValue.createPercentValue(50));

        monthlyIncomeTable.addHeaderCell(new Cell().add(new Paragraph("Month")));
        monthlyIncomeTable.addHeaderCell(new Cell().add(new Paragraph("Total($)")));

        for (MonthlyIncomeDTO dto:monthlyIncome) {
            monthlyIncomeTable.addCell(new Cell().add(new Paragraph(dto.getMonth())));
            monthlyIncomeTable.addCell(new Cell().add(new Paragraph(dto.getTotalIncome().toString())));
        }

        monthlyIncomeTable.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(monthlyIncomeTable);

        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));


        Paragraph header3 = new Paragraph("Salary expenses logs");
        header3.setFontSize(16);
        header3.setBold();
        header3.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(header3);
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

        tableSalaries.setHorizontalAlignment(HorizontalAlignment.CENTER);

        document.add(tableSalaries);

        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));


        Paragraph header4 = new Paragraph("Average expenses per category in year 2022");
        header4.setFontSize(16);
        header4.setBold();
        header4.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(header4);
        document.add(new Paragraph("\n"));



        List<ExpensesCategorized> expensesCategorized = expenseService.perCategory();

        Table expensesCategorizedTable = new Table(UnitValue.createPercentArray(new float[]{2, 2}));
        expensesCategorizedTable.setWidth(UnitValue.createPercentValue(50));

        expensesCategorizedTable.addHeaderCell(new Cell().add(new Paragraph("Category")));
        expensesCategorizedTable.addHeaderCell(new Cell().add(new Paragraph("Average amount($)")));
        for (ExpensesCategorized dto: expensesCategorized) {
            expensesCategorizedTable.addCell(new Cell().add(new Paragraph(dto.getCategory())));

            BigDecimal rounded = BigDecimal.valueOf(dto.getAverage());
            rounded = rounded.setScale(2, RoundingMode.HALF_UP);

            expensesCategorizedTable.addCell(new Cell().add(new Paragraph(rounded.toString())));

        }
        expensesCategorizedTable.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(expensesCategorizedTable);

        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));


        Paragraph header5 = new Paragraph("Ticket Sales Per Type For Highest Earning Match");
        header5.setFontSize(16);
        header5.setBold();
        header5.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(header5);
        document.add(new Paragraph("\n"));



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
        tableTicketsByType.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(tableTicketsByType);

        document.close();

        return byteArrayOutputStream.toByteArray();

    }



}
