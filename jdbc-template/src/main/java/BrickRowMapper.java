import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrickRowMapper implements RowMapper<Brick> {

    @Override
    public Brick mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Brick(
            rs.getLong("id"),
            rs.getString("material")
        );
    }
}
