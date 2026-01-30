package com.ll.wiseSaying;

import java.util.ArrayList;

public class WiseSayingService {

    private final WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();

    public WiseSaying write(String content, String author) {
        return wiseSayingRepository.save(content, author); // <--- 그대로 전달
    }

    public ArrayList<WiseSaying> list() {
        return wiseSayingRepository.getWiseSayingArrayList();
    }

    public boolean delete(int id) {
        return wiseSayingRepository.deleteById(id);
    }

    public WiseSaying findById(int id){
        return wiseSayingRepository.findById(id);
    }

    public void modify(WiseSaying wiseSaying, String content, String author){
        wiseSayingRepository.modify(wiseSaying, content, author);
    }

    public void build() {
        wiseSayingRepository.build();
    }
}
