package io.thomastodon.spring.data.examples.brick;

import io.thomastodon.spring.data.examples.Utility;
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
            .orElseGet(() -> Assertions.fail("io.thomastodon.spring.data.examples.brick.Brick does not exist"));

        assertThat(brick.getMaterial()).isEqualTo("clay");
    }

    @Test
    @DisplayName("returns an empty optional if the io.thomastodon.spring.data.examples.brick is not there")
    void test01() {
        assertThat(brickRepository.findById(1L)).isEmpty();
    }

    @AfterEach
    void tearDown() {
        brickRepository.deleteAll();
    }
}
