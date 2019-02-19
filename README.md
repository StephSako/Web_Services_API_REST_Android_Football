# Description du projet

L'application "Football API" permet de récupérer des données au format JSON envoyées via des requêtes HTTP depuis une API REST
concernant des compétitions, équipes, matches et joueurs de football des huit principaux championnats, à savoir la Bundesliga (Allemagne), la Ligue 1 (France), La Primera Division (Espagne), la Premier League (Angleterre), la Serie A (Brésil et Italie), l'Eredivisie (Pays-Bas), et enfin la Primereira Liga (ou "Liga NOS", Portugal).

# Choix de l'API REST

Dans l'optique de récupérer un nombre conséquent de données, j'ai choisi l'API de [football-data.org](https://www.football-data.org/ "Site de football-data.org").

![](https://www.football-data.org/assets/logo.jpg)

Afin d'accéder aux services de l'API, nous devons créer un compte et ainsi obtenir un tocken<sup>1</sup> qui sera passé dans le Header de la requête.
Tous les WebServices sont à appeler avec la méthode HTTP @GET.

**_ATTENTON : l'API me restreint à 10 appels/min_**

<sup>1</sup> : "Jeton d'authentification, séquence de lettres et de chiffres en guise de mot de passe pour une authentification forte"

# Outils de développement

- Android Studio
Android Studio est un environnement de développement pour développer des applications mobiles Android.

- Retrofit2
Retrofit est une librairie permettant de réaliser des appels à des webservices REST sur Android.

https://square.github.io/retrofit/

- Postman
PostMan est un envrionnement de développement permettant de visualiser les réponses d'API sous différents formats, pour différentes requêtes HTTP.
https://www.getpostman.com/

# Modèle MVC

Voici la structure MVC du projet : 

```
.
+-- app/
|   +-- manifests/
|   |   +-- AndroidManifest.xml
|   +-- java/
|   |   +-- com.example.footballapi/
|   |   |   +-- controleur/
|   |   |   |   +-- (*.java)
|   |   |   +-- model/
|   |   |   |   +-- model_dao/
|   |   |   |   |   +-- (*.java)
|   |   |   |   +-- model_recyclerview/
|   |   |   |   |   +-- classement/
|   |   |   |   |   |   +-- (*.java)
|   |   |   |   |   +-- matches/
|   |   |   |   |   |   +-- (*.java)
|   |   |   |   |   +-- squad/
|   |   |   |   |   |   +-- (*.java)
|   |   |   |   +-- model_retrofit/
|   |   |   |   |   +-- competition/
|   |   |   |   |   |   +-- (*.java)
|   |   |   |   |   +-- player/
|   |   |   |   |   |   +-- (*.java)
|   |   |   |   |   +-- restService/
|   |   |   |   |   |   +-- (*.java)
|   |   |   |   |   +-- team/
|   |   |   |   |   |   +-- (*.java)
|   |   |   +-- view/
|   |   |   |   +-- activities/
|   |   |   |   |   +-- (*.java)
|   |   |   |   +-- fragments/
|   |   |   |   |   +-- (*.java)
|   |   +-- com.example.footballapi (androidTest/
|   |   |   +-- (...)
|   |   +-- com.example.footballapi (test)/
|   |   |   +-- (...)
|   +-- generatedJava/
|   |   +-- (...)
|   +-- res/
|   |   +-- anim/
|   |   |   +-- (*.xml)
|   |   +-- drawable/
|   |   |   +-- (*.xml)
|   |   +-- layout/
|   |   |   +-- (*.xml)
|   |   +-- menu/
|   |   |   +-- (*.xml)
|   |   +-- mipmap/
|   |   |   +-- ic_[CHAMPIONNAT]_foreground
|   |   |   |   +-- (*.png)
|   |   +-- values/
|   |   |   +-- (*.xml)
|   |   +-- xml/
|   |   |   +-- (*.xml)
+-- svgloader/
+-- Gradle Scripts/
```

# Enchaînement et compositions des écrans

## SplashScreen
En ouvrant l'application, un SplashScreen apparait laissant le temps au programme de mettre en cache<sup>2</sup> les classements des championnats proposés.

## Choix de la compétition (Menu principal)

Le second écran présente huit championnats consultables, avec le logo et le pays associé.

## Classement

En cliquant sur un championnat, l'écran suivant affiche le classement des équipes sous forme de liste avec les logos, les noms des club, leur différences de buts et leurs points. Le nom du championnat est spécifié dans le titre de l'ActionBar.
Un bouton est disponible en haut en droite dans l'ActionBar pour revenir au menu principal.

S'il n'y a pas de connexion Internet, les équipes dans le classement ne sont pas cliquables.

## Ecran de l'équipe

Nous avons accès aux détails, à la liste (fragment) des matches et des joueurs d'une équipe en cliquant sur un item de la liste.

### Liste des matches

Ce fragment liste les matches par ordre chronologique en renseignant la journée, l'équipe domicile et extèrieure et le score (date de la rencontre si le match n'a pas encore été joué).
Il est possible de cliquer sur l'équipe adversaire pour accéder à sa fiche.

### Liste des joueurs (Line-up)

Ce fragment liste tous les joueurs de l'équipe. IL est possible de cliquer sur un joueur pour accéder à sa fiche.

# Fiche d'un joueur

Cette fiche détaille des informations sur le joueur comme sa date de naissance, son nom, sa nationalité, son poste et son numéro de maillot.

# Menu

Un Overflow est disponible sur tous les écrans et permet d'accéder aux préférences et aux crédits.

## Bouton principal

Un bouton (home) redirigeant vers le menu principal est disponible sur chaque écrans de l'application.

## Recherche d'équipes

Un autre bouton (loupe) permet de rechercher une équipe : une liste apparaît et affiche les équipes correspondant au mot-clef saisi grâce à une requête SQL réalisée sur la base de données locale. Un bouton permet d'accéder au championnat de l'équipe ou directement à sa fiche.

## Crédits

Une description, les coordonnées du développeur ainsi que le Git du projet sont renseignés.

## Préférences

**Affichage des images** : l'uilisateur peut choisir d'afficher ou non les logos des clubs dans le classement ainsi que dans l'écran de détails d'une équipe.

**Mise en cache**<sup>2</sup>  : l'utlisateur peut choisir d'activer ou non la mise en cache des classements dans la base de données locale au démarrage de l'application.

**_ATTENTON : l'API me restreignant à 10 appels/min et la mise en cache en réalisant 8 d'un coup, cette préférence est désactivée par défaut_**

# Mise en cache des données
## DAO (Data Access Object)

L'API me renvoyant beaucoup de données que je traite dans mon application, à savoir 8 championnats composées d'une vingtaine d'équipes chacun, elles-mêmes composées de plus de 40 matches et d'une trentaine de joueurs chacune, j'ai décidé de stocker les classements de chaque championnat.

Afin d'assurer une persistance longue des classements, j'ai choisi d'opter pour la DAO qui permet de stocker des données dans une base de données locale SQLite. Voici sa construction :

| Colonne       | Type    | Nullable | Description                                                        |
|:-------------:|:-------:|:--------:|--------------------------------------------------------------------|
| idCompet (PK) | integer | Non      | ID de la compétition associée à l'équipe                           |
| idTeam        | integer | Non      | ID de l'équipe                                                     |
| position      | integer | Non      | Position de l'équipe dans le classement de la compétition associée |
| nomTeam       | text    | Non      | Nom de l'équipe                                                    |
| diff          | integer | Non      | Différence de buts de l'équipe (NbButsMarqués - NbButsConcédés)    |
| points        | integer | Non      | Points de l'équipe dans le classement de la compétition associée   |


Au démarrage, s'il y a Internet et que l'utilisateur à activer cette fonctionnalités dans les préférences, on met à jour la BD locale.

# Problèmes rencontrés

## API
Des informations ne sont pas fournies par l'API  certains numéros de maillots de joueurs ou lien vers les logos des clubs. D'ailleurs, les logos des équipes de Ligue 1 sont fournis mais ils sont protégés par des licenses : le logo de l'application est alors affiché.
De plus, le championnat brésilien dure de mai à décembre; les matches ne sont plus disponibles en dehors de cette plage.

## Git

Bien que j'utilise Git régulièrement, j'ai eu des difficultés à comprendre l'intéret des branches au tout début du développement.
Le GitFlow a été respecté entre les Merge #4 et #10.

## IRL d'images 

Charger des images au format SVG depuis un URL est une tâche compliquée. J'ai d'abord recherché du côté de Glide et de la librairie svg:android, mais les images ne s'affichaient pas correctement. J'ai enfin fini par trouvé une librairie qui parse et affiche les images dans des ImageViews (projet Android-SVGLoader forked dans mes repos).
