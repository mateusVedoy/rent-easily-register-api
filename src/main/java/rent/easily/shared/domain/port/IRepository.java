package rent.easily.shared.domain.port;

import java.util.List;

public interface IRepository<T> {
    void save(T entity);
    T findById(Long id);
    List<T> findAll();
}