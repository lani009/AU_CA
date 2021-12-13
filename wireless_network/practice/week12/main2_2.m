L_frame = 100;
N_Packets = 1000;
EbNo = 0:2:12;
N = 4;
M = 4;
P = 2;
bpskMod = comm.BPSKModulator;
bpskDemod = comm.BPSKDemodulator('OutputDataType', 'double');

ostbcEnc = comm.OSTBCEncoder;
ostbcComb = comm.OSTBCCombiner;

awgn1Rx = comm.AWGNChannel('NoiseMethod', 'Signal to noise ratio (Eb/No)', 'SignalPower', 1);
awgn2Rx = clone(awgn1Rx);

errorCalc1 = comm.ErrorRate;
errorCalc2 = comm.ErrorRate;
errorCalc3 = comm.ErrorRate;

H = zeros(L_frame, N, M);
ber_noDiver = zeros(4, length(EbNo));
ber_Alamouti = zeros(4, length(EbNo));
ber_MaxRatio = zeros(4, length(EbNo));

fig = figure;
grid on;
ax = fig.CurrentAxes;
hold(ax, 'on');

ax.YScale = 'log';
xlim(ax, [EbNo(1), EbNo(end)]);
ylim(ax, [1e-4 1]);
xlabel(ax, 'Eb/No (dB)');
ylabel(ax, 'BER');
fig.NumberTitle = 'off';
fig.Renderer = 'painters';
fig.Name = 'Transmit vs Receiver Diversity';
set(fig, 'DefaultLegendAutoUpdate', 'off');
fig.Position = figposition([15 50 25 30]);

for idx = 1:length(EbNo)
    reset(errorCalc1);
    reset(errorCalc2);
    reset(errorCalc3);
    awgn1Rx.EbNo = EbNo(idx);
    awgn2Rx.EbNo = EbNo(idx);
    
    for packet_idx = 1:N_Packets
        data = randi([0 P-2], L_frame, 1);
        modData = bpskMod(data);
        encData = ostbcEnc(modData);
        H(1:N:end, :, :) = (rand(L_frame/2, N, M) + 1i*randn(L_frame/2, N, M))/sqrt(2);
        H(2:N:end, :, :) = H(1:N:end, :, :);
        H11 = H(:, 1, 1);
        H21 = H(:, :, 1)/sqrt(2);
        H12 = squeeze(H(:, 1, :));
        
        chanOut11 = H11 .* modData;
        chanOut21 = sum(H21 .* encData, 2);
        chanOut12 = H12 .* repmat(modData, 1, 2);
        
        rxSig11 = awgn1Rx(chanOut11);
        rxSig21 = awgn1Rx(chanOut21);
        rxSig12 = awgn2Rx(chanOut12);
        
        decData = ostbcComb(rxSig21, H21);
        
        demod11 = bpskDemod(rxSig11 .* conj(H11));
        demod21 = bpskDemod(decData);
        demod12 = bpskDemod(sum(rxSig12 .* conj(H12), 2));
        
        ber_noDiver(:, idx) = errorCalc1(data, demod11);
        ber_Alamouti(:, idx) = errorCalc2(data, demod21);
        ber_MaxRatio(:, idx) = errorCalc3(data, demod12);
    end
    
    semilogy(ax, EbNo(1:idx), ber_noDiver(1, 1:idx), 'r*', ...
        EbNo(1:idx), ber_Alamouti(1, 1:idx), 'go', ...
        EbNo(1:idx), ber_MaxRatio(1, 1:idx), 'bs');
    legend(ax, 'No Diversity(1Tx, 1Rx)', 'Alamouti (2Tx, 1Rx', ...
        'Maximal-Ratio Combining (1Tx, 2Rx)');
    drawnow;
end

fitBER11 = berfit(EbNo, ber_noDiver(1, :));
fitBER21 = berfit(EbNo, ber_Alamouti(1, :));
fitBER12 = berfit(EbNo, ber_MaxRatio(1, :));
semilogy(ax, EbNo, fitBER11, 'r', EbNo, fitBER21, 'g', EbNo, fitBER12, 'b');
hold(ax, 'off');



N_MaxErrs = 300;
N_MaxPackets = 3000;
L_pilot = 8;
W = hadamard(L_pilot);
pilots = W(:, 1:N);
release(ostbcComb);
ostbcComb.NumReceiveAntennas = N;
s = rng(55408);

chan = comm.MIMOChannel('MaximumDopplerShift', 0, ...
    'SpatialCorrelationSpecification', 'None', ...
    'NumTransmitAntennas', N, ...
    'NumReceiveAntennas', M, ...
    'PathGainsOutputPort', true);

HEst = zeros(L_frame, N, M);
ber_Estimate = zeros(3, length(EbNo));
ber_Known = zeros(3, length(EbNo));

for idx = 1:length(EbNo)
    reset(errorCalc1);
    reset(errorCalc2);
    awgn2Rx.EbNo = EbNo(idx);
    while (ber_Estimate(2, idx) < N_MaxErrs) && ...
            (ber_Known(2, idx) < N_MaxErrs) && ...
            (ber_Estimate(3, idx)/L_frame < N_MaxPackets)
        data = randi([0 P-2], L_frame, 1);
        
        modData = bpskMod(data);
        
        encData = ostbcEnc(modData);
        
        txSig = [pilots; encData];
        
        reset(chan);
        [chanOut, H] = chan(txSig);
        
        rxSig = awgn2Rx(chanOut);
        
        HEst(1, :, :) = pilots(:, :).' * rxSig(1:L_pilot, :) / L_pilot;
        HEst = HEst(ones(L_frame, 1), :, :);
        
        decDataEst = ostbcComb(rxSig(L_pilot+1:end, :), HEst);
        decDataKnown = ostbcComb(rxSig(L_pilot+1:end, :), ...
            squeeze(H(L_pilot+1:end, :, :, :)));
        
        demodEst = bpskDemod(decDataEst);
        demodKnown = bpskDemod(decDataKnown);
        
        ber_Estimate(:, idx) = errorCalc1(data, demodEst);
        ber_Known(:, idx) = errorCalc2(data, demodKnown);
    end
end

semilogy(ax, EbNo(1:idx), ber_noDiver(1, 1:idx), 'r*-', ...
    EbNo(1:idx), ber_Alamouti(1, 1:idx), 'go-', ...
    EbNo(1:idx), ber_MaxRatio(1, 1:idx), 'bs-', ...
    EbNo(1:idx), ber_Estimate(1, 1:idx), 'md-', ...
    EbNo(1:idx), ber_Known(1, 1:idx), 'kv-');

legend(ax, 'No Diversity (1Tx, 1Rx)', 'Alamouti (2Tx, 1Rx)', ...
    'Maximal-Ratio Combining(1Tx, 2Rx)', 'Alamouti (2Tx, 2Rx) with pilot', ...
    'Alamouti (2Tx, 2Rx) with known channel');

drawnow;
hold(ax, 'off');
