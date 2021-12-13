function [crc, frame] = crc_gen(msg, poly)
[~, N] = size(poly);
mseg = [msg zeros(1, N-1)];
[q, r] = deconv(mseg, poly);
r = abs(r);

for i = length(q) : length(r)
    a = r(i);
    if mod(a, 2) == 0
        r(i) = 0;
    else
        r(i) = 1;
    end
end
crc = r(length(msg)+1 : end);
frame = bitor(mseg, r);