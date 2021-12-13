function[mout]=mseq(stg,taps,inidata)
%*********************************************
% stg     : number of stages
% taps    : position of register feedback
% inidata : initial sequences
% mout    : output M sequence
%*********************************************
% M sequence
mout=zeros(1,2^stg-1); 
% 각각의 stage 값을 저장하는 배열
fpos=zeros(stg,1);
% tap 위치에 1
fpos(taps)=1;

for ii=1:2^stg-1
    % 첫번째 mout
    mout(1,ii)=inidata(stg);
    % XOR 연산 
    num=mod(inidata*fpos,2);
    % 오른쪽으로 Shift
    inidata=shift(inidata,1,0);
    inidata(1)=num;
end