package rs.ac.uns.acs.nais.ColumnarDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.ExpenseDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.TicketSaleDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.TicketSalesPerTypeDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.TicketTypeAndMatchIdDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.TicketSale;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.service.TicketSaleService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ticket-sales")
public class TicketSaleController {

    @Autowired
    private TicketSaleService ticketSaleService;

    @GetMapping
    public ResponseEntity<List<TicketSale>> getAll() {
        return ResponseEntity.ok((List<TicketSale>) ticketSaleService.getAll());
    }

    @PostMapping
    public ResponseEntity<TicketSale> create(@RequestBody TicketSaleDTO dto) {
        TicketSale createdTicketSale = ticketSaleService.create(dto);
        return ResponseEntity.ok(createdTicketSale);
    }


    @PutMapping("/{ticketId}")
    public ResponseEntity<TicketSale> updateTicketSale(@PathVariable UUID ticketId, @RequestBody TicketSaleDTO updatedTicketSale) {
        TicketSale updatedTicket = ticketSaleService.updateTicketSale(ticketId, updatedTicketSale);
        return ResponseEntity.ok(updatedTicket);
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Void> deleteTicketSale(@PathVariable UUID ticketId) {
        ticketSaleService.deleteTicketSale(ticketId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/add-list")
    public void createTicketSales(@RequestBody List<TicketSaleDTO> DTOs){
        ticketSaleService.createTicketSales(DTOs);
    }

    @GetMapping("/sales-per-type/{matchId}")
    public List<TicketSalesPerTypeDTO> getTicketSalesPerType(@PathVariable UUID matchId){
        return  ticketSaleService.getTicketSalesPerType(matchId);
    }
    @GetMapping("/highest-paying-match")
    public TicketTypeAndMatchIdDTO getHighestPayingMatch(){
        return  ticketSaleService.getHighestPayingMatch();
    }
}
