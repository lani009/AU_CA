function check = crc_check(r_frame, poly)
[q, r] = deconv(r_frame, poly);
r = abs(r);

for i = length(q) : length(r)
    a = r(i);
    if mod(a, 2) == 0
        r(i) = 0;
    else
        r(i) = 1;
    end
end
check = r(length(q)+1 : end);