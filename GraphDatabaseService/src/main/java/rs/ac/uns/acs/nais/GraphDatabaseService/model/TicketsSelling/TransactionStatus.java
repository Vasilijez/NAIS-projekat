package rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling;

import rs.ac.uns.acs.nais.GraphDatabaseService.enumeration.TransactionStateEnumeration;

public class TransactionStatus {
    private String requestId;
    private TransactionStateEnumeration state;
    private String message;

    public TransactionStatus(String requestId, TransactionStateEnumeration state, String message) {
        this.requestId = requestId;
        this.state = state;
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public TransactionStateEnumeration getState() {
        return state;
    }

    public void setState(TransactionStateEnumeration state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Status of your request: \n{" +
                "requestId: '" + requestId + "'\n" +
                "state: " + state + '\n' +
                "message: '" + message + "'\n";
    }
}