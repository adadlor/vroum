# LOMY TEAM : Projet Vroom -> État WIP

## Requis

Fonctionne avec Maven & Java 19
- [ ] Installer Java 19 
- [ ] Installer Maven

### Alternative
Vous pouvez utiliser intellij pour lancer le projet. 
Tout se configure automatiquement.


## But du jeu 
Imaginez une course de voiture électique en usant de statégie pour parvenir en premier à la ligne de départ ! Comprenez et apprennez sur le développement durable en répondant au questions et débloquez de nouvelles voitures stylées.

## Règles
Jeu de courses en tour par tour, nombre de tours à définir par le joueur hôte, sur une grille, le premier arrivé gagne
Avant-course :
    - Détermination de l'ordre des joueurs 
    - Mise en place des joueurs un par un
Course :
    - Le premier joueur effectue son premier déplacement
    - Comme il part de la vitesse 0, il a 8 possibilités de déplacement (les 8 noeuds autour de sa position)
    - Chaque joueur se déplace sur le même principe
    - Au tour suivant, on reporte le vecteur de déplacement du tour précédent à partir de la position actuelle. Il existe donc 9        possibilités de déplacement pour ce tour (le point de report du vecteur + les 8 noeuds autour).
    - Les déplacements s'effectuent sur ce principe jusqu'à la fin de la course.
    - En cas de sortie de piste, le joueur ne peut pas jouer le tour suivant et passe à l'arrêt. Il peut repartir au deuxième tour 
        après sa sortie, en passant par son point de sortie.
    - Les colisions sont interdites, le joueur est éliminé si elle est inévitable.



## Usage
Le jeu se joue seul, à 2 où a plusieurs.

## Support
-> Joindre par la messagerie ENT 

# Création de Jeu
## Première partie
    - Faire une première version de jeux simple entre deux joueur humains

## Developpement de jeu

    - Initier l'IA pour le jeux en mode Solo

## Dernière partie

    -  Intégration aux activités discord

