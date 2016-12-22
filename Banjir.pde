import processing.serial.*;
Serial port;
String nilai;
PFont font;

void setup() {
  background(241, 196, 15);
  size(1366, 768);
  println( Serial.list() );
  String portName = Serial.list()[0]; 
  port = new Serial(this, portName, 9600);
  font = createFont ("Calibri",200);
  delay(5000);

}

void draw() {
  background(241, 196, 15);
  if ( port.available() > 0) 
  {  // If data is available,
  nilai = port.readString();        
  } 
  textFont(font);
  textAlign(CENTER);
  fill(192, 57, 43);
  text(nilai,width/2,height/2); 
  delay(5000);
}