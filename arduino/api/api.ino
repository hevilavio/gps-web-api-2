// Product name: GPS/GPRS/GSM Module V3.0
// # Product SKU : TEL0051
// # Version     : 1.2
 
// # Description:
// # The sketch for driving the gps mode via the Arduino board
 
// # Steps:
// #        1. Turn the S1 switch to the Prog(right side)
// #        2. Turn the S2 switch to the Arduino side(left side)
// #        3. Set the UART select switch to middle one.
// #        4. Upload the sketch to the Arduino board
// #        5. Turn the S1 switch to the comm(left side) 
// #        6. RST the board 
 
// #        If you get 'inf' values, go outdoors and wait until it is connected.
// #        wiki link- http://www.dfrobot.com/wiki/index.php/GPS/GPRS/GSM_Module_V3.0_(SKU:TEL0051)
 
double Datatransfer(char *data_buf,char num, int convert)//convert the data to the float type
{                                           //*data_buf：the data array                                       
  double temp=0.0;                           //the number of the right of a decimal point
  unsigned char i,j;
 
  if(data_buf[0]=='-')
  {
    i=1;
    //process the data array
    while(data_buf[i]!='.')
      temp=temp*10+(data_buf[i++]-0x30);
    for(j=0;j<num;j++)
      temp=temp*10+(data_buf[++i]-0x30);
    //convert the int type to the float type
    for(j=0;j<num;j++)
      temp=temp/10;
    //convert to the negative numbe
    temp=0-temp;
  }
  else//for the positive number
  {
    i=0;
    while(data_buf[i]!='.')
      temp=temp*10+(data_buf[i++]-0x30);
    for(j=0;j<num;j++)
      temp=temp*10+(data_buf[++i]-0x30);
    for(j=0;j<num;j++)
      temp=temp/10 ;
  }
  
  if(convert == 0){
    return temp;
  }
  // Special thanks to:
  // http://home.online.no/~sigurdhu/Deg_formats.htm
  // Supouse temp=2333.23
  
  int D = (int)(temp/100); // D->23
  double m = temp - (D * 100); // m = D -> 2333.23 - 2300 -> 33.23
  return (D + (m/60)) * 100; // 23 + 0,5537 -> 23,5537 (* 100 ??)
  
}
 
char ID()//Match the ID commands
{ 
  char i=0;
  char value[6]={
    '$','G','P','G','G','A'      };//match the gps protocol
  char val[6]={
    '0','0','0','0','0','0'      };
 
  while(1)
  {
    if(Serial.available())
    {
      val[i] = Serial.read();//get the data from the serial interface
      if(val[i]==value[i]) //Match the protocol
      {    
        i++;
        if(i==6)
        {
          i=0;
          return 1;//break out after get the command
        }
      }
      else
        i=0;
    }
  } 
}
 
void comma(char num)//get ','
{   
  char val;
  char count=0;//count the number of ','
 
  while(1)
  {
    if(Serial.available())
    {
      val = Serial.read();
      if(val==',')
        count++;
    }
    if(count==num)//if the command is right, run return
      return;
  }
 
}
void UTC()//get the UTC data -- the time
{
  char i;
  char time[9]={
    '0','0','0','0','0','0','0','0','0'
  };
  double t=0.0;
 
  if( ID())//check ID
  {
    comma(1);//remove 1 ','
    //get the datas after headers
    while(1)
    {
      if(Serial.available())
      {
        time[i] = Serial.read();
        i++;
      }
      if(i==9)
      {
        i=0;
        t=Datatransfer(time,2,0);//convert data
        t=t-30000.00;//convert to the chinese time GMT+8 Time zone
        long time=t;
        int h=time/10000;
        int m=(time/100)%100;
        int s=time%100;
        
//        if(h>=24)               //UTC+
//        {
//        h-=24;
//        }
 
         if (h<0)               //UTC-
        {
          h+=24;
        }
        Serial.print(h);
        Serial.print("h");
        Serial.print(m);
        Serial.print("m");
        Serial.print(s);
        Serial.println("s");
 
        //Serial.println(t);//Print data 
        return;
      }  
    }
  }
}
void latitude()//get latitude
{
  char i;
  char lat[10]={
    '0','0','0','0','0','0','0','0','0','0'
  };
 
 
  if( ID())
  {
    comma(2);
    while(1)
    {
      if(Serial.available())
      {
        lat[i] = Serial.read();
        i++;
      }
      if(i==10)
      {
        i=0;
        Serial.print(Datatransfer(lat,5,1)/100.0,7);//print latitude 
        return;
      }  
    }
  }
}
void lat_dir()//get dimensions
{
  char i=0,val;
 
  if( ID())
  {
    comma(3);
    while(1)
    {
      if(Serial.available())
      {
        val = Serial.read();
        Serial.print(val);
        i++;
      }
      if(i==1)
      {
        i=0;
        return;
      }  
    }
  }
}
void longitude()//get longitude
{
  char i;
  char lon[11]={
    '0','0','0','0','0','0','0','0','0','0','0'
  };
 
  if( ID())
  {
    comma(4);
    while(1)
    {
      if(Serial.available())
      {
        lon[i] = Serial.read();
        i++;
      }
      if(i==11)
      {
        i=0;
        Serial.print(Datatransfer(lon,5,1)/100.0,7);
        return;
      }  
    }
  }
}
void lon_dir()//get direction data
{
  char i=0,val;
 
  if( ID())
  {
    comma(5);
    while(1)
    {
      if(Serial.available())
      {
        val = Serial.read();
        Serial.print(val); //Serial.println(val,BYTE);
        i++;
      }
      if(i==1)
      {
        i=0;
        return;
      }  
    }
  }
}
void altitude()//get altitude data
{
  char i,flag=0;
  char alt[8]={
    '0','0','0','0','0','0','0','0'
  };
 
  if( ID())
  {
    comma(9);
    while(1)
    {
      if(Serial.available())
      {
        alt[i] = Serial.read();
        if(alt[i]==',')
          flag=1;
        else
          i++;
      }
      if(flag)
      {
        i=0;
        Serial.println(Datatransfer(alt,1,0),1);//print altitude data
        return;
      }  
    }
  }
}

/*
  Pinos:
  
  UART: no meio
  S1: PROG para upload de codigo, COMM para uso com Arduino.
  S2: ARDUINO para uso com o mesmo, USB para conectar via Serial.
  JUMPERS: Todos os 3 conectados.

  Verificar:
  
  * Antena do GPS esta bem conectada e posicionada.
  * SIM CARD tem conectividade com a Operadora
  * SIM CARD tem pacote de dados.
  * 12V para usar GPRS.
  
  
*/

// Configuracoes do dispositivo
const char* gpsId = "1011";
const char* serverUrl = "apiv1-gpsapi.rhcloud.com/api/position/save";
const int secIntervalData = 5;
const int testMode = 0;
//

void setup()
{
  //The default digital driver pins for the GSM and GPS mode
  pinMode(3,OUTPUT);
  pinMode(4,OUTPUT);
  pinMode(5,OUTPUT);
  
  //Output GSM Timing
  digitalWrite(5,HIGH); 
  delay(1500);
  digitalWrite(5,LOW);
  
  digitalWrite(3,LOW);//Enable the GSM mode
  digitalWrite(4,LOW);//Enable the GPS mode
  delay(2000);

  Serial.begin(9600); //set the baud rate
  delay(5000);//call ready
  delay(5000);
  delay(5000);  
  
  
  startGPS();
  startGPRS();
}


void loop()
{
    info("loop interaction");
    
    if(testMode == 1){
      while(1)
      { 
        testGPS();
      //testGPRS();
      }
    }
    
    sendToSerial("AT+HTTPPARA=\"URL\"");
    
    sendToSerial(",");
    
    sendToSerial("\""); // aspas
    sendToSerial(serverUrl);
    sendToSerial("/");
    sendToSerial(gpsId);
    sendToSerial("/");
    latitude();
    sendToSerial("/");
    longitude();
    sendToSerial("/");
    lat_dir();
    sendToSerial("/");
    lon_dir();
    sendToSerial("/");

    sendToSerial("\""); // aspas
    
    Serial.println("");     
    delay(2 * 1000);
    
    Serial.println("AT+HTTPACTION=0"); // GET Action
    delay(7 * 1000);

    delay(secIntervalData * 1000);

    /*
    while(1)
    { 
        testGPS();
        testGPRS();
    }
    */
}

void startGPS(){
    Serial.println("AT");   
    delay(2000);
    //turn on GPS power supply
    Serial.println("AT+CGPSPWR=1");
    delay(1000);
    //reset GPS in autonomy mode
    Serial.println("AT+CGPSRST=1");
    delay(1000); 
}

void startGPRS(){
  
    Serial.println("AT+SAPBR=3,1,\"APN\",\"tim.br\"");
    delay(2000);
    Serial.println("AT+SAPBR=3,1,\"USER\",\"tim\"");
    delay(2000);
    Serial.println("AT+SAPBR=3,1,\"PWD\",\"tim\"");
    delay(2000);
    Serial.println("AT+SAPBR=3,1,\"Contype\",\"GPRS\"");
    delay(2000);
    Serial.println("AT+SAPBR=1,1");
    delay(10 * 1000);
    Serial.println("AT+HTTPINIT");
    delay(2 * 1000);
    Serial.println("AT+HTTPPARA=\"CID\",1");
    delay(2 * 1000);
 }
 
 void testGPS(){
    Serial.print("UTC:");
    UTC();
    Serial.print("Lat:");
    latitude();
    Serial.print("Dir:");
    lat_dir();
    Serial.print("Lon:");
    longitude();
    Serial.print("Dir:");
    lon_dir();
    Serial.print("Alt:");
    altitude();
    Serial.println(' ');
    Serial.println(' ');
 }
 
void testGPRS(){
    Serial.println("AT+HTTPPARA=\"URL\",\"apiv1-gpsapi.rhcloud.com/api/position/save/1010/100/200/\"");
    delay(2 * 1000);
    Serial.println("AT+HTTPACTION=0"); //GET action
    delay(15 * 1000);  
}

int debugActived = 1;
int infoActived = 1;

void debug(char *msg){
  if(debugActived == 1){
    Serial.println(msg);
  } 
}

void info(char *msg){
  if(infoActived == 1){
    Serial.println(msg);
  } 
}

void qMark(){
    Serial.print("\"");
}
void slash(){
   Serial.print("/");
}

void sendToSerial(const char *msg){
  Serial.print(msg);
}






