package com.luv2code.springboot.thymeleafdemo.service;

import com.luv2code.springboot.thymeleafdemo.FIELD;
import com.luv2code.springboot.thymeleafdemo.dao.ClienteRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Cliente;
import com.luv2code.springboot.thymeleafdemo.paging.Page;
import com.luv2code.springboot.thymeleafdemo.paging.Paged;
import com.luv2code.springboot.thymeleafdemo.paging.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Paged<Cliente> findAll(int pageNumber, int size) {

        List<Cliente> clientes = clienteRepository.findAll();
        List<Cliente> paged = clientes.stream()
                                        .skip(pageNumber>1?pageNumber*size:0)
                                        .limit(size)
                                        .collect(Collectors.toList());
        int totalPages = clientes.size()/size;

        return new Paged<>(new Page<>(paged, totalPages), Paging.of(totalPages, pageNumber, size));
    }

    @Override
    public Cliente findById(int id) {

        Optional<Cliente> result = clienteRepository.findById(id);

        Cliente cliente = null;

        if(result.isPresent()){
            cliente = result.get();
        }else{
            throw new RuntimeException("Cliente não encontrado" + id);
        }

        return cliente;
    }

    @Override
    public Cliente save(Cliente cliente) {
        clienteRepository.save(cliente);
        return cliente;
    }

    @Override
    public void deleteById(int id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Paged<Cliente> search(int pageNumber, int size, String keyword, FIELD field) {
        List<Cliente> clientes = null;
        if(field == FIELD.CODIGO){
           clientes = clienteRepository.findByIdContaining(Integer.parseInt(keyword));
        }else if(field == FIELD.NOME_FANTASIA){
            clientes = clienteRepository.findByNomeFantasiaContaining(keyword);
        }else{
            clientes = clienteRepository.findByRazaoSocialContaining(keyword);
        }

        List<Cliente> paged = clientes.stream()
                                        .skip(pageNumber>1?pageNumber*size:0)
                                        .limit(size)
                                        .collect(Collectors.toList());
        int totalPages = clientes.size()/size;

        return new Paged<>(new Page<>(paged, totalPages),Paging.of(totalPages,pageNumber,size));
    }
}
