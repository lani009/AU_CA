clc; clear all;

%**********parameter setting***********
nloop=50;
noe=0;
datalen=10;
user=2;
seq=3; % 1=gold, 2=orthogonal gold, 3= walsh
stage=3;
ptap1=[1 3];
ptap2=[2 3];
regi1=[1 1 1];
regi2=[1 1 1];
degree=3;

%*********SNR, AWGN parameter setting****************
SNR_start=-20;
SNR_end=10;
SNR=SNR_start:1:SNR_end;
bit_len=1000; % ������ 1000
BER=[];
BER_avg=[];
AWGN_sum=[];
AWGN_map=[];
Final_BER=[];

%*********generation of spreading code********
switch seq
    
    % gold seq
    case 1 
        m1=mseq(stage, ptap1, regi1);
        m2=mseq(stage, ptap2, regi2);
        code=goldseq(m1,m2,user);
    
    % orthogonal gold     
    case 2
        m1=mseq(stage,ptap1, regi1);
        m2=mseq(stage,ptap2, regi2);
        code=[goldseq(m1,m2,user),zeros(user,1)];
    
    % walsh
    case 3
        tcode=walshcode(degree);
        code=tcode(1:user, :);
end

if seq<3
    code=code*2-1;
end
    clen=length(code);

        
 % **************Transmitter*************
        % �־��� �ڵ忡 ���ؼ� spreading ���� 
        data=rand(user,datalen)>0.5;
        spdata=spread(data*2-1,code);
        
        % ������ �������� ��, ������ �ռ�
        if user==1
            txdata=spdata;
        else
            txdata=ones(user,1)*sum(spdata);
        end
        
 %********** BPSK, BER ***************** 
for unum=1:user
    BER_avg=[];
    for ii=1:length(SNR)
        BER=[]; 
        for sim=1:nloop 

        % ������ �Է� �޾� bpsk ����
        [bpsk]=Mod_bpsk(txdata(unum,:));
        % ���Ŵ��� AWGN noise ����
        [AWGN_bits AWGN_sig]=AWGN(bpsk, SNR(ii),txdata(unum,:));
        AWGN_sum=[AWGN_sum; AWGN_bits];
        % BPSK ���� ����Ͽ� ���� ������ ����
        [dbpsk]=dMod_bpsk(AWGN_sig,txdata(unum,:));
        despdata=despread(dbpsk,code(unum,:)); %despreading
        demodata=despdata>0;

        % ���� �����Ϳ� ���Ͽ� BER ����
        BER=[BER sum(xor(data(unum,:),demodata))/length(data(unum,:))];
        end

        BER_avg=[BER_avg mean(BER)];
        AWGN_map=[AWGN_map; mean(AWGN_sum,1)];
    end
    Final_BER=[Final_BER ;BER_avg];
end

%*********Plot*****************
f1 = figure('Name', '�� user�� BER');
grid on
plot(SNR,Final_BER(1,:),'b')
hold on
plot(SNR,Final_BER(2,:),'r')
hold on
plot(SNR,Final_BER(3,:),'g')
