public interface BrickRepository {

    void save(Brick brick);

    Brick findById(long id);

    void deleteAll();
}
