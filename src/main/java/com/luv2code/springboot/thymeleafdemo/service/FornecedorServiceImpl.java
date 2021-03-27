package com.luv2code.springboot.thymeleafdemo.service;

import com.luv2code.springboot.thymeleafdemo.dao.FornecedorRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Fornecedor;
import com.luv2code.springboot.thymeleafdemo.entity.Produto;
import com.luv2code.springboot.thymeleafdemo.paging.Paged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorServiceImpl implements Servicer<Fornecedor> {

    private FornecedorRepository fornecedorRepository;

    @Autowired
    public FornecedorServiceImpl(FornecedorRepository thefornecedorRepository){
        fornecedorRepository = thefornecedorRepository;
    }

    @Override
    public List<Fornecedor> findAll(){return fornecedorRepository.findAllByOrderByRazaoSocialAsc();}

    @Override
    public Paged<Fornecedor> findAll(int pageNumber, int size) {
        return null;
    }

    @Override
    public Fornecedor findById(int id){
        Optional<Fornecedor> result = fornecedorRepository.findById(id);

        Fornecedor fornecedor = null;

        if(result.isPresent()){
            fornecedor = result.get();
        }else{
            throw new RuntimeException("Fornecedor não encontrado" + id);
        }
        return fornecedor;
    }

    @Override
    public Produto save(Fornecedor fornecedor){ fornecedorRepository.save(fornecedor);
        return null;
    }

    @Override
    public void deleteById(int id){fornecedorRepository.deleteById(id);}

    @Override
    public Paged<Fornecedor> search(int pageNumber, int size, String keyword) {
        return null;
    }


}
