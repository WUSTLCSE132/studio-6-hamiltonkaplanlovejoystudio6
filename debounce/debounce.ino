const int buttonPin = 2;
unsigned long lastDebounceTime = 0;  // the last time the output pin was toggled
unsigned long debounceDelay = 50;
unsigned int counter = 0;

void buttonPressed() {
  if(lastDebounceTime + debounceDelay < millis()){
    lastDebounceTime = millis();
    counter++;
  }
}

void setup() {
  pinMode(buttonPin, INPUT_PULLUP);

  // Interrupts can happen on "edges" of signals.  
  // Three edge types are supported: CHANGE, RISING, and FALLING 
  attachInterrupt(digitalPinToInterrupt(buttonPin), buttonPressed, FALLING);
  Serial.begin(9600);
}

void loop() {
  Serial.println(counter);
  delay(1000);
}
