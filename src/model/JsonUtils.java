package model;

import controller.Paciente;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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

    // GAMBIARRA FUNCIONAL
    public static Paciente getPaciente(String json) {
        Paciente p = new Paciente();
        json = json.replaceAll("\"|\\{|\\}", "");
        String[] strings = json.split(",");
        p.setTelefone((strings[0].strip().split(":"))[1]);
        p.setNascimento((strings[1].strip().split(":"))[1]);
        p.setTipo_medico((strings[2].strip().split(":"))[1]);
        p.setCpf((strings[3].strip().split(":"))[1]);
        p.setNome((strings[4].strip().split(":"))[1]);
        p.setSexo((strings[5].strip().split(":"))[1]);
        p.setEmail((strings[6].strip().split(":"))[1]);
        return p;
    }
}
