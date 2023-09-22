package rent.easily.shared.domain.port;

import java.util.List;

public interface IRepository<T1, T2> {
    void save(T1 entity);
    T1 findPerId(Long id);
    List<T1> getAll();
    void editById(Long id, T1 entity);
    T2 convertToModel(T1 entity);
    List<T1> convertModelToDomainList(List<T2> models);
}