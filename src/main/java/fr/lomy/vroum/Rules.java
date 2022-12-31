package fr.lomy.vroum;
import static com.almasb.fxgl.dsl.FXGL.*;

public class Rules {
    public Rules(){
        getDialogService().showMessageBox("         Avant-course :\n\n" +
                "- Détermination de l'ordre des joueurs\n" +
                "- Mise en place des joueurs un par un\n\n" +
                "         Course :\n\n" +
                "- Le premier joueur effectue son premier déplacement\n" +
                "- Comme il part de la vitesse zéro, il a huit possibilités de déplacement\n(les huits noeuds autour de sa position)\n" +
                "- Au tour suivant, on reporte le vecteur de déplacement du tour précédent\nà partir de la position actuelle.\n" +
                "- Les déplacements s'effectuent sur ce principe jusqu'à la fin de la course.\n" +
                "- En cas de sortie de piste, le joueur ne peut pas jouer le tour suivant\net passe à l'arrêt. Il peut repartir au deuxième tour après sa sortie,\nen passant par son point de sortie.\n" +
                "- Les colisions sont interdites, le joueur est éliminé si elle est inévitable.");
    }
}
