##Badges
### Quality
- [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=GLO2003-H22-eq24_Floppa&metric=bugs)](https://sonarcloud.io/summary/new_code?id=GLO2003-H22-eq24_Floppa)
- [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=GLO2003-H22-eq24_Floppa&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=GLO2003-H22-eq24_Floppa)
- [![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=GLO2003-H22-eq24_Floppa&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=GLO2003-H22-eq24_Floppa)
- [![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=GLO2003-H22-eq24_Floppa&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=GLO2003-H22-eq24_Floppa)
### Tests
- [![tests](https://github.com/GLO2003-H22-eq24/Floppa/actions/workflows/.github-actions.yml/badge.svg)](https://github.com/GLO2003-H22-eq24/Floppa/actions/workflows/.github-actions.yml)
- [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=GLO2003-H22-eq24_Floppa&metric=coverage)](https://sonarcloud.io/summary/new_code?id=GLO2003-H22-eq24_Floppa)
### Security
- [![CodeQL](https://github.com/GLO2003-H22-eq24/Floppa/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/GLO2003-H22-eq24/Floppa/actions/workflows/codeql-analysis.yml)
### Deploys
- [![Deploy Staging](https://github.com/GLO2003-H22-eq24/Floppa/actions/workflows/deploy-staging.yml/badge.svg)](https://github.com/GLO2003-H22-eq24/Floppa/actions/workflows/deploy-staging.yml)
- [![Sonar cloud analyse CI](https://github.com/GLO2003-H22-eq24/Floppa/actions/workflows/sonarCloud.yml/badge.svg)](https://github.com/GLO2003-H22-eq24/Floppa/actions/workflows/sonarCloud.yml)
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
Favorise les **Custom Args**. 
Si échec de lectures d'un arg, alors utilisations des **Variables système**. 
Sinon, utilisation du **Default**.
#### Default
```
mvn exec:java
```
- Will run on port 8080
- Use in memory persistence
#### Variables système
```
mvn exec:java
```
- Changer le port via $PORT.
- Changer le nom de la DB via $FLOPPA_DB_NAME.
- Changer l'url de la DB via $FLOPPA_DB_URL.
#### Custom Args
```
mvn exec:java -Dport={int} -DdbName={string} -DdbUrl={string}
```
- Changer le port via -Dport.
- Changer le nom de la DB via -DdbName.
- Changer l'url de la DB via -DdbUrl.
##### Exemple custom args
```
mvn clean install exec:java "-DdbName=floppa-staging" "-Dport=8080" "-DdbUrl=mongodb+srv://floppa-api:XxIDt04RxHTps0YZ@floppa.3oieg.mongodb.net/Floppa?retryWrites=true&w=majority"
```
#### Comment utiliser
- Aller sur l'adresse "http://localhost:8080/" 

## Mongo
### DB Urls
- ATLAS_CONNECTION_URL = "mongodb+srv://floppa-api:
  XxIDt04RxHTps0YZ@floppa.3oieg.mongodb.net/Floppa?retryWrites=true&w=majority";
- LOCAL_CONNECTION_URL = "mongodb://localhost";

### DB names

- PRODUCTION; **floppa-production**
- STAGING; **floppa-staging**
- LOCAL; **floppa-dev**

## Tests d'intégration (TestIT)
```
mvn verify -PTestIT
```

## Outils de vérification
Utilise SonarCloud via la commande
```
mvn verify sonar:sonar -Pcoverage "-Dsonar.host.url=https://sonarcloud.io" "-Dsonar.organization=glo2003-h22-eq24" "-Dsonar.projectKey=GLO2003-H22-eq24_Floppa"
```