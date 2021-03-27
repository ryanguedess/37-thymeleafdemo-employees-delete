package com.luv2code.springboot.thymeleafdemo.service;

import com.luv2code.springboot.thymeleafdemo.entity.Produto;
import com.luv2code.springboot.thymeleafdemo.paging.Paged;

import java.util.List;

public interface Servicer<T> {

    public List<T> findAll();

    public Paged<T> findAll(int pageNumber, int size);

    public T findById(int Id);

    public Produto save(T type);

    public void deleteById(int id);

    Paged<T> search(int pageNumber, int size,String keyword);

}
