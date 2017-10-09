/**
 * Created by bing on 17/9/1.
 */
public class UserDao implements Dao<User>{
    public User getObject(int id) {
        return new User(1, "111");
    }
}
