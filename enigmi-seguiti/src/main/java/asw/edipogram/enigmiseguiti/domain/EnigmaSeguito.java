package asw.edipogram.enigmiseguiti.domain;

import lombok.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "enigmi_seguiti")
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Immutable
public class EnigmaSeguito {

	@EmbeddedId
	private EnigmaSeguitoKey userInGroupKey = new EnigmaSeguitoKey();

	@Column(name = "utente", nullable = false, insertable = false, updatable = false)
	@NonNull
	@EqualsAndHashCode.Include
	private String utente;

	@Column(name = "id_enigma", nullable = false, insertable = false, updatable = false)
	@NonNull
	@EqualsAndHashCode.Include
	private Long idEnigma;

	@Column(name = "autore_enigma", nullable = false)
	@NonNull
	private String autoreEnigma;

	@Column(name = "tipo_enigma", nullable = false)
	@NonNull
	private String tipoEnigma;

	@Column(name = "tipo_specifico_enigma", nullable = false)
	@NonNull
	private String tipoSpecificoEnigma;

	@Column(name = "titolo_enigma", nullable = false)
	@NonNull
	private String titoloEnigma;

	@Column(name = "testo_enigma", nullable = false, columnDefinition = "text[]")
	@NonNull
	@Type(type = "asw.edipogram.enigmiseguiti.domain.StringArrayType")
	private String[] testoEnigma;

	public EnigmaSeguito(@NonNull String utente, @NonNull Long idEnigma, @NonNull String autoreEnigma,
						 @NonNull String tipoEnigma, @NonNull String tipoSpecificoEnigma,
						 @NonNull String titoloEnigma, @NonNull String[] testoEnigma) {
		this();
		this.utente = utente;
		this.idEnigma = idEnigma;
		this.autoreEnigma = autoreEnigma;
		this.tipoEnigma = tipoEnigma;
		this.tipoSpecificoEnigma = tipoSpecificoEnigma;
		this.titoloEnigma = titoloEnigma;
		this.testoEnigma = testoEnigma;
	}

	@Data
	@NoArgsConstructor
	@Embeddable
	public static class EnigmaSeguitoKey implements Serializable {
		@Column(name = "utente")
		private String utente;

		@Column(name = "id_enigma")
		private Long idEnigma;
	}
}
