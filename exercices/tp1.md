# TP1

## Convention de commits utilisées pour l'itération 1. 

- **Comment nommer les commits selon leur type/section/grosseur/etc.**

    Notre façon de nommer les commits est très simple. On vise à avoir le nom le plus court possible ***ET*** le nom le plus indicatif possible. Nous avons donc pris la décision de ne pas nommer les commits d'une façon très formel selon la branche, le tpye, la grosseur, etc. Par contre, nous avons tout de même décidez de quelques règles simple:

    - Le commit doit dire qu'est-ce qui est modifié / ajouté / supprimer / etc.
    - Le commit doit dire dans quel fichier celui-ci effectu son action. 


- **Quoi et quand commiter?**

    - Quoi commiter?

        Notre convention d'équipe ressemble beaucoup aux standards pour ce point. On commit toujours dès que l'on termine un bout de code qui pourrait modifier le comportement de l'application. 

    - Quand commiter?

        Nous avons établi que, si quelqu'un travail sur du code, il doit commiter ***au minimum*** 1 fois par jour. Cela dit, il serait préférable de la faire 2-3 fois par jours. Nous voulons qu'il y ai beaucoup de commits, car c'est plus sécuritaire pour un travail en équipe. 


## Convention de branchage utilisées pour l'itération 1. 

- **Quelles sont les branches de base (qui sont communes et qui existeront toujours) et quels sont leurs rôles (chacune)?**

    - La branche **MAIN**:

        Son rôle est simple: héberger la version la plus stable et fonctionnelle de l'application. Personne ne pousse directement sur cette branche et le seul pull request qui peut lui être associé provient de la branche DEV. C'est la branche utilisé pour faire rouler l'application en mode production.


    - La branche **DEV** 

        C'est sur cette branche que le code en développement sera hébergé. Par contre, aucun développeur de développera de code directement sur cette branche. Celui-ci devra se creer une branche bien spécifique à l'issue qui lui est associée pour ensuite creer un "*pull request*" de sa nouvelle branche à dev. 


- **Quelle branche est LA branche principale (contenant le code officiellement intégré et pouvant être remis)?**

    La branche **MAIN**


- **Quand créer une nouvelle branche?**

    Il faut creer une nouvelle branche quand on s'attaque à un issue. On peut donc comprendre que du moment que l'on veut travailler sur un nouveau bout de l'application, il faut creer une nouvelle branche. Celle-ci sera éventuellement *merged* à dev, pour finalement être supprimé


- **Quand faire une demande de changement / d'intégration (pull request / merge request) et sur quelle branche la faire?**

    Notre convention pour les pull/merge request est très simple. Il faut en creer un quand on commence à travailler sur un issue. Cela va permettre pour toute l'équipe (souvent une seule personne qui est assignée au pull request en question) de vérifier les commits. On fait toujours les pull request sur la branche dev. 

## Photos prises vers la fin de l'itération 1 pour s'assurer du respect des conventions. 

### Projet
![Alt text](/exercices/Screenshots/projetScreen.png?raw=true "Optional Title")

### Milestones
![Alt text](/exercices/Screenshots/milestone.png?raw=true "Optional Title")

### Issues ouvertes
![Alt text](/exercices/Screenshots/openedIssues.png?raw=true "Optional Title")

### Issues fermées
![Alt text](/exercices/Screenshots/closedIssues.png?raw=true "Optional Title")

### une issue détaillée
![Alt text](/exercices/Screenshots/insideIssue.png?raw=true "Optional Title")

### Pull requests ouvertes
![Alt text](/exercices/Screenshots/openPr.png?raw=true "Optional Title")

### Pull requests fermées
![Alt text](/exercices/Screenshots/closedPr.png?raw=true "Optional Title")

### Une pull request détaillée
![Alt text](/exercices/Screenshots/insidePr.png?raw=true "Optional Title")


