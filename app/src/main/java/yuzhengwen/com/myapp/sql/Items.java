package yuzhengwen.com.myapp.sql;

public class Items {

    private int _id;
    private String _name;

    public Items(String _name) {
        this._name = _name;
    }

    public Items() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }
}
