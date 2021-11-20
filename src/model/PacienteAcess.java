package model;

import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utils.Conectar;
import controller.Paciente;

/**
 *
 * @author Mateus Couto
 */
public class PacienteAcess {

    // CRUD
    public void register(Paciente p) {
        Connection con = Conectar.getConectar();
        // JSONObject objetoJson = new JSONObject();
        String sql = "INSERT INTO paciente(pacientes) VALUES( ? )";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, JsonUtils.getJson(p));
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

        String sql = "DELETE FROM paciente WHERE id = ? ";
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
        String sql = "SELECT * FROM paciente ";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String jsonString = result.getString("pacientes");

                Paciente p = JsonUtils.getPaciente(jsonString);
                p.setId_paciente(id);

                lista.add(p);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error ao buscar os registros"+ex);
        }
        return lista;
    }
}
