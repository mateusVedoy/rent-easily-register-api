package rent.easily.user.infrastructure.database;

import java.util.ArrayList;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import rent.easily.shared.infrastructure.Repository;
import rent.easily.user.domain.entity.Credentials;
import rent.easily.user.domain.entity.User;

@ApplicationScoped
public class UserRepository extends Repository<User, UserModel> {

    public boolean existsUserById(Long id) {
        Long result = count("id = ?1",id);
        return result > 0;
    }

    public boolean existsUserByCredentials(String mail, String pass) {
        Long result = count("mail = ?1 AND password = ?2", mail, pass);
        return result > 0;
    }

    public User getUserByCredentials(String mail, String pass) {
        PanacheQuery<UserModel> result = find("mail = ?1 AND password = ?2", mail, pass);
        UserModel model = result.firstResult();
        if(model != null)
            return convertToDomainList(List.of(model)).stream().findFirst().get();
        return null;
    }

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
        entity.getCredentials().getMail(),
        entity.getCredentials().getPassword(),
        entity.getType().getValue());
    }

    @Override
    public List<User> convertToDomainList(List<UserModel> model) {
        List<User> users = new ArrayList<>();
        for(UserModel mdl: model) {
            users.add(new User(mdl.getId(), mdl.getFullName(), mdl.getCPF(), mdl.getIncome(), new Credentials(mdl.getMail(), mdl.getPassword()), mdl.getTypeId()));
        }
        return users;
    }
}
