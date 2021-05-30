%% 기하 분포
function [xVal]=geometricrv(p, m)
    xVal = ceil(exponentialrv(-log(1-p), m));
end