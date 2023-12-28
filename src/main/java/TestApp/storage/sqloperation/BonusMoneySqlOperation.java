package TestApp.storage.sqloperation;

public enum BonusMoneySqlOperation {
    GET_ALL_BONUS_MONEY(
            "SELECT * " +
                    "FROM bonus"),
    GET_BONUS_BY_BONUS_ID(
            "SELECT * " +
                    "FROM bonus " +
                    "WHERE bonus_id = ?"),
    UPDATE_BONUS(
            "UPDATE bonus " +
                    "SET bonus_amount = ? " +
                    "WHERE bonus_id = ?");


    private final String title;

    BonusMoneySqlOperation(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
