


# Description du projet

Projet de développement mobile de 4ème année à l'ESIEA, utilisant le pattern MVC pour une application Android codée en Java.

L'application **FootLite** traite des données reçues au format JSON récupérées via requêtes HTTP GET sécurisées depuis l'API REST de <a href="https://www.football-data.org/">football-data.org</a> relative à des compétitions, équipes, matches, historiques de rencontres et joueurs de football des huit principaux championnats européens, à savoir la **Bundesliga** (Allemagne), la **Ligue 1** (France), la **Primera Division** (Espagne), la **Premier League** (Angleterre), la **Serie A** (Brésil et Italie), l'**Eredivisie** (Pays-Bas) et enfin la **Primeira Liga** (ou "Liga NOS", Portugal).

L'utilisateur a également la possibilité de s'inscrire et de se connecter à un compte dans lequel il renseignera son pseudo, mot de passe ainsi que le club qu'il supporte. De plus, il pourra parier sur les matches de son choix des 8 championnats.
J'ai donc développé <a href="https://github.com/StephSako/FootLite-PHP-REST-API">mon API REST</a> implémentée en PHP sur un serveur mutualisé Linux grâce à l'hébergeur Web <a href="https://www.alwaysdata.com/fr/">always-data</a> dans le but de gérer des comptes et des paris.

# Outils et technologies de développement

<p align="center"><img src="https://uploads-ssl.webflow.com/5b2117aebeee55aa5a8260df/5b3ad6f600c8d86a49530010_android-studio.png" width="16%"></p>

- **<a href="https://developer.android.com/studio">Android Studio</a>** est un environnement de développement pour développer des applications mobiles Android.
<br>
<p align="center"><img src="https://miro.medium.com/max/1030/0*0RDQH_SlaGamudtr.jpg" width="16%"></p>

- **<a href="https://square.github.io/retrofit/">Retrofit2</a>** est une librairie permettant de réaliser des appels à des webservices REST sur Android.
<br>
<p align="center"><img src="https://huddle.eurostarsoftwaretesting.com/wp-content/uploads/2018/10/pm-logo-vert.png" width="24%"></p>

- **<a href="https://www.getpostman.com/">Postman</a>** est un envrionnement de développement permettant de visualiser les réponses d'API sous différents formats, pour différentes requêtes HTTP.
<br>
<p align="center"><img src="https://www.primefaces.org/wp-content/uploads/2016/10/feature-8.png" width="15%"></p>

- **<a href="https://www.getpostman.com/">Material design</a>** est un ensemble de règles de design proposées par Google et qui s'appliquent à l'interface graphique des logiciels et applications.

## Consignes et fonctionnalités implémentées :
  - Une quinzaine d'écrans (4 activités et 12 fragments)
  - Utilisation de *RecyclerView*, *ViewPager*, *WebView*, *GoogleMap*, *ProgressBar* stylisés
   - Une dizaine d'appels WebService :
	   - API REST de football avec méthodes HTTP @GET
	   - API REST développée en PHP et hébergée sur un serveur Linux avec méthodes HTTP @GET & @POST (gestions des comptes et paris sportifs)
   - Stockage des données en cache :
	   - SharedPreferences
	   - Base de données locale SQLite
   - Architecture MVC
   - SplashScreen au démarrage de l'application
   - GitFlow respecté (master → developp → features → #...)
   - Animations entre les écrans
   - Option de recherche d'équipes dans la BDD locale
   - Utilisation du Material Design & migration vers androidx
   - Préférences SharedPreferences :
		- Affichage des logos
		- Activation de la mise en cache au démarrage de l'application

## Navigation dans l'application :
L'application **FootLite** est composée des vues suivantes :
* Activité principale composée d'une *Navigation Drawer* permettant la navigation entre chaque championnats et activités listés ci-dessous
* Activités de connexion & d'inscription à un compte FootLite
* Fragment principal listant les matches de la saison de l'équipe supportée
* Fragment détaillant un match (boutons de paris, score, dates, historique des rencontres, ...)
* Fragment d'une compétition composée d'un *ViewPager* de deux fragments :
	* son classement
	* les matches de toutes ses équipes
* Fragment détaillant une équipe à l'aide d'un *ViewPager* composé de 4 fragments :
	- les matches de la saison
	- l'équipe (joueurs et staff)
	- carte *Google Maps* situant le club avec le nom du stade
	- page Web officiel du club affiché
* Fragment présentant un joueur
* Activité de recherche d'une équipe ou de son championnat
* Activité pour effectuer des modifications de son compte FootLite
* Activité des paramètres de l'application
* Fragment des crédits

# API REST - Récupération des données à afficher
Dans l'optique de récupérer un nombre conséquent de données, j'ai choisi l'API REST sportive <a href="https://www.football-data.org/">football-data.org</a>.

<p align="center"><img src="https://www.football-data.org/assets/logo.jpg" width="50%"></p>

Afin d'accéder aux services de l'API, nous devons créer un compte et ainsi obtenir un tocken<sup>1</sup> qui sera passé dans le header de la requête. Tous les WebServices sont à appeler avec la méthode HTTP @GET (pattern *Singleton* & *Facade*). De plus, les requêtes utilisent une couche de transport sécurisée (TSL Connection).

Les contrôleurs sont basés sur le pattern de l'injection de dépendances.

**_ATTENTON : cette API restreint à 10 appels/min. Pour la première utilisation, la mise en cache est activée afin de remplir une première fois la base de données locale. Une fois l'application installée, désactivez-la dans les préférences pour ne plus utiliser 8 appels à chaque redémarrage de l'application._**

<sup>1</sup> *token* : Jeton d'authentification, séquence de lettres et de chiffres en guise de mot de passe pour une authentification forte.

### Structure MVC du code Java

```
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
|   |   |   |   +-- model_session_manager/
|   |   |   |   |   |   +-- (*.java)
|   |   |   |   +-- model_view_pager/
|   |   |   |   |   |   +-- competition/
|   |   |   |   |   |   |   +-- (*.java)
|   |   |   |   |   |   +-- team/
|   |   |   |   |   |   |   +-- (*.java)
|   |   |   +-- view/
|   |   |   |   +-- activities/
|   |   |   |   |   +-- (*.java)
|   |   |   |   +-- fragments/
|   |   |   |   |   +-- (*.java)
|   +-- res/
|   |   +-- anim/, drawable/, layout/, menu/, values/, xml/
|   |   |   +-- (*.xml)
|   |   +-- mipmap/
|   |   |   +-- (*.png)
+-- svgloader/
+-- Gradle Scripts/
```

# Implémentation d'une API REST en PHP via hébergeur Web (serveur Linux mutualisé)
Afin de pouvoir gérer librement les comptes des utilisateurs ainsi que leurs paris, j'ai choisi de développer une API REST en PHP, grâce aux multiples services que propose l'hébergeur web <a href="https://www.alwaysdata.com/fr/">always-data</a>, afin de réaliser des requêtes HTTP @POST.
Cette solution me permet de stocker et d'avoir accès à 100 Mo de code sur un serveur Linux (notamment grâce à l'accès distant <a href="https://openclassrooms.com/fr/courses/43538-reprenez-le-controle-a-laide-de-linux/41773-la-connexion-securisee-a-distance-avec-ssh">SSH</a>) et de gérer une base de données MySQL.

Voici le schéma relationnel de la base de données :

<p align="center"><img src="https://image.noelshack.com/fichiers/2019/47/4/1574346595-mld-ad.png"></p>

J'ai également structuré mes fichiers de code PHP selon le modèle MVC :

```
+-- www/
|   +-- bet/				Contrôleurs des paris
|   |   +-- *.php
|   +-- supporter/			Contrôleurs des supporters
|   |   +-- *.php
|   +-- config/				Modèle de la BDD
|   |   +-- bdd.php
|   +-- objects/			Modèles
|   |   |   +-- supporter.php
|   |   |   +-- bet.php
|   +-- rest.php
```
*Le fichier* **rest.php** *est chargé de récupérer les données envoyées par les requêtes SQL et de les convertir au bon format JSON.*

Le code est disponible <a href="https://github.com/StephSako/FootLite-PHP-REST-API">ici</a>.

# Enchaînement et compositions des écrans

### SplashScreen
En ouvrant l'application, un SplashScreen apparaît, laissant le temps au programme de mettre en cache<sup>2</sup> les classements et équipes des championnats. Une animation de rotation zoom-dézoom est lancée.

<p align="center"><img src="https://image.noelshack.com/fichiers/2019/47/4/1574369228-screenshot-20191121-214631-footlite.jpg" width="40%"></p>

### Connexion & inscription à son compte FootLite

Voici l'écran de connexion où doivent être renseignés le pseudo et le mot de passe :

<p align="center"><img src="https://image.noelshack.com/fichiers/2019/47/4/1574346856-screenshot-20191120-095852-footlite.jpg" width="40%"></p>

* Cet écran apparaît lorsque l'utilisateur n'est pas connecté, donc lorsque les SharedPreferences relatives au compte sont vides. Après connexion, ses données personnelles ainsi que l'ensemble de ses paris sont mis à jour dans les SharedPreferences (le tableau de paris est donc sérialisé).

Voici l'écran d'inscription où doivent être renseignés le pseudo, le mot de passe et l'équipe supportée en la sélectionnant grâce à un Spinner (les équipes sont récupérées grâce aux données stockées dans la base de données locale SQLite) :

<p align="center"><img src="https://image.noelshack.com/fichiers/2019/47/4/1574346990-screenshot-20191120-095858-footlite.jpg" width="40%"></p>

Il est possible de se déconnecter depuis le drawer. Les données en cache relatives aux comptes sont donc vidées.

## Liste des matches de l'équipe supportée

### Navigation Drawer

Une fois l'utilisateur connecté, le header de la Navigation Drawer se met à jour faisant apparaître le logo de l'équipe supportée ainsi que le nom du supporter.
De plus, ce drawer permet de rechercher une équipe et son championnat, de naviguer entre les différentes compétitions, de modifier son compte FootLite, de gérer les paramètres de l'application, de se déconnecter puis de voir les crédits.

<p align="center"><img src="https://image.noelshack.com/fichiers/2019/47/4/1574351279-nd-up.jpg" width="40%"></p>
<p align="center"><img src="https://image.noelshack.com/fichiers/2019/47/4/1574351282-nd-down.jpg" width="40%"></p>

Le fragment initialisé est une RecyclerView listant les matches de la saison (compétition d'appartenance et potentielle participation à la Ligue des Champions) de l'équipe ainsi supportée. La RecyclerView s'initialise à la date du dernier match joué pour éviter de scroller la liste à date actuelle.

<p align="center"><img src="https://image.noelshack.com/fichiers/2019/47/4/1574351618-screenshot-20191120-100221-footlite.jpg" width="40%"></p>

Les matches terminés sont encadrés en noir, programmés en bleu ciel, annulés en rouge, en live en vert clair puis ceux en pause (mi-temps par exemple) en orange.

## Compétition

En cliquant sur une compétition, l'écran charge un ViewPager révélant le classement et tous les matches de la saison en cours.
Afin de générer les différentes RecyclerView de l'application, j'ai utilisé un pattern Adapter.

<p align="center"><img src="https://image.noelshack.com/fichiers/2019/47/4/1574352699-screenshot-20191120-095816-footlite.jpg" width="35%">
    -
<img src="https://image.noelshack.com/fichiers/2019/47/4/1574352728-screenshot-20191121-145626-footlite.jpg" width="35%"></p>

S'il n'y a pas de connexion Internet, les équipes dans le classement ne sont pas cliquables et un message d'avertissement apparaît.

<p align="center"><img src="https://image.noelshack.com/fichiers/2019/47/4/1574370394-screenshot-20191121-220600-footlite.jpg" width="40%"></p>

## L'équipe

Nous pouvons accéder à cet écran depuis plusieurs sources :
* En recherchant une équipe : une liste apparaît et affiche les équipes correspondant au mot-clef saisi, grâce à une requête SQL réalisée sur la base de données locale. Les boutons permettent d'accéder à sa compétition ou à sa fiche.
<p align="center"><img src="https://image.noelshack.com/fichiers/2019/47/4/1574352962-screenshot-20191120-100201-footlite.jpg" width="40%"></p>

* En cliquant sur le logo de l'équipe supportée dans la NavigationDrawer
* En cliquant sur un item dans les classements

Un ViewPager de 4 fragments nous expose différentes informations :

* une RecyclerView des matches de l'équipe. Il est possible de cliquer sur un match pour accéder à ses détails.
<p align="center"><img src="https://image.noelshack.com/fichiers/2019/47/4/1574353458-screenshot-20191120-125643-footlite.jpg" width="40%"></p>

* une RecyclerView du line-up (joueurs et staff).  Il est possible de cliquer sur un joueur/personnel pour accéder à sa fiche.
<p align="center"><img src="https://image.noelshack.com/fichiers/2019/47/4/1574353491-screenshot-20191120-125653-footlite.jpg" width="40%"></p>

* une carte Google Maps situant le club (librairie **Geocoder**) avec un marker titré du nom du stade (j'ai dû utiliser l'API Maps de Google et m'authentifier grâce à un token)
<p align="center"><img src="https://image.noelshack.com/fichiers/2019/47/4/1574353512-screenshot-20191120-125659-footlite.jpg" width="40%"></p>

* un composant WebView affichant le site officiel du club
<p align="center"><img src="https://image.noelshack.com/fichiers/2019/47/4/1574353592-screenshot-20191120-125704-footlite.jpg" width="40%"></p>

## Fiche d'un match

En cliquant sur un match depuis une RecyclerView, nous avons accès à la fiche d'un match renseignant les logos et noms des deux équipes, la date et stade de la rencontre, le score à la mi-temps ("MT") et à la fin du match, ainsi qu'un historique des dernières rencontres avec le nombre de match disputés, de buts et des statistiques de victoires/défaites matérialisées par des ProgressBar stylisés.

<p align="center"><img src="https://image.noelshack.com/fichiers/2019/47/4/1574353817-screenshot-20191120-184726-footlite.jpg" width="40%"></p>

Le bandeau vert central permet au supporters de parier sur le match en cliquant sur le bouton associé à un potentiel vainqueur. Le nombre de parieurs ainsi que les **côtes** sont renseignés et mis à jour dès le pari effectué.
Il n'est pas possible de parier une seconde fois, ni lorsque le match a un autre statut que *programmé* (autrement dit, un match *suspendu*, *annulé*, *en live* ou *terminé* n'est plus susceptible d'être parié ... tel est le principe du pari sportif 😁).

<p align="center"><img src="https://image.noelshack.com/fichiers/2019/47/4/1574354182-screenshot-20191121-173556-footlite.jpg" width="40%"></p>

## Fiche d'un joueur

Cette fiche détaille des informations personnelles sur le joueur.

<p align="center"><img src="https://image.noelshack.com/fichiers/2019/47/4/1574357233-screenshot-20191120-152920-footlite.jpg" width="40%"></p>

## Modifier son compte FootLite

L'utilisateur a la possibilité de modifier ses informations personnelles telles que son pseudo, mot de passe et équipe supportée. Les inputs sont bloqués le temps que la requête renvoie une réponse.
Les informations sont alors immédiatement mises à jour dans le header du drawer de l'activité principale.

<p align="center"><img src="https://image.noelshack.com/fichiers/2019/47/4/1574354575-screenshot-20191120-125719-footlite.jpg" width="40%"></p>

## Crédits

Une description, les coordonnées du développeur ainsi que le Git du projet sont renseignés.

<p align="center"><img src="https://image.noelshack.com/fichiers/2019/47/4/1574354382-screenshot-20191120-190138-footlite.jpg" width="40%"></p>

## Préférences

<p align="center"><img src="https://image.noelshack.com/fichiers/2019/47/4/1574354681-screenshot-20191121-173557-footlite-convertimage.jpg" width="60%"></p>

**Affichage des images** : l'uilisateur peut choisir d'afficher ou non les logos des clubs dans le classement ainsi que dans l'écran de détails d'une équipe.

<sup>2</sup> **Mise en cache** : l'utlisateur peut choisir d'activer ou non la mise en cache des classements dans la base de données locale au démarrage de l'application.

# Mise en cache des données

### SharedPreferences

Le contrôleur/helper SessionManagerPreferences donne un accès aux données stockées en cache et permet de manager les paramètres et les données du compte FootLite, comme effectuer des opérations de type CRUD , vérifier que l'utilisateur est bien connecté ou vider le cache lors d'une déconnexion.

### DAO (Data Access Object)

L'API me renvoie beaucoup de données que je traite dans mon application, à savoir 8 championnats composés d'une vingtaine d'équipes chacun, elles-mêmes composées de plus de 40 matches et d'une trentaine de joueurs chacune; j'ai décidé de stocker les classements de chaque championnat.

Afin d'assurer une persistance longue des classements, j'ai choisi d'opter pour la DAO qui permet de stocker des données dans une base de données locale SQLite. Voici sa structure :

| Colonne       | Type    | Nullable | Description                                                        |
|:-------------:|:-------:|:--------:|--------------------------------------------------------------------|
| idCompet (PK) | integer | Non      | ID de la compétition associée à l'équipe                           |
| idTeam        | integer | Non      | ID de l'équipe                                                     |
| position      | integer | Non      | Position de l'équipe dans le classement de la compétition associée |
| nomTeam       | text    | Non      | Nom de l'équipe                                                    |
| diff          | integer | Non      | Différence de buts de l'équipe (NbButsMarqués - NbButsConcédés)    |
| points        | integer | Non      | Points de l'équipe dans le classement de la compétition associée   |
| crest         | text    | Oui      | URL de l'image de l'équipe (format SVG, PNG ou GIF)                |

Au démarrage, s'il y a Internet et que l'utilisateur a activé cette fonctionnalité dans les préférences, la base de données locale est mise à jour.

# Problèmes rencontrés

### API
Des informations ne sont pas fournies par l'API comme certaines URL d'images pour des équipes ainsi que pour les matches. Par conséquent, j'ai opté pour la création d'un helper qui fournit les URL d'images manquantes en passant le nom de l'équipe en paramètre.
De plus, le championnat brésilien a lieu de Mai à Décembre; les matches ne sont plus consultables en dehors de cette plage.
<br>
### URL d'images

Charger des images au format SVG depuis une URL est une tâche compliquée. J'ai d'abord recherché du côté de Glide et de la librairie svg:android, mais les images ne s'affichaient pas correctement. J'ai finalement trouvé une <a href="https://github.com/ar-android/AndroidSvgLoader">librairie</a> qui parse et affiche les images dans des ImageView correctement (il faut parfois faire des va-et-vient avec les RecyclerView pour les afficher).
