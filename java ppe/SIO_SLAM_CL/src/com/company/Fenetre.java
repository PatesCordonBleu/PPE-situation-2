package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;

import com.company.bdd.Gestionnaire;
import com.itextpdf.text.DocumentException;

public class Fenetre extends JFrame implements ActionListener {

    private TextField titre = new TextField();
    private TextField nomPdf = new TextField();
    private JLabel TitreBatChoix = new JLabel();
    private JLabel TitrePage = new JLabel();
    private JComboBox selecBat = new JComboBox();
    private JComboBox BatSelectionne = new JComboBox();
    private JButton Ajouter = new JButton();
    private JButton Supprimer = new JButton();
    private JButton Generer = new JButton();
    private Gestionnaire bdd = new Gestionnaire();
    private JTextArea infoBat = new JTextArea();
    private BateauVoyageur batVoy;
    private ArrayList<BateauVoyageur> lesBatSelect = new ArrayList<BateauVoyageur>();

    //lance l'affichage de la fenêtre et des composants graphiques
    Fenetre(){
        try{
            FenInit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void FenInit(){
        this.getContentPane().setLayout(null);

        this.pack();
        this.setVisible(true);
        this.setSize(800,600);
        this.setTitle("MarieTeam");

        titre.setBounds(350, 100, 100, 20);
        titre.setText("MarieTeam");

        nomPdf.setBounds(1100,450,300, 40);
        nomPdf.setText("Veuillez saisir le nom du pdf");
        this.add(nomPdf);

        TitrePage.setBounds(740,100,150, 50);
        TitrePage.setText("MARIETEAM");
        this.add(TitrePage);

        selecBat.setBounds(200,400,300, 40);
        this.add(selecBat);
        initListBat();

        Ajouter.setBounds(300, 700, 150, 50);
        Ajouter.setText("AJOUTER");
        Supprimer.setBounds(700, 700, 150, 50);
        Supprimer.setText("SUPPRIMER");
        Generer.setBounds(1100, 700, 150, 50);
        Generer.setText("GENERER");
        this.add(Generer);
        this.add(Supprimer);
        this.add(Ajouter);

        infoBat.setBounds(600, 300, 350,225);
        infoBat.setEditable(false);

        TitreBatChoix.setBounds(1100,300,300, 40);
        TitreBatChoix.setText("Voici les bateaux que vous avez choisi :");
        this.add(TitreBatChoix);

        BatSelectionne.setBounds(1100,340,300, 40);
        this.add(BatSelectionne);



        selecBat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bdd.selectBateauVoyageur(selecBat.getSelectedItem().toString());
                batVoy = bdd.getBateauvoyageur();
                infoBat.setText(getBatVoy().toString());
            }
        });

        this.add(infoBat);

        Ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selecBat.getSelectedItem() != "") {
                    bdd.selectBateauVoyageur(selecBat.getSelectedItem().toString());
                    batVoy = bdd.getBateauvoyageur();
                    lesBatSelect.add(batVoy); //Ajoute le bateau sélectionné à la liste des bateaux à inscrire dans le pdf
                    BatSelectionne.addItem(batVoy.getNomBat());
                    selecBat.removeItem(selecBat.getSelectedItem()); //Enlève le bateau sélectionné de la liste des bateaux sélectionnables
                }
                else {
                    JOptionPane.showMessageDialog(null, "Erreur : objet sélectionné invalide");

                }
            }

        });

        Supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(BatSelectionne.getSelectedItem() != ""){
                    bdd.selectBateauVoyageur(BatSelectionne.getSelectedItem().toString());
                    batVoy = bdd.getBateauvoyageur();
                    selecBat.addItem(batVoy.getNomBat());
                    lesBatSelect.remove(batVoy); //Enlève le bateau sélectionné à la liste des bateaux à inscrire dans le pdf
                    BatSelectionne.removeItem(BatSelectionne.getSelectedItem()); //Enlève le bateau sélectionné de la liste des bateaux sélectionnés
                }
                else {
                    JOptionPane.showMessageDialog(null, "Erreur : objet sélectionné invalide");
                }
            }
        });

        Generer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lesBatSelect.size() != 0 && !(nomPdf.getText().equalsIgnoreCase("Veuillez saisir le nom du pdf"))){
                    try {
                        Pdf lePdf = new Pdf(nomPdf.getText().toLowerCase(Locale.ROOT), lesBatSelect);
                        cleanSelect();
                    } catch (FileNotFoundException | DocumentException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                }

            }
        });

    }

    private void initListBat(){
        //Initialise la liste des bateaux disponibles dans la comboBox selecBat
        selecBat.addItem("");
        BatSelectionne.addItem("");
        Gestionnaire forBatList = new Gestionnaire();
        this.bdd.selectListeBateaux();
        for(Bateau unBateau : this.bdd.getBatList()) {
            selecBat.addItem(unBateau.getNomBat());
        }
    }

    public BateauVoyageur getBatVoy(){
        return this.batVoy;
    }

    public void cleanSelect(){
        for(Bateau leBat : lesBatSelect){
            selecBat.addItem(leBat.getNomBat());
            BatSelectionne.removeItem(leBat.getNomBat());
        }
        lesBatSelect.clear();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
