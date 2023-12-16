package service;

import java.util.List;

public interface Service<T> {
    Boolean add(T t);
    List<T> get();
    String update(T t, String email);
    Boolean delete(T t);

    
}
