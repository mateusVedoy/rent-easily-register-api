package rent.easily.user.infrastructure.database;

import java.util.List;

import rent.easily.shared.infrastructure.Repository;
import rent.easily.user.domain.User;

public class UserRepository extends Repository<User, UserModel>{

    @Override
    protected UserModel convertToModel(User entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToModel'");
    }

    @Override
    protected List<User> convertToDomain(List<UserModel> entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToDomain'");
    }
    
}
