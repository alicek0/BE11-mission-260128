package com.ll.wiseSaying;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WiseSayingRepository {
    private final ArrayList<WiseSaying> wiseSayings;
    private static int lastId;
    private final String DB_PATH = "db/wiseSaying";

    public WiseSayingRepository(){
        this.wiseSayings = new ArrayList<WiseSaying>();
        lastId = 0;
        initData();
    }

    private void loadLastId(){
        try {
            Path file = Paths.get(DB_PATH + "/lastId.txt");
            if (Files.exists(file)){
                String idStr = Files.readString(file);
                lastId = Integer.parseInt(idStr.trim());
            }
            else {
                System.out.println(DB_PATH + "lastId.txt" + " file not found.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadWiseSayings(){
        File dir = new File(DB_PATH);

        File[] files = dir.listFiles((d, name) -> name.matches("\\d+\\.json"));

        if (files == null){
            System.out.println("json 파일이 존재하지 않습니다.");
            return;
        }

        for (File file : files) {
            try {
                String jsonStr = Files.readString(file.toPath());
                WiseSaying wiseSaying = jsonToWiseSaying(jsonStr);
                wiseSayings.add(wiseSaying);
            } catch (IOException e){
                System.out.println("파일 불러오는 중 에러: " + e.getMessage());
            }
        }

    }

    private WiseSaying jsonToWiseSaying(String json) {

        // Extract id, content, author with regex pattern matching

        Pattern idPattern = Pattern.compile("\"id\"\\s*:\\s*(\\d+)");
        Pattern contentPattern = Pattern.compile("\"content\"\\s*:\\s*\"(.*?)\"");
        Pattern authorPattern = Pattern.compile("\"author\"\\s*:\\s*\"(.*?)\"");

        int id = 0;
        String content = "";
        String author = "";

        Matcher idMatcher = idPattern.matcher(json);
        if (idMatcher.find()) {
            id = Integer.parseInt(idMatcher.group(1));
        }
        Matcher contentMatcher = contentPattern.matcher(json);
        if (contentMatcher.find()) {
            content = contentMatcher.group(1);
        }
        Matcher authorMatcher = authorPattern.matcher(json);
        if (authorMatcher.find()) {
            author = authorMatcher.group(1);
        }

        return new WiseSaying(id, content, author);
    }

    public void initData(){
        loadLastId();
        loadWiseSayings();
    }

    public WiseSaying save(String content, String author) {
        int id = ++lastId;

        WiseSaying wiseSaying = new WiseSaying(id, content, author);

        wiseSayings.add(wiseSaying);

        // Save to actual file
        String jsonStr = "{\n"
                + "\t" + "\"id\": " + id + ",\n"
                + "\t" + "\"content\": \"" + content + "\",\n"
                + "\t" + "\"author\": \"" + author + "\"\n"
                + "}";

        writeStringToFile(id + ".json", jsonStr);
        writeStringToFile("lastId.txt", String.valueOf(id));

        return wiseSaying;
    }

    public ArrayList<WiseSaying> getWiseSayingArrayList() {
        return wiseSayings;
    }

    public WiseSaying findById(int id){
        for (WiseSaying saying : wiseSayings){
            if (saying.getId() == id){
                return saying;
            }
        }
        return null;
    }

    public boolean deleteById(int id) {
        WiseSaying saying = findById((id));

        if (saying == null){
            return false;
        }

        wiseSayings.remove(saying);
        return true;
    }

    public void modify(WiseSaying wiseSaying, String content, String author){
        wiseSaying.setWiseSaying(content);
        wiseSaying.setAuthor(author);
    }


    // Private functions
    private void writeStringToFile(String fileName, String content){
        Path file = Paths.get(DB_PATH + "/" + fileName);
        try {
            Files.writeString(file, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("파일 저장 실패: " + e.getMessage());
        }
    }
}
