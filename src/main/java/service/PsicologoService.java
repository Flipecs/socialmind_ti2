package service;

import dao.PsicologoDAO;
import model.Psicologo;

import spark.Request;
import spark.Response;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PsicologoService {

    private PsicologoDAO PsicologoDAO;

    public PsicologoService() {
        try {
            PsicologoDAO = new PsicologoDAO();
            PsicologoDAO.conectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Object addPsicologo(Request request, Response response) throws Exception {
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
        String Sobre = request.queryParams("txt_sobre");
        String Sobre_trabalho = request.queryParams("txt_sobre_trabalho");
        String Senha = request.queryParams("txt_senha");
        String Senha2 = request.queryParams("txt_senha2");
        String Email = request.queryParams("txt_email");
        String Cpf = request.queryParams("txt_cpf");
        String CRP = request.queryParams("txt_crp");

        int Id = (PsicologoDAO.getMaxCodigo() + 4);
        System.out.println("id = " + Id);

        Psicologo Psicologo = new Psicologo(Id, Nome, Sobrenome, Anonimo, Sexo, Telefone, Cep, Valor, Senha, Senha2,
                Cpf, Email, Sobre, Sobre_trabalho, Criacao, DataNascimento, CRP);

        PsicologoDAO.inserirPsicologo(Psicologo);

        response.status(201);

        return Id;
    }

    public Object getPsicologo(Request request, Response response) {
        int id = Integer.parseInt(request.params(":Id"));

        Psicologo Psicologo = (Psicologo) PsicologoDAO.procurarPsicologo(id);

        if (Psicologo != null) {
            response.header("Content-Type", "application/xml");
            response.header("Content-Encoding", "UTF-8");

            return "<Psicologo>\n" + "\t<id>" + Psicologo.getId() + "</id>\n" + "\t<nome>" + Psicologo.getNome()
                    + "</nome>\n" + "\t<sobrenome>" + Psicologo.getSobrenome() + "</sobrenome>\n" + "\t<Anonimo>"
                    + Psicologo.getAnonimo() + "</Anonimo>\n" + "\t<sexo>" + Psicologo.getSexo() + "</sexo>\n"
                    + "\t<Telefone>" + Psicologo.getTelefone() + "</Telefone>\n" + "\t<CEP>" + Psicologo.getCEP()
                    + "</CEP>\n" + "\t<Valor>" + Psicologo.getValor() + "</Valor>\n" + "\t<Senha>"
                    + Psicologo.getSenha() + "</Senha>\n" + "\t<Senha2>" + Psicologo.getSenha2() + "</Senha2>\n"
                    + "\t<Cpf>" + Psicologo.getCpf() + "</Cpf>\n" + "\t<Email>" + Psicologo.getEmail() + "</Email>\n"
                    + "\t<Sobre>" + Psicologo.getSobre() + "</Sobre>\n" + "\t<Sobre_trabalho>" + Psicologo.getSobre_trabalho()
                    + "</Sobre_trabalho>\n" + "\t<Criacao>" + Psicologo.getCriacao() + "</Criacao>\n" + "\t<Nascimento>"
                    + Psicologo.getNascimento() + "</Nascimento>\n" + "\t<CRP>" + Psicologo.getCRP()
                    + "</CRP>\n" + "</Psicologo>\n";

        } else {
            response.status(404); // 404 erro
            return "Psicologo com id [" + id + "] nao encontrado.";
        }
    }

    public Object updatePsicologo(Request request, Response response) throws Exception {
        int id = Integer.parseInt(request.params(":Id"));
        SimpleDateFormat br_format = new SimpleDateFormat("dd/MM/yyyy");
        Psicologo Psicologo = (Psicologo) PsicologoDAO.procurarPsicologo(id);

        if (Psicologo != null) {
            Psicologo.setAnonimo(Integer.parseInt(request.queryParams("select_anonimo")));
            Psicologo.setCpf(request.queryParams("txt_cpf"));
            Psicologo.setNome(request.queryParams("txt_nome"));
            Psicologo.setSobenome(request.queryParams("txt_sobrenome"));
            Psicologo.setEmail(request.queryParams("txt_email"));
            Psicologo.setSobre(request.queryParams("txt_sobre"));
            Psicologo.setSobre_trabalho(request.queryParams("txt_sobre_trabalho"));
            Psicologo.setNascimento(br_format.parse(request.queryParams("user_birth")));
            Psicologo.setSexo(request.queryParams("select_genre"));
            Psicologo.setTelefone(request.queryParams("txt_phone"));
            Psicologo.setCEP(request.queryParams("txt_cep"));
            Psicologo.setSenha(request.queryParams("txt_senha"));
            Psicologo.setSenha2(request.queryParams("txt_senha2"));
            Psicologo.setValor(request.queryParams("select_valor"));
            Psicologo.setCRP(request.queryParams("txt_crp"));

            PsicologoDAO.atualizarPsicologo(Psicologo);

            return id;
        } else {
            response.status(404); // 404 Erro
            return "Psicologo com id [" + id + "] nao econtrado";
        }
    }

    public Object removePsicologo(Request request, Response response) {
        int id = Integer.parseInt(request.params(":Id"));

        Psicologo Psicologo = (Psicologo) PsicologoDAO.procurarPsicologo(id);

        if (Psicologo != null) {
            PsicologoDAO.excluirPsicologo(id);

            response.status(200); // Sucesso
            return id;
        } else {
            response.status(404); // 404 Erro
            return "Psicologo com id [" + id + "] nao econtrado";
        }
    }

    public Object getAllPsicologo(Request request, Response response) {
        StringBuffer returnValue = new StringBuffer("<Psicologos type=\"array\">");

        if (PsicologoDAO.getPsicologos() != null) {
            for (Psicologo Psicologo : PsicologoDAO.getPsicologos()) {
                returnValue.append("<Psicologo>\n" + "\t<id>" + Psicologo.getId() + "</id>\n" + "\t<nome>"
                        + Psicologo.getNome() + "</nome>\n" + "\t<sobrenome>" + Psicologo.getSobrenome()
                        + "</sobrenome>\n" + "\t<Anonimo>" + Psicologo.getAnonimo() + "</Anonimo>\n" + "\t<sexo>"
                        + Psicologo.getSexo() + "</sexo>\n" + "\t<Telefone>" + Psicologo.getTelefone() + "</Telefone>\n"
                        + "\t<CEP>" + Psicologo.getCEP() + "</CEP>\n" + "\t<Valor>" + Psicologo.getValor()
                        + "</Valor>\n" + "\t<Senha>" + Psicologo.getSenha() + "</Senha>\n" + "\t<Senha2>"
                        + Psicologo.getSenha2() + "</Senha2>\n" + "\t<Cpf>" + Psicologo.getCpf() + "</Cpf>\n"
                        + "\t<Email>" + Psicologo.getEmail() + "</Email>\n" + "\t<Sobre>" + Psicologo.getSobre()
                        + "</Sobre>\n" + "\t<Sobre_trabalho>" + Psicologo.getSobre_trabalho()
                        + "</Sobre_trabalho>\n" + "\t<Criacao>" + Psicologo.getCriacao() + "</Criacao>\n" + "\t<Nascimento>"
                        + Psicologo.getNascimento() + "</Nascimento>\n" + "\t<CRP>" + Psicologo.getCRP()
                        + "</CRP>\n" + "</Psicologo>\n");
            }
        } else {
            response.status(404); // 404 Erro
            System.out.println("Lista de Psicologos vazia");
        }

        returnValue.append("</Psicologos>");
        response.header("Content-Type", "application/xml");
        response.header("Content-Encoding", "UTF-8");

        return returnValue.toString();
    }
}
