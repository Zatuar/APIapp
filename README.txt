Bonjour,
Voici l'application associé à l'API des Tâches et Users
Je sais que ce n'était pas demandé mais c'était plus facile pour moi pour faire les tests.
Cette application est en android java avec le .apk joint avec ce dossier, je vous conseille de l'utiliser pour tester mon API.
Mon API est donc agencé pour lui correspondre, ce qui n'est pas complètement hors-sujet puisque comme dit dans l'énoncé :
"Cette API sera le back-end d’une application mobile"
Je l'ai donc réalisé en plus
Pour ce qui est des Test unitaires, je ne pense pas les  réalisés de manières attendu.

Description de mon application :
Page 1 : page de login
- Entrez votre Nom et email puis appuer sur "Sign in", s'il y a un compte associé vous passerez à la page 3.
  Sinon un message disant "Aucun compte avec ce nom et mot de passe" apparaitra
  Si l'un de deux champs (ou les deux) ne sont pas rempli un message disant "le champ Nom(ou mot de passe) est vide, remplissez le"
- Appuez sur le bouton "Sign up" pour aller à la page 2

Page 2 : page de création de compte
- Entrez votre nom(ou pseudo), votre email et mot de passe, puis appuyez sur "Create" s'il y a aucun compte associé a ce nom ou email alors
  un compte sera créer et vous retournerez sur la page 1
  S'il y a un champs vide, il sera signalé à l'utilisateur
  Pareil s'il y a déjà un compte associé à ce nom ou email, l'utilisateur sera informé

Page 3 : la liste des tâches de l'utilisateur s'affiche
- Si l'utilisateur appuie sur le switchButton, la tâche passera de non-réalisée à réalisée (ou inversement)
- S'il appuie sur la poubelle (le bouton rouge) la tâche sera supprimé
- S'il appuie sur le plus en bas de la page il ira sur la page 4
- s'il appuie sur la tâche il ira sur la page 5

Page 4 : page de création de tâche
- Entrez le titre et le contenu de la tâche et appuyez sur "Create", s'il y a au moins un titre la tâche sera créer et retournera sur la page 3
  sinon l'utilisateur sera avertie

page 5 : page du détail de la tâche