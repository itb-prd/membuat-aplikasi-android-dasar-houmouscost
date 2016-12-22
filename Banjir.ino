int baca;
int visitor;
int a;

void setup() {
  // put your setup code here, to run once:
Serial.begin(9600);
  pinMode(LED_BUILTIN, OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
baca = analogRead(A0);
if (baca<300)
{
  Serial.write("Banjir");
  digitalWrite(LED_BUILTIN, HIGH);
}
else if (baca>300)
{
  digitalWrite(LED_BUILTIN, LOW);
  Serial.write("Tidak Banjir");
}

delay(5000);
}
