package model;

import java.util.Scanner;

public class Kasse {
    private double stundenTarif;

    public Kasse(double stundenTarif) {
        this.stundenTarif = stundenTarif;
    }

    public double berechneParkgebuehr(Auto auto) {
        long parkdauerInMillisekunden = System.currentTimeMillis() - auto.getEinfahrtszeit();
        double parkdauerInStunden = parkdauerInMillisekunden / (1000.0 * 60 * 60);
        return Math.ceil(parkdauerInStunden) * stundenTarif;
    }

    public void kassiere(Auto auto) {
        double gebuehr = berechneParkgebuehr(auto);
        System.out.println("Bitte zahlen Sie " + gebuehr + " CHF.\nBitte geben Sie den Betrag ein:");

        Scanner sc = new Scanner(System.in);
        double betrag;
        boolean correctPayment = false;

        while (!correctPayment) {
            if (sc.hasNextDouble()) {
                betrag = sc.nextDouble();
                if (betrag == gebuehr) {
                    auto.setBezahlt(true);
                    System.out.println("Vielen Dank für Ihre Zahlung!");
                    correctPayment = true;
                } else {
                    System.out.println("Der Betrag stimmt nicht. Bitte versuchen Sie es erneut.");
                }
            } else {
                System.out.println("Ungültiger Betrag. Bitte geben Sie eine Zahl ein.");
                sc.next();
            }
        }
    }
}
