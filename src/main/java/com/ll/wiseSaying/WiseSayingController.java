package com.ll.wiseSaying;

import java.util.ArrayList;
import java.util.Scanner;

public class WiseSayingController {
    private final Scanner sc;
    private final WiseSayingService wiseSayingService;

    public WiseSayingController(Scanner sc) {
        this.sc = sc;
        this.wiseSayingService = new WiseSayingService();
    }

    public void write() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        WiseSaying wiseSaying = wiseSayingService.write(content, author);

        System.out.println(wiseSaying.getId() + "번 명언이 등록되었습니다.");
    }

    public void list(){
        System.out.println("번호 / 작가 / 명언\n" +
                "----------------------");

        ArrayList<WiseSaying> wiseSayings = wiseSayingService.list();
        for (int i = wiseSayings.size()-1; i >= 0; i--){
            WiseSaying saying = wiseSayings.get(i);
            System.out.printf("%d / %s / %s\n", saying.getId(), saying.getWiseSaying(), saying.getAuthor());
        }
    }

    public void delete(int id) {
        boolean exists = wiseSayingService.delete(id);
        
        if (exists){
            System.out.println(id + "번 명언이 삭제되었습니다.");
        }
        else{
            System.out.println(id + "번 명언은 존재하지 않습니다.");
        }
    }

    public void modify(int id) {
        WiseSaying wiseSaying = wiseSayingService.findById(id);

        if (wiseSaying == null) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        System.out.println("명언(기존) : " + wiseSaying.getWiseSaying());
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.println("작가(기존) : " + wiseSaying.getAuthor());
        System.out.print("작가 : ");
        String author = sc.nextLine();

        wiseSayingService.modify(wiseSaying, content, author);
    }
}
