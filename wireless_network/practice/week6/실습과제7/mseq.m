function[mout]=mseq(stg,taps,inidata)
%*********************************************
% stg     : number of stages
% taps    : position of register feedback
% inidata : initial sequences
% mout    : output M sequence
%*********************************************
% M sequence
mout=zeros(1,2^stg-1); 
% ������ stage ���� �����ϴ� �迭
fpos=zeros(stg,1);
% tap ��ġ�� 1
fpos(taps)=1;

for ii=1:2^stg-1
    % ù��° mout
    mout(1,ii)=inidata(stg);
    % XOR ���� 
    num=mod(inidata*fpos,2);
    % ���������� Shift
    inidata=shift(inidata,1,0);
    inidata(1)=num;
end