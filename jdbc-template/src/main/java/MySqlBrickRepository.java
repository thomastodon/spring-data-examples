import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;

public class MySqlBrickRepository implements BrickRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private BrickRowMapper brickRowMapper;

    MySqlBrickRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.brickRowMapper = new BrickRowMapper();
    }

    @Override
    public void save(Brick brick) {

        String sql = "INSERT INTO brick (id, material) VALUES (:id, :material)";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", brick.getId());
        parameters.put("material", brick.getMaterial());

        jdbcTemplate.update(sql, parameters);
    }

    @Override
    public Brick findById(long id) {

        String sql = "SELECT * FROM brick WHERE id=:id";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);

        return jdbcTemplate.queryForObject(sql, parameters, brickRowMapper);
    }

    @Override
    public void deleteAll() {

        String sql = "DELETE FROM brick";

        jdbcTemplate.update(sql, new HashMap<>());
    }
}
