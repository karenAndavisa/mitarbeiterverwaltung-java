package de.personalabteilung.datei;

import de.personalabteilung.mitarbeiter.Arbeitnehmer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Hier werden die Aktionen implementiert, die auf der Datei mitarbeiter.txt ausgeführt werden.
 * aqui se implementan las acciones que se desarrolan sobre mitarbeiter.txt
 * </pre>
 */

public class Datei {

    /**
     * <pre>el metodo List<String> lessenDatei() se lee todas las lineas de el archivo mitarbeiter.txt
     *   y se guardan en una lista , esto para despues extraer valores independientes
     * @return una lista con todos los empleados registrados en el sistema que ya estan en mitarbeiter.txt
     *
     * el metodo List<String> lessenDatei() se lee todas las lineas de el archivo mitarbeiter.txt
     * y se guardan en una lista , esto para despues extraer valores independientes
     * @return una lista con todos los empleados registrados en el sistema que ya estan en mitarbeiter.txt
     * </pre>
     */
    public static List<String> lessenDatei(){
        List<String> alleZeilen = new ArrayList<>();
     try (BufferedReader reader = new BufferedReader(new FileReader("mitarbeiterliste.txt" , StandardCharsets.UTF_8))){
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
    // el metodo devuelve un valor id, el ultimo de la lista alleZeilen
    //     * zeile.indexOf("idPerson=") busca en la linea (zeile) todos los que contienen idPerson= y donde empietza la palabra
    //     * contar 9 para que desde alli empiece a localizar idPerson=, termina cuando empieza una coma
    //     * if(end ==-1) si no encuentra alfinal una coma , entonces buscar hasta el siguiente sierre de llaves
    /** <pre>
     *
     * Die Methode gibt einen ID-Wert zurück, nämlich den letzten aus der Liste alleZeilen.
     * zeile.indexOf("idPerson=") sucht in der Zeile (zeile) nach allen Vorkommen von "idPerson="
     * und ermittelt, wo das Wort beginnt.
     * Es wird +9 gezählt, damit ab diesem Punkt die ID von "idPerson=" gefunden wird.
     * Das Ende wird durch das nächste Komma markiert.
     * if(end ==-1) wenn kein komma da ist, dann bis zur schliessenden Klammer '}' suchen
     * </pre>
     * */
    // ya no se usa agrege la extraccionn del ultimo id en ladenMitarbeiterAusMitarbeiterliste() , dejo el metodo por si necesite algo parecido despues
    public static int letzteidExtrahieren(List<String> alleZeilen){
        int letzteid = -1;
        for (String zeile : alleZeilen) { //por cada zeile de alleZeilen
            if (zeile.contains("idPerson=")){
                int start = zeile.indexOf("idPerson=") + 9;
                int end = zeile.indexOf(",", start);
                if (end ==-1){
                    end = zeile.indexOf("}", start);
                }
                String teil = zeile.substring(start, end).trim();
                letzteid = Integer.parseInt(teil);

            }


        }
        return letzteid;
    }


}
