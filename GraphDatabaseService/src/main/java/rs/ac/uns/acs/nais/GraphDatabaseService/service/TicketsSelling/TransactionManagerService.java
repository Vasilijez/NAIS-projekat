package rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.enumeration.TransactionStateEnumeration;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.TransactionStatus;

import java.util.concurrent.ConcurrentHashMap;

import static org.neo4j.driver.Values.parameters;

@Service
public class TransactionManagerService {

    private final Driver neo4jDriver;

    private final ConcurrentHashMap<String, Transaction> neo4jTransactions;

    private final ConcurrentHashMap<String, TransactionStatus> transactionStatuses;

    public TransactionManagerService(Driver neo4jDriver) {
        this.neo4jDriver = neo4jDriver;
        this.neo4jTransactions = new ConcurrentHashMap<>();
        this.transactionStatuses = new ConcurrentHashMap<>();
    }

    public void beginTransaction(String requestId) {
        Session session = neo4jDriver.session();
        Transaction tx = session.beginTransaction();
        neo4jTransactions.put(requestId, tx);
        transactionStatuses.put(requestId, new TransactionStatus(requestId, TransactionStateEnumeration.IN_PROCESS, "Transaction started"));
    }

    public boolean createLoyaltyProgram(String requestId, String level, Double discountRate) {
        Transaction tx = neo4jTransactions.get(requestId);
        if (tx != null) {
            Result result = tx.run("OPTIONAL MATCH (lp:LoyaltyProgram) " +
                    "WHERE lp.level = $level " +
                    "WITH lp " +
                    "WHERE lp IS NULL " +
                    "CREATE (newLp: LoyaltyProgram {level: $level, discountRate: $discountRate}) " +
                    "RETURN count(newLp) >= 1 AS isCreated", parameters("level", level, "discountRate", discountRate));
            return result.single().get("isCreated").asBoolean();
        }
        return false;
    }

    public void commitTransaction(String requestId) {
        Transaction tx = neo4jTransactions.remove(requestId);
        if (tx != null) {
            tx.commit();
            tx.close();
            transactionStatuses.get(requestId).setState(TransactionStateEnumeration.SUCCESSFUL);
            transactionStatuses.get(requestId).setMessage("Transaction committed");
        }
    }

    public void rollbackTransaction(String requestId) {
        Transaction tx = neo4jTransactions.remove(requestId);
        if (tx != null) {
            tx.rollback();
            tx.close();
            transactionStatuses.get(requestId).setState(TransactionStateEnumeration.UNSUCCESSFUL);
            transactionStatuses.get(requestId).setMessage("Transaction rolled back");
        }
    }

    public TransactionStatus getStatus(String requestId) {
        return transactionStatuses.get(requestId);
    }
}
