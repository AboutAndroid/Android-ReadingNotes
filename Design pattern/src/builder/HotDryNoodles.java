package builder;

class HotDryNoodles {

	private boolean addShallot; 	// 葱花
	private boolean addParsley; 	// 香菜
	private boolean addChili; 		// 辣椒
	private boolean addSaurekrautt; // 酸菜
	
	
	/**
	 * 制作热干面
	 * @param addShallot  葱花
	 * @param addParsley  香菜
	 * @param addChili    辣椒
	 * @param addSaurekrautt  酸菜
	 */
	public HotDryNoodles(boolean addShallot, boolean addParsley,
			boolean addChili, boolean addSaurekrautt) {
		this.addShallot = addShallot;
		this.addParsley = addParsley;
		this.addChili = addChili;
		this.addSaurekrautt = addSaurekrautt;
	}

	@Override
	public String toString() {
		StringBuilder sbr = new StringBuilder();
		if(addShallot) {
			sbr.append("葱花.");
		}
		
		if(addParsley) {
			sbr.append("香菜.");
		}
		
		if(addChili) {
			sbr.append("辣椒.");
		}
		
		if(addSaurekrautt) {
			sbr.append("酸菜.");
		}	
		return sbr.toString();
	}
}
