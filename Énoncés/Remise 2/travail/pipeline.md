# Pipeline automatisé

Afin d'augmenter la confiance de vos intégrations logicielles, l'équipe vous demande de mettre en place un pipeline de tests automatisés (CI pipeline). Pour ce faire, vous devrez utiliser Github Actions, qui est intégré à Github. Vous devez avoir au moins les étapes séparées et explicites suivantes :

1. Vérification du formatage (étape `checkstyle` de Maven)
2. Compilation (étape `compile` de Maven) et tests (étapes `test` et `integration-test` ou `verify` de Maven)

Chacune de ces étapes **doit** faire échouer le pipeline si celle-ci échoue. Ex: s'il y a une erreur de formatage, le pipeline doit échouer.

Vous devez également satisfaire aux exigences suivantes :

1. Utilisation de la cache pour les dépendances
2. Bloquer toute pull-request avec un pipeline qui échoue (configurable dans Github)
3. Exécution du pipeline sur toutes les pull requests, ainsi que sur tous les commits de chaque branche principale (ex: `dev`, `master`, etc.)

Il vous est fortement suggéré de lire la [documentation](https://docs.github.com/en/actions/using-workflows/workflow-syntax-for-github-actions) de Github Actions.

## IMPORTANT

Vous disposez d'une limite de 2000 minutes d'exécution par mois par équipe pour le pipeline CI. Afin d'éviter de dépasser les minutes, vous pouvez configurer une limite de temps par exécution. Normallement, une exécution ne devrait jamais dépasser 5min (dans votre cas).

Pour plus de détails : https://docs.github.com/en/actions/using-workflows/workflow-syntax-for-github-actions#jobsjob_idstrategy
