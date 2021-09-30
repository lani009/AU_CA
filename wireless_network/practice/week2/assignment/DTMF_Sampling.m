N = 2000;
Ts = 1/100000;

t = zeros(1, N);
x_value = zeros(1, N);

for n = 0: N-1
    tn = n * Ts;
    t = [t(2:end) tn];
    x_value = [x_value(2:end) sin(2*pi*770*tn)+sin(2*pi*1477*tn)];
end

plot(t, x_value);