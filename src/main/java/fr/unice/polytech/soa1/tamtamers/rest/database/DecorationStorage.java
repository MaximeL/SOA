package fr.unice.polytech.soa1.tamtamers.rest.database;

import fr.unice.polytech.soa1.tamtamers.rest.entity.Decoration;

import java.util.HashMap;

public class DecorationStorage {
    // Mockage de la DB
    private static HashMap<Integer, Decoration> decorations = new HashMap<Integer, Decoration>();

    // DECORATION
    public static void createDecoration(Decoration decoration) {
        decorations.put(decoration.getId(), decoration);
    }
    public static Decoration getDecoration(Integer id) {
        return decorations.get(id);
    }

    static {
        Decoration leopard = new Decoration(1);
        leopard.setName("Design Léopard");
        leopard.setPrice(0.12);
        leopard.setImage("http://images.jedessine.com/_uploads/_tiny_galerie/20091044/leopard-dessin-source_sen.jpg");

        Decoration zebre = new Decoration(2);
        zebre.setName("Design zébré");
        zebre.setPrice(2);
        zebre.setImage("http://www.quizz.biz/uploads/quizz/823431/10_MSbd0.jpg");

        Decoration militaire = new Decoration(3);
        militaire.setName("Furtif");
        militaire.setPrice(-2);
        militaire.setImage("http://i.ytimg.com/vi/8DIhCp1O52Q/hqdefault.jpg");

        DecorationStorage.createDecoration(leopard);
        DecorationStorage.createDecoration(zebre);
        DecorationStorage.createDecoration(militaire);
    }
}
