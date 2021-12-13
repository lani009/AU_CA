freq = 12;
rate = 100;
bits = [ -1 -1 -1 1 1 ];
t = [0:2*pi/(rate-1):2*pi];
T = [0:2*pi/(rate-1):length(bits)*2*pi+2*pi/(rate-1)*(length(bits)-1)];
bpsk = [];
carrier = sin(freq*t);

for n = 1:length(bits)
    bpsk = [bpsk bits(n)*carrier];
end

plot(T, bpsk)