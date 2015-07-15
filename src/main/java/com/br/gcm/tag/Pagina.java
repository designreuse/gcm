package com.br.gcm.tag;

import org.springframework.data.domain.Pageable;

public class Pagina {

    private int page;
    private int size;
    private long total;

    public Pagina(Pageable pageable, long total) {
        this(pageable.getPageNumber(), pageable.getPageSize(), total);
    }

    public Pagina(int page, int size, long total) {
        this.page = page;
        this.size = size;
        this.total = total;
    }

    public int getTotalPaginas() {
        double t = total, s = size, r = t / s;
        return (int) Math.ceil(r);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

}