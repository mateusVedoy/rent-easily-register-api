package rent.easily.shared.application.response;

public enum StatusMessage {
    EMPTY_SUCCESS("There's no data to be recovered."),
    SUCCESS("Data fetched bellow."),
    CREATED("Data saved successfully."),
    ERROR("Something went wrong. Consult errors.");


    private final String value;
    StatusMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
