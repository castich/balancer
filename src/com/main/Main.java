package com.main;

public class Main {
	static BallGroup groupA = new BallGroup("a");
	static BallGroup groupB = new BallGroup("b");
	static BallGroup groupC = new BallGroup("c");
	static boolean balanced = false;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int oddIndex = 4;
		BallGroup aNew = new BallGroup("aNew");
		BallGroup bNew = new BallGroup("bNew");
		
		groupA.init();
		groupB.init();
		groupC.init();
		setOddIndex(oddIndex);
		
		//First scale
		balanced = balance(groupA, groupB, 4);
		System.out.println(balanced);
		if(!balanced) {
			
			Ball aOut = groupA.getBalls()[0]; //Remove ball 1 from group A
			Ball bOut = groupB.getBalls()[0]; //Remove ball 5 from group B
			aNew.setBalls(new Ball[3]);
			bNew.setBalls(new Ball[3]);
			
			//Switch 2 last balls from Group A and B, so that
			//Group A will be 2, 7, 8 and Group B will be 6, 3, 4
			aNew.getBalls()[0]=groupA.getBalls()[1];
			aNew.getBalls()[1]=groupB.getBalls()[2];
			aNew.getBalls()[2]=groupB.getBalls()[3];
			bNew.getBalls()[0]=groupB.getBalls()[1];
			bNew.getBalls()[1]=groupA.getBalls()[2];
			bNew.getBalls()[2]=groupA.getBalls()[3];
			
			//A : 2, 7, 8
			//B : 6, 3, 4
			
			//2nd scale in this scenario
			balanced = balance(aNew, bNew, 3);
			
			if(balanced) {
				// Will assume the odd ball is either Ball 1 or Ball 5
				// Will balance Ball 1 against a normal ball, and will sit Ball 5 out.
				// In this example, I'll use normal Ball 9.
				aNew.setBalls(new Ball[1]);
				bNew.setBalls(new Ball[1]);
				aNew.getBalls()[0] = aOut;
				bNew.getBalls()[0] = groupC.getBalls()[0]; //Ball 9

				// 3rd scale in this scenario
				balanced = balance(aNew, bNew, 1);
				if(balanced) {
					//Odd ball is Ball 5 if Ball 1 balances with Ball 9
					System.out.println("Odd ball is Ball "+ bOut.getIndex()+" weighting "+bOut.getWeight()+".");
				}
				
				else {
					//Odd ball is Ball 1 if it doesn't balance with normal Ball 9
					System.out.println("Odd ball is Ball "+ aNew.getBalls()[0].getIndex()+" weighing "+aNew.getBalls()[0].getWeight());
				}
			}
			
			else {
				//If not balanced, will remove Ball 6 and sit out Ball 7.
				//Retain Balls 2, 3, 8, 4
				Ball ball7 = aNew.getBalls()[1];
				BallGroup temp = new BallGroup("temp");
				temp.setBalls(new Ball[4]);
				temp.getBalls()[0] = aNew.getBalls()[0];
				temp.getBalls()[1] = aNew.getBalls()[2];
				temp.getBalls()[2] = bNew.getBalls()[1];
				temp.getBalls()[3] = bNew.getBalls()[2];
				
				//Move Balls 2 and 3 to A. Move Balls 8 and 4 to B.
				aNew.setBalls(new Ball[2]);
				bNew.setBalls(new Ball[2]);
				
				aNew.getBalls()[0] = temp.getBalls()[0];
				aNew.getBalls()[1] = temp.getBalls()[1];
				bNew.getBalls()[0] = temp.getBalls()[2];
				bNew.getBalls()[1] = temp.getBalls()[3];
				
				//3rd scale in this scenario.
				balanced = balance(aNew, bNew, 2);
				
				if(balanced) {
					//7 is the odd ball.
					System.out.println("Odd ball is Ball "+ ball7.getIndex()+" weighing "+ball7.getWeight());
				}
				
				else {
					System.out.println(aNew.getBalls()[0].isStateChanged());
					System.out.println(aNew.getBalls()[1].isStateChanged());
					System.out.println(bNew.getBalls()[0].isStateChanged());
					System.out.println(bNew.getBalls()[1].isStateChanged());
				}
			}
			
		}
		
		else {
			//Will assume odd ball is in Group C if A and B balanced.
			aNew.setBalls(new Ball[1]);
			bNew.setBalls(new Ball[1]);

			//Get ball 9 and ball 10 to balance
			aNew.getBalls()[0]=groupC.getBalls()[0];
			bNew.getBalls()[0]=groupC.getBalls()[1];

			//Second scale if balanced at first scale
			balanced = balance(aNew, bNew, 1);
			
			if(balanced) {
				//If 9 balances with 10, weigh 9 against 11.
				bNew.getBalls()[0]=groupC.getBalls()[2];
				balanced = balance(aNew, bNew, 1);
				
				//Third scale, if 2nd scale in this scenario balanced.
				if(balanced) {
//					can assume odd ball is ball 12, because 9, 10, and 11 balanced.
					System.out.println("Odd ball is Ball "+ groupC.getBalls()[3].getIndex()+" weighing "+groupC.getBalls()[3].getWeight()+".");
				}
				else {
//					can assume odd ball is ball 11, because it never did not balance with 9 that already balanced earlier with another ball.
					System.out.println("Odd ball is Ball "+ groupC.getBalls()[2].getIndex()+" weighing "+groupC.getBalls()[2].getWeight());
				}
			}
			
			else {
				//If 9 didn't balance with 10, balance 9 against 11.
				bNew.getBalls()[0]=groupC.getBalls()[2];
				//Third scale, if 2nd scale in this scenario failed.
				balanced = balance(aNew, bNew, 1);
				
				if(balanced) {
					System.out.println("balanced");
					System.out.println(aNew.getBalls()[0].getWeight());
					System.out.println(bNew.getBalls()[0].getWeight());
					//Can assume odd ball is 10, because 9 balanced with another ball.
					System.out.println("Odd ball is Ball "+ groupC.getBalls()[1].getIndex()+" weighing "+groupC.getBalls()[1].getWeight());
				}
				else {
					//Can assume odd ball is 9, because it did not balance with more than one ball.
					System.out.println("Odd ball is Ball "+ groupC.getBalls()[0].getIndex()+" weighing "+groupC.getBalls()[0].getWeight());
				}
				
			}
		}
		
	}
	
	public static Boolean balance(BallGroup a, BallGroup b, int groupSize) {
		int aSum=0, bSum=0;
		
		for(int i=0; i<groupSize; i++) {
			aSum+=a.getBalls()[i].getWeight();
			bSum+=b.getBalls()[i].getWeight();
		}
				
		if(aSum>bSum) {
			//First scale
			if(a.getPreviousState()==null && b.getPreviousState()==null) {
				a.setPreviousState("down");
				b.setPreviousState("up");
				a.setCurrentState("down");
				b.setCurrentState("up");
			}
			else {
				a.setPreviousState(a.getCurrentState());
				b.setPreviousState(b.getCurrentState());
				a.setCurrentState("down");
				b.setCurrentState("up");
			}
			if(!a.getPreviousState().equalsIgnoreCase(a.getCurrentState())) {
				for(int i=0; i<groupSize; i++) {
					a.getBalls()[i].setStateChanged(true);
				}
			}

			if(!b.getPreviousState().equalsIgnoreCase(b.getCurrentState())) {
				for(int i=0; i<groupSize; i++) {
					b.getBalls()[i].setStateChanged(true);
				}
			}
			return false;
		}
		else if(aSum<bSum) {
			if(a.getPreviousState()==null && b.getPreviousState()==null) {
				a.setPreviousState("up");
				b.setPreviousState("down");
				a.setCurrentState("up");
				b.setCurrentState("down");
			}
			else {
				a.setPreviousState(a.getCurrentState());
				b.setPreviousState(b.getCurrentState());
				a.setCurrentState("up");
				b.setCurrentState("down");
			}
			if(!a.getPreviousState().equalsIgnoreCase(a.getCurrentState())) {
				for(int i=0; i<groupSize; i++) {
					a.getBalls()[i].setStateChanged(true);
				}
			}

			if(!b.getPreviousState().equalsIgnoreCase(b.getCurrentState())) {
				for(int i=0; i<groupSize; i++) {
					b.getBalls()[i].setStateChanged(true);
				}
			}
			return false;
		}
		else {
			if(a.getPreviousState()==null && b.getPreviousState()==null) {
				a.setPreviousState("balanced");
				b.setPreviousState("balanced");
				a.setCurrentState("balanced");
				b.setCurrentState("balanced");
			}
			else {
				a.setPreviousState(a.getCurrentState());
				b.setPreviousState(b.getCurrentState());
				a.setCurrentState("balanced");
				b.setCurrentState("balanced");
			}
			return aSum==bSum;
		}
	}
	
	public static void setOddIndex(int oddIndex) {
		if(oddIndex>=1 && oddIndex<=4) {
			BallGroup.setOddIndex(groupA, oddIndex);	
		}
		else if(oddIndex>=5 && oddIndex<=8) {
			BallGroup.setOddIndex(groupB, oddIndex);
		}
		else if(oddIndex>=9 && oddIndex<=12) {
			BallGroup.setOddIndex(groupC, oddIndex);	
		}
	}
}
