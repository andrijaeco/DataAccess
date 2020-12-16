package silab.nst.dan9.dataAccess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import silab.nst.dan9.dataAccess.domain.Stats;
import silab.nst.dan9.dataAccess.repository.UpdateStatsRepository;
import silab.nst.dan9.dataAccess.repository.stats.operation.StatsOperation;
import silab.nst.dan9.dataAccess.service.StatsService;

import java.util.List;

@Component
@Transactional
public class StatsServiceImpl implements StatsService {

    @Autowired
    private UpdateStatsRepository statsRepository;

    @Override
    public Stats updateStats(String key, Long value, StatsOperation operation) throws Exception {
        return (Stats) statsRepository.update(new Stats(key, value), operation);
    }

    @Override
    public List<Stats> getALlStats() throws Exception {
        return statsRepository.getAllStats();
    }
}
