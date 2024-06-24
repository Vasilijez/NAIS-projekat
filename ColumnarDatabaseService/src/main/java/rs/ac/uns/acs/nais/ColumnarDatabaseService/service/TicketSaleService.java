package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.TicketSaleDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.TicketSale;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.TicketSaleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@EnableCaching
public class TicketSaleService {

    @Autowired
    private TicketSaleRepository ticketSaleRepository;

    public List<TicketSale> getAll() {
        return ticketSaleRepository.findAll();
    }

    public TicketSale create(TicketSaleDTO ticketSaleDTO) {
        TicketSale ticketSale = new TicketSale();
        ticketSale.setTicketId(UUID.randomUUID());
        ticketSale.setMatchId(ticketSaleDTO.getMatchId());
        ticketSale.setTicketType(ticketSaleDTO.getTicketType());
        ticketSale.setSaleDate(ticketSaleDTO.getSaleDate());
        ticketSale.setAmount(ticketSaleDTO.getAmount());
        ticketSale.setQuantity(ticketSaleDTO.getQuantity());
        return ticketSaleRepository.save(ticketSale);
    }

    public TicketSale updateTicketSale(UUID ticketId, TicketSaleDTO updatedTicketSaleDTO) {
        Optional<TicketSale> existingTicketSaleOpt = ticketSaleRepository.findById(ticketId);
        if (existingTicketSaleOpt.isPresent()) {
            TicketSale existingTicketSale = existingTicketSaleOpt.get();
            // update fields from updatedTicketSaleDTO to existingTicketSale
            existingTicketSale.setMatchId(updatedTicketSaleDTO.getMatchId());
            existingTicketSale.setTicketType(updatedTicketSaleDTO.getTicketType());
            existingTicketSale.setSaleDate(updatedTicketSaleDTO.getSaleDate());
            existingTicketSale.setAmount(updatedTicketSaleDTO.getAmount());
            existingTicketSale.setQuantity(updatedTicketSaleDTO.getQuantity());
            return ticketSaleRepository.save(existingTicketSale);
        } else {
            throw new RuntimeException("Ticket sale not found");
        }
    }

    public void deleteTicketSale(UUID ticketId) {
        ticketSaleRepository.deleteById(ticketId);
    }

    public void createTicketSales(List<TicketSaleDTO> DTOs) {
        List<TicketSale> newSales = new ArrayList<TicketSale>();
        for (TicketSaleDTO dto: DTOs) {
            TicketSale ticketSale = new TicketSale();
            ticketSale.setTicketId(UUID.randomUUID());
            ticketSale.setMatchId(dto.getMatchId());
            ticketSale.setTicketType(dto.getTicketType());
            ticketSale.setSaleDate(dto.getSaleDate());
            ticketSale.setAmount(dto.getAmount());
            ticketSale.setQuantity(dto.getQuantity());

            newSales.add(ticketSale);
        }
        ticketSaleRepository.saveAll(newSales);
    }
}
