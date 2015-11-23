package cz.vasekric.beetletrack.connector.jdbcstore;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * Created by vasek on 04.11.2015.
 */
@ApplicationScoped
@Named
public class JdbcTemplateConfig {

    @Produces
    @Named
    @ApplicationScoped
    public JdbcTemplate getJdbcTemplate() {
        final DataSource dataSource = new DataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://kraa.info:3306/beetletrack");
        dataSource.setUsername("beetletrack");
        dataSource.setPassword("JFLZ3bjhlpAegMhs");
        return new JdbcTemplate(dataSource);
    }
}
