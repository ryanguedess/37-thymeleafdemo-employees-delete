package com.luv2code.springboot.thymeleafdemo.service;

import com.luv2code.springboot.thymeleafdemo.entity.Produto;

import java.util.List;

public interface Servicer<T> {

    public List<T> findAll();

    public T findById(int Id);

    public Produto save(T type);

    public void deleteById(int id);

    List<T> search(String theSearchName);
}
