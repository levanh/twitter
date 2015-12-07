package bayes;

public class BayesResult {
	private double probPos;
	private double probNeu;
	private double probNeg;
	
	public BayesResult() {
		this.probPos = -1;
		this.probNeu = -1;
		this.probNeg = -1;
	}
	
	public int noteResult(){
		if (probPos > probNeu){
			if (probPos > probNeg)
				return 4;
			else
				return 0;		
		}
		else{
			if (probNeu > probNeg)
				return 2;
			else
				return 0;
		}
	}

	public double getProbPos() {
		return probPos;
	}

	public void setProbPos(double probPos) {
		this.probPos = probPos;
	}

	public double getProbNeu() {
		return probNeu;
	}

	public void setProbNeu(double probNeu) {
		this.probNeu = probNeu;
	}

	public double getProbNeg() {
		return probNeg;
	}

	public void setProbNeg(double probNeg) {
		this.probNeg = probNeg;
	}
	
	
	
}
