sampleRate = 100000;
dopplerFreq = 100;

simSample = 1024;
cp_length = 80;
modOrder = 4;
EbN0 = -10:5:30;

tx = randi([0 modOrder-1], simSample, 1);
pskSigOfdm = pskmod(tx, modOrder, 0);

scatter(real(pskSigOfdm), imag(pskSigOfdm), 30, 'b', 'filled'); grid;
axis([-1.5 1.5 -1.5 1.5]);
xlabel('In-Phase');
ylabel('Quadrature');
title('Freq domain signal');

pskSig = ifft(pskSigOfdm)*sqrt(simSample);
figure; plot(1:100, real(pskSig(1:100)), 'b', 1:100, imag(pskSig(1:100)), 'r');
grid; legend('real', 'imag');
xlabel('sample'); ylabel('value');
title('time domain signal');
scatterplot(pskSig); grid; axis([-1.5 1.5 -1.5 1.5]);

pskSig = [pskSig(simSample-cp_length+1:simSample); pskSig];
rayleighchan = comm.RayleighChannel('SampleRate', sampleRate, 'MaximumDopplerShift', dopplerFreq);
[fadedSig] = rayleighchan(pskSig);
fadedSig = fadedSig(cp_length+1:end);

fadingCoeffOfdm = fft(fadedSig)/sqrt(simSample)./pskSigOfdm;
figure; plot(1:100, real(fadedSig(1:100)), 'b', 1:100, imag(fadedSig(1:100)), 'r');
grid; legend('real', 'imag');
xlabel('sample'); ylabel('value');
title('faded and noise added signal');
scatterplot(fadedSig); grid; axis([-1.5 1.5 -1.5 1.5]);

for n = 1:length(EbN0)
    for iter = 1:100
        rxSigTime = awgn(fadedSig, EbN0(n)+10*log10(log(modOrder)/log(2)));
        rxSigFreq = fft(rxSigTime)/sqrt(simSample);
        
        chanCompensation = rxSigFreq ./ fadingCoeffOfdm;
        
        if (iter == 1)
            scatterplot(chanCompensation); grid; axis([-1.5 1.5 -1.5 1.5]);
            title_str = sprintf('channel compensated EbN0 %ddB', EbN0(n));
            title(title_str);
            pause(1);
        end
        rx = pskdemod(chanCompensation, modOrder, 0);
        
        [nErrors, berIter(iter)] = biterr(tx, rx);
    end
    BER(n) = mean(berIter);
end

BERtheory = berfading(EbN0, 'psk', modOrder, 1);

figure;
semilogy(EbN0, BERtheory, 'b-', EbN0, BER, 'r*');
legend('Theoretical BER', 'Empirical BER');
xlabel('SNR (dB)'); ylabel('BER');
title('QPSK over Rayleigh Fading Channel');
grid;