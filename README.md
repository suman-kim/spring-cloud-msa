# 🚀 Spring Cloud MSA (Microservices Architecture)

<div align="center">

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.8-6DB33F?style=for-the-badge&logo=spring-boot)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2023.0.3-6DB33F?style=for-the-badge&logo=spring)
![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk)
![Kafka](https://img.shields.io/badge/Apache%20Kafka-3.8.0-231F20?style=for-the-badge&logo=apache-kafka)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

**Spring Cloud 기반의 마이크로서비스 아키텍처 학습 및 실습 프로젝트**

</div>

---

## 📋 목차

- [프로젝트 개요](#-프로젝트-개요)
- [시스템 아키텍처](#-시스템-아키텍처)
- [서비스 구성](#-서비스-구성)
- [기술 스택](#-기술-스택)
- [시작하기](#-시작하기)
- [API 엔드포인트](#-api-엔드포인트)
- [설정 정보](#-설정-정보)

---

## 📖 프로젝트 개요

이 프로젝트는 **Spring Cloud**를 활용한 마이크로서비스 아키텍처(MSA) 학습 및 실습을 위한 프로젝트입니다.

### 주요 학습 내용
- ✅ Service Discovery (Eureka)
- ✅ API Gateway (Spring Cloud Gateway)
- ✅ Configuration Management (Spring Cloud Config)
- ✅ 서비스 간 통신 (Feign Client, Kafka)
- ✅ 분산 추적 (Zipkin, Micrometer)
- ✅ Circuit Breaker (Resilience4J)
- ✅ Message Queue (RabbitMQ, Kafka)

---

## 🏗 시스템 아키텍처

```
                                    ┌─────────────────┐
                                    │  Config Server  │
                                    │    (Port 8888)  │
                                    └────────┬────────┘
                                             │
                    ┌────────────────────────┼────────────────────────┐
                    │                        │                        │
                    ▼                        ▼                        ▼
┌─────────┐   ┌──────────┐   ┌─────────────────────────────────────────────┐
│ Client  │──▶│  Gateway │──▶│           Eureka Discovery Server          │
│         │   │(Port 8000)│   │              (Port 8761)                   │
└─────────┘   └──────────┘   └─────────────────────────────────────────────┘
                    │                        │
                    │         ┌──────────────┼──────────────┐
                    │         │              │              │
                    ▼         ▼              ▼              ▼
              ┌───────────┐ ┌───────────┐ ┌───────────┐
              │   User    │ │  Catalog  │ │   Order   │
              │  Service  │ │  Service  │ │  Service  │
              │(Random)   │ │(Random)   │ │(Random)   │
              └─────┬─────┘ └─────┬─────┘ └─────┬─────┘
                    │             │             │
                    └─────────────┼─────────────┘
                                  │
                    ┌─────────────┼─────────────┐
                    │             │             │
                    ▼             ▼             ▼
              ┌───────────┐ ┌───────────┐ ┌───────────┐
              │   MySQL   │ │  RabbitMQ │ │   Kafka   │
              │           │ │(Port 5672)│ │(Port 9092)│
              └───────────┘ └───────────┘ └───────────┘
                                  │
                                  ▼
                          ┌───────────┐
                          │  Zipkin   │
                          │(Port 9411)│
                          └───────────┘
```

---

## 🔧 서비스 구성

| 서비스명 | 포트 | 설명 |
|---------|------|------|
| **Discovery Service** | `8761` | Eureka Server - 서비스 레지스트리 |
| **Config Service** | `8888` | Spring Cloud Config Server - 중앙 설정 관리 |
| **Gateway Service** | `8000` | Spring Cloud Gateway - API 게이트웨이 |
| **User Service** | `Random` | 사용자 관리 서비스 |
| **Order Service** | `Random` | 주문 관리 서비스 |
| **Catalog Service** | `Random` | 상품 카탈로그 서비스 |
| **First Service** | `Random` | 테스트용 서비스 1 |
| **Second Service** | `Random` | 테스트용 서비스 2 |

> 💡 **Random Port**: 각 비즈니스 서비스는 동적 포트를 사용하여 다중 인스턴스 실행이 가능합니다.

---

## 🛠 기술 스택

### Backend
| 기술 | 버전 | 설명 |
|-----|------|------|
| Java | 17 | 프로그래밍 언어 |
| Spring Boot | 3.2.8 | 애플리케이션 프레임워크 |
| Spring Cloud | 2023.0.3 | 마이크로서비스 프레임워크 |
| Spring Data JPA | - | ORM |
| Spring Security | - | 보안 |

### Spring Cloud Components
| 컴포넌트 | 설명 |
|---------|------|
| Netflix Eureka | 서비스 디스커버리 |
| Spring Cloud Gateway | API 게이트웨이 |
| Spring Cloud Config | 중앙 설정 관리 |
| Spring Cloud Bus | 설정 변경 전파 (AMQP) |
| OpenFeign | 선언적 REST 클라이언트 |
| Resilience4J | Circuit Breaker |
| Micrometer + Zipkin | 분산 추적 |

### Message Queue & Database
| 기술 | 버전 | 용도 |
|-----|------|------|
| Apache Kafka | 3.8.0 | 이벤트 스트리밍 |
| Confluent Platform | 7.3.1 | Kafka 생태계 |
| RabbitMQ | - | 메시지 브로커 (Config Bus) |
| MySQL | 8.0 | 관계형 데이터베이스 |

### Monitoring & Tracing
| 기술 | 설명 |
|-----|------|
| Zipkin | 분산 추적 시스템 |
| Spring Boot Actuator | 애플리케이션 모니터링 |

---

## 🚀 시작하기

### 사전 요구사항

- **JDK 17** 이상
- **MySQL 8.0**
- **RabbitMQ**
- **Apache Kafka** (선택)
- **Zipkin** (선택)

### 1. 인프라 서비스 실행

#### MySQL
```bash
# MySQL 실행 (Docker 사용 시)
docker run -d --name mysql \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=micro_service \
  -p 3306:3306 \
  mysql:8.0
```

#### RabbitMQ
```bash
# RabbitMQ 실행 (Docker 사용 시)
docker run -d --name rabbitmq \
  -p 5672:5672 \
  -p 15672:15672 \
  rabbitmq:3-management
```

#### Zipkin
```bash
# Zipkin 실행
java -jar zipkin.jar
# 또는 Docker 사용
docker run -d --name zipkin -p 9411:9411 openzipkin/zipkin
```

#### Kafka (선택)
```bash
# Zookeeper 실행
cd kafka_2.13-3.8.0
bin/zookeeper-server-start.sh config/zookeeper.properties

# Kafka 실행
bin/kafka-server-start.sh config/server.properties
```

### 2. Spring Cloud 서비스 실행 순서

> ⚠️ **중요**: 서비스 실행 순서를 반드시 지켜주세요!

```bash
# 1️⃣ Config Service (설정 서버)
cd config-service
./gradlew bootRun

# 2️⃣ Discovery Service (Eureka Server)
cd discoveryService
./gradlew bootRun

# 3️⃣ Gateway Service (API Gateway)
cd gatewayService
./gradlew bootRun

# 4️⃣ Business Services (순서 무관)
cd userService && ./gradlew bootRun
cd order-service && ./gradlew bootRun
cd catalog-service && ./gradlew bootRun
```

### 3. 서비스 확인

- **Eureka Dashboard**: http://localhost:8761
- **Zipkin Dashboard**: http://localhost:9411
- **RabbitMQ Management**: http://localhost:15672 (guest/guest)

---

## 📡 API 엔드포인트

모든 API는 Gateway(`http://localhost:8000`)를 통해 접근합니다.

### User Service
| Method | Endpoint | 설명 |
|--------|----------|------|
| GET | `/user-service/users` | 전체 사용자 조회 |
| GET | `/user-service/users/{userId}` | 특정 사용자 조회 |
| POST | `/user-service/users` | 사용자 생성 |

### Catalog Service
| Method | Endpoint | 설명 |
|--------|----------|------|
| GET | `/catalog-service/catalogs` | 전체 상품 조회 |

### Order Service
| Method | Endpoint | 설명 |
|--------|----------|------|
| GET | `/order-service/orders` | 전체 주문 조회 |
| GET | `/order-service/{userId}/orders` | 사용자별 주문 조회 |
| POST | `/order-service/{userId}/orders` | 주문 생성 |

---

## ⚙️ 설정 정보

### Config Server 설정

Config Server는 두 가지 모드를 지원합니다:

#### 1. Native (로컬 파일 시스템)
```yaml
spring:
  profiles:
    active: native
  cloud:
    config:
      server:
        native:
          search-locations: file:///path/to/config/files
```

#### 2. Git (원격 저장소)
```yaml
spring:
  profiles:
    active: git
  cloud:
    config:
      server:
        git:
          uri: https://github.com/your-repo/spring-cloud-config
          default-label: main
```

### 설정 갱신 (Spring Cloud Bus)

설정 변경 시 모든 서비스에 전파:
```bash
# 특정 서비스 갱신
curl -X POST http://localhost:8000/actuator/refresh

# 모든 서비스 갱신 (Bus Refresh)
curl -X POST http://localhost:8000/actuator/busrefresh
```

---

## 📁 프로젝트 구조

```
spring_cloud_msa/
├── 📂 config-service/          # 중앙 설정 서버
├── 📂 discoveryService/        # Eureka 서버
├── 📂 gatewayService/          # API Gateway
├── 📂 userService/             # 사용자 서비스
├── 📂 order-service/           # 주문 서비스
├── 📂 order-service_2/         # 주문 서비스 (복제본)
├── 📂 catalog-service/         # 카탈로그 서비스
├── 📂 firstService/            # 테스트 서비스 1
├── 📂 secondService/           # 테스트 서비스 2
├── 📂 kafka_2.13-3.8.0/        # Kafka 바이너리
├── 📂 confluent-7.3.1/         # Confluent Platform
├── 📂 keystore/                # 암호화 키 저장소
├── 📄 zipkin.jar               # Zipkin 서버
└── 📄 README.md
```

---

## 🔐 보안

### JWT 인증 (구현 예정)
- User Service에서 JWT 토큰 발급
- Gateway에서 토큰 검증

### 설정 암호화
- `keystore/` 폴더에 암호화 키 저장
- Spring Cloud Config의 암호화/복호화 기능 활용

---

## 📊 모니터링

### Zipkin 분산 추적
- 서비스 간 호출 추적
- 지연 시간 분석
- 에러 추적

### Spring Boot Actuator
각 서비스에서 노출되는 엔드포인트:
- `/actuator/health` - 헬스 체크
- `/actuator/beans` - 빈 목록
- `/actuator/refresh` - 설정 갱신
- `/actuator/busrefresh` - Bus 설정 갱신

---

## 🔄 서비스 간 통신

### 동기 통신 (Feign Client)
```java
@FeignClient(name = "ORDER-SERVICE")
public interface OrderServiceClient {
    @GetMapping("/{userId}/orders")
    List<ResponseOrder> getOrders(@PathVariable String userId);
}
```

### 비동기 통신 (Kafka)
- **Producer**: Order Service → Kafka Topic
- **Consumer**: Catalog Service ← Kafka Topic

### Circuit Breaker (Resilience4J)
- 서비스 장애 시 Fallback 처리
- 장애 전파 방지

---

## 📝 참고 자료

- [Spring Cloud Documentation](https://spring.io/projects/spring-cloud)
- [Netflix Eureka](https://github.com/Netflix/eureka)
- [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway)
- [Apache Kafka](https://kafka.apache.org/)
- [Zipkin](https://zipkin.io/)

---

## 📜 라이선스

이 프로젝트는 학습 목적으로 제작되었습니다.

---

<div align="center">

**Made with ❤️ using Spring Cloud**

</div>

