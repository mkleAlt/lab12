package interfaces;

import java.util.List;
import java.util.function.Predicate;

public interface RepositoryInterface<T> {
    boolean add(T object);
    boolean update(String index, T object);
    List<T> getAll();
    List<T> filter(Predicate<T> predicate);
    boolean remove(String index);
}
