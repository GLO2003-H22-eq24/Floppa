# TP4
# Planification du travail sur Github

L'ensemble de votre travail doit être documenté et planifier sur Github Project en respectant les meilleures pratiques de développement et de processus logiciel. Vous devez donc :

1. Créer un *Project* pour l'ensemble du projet. 
    
2. Créer un *milestone* pour le contenu du TP2

3. Créer des issues afin de planifier et séparer le travail. 
   
4. Créer des *Pull Requests* (PR) afin d'intégrer (*merge*) les changements. 
   
5. Décidez d'une stratégie de commits et de branches afin d'uniformiser et optimiser votre processus.


Afin de vous évaluer, **vous devez fournir ces captures d'écran** dans le fichier `exerices/tp4.md` :

- **1** pour le ***Project*** comprenant les colonnes et les issues associées
- **1** pour le ***milestone*** comprenant le titre, la description et les issues associées
- **3** pour les **issues** avec tous les éléments demandés visibles
- **3** pour les **PR** avec tous les éléments demandés visibles
- **1** pour votre **arbre de commits et de branches** (au moins 3 branches et/ou 10 commits visibles)

**Vos captures d'écran doivent être cohérentes et prise à un moment fixe de votre développement**.


# Rétrospective finale

Répondez aux questions suivantes dans le fichier `exercices/tp4.md`.

1. Décrivez 2 problématiques que possèdent votre processus et développer 2 plans distincts afin de les résoudres. Soyez constructifs dans vos critiques et évitez de mettre la faute sur une ou un groupe de personne en particulier.
2. Décrivez la démarche que vous aviez entrepris afin d'intégrer de nouveaux outils technologiques. Quelles étaient les étapes du processus? Comment avez-vous réagis aux différents bogues? Exploriez-vous à l'aide de tests unitaires ou manuels? Qu'avez-vous appris suite à cette démarche?
3. Quels sont les bons coups de votre équipe? De quelles parties êtes-vous fiers? Nommez-en 3.
4. Quel conseil donneriez-vous aux prochains étudiants qui doivent faire ce projet?
5. Quels apprentissages, trucs ou techniques appris dans ce projet croyez-vous pouvoir utiliser plus tard? Décrivez-en au moins 2. Cela peut être des apprentissages techniques, pratiques, sur le travail d'équipe ou encore par rapport au processus.

# Open source

## Préparation

L'Open Source est une pratique bien répendue dans la communauté informatique mais souvent mal comprise. Floppa désire contribuer au développement logiciel communautaire et aurait ainsi besoin de connaître les enjeux principaux de la pratique.

Pour ce faire, lisez d'abord les articles suivants et discuttez-en en équipe :

- [How to Contribute to Open Source](https://opensource.guide/how-to-contribute/)
- [Starting an Open Source Project](https://opensource.guide/starting-a-project/)
- [Best Practices for Maintainers](https://opensource.guide/best-practices/)
- [Leadership and Governance](https://opensource.guide/leadership-and-governance/)
- [Your Code of Conduct](https://opensource.guide/code-of-conduct/)
- [The Legal Side of Open Source](https://opensource.guide/legal/)

Puis, répondez aux questions suivantes dans le fichier `exercices/tp4.md` :

1. Nommez 3 avantages à contribuer à des projets open source en tant qu'entreprise et justifiez en quoi cela peut être bénéfique pour tous.
2. Décrivez 3 défis qu'impose la mise en place d'un projet open source et justifiez.
3. Quelle information vous a-t-elle le plus surprise à propos de l'open source?

## Exécution

Maintenant que vous êtes mieux renseignés sur l'open source, vous savez qu'il vous faut mettre en place des pratiques permettant de partager votre vision et l'information requise pour contribuer au projet. Ainsi, vous devez :

1. Créer un fichier pour le "Code of conduct" en suivant les meilleures pratique. Si vous utilisez un template, vous **devez en citer la source** et **expliquer pourquoi** vous l'avez utilisé.
2. Créer un fichier pour la licence en suivant les meilleures pratiques. Indiquez les **3 raisons principales** (dans le fichier d'exercice) pour lesquelles vous avez choisi cette licence par rapport aux autres.
3. Créer un fichier pour la contribution décrivant les meilleures pratiques de collaboration et de développement que vous voulez encourager au sein du projet. Si vous utilisez un template, vous **devez en citer la source** et **expliquer pourquoi** vous l'avez utilisé.
4. Mettre à jour le README afin de décrire le projet, mettre en lien les différents fichiers pour l'open source, et **ajouter les badges pour les pipelines CI** (voir la documentation sur Github).

# Outils de métriques

## Outil d'analyse de la qualité du code: **test**
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



