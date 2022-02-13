# CONVENTION DES COMMITS



## Faire de petits commits à usage unique
En ne validant que de petites sections de code, tous les membres de l'équipe peuvent rapidement comprendre quel travail a été effectué. En cas de problème, des commits plus petits facilitent l'annulation des mauvais enregistrements, ce qui aide à maintenir une base de code stable.

Il est plus facile de comprendre le code soumis lorsque seules les modifications associées sont archivées. Si vous corrigez deux bogues distincts, il devrait y avoir deux commits distincts.


## Nom des commits

Chaque nom de commit doit inclure :

* Nom de la branche courante des modifications
* Nom de la feature touchée par les modifications
* Description sommaire des modifications (nom de méthode ou autre information pertinante)
  * EX : dev - product - GET /Product




## Rédaction des messages de validation courts et détaillés

Un message de validation  devrait :
* commencer par un bref résumé de la modification.
* être rédigé au présent
* ligne d'objet limitée  à 50 caractères
* avoir deuxième ligne vide pour séparer la ligne d'objet du message pour garantir que seule la ligne d'objet s'affiche



Assurez-vous de fournir suffisamment de détails pour répondre :

* Qu'est-ce qui a changé depuis la dernière version ?
* Comment a-t-il résolu le problème ?
* Pourquoi avez-vous fait le changement ?

### Voici quelques mauvais exemples :

 * Adresse de l'élément de travail xxxyyy.
 * Correction d'un bogue.
 * X et Y refactorisés. Fichiers ajoutés.
 * Qui a cassé ce code ?????
 
