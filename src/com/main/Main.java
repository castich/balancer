package com.main;

public class Main {
	static BallGroup groupA = new BallGroup("a");
	static BallGroup groupB = new BallGroup("b");
	static BallGroup groupC = new BallGroup("c");
	static boolean balanced = false;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int oddIndex = 9;
		
		groupA.init();
		groupB.init();
		groupC.init();
		setOddIndex(oddIndex);
		
		//First scale
		balanced = balance(groupA, groupB, 4);
		System.out.println(balanced);
		if(!balanced) {
			
		}
		
		else {
			//Will assume odd ball is in Group C if A and B balanced.
			BallGroup aNew = new BallGroup("aNew");
			BallGroup bNew = new BallGroup("bNew");
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
					System.out.println("Odd ball is "+ groupC.getBalls()[3].getIndex()+" weighting "+groupC.getBalls()[3].getWeight()+".");
				}
				else {
//					can assume odd ball is ball 11, because it never did not balance with 9 that already balanced earlier with another ball.
					System.out.println("Odd ball is "+ groupC.getBalls()[2].getIndex()+" weighing "+groupC.getBalls()[2].getWeight());
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
					System.out.println("Odd ball is "+ groupC.getBalls()[1].getIndex()+" weighing "+groupC.getBalls()[1].getWeight());
				}
				else {
					//Can assume odd ball is 9, because it did not balance with more than one ball.
					System.out.println("Odd ball is "+ groupC.getBalls()[0].getIndex()+" weighing "+groupC.getBalls()[0].getWeight());
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
				
		
		//First scale
		if(aSum>bSum) {

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
