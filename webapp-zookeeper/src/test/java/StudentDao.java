/**
 * Created by bing on 17/9/1.
 */
public class StudentDao implements Dao<Student> {
    public Student getObject(int id) {
        return new Student(2, "222");
    }
}
