numOfSat = 10; % GPS 위성의 개수
numOfStage = 10; % G1, G2의 stage 개수
SV = [ 2 6; 3 7; 4 8; 5 9; 1 9; 2 10; 1 8; 2 9; 3 10; 2 3];
G1Taps = [3, 10];
G2Taps = [2, 3, 6, 8, 9, 10];
G1Fpos = zeros(numOfStage, 1);
G2Fpos = zeros(numOfStage, 1);
G1Fpos(G1Taps) = 1;
G2Fpos(G2Taps) = 1;
CAOutput = zeros(numOfSat, numOfStage^2-1);

for i = 1 : numOfSat
    G1Inidata = ones(1, 10);
    G2Inidata = ones(1, 10);
    SVFpos = zeros(numOfStage, 1);
    SVFpos(SV(i, :)) = 1;
    for j = 1 : 2^numOfStage-1
        num = mod(G1Inidata*G1Fpos, 2);
        G1Inidata = shift(G1Inidata, 1, 0);
        G1Inidata(1) = num;

        num = mod(G2Inidata*G2Fpos, 2);
        G2Inidata = shift(G2Inidata, 1, 0);
        G2Inidata(1) = num;

        CAOutput(i, j) = mod(G1Inidata(10)+mod(G2Inidata*SVFpos, 2), 2);
    end
end

a = crosscorr(CAOutput(1, :), CAOutput(1, :));
stem(a);