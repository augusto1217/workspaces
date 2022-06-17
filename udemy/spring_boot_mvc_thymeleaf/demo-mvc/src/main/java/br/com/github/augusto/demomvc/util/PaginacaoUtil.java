package br.com.github.augusto.demomvc.util;

import java.util.List;

public class PaginacaoUtil<T> {
    
    private int tamanho;
    private int pagina;
    private long totalDePaginas;
    private String direcao;
    private List<T> registros;


    public PaginacaoUtil(int tamanho, int pagina, long totalDePaginas, String direcao, List<T> registros) {
        this.tamanho = tamanho;
        this.pagina = pagina;
        this.totalDePaginas = totalDePaginas;
        this.direcao = direcao;
        this.registros = registros;
    }
    
    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getTamanho() {
        return tamanho;
    }
    
    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public int getPagina() {
        return pagina;
    }

    public void setTotalDePaginas(long totalDePaginas) {
        this.totalDePaginas = totalDePaginas;
    }

    public long getTotalDePaginas() {
        return totalDePaginas;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    public String getDirecao() {
        return direcao;
    }

    public void setRegistros(List<T> registros) {
        this.registros = registros;
    }

    public List<T> getRegistros() {
        return registros;
    }

}
