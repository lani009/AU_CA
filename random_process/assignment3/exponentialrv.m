%% exponential random variable
function x = exponentialrv(lambda, m)
x = -(1/lambda)*log(1-rand(m,1));
end