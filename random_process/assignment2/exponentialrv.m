%% 지수 분포
function [xVal]=exponentialrv(lambda, N)
    xVal = zeros(0, N);
    for i = 1 : N
        xVal(i) = -(1/lambda)*log(1-rand(1));
    end
end