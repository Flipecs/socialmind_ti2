package service;

import dao.PacienteDAO;
import model.Paciente;

import spark.Request;
import spark.Response;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PacienteService {

    private PacienteDAO PacienteDAO;

    public PacienteService() {
        try {
            PacienteDAO = new PacienteDAO();
            PacienteDAO.conectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Object addPaciente(Request request, Response response) throws Exception {
        SimpleDateFormat br_format = new SimpleDateFormat("dd/MM/yyyy");

        String Nome = request.queryParams("txt_nome");
        String Sobrenome = request.queryParams("txt_sobrenome");
        int Anonimo = Integer.parseInt(request.queryParams("select_anonimo"));
        String Sexo = request.queryParams("select_genre");
        Date Criacao = new Date();
        Date DataNascimento = br_format.parse(request.queryParams("user_birth"));
        String Telefone = request.queryParams("txt_phone");
        String Cep = request.queryParams("txt_cep");
        String Valor = request.queryParams("select_valor");
        String Sobre = request.queryParams("user_message");
        String Senha = request.queryParams("txt_senha");
        String Senha2 = request.queryParams((String) "txt_senha2");
        String Email = request.queryParams("txt_email");
        String Cpf = request.queryParams("txt_cpf");

        int Id = (PacienteDAO.getMaxCodigo() + 4);
        System.out.println("id = " + Id);

        Paciente Paciente = new Paciente(Id, Nome, Sobrenome, Anonimo, Sexo, Telefone, Cep, Valor, Senha, Senha2, Cpf,
                Email, Sobre, Criacao, DataNascimento);

        PacienteDAO.inserirPaciente(Paciente);

        response.status(201);

        return Id;
    }


    public Object getPaciente(Request request, Response response) {
        int id = Integer.parseInt(request.params(":Id"));

        Paciente Paciente = (Paciente) PacienteDAO.procurarPaciente(id);

        if (Paciente != null) {
            response.header("Content-Type", "application/xml");
            response.header("Content-Encoding", "UTF-8");

            return "<Paciente>\n" + "\t<id>" + Paciente.getId() + "</id>\n" + "\t<nome>" + Paciente.getNome()
                    + "</nome>\n" + "\t<sobrenome>" + Paciente.getSobrenome() + "</sobrenome>\n" + "\t<Anonimo>"
                    + Paciente.getAnonimo() + "</Anonimo>\n" + "\t<sexo>" + Paciente.getSexo() + "</sexo>\n"
                    + "\t<Telefone>" + Paciente.getTelefone() + "</Telefone>\n" + "\t<CEP>" + Paciente.getCEP()
                    + "</CEP>\n" + "\t<Valor>" + Paciente.getValor() + "</Valor>\n" + "\t<Senha>" + Paciente.getSenha()
                    + "</Senha>\n" + "\t<Senha2>" + Paciente.getSenha2() + "</Senha2>\n" + "\t<Cpf>" + Paciente.getCpf()
                    + "</Cpf>\n" + "\t<Email>" + Paciente.getEmail() + "</Email>\n" + "\t<Sobre>" + Paciente.getSobre()
                    + "</Sobre>\n" + "\t<Criacao>" + Paciente.getCriacao() + "</Criacao>\n" + "\t<Nascimento>"
                    + Paciente.getNascimento() + "</Nascimento>\n" + "</Paciente>\n";

        } else {
            response.status(404); // 404 erro
            return "Paciente com id [" + id + "] nao encontrado.";
        }
    }

    public Object updatePaciente(Request request, Response response) throws Exception {
        int id = Integer.parseInt(request.params(":Id"));
        SimpleDateFormat br_format = new SimpleDateFormat("dd/MM/yyyy");
        Paciente Paciente = (Paciente) PacienteDAO.procurarPaciente(id);

        if (Paciente != null) {
            Paciente.setAnonimo(Integer.parseInt(request.queryParams("select_anonimo")));
            Paciente.setCpf(request.queryParams("txt_cpf"));
            Paciente.setNome(request.queryParams("txt_nome"));
            Paciente.setSobenome(request.queryParams("txt_sobrenome"));
            Paciente.setEmail(request.queryParams("txt_email"));
            Paciente.setSobre(request.queryParams("user_message"));
            Paciente.setNascimento(br_format.parse(request.queryParams("user_birth")));
            Paciente.setSexo(request.queryParams("select_genre"));
            Paciente.setTelefone(request.queryParams("txt_phone"));
            Paciente.setCEP(request.queryParams("txt_cep"));
            Paciente.setSenha(request.queryParams("txt_senha"));
            Paciente.setSenha2(request.queryParams("txt_senha2"));
            Paciente.setValor(request.queryParams("select_valor"));

            PacienteDAO.atualizarPaciente(Paciente);

            return id;
        } else {
            response.status(404); // 404 Erro
            return "Paciente com id [" + id + "] nao econtrado";
        }
    }

    public Object removePaciente(Request request, Response response) {
        int id = Integer.parseInt(request.params(":Id"));

        Paciente Paciente = (Paciente) PacienteDAO.procurarPaciente(id);

        if (Paciente != null) {
            PacienteDAO.excluirPaciente(id);

            response.status(200); // Sucesso
            return id;
        } else {
            response.status(404); // 404 Erro
            return "Paciente com id [" + id + "] nao econtrado";
        }
    }

    public Object getAllPaciente(Request request, Response response) {
        StringBuffer returnValue = new StringBuffer("<Pacientes type=\"array\">");

        if (PacienteDAO.getPacientes() != null) {
            for (Paciente Paciente : PacienteDAO.getPacientes()) {
                returnValue.append("<Paciente>\n" + "\t<id>" + Paciente.getId() + "</id>\n" + "\t<nome>"
                        + Paciente.getNome() + "</nome>\n" + "\t<sobrenome>" + Paciente.getSobrenome()
                        + "</sobrenome>\n" + "\t<Anonimo>" + Paciente.getAnonimo() + "</Anonimo>\n" + "\t<sexo>"
                        + Paciente.getSexo() + "</sexo>\n" + "\t<Telefone>" + Paciente.getTelefone() + "</Telefone>\n"
                        + "\t<CEP>" + Paciente.getCEP() + "</CEP>\n" + "\t<Valor>" + Paciente.getValor() + "</Valor>\n"
                        + "\t<Senha>" + Paciente.getSenha() + "</Senha>\n" + "\t<Senha2>" + Paciente.getSenha2()
                        + "</Senha2>\n" + "\t<Cpf>" + Paciente.getCpf() + "</Cpf>\n" + "\t<Email>" + Paciente.getEmail()
                        + "</Email>\n" + "\t<Sobre>" + Paciente.getSobre() + "</Sobre>\n" + "\t<Criacao>"
                        + Paciente.getCriacao() + "</Criacao>\n" + "\t<Nascimento>" + Paciente.getNascimento()
                        + "</Nascimento>\n" + "</Paciente>\n");
            }
        } else {
            response.status(404); // 404 Erro
            System.out.println("Lista de Pacientes vazia");
        }

        returnValue.append("</Pacientes>");
        response.header("Content-Type", "application/xml");
        response.header("Content-Encoding", "UTF-8");

        return returnValue.toString();
    }
}
