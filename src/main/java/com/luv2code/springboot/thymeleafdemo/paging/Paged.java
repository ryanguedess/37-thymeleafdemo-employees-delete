package com.luv2code.springboot.thymeleafdemo.paging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paged<T> {
    private Page<T> page;
    private Paging paging;
}
