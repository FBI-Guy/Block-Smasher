import processing.core.PApplet;
import processing.core.PFont;

import java.util.Random;


public class ProcessingTest extends PApplet {

	public class Brick{
		public int x;
		public int y;
		public boolean exists;
	}


	PFont myFont;
	Random generator = new Random();
	int randomIndex = generator.nextInt(10);
	int ballX = 400;
	int ballY = 60;
	int ballVelX = 5;
	int ballVelY = 5;
	int paddleY = 590;
	int paddleX = 400;
	Brick[][] bricks = new Brick[5][8];
	boolean ballExists = true;
	int lives = 3;

	public void setup() {
		myFont = loadFont("font.vlw");
		int counterX = 0;
		int counterY = 0;
		setSize(800,600);
		background(0,255,0);
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 8; j++){
				Brick temp = new Brick();
				temp.x = counterX;
				temp.y = counterY;
				temp.exists = true;
				bricks[i][j] = temp;
				counterX = counterX + 100;
			}
			counterX = 0;
			counterY = counterY + 10;
		}
	}

	public void keyPressed(){
		if(key == CODED){
			if(keyCode == 37){
				paddleX = paddleX - 10;
			}
			if(keyCode == 39){
				paddleX = paddleX + 10;
			}
		}
	}

	public void keyReleased(){

	}

	public void draw() {
		background(0,255,0);
		ballX = ballX + ballVelX;
		ballY = ballY + ballVelY;

		textFont(myFont, 30);
		text("Lives: " + lives,690,590);

		if(lives <= 3 && lives > 0){
			if(ballExists = true){
				fill(0);
				ellipse(ballX,ballY,8,8);

			}
			if(ballY >= 600){
				ballExists = false;
				lives = lives - 1;
				ballX = 400;
				ballY = 60;
				ballExists = true;
			}
		}
		if(lives == 0){
			textFont(myFont, 50);
			text("Game Over",300,300);
		}
		fill(255,0,255);
		rect(paddleX,paddleY,100,5);

		// Paddle
		if(ballX >= paddleX && ballX <= (paddleX + 100)){
			if(ballY == paddleY){
				ballVelY = - ballVelY;
			}
		}
		if(ballY >= paddleY && ballY <= (paddleY + 10)){
			if(ballX == paddleX || ballX == paddleX + 100){
				ballVelX = - ballVelX;
			}
		}
		if(ballY >= paddleY && ballY <= (paddleY)){
			if(ballX == paddleX || ballX == paddleX + 100){	
				ballVelX = - ballVelX;
			}
		}

		if(ballX <= 0 || ballX >= 800){
			ballVelX = - ballVelX;
		}
		if(ballY <= 0){
			ballVelY = - ballVelY;			
		}
		if(paddleX >= 700){
			paddleX = 700;
		}
		if(paddleX <= 0){
			paddleX = 0;
		}
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 8; j++){
				if (bricks[i][j].exists == true) {
					fill(255,0,0);
					rect(bricks[i][j].x,bricks[i][j].y,100,10);
				}
			}
		}

		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 8; j++)
				if (bricks[i][j].exists == true) {
					if (ballX >= bricks[i][j].x && ballX <= bricks[i][j].x + 100 && ballY <= bricks[i][j].y + 10) {
						ballVelY = - ballVelY;
						bricks[i][j].exists = false;
					}
				}
		}

	}
}