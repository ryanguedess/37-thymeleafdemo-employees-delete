package com.luv2code.springboot.thymeleafdemo.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Page<T> {
    List<T> content;
    int totalPages;
}
