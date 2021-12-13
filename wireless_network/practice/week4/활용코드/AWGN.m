function [AWGN_bits AWGN_sig] = AWGN(fade_sig,SNR,fade_bits)

% generate noise signal
EbNo_scale = 10^(SNR/10);               % conversion of EbNo to real scale
sigma = sqrt(1./(2*EbNo_scale));        % calculate standard deviation of noise
noise = randn(size(fade_sig)) .* sigma; % generate noise
noise_bit = randn(size(fade_bits)) .*sigma;

% add noise to original signal
AWGN_sig = fade_sig + noise;
AWGN_bits = fade_bits + noise_bit;