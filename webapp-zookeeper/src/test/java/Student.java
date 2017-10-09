/**
 * Created by bing on 17/8/31.
 */
public class Student {
    private Integer cid;
    private String cname;

    public Student(Integer id, String name) {
        this.cid = id;
        this.cname = name;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
