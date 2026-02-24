package service;

import respository.ProfissionalRepository;
import main.java.model.Profissional;

public interface ProfissionalService {

    boolean salvarProfissional(Profissional profissional);

    /*protected ProfissionalRepository profissionalRepository;

    public ProfissionalService(ProfissionalRepository profissionalRepository){
        this.profissionalRepository = profissionalRepository;
    }

    public boolean salvarProfissional(Profissional profissional){
        if(profissional==null){
            throw new IllegalArgumentException("O profissional não pode ser nulo.");
        }

        if(profissional.getUsuario()==null || eNuloOuVazio(profissional.getDocumentoIdentificacao()) || eNuloOuVazio(profissional.getFotoComDocumento())) {
            throw new IllegalArgumentException("Nenhum dos campos deve ser vazio.");
        }

        if(profissional.getRaioAtendimentoKm()<=0){
            throw new IllegalArgumentException("O campo raio de atendimento deve ter um número positivo maior que zero (em quilômetros).");
        }

        profissionalRepository.salvar(profissional);
        return true;

    }

    private boolean eNuloOuVazio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }*/
}
