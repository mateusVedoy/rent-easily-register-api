package rent.easily.user.infrastructure.database;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.shared.infrastructure.Repository;
import rent.easily.user.domain.User;

@ApplicationScoped
public class UserRepository extends Repository<User, UserModel> {

    @Override
    public UserModel convertToModel(User entity) {
        UserModel model = new UserModel(entity.getFullName(), 
        entity.getCPF(), 
        entity.getIncome(),
        entity.getType().getValue());

        System.out.println(model.toString());
        return model;
    }

    @Override
    public List<User> convertToDomainList(List<UserModel> model) {
        List<User> users = new ArrayList<>();
        for(UserModel mdl: model) {
            users.add(new User(mdl.getFullName(), mdl.getCPF(), mdl.getIncome(), mdl.getTypeId()));
        }
        return users;
    }

    
}
