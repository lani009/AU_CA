t = 0 : 2*pi/100 : 2*pi;

x = sin(t/5*2*pi);
y = cos(t/5*2*pi);

plot(t, y);
hold on
plot(t, x, 'r');