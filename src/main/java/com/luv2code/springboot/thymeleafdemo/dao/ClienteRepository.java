package com.luv2code.springboot.thymeleafdemo.dao;

import com.luv2code.springboot.thymeleafdemo.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

    @Query("SELECT c FROM Cliente c WHERE c.razaoSocial LIKE %:keyword% or c.nomeFantasia LIKE %:keyword% or c.cnpj LIKE %:keyword% or c.email LIKE %:keyword%")
    List<Cliente> search(@Param("keyword") String keyword);

    List<Cliente> findByIdContaining(int keyword);

    List<Cliente> findByRazaoSocialContaining(String keyword);

    List<Cliente> findByNomeFantasiaContaining(String keyword);
}
