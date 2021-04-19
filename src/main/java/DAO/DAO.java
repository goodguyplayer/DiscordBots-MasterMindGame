package DAO;

import java.util.List;

/**
 * Interface DAO
 *
 * @author Nathan (goodguyplayer)
 * @Version 0
 * @since 2021-04-18
 */

/*
Changelog.:

Version 0.:
    - interface created
 */

public interface DAO<T>{
    List<T> get(String condition);
    List<T> getAll();
    int update(T t);
    int delete(T t);
    int create(T t);
}