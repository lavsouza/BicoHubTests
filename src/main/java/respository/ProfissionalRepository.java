package respository;
import main.java.model.Profissional;

public interface ProfissionalRepository {

    boolean salvar(Profissional profissional);
    boolean atualizar(Profissional profissionalAtualizado);
    void deletarPorCpf(String cpfProfissional);
    boolean existePorCpf(String cpfProfissional);
    Profissional buscarPorCpf(String cpfProfissional);
}
