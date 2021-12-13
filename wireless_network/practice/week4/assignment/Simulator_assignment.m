% add ./function/ folder to path before running this file

% Simulator Initial Condition Variables
bit_length = 10^3;
freq = 1;
rate = 100;
t = [0:2*pi/(rate-1):2*pi];
carrier = sin(freq*t);
sim_loop = 1;

SNR = [-20:19];

BER_avg = [];
AWGN_map = [];
for ii = 1 : length(SNR)
    AWGN_sum = [];
    for sim = 1 : sim_loop
        % Generate Random bit
        bits = round(rand(1, bit_length));

        % BPSK modulation
        bpsk = [];
        for n = 1:length(bits)
            if bits(n) == 1
                bpsk = [bpsk carrier];
            else
                bpsk = [bpsk -carrier];
            end
        end

        % Add fading
        [fade_bits fade_sig] = Rayleigh(bpsk,bits);

        % Add AWGN
        [AWGN_bits AWGN_sig] = AWGN(fade_sig, SNR(ii), fade_bits);
        AWGN_sum = [AWGN_sum; AWGN_bits];

        % BPSK demodulation
        dbpsk = [];
        for n = 1 : length(bits)
            demod = AWGN_sig((n-1)*100+1:n*100) .* carrier;
            tt = sum(demod / (99/2));
            if round(tt) == 1
                dbpsk = [dbpsk 1];
            else
                dbpsk = [dbpsk 0];
            end
        end

        % Calculate BER performance
        BER(sim) = sum(xor(bits, dbpsk))/length(bits);
    end
    BER_avg = [BER_avg mean(BER)];
    AWGN_map = [AWGN_map; mean(AWGN_sum, 1)];
end

plot(SNR, BER_avg, '-rp');
title('BER simulator BER performance');
xlabel('AWGN Noise SNR(dB)');
ylabel('BER performance');
grid on;