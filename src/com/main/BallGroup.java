package com.main;

public class BallGroup {

	private Ball[] balls;
	private String group;

	public BallGroup(String group) {
		this.setBalls(new Ball[4]);
		this.group = group;
	}
	
	public Ball[] getBalls() {
		return balls;
	}
	
	
	public void setBalls(Ball[] balls) {
		this.balls = balls;
	}

	
	public void init() {
		if("a".equalsIgnoreCase(this.group)) {
			this.getBalls()[0] = new Ball(1,0);
			this.getBalls()[1] = new Ball(2,0);
			this.getBalls()[2] = new Ball(3,0);
			this.getBalls()[3] = new Ball(4,0);
		}
		else if("b".equalsIgnoreCase(this.group)) {
			this.getBalls()[0] = new Ball(5,0);
			this.getBalls()[1] = new Ball(6,0);
			this.getBalls()[2] = new Ball(7,0);
			this.getBalls()[3] = new Ball(8,0);
		}else if("c".equalsIgnoreCase(this.group)) {
			this.getBalls()[0] = new Ball(9,0);
			this.getBalls()[1] = new Ball(10,0);
			this.getBalls()[2] = new Ball(11,0);
			this.getBalls()[3] = new Ball(12,0);
		}
	}
	
	public static void setOddIndex(BallGroup group, int oddIndex) {
		for(int i=0; i<4; i++) {
			if(oddIndex==group.getBalls()[i].getIndex()) {
				group.getBalls()[i].setWeight(1);
			}
		}
	}

}
