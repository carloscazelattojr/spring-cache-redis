# WSpring Cache + Redis

Estudo sobre Spring Cache e Redis 

## Tecnologias
`Java 17` 
`Spring Boot 3`
`Spring Cache`
`Redis`
`Spring JPA`
`H2`
`Docker`

## Docker

Utilizei o redis no docker. Segue comando para baixar e instalar image, 

```
docker run --name redis -p 6379:6379 -d redis
```

Para testar o redis, utilize os comandos abaixo
```
docker exec -it redis-carlos /bin/bash
```
depois,
```
redis-cli
```
para listar dentro do redis,

```
keys *
```





