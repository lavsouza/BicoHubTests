package service;

import respository.JobRepository;
import main.java.model.Job;

public class JobService {

    protected JobRepository jobRepository;

    public JobService(JobRepository jobRepository) { this.jobRepository = jobRepository; }

    public boolean salvarJob(Job job){
        if(job==null){
            throw new IllegalArgumentException("Job não pode ser nulo.");
        }

        if(job.getCategoria() == null) {
            throw new IllegalArgumentException("Categoria não pode estar vazia.");
        }

        if(job.getDescricao() == null) {
            throw new IllegalArgumentException("Descrição não pode estar vazia");
        }

        if(job.getFotosJob().getFotos()==null){
            throw new IllegalArgumentException("Job não pode ser postado sem fotos");
        }
        
        return true;
    }
}
