package main.java.model;

public class Avaliacao {
    private int qualidade;
    private int pontualidade;
    private int prestatividade;
    private String comentario;
    private Job job;
    private Profissional profissional;

    public Avaliacao() {
    }

    public Avaliacao(int qualidade, int pontualidade, int prestatividade,
                     String comentario, Job job, Profissional profissional) {
        this.qualidade = qualidade;
        this.pontualidade = pontualidade;
        this.prestatividade = prestatividade;
        this.comentario = comentario;
        this.job = job;
        this.profissional = profissional;
    }

    public int getQualidade() {
        return qualidade;
    }

    public void setQualidade(int qualidade) {
        this.qualidade = qualidade;
    }

    public int getPontualidade() {
        return pontualidade;
    }

    public void setPontualidade(int pontualidade) {
        this.pontualidade = pontualidade;
    }

    public int getPrestatatividade() {
        return prestatividade;
    }

    public void setPrestatatividade(int prestatividade) {
        this.prestatividade = prestatividade;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }
}