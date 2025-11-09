# 물듬 사용자 서비스

## 개요
- 구글 OAuth 로그인을 통해 사용자 인증을 수행하고, JWT 발급·재발급과 로그아웃을 담당하는 사용자 전용 백엔드입니다.
- 사용자 활동/관리 로그 API와 관리자용 조회 기능을 포함하며, 사용자·팀·멤버 테이블을 그대로 활용해 팀 정보를 관리합니다.

## 주요 기능
- `POST /auth/google/login`: 구글 인증 코드로 사용자 정보를 확인 후 Access/Refresh Token 발급.
- `POST /auth/token/refresh`: 만료된 Access Token을 Refresh Token으로 재발급.
- `POST /auth/logout`: Refresh Token 제거와 함께 로그아웃 처리.
- `GET /sup/user/logs`: 운영/슈퍼관리자를 위한 사용자 로그 조회.

## RabbitMQ 연동
- 로그인 성공 시 `UserEventPublisher`가 `USER_LOGIN` 이벤트를 RabbitMQ로 발행해 다른 마이크로서비스가 사용자 활동을 구독할 수 있도록 했습니다.
- 구성
  - Exchange: `user.event.exchange`
  - Queue: `user.event.queue`
  - Routing Key: `user.event`
- 메시지 포맷(`UserEventMessage`): `userId`, `email`, `eventType`, `occurredAt`.
- 연결 설정은 `spring.rabbitmq.*` 프로퍼티와 `.env` 값으로 제어합니다.

## 로컬 실행 절차
1. `.env` 파일에 필수 값을 채워 넣습니다. (예시는 `.env` 참고)
2. 의존 서비스 기동:
   ```shell
   docker compose up -d postgres rabbitmq
   ```
3. 애플리케이션 실행:
   ```shell
   ./gradlew bootRun
   ```
4. Swagger UI: `http://localhost:8080/swagger-ui.html`

## 환경 변수
| Key | 설명 | 기본값 |
| --- | --- | --- |
| `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `DB_PASSWORD` | PostgreSQL 연결 정보 | `localhost`, `5432`, `muldum_db`, `muldum_db_user`, `muldum_db_password` |
| `RABBITMQ_HOST`, `RABBITMQ_PORT`, `RABBITMQ_USERNAME`, `RABBITMQ_PASSWORD` | RabbitMQ 접속 정보 | `localhost`, `5672`, `guest`, `guest` |
| `GOOGLE_*` | 구글 OAuth Client 설정 | 빈 값 |
| `JWT_SECRET_KEY`, `JWT_ACCESS_TOKEN_EXPIRATION`, `JWT_REFRESH_TOKEN_EXPIRATION` | JWT 서명 및 만료시간(ms) | 빈 값 |

## 테스트
```shell
./gradlew test
```
> 현재 테스트는 기존 코드의 미정의 예외 클래스 때문에 실패합니다. RabbitMQ 연동 변경 사항은 컴파일 타임 기준으로 검증되었습니다.
