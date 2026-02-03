package main.java.model;

import java.time.LocalDateTime;

public class Proposta {
    private double valor;
    private String status;
    private boolean solicitaVisita;
    private LocalDateTime dataCriacao;
    private Profissional profissional;
    private Job job;

    public Proposta() {
    }

    public Proposta(double valor, String status, boolean solicitaVisita,
                    LocalDateTime dataCriacao, Profissional profissional, Job job) {
        this.valor = valor;
        this.status = status;
        this.solicitaVisita = solicitaVisita;
        this.dataCriacao = dataCriacao;
        this.profissional = profissional;
        this.job = job;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSolicitaVisita() {
        return solicitaVisita;
    }

    public void setSolicitaVisita(boolean solicitaVisita) {
        this.solicitaVisita = solicitaVisita;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}