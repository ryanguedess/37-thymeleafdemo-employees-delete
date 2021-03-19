package com.luv2code.springboot.thymeleafdemo.service;

import com.luv2code.springboot.thymeleafdemo.dao.ClienteRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Cliente;
import com.luv2code.springboot.thymeleafdemo.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements Servicer<Cliente> {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findById(int id) {

        Optional<Cliente> result = clienteRepository.findById(id);

        Cliente cliente = null;

        if(result.isPresent()){
            cliente = result.get();
        }else{
            throw new RuntimeException("Fornecedor não encontrado" + id);
        }

        return cliente;
    }

    @Override
    public Produto save(Cliente cliente) {
        clienteRepository.save(cliente);
        return null;
    }

    @Override
    public void deleteById(int id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public List<Cliente> search(String theSearchName) {
        return clienteRepository.search(theSearchName);
    }
}
