import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class MySqlBrickRepositoryTest {

    private BrickRepository brickRepository;

    @BeforeEach
    void setUp() {

        try {
            DataSource dataSource = new SimpleDriverDataSource(
                new com.mysql.cj.jdbc.Driver(),
                "jdbc:mysql://localhost:3306/house?useSSL=false",
                "admin",
                "admin"
            );
            NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
            brickRepository = new MySqlBrickRepository(jdbcTemplate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("writes to database")
    void test() {

        brickRepository.save(new Brick(1L, "clay"));

        Brick brick = brickRepository.findById(1L);

        assertThat(brick.getMaterial()).isEqualTo("clay");
    }

    @AfterEach
    void tearDown() {
        brickRepository.deleteAll();
    }
}
