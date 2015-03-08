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
 
double Datatransfer(char *data_buf,char num)//convert the data to the float type
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
  return temp;
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
        t=Datatransfer(time,2);//convert data
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
double latitude()//get latitude
{
  char i;
  double latitude;
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
        //Serial.println(Datatransfer(lat,5)/100.0,7);//print latitude 
        Serial.print(Datatransfer(lat,5)/100.0,7);
        
        latitude = Datatransfer(lat,5)/100.0,7;
        return latitude;
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
        Serial.write(val);
        Serial.println();
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
double longitude()//get longitude
{
  char i;
  double longitude;
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
        //Serial.println(Datatransfer(lon,5)/100.0,7);
        Serial.print(Datatransfer(lon,5)/100.0,7);
        
        longitude = Datatransfer(lon,5)/100.0,7;
        return longitude;
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
        Serial.write(val); //Serial.println(val,BYTE);
        Serial.println();
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
        Serial.println(Datatransfer(alt,1),1);//print altitude data
        return;
      }  
    }
  }
}

byte gsmDriverPin[3] = {
  3,4,5};//The default digital driver pins for the GSM and GPS mode
//If you want to change the digital driver pins
//or you have a conflict with D3~D5 on Arduino board,
//you can remove the J10~J12 jumpers to reconnect other driver pins for the module!
void setup()
{
  //Init the driver pins for GSM function
  for(int i = 0 ; i < 3; i++){
    pinMode(gsmDriverPin[i],OUTPUT);
  }
  digitalWrite(5,HIGH);//Output GSM Timing 
  delay(1500);
  digitalWrite(5,LOW);  
  
  digitalWrite(3,LOW);//Enable the GSM mode
  digitalWrite(4,HIGH);//Disable the GPS mode
  delay(2000);
  Serial.begin(9600); //set the baud rate
  delay(5000);//call ready
  delay(5000);
  delay(5000);
  
  start_GSM();
  start_GPS();
}

 

void loop()
{
    //testHttpGet();
    
    //readGpsInfo();

    readAndSendGpsData();
    /*
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
    */
    delay(5 * 1000);

}
double lastLat;
double lastLon;
void readGpsInfo()
{
   Serial.println("gri.i");

   double lat = latitude();
   double lon = longitude();
   
   lastLat = lat;
   lastLon = lon;
   
   Serial.print("Lat: ");
   Serial.println(lat); 
   
   Serial.print("Long: ");
   Serial.println(lon); 

   //makeUrl(lat, lon);

   Serial.println("gri.s");
}

void testHttpGet(){
  Serial.println("AT+HTTPPARA=\"URL\",\"apiv1-gpsapi.rhcloud.com/api/position/save/1010/23.3322410/46.5072746/\"");
  
  delay(3 * 1000);
  Serial.println("AT+HTTPACTION=0"); //now GET action
  delay(7 * 1000);

  Serial.println("AT+HTTPREAD=1,200"); //now GET action
}

// Usado temporariamente, o correto seria enviar dados via POST
void readAndSendGpsData(){
  Serial.print("AT+HTTPPARA=\"URL\",\"apiv1-gpsapi.rhcloud.com/api/position/save/");

  Serial.print("1011");// gpsId
  Serial.print("/");

  latitude();// manda latitude para porta serial
  Serial.print("/");

  longitude();
  Serial.print("/");

  Serial.println("\"");// ->"<- para "enviar" o comando  
  delay(3 * 1000);

  Serial.println("AT+HTTPACTION=0"); //now GET action
  delay(7 * 1000);
}

void showResponseCommand(){
   if(Serial.available())
   {
      Serial.print(Serial.readStringUntil('\n'));
    } 
}
void start_GSM(){
  
    delay(10 * 1000);
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
    delay(2000);
    Serial.println("AT+HTTPPARA=\"CID\",1");
    delay(2000);
 }
void start_GPS(){
    Serial.println("AT");   
    delay(2000);
    //turn on GPS power supply
    Serial.println("AT+CGPSPWR=1");
    delay(1000);
    //reset GPS in autonomy mode
    Serial.println("AT+CGPSRST=1");
    delay(1000); 
}

void makeUrl(double lat, double lon){
  
   char url[100]; 
   
   strcat(url, "http://site.com/savedata?");
   strcat(url, "latitude=");
   strcat(url, toChar(lat));
   strcat(url, ", longitude=");
   strcat(url, toChar(lon));

   Serial.print("url: ");
   Serial.println(url); 
}

char* toChar(double d){
   return "A"; 
}
