package com.luv2code.springboot.thymeleafdemo.paging;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Paging {

    private static final int PAGINATION_STEP = 3;

    private boolean nextEnabled;
    private boolean prevEnabled;

    private int pageSize;
    private int pageNumber;
    private List<PageItem> items = new ArrayList<>();

    public void addPageItems(int from, int to, int pageNumber) {
        //1,6,1
        //1,10,1
        for (int i = from; i < to; i++) {
            items.add(PageItem.builder()
                    .active(pageNumber != i)
                    .index(i)
                    .pageItemType(PageItemType.PAGE)
                    .build());
        }
    }

    public void last(int pageSize) {
        items.add(PageItem.builder()
                .active(false)
                .pageItemType(PageItemType.DOTS)
                .build());

        items.add(PageItem.builder()
                .active(true)
                .index(pageSize)
                .pageItemType(PageItemType.PAGE)
                .build());
    }

    public void first(int pageNumber) {
        items.add(PageItem.builder()
                .active(pageNumber != 1)
                .index(1)
                .pageItemType(PageItemType.PAGE)
                .build());

        items.add(PageItem.builder()
                .active(false)
                .pageItemType(PageItemType.DOTS)
                .build());
    }

    public static Paging of(int totalPages, int pageNumber, int pageSize) {
        Paging paging = new Paging();
        paging.setPageSize(pageSize);
        paging.setNextEnabled(pageNumber != totalPages);
        paging.setPrevEnabled(pageNumber != 1);
        paging.setPageNumber(pageNumber);

        //se o total de páginas for menos que 12
        if (totalPages < PAGINATION_STEP * 2 + 6) {

            paging.addPageItems(1, totalPages + 1, pageNumber);
            //se o page number for menor que 7
        } else if (pageNumber < PAGINATION_STEP * 2 + 1) {
            //1,10,1
            paging.addPageItems(1, PAGINATION_STEP * 2 + 4, pageNumber);
            paging.last(totalPages);
            //se o page number for maior que -1
        } else if (pageNumber > totalPages - PAGINATION_STEP * 2) {
            paging.first(pageNumber);
            paging.addPageItems(totalPages - PAGINATION_STEP * 2 - 2, totalPages + 1, pageNumber);
            //
        } else {
            paging.first(pageNumber);
            paging.addPageItems(pageNumber - PAGINATION_STEP, pageNumber + PAGINATION_STEP + 1, pageNumber);
            paging.last(totalPages);
        }

        return paging;
    }

}
