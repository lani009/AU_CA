function [fade_bits fade_sig] = Rayleigh(bpsk,bits)

type = 'Rayleigh';
% type = 'Rician';

if strcmp(type, 'Rayleigh') == 1
    mu = [0 ; 0];
    var = 1;
else
    mu = [0.5 ; 0.5];
    var = 1;
end

C = var * eye(2);
r = mvnrnd(mu, C, length(bpsk));
r_bit = mvnrnd(mu, C, length(bits));
% Convert to polar coordinates and compute magnitude and phase
z = r(:,1) + 1i*r(:,2);
z_bit = r_bit(:,1) + 1i*r_bit(:,2);
env = abs(z);
env_bit = abs(z_bit);
phi = angle(z);
fad(:,1) = sqrt(env);
fad_bit(:,1) = sqrt(env_bit);
fad(:,2) = phi;

% add fading
fade_sig = bpsk .* fad(:,1)';
fade_bits = bits + fad_bit(:,1)';
