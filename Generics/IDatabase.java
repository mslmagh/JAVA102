
public interface IDatabase <T> {

    public void insert(T data);

    public boolean delete(T data);

    public boolean update(T data);

    public T select();

}