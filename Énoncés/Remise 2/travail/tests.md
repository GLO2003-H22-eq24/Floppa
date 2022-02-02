# Tests

Avec maintenant plus de contenu logique, il est temps de tester! Vous aurez donc 2 types de tests à effectuer.

## Tests unitaires

Afin d'assurer un bon fonctionnement de chaque partie du code, vous devez tester unitairement **toutes les méthodes de toutes vos classes**. Par contre, en pratique, on ne test souvent pas les éléments suivants :

- Les constructeurs (**sauf** pour les validations)
- Les classes de ressources (sont testés dans les e2e)
- Le "contexte" (`Main`, préparation et enregistrement des resources, etc.)
- Les méthodes `get` (sont testées par la bande)

Avoir de pouvoir facilement tester des comportements uniques, vous devrez créer beaucoup plus de classes que simplement celles des resources et des entités. Par exemple, pensez à créer des *factories* pour la création (avec validations) des entités, des *repositories* pour la sauvegarde et le *fetching* des entités, ou encore des *assemblers* pour la convertion des entités en réponses.

## Tests fonctionnels

Afin de vérifier que votre application répond aux exigences fonctionnelles, vous devez effectuer des tests e2e, c'est-à-dire **sans aucun mock** et **tel qu'un utilisateur l'utiliserait**.

### Tests de succès

Voici les tests de succès à effectuer :

1. Créer un vendeur
3. Ajouter un produit au vendeur
4. Obtenir le produit
5. Obtenir le vendeur (avec produit)

> Pour obtenir le produit et le vendeur, **vous devez assumer que l'id est généré aléatoirement**, et êtes donc **obligés** d'utiliser le header `Location` pour accéder à ces resources!

### Tests d'échec

Pour chaque route, vous devez tester :

- Les payloads aux champs manquants (hint : vous pouvez utiliser des `null` en Java pour mimer un champ manquant)
   - 1 seul test pour tous les champs est assez
- Les payloads aux erreurs logiques. Par exemple :
   - Age invalide
   - Élément non trouvé

### Détails additionnels

Pour chacun des tests fonctionnels, assurez-vous de vérifier ces éléments de la réponse :

- Le code de statut
- Le body
- Les headers

On vous demande de faire ces tests avec la librarie [Rest-Assured](https://github.com/rest-assured/rest-assured/wiki/Usage). Vous pouvez l'utiliser avec la librairie d'assertion [Hamcrest](https://mvnrepository.com/artifact/org.hamcrest/hamcrest-all/1.3) tel que montré dans la documentation, mais **je vous suggère fortement** d'extraire (avec `.then().extract()`) les différentes parties (status code, body, headers) afin de faire des tests séparés et clean en utilisant la librarie d'assertion de votre choix.

**IMPORTANT**

Pour que l'extraction typée (convertion JSON vers Java) fonctionne, vous devez remplacer la dépendance `moxy` pour `json-jackson` dans le fichier `pom.xml`.

```diff
<dependency>
    <groupId>org.glassfish.jersey.media</groupId>
-    <artifactId>jersey-media-moxy</artifactId>
+    <artifactId>jersey-media-json-jackson</artifactId>
</dependency>
```

## Clean code

L'ensemble de vos tests doivent respecter les meilleures pratique en matière de Clean Code. Vous devez donc vous assurer de :

- Avoir un naming représentant clairement les 3 étapes du test (arrange / act / assert)
- Avoir un contenu qui représente clairement ces 3 étapes
- Avoir un contenu clair et précis, sans trop de préparation qui viendrait bruité le test
- Découper le code en sous-fonction et sous-classes lorsque nécessaire
- Éviter les "valeurs magiques"
