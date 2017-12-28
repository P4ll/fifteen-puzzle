package testing;

import pac.Board;
import pac.Generator;
import pac.Solver2;

public class Tester {
	public static void main(String[] args) {
		final int N = 30;
		final int left = 60;
		final int right = 80;
		final int hType = 0;
		final int numberOfExp = 16;
		Generator gen = new Generator();
    	Board startBoard;
    	Solver2 solve;
    	//System.out.println("countMoves\t" + "countCheckedVertex\t" + "countCreatedVertex\t" + "time\t" + "h(x)\t" + "h*(x)");
    	System.out.println("N\t" + "countCheckedVertex\t" + "time\t");
    	for (int i = N; i <= N; i++) {
    		for (int j = left; j <= right; j++) {
    			startBoard = gen.getGen(j, i, hType);
    			double elapsed = 0;
    			long countCheckVertex = 0;
    			long countCreateVertex = 0;
    			int countMoves = 0;
    			for (int k = 0; k < numberOfExp; k++) {
        			double sTime = System.currentTimeMillis();
        			solve = new Solver2(startBoard);
        			double eTime = System.currentTimeMillis();
        			elapsed += (eTime - sTime);
        			countMoves += solve.moves();
        			countCheckVertex += (long)solve.countCheckVertex;
        			countCreateVertex += (long)solve.countCreateVertex;
    			}
    			countMoves /= numberOfExp;
    			elapsed /= numberOfExp;
    			countCheckVertex /= numberOfExp;
    			countCreateVertex /= numberOfExp;
    			System.out.println(j + "\t" + countCheckVertex + "\t" + elapsed);
//    			System.out.println(j + "\t" + countCheckVertex + "\t" + countCreateVertex + "\t" + elapsed + "\t" + startBoard.getHeuristicValue() +
//    								"\t" + countMoves);
    		}
    	}
	}
}
