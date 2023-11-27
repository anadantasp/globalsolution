package br.com.fiap.globalsolution.config;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.globalsolution.model.Especialidade;
import br.com.fiap.globalsolution.model.ItemReceita;
import br.com.fiap.globalsolution.model.Medicamento;
import br.com.fiap.globalsolution.model.Receita;
import br.com.fiap.globalsolution.model.Pessoa.Medico;
import br.com.fiap.globalsolution.model.Pessoa.Paciente;
import br.com.fiap.globalsolution.repository.EspecialidadeRepository;
import br.com.fiap.globalsolution.repository.ItemReceitaRepository;
import br.com.fiap.globalsolution.repository.MedicamentoRepository;
import br.com.fiap.globalsolution.repository.MedicoRepository;
import br.com.fiap.globalsolution.repository.PacienteRepository;
import br.com.fiap.globalsolution.repository.ReceitaRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner{

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private ItemReceitaRepository itemReceitaRepository;

    @Autowired
    private ReceitaRepository receitaRepository;

    private Especialidade clinico = new Especialidade(null, "Clinico Geral");
    private Especialidade ortopedista = new Especialidade(null, "Ortopedista");

    private Medico medico1 = new Medico(null, "Maria Clara", "mariaclara@email.com.br", "12345", "senha123", ortopedista);
    private Medico medico2 = new Medico(null, "Eduardo", "eduardo@email.com.br", "67890", "senha123", clinico);

    private Paciente paciente = new Paciente(null, "Arthur", "arthur@email.com.br", "123456789", LocalDate.of(1998, 4, 20));
    private Paciente paciente2 = new Paciente(null, "Bruna", "bruna@email.com.br", "987654321", LocalDate.of(1990, 9, 15));

    private Medicamento medicamento = new Medicamento(null, "Neosaldina");
    private Medicamento medicamento1 = new Medicamento(null, "Buscopan");
    private Medicamento medicamento2 = new Medicamento(null, "Postan");
    private Medicamento medicamento3 = new Medicamento(null, "Stezza");
    private Medicamento medicamento4 = new Medicamento(null, "Naramig");
    private Medicamento medicamento5 = new Medicamento(null, "Floratil");

    private ItemReceita itemReceita = new ItemReceita(null, "para dores de cabeça", "3 dias", "500mg", medicamento);
    private ItemReceita itemReceita1 = new ItemReceita(null, "para dores abdominais", "4 dias", "600mg", medicamento1);
    private ItemReceita itemReceita2 = new ItemReceita(null, "antiinflamatorio", "3 dias", "500mg", medicamento2);
    private ItemReceita itemReceita3 = new ItemReceita(null, "anticoncepcional", "continuo", "1 caixa por mes", medicamento3);
    private ItemReceita itemReceita4 = new ItemReceita(null, "para enxaqueca", "3 dias", "500mg", medicamento4);
    private ItemReceita itemReceita5 = new ItemReceita(null, "para intestino", "4 dias", "500mg", medicamento5);

    

    @Override
    public void run(String... args) throws Exception {

        especialidadeRepository.saveAll(
            List.of(clinico, ortopedista)
        );
        
        medicoRepository.saveAll(
            List.of(medico1, medico2)
        );
        

        pacienteRepository.saveAll(
            List.of(paciente, paciente2)
        );

        medicamentoRepository.saveAll(
            List.of(medicamento, medicamento1, medicamento2, medicamento3, medicamento4, medicamento5)
        );

        itemReceitaRepository.saveAll(
            List.of(itemReceita, itemReceita1, itemReceita2, itemReceita3, itemReceita4, itemReceita5)
        );

        receitaRepository.saveAll(
            List.of(
                new Receita()
                 .withData(LocalDate.now())
                 .withDataValidade(LocalDate.now().plusDays(7))
                 .withStatus(false)
                 .withMedico(medico1)
                 .withPaciente(paciente)
                 .addSolicitacao(itemReceita),

                 new Receita()
                 .withData(LocalDate.now())
                 .withDataValidade(LocalDate.now().plusDays(3))
                 .withStatus(false)
                 .withMedico(medico2)
                 .withPaciente(paciente2)
                 .addSolicitacao(itemReceita1),

                 new Receita()
                 .withData(LocalDate.now())
                 .withDataValidade(LocalDate.now().plusDays(30))
                 .withStatus(true)
                 .withMedico(medico1)
                 .withPaciente(paciente)
                 .addSolicitacao(itemReceita2)
                 .addSolicitacao(itemReceita3),

                 new Receita()
                 .withData(LocalDate.now())
                 .withDataValidade(LocalDate.now().plusDays(4))
                 .withStatus(false)
                 .withMedico(medico2)
                 .withPaciente(paciente2)
                 .addSolicitacao(itemReceita4),

                 new Receita()
                 .withData(LocalDate.now())
                 .withDataValidade(LocalDate.now().plusDays(2))
                 .withStatus(false)
                 .withMedico(medico1)
                 .withPaciente(paciente)
                 .addSolicitacao(itemReceita5) 
            )
        );


       
    }
    
}
