package silab.nst.dan9.dataAccess.repository;

import java.util.List;

public interface Repository<T, ID> {
    T add(T t) throws Exception;

    T update(T t) throws Exception;

    T delete(T t) throws Exception;

    List<T> getAll() throws Exception;

    T findById(ID id) throws Exception;
}
