package main.java.model;

public class Profissional {
    private Usuario usuario;
    private String documentoIdentificacao;
    private String fotoComDocumento;
    private double notaMedia;
    private int raioAtendimentoKm;

    public Profissional() {
    }

    public Profissional(Usuario usuario, String documentoIdentificacao, String fotoComDocumento,
                        double notaMedia, int raioAtendimentoKm) {
        this.usuario = usuario;
        this.documentoIdentificacao = documentoIdentificacao;
        this.fotoComDocumento = fotoComDocumento;
        this.notaMedia = notaMedia;
        this.raioAtendimentoKm = raioAtendimentoKm;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDocumentoIdentificacao() {
        return documentoIdentificacao;
    }

    public void setDocumentoIdentificacao(String documentoIdentificacao) {
        this.documentoIdentificacao = documentoIdentificacao;
    }

    public String getFotoComDocumento() {
        return fotoComDocumento;
    }

    public void setFotoComDocumento(String fotoComDocumento) {
        this.fotoComDocumento = fotoComDocumento;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public int getRaioAtendimentoKm() {
        return raioAtendimentoKm;
    }

    public void setRaioAtendimentoKm(int raioAtendimentoKm) {
        this.raioAtendimentoKm = raioAtendimentoKm;
    }
}