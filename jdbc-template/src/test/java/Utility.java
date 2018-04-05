import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.Driver;
import java.sql.SQLException;

class Utility {

    private static Driver driver;

    static {
        try {
            driver = new com.mysql.cj.jdbc.Driver();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static final SimpleDriverDataSource dataSource = new SimpleDriverDataSource(
        driver,
        "jdbc:mysql://localhost:3306/house?useSSL=false",
        "admin",
        "admin"
    );

    static final NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
}
