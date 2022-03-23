# Floppa

Le meilleur site de vente anonyme au Québec?

## Requis

- Java 11 dans le path de votre OS
- Maven dans le path de votre OS

## Setup

### Compilation

```
mvn clean install
```

### Exécution
#### Default
```
mvn exec:java
```
#### Args
- Changer le port via -Dport. Default = 8080
- Changer l'environnement de la BD via Denv (stg ou prod). Default = stg
```
mvn exec:java -Dport=8080 -Denv=stg
```

#### Comment utiliser
- Aller sur l'adresse "http://localhost:{port}/" 

## Tests d'intégration (TestIT)
```
mvn verify -PTestIT
```

##Archi
- separer par topic du domaine d'affaire
- chaque topic a son "api", son "domaine", sa "persistance"