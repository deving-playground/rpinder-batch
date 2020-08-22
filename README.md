# rpinder-batch
rpinder-batch

## docker-compose
- elasticsearch 7.9.0
- kibana 7.9.0

### install & run
1. 도커 설치 (https://docs.docker.com/get-docker/)
2. 도커 컴포즈 설치(https://docs.docker.com/compose/install/)
3. 네트워크 생성 스크립트 실행 (docker/createNetwork.sh) (최초 1회 실행)
4. elasticsearch & kibana 실행(docker/docker-compose.yml)
- 커맨드 실행
```shell script
# -d : 백그라운드 실행
docker-compose -f docker/docker-compose.yml up -d
```
- intellij 실행
```shell script
 1. command + 8 (`services` 활용)
 2. `deploy` 클릭
```

