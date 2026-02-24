package model;
import java.util.ArrayList;
import main.java.model.Usuario;
import main.java.model.Job;

public class Portfolio {
    private Usuario usuario;
    private ArrayList<Job> jobsUsuario;

    public Portfolio(Usuario usuario) {
        this.usuario = usuario;
        this.jobsUsuario = new ArrayList<Job>();
    }

    public void addJob(Job novoJob){
        jobsUsuario.add(novoJob);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Job> getJobsUsuario() {
        return jobsUsuario;
    }

    public void setJobsUsuario(ArrayList<Job> jobsUsuario) {
        this.jobsUsuario = jobsUsuario;
    }
}
