/*
  This sketch is part of a tutorial for connecting to and communicating with an HC-06 or an RN-42 bluetooth module using a custom Android App. 
  The bluetooth modules are connected to an Arduino and the Arduino is connected to an LED. The Android app is used to wirelessly turn on and
  off the LED using  bluetooth. 

  This code is in the public domain.
 */

int led = LED_BUILTIN;

// the setup routine runs once when you press reset:
void setup() {
  
  Serial.begin(9600);
  pinMode(led, OUTPUT);
  digitalWrite(led, LOW);
}

// the loop routine runs over and over again forever:
void loop() {
  delay(30);
  String t; 
  while(Serial.available()) { 
    t += (char)Serial.read(); 
  }
  
  if(t.length()) { 
    if(t == "on") {
      digitalWrite(led, HIGH); 
      Serial.write("LED menyala"); 
    }
    else if (t == "off") { 
      digitalWrite(led, LOW);  
      Serial.write("LED mati");
    } 
  }
}
