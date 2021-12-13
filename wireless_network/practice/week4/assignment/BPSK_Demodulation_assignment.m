freq = 1;
rate = 100;
bits = [ 1 -1 1 -1 1 1 -1 -1 -1 1 ];
t = [0:2*pi/(rate-1):2*pi];
bits_t = [0:1/(rate - 1/length(bits)):length(bits)]
bpsk = [];
carrier = sin(freq*t);

% 변조
for n = 1:length(bits)
    bpsk = [bpsk bits(n)*carrier];
end


dbpsk = [];
carrier = sin(freq*t);

% 복조
for n = 1 : length(bits)
    demod = bpsk((n-1)*100+1:n*100) .* carrier;
    tt = sum(demod / (99/2));
    if round(tt) == 1
        dbpsk = [dbpsk 1];
    else
        dbpsk = [dbpsk -1];
    end
end

% dbpsk inflation
inflated_dbpsk = [];
for i = 1: length(dbpsk)
    inflated_dbpsk = [inflated_dbpsk dbpsk(i)*ones(1,rate)];
end

subplot(2, 1, 1);
plot(bits_t, bpsk);
title('Modulated signals');
xlabel('bits');
ylabel('Amplidude');
grid on;

subplot(2, 1, 2);
plot(bits_t, inflated_dbpsk);
title('Demodulated signals');
xlabel('bits');
ylabel('Amplidude');
grid on;