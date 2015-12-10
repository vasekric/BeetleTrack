package cz.vasekric.beetletrack.connector.mappers;

import cz.vasekric.beetletrack.domain.models.SpendTimeDO;
import cz.vasekric.beetletrack.domain.models.UserDO;
import org.springframework.jdbc.core.RowMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;

/**
 * Created by vasek on 10.12.2015.
 */
@Named
@ApplicationScoped
public class SpentTimeRowMapper implements RowMapper<SpendTimeDO> {
    @Override
    public SpendTimeDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        final int id = rs.getInt("id");
        final long time = rs.getLong("time");
        final int issueId = rs.getInt("issue_id");
        final int userId = rs.getInt("user_id");

        return SpendTimeDO.builder()
                    .time(Duration.ofHours(time))
                    .build();
    }
}
