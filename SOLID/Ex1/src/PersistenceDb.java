import java.util.List;

public interface PersistenceDb {

    void save(StudentRecord r);

    int count();

    List<StudentRecord> all();
}