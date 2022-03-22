package com.example.testws;

import java.util.List;

import retrofit2.Call;

public interface DAO {
    Object create(Object object);
    Object update(Object object);
    Object get(Object key);
    List<Object> getAll();
    boolean delete(Object key);
}
