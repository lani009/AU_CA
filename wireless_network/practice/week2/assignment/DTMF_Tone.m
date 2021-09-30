N = 10000;

t = 0 : 1/N : 1;
dial0 = sin(2*pi*941*t)+sin(2*pi*1336*t);
dial1 = sin(2*pi*697*t)+sin(2*pi*1209*t);
dial2 = sin(2*pi*697*t)+sin(2*pi*1336*t);
dial6 = sin(2*pi*770*t)+sin(2*pi*1477*t);
dial9 = sin(2*pi*852*t)+sin(2*pi*1477*t);

result = [dial2 dial0 dial1 dial9 dial2 dial1 dial1 dial6 dial6];
audiowrite('dtmf.wav', result, N);