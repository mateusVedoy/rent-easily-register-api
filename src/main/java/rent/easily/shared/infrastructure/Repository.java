package rent.easily.shared.infrastructure;

import java.util.List;
import jakarta.transaction.Transactional;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import rent.easily.shared.domain.port.IRepository;

public abstract class Repository<T1, T2> implements PanacheRepository<T2>, IRepository<T1, T2> {

    @Override
    @Transactional
    public List<T1> save(T1 entity) {
        T2 model = convertToModel(entity);
        if(!isPersistent(model))
            persist(model); //depois add uma validação que salvou realmente com isPersistent
        return convertToDomainList(List.of(model));
    }

    @Override
    public T1 findPerId(Long id) {
        T2 model = findById(id);
        if(isNull(model))
            return null;
        return convertToDomainList(List.of(model)).get(0); //refact depois
    }

    @Override
    public List<T1> getAll() {
        List<T2> models = findAll().list();
        return convertToDomainList(models);
    }

    @Override
    @Transactional
    public void edit(T2 model) { //estudar forma melhor de editar
        persist(model);
    }

    private boolean isNull(T2 object) {
        return object == null;
    }

    protected abstract T2 convertToModel(T1 entity);
    protected abstract List<T1> convertToDomainList(List<T2> models);
}
