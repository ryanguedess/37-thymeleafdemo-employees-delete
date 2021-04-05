package com.luv2code.springboot.thymeleafdemo.service;

import com.luv2code.springboot.thymeleafdemo.FIELD;
import com.luv2code.springboot.thymeleafdemo.entity.Cliente;
import com.luv2code.springboot.thymeleafdemo.entity.Produto;
import com.luv2code.springboot.thymeleafdemo.paging.Paged;

import java.util.List;

public interface Servicer<T> {

    public List<T> findAll();

    public Paged<T> findAll(int pageNumber, int size);

    public T findById(int Id);

    public T save(T type);

    public void deleteById(int id);

    Paged<Cliente> search(int pageNumber, int size, String keyword, FIELD field);
}
