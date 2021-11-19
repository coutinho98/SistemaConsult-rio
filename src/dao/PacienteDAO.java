package dao;

import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.json.simple.JSONObject;

import utils.Conectar;
import controller.Paciente;

/**
 *
 * @author Mateus Couto
 */
public class PacienteDAO {
    // CRUD
    public void register(Paciente p) {
        Connection con = Conectar.getConectar();
        JSONObject objetoJson = new JSONObject();
        String sql = "INSERT INTO paciente (nome, cpf, email, nascimento, sexo, telefone, tipo_medico) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, p.getNome());
            stm.setString(2, p.getCpf());
            stm.setString(3, p.getEmail());
            stm.setString(4, p.getNascimento());
            stm.setString(5, p.getSexo());  
            stm.setString(6, p.getTelefone());
            stm.setString(7, p.getTipo_medico());
            stm.executeUpdate();
            stm.close();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error ao Cadastrar!");
        }
    }

    public void update(Paciente p) {
        Connection con = Conectar.getConectar();
        String sql = "UPDATE paciente SET nome = ?, cpf = ?, email = ?, nascimento = ?, telefone = ?, sexo = ? WHERE id_paciente = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, p.getNome());
            stm.setString(2, p.getCpf());
            stm.setString(3, p.getEmail());
            stm.setString(4, p.getNascimento());
            stm.setString(5, p.getSexo());
            stm.setString(6, p.getTelefone());
            stm.setInt(7, p.getId_paciente());
            stm.close();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o registro!");
        }
    }

    public void delete(Paciente p) {
        Connection con = Conectar.getConectar();

        String sql = "DELETAR FROM paciente WHERE id_paciente = ? ";
        int option = JOptionPane.showConfirmDialog(null, "Deseja excluir o paciente "
                + p.getNome() + "?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, p.getId_paciente());
                stm.close();
                JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir o registro!");
            }
        }
    }

    public List<Paciente> listarTodos() {
        Connection con = Conectar.getConectar();
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM paciente ORDER by id_paciente";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet resultado = stm.executeQuery();
            while (resultado.next()) {
                Paciente p = new Paciente();
                p.setId_paciente(resultado.getInt("id_paciente"));
                p.setNome(resultado.getString("nome"));
                p.setCpf(resultado.getString("cpf"));
                p.setEmail(resultado.getString("email"));
                p.setNascimento(resultado.getString("nascimento"));
                p.setTelefone(resultado.getString("telefone"));
                p.setSexo(resultado.getString("sexo"));
                p.setTipo_medico(resultado.getString("tipo_medico"));
                lista.add(p);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error ao buscar os registros");
        }
        return lista;
    }
}
