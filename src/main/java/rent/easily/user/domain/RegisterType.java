package rent.easily.user.domain;

public enum RegisterType {
    LESSEE(1L),
    LESSOR(2L);

    private final Long value;


    RegisterType(Long value){
        this.value = value;
    }

    public Long getValue() {
        return this.value;
    }
}
