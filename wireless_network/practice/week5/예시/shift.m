function[outregi]=shift(inregi, shiftr,shiftu)
% ********************************************
% inregi    : vector or matrix
% shiftr    : The amount of shift to the right
% shiftu    : The amount of shift to the top
% Outregi   : Register output
% ********************************************
num=length(inregi);
use = mod(shiftr, num);
back = inregi(1:num-use);
front = inregi(num-use+1: num);
%shift
outregi = [front back];