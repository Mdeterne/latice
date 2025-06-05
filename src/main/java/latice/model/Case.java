package latice.model;

import latice.util.exception.CaseInaccessibleException;

public class Case {
	private Position position;
	private Tuile tuile;
	private boolean estSoleil;
	private boolean estLune;

	public Case(Position position, boolean estSoleil, boolean estLune) {
		this.estSoleil = estSoleil;
		this.estLune = estLune;
		this.position = position;
		this.tuile = null;
	}

	public boolean estVide() {
		return tuile == null;
	}

	public void posertuile(Tuile tuile) throws CaseInaccessibleException {
		if (this.tuile == null) {
			this.tuile = tuile;
		} else {
			throw new CaseInaccessibleException("la case est inaccessible");
		}

	}

	public Tuile getTuile() {
		return tuile;
	}

	public boolean estSoleil() {
		return estSoleil;
	}

	public boolean estLune() {
		return estLune;
	}

	public Position getPosition() {
		return position;
	}
}