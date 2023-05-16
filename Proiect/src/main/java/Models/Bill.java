package Models;

import java.time.LocalDateTime;

/**
 * Represents a bill for an order in a store.
 */
public record Bill(
        /** The unique identifier for this bill. */
        int id,
        /** The unique identifier for the order associated with this bill. */
        int orderId,
        /** The unique identifier for the client who made the order associated with this bill. */
        int clientId,
        /** The total amount due for the order associated with this bill. */
        double total,
        /** The date and time when this bill was generated. */
        LocalDateTime date
) {
}
