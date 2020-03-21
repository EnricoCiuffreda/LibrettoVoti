package it.polito.tdp.libretto.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * memorizza e gestisce un insieme di voti separati
 * @author Enrico
 *
 */
public class Libretto {
	
	private List<Voto> voti= new ArrayList <>();
	
	
	
	/**
	 * 
	 * copy Constructor
	 * @param lib
	 */
	public Libretto (Libretto lib) {
		this.voti.addAll(lib.voti);
	}
	
	public Libretto() {
	
	}

	/**
	 * aggiunge un nuovo voto al libretto
	 * 
	 * @param v Voto da aggiungere
	 */
	public boolean add(Voto v) {
		if(this.isConflitto(v) || this.isDuplicato(v)){
			return false;
		}
		else {
		this.voti.add(v);
		return true;
		}
	}
	/**
	 * dato un Libretto, restituisce una stringa nella quale
	 * vi sono solamente i voti pari al valore specificato
	 * @param voto
	 * @return
	 */
	public String stampaVotiUguali(int voto) {
		String s="";
		for(Voto v: this.voti) {
			if(v.getVoto()==voto) {
			s+=v.toString()+"\n";
			}
		}
		return s;
	}
	
	
	/**
	 * Genera un nuovo libretto, apartire da quello esistente,
	 * che conterrà esclusivamente i voti con votazione pari a quella specificata.
	 * @param voto votazione specificata
	 * @return nuovo Libretto "ridotto"
	 */
	public Libretto estraiVotiUguali(int voto) {
		Libretto nuovo= new Libretto();
		for(Voto v: this.voti) {
			if(v.getVoto()==voto) {
				nuovo.add(v);
			}
		}
		return nuovo;
	}
	
	public String toString() {
		String s="";
		for(Voto v: this.voti) {
			s+=v.toString()+"\n";
		}
		return s;
	}
	/**
	 * dato il nome del corso ricerca se quell'esame esiste.
	 * @param nomeCorso
	 * @return oggetto Voto corrispondente
	 */
	public Voto cercaNomeCorso(String nomeCorso) {
		/*for(Voto v: this.voti)
		{
			if(v.getCorso().equals(nomeCorso))
				return v;
		}
		return null;
		*/
		int pos=this.voti.indexOf(new Voto(nomeCorso,0,null));
		if(pos!= -1)
		{
		return this.voti.get(pos);
		}
		else {
			return null;
		}
	}
	/**
	 * ritorna {@code true} se corso specificato esiste con la stessa valutazione
	 * false se non esiste o valutazione diversa
	 * @param v
	 * @return
	 */
	public boolean isDuplicato(Voto v) {
		Voto esiste=this.cercaNomeCorso(v.getCorso());
		if(esiste==null) {
			return false;
		}
			return (esiste.getVoto()==v.getVoto());
	}
	
	public boolean isConflitto(Voto v) {
		Voto esiste=this.cercaNomeCorso(v.getCorso());
		if(esiste==null) {
			return false;
		}
		return(esiste.getVoto()!= v.getVoto());
	}
	
	/**
	 * 
	 * Restituisce un nuovo libretto migliorando i voti del libretto attuale
	 * @return
	 */
	public Libretto creaLibrettoMigliorato() {
		Libretto nuovo =new Libretto();
		
		for( Voto v: this.voti) {
			Voto v2=new Voto(v);
			if(v2.getVoto()>=24) {
			v2.setVoto(v2.getVoto()+2);
			if(v2.getVoto()>30)
			{
				v2.setVoto(30);
			}
			}
			else if(v2.getVoto()>=18) {
				v2.setVoto(v2.getVoto()+1);
			}
			nuovo.add(v2);
		}
		return nuovo;
	}
	
	public void ordinaPerCorso() {
		Collections.sort(this.voti);
	}
	
	public void ordinaPerVoto() {
		Collections.sort(this.voti,new ComparatorePerVoti());
	}
	
	public void cancellaVotiScarsi() {
		List <Voto> daRimuovere=new ArrayList<>();
		for(Voto v:this.voti) {
			if(v.getVoto()<24) {
				daRimuovere.add(v);
			}
		}
		this.voti.removeAll(daRimuovere);
	}

}
