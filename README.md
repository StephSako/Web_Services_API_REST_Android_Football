# Description du projet

L'application "Football API" permet de récupérer des données au format JSON envoyées via des requêtes HTTP depuis une API REST
concernant des compétitions, équipes, matches et joueurs de football des huit principaux championnats, à savoir la Bundesliga (Allemagne), la Ligue 1 (France), La Primera Division (Espagne), la Premier League (Angleterre), la Serie A (Brésil et Italie), l'Eredivisie (Pays-Bas), et enfin la Primereira Liga (ou "Liga NOS", Portugal).

# Choix de l'API REST

Dans l'optique de récupérer un nombre conséquent de données, j'ai choisi l'API de [football-data.org](https://www.football-data.org/ "Site de football-data.org").

![](https://www.football-data.org/assets/logo.jpg)

Afin d'accéder aux services de l'API, nous devons créer un compte et ainsi obtenir un tocken<sup>1</sup> qui sera passé dans le Header de la requête.
Tous les WebServices sont à appeler avec la méthode HTTP @GET

<sup>1</sup> : "Jeton d'authentification, séquence de lettres et de chiffres en guise de mot de passe pour une authentification forte"

# Outils de développement

- Android Studio
- Retrofit2

https://square.github.io/retrofit/

- Postman

# Enchaînement et compositions des écrans

# Préférences

# Chargement des images au format SVG

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

Voici ma logique concernant la mise en cache et son utilisation :

~~~
Au démarrage (ce qui permet d'avoir tous les classements au lieu d'en avoir qu'un seul à sa consultation)
    s'il y a Internet
        on met à jour la BD locale
    sinon
        rien
~~~

# Problèmes rencontrés

# Conclusion
