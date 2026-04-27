package de.personalabteilung.datei;

import de.personalabteilung.mitarbeiter.Arbeitnehmer;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArbeitnehmerPersistens {

    private static final String DATEI = "mitarbeiterliste.txt";

//    /**
//     * <pre>el metodo List<String> lessenDatei() se lee todas las lineas de el archivo mitarbeiter.txt
//     *   y se guardan en una lista , esto para despues extraer valores independientes
//     * @return una lista con todos los empleados registrados en el sistema que ya estan en mitarbeiter.txt
//     *
//     * el metodo List<String> lessenDatei() se lee todas las lineas de el archivo mitarbeiter.txt
//     * y se guardan en una lista , esto para despues extraer valores independientes
//     * @return una lista con todos los empleados registrados en el sistema que ya estan en mitarbeiter.txt
//     * </pre>
//     */

    /**
     * Liest alle Zeilen aus der Datei "mitarbeiterliste.txt"
     * und speichert sie in einer Liste von Strings.
     * Jede Zeile entspricht einem gespeicherten Arbeitnehmer.
     * @return Liste aller Zeilen aus der Datei
     */
    public List<String> lessenDatei(){
        List<String> alleZeilen = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("mitarbeiterliste.txt"))){
            System.out.println("------Daten von Mitarbeitern lesen-------");
            String zeile;
            while ((zeile = reader.readLine()) != null){
                alleZeilen.add(zeile);
                System.out.println(zeile);
            }
        } catch (FileNotFoundException exception) {
            System.out.println("Fehler beim Lesen "+ exception.getMessage());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return alleZeilen;
    }

    /**
     * Liest alle Arbeitnehmer aus der Datei "mitarbeiterliste.txt"
     * und wandelt jede Zeile in ein Arbeitnehmer-Objekt um.
     * @return Liste aller Arbeitnehmer-Objekte
     */
    public  List<Arbeitnehmer> lessenAllerArbeitnehmer(){
        List<Arbeitnehmer> alleArbeitnehmer= new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("mitarbeiterliste.txt"))){
            System.out.println("------Daten von Mitarbeitern lesen-------");
            String zeile;
            while ((zeile = reader.readLine()) != null){
                Arbeitnehmer arbeitnehmer = Arbeitnehmer.fromString(zeile);
                alleArbeitnehmer.add(arbeitnehmer);
                System.out.println(zeile);
            }
        } catch (FileNotFoundException exception) {
            System.out.println("Fehler beim Lesen "+ exception.getMessage());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        return alleArbeitnehmer;
    }

    /**
     * speichert die über die GUI eingegebenen Daten in einer Datei
     * FileWriter(DATEI, true) öffnet die Datei im Anhänge-Modus (Append), dadurch wird der bestehende Inhalt nicht überschrieben.
     * mitarbeiter.toFileString() wandelt dein Objekt in die Zeile um, die in der TXT-Datei gespeichert wird.
     * Jedes Mal, wenn du Speichern drückst, wird diese Methode aufgerufen und der Mitarbeiter wird dauerhaft in der Datei gespeichert.
     */
    public void speichernInDatei(Arbeitnehmer mitarbeiter){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(DATEI,true))){
            writer.write(mitarbeiter.toFileString());
            writer.newLine();
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }
    //---------------pobando de ultima hora -------------
    //Überschreibt die gesamte Datei mit der aktuellen Sammlung (vermeidet Duplikate)
    public void speichernAlle(Collection<Arbeitnehmer> alleArbeitnehmer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATEI, false))) {
            for (Arbeitnehmer a : alleArbeitnehmer) {
                writer.write(a.toFileString());
                writer.newLine();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
