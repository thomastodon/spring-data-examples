import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MySqlBrickRepositoryTest {

    private BrickRepository brickRepository;

    @BeforeEach
    void setUp() {
        brickRepository = new MySqlBrickRepository(Utility.jdbcTemplate);
    }

    @Test
    @DisplayName("writes to and reads from the database")
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
