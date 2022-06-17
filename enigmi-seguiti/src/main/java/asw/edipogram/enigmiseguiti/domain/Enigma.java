package asw.edipogram.enigmiseguiti.domain;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/* Enigma, in formato completo. */ 
@Entity(name = "enigmi")
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Enigma implements Comparable<Enigma> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name="id", nullable = false)
	@NonNull
	private Long id;

	@Column(name="autore", nullable = false)
	@NonNull
	private String autore;

	@Column(name="tipo", nullable = false)
	@NonNull
	private String tipo;

	@Column(name="tipo_specifico", nullable = false)
	@NonNull
	private String tipoSpecifico;

	@Column(name="titolo", nullable = false)
	@NonNull
	private String titolo;

	@Column(name = "testo", nullable = false, columnDefinition = "text[]")
	@NonNull
	@Type(type = "asw.edipogram.enigmiseguiti.domain.StringArrayType")
	private String[] testo;

	public Enigma(@NonNull String autore, @NonNull String tipo, @NonNull String tipoSpecifico, @NonNull String titolo, @NonNull String[] testo) {
		this();
		this.autore = autore;
		this.tipo = tipo;
		this.tipoSpecifico = tipoSpecifico;
		this.titolo = titolo;
		this.testo = testo;
	}

	@Override
	public int compareTo(Enigma other) {
		return this.id.compareTo(other.id); 
	}

}
