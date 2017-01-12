package builder;

class HotDryNoodlesWithBuilder {

	private boolean addShallot; 	// 葱花
	private boolean addParsley; 	// 香菜
	private boolean addChili; 		// 辣椒
	private boolean addSaurekrautt; // 酸菜
	
	public HotDryNoodlesWithBuilder() {
		this(new Builder());
	}
	
	public HotDryNoodlesWithBuilder(Builder builder) {
		addShallot = builder.addShallot;
		addParsley = builder.addParsley;
		addChili = builder.addChili;
		addSaurekrautt = builder.addSaurekrautt;
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
	
	
	// 静态内部类，提高内聚
	public static class Builder {
		private boolean addShallot; 	// 葱花
		private boolean addParsley; 	// 香菜
		private boolean addChili; 		// 辣椒
		private boolean addSaurekrautt; // 酸菜
		
		public Builder() {
	
		}
		
		public Builder addShallot() {
			addShallot = true;
			return this;
		}
		
		public Builder addParsley() {
			addParsley = true;
			return this;
		}
		
		public Builder addChili() {
			addChili = true;
			return this;
		}
		
		public Builder addSaurekrautt() {
			addSaurekrautt = true;
			return this;
		}
		
		public HotDryNoodlesWithBuilder build() {
			return new HotDryNoodlesWithBuilder(this);
		}
	}
}
