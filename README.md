# stock-example

[재고시스템으로 알아보는 동시성이슈](https://inf.run/C1Bh)

### 특이사항
- 테스트코드 실행 시 Stock Id의 @GeneratedValue(strategy = GenerationType.AUTO) 옵션때문에 테스트를 여러 번 실행하면 Id 값이 달라 findById가 맞지 않았다.
  - 두번째 테스트 실행 시 DB Stock Id = 2 but 테스트 코드 Stock Id = 1
  - 따라서 @GeneratedValue(strategy = GenerationType.AUTO) 를 제거했다.

## 환경 구성
### MySQL
local mysql로 대체

### Redis
1. docker pull redis
2. docker run --name myredis -d -p 6379:6379 redis
3. docker ps로 컨테이너 아이디 가져오기
4. docker exec -it ee7309a05852(컨테이너 아이디) redis-cli
