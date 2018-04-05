import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

class MySqlBrickRepositoryTest {

    private BrickRepository brickRepository;

    @BeforeEach
    void setUp() {
        brickRepository = new MySqlBrickRepository(Utility.jdbcTemplate);
    }

    @Test
    @DisplayName("writes to and reads from the database")
    void test00() {

        brickRepository.save(new Brick(1L, "clay"));

        Brick brick = brickRepository
            .findById(1L)
            .orElseGet(() -> Assertions.fail("Brick does not exist"));

        assertThat(brick.getMaterial()).isEqualTo("clay");
    }

    @Test
    @DisplayName("returns an empty optional if the brick is not there")
    void test01() {
        assertThat(brickRepository.findById(1L)).isEmpty();
    }

    @AfterEach
    void tearDown() {
        brickRepository.deleteAll();
    }
}
