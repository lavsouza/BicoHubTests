package main.java.model;

import model.FotosJob;

import java.time.LocalDateTime;

public class Job {
    private String titulo;
    private String descricao;
    private String status;
    private LocalDateTime dataCriacao;
    private double valorFinal;
    private Usuario cliente;
    private Categoria categoria;
    private FotosJob fotosJob;

    public Job() {
    }

    public Job(String titulo, String descricao, String status, LocalDateTime dataCriacao,
               double valorFinal, Usuario cliente, Categoria categoria) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.valorFinal = valorFinal;
        this.cliente = cliente;
        this.categoria = categoria;
        this.fotosJob = new FotosJob();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public FotosJob getFotosJob() {return fotosJob;}
}