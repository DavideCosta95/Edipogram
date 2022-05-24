package asw.edipogram.connessioni.domain;

import javax.persistence.*; 

import lombok.*; 

@Entity(name = "connessioni")
@Data
@NoArgsConstructor
public class Connessione {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	@NonNull
	private Long id;

	@Column(name="utente", nullable = false)
	@NonNull
	private String utente;

	@Column(name="tipo", nullable = false)
	@NonNull
	private String tipo;
	
	public Connessione(@NonNull String utente, @NonNull String tipo) {
		this(); 
		this.utente = utente; 
		this.tipo = tipo; 
	}
}
