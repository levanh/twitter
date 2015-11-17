package bayes;

public class BayesResult {
	private float probPos;
	private float probNeu;
	private float probNeg;
	
	public BayesResult() {
		this.probPos = -1;
		this.probNeu = -1;
		this.probNeg = -1;
	}

	public float getProbPos() {
		return probPos;
	}

	public void setProbPos(float probPos) {
		this.probPos = probPos;
	}

	public float getProbNeu() {
		return probNeu;
	}

	public void setProbNeu(float probNeu) {
		this.probNeu = probNeu;
	}

	public float getProbNeg() {
		return probNeg;
	}

	public void setProbNeg(float probNeg) {
		this.probNeg = probNeg;
	}
	
	
	
}
