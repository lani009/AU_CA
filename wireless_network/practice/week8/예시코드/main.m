clear all;
clc;
 
global STANDBY TRANSMIT COLLISION
global Srate Plen
global Mnum Mplen Mstate
global Tint Rint
global Spnum Splen Tplen Wtime

throughput = [];
offeredload = [];
theorys = [];

STANDBY = 0;
TRANSMIT = 1;
COLLISION = 2;

protocol={ 'paloha' 'saloha' };

Srate=256e3;  % symbol rate
Plen=128;     % length of a packet

Mnum=100; % 단말 수 
 
pno=1;       % protocol number  
spend=10000;   % number of packets that simulation is finished 
Ttime=Plen/Srate;    % transmission time of one packet

for G=[0.1:0.1:2]      % offered traffic
    fprintf("%f\n", G);
    Tint=-Ttime / log(1-G/Mnum);  % expectation value of the packet generation interval
    Rint=Tint;                   % expectation value of packet resending interval 
    Spnum=0;                     % sending packet number
    Splen=0;                      
    Tplen=0;                     
    Wtime=0;
   
    now_time = feval(char(protocol(pno)),-1); % initialize of the access terminals
    
    while 1
        next_time = feval(char(protocol(pno)),now_time); % paloha start
        if Spnum >= spend % paloha end
            break
        end  
        
        idx=find(Mstate==TRANSMIT | Mstate==COLLISION); % 단말의 state가 전송이거나 충돌인 경우 인덱스 리턴 
        if length(idx)>1
           Mstate(idx) = COLLISION;  % collision occurs
        end
        now_time=next_time; % now_time 리셋
    end
    
    offeredload=[offeredload G]; % measure of traffic in queue
     
    temp=Splen/Srate/now_time;
    throughput= [throughput temp];
    
    % 이론적 처리율
    if pno==1
        temp=G*exp(-2*G); % paloha
    else
        temp=G*exp(-G);   % saloha
    end
    theorys=[theorys temp];
    
end

plot(offeredload, throughput,'o')
hold on 
plot(offeredload, theorys)


