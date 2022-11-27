# Comment lancer la démo du kata

Option1:  Build le jar puis lancer avec la ligne de commande de java

Option2: lancer la commande `gradle run` dans le répertoire racine du projet.

# Comment la démo marche-t-elle

La démo est constituée d'une interface de linge de commande. Ci-dessous sont des commande valable:

* `deposit <montant>`: Elle est pour faire un dépôt. `<montant>` doit être un chiffre positif de format américan. Par exemple: 12.34 (non pas 12,34) 

* `withdraw <montant>`: Elle est pour faire un retrait. Il est indifférent d'entrer un chiffre positif ou un chiffre négatif.

* `print`: Elle est pour imprimer dans la console le journal bancaire
