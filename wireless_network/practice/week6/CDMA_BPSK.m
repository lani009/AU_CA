clc; clear all;

% simulation parameters
nloop = 1;
noe = 0;
datalen = 20;
user = 3;
seq = 3;
stage = 3;
ptap1 = [1 3];
ptap2 = [2 3];
regi1 = [1 1 1];
regi2 = [1 1 1];
degree = 5;

freq = 6;
rate = 100;
t = [0:2*pi/(rate-1):2*pi];
carrier = sin(freq*t);

SNR = -20 : 1 : 10;
AWGN_sum = [];
AWGN_map = [];
BER_user = [];

% generation of the spreading code
switch seq
    case 1 
        m1 = mseq(stage, ptap1, regi1);
        m2 = mseq(stage, ptap2, regi2);
        code = goldseq(m1, m2, user);  
    case 2
        m1 = mseq(stage,ptap1, regi1);
        m2 = mseq(stage,ptap2, regi2);
        code = [goldseq(m1,m2,user), zeros(user, 1)];
    case 3
        tcode = walshcode(degree);
        code = tcode(1:user, :);
end

if seq < 3
    code = code*2-1;
end
clen = length(code);

data = rand(user, datalen) > 0.5;
spdata = spread(data*2-1, code);

if user == 1
    txdata = spdata;
else
    txdata = ones(user, 1)*sum(spdata);
end

for userIndex = 1 : user
    BER_avg = [];
    for ii = 1 : length(SNR)
        BER = []; 
        for jj = 1 : nloop 
            bpsk = [];
            for n = 1:length(txdata(userIndex, :))
                bpsk = [bpsk txdata(userIndex, n)*carrier];
            end

            [AWGN_bits AWGN_sig] = AWGN(bpsk, SNR(ii), txdata(userIndex, :));
            AWGN_sum = [AWGN_sum; AWGN_bits];
            
            dbpsk = [];
            for n = 1 : length(txdata(userIndex, :))
                demod = AWGN_sig((n-1)*100+1:n*100) .* carrier;
                tt = sum(demod / (99/2));
                if round(tt) > 0
                    dbpsk = [dbpsk 1];
                else
                    dbpsk = [dbpsk -1];
                end
            end

            despdata = despread(dbpsk, code(userIndex, :));
            demodata = despdata > 0;

            BER = [BER sum(xor(data(userIndex, :), demodata))/length(data(userIndex, :))];
        end

        BER_avg = [BER_avg mean(BER)];
        AWGN_map = [AWGN_map; mean(AWGN_sum,1)];
    end
    BER_user = [BER_user; BER_avg];
end

plot(SNR, BER_user(1,:),'r')
hold on
plot(SNR, BER_user(2,:),'g')
hold on
plot(SNR, BER_user(3,:),'b')