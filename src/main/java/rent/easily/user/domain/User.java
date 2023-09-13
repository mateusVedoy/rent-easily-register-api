package rent.easily.user.domain;

import rent.easily.shared.domain.Entity;

public class User extends Entity{

    private Long id;
    private String fullName;
    private String CPF;
    private double income;
    private RegisterType type;

    public User(String fullName, String CPF, double income, RegisterType type) {
        this.fullName = fullName;
        this.CPF = CPF;
        this.income = income;
        this.type = type;
        this.validate();
    }

    public User(Long id, String fullName, String CPF, double income, RegisterType type) {
        this.id = id;
        this.fullName = fullName;
        this.CPF = CPF;
        this.income = income;
        this.type = type;
        this.validate();
    }
    
    public double getIncome() {
        return income;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public RegisterType getType() {
        return type;
    }

    public String getCPF() {
        return CPF;
    }

    @Override
    public void validate() {
        if(!isFullNameValid())
            addError("User name is mandatory", "domain.User.fullName");
        if(!isCPFValid())
            addError("User CPF is mandatory", "domain.User.CPF");
        if(!isIncomeValid())
            addError("User income must to be greater than zero", "domain.User.income");
        if(!isTypeValid())
            addError("User type must to be LESSEE or LESSOR", "domain.User.type");
    }

    private boolean isFullNameValid() {
        return this.fullName.isEmpty() || this.fullName.isBlank();
    }

    private boolean isCPFValid() {
        return this.CPF.isEmpty() || this.CPF.isBlank();
    }

    private boolean isIncomeValid() {
        return this.income > 0;
    }

    private boolean isTypeValid() {
        return this.type != null;
    }
    
}
