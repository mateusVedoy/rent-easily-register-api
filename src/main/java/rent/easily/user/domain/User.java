package rent.easily.user.domain;

import lombok.Getter;
import lombok.Setter;
import rent.easily.shared.domain.Entity;

@Getter @Setter
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

    public User(String fullName, String CPF, double income, Long typeId) {
        this.fullName = fullName;
        this.CPF = CPF;
        this.income = income;
        this.type  = setType(typeId);
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
        if(isFullNameInvalid())
            addError("User name is mandatory", "domain.User.fullName");
        if(isCPFInvalid())
            addError("User CPF is mandatory", "domain.User.CPF");
        if(!isIncomeValid())
            addError("User income must to be greater than zero", "domain.User.income");
        if(!isTypeValid())
            addError("User type must to be LESSEE or LESSOR", "domain.User.type");
    }

    private boolean isFullNameInvalid() {
        return this.fullName.isEmpty() || this.fullName.isBlank();
    }

    private RegisterType setType(Long typeId) {
        if(RegisterType.LESSEE.getValue() == typeId)
            return RegisterType.LESSEE;
        else if(RegisterType.LESSOR.getValue() == typeId)
            return RegisterType.LESSOR;
        else
            return null;
    }

    private boolean isCPFInvalid() {
        return this.CPF.isEmpty() || this.CPF.isBlank();
    }

    private boolean isIncomeValid() {
        return this.income > 0;
    }

    private boolean isTypeValid() {
        return this.type != null;
    }
    
    public String toString() {
        return "FullName: "+this.getFullName()+
        ", CPF: "+this.getCPF()+
        ", Income: "+this.getIncome()+
        ", type: "+this.getType();
    }
}
