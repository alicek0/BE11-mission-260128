# p-14471-mission-2-260128

## 파일 설명
### Main
- App 실행되는 곳 
### App
- 초기 메뉴 출력하고 입력된 명령에 따라 `WiseSayingController`를 호출해서 명령어를 수행
- 사용 가능한 명령: `등록`, `목록`, `삭제?id={숫자}`, `수정?id={숫자}`, `종료`
### WiseSaying
- 명언 클래스 
- `id`, `content`, `author`
### WiseSayingController
`WiseSayingService` 호출해서 명령어 수행: 
- 등록 `write`
- 목록 `list`
- 삭제 `delete`
- 수정 `modify`
### WiseSayingService
WiseSayingRepository 호출
이번 앱에서는 굳이 필요없던 것 같은데 Service는 순수 비지니스 로직을 구현하는 목적

### WiseSayingRepository
- p

### 데이터베이스 파일 `db/`
- `{숫자}.json` - n번째 명언이 저장된 파일.
- `lastId.txt` - 마지막으로 등록된 ID 숫자. 다음으로 등록할 명언의 숫자를 기억하기 위해 저장됨. 
- `data.json` - 전체 명언이 저장된 파일. 
