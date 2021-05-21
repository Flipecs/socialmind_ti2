package dao;

import java.sql.*;
import model.Psicologo;

public class PsicologoDAO {
    private Connection conexao;
    private int maxId;

    public PsicologoDAO() {
        conexao = null;
    }

    public int getMaxCodigo() {
        return this.maxId;
    }

    public boolean conectar() {
        String driverName = "org.postgresql.Driver";
        String serverName = "socialmind-ti02.postgres.database.azure.com"; // Nome da azure que ela vai nos fornecer
        String mydatabase = "projeto"; // Eu tenho que criar na azure
        int porta = 5432; // Vou escolher na azure
        String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase + "?gssEncMode=disable";
        String username = "adm@socialmind-ti02";
        String password = "socialmindti-02";
        boolean status = false;

        try {
            Class.forName(driverName);
            conexao = DriverManager.getConnection(url, username, password);
            status = (conexao == null);
            System.out.println("Conexão efetuada com o postgres!");
        } catch (ClassNotFoundException e) {
            System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
        }

        return status;
    }

    public boolean close() {
        boolean status = false;

        try {
            conexao.close();
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }

    public boolean inserirPsicologo(Psicologo Psicologo) {
        boolean status = false;

        try {
            Statement st = conexao.createStatement();
            st.executeUpdate(
                    "INSERT INTO Psicologo (Id, Nome, Sobrenome, Anonimo, Sexo, Telefone, Cep, Valor, Senha, Senha2, Cpf, Email, Sobre, Sobre_trabalho, Criacao, Nascimento, CRP)"
                            + "VALUES (" + Psicologo.getId() + ", '" + Psicologo.getNome() + "', '"
                            + Psicologo.getSobrenome() + "', '" + Psicologo.getAnonimo() + "', '" + Psicologo.getSexo()
                            + "', '" + Psicologo.getTelefone() + "', '" + Psicologo.getCEP() + "', '"
                            + Psicologo.getValor() + "', '" + Psicologo.getSenha() + "', '" + Psicologo.getSenha2()
                            + "', '" + Psicologo.getCpf() + "', '" + Psicologo.getEmail() + "', '"
                            + Psicologo.getSobre() + "', '" + Psicologo.getSobre_trabalho() + "', '"
                            + Psicologo.getCriacao() + "', '" + Psicologo.getNascimento() + "', '" + Psicologo.getCRP()
                            + "');");
            st.close();
            status = true;

            // Somar mais um ao maxID
            this.maxId = maxId + 1;
            Psicologo.setId(maxId + 1);

            System.out.println("Insercao do Psicologo com id [" + Psicologo.getId() + "] efetuada com sucesso.");

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return status;
    }

    public boolean atualizarPsicologo(Psicologo Psicologo) {
        boolean status = false;

        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE Psicologo SET Nome = '" + Psicologo.getNome() + "', Sobrenome = '"
                    + Psicologo.getSobrenome() + "', DataNascimento = '" + Psicologo.getNascimento() + "', Senha = '"
                    + Psicologo.getSenha() + "', Email = '" + Psicologo.getEmail() + "', Sexo = '" + Psicologo.getSexo()
                    + "', Anonimo = '" + Psicologo.getAnonimo() + "', Telefone = '" + Psicologo.getTelefone()
                    + "', CEP = '" + Psicologo.getCEP() + "', Valor = '" + Psicologo.getValor() + "', Senha2 = '"
                    + Psicologo.getSenha2() + "', CPF = '" + Psicologo.getCpf() + "', Sobre = '" + Psicologo.getSobre()
                    + "', Sobre_trabalho = '" + Psicologo.getSobre_trabalho() + "', Criacao = '"
                    + Psicologo.getCriacao() + "', CRP = '" + Psicologo.getCRP() + "'" + " WHERE Id = "
                    + Psicologo.getId();
            st.executeUpdate(sql);
            st.close();
            status = true;

            System.out.println("Atualizacao do Psicologo com codigo [" + Psicologo.getId() + "] efetuada com sucesso.");
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return status;
    }

    public boolean excluirPsicologo(int id) {
        boolean status = false;

        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM Psicologo WHERE Id = " + id);
            st.close();
            status = true;

            // subtrair um ao maxID
            if (this.maxId > 0) {
                this.maxId = this.maxId - 1;
            }

            System.out.println("Remocao do Psicologo com id [" + id + "] efetuada com sucesso.");
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return status;
    }

    public Psicologo[] getPsicologos() {
        Psicologo[] Psicologos = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM Psicologo");
            if (rs.next()) {
                rs.last();
                Psicologos = new Psicologo[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    Psicologos[i] = new Psicologo(rs.getInt("Id"), rs.getString("Nome"), rs.getString("Sobrenome"),
                            rs.getInt("Anonimo"), rs.getString("Sexo"), rs.getString("Telefone"), rs.getString("CEP"),
                            rs.getString("Valor"), rs.getString("Senha"), rs.getString("Senha2"), rs.getString("CPF"),
                            rs.getString("Email"), rs.getString("Sobre"), rs.getString("Sobre_trabalho"),
                            rs.getDate("Criacao"), rs.getDate("DataNascimento"), rs.getString("CRP"));
                }
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return Psicologos;
    }

    public Psicologo procurarPsicologo(int id) {
        Psicologo Psicologos = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM Psicologo WHERE Psicologo.Id = " + id);

            if (rs.next()) {
                Psicologos = new Psicologo(rs.getInt("Id"), rs.getString("Nome"), rs.getString("Sobrenome"),
                        rs.getInt("Anonimo"), rs.getString("Sexo"), rs.getString("Telefone"), rs.getString("CEP"),
                        rs.getString("Valor"), rs.getString("Senha"), rs.getString("Senha2"), rs.getString("CPF"),
                        rs.getString("Email"), rs.getString("Sobre"), rs.getString("Sobre_trabalho"),
                        rs.getDate("Criacao"), rs.getDate("DataNascimento"), rs.getString("CRP"));
            }

            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return Psicologos;
    }
}
