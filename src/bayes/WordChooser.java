package bayes;

public enum WordChooser {
	ALL(0), NO2(2), NO3(3);
	
	private final int maxLength;
	
	WordChooser(int l){
		this.maxLength = l;
	}
	
	public int getMaxLength(){
		return this.maxLength;
	}
}
