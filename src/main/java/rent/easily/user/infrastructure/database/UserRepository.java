package rent.easily.user.infrastructure.database;

import java.util.ArrayList;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.shared.infrastructure.Repository;
import rent.easily.user.domain.User;

@ApplicationScoped
public class UserRepository extends Repository<User, UserModel> {

    public User getUserByCPF(String cpf) {
        PanacheQuery<UserModel> result = find("CPF = ?1", cpf);
        UserModel model = result.firstResult();
        if(model != null)
            return convertToDomainList(List.of(model)).stream().findFirst().get();
        return null;
    }

    @Override
    public UserModel convertToModel(User entity) {
        return new UserModel(entity.getFullName(), 
        entity.getCPF(), 
        entity.getIncome(),
        entity.getType().getValue());
    }

    @Override
    public List<User> convertToDomainList(List<UserModel> model) {
        List<User> users = new ArrayList<>();
        for(UserModel mdl: model) {
            users.add(new User(mdl.getId(), mdl.getFullName(), mdl.getCPF(), mdl.getIncome(), mdl.getTypeId()));
        }
        return users;
    }
}
