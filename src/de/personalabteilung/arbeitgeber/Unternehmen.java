package de.personalabteilung.arbeitgeber;



import de.personalabteilung.mitarbeiter.Arbeitnehmer;

import java.util.*;

///**
// * <pre>
// * usa un Map<Integer, Arbeitnehmer> llamando  arbeitnehmerMap para guardar tooss los objetos de Arbeitnehmer.java
// * con el metodo getArbeitnehmerMap() en otras clases se puede leer o modificar empleados en memoria
// *
// * </pre>
// */

/**
 * <pre>
 *     Die Klasse Unternehmen repräsentiert ein Unternehmen und verwaltet die Mitarbeiter.
 *
 *   - nameUnternehmen: Name des Unternehmens.
 *   - arbeitnehmerMap: Map mit allen Arbeitnehmer-Objekten,
 *     Schlüssel ist die eindeutige ID (Integer), Wert ist das Arbeitnehmer-Objekt.
 *
 *   Die Klasse ermöglicht das Hinzufügen neuer Mitarbeiter und
 *   den Zugriff auf die gesamte Mitarbeiterliste.
 * </pre>
 */
public class Unternehmen {

    private String nameUnternehmen;
    private int nextId; // für im GUI benutzen

    // aqui se declara mitarbeiterMap estara toda la informacion de Arbeitnehmer
    /** Map, die alle Mitarbeiter des Unternehmens enthält
     * Key: Integer (idPerson), Value: Arbeitnehmer-Objekt*/
    private Map<Integer, Arbeitnehmer> arbeitnehmerMap = new HashMap<>();

    /**
     * Konstruktor mit Name und existierender Mitarbeiter-Map.
     * @param nameUnternehmen
     * @param arbeitnehmerMap
     */
    public Unternehmen(String nameUnternehmen, Map<Integer, Arbeitnehmer> arbeitnehmerMap) {
        this.nameUnternehmen = nameUnternehmen;
        this.arbeitnehmerMap = arbeitnehmerMap;
    }
    /**
     * Konstruktor mit nur dem Namen des Unternehmens.
     * Erzeugt eine leere Map für Mitarbeiter.
     * @param nameUnternehmen Name des Unternehmens
     */
    public Unternehmen(String nameUnternehmen) {
        this.nameUnternehmen = nameUnternehmen;
    }

    /**
     * Gibt die Map aller Mitarbeiter zurück.
     * @return Map<Integer, Arbeitnehmer> mit allen Mitarbeitern
     */
    public Map<Integer, Arbeitnehmer> getArbeitnehmerMap() {
        return arbeitnehmerMap;
    }

    /**
     * Fügt einen neuen Mitarbeiter zur Map hinzu.
     * @param id
     * @param mitarbeiter
     */
    public void addMitarbeiter(int id, Arbeitnehmer mitarbeiter){
        arbeitnehmerMap.put(id, mitarbeiter);
    }

    /**
     * erstellt nur für GUI,
     * @return
     */
    public int getNextId() {
        return arbeitnehmerMap.keySet().stream().mapToInt(Integer::intValue).max().orElse(0)+1;
    }
}
