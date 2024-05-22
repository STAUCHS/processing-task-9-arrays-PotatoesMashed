import processing.core.PApplet;

public class Sketch extends PApplet {
  boolean blnLeft = false;
  boolean blnRight = false;
  boolean blnUp = false;
  boolean blnDown = false;
  float fltSnowSpd = 2;
  int intPlayerX = 700;
  int intPlayerY = 800;
  int intLives = 3;

  int [] intSnowX = new int [200];
  int [] intSnowY = new int [200];
  boolean [] blnSnowActive = new boolean [200];

  public void settings() {
    size(1400, 900);
  }

  public void setup() {
    background(0, 5, 40);
    for (int i = 0; i < 200; i++){
      randomizeSnowball(i);
    }
  }

  public void draw() {
    // checks if player has lives
    if (intLives > 0){
      fill(0, 5, 40);
      rect(0, 0, width, height);
      playerMovement();
      updateSnowball(fltSnowSpd);
      snowCollision();
      visuals();
      
    } else {
      fill(255);
      rect(0, 0, width, height);
    }
    
  }
  
  // All other defined methods are written below:
  /**
   * updates player position
   */
  public void playerMovement(){
    if (blnLeft){
      intPlayerX -= 5;
    } else if (blnRight){
      intPlayerX += 5;
    } 
    if (blnUp){
      intPlayerY -= 5;
    } else if (blnDown){
      intPlayerY += 5;
    } 
    
    if (intPlayerX < 0){
      intPlayerX = width;
    } else if (intPlayerX > width){
      intPlayerX = 0;
    }
    if (intPlayerY < 0){
      intPlayerY = 0;
    } else if (intPlayerY > height){
      intPlayerY = height;
    }
  }

  /**
   * prints visuals: snow, player, lives
   */
  public void visuals(){
    fill(255);
    for (int i = 0; i < 200; i++){
      if (blnSnowActive[i]){ // checks if snow is active
        ellipse(intSnowX[i], intSnowY[i], 10, 10);
      }
    }
    fill(100, 100, 255);
    ellipse(intPlayerX, intPlayerY, 20, 20);
    fill(255, 100, 100);
    for (int i = 1; i <= intLives; i++){
      rect( width + 30 - (60 * i), 30, -30, 30);
    }
  }

  /**
   * checks if player collides with snow 
   */
  public void snowCollision(){
    for (int i = 0; i < 200; i++){
      if (blnSnowActive[i] && dist(intPlayerX, intPlayerY, intSnowX[i], intSnowY[i]) < 15){
        intLives -= 1;
        blnSnowActive[i] = false;
      }
    }
  }

  /**
   * updates snowball position
   * @param snowSpd speed of snow
   * @author Geo D.
   */
  public void updateSnowball(float snowSpd){
    for(int i = 0; i < 200; i++){
      intSnowY[i] += snowSpd;
      if (intSnowY[i] > height){ // checks if snow is below 0
        randomizeSnowball(i);
      }
    }
  }

  /**
   * resets snowball position
   * @param snowballNumber which snowball to reset
   * @author Geo D.
   */
  public void randomizeSnowball(int snowballNumber){
    intSnowY[snowballNumber] = -1 * (int) random(550);
    intSnowX[snowballNumber] = (int) random(width);
    blnSnowActive[snowballNumber] = true;
  }
  public void keyPressed() {
    if (key == 'a') {
      blnLeft = true;
    }
    if (key == 'd') {
      blnRight = true;
    }
    if (key == 's') {
      blnDown = true;
    }
    if (key == 'w') {
      blnUp = true;
    }

    if (keyCode == UP) {
      fltSnowSpd = (float)1.5;
    }else if (keyCode == DOWN) {
      fltSnowSpd = 6;
    }
    
  }

  public void keyReleased() {
    if (key == 'a') {
      blnLeft = false;
    }
    if (key == 'd') {
      blnRight = false;
    }
    if (key == 's') {
      blnDown = false;
    }
    if (key == 'w') {
      blnUp = false;
    }

    if (keyCode == UP) {
      fltSnowSpd = 3;
    } else if (keyCode == DOWN) {
      fltSnowSpd = 3;
    }
  }

  public void mouseReleased(){
    for (int i = 0; i < 200; i++){
      if (dist(mouseX, mouseY, intSnowX[i], intSnowY[i]) < 25){
        blnSnowActive[i] = false;
      }
    }
  }
}