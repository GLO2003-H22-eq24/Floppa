# Floppa

Le meilleur site de vente anonyme au Québec!

## Requis

- Java 11
- Maven

## Setup

### Compilation

```
mvn clean install
```

### Exécution
```
mvn exec:java
```

#### Comment utiliser
- Aller sur l'adresse "http://localhost:8080/"

## Tests d'intégration (TestIT)

```
mvn verify -PTestIT
```


##Wiki

- POJO -> json & json -> POJO ==> https://eclipse-ee4j.github.io/jsonb-api/docs/user-guide.html
- injecter des dépendances au constructeur des resources https://riptutorial.com/jersey/example/23632/basic-dependency-injection-using-jersey-s-hk2
##Archi
- separer par topic du domaine d'affaire -> chaque topic a son "api", son "domaine", sa "persistance" 