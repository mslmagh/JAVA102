public class Student<T> implements IDatabase<T> {

    @Override
    public void insert(T data) {
        System.out.println(data);
    }

    @Override
    public boolean delete(T data) {
        return true;
    }

    @Override
    public boolean update(T data) {
        return true;
    }

    @Override
    public T select() {
        return null;
    }

}
