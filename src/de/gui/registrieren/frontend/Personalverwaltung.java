package de.gui.registrieren.frontend;

import de.personalabteilung.arbeitgeber.Unternehmen;
import de.personalabteilung.arbeitsdatenEnum.Abteilung;
import de.personalabteilung.arbeitsdatenEnum.PositionImAbteilung;
import de.personalabteilung.arbeitsdatenEnum.Statuss;
import de.personalabteilung.arbeitsdatenEnum.Vertragsart;
import de.personalabteilung.datei.ArbeitnehmerPersistens;
import de.personalabteilung.mitarbeiter.Arbeitnehmer;
import de.personalabteilung.service.ArbeitnehmerService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.time.LocalDate;
import java.util.List;

/**
 * <pre>
 *    wird direkt aus der Datei mitarbeiterliste.txt gelesen,
 *    mit ArbeitnehmerPersistens, ArbeitnehmerService, und Unternehmen
 *
 * Willkommensbildschirm.
 * Schaltflächen: Neuen Mitarbeiter registrieren, Mitarbeiterliste anzeigen, Änderungen speichern, Beenden.
 * Integration mit txt-Dateien.
 * </pre>
 */

public class Personalverwaltung extends Application {

    private Stage primaryStage; //Stage escenario // primaryStage ventana principal
    private Unternehmen unternehmen;
    private ArbeitnehmerService arbeitnehmerService;
   // private VBox mainLayout;

    @Override
        public void start(Stage primaryStage) { // comienza la interfaz
        //this.startScene=startScene;  // guardara la escena inicial en clase principal
        this.primaryStage=primaryStage;  // se guarda para usarlo en otros metodos al cambiar de escena
        primaryStage.setTitle("Personalverwaltung"); // nombre a la interfaz

        /** Unternehmen und Dienstleistungen*/
        unternehmen = new Unternehmen("Personalverwaltung-Airline-Catering-Service");
        arbeitnehmerService = new ArbeitnehmerService(); // direkt aus Datei laden
        //--------------------Layoout -hauptsächlich---------------

        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.CENTER);

        //----------------------ein Hintergrundbild hinzufügen--------

        Image image = new Image("file:resources/10460.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(
                image, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO, false, false, true, true)
        );
        mainLayout.setBackground(new Background(backgroundImage));


                //--------Willkommensetikett-----------------
        Label begruessung =new Label("Willkommen bei Catering Iberia ");  //etiqueta de bienvenida con estilo CSS
        begruessung.setStyle("-fx-font-family: \"Playwrite CA\", cursive;-fx-font-size:45;"); // bienvenida letra
        VBox.setMargin(begruessung,new Insets(0,0,200,0));
        //----------------------Schaltflaechen----------------------------
        Button  btnNeuerMitarbeiter = new Button ("Mitarbeiter registrieren"); // se crean tres botones
        Button  btnListeMitarbeiter  = new Button ("Liste Mitarbeiter");
        Button  btnSpeichern = new Button("Speichern");
        Button  btnExit = new Button("Exit");
        btnExit.setOnAction(e->primaryStage.close());

        //------------Anpassen der Buttonsgröße----------
        btnNeuerMitarbeiter.setPrefWidth(250);
        btnNeuerMitarbeiter.setPrefHeight(50);

        btnListeMitarbeiter.setPrefWidth(250);
        btnListeMitarbeiter.setPrefHeight(50);

        btnSpeichern.setPrefWidth(250);
        btnSpeichern.setPrefHeight(50);

        btnExit.setPrefWidth(250);
        btnExit.setPrefHeight(50);

        //----------Stil, Schriftgröße von------------------
        btnNeuerMitarbeiter.setStyle("-fx-font-size:20px;");
        btnListeMitarbeiter.setStyle("-fx-font-size:20px;");
        btnSpeichern.setStyle("-fx-font-size:20px;");
        btnExit.setStyle("-fx-font-size:20px;");


        //-----------------Schaltflächenbehälter------------------
        HBox buttonBox = new HBox(20);//Hbox horizontal coloca botones juntos con separacion de 20px
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonBox.getChildren().addAll(btnNeuerMitarbeiter, btnListeMitarbeiter, btnSpeichern, btnExit);

        mainLayout.getChildren().addAll(begruessung, buttonBox);

        //-----------------Schaltflächenereignis----------------------

        btnNeuerMitarbeiter.setOnAction(event -> mitarbeiterRegistrierenbtn());
        btnListeMitarbeiter.setOnAction(event -> listeMitarbeiterbtn());
        btnSpeichern.setOnAction(event -> aenderungenSpeichernbtn());

        //------------Hauptszene---------------

        Scene einstiegsSzene = new Scene(mainLayout, 1300,1000); // espacio organizacion de toda la ventana , pero falta una ventana encima
        primaryStage.setScene(einstiegsSzene);//se aplican estilos CSS (hoja local bananastyle.css)// se carga en primaryStage
        primaryStage.show();

        // fuentes y estilos
        einstiegsSzene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Playwrite+CA:wght@100..400&display=swap"); // letra la bienvenida

    }

    //______________________Szenen Methoden ___________________


    public void mitarbeiterRegistrierenbtn() {
        Stage stage = new Stage();
        stage.setTitle("Neuen Mitarbeiter registrieren");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(50));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        // Grundfelder
        TextField tfVorname = new TextField();
        TextField tfNachname = new TextField();
        TextField tfEmail = new TextField();
        TextField tfAusweisnummer = new TextField();
        TextField tfTelefonnummer = new TextField();
        DatePicker dpGeburtsdatum = new DatePicker();
        TextField tfAdresse = new TextField();
        TextField tfGeburtsort = new TextField();
        TextField tfStaatsangehoerigkeit = new TextField();
        TextField tfFamilienstand = new TextField();
        TextField tfKinderAnzahl = new TextField();
        //Enum-Felder
        ComboBox<Vertragsart> vertragsartComboBox = new ComboBox<>();
        vertragsartComboBox.getItems().addAll(Vertragsart.values());

        ComboBox<Abteilung> abteilungComboBox = new ComboBox<>();
        abteilungComboBox.getItems().addAll(Abteilung.values());

        ComboBox<PositionImAbteilung> positionImAbteilungComboBox = new ComboBox<>();
        positionImAbteilungComboBox.getItems().addAll(PositionImAbteilung.values());

        ComboBox<Statuss> statussComboBox = new ComboBox<>();
        statussComboBox.getItems().addAll(Statuss.values());

        gridPane.add(new Label("Vorname"),0,0);
        gridPane.add(tfVorname,1,0);
        gridPane.add(new Label("Nachname"),0,1);
        gridPane.add(tfNachname,1,1);
        gridPane.add(new Label("Email"),0,2);
        gridPane.add(tfEmail,1,2);
        gridPane.add(new Label("Ausweisnummer"),0,3);
        gridPane.add(tfAusweisnummer,1,3);
        gridPane.add(new Label("Telefonnummer"),0,4);
        gridPane.add(tfTelefonnummer,1,4);
        gridPane.add(new Label("Geburtsdatum"),0,5);
        gridPane.add(dpGeburtsdatum,1,5);
        gridPane.add(new Label("Adresse"),0,6);
        gridPane.add(tfAdresse,1,6);
        gridPane.add(new Label("Geburtsort"),0,7);
        gridPane.add(tfGeburtsort,1,7);
        gridPane.add(new Label("Staatsangehoerigkeit"),0,8);
        gridPane.add(tfStaatsangehoerigkeit,1,8);
        gridPane.add(new Label("Familienstand"),0,9);
        gridPane.add(tfFamilienstand,1,9);
        gridPane.add(new Label("KinderAnzahl"),0,10);
        gridPane.add(tfKinderAnzahl,1,10);

        // Enum Felder ins Grid
        gridPane.add(new Label("Vertragsart"),0,11);
        gridPane.add(vertragsartComboBox,1,11);
        gridPane.add(new Label("Abteilung"),0,12);
        gridPane.add(abteilungComboBox,1,12);
        gridPane.add(new Label("Position"),0,13);
        gridPane.add(positionImAbteilungComboBox,1,13);
        gridPane.add(new Label("Statuss"),0,14);
        gridPane.add(statussComboBox,1,14);

        Button btnSpeichern = new Button("Speichern");
        gridPane.add(btnSpeichern, 1,15);

        btnSpeichern.setOnAction(event ->{
            //Normalisieren von Eingaben
            String vorN=tfVorname.getText()==null ? " ": tfVorname.getText().trim();
            String nachN=tfNachname.getText()==null ? " ": tfNachname.getText().trim();
            String email=tfEmail.getText()==null ? " ": tfEmail.getText().trim();
            String ausweis=tfAusweisnummer.getText()==null ? " ": tfAusweisnummer.getText().trim();

            //sichere ID generieren
            int neueId=unternehmen.getNextId();

            //wird der Liste im Speicher hinzugefügt

            /**Entstehung des neuen Arbeitnehmers (Gedenkobjekt) */
            Arbeitnehmer mitarbeiter = new Arbeitnehmer(
                    neueId,
                    vorN,
                    nachN,
                    email,
                    ausweis,
                    tfTelefonnummer.getText(),
                    dpGeburtsdatum.getValue()!= null? dpGeburtsdatum.getValue(): LocalDate.now(),
                    tfAdresse.getText(),
                    tfGeburtsort.getText(),
                    tfStaatsangehoerigkeit.getText(),
                    tfFamilienstand.getText(),
                    tfKinderAnzahl.getText(),
                    vertragsartComboBox.getValue(),
                    abteilungComboBox.getValue(),
                    positionImAbteilungComboBox.getValue(),
                    statussComboBox.getValue()
            );

            /**Aktualisieren Sie den Dienst mit dem aktuellen Status des Unternehmens */
            arbeitnehmerService.setAlleArbeitnehmer(List.copyOf(unternehmen.getArbeitnehmerMap().values()));

            //Validierungen vor dem Erstellen des Objekts
            if (arbeitnehmerService.existsByName(vorN, nachN)){
               new Alert(Alert.AlertType.ERROR,"Es gibt schon eine Person mit derselben Name-Nachname").show();
                return ;
            }
            if (arbeitnehmerService.existsByAusweis(ausweis)){
                new Alert(Alert.AlertType.ERROR,"Es gibt schon eine Person mit derselben Ausweis").show();
                return ;
            }
            if (arbeitnehmerService.existsByEmail(email)){
                new Alert(Alert.AlertType.ERROR,"Es gibt schon eine Person mit derselben Emailadresse").show();
                return ;
            }



                    //im Speicher speichern
                    unternehmen.addMitarbeiter(neueId,mitarbeiter);
                    //wird der Datei hinzugefügt
                    ArbeitnehmerPersistens persistens = new ArbeitnehmerPersistens();
                    persistens.speichernInDatei(mitarbeiter);
                    /// ** Hier pruefen wir mit dem Service gegen die aktuelle Liste*/
                    arbeitnehmerService.setAlleArbeitnehmer(List.copyOf(unternehmen.getArbeitnehmerMap().values()));
                    new Alert(Alert.AlertType.INFORMATION, "Mitarbeiter erfolgreich gespeichert").show();
                    stage.close();
                    });
                    Scene scene = new Scene(gridPane, 1100, 900);
                    stage.setScene(scene);
                    stage.show();
    }


    private void listeMitarbeiterbtn(){
        Stage stage = new Stage();
        stage.setTitle("Liste alle Mitarbeiter");

        VBox layoutVbox = new VBox(10);
        layoutVbox.setPadding(new Insets(20));

        List<Arbeitnehmer> alleArbeitnehmer = arbeitnehmerService.getAlleArbeitnehmer();

        for (Arbeitnehmer arbeitnehmer : alleArbeitnehmer){
            Label label = new Label(arbeitnehmer.getIdPerson()+"-"+arbeitnehmer.getVorname()+ " "+ arbeitnehmer.getNachname()+ "-" + arbeitnehmer.getEmailAdresse()
                +" "+ arbeitnehmer.getAusweisnummer()+"-"+arbeitnehmer.getTelefonnummer()+" "+ arbeitnehmer.getGeburtsdatum()+"-"+arbeitnehmer.getAdresse()+" "
                +arbeitnehmer.getGeburtsort()+"-"+arbeitnehmer.getStaatsangehoerigkeit()+" "+arbeitnehmer.getFamilienstand()+"-"+arbeitnehmer.getKinderAnzahl()+" "
                +arbeitnehmer.getVertragsart()+" "+arbeitnehmer.getAbteilung()+"-"+arbeitnehmer.getPositionImAbteilung()+" "+arbeitnehmer.getStatuss());

            label.setStyle("-fx-font-size:14px;"); // aumenta tamano de letra
            layoutVbox.getChildren().add(label);
        }

        Scene scene = new Scene(layoutVbox, 1100, 900);
        stage.setScene(scene);
        stage.show();
    }

    private void aenderungenSpeichernbtn(){
        try{
            List<Arbeitnehmer> alleArbeitnehmer = List.copyOf(unternehmen.getArbeitnehmerMap().values());
            for (Arbeitnehmer arbeitnehmer: alleArbeitnehmer){
                System.out.println("gespeichert"+arbeitnehmer.toFileString());
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Die Daten korrekt gespeichert wurden");
            alert.show();
        }catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Fehler beim Speichern"+exception.getMessage());
            alert.show();
        }
    }

    @Override
    public void init(){ // ciclo de vida de la aplicacionn init() se ejecuta antes de start()

        System.out.println("Init-Methode: meist Initialisierung von Attributen");
    }

     @Override
     public void stop(){ //se ejecuta al cerrar la aplicacio
            System.out.println("Ordnungsgemaess verschlossen");

     }

     public static void main(String[] args) {
        launch(args);
    }

}
