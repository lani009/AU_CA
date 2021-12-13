freq = 1;
rate = 100;
bits = [ 1 -1 1 -1 1 1 -1 -1 -1 1 ];
t = [0:2*pi/(rate-1):2*pi];
bits_t = [0:1/(rate - 1/length(bits)):length(bits)]
bpsk = [];
carrier = sin(freq*t);

for n = 1:length(bits)
    bpsk = [bpsk bits(n)*carrier];
end

bit_plot = [];
for i = 1: length(bits)
    bit_plot = [bit_plot bits(i)*ones(1,rate)];
end

subplot(2, 1, 1);
plot(bits_t, bit_plot);
title('Original bits');
xlabel('bits');
ylabel('Amplidude');
grid on;

subplot(2, 1, 2);
plot(bits_t, bpsk);
title('Modulated signals');
xlabel('bits');
ylabel('Amplidude');
grid on;
