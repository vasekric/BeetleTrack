package cz.vasekric.beetletrack.connector.mappers;

import cz.vasekric.beetletrack.domain.models.IssueNodeDO;
import org.springframework.jdbc.core.RowMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vasek on 11.11.2015.
 */
@Named
@ApplicationScoped
public class IssueNodeRowMapper implements RowMapper<IssueNodeDO> {

    @Override
    public IssueNodeDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String name = rs.getString("name");
        final int id = rs.getInt("id");

        return IssueNodeDO.builder()
                .id(id)
                .name(name)
                .build();
    }
}
