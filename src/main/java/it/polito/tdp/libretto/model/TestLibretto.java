package it.polito.tdp.libretto.model;

import java.time.LocalDate;

public class TestLibretto {
	Libretto lib;
	
	private void run() {
		this.lib=new Libretto(); //crea libretto vuoto
		Voto v1= new Voto("Tecniche di programmazione",30,LocalDate.of(2020, 06, 15));
		Voto v2= new Voto("Analisi II",28,LocalDate.of(2020, 06, 28));
		lib.add(v1);
		lib.add(v2);
		lib.add(new Voto("Economia",24, LocalDate.of(2020, 02, 14)));
		lib.add(new Voto("Chimica",19,LocalDate.now()));
		System.out.println(this.lib);
		System.out.println(this.lib.stampaVotiUguali(28));
		System.out.println(this.lib.estraiVotiUguali(28));
		String nomeCorso= "Analisi II";
		System.out.println(lib.cercaNomeCorso(nomeCorso));
		Voto economia2=new Voto("Economia",24,LocalDate.now());
		Voto economia3=new Voto("Economia",21,LocalDate.now());
		System.out.println("Economia con 24 è duplicato: "+ lib.isDuplicato(economia2)+" / conflitto: "+ lib.isConflitto(economia2));
		System.out.println("Economia con 21 è duplicato: "+ lib.isDuplicato(economia3)+" / conflitto: "+ lib.isConflitto(economia3));
		Libretto migliorato=lib.creaLibrettoMigliorato();
		System.out.println(lib+"\n");
		System.out.println(migliorato);
		Libretto alfabetico=new Libretto(lib);
		alfabetico.ordinaPerCorso();
		System.out.println(alfabetico);
		Libretto valutazione=new Libretto(lib);
		valutazione.ordinaPerVoto();
		System.out.println(valutazione);
		lib.ordinaPerCorso();
		lib.cancellaVotiScarsi();
		System.out.println(lib);
		
	}

	public static void main(String[] args) {
		TestLibretto test= new TestLibretto();
		test.run();
	}

}
