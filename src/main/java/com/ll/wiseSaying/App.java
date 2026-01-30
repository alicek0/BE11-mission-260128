package com.ll.wiseSaying;

import java.util.Scanner;

public class App {
    private final Scanner sc;
    private final WiseSayingController wiseSayingController;

    public App() {
        sc = new Scanner(System.in);
        wiseSayingController = new WiseSayingController(sc);
    }

    public void run() {

        System.out.println("== 명언 앱 ==\n");

        while (true) {
            System.out.print("명령) ");
            String command = sc.nextLine().trim();

            if (command.equals("종료")){
                break;
            }
            else if (command.equals("등록")){
               wiseSayingController.write();

            }
            else if (command.equals("목록")){
                wiseSayingController.list();
            }
            else if (command.startsWith("삭제?id=")){
                String idString = command.split("=")[1];
                int id = Integer.parseInt(idString);
                wiseSayingController.delete(id);
            }
            else if (command.startsWith("수정?id=")){
                String idString = command.split("=")[1];
                int id = Integer.parseInt(idString);
                wiseSayingController.modify(id);
            }
            else if (command.equals("빌드")) {
                wiseSayingController.build();
                System.out.println("data.json 파일의 내용이 갱신되었습니다.");
            }
            else {
                System.out.println("존재하지 않는 명령입니다.");
                System.out.println("사용 가능한 명령: 등록, 목록, 종료, 삭제?id={숫자}, 수정?id={숫자}, 빌드");
            }

        }

        System.out.println("명령) 종료");
        sc.close();
    }
}