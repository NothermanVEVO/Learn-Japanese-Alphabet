package src.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import src.GUI.Config;

public class Data {

    @SuppressWarnings("unused")
    private static Path folder_path;
    private static Path file_path;
    private final static String file_name = "user_presets";
    private final static String file_type = ".json";

    private static JSONObject user_data;

    public Data(){
        folder_path = get_folder_path();
        try {
            file_path = get_file_path();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Impossivel de achar o arquivo.", 
                "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
        user_data = open_file(file_path);
    }

    private Path get_folder_path(){
        String userHome = System.getProperty("user.home");
        String documentsPath = userHome + "/Documents/";
        Path path = Paths.get(documentsPath + "/japData/");
        try {
            if(!Files.exists(path)){
                System.out.println("Criando diretorio...");
                Path new_path = Files.createDirectories(path);
                System.out.println("Diretorio criado!");
                return new_path;
            } else{
                System.out.println("Diretorio ja existe! Retornando o caminho.");
                return path;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Impossivel de criar o diretorio.", 
                "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
            return null;
        }
    }

    private Path get_file_path() throws IOException{
        String userHome = System.getProperty("user.home");
        String documentsPath = userHome + "/Documents/";
        Path path = Paths.get(documentsPath + "/japData/" + file_name + file_type);
        if(!Files.exists(path)){
            create_file(path, create_user_data_json());
        } else{
            System.out.println("Arquivo ja existe! Retornando o caminho.");
        }
        return path;
    }

    private static void create_file(Path path, JSONObject user_data){
        try {
            System.out.println("Criando arquivo...");
            Files.writeString(path, user_data.toJSONString());
            System.err.println("Arquivo criado");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Impossivel de criar o arquivo.", 
                "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

    private static JSONObject open_file(Path path){
        try {
            System.out.println("Abrindo arquivo...");
            JSONParser json_parser = new JSONParser();
            Object object = json_parser.parse(Files.readString(path));
            System.out.println("Arquivo aberto!");
            return (JSONObject) object;
        } catch (IOException | ParseException e) {
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static JSONObject create_user_data_json(){
        JSONObject user_preset = new JSONObject();

        JSONArray alphabets = new JSONArray();
        JSONObject hiragana = new JSONObject();
        hiragana.put("is_hiragana_selected", false);
        hiragana.put("is_A_selected", false);
        hiragana.put("is_E_selected", false);
        hiragana.put("is_I_selected", false);
        hiragana.put("is_O_selected", false);
        hiragana.put("is_U_selected", false);
        alphabets.add(hiragana);

        JSONObject katakana = new JSONObject();
        katakana.put("is_katakana_selected", false);
        katakana.put("is_A_selected", false);
        katakana.put("is_E_selected", false);
        katakana.put("is_I_selected", false);
        katakana.put("is_O_selected", false);
        katakana.put("is_U_selected", false);
        alphabets.add(katakana);
        user_preset.put("Alphabets", alphabets);

        JSONObject mode = new JSONObject();
        mode.put("is_japanese_to_syllable", false);
        mode.put("is_syllable_to_japanese", false);
        user_preset.put("Mode", mode);

        JSONObject study = new JSONObject();
        study.put("is_elimination", false);
        study.put("is_infinite", false);
        user_preset.put("Study", study);

        return user_preset;
    }

    @SuppressWarnings("unchecked")
    public static void save_file(){
        System.out.println("Salvando arquivo...");
        JSONObject user_preset = new JSONObject();

        JSONArray alphabets = new JSONArray();
        JSONObject hiragana = new JSONObject();
        hiragana.put("is_hiragana_selected", Config.is_hiragana_selected());
        hiragana.put("is_A_selected", Config.is_hiragana_A_selected());
        hiragana.put("is_E_selected", Config.is_hiragana_E_selected());
        hiragana.put("is_I_selected", Config.is_hiragana_I_selected());
        hiragana.put("is_O_selected", Config.is_hiragana_O_selected());
        hiragana.put("is_U_selected", Config.is_hiragana_U_selected());
        alphabets.add(hiragana);

        JSONObject katakana = new JSONObject();
        katakana.put("is_katakana_selected", Config.is_katakana_selected());
        katakana.put("is_A_selected", Config.is_katakana_A_selected());
        katakana.put("is_E_selected", Config.is_katakana_E_selected());
        katakana.put("is_I_selected", Config.is_katakana_I_selected());
        katakana.put("is_O_selected", Config.is_katakana_O_selected());
        katakana.put("is_U_selected", Config.is_katakana_U_selected());
        alphabets.add(katakana);
        user_preset.put("Alphabets", alphabets);

        JSONObject mode = new JSONObject();
        mode.put("is_japanese_to_syllable", Config.is_japanese_to_syllable_selected());
        mode.put("is_syllable_to_japanese", Config.is_syllable_to_japanese_selected());
        user_preset.put("Mode", mode);

        JSONObject study = new JSONObject();
        study.put("is_elimination", Config.is_elimination_selected());
        study.put("is_infinite", Config.is_infinite_selected());
        user_preset.put("Study", study);

        create_file(file_path, user_preset);
        System.out.println("Arquivo salvo!");
    }

    public static void load_file(){
        System.out.println("Carregando arquivo...");
        JSONObject mode = (JSONObject) user_data.get("Mode");
        if((boolean) mode.get("is_japanese_to_syllable")){
            Config.set_japanese_to_syllable_selected();
        } else if((boolean) mode.get("is_syllable_to_japanese")){
            Config.set_syllable_to_japanese_selected();
        }

        JSONObject study = (JSONObject) user_data.get("Study");
        if((boolean) study.get("is_elimination")){
            Config.set_elimination_selected();
        } else if((boolean) study.get("is_infinite")){
            Config.set_infinite_selected();
        }

        JSONArray alphabets = (JSONArray) user_data.get("Alphabets");
        for(int i = 0; i < alphabets.size(); i++){
            JSONObject alphabet = (JSONObject) alphabets.get(i);
            if(alphabet.containsKey("is_hiragana_selected")){
                Config.set_hiragana_selected((boolean) alphabet.get("is_hiragana_selected"));
                Config.set_hiragana_A_selected((boolean) alphabet.get("is_A_selected"));
                Config.set_hiragana_E_selected((boolean) alphabet.get("is_E_selected"));
                Config.set_hiragana_I_selected((boolean) alphabet.get("is_I_selected"));
                Config.set_hiragana_O_selected((boolean) alphabet.get("is_O_selected"));
                Config.set_hiragana_U_selected((boolean) alphabet.get("is_U_selected"));
            } else if(alphabet.containsKey("is_katakana_selected")){
                    Config.set_katakana_selected((boolean) alphabet.get("is_katakana_selected"));
                    Config.set_katakana_A_selected((boolean) alphabet.get("is_A_selected"));
                    Config.set_katakana_E_selected((boolean) alphabet.get("is_E_selected"));
                    Config.set_katakana_I_selected((boolean) alphabet.get("is_I_selected"));
                    Config.set_katakana_O_selected((boolean) alphabet.get("is_O_selected"));
                    Config.set_katakana_U_selected((boolean) alphabet.get("is_U_selected"));
            }
        }
        System.out.println("Arquivo carregado!");
    }

}
