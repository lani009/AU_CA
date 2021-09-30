Fs = 5000;
N = 10000;
t = [0:N-1]/Fs;
f = [0:N-1]/N*Fs;

y0 = sin(2*pi*770*t)+sin(2*pi*1477*t);

Y0 = abs(fft(y0));

plot(f, Y0);