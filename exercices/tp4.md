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


## Exécution

Maintenant que vous êtes mieux renseignés sur l'open source, vous savez qu'il vous faut mettre en place des pratiques permettant de partager votre vision et l'information requise pour contribuer au projet. Ainsi, vous devez :

1. Créer un fichier pour le "Code of conduct" en suivant les meilleures pratique. Si vous utilisez un template, vous **devez en citer la source** et **expliquer pourquoi** vous l'avez utilisé.
2. Créer un fichier pour la licence en suivant les meilleures pratiques. Indiquez les **3 raisons principales** (dans le fichier d'exercice) pour lesquelles vous avez choisi cette licence par rapport aux autres.
3. Créer un fichier pour la contribution décrivant les meilleures pratiques de collaboration et de développement que vous voulez encourager au sein du projet. Si vous utilisez un template, vous **devez en citer la source** et **expliquer pourquoi** vous l'avez utilisé.
4. Mettre à jour le README afin de décrire le projet, mettre en lien les différents fichiers pour l'open source, et **ajouter les badges pour les pipelines CI** (voir la documentation sur Github).

# Outils de métriques

Afin de s'assurer que la qualité de votre code respecte les standards de l'industrie, on vous demande d'intégrer **3** outils d'analyse et de métrique du code. Ainsi, vous devez trouver et intégrer :

- 1 outil d'analyse de la **qualité du code**
- 1 outil de détection de **failles de sécurité**
- 1 outil de mesure du **test coverage**

Remettez au moins **2 screenshot par outil** afin de bien montrer les résultats des analyses dans le fichier `exercices/tp4.md`.

**🚨 IMPORTANT 🚨** Vos outils doivent s'intégrer à votre code de manière continue, soit par le pipeline CI, soit en y connectant votre repository Github. Ces outils ne **peuvent pas** être des outils exécutés manuellement (ex : par IntelliJ).