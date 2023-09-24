package rent.easily.user.infrastructure.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter @Setter
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_code")
    private Long id;
    @Column(name = "usr_fullname")
    private String fullName;
    @Column(name = "usr_cpf")
    private String CPF;
    @Column(name = "usr_income")
    private double income;
    @Column(name = "rgt_code")
    private Long typeId;

    public UserModel(){}

    public UserModel(String fullName, String cpf, double income, Long typeId) {
        this.fullName = fullName;
        this.CPF = cpf;
        this.income = income;
        this.typeId = typeId;
    }

    public UserModel(Long id, String fullName, String cpf, double income, Long typeId) {
        this.id = id;
        this.fullName = fullName;
        this.CPF = cpf;
        this.income = income;
        this.typeId = typeId;
    }

    public String toString() {
        return "FullName: "+this.getFullName()+
        ", CPF: "+this.getCPF()+
        ", Income: "+this.getIncome()+
        ", type: "+this.getTypeId();
    }
}
