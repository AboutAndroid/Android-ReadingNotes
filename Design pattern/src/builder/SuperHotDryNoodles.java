package builder;

class SuperHotDryNoodles {

	private boolean addShallot; 	// 葱花
	private boolean addParsley; 	// 香菜
	private boolean addChili; 		// 辣椒
	private boolean addSaurekrautt; // 酸菜

	@Override
	public String toString() {
		StringBuilder sbr = new StringBuilder();
		if (addShallot) {
			sbr.append("葱花.");
		}

		if (addParsley) {
			sbr.append("香菜.");
		}

		if (addChili) {
			sbr.append("辣椒.");
		}

		if (addSaurekrautt) {
			sbr.append("酸菜.");
		}
		return sbr.toString();
	}

	public SuperHotDryNoodles addShallot() {
		addShallot = true;
		return this;
	}

	public SuperHotDryNoodles addParsley() {
		addParsley = true;
		return this;
	}

	public SuperHotDryNoodles addChili() {
		addChili = true;
		return this;
	}

	public SuperHotDryNoodles addSaurekrautt() {
		addSaurekrautt = true;
		return this;
	}

}
