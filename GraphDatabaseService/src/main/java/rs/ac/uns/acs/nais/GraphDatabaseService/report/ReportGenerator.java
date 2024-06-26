package rs.ac.uns.acs.nais.GraphDatabaseService.report;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Order;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling.FanService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling.FootballMatchService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling.OrderService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling.SeatService;


public class ReportGenerator {

    private OrderService orderService;

    private FanService fanService;

    private FootballMatchService footballMatchService;

    private SeatService seatService;

    public ReportGenerator(OrderService orderService, FanService fanService, FootballMatchService footballMatchService, SeatService seatService) {
        this.orderService = orderService;
        this.fanService = fanService;
        this.footballMatchService = footballMatchService;
        this.seatService = seatService;
    }

    public byte[] generateReport(String fanUsername) {
        System.out.println("Starting to generate report...");
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            Font headerTitleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 26);
            Paragraph headerTitle = new Paragraph("Tickets Selling Report", headerTitleFont);
            headerTitle.setAlignment(Element.ALIGN_CENTER);
            headerTitle.setSpacingAfter(6);
            document.add(headerTitle);
            Date date = new Date();
            Font headerSubtitleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13, Font.ITALIC);
            Paragraph headerSubtitle = new Paragraph("Generated on: " + date.toString(), headerSubtitleFont);
            headerSubtitle.setAlignment(Element.ALIGN_CENTER);
            headerSubtitle.setSpacingAfter(6);
            document.add(headerSubtitle);


            document.add(new Paragraph(" "));


            // Prost upit br. 1
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 15);
            Paragraph title = new Paragraph("Top 3 Student Orders: Shipped and Paid by Cash", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(12);
            document.add(title);

            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);

            float[] columnWidths = new float[]{2, 2, 2, 2, 2, 2, 2};
            table.setWidths(columnWidths);
//            twoFansTable.setWidths(columnWidths);
//            latest5MatchesStatsTable.setWidths(columnWidths);


            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            PdfPCell headerCell;
            headerCell = new PdfPCell(new Phrase("Order ID", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            table.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Total Price", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            table.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Creation Date", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            table.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Delivery Type", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            table.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Status", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            table.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Payment Method", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            table.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Username", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            table.addCell(headerCell);

            List<Order> orders =  orderService.findTop3ShippedAndCashPaidOrdersMadeByStudents();
            for (Order order : orders) {
                table.addCell(order.getOrderID());
                table.addCell(String.valueOf(order.getTotalPrice()));
                table.addCell(String.valueOf(order.getCreationDate()));
                table.addCell(order.getDeliveryType());
                table.addCell(order.getStatus());
                table.addCell(order.getPaymentMethod());
                table.addCell(order.getUsername());
            }

            document.add(table);

            // Prost upit br. 2
            document.add(new Paragraph(" "));
            Paragraph twoFans = new Paragraph("Two Fans with Lowest Points and Minimum One Order in Current Year", titleFont);
            twoFans.setAlignment(Element.ALIGN_CENTER);
            twoFans.setSpacingAfter(12);
            document.add(twoFans);

            PdfPTable twoFansTable = new PdfPTable(7);
            twoFansTable.setWidthPercentage(100);

//            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//            PdfPCell headerCell;

            headerCell = new PdfPCell(new Phrase("Username", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            twoFansTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Fan Type", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            twoFansTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Favourite Player", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            twoFansTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Date of Birth", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            twoFansTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Gender", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            twoFansTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Level", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            twoFansTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Points", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            twoFansTable.addCell(headerCell);

            List<TwoBottomFansDTO> fans = fanService.findTwoFansWithLowestPointsAndAtLeastOneOrderThisYear();

            for (TwoBottomFansDTO fan : fans) {
                twoFansTable.addCell(fan.getFan().getUsername());
                twoFansTable.addCell(fan.getFan().getFanType());
                twoFansTable.addCell(fan.getFan().getFavouritePlayer());
                twoFansTable.addCell(String.valueOf(fan.getFan().getDateOfBirth()));
                twoFansTable.addCell(fan.getFan().getGender());
                twoFansTable.addCell(fan.getFan().getLevel());
                twoFansTable.addCell(String.valueOf(fan.getPointsNumber()));
            }

            document.add(twoFansTable);

            // Slozen upit br. 1 - preporuka
            document.add(new Paragraph(" "));
            Paragraph recommendStandardTicketSeat = new Paragraph("Standard Ticket Seat Recommendation for User 'Vasilijez'", titleFont);
            recommendStandardTicketSeat.setAlignment(Element.ALIGN_CENTER);
            recommendStandardTicketSeat.setSpacingAfter(12);
            document.add(recommendStandardTicketSeat);

            PdfPTable recommendStandardTicketSeatTable = new PdfPTable(5);
            recommendStandardTicketSeatTable.setWidthPercentage(100);

            headerCell = new PdfPCell(new Phrase("Seat number", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            recommendStandardTicketSeatTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Sector", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            recommendStandardTicketSeatTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Tribune", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            recommendStandardTicketSeatTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Seat Reservation Count", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            recommendStandardTicketSeatTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Is Reserved", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            recommendStandardTicketSeatTable.addCell(headerCell);

            List<RecommendedStandardTicketSeatsDTO> seats = seatService.recommendStandardTicketSeat("Vasilijez");

            for (RecommendedStandardTicketSeatsDTO seat : seats) {
                recommendStandardTicketSeatTable.addCell(seat.getSeat().getSeatNumber());
                recommendStandardTicketSeatTable.addCell(String.valueOf(seat.getSeat().getSector()));
                recommendStandardTicketSeatTable.addCell(seat.getSeat().getTribune());
                recommendStandardTicketSeatTable.addCell(String.valueOf(seat.getSeatReservationCount()));
                recommendStandardTicketSeatTable.addCell(seat.getIsReserved());
            }

            document.add(recommendStandardTicketSeatTable);

            // Slozen upit br. 2 - preporuka
            document.add(new Paragraph(" "));
            Paragraph recommendSeasonTicketSeat = new Paragraph("Season Ticket Seat Recommendation for User 'Peraa'", titleFont);
            recommendSeasonTicketSeat.setAlignment(Element.ALIGN_CENTER);
            recommendSeasonTicketSeat.setSpacingAfter(12);
            document.add(recommendSeasonTicketSeat);

            PdfPTable recommendSeasonTicketSeatTable = new PdfPTable(5);
            recommendSeasonTicketSeatTable.setWidthPercentage(100);

            headerCell = new PdfPCell(new Phrase("Seat number", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            recommendSeasonTicketSeatTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Sector", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            recommendSeasonTicketSeatTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Tribune", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            recommendSeasonTicketSeatTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Seat Reservation Count", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            recommendSeasonTicketSeatTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Is Reserved", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            recommendSeasonTicketSeatTable.addCell(headerCell);

            LocalDate localDate = LocalDate.parse("2024-04-04");
            LocalTime localTime = LocalTime.parse("21:00:00");
            LocalDateTime seasonTicketStartTime = LocalDateTime.of(localDate, localTime);
            System.out.println("The season ticket starts at: " + seasonTicketStartTime);
            SeasonTicketStartTimeDTO seasonTicketStartTimeDTO = new SeasonTicketStartTimeDTO(seasonTicketStartTime);
            List<RecommendedSeasonTicketSeatsDTO> seasonTicketSeatsDTOS = seatService.recommendSeasonTicketSeat("Peraa", seasonTicketStartTimeDTO);

            for (RecommendedSeasonTicketSeatsDTO seat : seasonTicketSeatsDTOS) {
                recommendSeasonTicketSeatTable.addCell(seat.getSeat().getSeatNumber());
                recommendSeasonTicketSeatTable.addCell(String.valueOf(seat.getSeat().getSector()));
                recommendSeasonTicketSeatTable.addCell(seat.getSeat().getTribune());
                recommendSeasonTicketSeatTable.addCell(String.valueOf(seat.getSeatReservationCountEver()));
                recommendSeasonTicketSeatTable.addCell(seat.getIsReserved());
            }

            document.add(recommendSeasonTicketSeatTable);


            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            // Slozen upit br. 3 - preporuka
            document.add(new Paragraph(" "));
            Paragraph recommendPopularFootballMatch = new Paragraph("Popular Football Match Recommendation for User 'Peraa'", titleFont);
            recommendPopularFootballMatch.setAlignment(Element.ALIGN_CENTER);
            recommendPopularFootballMatch.setSpacingAfter(12);
            document.add(recommendPopularFootballMatch);

            PdfPTable recommendPopularFootballMatchTable = new PdfPTable(6);
            recommendPopularFootballMatchTable.setWidthPercentage(100);

            headerCell = new PdfPCell(new Phrase("Football Match ID", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            recommendPopularFootballMatchTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Start Time", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            recommendPopularFootballMatchTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Opponent Name", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            recommendPopularFootballMatchTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Result", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            recommendPopularFootballMatchTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Total Seat Reservations Number", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            recommendPopularFootballMatchTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Has Ticket", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            recommendPopularFootballMatchTable.addCell(headerCell);

            List<PopularFootballMatchDTO> recommendedSeasonTicketSeatsDTOS = footballMatchService.recommendPopularFootballMatch("Peraa");

            for (PopularFootballMatchDTO footballMatch : recommendedSeasonTicketSeatsDTOS) {
                recommendPopularFootballMatchTable.addCell(footballMatch.getFootballMatch().getFootballMatchID());
                recommendPopularFootballMatchTable.addCell(String.valueOf(footballMatch.getFootballMatch().getStartTime()));
                recommendPopularFootballMatchTable.addCell(footballMatch.getFootballMatch().getOpponentName());
                recommendPopularFootballMatchTable.addCell(footballMatch.getFootballMatch().getResult());
                recommendPopularFootballMatchTable.addCell(String.valueOf(footballMatch.getTotalSeatReservationsNumber()));
                recommendPopularFootballMatchTable.addCell(footballMatch.getHasTicket());
            }

            document.add(recommendPopularFootballMatchTable);

            // Slozen upit br. 4
            document.add(new Paragraph(" "));
            Paragraph latest5MatchesStats = new Paragraph("Stats from Five Most Recent Football Matches", titleFont);
            latest5MatchesStats.setAlignment(Element.ALIGN_CENTER);
            latest5MatchesStats.setSpacingAfter(12);
            document.add(latest5MatchesStats);

            PdfPTable latest5MatchesStatsTable = new PdfPTable(7);
            latest5MatchesStatsTable.setWidthPercentage(100);

            headerCell = new PdfPCell(new Phrase("Football Match ID", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            latest5MatchesStatsTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Start Time", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            latest5MatchesStatsTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Opponent Name", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            latest5MatchesStatsTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Result", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            latest5MatchesStatsTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Reserved Tickets Number", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            latest5MatchesStatsTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Cancelled Tickets Number", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            latest5MatchesStatsTable.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Paid Tickets Number", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setPaddingTop(5);
            headerCell.setPaddingBottom(5);
            latest5MatchesStatsTable.addCell(headerCell);

            List<FiveLatestFootballMatchesDTO> ticketsStats = footballMatchService.getTicketsStatsFromFiveLatestFootballMatches();

            for (FiveLatestFootballMatchesDTO ticketsStat : ticketsStats) {
                latest5MatchesStatsTable.addCell(ticketsStat.getFootballMatch().getFootballMatchID());
                latest5MatchesStatsTable.addCell(String.valueOf(ticketsStat.getFootballMatch().getStartTime()));
                latest5MatchesStatsTable.addCell(ticketsStat.getFootballMatch().getOpponentName());
                latest5MatchesStatsTable.addCell(ticketsStat.getFootballMatch().getResult());
                latest5MatchesStatsTable.addCell(String.valueOf(ticketsStat.getReservedTicketsNumber()));
                latest5MatchesStatsTable.addCell(String.valueOf(ticketsStat.getCancelledTicketsNumber()));
                latest5MatchesStatsTable.addCell(String.valueOf(ticketsStat.getPaidTicketsNumber()));
            }

            document.add(latest5MatchesStatsTable);
            
            System.out.println("Report generated successfully!");
        } catch (DocumentException e) {
            System.out.println("Error: Document exception: " + e.getMessage());
        } finally {
            document.close();
        }

        return outputStream.toByteArray();
    }
}
