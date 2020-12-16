package silab.nst.dan9.dataAccess.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import silab.nst.dan9.dataAccess.domain.Stats;
import silab.nst.dan9.dataAccess.repository.UpdateStatsRepository;
import silab.nst.dan9.dataAccess.repository.stats.operation.StatsOperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@Transactional(propagation = Propagation.MANDATORY)
public class StatsRepository implements UpdateStatsRepository<Stats, StatsOperation> {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Stats update(Stats stats, StatsOperation operation) throws Exception {
        switch (operation) {
            case INSERT:
                jdbcTemplate.update("update stats set numberOfEntities = " +
                        "(select numberOfEntities from stats where tableName = ?) + 1 " +
                        "where tableName = ?", stats.getKey(), stats.getKey());
                break;
            case DELETE:
                jdbcTemplate.update("update stats set numberOfEntities = " +
                        "(select numberOfEntities from stats where tableName = ?) - 1 " +
                        "where tableName = ?", stats.getKey(), stats.getKey());
                break;
        }
        return stats;
    }

    @Override
    public List<Stats> getAllStats() {
        return jdbcTemplate.query("select * from stats", new StatsMapper());
    }

    class StatsMapper implements RowMapper<Stats> {
        @Override
        public Stats mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return new Stats(resultSet.getString("tableName"), resultSet.getLong("numberOfEntities"));
        }
    }
}
