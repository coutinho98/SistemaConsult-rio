package model;

import controller.Paciente;
import org.json.simple.JSONObject;

/**
 *
 * @author Mateus Couto
 */
public class JsonUtils {

    public static String getJson(Paciente p) {
        JSONObject objetoJson = new JSONObject();
        objetoJson.put("nome", p.getNome());
        objetoJson.put("cpf", p.getCpf());
        objetoJson.put("email", p.getEmail());
        objetoJson.put("sexo", p.getSexo());
        objetoJson.put("telefone", p.getTelefone());
        objetoJson.put("nascimento", p.getNascimento());
        objetoJson.put("tipo_medico", p.getTipo_medico());
        return objetoJson.toJSONString();
    }

    public static Paciente getPaciente(String json) {

    }
}
