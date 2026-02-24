package respository;
import main.java.model.Job;
import main.java.model.Usuario;
import main.java.model.Categoria;

import java.util.List;

public interface JobRepository {

    boolean salvarJob(Job job);
    void cancelarJob(Job job);
    List<Job> getJobs();
    List<Job> buscarJobPorUsuario(Usuario usuario);
    List<Job> buscarJobPorCategoria(Categoria categoria);
}
