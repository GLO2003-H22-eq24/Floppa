# TP4
# Planification du travail sur Github


- **1** pour le ***Project*** comprenant les colonnes et les issues associées
- **1** pour le ***milestone*** comprenant le titre, la description et les issues associées
- **3** pour les **issues** avec tous les éléments demandés visibles
- **3** pour les **PR** avec tous les éléments demandés visibles
- **1** pour votre **arbre de commits et de branches** (au moins 3 branches et/ou 10 commits visibles)

**Vos captures d'écran doivent être cohérentes et prise à un moment fixe de votre développement**.


# Rétrospective finale

1. Décrivez 2 problématiques que possèdent votre processus et développer 2 plans distincts afin de les résoudres. Soyez constructifs dans vos critiques et évitez de mettre la faute sur une ou un groupe de personne en particulier.
<br></br>
2. Décrivez la démarche que vous aviez entrepris afin d'intégrer de nouveaux outils technologiques. Quelles étaient les étapes du processus? Comment avez-vous réagis aux différents bogues? Exploriez-vous à l'aide de tests unitaires ou manuels? Qu'avez-vous appris suite à cette démarche?
<br></br>
3. Quels sont les bons coups de votre équipe? De quelles parties êtes-vous fiers? Nommez-en 3.
    - Beaucoup de communication
    - On bon travail au niveau de la rigueur dans git
    - le respect des standards établis dans le contract d'équipe ainsi qu'au niveau de la programmtion
    <br></br>

4. Quel conseil donneriez-vous aux prochains étudiants qui doivent faire ce projet?

    - S'assurer d'établir une bonne architecture dès le départ. Cela va éviter beaucoup de problèmes d'intégration plus tard. Avoir une bonne fondation va aussi permettre aux étudiants de se retrouver facilement dans leur code, et leur donner la possibilité de "Keep It Stupid Simple".
    <br></br>
    
5. Quels apprentissages, trucs ou techniques appris dans ce projet croyez-vous pouvoir utiliser plus tard? Décrivez-en au moins 2. Cela peut être des apprentissages techniques, pratiques, sur le travail d'équipe ou encore par rapport au processus.

    - Avoir une gestion de Git demande beaucoup d'effort, mais ca en vaut la peine. 
    - aaaaa
    <br></br>

# Open source

## Préparation

1. Une compagnie qui contribue à des projet open source obtient plusieurs bénéfices. Tout d'abord, si le projet est créé par la compagnie, les utilisateurs du projet seront en mesure d'aider à régler un bug lorsqu'ils en trouvent un.
Cela fait en sorte qu'il y aura moins d'effort nécessaires pour l'équipe de maintenance afin de garder le projet stable. Cela bénéficie à tous, car il y aura moins de problème avec le projet,
donc les utilisateurs seront plus satisfait avec le projet. Également, rendre le projet open source permet une plus grande adoption, ce qui peut être bénéfique pour l'entreprise, car cela amène
un nouvelle intérêt envers l'entreprise qui n'aurait pas été présent si le projet aurait été closed source. Dans le cas où le projet ne provient pas de l'entreprise, il est tout de même à son 
avantage de contribuer à ce type de projet, car elle pourrait simplement engager quelqu'un afin de faire des modifications à un projet qui existe déjà afin que celui-ci réponde plus aux besoins 
de l'entreprise au lieu de créer un nouveau projet closed source qui prendrait beaucoup plus de temps afin d'obtenir le même résultat.

2. Il est important de créer une bonne documentation afin que peu importe ce qu'une personne cherche, elle est en mesure de le trouver dans la documentation. Par exemple, il est important 
d'avoir un README afin que de nouveaux utilisateurs soient en mesure de savoir comment utiliser le projet. Un projet open source peut devenir trop grand pour l'équipe en place, cela peut 
causer plusieurs problèmes, car l'équipe ne serait pas en mesure de répondre à la quantité de demandes qui ne feraiewnt qu'augmenter au point de perdre le contrôle du projet. Il peut également
être difficile de gérer la partie légale d'un projet open source. Autant les dépendances que d'autres aspects du projet comme son nom doivent être grandment vérifier afin qu'il n'y est pas de
problèmes légaux liés à ceux-ci. Il faut également protéger le projet, mais cela ce fait bien plus facilement à l'aide d'une licence.

3. Nous avons été particulièrement surpris par la capacité que n'importe qui peut contribuer à un projet open source, peu importe leurs connaisances. Quelqu'un 
qui aime écrire peut aider un projet en écrivant de la documentation, quelqu'un qui aime la conception peut aider un projet avec sa conception afin de rendre le projet plus facile à utiliser,
quelqu'un qui aime planifier des événements peut aider en organsisant une conférence pour le projet afin qu'il soit plus connu, etc.

## Choix de la license: MIT
- Raison 1
- Raison 2
- Raison 3

## Exécution

Maintenant que vous êtes mieux renseignés sur l'open source, vous savez qu'il vous faut mettre en place des pratiques permettant de partager votre vision et l'information requise pour contribuer au projet. Ainsi, vous devez :

1. Créer un fichier pour le "Code of conduct" en suivant les meilleures pratique. Si vous utilisez un template, vous **devez en citer la source** et **expliquer pourquoi** vous l'avez utilisé.
2. Créer un fichier pour la licence en suivant les meilleures pratiques. Indiquez les **3 raisons principales** (dans le fichier d'exercice) pour lesquelles vous avez choisi cette licence par rapport aux autres.
3. Créer un fichier pour la contribution décrivant les meilleures pratiques de collaboration et de développement que vous voulez encourager au sein du projet. Si vous utilisez un template, vous **devez en citer la source** et **expliquer pourquoi** vous l'avez utilisé.
4. Mettre à jour le README afin de décrire le projet, mettre en lien les différents fichiers pour l'open source, et **ajouter les badges pour les pipelines CI** (voir la documentation sur Github).

# Outils de métriques

## Outil d'analyse de la qualité du code: **SonarCloud**
![img.png](Screenshots/TP4/screen1.png)
![img.png](Screenshots/TP4/screen2.png)

## outil de détection de failles de sécurité: **CodeQL**
![img.png](Screenshots/TP4/CodeQLscreen1.png)
![img.png](Screenshots/TP4/CodeQLscreen2.png)

## outil de mesure du test coverage: **Code coverage**
![img.png](Screenshots/TP4/screen1.png)
![img.png](Screenshots/TP4/screen2.png)

# Story: Statistique 

##  Description
En tant que vendeur, je veux pouvoir ajouter une vue sur un produit afin de signaler l'intérêt pour ce produit ainsi que de visualiser cet intérêt.

##  Critères de succès
1. On peut incrémenter le nombre de vue pour un produit.
2. On peut visualiser le nombre de vue par produit. 
3. Le vendeur courant peut visualiser ses vues.

##  Détails techniques

### Requête ajouter view

#### *Route*
```
POST /products/{productId}/views
```

### Réponse status

- <code> 200 OK </code>

### Exceptions
 
- <code> ITEM_NOT_FOUND </code> si le produit n'existe pas.
- <code> MISSING_PARAMETER </code> si product ID est manquant (<code> null </code>).

### Requête voir views

#### *Route*
```
GET /products/@me 
```
 *Headers*
- <code> X-Seller-Id </code>: <code> String </code>
    - ID du vendeur

### Payload 
```javascript
[
    {
      productId: string,
      views: int,
    }
]
```

### Exemple de payload valide
```javascript
[
    {
        "productId": "7f12e673-4250-2177-91ec-7a675042607c",
        "views": 7
    },
    {
        "productId": "6f00e6b3-4250-4177-91ec-8b675042607c",
        "views": 8
    },

]
``` 

### Réponse status

- <code> 200 OK </code>

### Exceptions
 
- <code> ITEM_NOT_FOUND </code> si le seller Id n'existe pas.
- <code> MISSING_PARAMETER </code> si le seller Id est manquant (<code> null </code>).



