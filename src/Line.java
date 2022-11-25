
public class Line {

	private int[] startPoint = new int[2];
	private int[] endPoint = new int[2];
	private int[] difference = new int[2];
	private double angle;
	private double magnitude;
	private double[] midPoint = new double[2];


	public Line() {
		// TODO Auto-generated constructor stub
	}

	public Line(int[]start, int[]end) {
		
		startPoint = start;
		endPoint = end;
		
		setDifference();
		setAngle();
		setMagnitude();
		setMidPoint();
	}
	
	public int[] getStartPoint() {
		
		return startPoint;
		
	}
	public int[] getEndPoint() {
		
		return endPoint;
		
	}
	public int[] getDifference() {
		
		return difference;
	}
	public double[] getMidPoint() {
		
		return midPoint;
	}
	public double getAngle() {
		
		return angle;
	}
	public double getMagnitude() {
		
		return magnitude;
	}
	
	public void setDifference() {

		difference[0] = endPoint[0] - startPoint[0];
		difference[1] = endPoint[1] - startPoint[1];

	}

	public void setMagnitude() {

		magnitude = Math.sqrt((Math.pow(difference[0], 2) + Math.pow(difference[1], 2)));

	}

	public void setMidPoint() {

		midPoint[0] = (0.5 * endPoint[0]) + (0.5 * startPoint[0]);
		midPoint[1] = (0.5 * endPoint[1]) + (0.5 * startPoint[1]);

	}

	public void setAngle() {
		
		angle = Math.atan2(difference[1], difference[0]);
	}
}
