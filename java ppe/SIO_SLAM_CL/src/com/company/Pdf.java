package com.company;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfDocument;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Pdf {

    private static int idPdf = 0;
    private String nomPdf;
    private Document document = new Document();

    public Pdf(String unNom, ArrayList<BateauVoyageur> listBatVoy) throws FileNotFoundException, DocumentException {
        this.nomPdf = unNom;
        idPdf += 1;

        try{
            FileOutputStream output = new FileOutputStream(this.idPdf + "_" + unNom + ".pdf");
            PdfWriter.getInstance(document, output);
            ecrire_texte(listBatVoy);

            JOptionPane.showMessageDialog(null, "PDF créé avec succès !");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ecrire_texte( ArrayList<BateauVoyageur> listBatVoy) throws DocumentException, IOException {
        document.open();
        for(BateauVoyageur bat : listBatVoy){
            charger_image(bat.getImage());
            document.add(new Paragraph(bat.toString() + "\n"));
        }

        document.close();
    }

    private void charger_image(String lien) throws DocumentException, IOException {
        String imageFile = lien;
        Image image = Image.getInstance(lien);
        document.add(image);
    }

}
