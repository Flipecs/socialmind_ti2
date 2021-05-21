package dao;

import java.sql.*;
import model.Paciente;

public class PacienteDAO {
	private Connection conexao;
	private int maxId;

	public PacienteDAO() {
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

	public boolean inserirPaciente(Paciente Paciente) {
		boolean status = false;

		try {
			Statement st = conexao.createStatement();
			st.executeUpdate(
					"INSERT INTO Paciente (Id, Nome, Sobrenome, Anonimo, Sexo, Telefone, CEP, Valor, Senha, Senha2, CPF, Email, Sobre, Criacao, DataNascimento)"
							+ "VALUES (" + Paciente.getId() + ", '" + Paciente.getNome() + "', '"
							+ Paciente.getSobrenome() + "', '" + Paciente.getAnonimo() + "', '" + Paciente.getSexo()
							+ "', '" + Paciente.getTelefone() + "', '" + Paciente.getCEP() + "', '"
							+ Paciente.getValor() + "', '" + Paciente.getSenha() + "', '" + Paciente.getSenha2()
							+ "', '" + Paciente.getCpf() + "', '" + Paciente.getEmail() + "', '" + Paciente.getSobre()
							+ "', '" + Paciente.getCriacao() + "', '" + Paciente.getNascimento() + "');");
			st.close();
			status = true;

			// Somar mais um ao maxID
			this.maxId = maxId + 1;
			Paciente.setId(maxId + 1);

			System.out.println("Insercao do paciente com id [" + Paciente.getId() + "] efetuada com sucesso.");

		} catch (SQLException u) {
			throw new RuntimeException(u);
		}

		return status;
	}

	public boolean atualizarPaciente(Paciente Paciente) {
		boolean status = false;

		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE Paciente SET Nome = '" + Paciente.getNome() + "', Sobrenome = '"
					+ Paciente.getSobrenome() + "', DataNascimento = '" + Paciente.getNascimento() + "', Senha = '"
					+ Paciente.getSenha() + "', Email = '" + Paciente.getEmail() + "', Sexo = '" + Paciente.getSexo()
					+ "', Anonimo = '" + Paciente.getAnonimo() + "', Telefone = '" + Paciente.getTelefone()
					+ "', CEP = '" + Paciente.getCEP() + "', Valor = '" + Paciente.getValor() + "', Senha2 = '"
					+ Paciente.getSenha2() + "', CPF = '" + Paciente.getCpf() + "', Sobre = '" + Paciente.getSobre()
					+ "', Criacao = '" + Paciente.getCriacao() + "'" + " WHERE Id = " + Paciente.getId();
			st.executeUpdate(sql);
			st.close();
			status = true;

			System.out.println("Atualizacao do Paciente com codigo [" + Paciente.getId() + "] efetuada com sucesso.");
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}

		return status;
	}

	public boolean excluirPaciente(int id) {
		boolean status = false;

		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM Paciente WHERE Id = " + id);
			st.close();
			status = true;

			// subtrair um ao maxID
			if (this.maxId > 0) {
				this.maxId = this.maxId - 1;
			}

			System.out.println("Remocao do Paciente com id [" + id + "] efetuada com sucesso.");
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}

		return status;
	}

	public Paciente[] getPacientes() {
		Paciente[] Pacientes = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM Paciente");
			if (rs.next()) {
				rs.last();
				Pacientes = new Paciente[rs.getRow()];
				rs.beforeFirst();

				for (int i = 0; rs.next(); i++) {
					Pacientes[i] = new Paciente(rs.getInt("Id"), rs.getString("Nome"), rs.getString("Sobrenome"),
							rs.getInt("Anonimo"), rs.getString("Sexo"), rs.getString("Telefone"), rs.getString("CEP"),
							rs.getString("Valor"), rs.getString("Senha"), rs.getString("Senha2"), rs.getString("CPF"),
							rs.getString("Email"), rs.getString("Sobre"), rs.getDate("Criacao"),
							rs.getDate("DataNascimento"));
				}
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return Pacientes;
	}

	public Paciente procurarPaciente(int id) {
		Paciente Pacientes = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM Paciente WHERE Paciente.Id = " + id);

			if (rs.next()) {
				Pacientes = new Paciente(rs.getInt("Id"), rs.getString("Nome"), rs.getString("Sobrenome"),
						rs.getInt("Anonimo"), rs.getString("Sexo"), rs.getString("Telefone"), rs.getString("CEP"),
						rs.getString("Valor"), rs.getString("Senha"), rs.getString("Senha2"), rs.getString("CPF"),
						rs.getString("Email"), rs.getString("Sobre"), rs.getDate("Criacao"),
						rs.getDate("DataNascimento"));
			}

			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return Pacientes;
	}
}
