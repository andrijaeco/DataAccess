package silab.nst.dan9.dataAccess.service;

import silab.nst.dan9.dataAccess.domain.Stats;
import silab.nst.dan9.dataAccess.repository.stats.operation.StatsOperation;

import java.util.List;

public interface StatsService {
    Stats updateStats(String key, Long value, StatsOperation operation) throws Exception;

    List<Stats> getALlStats() throws Exception;
}
