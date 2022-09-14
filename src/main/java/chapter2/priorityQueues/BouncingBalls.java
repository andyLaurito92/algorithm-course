package chapter2.priorityQueues;

import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdRandom;

public class BouncingBalls {
    public static void main(String[] args) {
	int N = Integer.parseInt(args[0]);
	Ball[] balls = new Ball[N];
	for (int i = 0; i < N; i++) 
	    balls[i] = new Ball();

	//StdDraw.setScale(-2, +2);
	//StdDraw.enableDoubleBuffering();
	
	while (true) {
	    StdDraw.clear();
	    for (int i = 0; i < N; i++) {
		balls[i].move(0.5);
		balls[i].draw();
	    }
	    StdDraw.show(50);
	}
    }

    static class Ball {
	private static final double RADIUS = 0.01;
	private double x, y;
	private double vx, vy;

	public Ball() {
	    x = StdRandom.uniform();
	    y = StdRandom.uniform();
	    vx = Math.max(0.015, 0.035 * StdRandom.uniform());
	    vy = Math.max(0.015, 0.035 * StdRandom.uniform());
	    draw();
	}
	public void move(double movement) {
	    if ((x + vx*movement < RADIUS) || (x + vx*movement > 1.0 - RADIUS)) vx = -vx;
	    if ((y + vy*movement < RADIUS) || (y + vy*movement > 1.0 - RADIUS)) vy = -vy;
	    x += vx*movement;
	    y += vy*movement;
	}

	public void draw() {
	    StdDraw.filledCircle(x, y, RADIUS);
	}
    }
}
