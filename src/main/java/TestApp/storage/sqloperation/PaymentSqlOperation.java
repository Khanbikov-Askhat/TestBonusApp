package TestApp.storage.sqloperation;

public enum PaymentSqlOperation {
    GET_ALL_PAYMENT(
            "SELECT * " +
                    "FROM payments"),
    GET_PAYMENT_BY_PAYMENT_ID(
            "SELECT * " +
                    "FROM payments " +
                    "WHERE payment_id = ?"),
    UPDATE_PAYMENT(
            "UPDATE payments " +
                    "SET operation_amount = ?" +
                    "WHERE payment_id = ?");


    private final String title;

    PaymentSqlOperation(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
