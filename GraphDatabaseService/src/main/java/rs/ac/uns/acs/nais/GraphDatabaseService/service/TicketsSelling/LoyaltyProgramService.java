package rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling;

import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.LoyaltyProgram;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling.LoyaltyProgramRepository;


@Service
public class LoyaltyProgramService {

    private LoyaltyProgramRepository loyaltyProgramRepository;

    public LoyaltyProgramService(LoyaltyProgramRepository loyaltyProgramRepository) {
        this.loyaltyProgramRepository = loyaltyProgramRepository;
    }

    public LoyaltyProgram createLoyaltyProgram(LoyaltyProgram loyaltyProgram) {
        return loyaltyProgramRepository.createLoyaltyProgram(loyaltyProgram.getLevel(), loyaltyProgram.getDiscountRate());
    }

    public LoyaltyProgram getLoyaltyProgramByLevel(String level) {
        return loyaltyProgramRepository.findLoyaltyProgram(level);
    }

    public LoyaltyProgram updateLoyaltyProgram(String level, LoyaltyProgram loyaltyProgram) {
        return loyaltyProgramRepository.updateLoyaltyProgram(level, loyaltyProgram.getLevel(), loyaltyProgram.getDiscountRate());
    }

    public boolean deleteLoyaltyProgram(String level) {
        return loyaltyProgramRepository.deleteLoyaltyProgram(level);
    }

//    @Override
//    public Customer addNewCustomer(Customer customer) {
//        customer.setActive(true);
//        Customer customer1 = customerRepository.save(customer);
//        return customer1;
//    }
//
//    @Override
//    public boolean deleteCustomer(String customerEmail) {
//        Customer customer = customerRepository.findByEmail(customerEmail);
//        if(customer != null){
//            customer.setActive(false);
//            customerRepository.save(customer);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean updateCustomer(String customerEmailOld, String customerEmailNew) {
//        Customer customer = customerRepository.findByEmail(customerEmailOld);
//        if(customer != null){
//            customer.setEmail(customerEmailNew);
//            customerRepository.save(customer);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public Customer addReview(ReviewDTO reviewDTO) {
//        Customer customer = customerRepository.findByEmail(reviewDTO.getCustomerEmail());
//        Optional<Product> product = productRepository.findById(reviewDTO.getProductId());
//        if(customer != null && product.isPresent()){
//            Review review = new Review();
//            review.setProduct(product.get());
//            review.setRating(reviewDTO.getRating());
//            customer.addReview(review);
//            return customerRepository.save(customer);
//        }
//        return null;
//    }
//
//    @Override
//    public void addPurchase(OrderDTO orderDTO) {
//        for(OrderItemDTO orderItemDTO: orderDTO.getItems()){
//            if (customerRepository.hasPurchasedProduct(orderDTO.getCustomerId(), orderItemDTO.getProductId())){
//                customerRepository.purchaseProduct(orderDTO.getCustomerId(), orderItemDTO.getProductId());
//            }else{
//                customerRepository.createPurchase(orderDTO.getCustomerId(), orderItemDTO.getProductId());
//            }
//
//        }
//
//    }
//
//    @Override
//    public List<Product> recommendProductsByPurchaseHistory(Long customerId) {
//        return productRepository.recommendProductsByPurchaseHistory(customerId);
//    }
//
//    @Override
//    public List<Product> recommendProductsByReviews(Long customerId) {
//        return productRepository.recommendProductsByReviews(customerId);
//    }

}
