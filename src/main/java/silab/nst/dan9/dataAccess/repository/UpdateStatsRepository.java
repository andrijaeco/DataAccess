package silab.nst.dan9.dataAccess.repository;

import silab.nst.dan9.dataAccess.domain.Stats;

import java.util.List;

public interface UpdateStatsRepository<T, O> {
    T update(T t, O o) throws Exception;

    List<T> getAllStats();
}
