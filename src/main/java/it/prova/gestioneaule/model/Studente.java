package it.prova.gestioneaule.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "studente")
public class Studente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cognome")
	private String cognome;

	@Column(name = "data_nascita")
	private Date dataNascita;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "municipio_id")
	private AulaScolastica aulaScolastica;

	public Studente() {
	}

	public Studente(String nome, String cognome, Date dataNascita, AulaScolastica aulaScolastica) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.aulaScolastica = aulaScolastica;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public AulaScolastica getAulaScolastica() {
		return aulaScolastica;
	}

	public void setAulaScolastica(AulaScolastica aulaScolastica) {
		this.aulaScolastica = aulaScolastica;
	}

	@Override
	public String toString() {
		return "Studente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita=" + dataNascita + "]";
//				+ ", aulaScolastica=" + aulaScolastica + "]";
	}

}
