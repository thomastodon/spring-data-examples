import java.util.Optional;

public interface BrickRepository {

    void save(Brick brick);

    Optional<Brick> findById(long id);

    void deleteAll();
}
