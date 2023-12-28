package TestApp.storage.sqloperation;

public enum BankAccountSqlOperation {
    GET_ALL_BANK_ACCOUNT(
            "SELECT * " +
                    "FROM accounts"),
    GET_ACCOUNT_BY_ACCOUNT_ID(
            "SELECT * " +
                    "FROM accounts " +
                    "WHERE account_id = ?"),
    UPDATE_ACCOUNT(
            "UPDATE accounts " +
                    "SET account_amount = ? " +
                    "WHERE account_id = ?");


    private final String title;

    BankAccountSqlOperation(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
