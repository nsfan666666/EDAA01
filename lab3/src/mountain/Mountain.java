package mountain;

import java.util.ArrayList;
import java.util.Iterator;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {
	private Point a;
	private Point b;
	private Point c;
	private Double originalDev;
	private ArrayList<Side> sides;
	
	public Mountain(Point a, Point b, Point c, Double dev) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.originalDev = dev;
		sides = new ArrayList<Side>();
	}

	@Override
	public String getTitle() {
		return "Bergmassiv";
	}

	@Override
	public void draw(TurtleGraphics turtle) {
		turtle.penDown();
		fractalTriangle(turtle, order, a, b, c, originalDev);
	}
	
	private void fractalTriangle(TurtleGraphics turtle, int order, Point a, Point b, Point c, Double dev) {
		if(order == 0) {
			// Draw a triangle
			turtle.moveTo(a.getX(), a.getY());
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
		} else {
			// Create side objects
			Side AB = null;
			Side BC = null;
			Side CA = null;
			
			// See if side object already has been created
			Iterator<Side> sideIterator = sides.iterator();
			while(sideIterator.hasNext()) {
				Side temp = sideIterator.next();
				if(AB == null && temp.isSame(a, b)) {
					AB = temp;
					sideIterator.remove();
				} else if(BC == null && temp.isSame(b, c)) {
					BC = temp;
					sideIterator.remove();
				} else if(CA == null && temp.isSame(c, a)) {
					CA = temp;
					sideIterator.remove();
				}
			}
			
			if(AB == null) {
				AB = new Side(a, b, dev);
				sides.add(AB);
			}
			
			if(BC == null) {
				BC = new Side(b, c, dev);
				sides.add(BC);
			}
			
			if(CA == null) {
				CA = new Side(c, a, dev);
				sides.add(CA);
			}
			
			// Get mid points
			Point midAB = AB.getMid();
			Point midBC = BC.getMid();
			Point midCA = CA.getMid();
			
			// Draw four new triangles
			fractalTriangle(turtle, order - 1, a, midAB, midCA, dev/2);
			fractalTriangle(turtle, order - 1, midAB, b, midBC, dev/2);
			fractalTriangle(turtle, order - 1, midCA, midAB, midBC, dev/2);
			fractalTriangle(turtle, order - 1, midCA, midBC, c, dev/2);
		}
	}
	
	private class Side {
		private Point p;
		private Point q;
		private Double dev;
		private Point mid;
		
		public Side(Point p, Point q, Double dev) {
			this.p = p;
			this.q = q;
			this.dev = dev;
		}
		
		public Point getMid() {
			if(mid == null) {
				// Calculate mid
				Point mid = new Point(0, 0); // The value of this point does not matter
				
				// Get x-coordinate
				if(p.getX() > q.getX()) {
					mid.move(p.getX() - (Math.abs(p.getX() - q.getX())/2), mid.getY());
				} else {
					mid.move(p.getX() + (Math.abs(p.getX() - q.getX())/2), mid.getY());
				}
				
				// Get y-coordinate
				if(p.getY() > q.getY()) {
					mid.move(mid.getX(), p.getY() - (Math.abs(p.getY() - q.getY())/2));
				} else {
					mid.move(mid.getX(), p.getY() + (Math.abs(p.getY() - q.getY())/2));
				}
				
				// Add distortion to y-axis
				mid.move(mid.getX(), mid.getY() + RandomUtilities.randFunc(dev));
				
				// Save mid
				this.mid = mid;
			}
			return mid;
		}
		
		public boolean isSame(Point p2, Point q2) {
			Boolean sameP = (p.equals(p2));
			Boolean sameQ = (q.equals(q2));
			Boolean P1EqualsQ2 = (p.equals(q2));
			Boolean Q1EqualsP2 = (q.equals(p2));
			if((sameP && sameQ) || (P1EqualsQ2 && Q1EqualsP2)) {
				return true;
			}
			return false;
		}
	}	
}




