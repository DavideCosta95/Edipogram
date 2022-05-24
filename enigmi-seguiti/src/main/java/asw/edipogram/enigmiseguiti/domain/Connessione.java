package asw.edipogram.enigmiseguiti.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

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
